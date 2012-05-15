package com.k_int.made4u.job.manufacturingOrder

import com.k_int.made4u.job.TimerData;
import com.k_int.made4u.job.TimerType;
import com.k_int.made4u.job.TimerStatus;
import com.k_int.made4u.job.OrderRequest;
import com.k_int.made4u.job.OrderStatus;

import com.k_int.made4u.job.DesignRequest;
import com.k_int.made4u.serviceInputs.LensCalcInput;
import com.k_int.made4u.serviceInputs.LensCalcOutput;

import com.k_int.made4u.frame.Frame;


import com.k_int.made4u.customer.CustomerChoices;
import com.k_int.made4u.serviceInputs.OpticalPrescription;
import com.k_int.made4u.serviceInputs.WCSInput;


import org.codehaus.groovy.grails.commons.ConfigurationHolder


class ManufacturingOrderWCSSubmissionTimerJob {
    def timeout = 300000l // execute job every five minutes
    def startDelay = 120000l; // Only start after 2 minutes
//    def startDelay = 12000000l; // Only start after some long time..

    // Lens calculation service
    def lensCalculationService
    // WCS service
    def webControlSystemService;


    def execute() {
        log.debug("Timer task due for the final part of manufacturing order requests - submitting the order to the WCS. Date: " + new Date());
        def newLastRunTime = new Date();
        
        
        // Go and get the timer data from the database so we can check that something's not running already
        def timerData = TimerData.findByTimerType(TimerType.MANUFACTURING_ORDER_WCS_SUBMISSION);
        if ( !timerData ) {
            // We don't have any timer data - create one so that we can start processing
            timerData = new TimerData(timerStatus: TimerStatus.IDLE, lastRunTime: new Date(), timerType: TimerType.MANUFACTURING_ORDER_WCS_SUBMISSION).save(flush: true);
        }
        // By now we'll have the timer data - check to see whether anything else is already using it
        if ( timerData.timerStatus == TimerStatus.IDLE ) {
            
            // We're not running already, so update the status so that any other threads don't start processing
            timerData.timerStatus = TimerStatus.RUNNING;
            timerData.runInterval = timeout;
            timerData.save(flush: true);

            // Work out the base directory where all relevant files will be, etc.
            def uploadDir = ConfigurationHolder.config.com.k_int.made4u.order.directory
            if ( !uploadDir.endsWith(File.separator) )
                uploadDir = uploadDir + File.separator;
                
            def designDir = ConfigurationHolder.config.com.k_int.made4u.design.directory;
            if ( !designDir.endsWith(File.separator) )
                designDir = designDir + File.separator;

            // Now go and find all manufacturing orders that are waiting for frame calculations to return
            def allRelevantOrderRequests = OrderRequest.findAllByRequestStatus(OrderStatus.AWAITING_WCS_SUBMISSION);

            if ( allRelevantOrderRequests ) {
                log.debug("We have found " + allRelevantOrderRequests.size + " relevant order requests that are waiting to be submitted to the WCS");

                // Loop through each of the order requests and process them
                for ( def thisOrder in allRelevantOrderRequests ) {
                    def processOutput = processOrder(thisOrder, uploadDir, designDir);
                }
            }
            else {
                log.debug("No relevant manufacturing order requests awaiting submission to the WCS. Date: " + newLastRunTime);
            }


            // We're all finished - update the last run time in the database and clear the RUNNING status
            timerData.timerStatus = TimerStatus.IDLE;
            timerData.lastRunTime = newLastRunTime;
            timerData.save(flush: true);
            
        } else {
            // Orders already being processed - don't do anything
            log.debug("Already processing manufacturing orders awaiting submission to the WCS so not starting again");
        }
        
    }
    
    def processOrder(OrderRequest order, uploadDir, designDir) {
        log.debug("Processing order: " + order.id);
                
        // Work out the paths to various files used here, etc.
        def jobBaseDir = uploadDir + order.id;
        def designBaseDir = designDir + order.linkedDesignId;

        def wcsDir = jobBaseDir + File.separator + "wcsFiles";
        def wcsInputFilename = "wcsInput.json";
        
        

        // Read the WCS input back from disk so that we can submit it to the WCS
        def fullWcsInput = parseWCSInput(wcsDir, wcsInputFilename);

        if ( fullWcsInput != null ) {
            // Submit the order to the WCS
            submitOrder(order, fullWcsInput.inputStructure, fullWcsInput.jobNumber, wcsDir, "wcsOutput.json");
        } else {
            // No input file - error
            order.requestStatus = OrderStatus.ERROR;
            order.messages.add("Unable to find the WCS input file to work out what to submit to the WCS");
            order.save(flush:true);
        }
    }
    
    def parseWCSInput(wcsDir, inputFile) {
        
        def wcsInputData = null;
        
        File actualFile = new File(wcsDir, inputFile);
        if ( actualFile.exists() ) {
            wcsInputData = WCSInput.parseInput(actualFile)
        } else {
            // Input file hasn't been created - complain..
            log.error("No WCS input file found on the disk to parse... Filename: " + wcsDir + "/" + inputFile);
        }
        
        return wcsInputData;
    }
    
    def submitOrder(order, wcsInput, jobNumber, wcsDir, outputFilename) {
        
        order.wcsSubmissionTime = new Date();
        order.requestStatus = OrderStatus.SUBMITTING_ORDER_TO_WCS;
        order.save(flush:true);

        def wcsResponse = webControlSystemService.submitOrder(wcsInput, jobNumber, wcsDir, outputFilename)

        if ( wcsResponse.success ) {
            // Submitted successfully...
            order.requestStatus = OrderStatus.ORDER_SUBMITTED_TO_WCS;
            order.save(flush:true);
        } else {
            // Failure submitting..
            order.requestStatus = OrderStatus.ERROR;
            order.statusMessages.add(wcsResponse.message);
            order.save(flush:true);
        }

    }
}
