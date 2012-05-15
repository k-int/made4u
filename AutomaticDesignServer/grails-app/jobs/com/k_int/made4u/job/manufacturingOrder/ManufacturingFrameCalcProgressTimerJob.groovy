package com.k_int.made4u.job.manufacturingOrder

import com.k_int.made4u.job.TimerData;
import com.k_int.made4u.job.TimerType;
import com.k_int.made4u.job.TimerStatus;
import com.k_int.made4u.job.OrderRequest;
import com.k_int.made4u.job.OrderStatus;

import com.k_int.made4u.job.DesignRequest;

import com.k_int.made4u.customer.CustomerChoices;

import org.codehaus.groovy.grails.commons.ConfigurationHolder


class ManufacturingFrameCalcProgressTimerJob {
    def timeout = 900000l // execute job every 15 minutes
    def startDelay = 120000l; // Only start after 2 minutes
//    def startDelay = 12000000l; // Only start after some long time..

    // Frame manufacturing calculation service
    def framesManufacturingCalculationService

    def execute() {
        log.debug("Timer task due for manufacturing order requests - checking frame calculation progress. Date: " + new Date());
        def newLastRunTime = new Date();
        
        
        // Go and get the timer data from the database so we can check that something's not running already
        def timerData = TimerData.findByTimerType(TimerType.MANUFACTURING_ORDER_FRAME_CALC_PROGRESS);
        if ( !timerData ) {
            // We don't have any timer data - create one so that we can start processing
            timerData = new TimerData(timerStatus: TimerStatus.IDLE, lastRunTime: new Date(), timerType: TimerType.MANUFACTURING_ORDER_FRAME_CALC_PROGRESS).save();
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

            // Now go and find all manufacturing orders that are waiting for frame calculations to return
            def allRelevantOrderRequests = OrderRequest.findAllByRequestStatus(OrderStatus.FRAME_CALCULATION_SUBMITTED);

            if ( allRelevantOrderRequests ) {
                log.debug("We have found " + allRelevantOrderRequests.size + " relevant order requests that are waiting for frames calculation results");

                // Loop through each of the order requests and process them
                for ( def thisOrder in allRelevantOrderRequests ) {
                    def processOutput = processOrder(thisOrder, uploadDir);
                }
                
            }
            else {
                log.debug("No relevant manufacturing order requests awaiting frames calculation results to process. Date: " + newLastRunTime);
            }


            // We're all finished - update the last run time in the database and clear the RUNNING status
            timerData.timerStatus = TimerStatus.IDLE;
            timerData.lastRunTime = newLastRunTime;
            timerData.save(flush: true);
            
        } else {
            // Orders already being processed - don't do anything
            log.debug("Already processing manufacturing orders that are awaiting frames calculation results so not starting again");
        }
        
    }
    
    def processOrder(OrderRequest order, uploadDir) {
        log.debug("Processing order: " + order.id);
                
        // Work out the paths to various files used here, etc.
        def jobBaseDir = uploadDir + order.id;
        def frameManufacturingDir = jobBaseDir + File.separator + "frameManufacturingCalcFiles";
        def inputFilename = "manufactureInput.xml";
        def progressFilename = "manufactureProgress.xml";
        def outputFilename = "manufactureOutput.xml";

        
        // Talk to the frame server with the particular frame details to see if the job has finished
        def progressOutput = framesManufacturingCalculationService.getCalcStatus(frameManufacturingDir, inputFilename, progressFilename);
        
        if ( progressOutput.success ) {
            // Look at the return value from the get status call to check and see whether we have a pending request or whether the request has been completed
            def actualProgressFile = new File(frameManufacturingDir, progressFilename);
            if ( actualProgressFile.exists() ) {

                def processOngoing;
                
                try {
                    processOngoing = processCalcResponse(frameManufacturingDir, progressFilename);
                } catch (Exception e) {
                    log.error("Exception thrown when trying to process the frame calculation output to see if it's still running: " + e.getMessage());
                    e.printStackTrace();
                    
                    order.requestStatus = OrderStatus.ERROR;
                    order.statusMessages.add("Error thrown when parsing the frame manufacture calculation progress output file: " + e.getMessage());
                    order.save(flush: true);
                }
                
                if ( order.requestStatus != OrderStatus.ERROR ) {
                    if ( processOngoing ) {
                        // Don't do anything - just carry on..
                    } else {
                        // Process is completed 

                        // Copy the progress output file so that we can get it later for continued processing (removing any old files first)
                        def outputFile = new File(frameManufacturingDir, outputFilename);
                        if ( outputFile.exists() )
                            outputFile.delete();

                        new File(frameManufacturingDir, outputFilename) << new File(frameManufacturingDir, progressFilename).text;

                        // Now remember that we're done here
                        order.requestStatus = OrderStatus.FRAME_CALCULATION_COMPLETED;
                        order.framesCalcMarkedAsCompletedTime = new Date();
                        order.save(flush: true);
                    }
                } else {
                    // Error when processing frame calc progress output - already handled
                }
            } else {
                // File doesn't exist and so can't be parsed - fail gracefully
                order.requestStatus = OrderStatus.ERROR;
                order.statusMessages.add("Unable to find the frame manufacture calculation progress output file to be processed.");
                order.save(flush:true);
            }
            
        } else {
            // Something went wrong talking to the service - log it and return..
            log.error("Error returned when getting the status of a previously submitted calculation");
            order.requestStatus = OrderStatus.ERROR;
            order.statusMessages.add(progressOutput.message);
            order.save(flush: true);
        }
        
    }
    
    /**
     * Process the output file from the frame calculation status request
     * @param workingDirectory The path to the directory containing the file to be inspected
     * @param responseFilename The filename of the file to be inspected
     * @return boolean true if the frame calculation is still pending, false if it has completed
     */
    def processCalcResponse(workingDirectory, responseFilename) {
        
        def retval = false;
        
        // Open the file and look for a <made4u:pending_order> element that signifies that the order is still 
        // awaiting or being processed
        
        // Open and parse the file
        def responseData = new XmlSlurper().parseText(new File(workingDirectory, responseFilename).text).declareNamespace(made4u: 'http://made4u.info/',soap: 'http://www.w3.org/2001/12/soap-envelope');

        responseData.'soap:Body'.'made4u:made4u'.each {
            def pendingOrder = it.'made4u:pending_order'.text();

            log.debug("Pending order contents: " + pendingOrder);

            if ( pendingOrder ) {
                // The order is still pending
                retval = true;
            }
        }

        return retval;
    }
    
}
