package com.k_int.made4u.job.manufacturingOrder

import com.k_int.made4u.job.TimerData;
import com.k_int.made4u.job.TimerType;
import com.k_int.made4u.job.TimerStatus;
import com.k_int.made4u.job.OrderRequest;
import com.k_int.made4u.job.OrderStatus;

import com.k_int.made4u.job.DesignRequest;


import com.k_int.made4u.serviceInputs.OpticalPrescription;
import com.k_int.made4u.customer.CustomerChoices;

import org.codehaus.groovy.grails.commons.ConfigurationHolder


class ManufacturingOrderTimerJob {
    def timeout = 300000l // execute job every 5 minutes
    def startDelay = 120000l; // Only start after 2 minutes
//    def startDelay = 12000000l; // Only start after some long time..

    
    // Frame manufacturing calculation service
    def framesManufacturingCalculationService

    def execute() {
        log.debug("Timer task due for manufacturing order requests. Date: " + new Date());
        def newLastRunTime = new Date();
        
        
        // Go and get the timer data from the database so we can check that something's not running already
        def timerData = TimerData.findByTimerType(TimerType.MANUFACTURING_ORDER);
        if ( !timerData ) {
            // We don't have any timer data - create one so that we can start processing
            timerData = new TimerData(timerStatus: TimerStatus.IDLE, lastRunTime: new Date(), timerType: TimerType.MANUFACTURING_ORDER).save();
        }

        // By now we'll have the timer data - check to see whether anything else is already using it
        if ( timerData.timerStatus == TimerStatus.IDLE ) {
            
            // We're not running already, so update the status so that any other threads don't start processing
            timerData.timerStatus = TimerStatus.RUNNING;
            timerData.runInterval = timeout;
            timerData.save(flush: true);

            // Work out the base directory where all relevant files will be, etc.
            def orderDir = ConfigurationHolder.config.com.k_int.made4u.order.directory
            if ( !orderDir.endsWith(File.separator) )
                orderDir = orderDir + File.separator;
                
            def designDir = ConfigurationHolder.config.com.k_int.made4u.design.directory
            if ( !designDir.endsWith(File.separator) ) 
                designDir = designDir + File.separator;

            // Now go and find all manufacturing orders submitted after
            // the date that the timer last ran
            def allRelevantOrderRequests = OrderRequest.findAllByRequestStatusAndOrderRequestSubmittedTimeLessThan(OrderStatus.REQUEST_SUBMITTED, newLastRunTime);

            if ( allRelevantOrderRequests ) {
                log.debug("We have found " + allRelevantOrderRequests.size + " relevant order requests");

                // Loop through each of the order requests and process them
                for ( def thisOrder in allRelevantOrderRequests ) {
                    def processOutput = processOrder(thisOrder, orderDir, designDir);
                }
                
            }
            else {
                log.debug("No relevant manufacturing order requests to process. Date: " + newLastRunTime);
            }


            // We're all finished - update the last run time in the database and clear the RUNNING status
            timerData.timerStatus = TimerStatus.IDLE;
            timerData.lastRunTime = newLastRunTime;
            timerData.save(flush: true);
            
        } else {
            // Orders already being processed - don't do anything
            log.debug("Already processing manufacturing orders so not starting again");
        }
        
    }
    
    def processOrder(OrderRequest order, orderDir, designDir) {
        log.debug("Processing order: " + order.id);
                
        // Work out the paths to various files used here, etc.
        def jobBaseDir = orderDir + order.id;
        def customerChoicesFilePath = jobBaseDir + File.separator + "inputFiles" + File.separator + "customerChoices";
        
        def frameManufacturingDir = jobBaseDir + File.separator + "frameManufacturingCalcFiles";
        new File(frameManufacturingDir).mkdir();


        
        // Parse the customer choices
        order.inputParsingStartTime = new Date();
        order.requestStatus = OrderStatus.PROCESSING_INPUT_FILES;
        order.save(flush:true);
        
        def customerChoices;
        
        try {
            customerChoices = CustomerChoices.parseFile(new File(customerChoicesFilePath));
            log.debug("Finished parsing the customer choices..." + customerChoices.getFrameId());
        } catch (Exception e) {
            log.error("Exception caught when parsing the customer choices: " + e.getMessage());
            e.printStackTrace();
            
            order.requestStatus = OrderStatus.ERROR;
            order.statusMessages.add("Error thrown when parsing the customer choices file: " + e.getMessage());
            order.save(flush: true);
        }
                
        
        if ( order.requestStatus != OrderStatus.ERROR ) {
            def ftpPrefix = ConfigurationHolder.config.com.k_int.made4u.ftp.prefix;
            if ( !ftpPrefix.endsWith("/") )
                ftpPrefix = ftpPrefix + "/";

            def custChoiceFrameInput = customerChoices.toFrameCalcInputString("made4u", ftpPrefix);


            // Find the design request based on the order id so that we can work out where to get the 
            // design request files from
            def designBaseDir = designDir + order.linkedDesignId;
            def initialInputDir = designBaseDir + File.separator + "inputFiles";

            order.inputParsingEndTime = new Date();
            order.save(flush:true);

            // Parse the prescription
            def optPreFilePath = initialInputDir + File.separator + "opticalPrescription";
            def optPre = OpticalPrescription.parsePrescription(new File(optPreFilePath));
                
            optPre.setJob(customerChoices.getJobNumber());
                
            def opticalPrescriptionData = optPre.toOmaOutput();


            // Submit the calculation to the frame calculation service
            if ( order.requestStatus != OrderStatus.ERROR ) {
                log.error("About to call the frames manufacturing calculation service...");
                def submissionOutput = performFrameManufacturingCalculation(order, opticalPrescriptionData, initialInputDir + File.separator + "morphologicalData", initialInputDir + File.separator + "nasalMesh", custChoiceFrameInput, frameManufacturingDir, "manufactureInput.xml", "manufactureSubmissionOutput.xml" );

                if (!submissionOutput.success) {
                    // Something went wrong with the submission..
                    log.error("Error returned when submitting a manufacturing frame calculation");
                    order.requestStatus = OrderStatus.ERROR;
                    order.statusMessages.add(submissionOutput.message);
                    order.save(flush:true);

                } else {
                    log.debug("About to log that this stage of processing is finished and allow the next stage to start");
                    order.requestStatus = OrderStatus.FRAME_CALCULATION_SUBMITTED;
                    order.save(flush:true);                
                }
            }                    
        } else {
            // Error processing customer input - already processed so don't do anything
        }        
    }
    
    
    /**
     * Wrap the communications with the Frame calculation service parsing the output and returning it in a useful format
     */
    def performFrameManufacturingCalculation(order, optPreData, morphoFilePath, meshFilePath, customerChoiceInputData, frameManufacturingCalcDir, inputFilename, outputFilename) {
        
        order.requestStatus = OrderStatus.FRAME_CALCULATION;
        order.framesCalcSubmissionTime = new Date();
        order.save(flush: true)
        
        log.debug("in the performFrameCalculation method");
        
        def calcOutput = framesManufacturingCalculationService.submitCalc(optPreData, morphoFilePath, meshFilePath, customerChoiceInputData, frameManufacturingCalcDir, inputFilename, outputFilename);

        // Remember the time when the calculation returned from submission
        order.framesCalcSubmissionEndTime = new Date();
        order.save(flush: true);
        
        return calcOutput;
    }

    
}
