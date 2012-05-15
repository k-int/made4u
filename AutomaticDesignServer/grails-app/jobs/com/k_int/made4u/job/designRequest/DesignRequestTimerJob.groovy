package com.k_int.made4u.job.designRequest

import com.k_int.made4u.job.TimerData;
import com.k_int.made4u.job.TimerType;
import com.k_int.made4u.job.TimerStatus;

import com.k_int.made4u.job.DesignRequest;
import com.k_int.made4u.job.DesignStatus;

import com.k_int.made4u.frame.Frame;
import com.k_int.made4u.frame.FrameDimensions;
import com.k_int.made4u.frame.FrameTempleDimensions;
import com.k_int.made4u.lens.LensMaterial;
import com.k_int.made4u.lens.LensPrescriptionProperties;
import com.k_int.made4u.lens.LensType;
import com.k_int.made4u.frame.RimType;

import java.text.ParseException;

import java.util.TreeMap;
import java.util.HashSet;

import com.k_int.made4u.oma.OMA;
import com.k_int.made4u.serviceInputs.LensCalcInput;
import com.k_int.made4u.serviceInputs.LensCalcOutput;
import com.k_int.made4u.serviceInputs.OpticalPrescription;
import com.k_int.made4u.design.DesignData;

import com.k_int.made4u.design.CreateDesignResponse;

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class DesignRequestTimerJob {
    def timeout = 60000l // execute job once in 60 seconds
    def startDelay = 120000l // Only start after 2 mins

    // Frame calculation service
    def framesCalculationService
    // Lens calculation service
    def lensCalculationService

    def execute() {
        log.debug("Timer task due for design requests. Date: " + new Date());
        def newLastRunTime = new Date();
        
        
        // Go and get the timer data from the database so we can check that something's not running already
        def timerData = TimerData.findByTimerTypeAndTimerStatus(TimerType.DESIGN_REQUEST, TimerStatus.IDLE);

        if ( timerData ) {
            
            // Update the status so that any other threads don't start processing
            timerData.timerStatus = TimerStatus.RUNNING;
            timerData.runInterval = timeout;
            timerData.save(flush: true);

            // Work out the base directory where all relevant files will be, etc.
            def uploadDir = ConfigurationHolder.config.com.k_int.made4u.design.directory
            if ( !uploadDir.endsWith(File.separator) )
            uploadDir = uploadDir + File.separator;


            // Now go and find all design requests that haven't been processed yet
            def allRelevantDesignRequests = DesignRequest.findAllByRequestStatusAndRequestSubmittedTimeLessThan(DesignStatus.REQUEST_SUBMITTED, newLastRunTime);

            if ( allRelevantDesignRequests ) {
                log.debug("We have found " + allRelevantDesignRequests.size + " relevant design requests");

                // Loop through each of the design requests and process them
                for ( def thisRequest in allRelevantDesignRequests ) {
                    def processOutput = processRequest(thisRequest, uploadDir);
                }
            }
            else {
                log.debug("No relevant design requests to process. Date: " + newLastRunTime);
            }


            // We're all finished - update the last run time in the database and clear the RUNNING status]
            timerData.timerStatus = TimerStatus.IDLE;
            timerData.lastRunTime = newLastRunTime;
            timerData.save(flush: true);
            
        } else {
            // Orders already being processed - don't do anything
            log.debug("Already processing design requests so not starting again");
        }
        
        
    }
    
    def processRequest(designReq, uploadDir) {
        log.debug("Processing design request: " + designReq.id);
        
        // Make a directory to hold the files going to / from the server
        def jobBaseDir = uploadDir + designReq.id;
        def initialInputDir = jobBaseDir + File.separator + "inputFiles";


        def frameDir = jobBaseDir + File.separator + "frameCalcFiles";
        new File(frameDir).mkdir();

        def lensDir = jobBaseDir + File.separator + "lensCalcFiles";
        new File(lensDir).mkdir();
        
        // Parse the prescription - Not actually needed, just stops us falling over later if we can't parse it
        def prescrip;
        try {
            log.error("About to parse the optical prescription..");
            prescrip = OpticalPrescription.parsePrescription(new File(initialInputDir + File.separator + "opticalPrescription"));
            log.error("Returned from parsing the optical prescription");
        } catch (NumberFormatException nfe) {
            log.error("Exception caught when parsing the optical prescription");
            designReq.requestStatus = DesignStatus.ERROR;
            designReq.statusMessages.add("Error reading in the optical prescription");
            designReq.save(flush:true);
        } catch (ParseException pe) {
            log.error("Parsing exception caught when parsing the optical prescription: " + pe.getMessage());
            designReq.requestStatus = DesignStatus.ERROR;
            designReq.statusMessages.add("Parsing error when reading the optical prescription: " + pe.getMessage());
            designReq.save(flush:true);
        }
        
        // Talk to the frame calculation server
        if ( designReq.requestStatus != DesignStatus.ERROR ) {
            log.error("About to call the frames calculation service...");
            def framesRet = performFrameCalculation(designReq, initialInputDir + File.separator + "opticalPrescription", initialInputDir + File.separator + "morphologicalData", initialInputDir + File.separator + "nasalMesh", frameDir, "input.xml", "output.xml" );
        }                    
        
        if ( designReq.requestStatus != DesignStatus.ERROR ) {
            log.error("About to log that this stage of processing is finished and allow stage 2 to start");
            designReq.requestStatus = DesignStatus.AWAITING_PROCESSING_PART_TWO;
            designReq.save(flush:true);
        }
    }
    
    
    /**
     * Wrap the communications with the Frame calculation service parsing the output and returning it in a useful format
     */
    def performFrameCalculation(designRequest, optPreFilePath, morphoFilePath, meshFilePath, frameCalcDir, inputFilename, outputFilename) {
        
        designRequest.requestStatus = DesignStatus.FRAMES_CALCULATION;
        designRequest.framesCalcSubmissionTime = new Date();
        designRequest.save(flush: true)
        
        log.debug("in the performFrameCalculation method");
        
        def calcOutput = framesCalculationService.calc(optPreFilePath, morphoFilePath, meshFilePath, frameCalcDir, inputFilename, outputFilename);

        if ( calcOutput.success ) {
            // Remember the time when the calculation returned
            designRequest.framesCalcReturnTime = new Date();
            designRequest.save(flush: true);
        } else {
            designRequest.requestStatus = DesignStatus.ERROR;
            designRequest.statusMessages.add("Frame calculation submission failed with message: " + calcOutput.message);
            designRequest.framesCalcReturnTime = new Date();
            designRequest.save(flush: true);
        }        
        return calcOutput.success;
    }
    
}
