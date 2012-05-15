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


import org.codehaus.groovy.grails.commons.ConfigurationHolder


class ManufacturingOrderPart2TimerJob {
    def timeout = 300000l // execute job every five minutes
    def startDelay = 120000l; // Only start after 2 minutes
//    def startDelay = 12000000l; // Only start after some long time..

    // Lens calculation service
    def lensCalculationService
    // WCS service
    def webControlSystemService;


    def execute() {
        log.debug("Timer task due for part two of manufacturing order requests - continuing after frame calculation. Date: " + new Date());
        def newLastRunTime = new Date();
        
        
        // Go and get the timer data from the database so we can check that something's not running already
        def timerData = TimerData.findByTimerType(TimerType.MANUFACTURING_ORDER_PART_TWO);
        if ( !timerData ) {
            // We don't have any timer data - create one so that we can start processing
            timerData = new TimerData(timerStatus: TimerStatus.IDLE, lastRunTime: new Date(), timerType: TimerType.MANUFACTURING_ORDER_PART_TWO).save();
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
            def allRelevantOrderRequests = OrderRequest.findAllByRequestStatus(OrderStatus.FRAME_CALCULATION_COMPLETED);

            if ( allRelevantOrderRequests ) {
                log.debug("We have found " + allRelevantOrderRequests.size + " relevant order requests that have completed frames calculation and need continued processing");

                // Loop through each of the order requests and process them
                for ( def thisOrder in allRelevantOrderRequests ) {
                    def processOutput = processOrder(thisOrder, uploadDir, designDir);
                }
            }
            else {
                log.debug("No relevant manufacturing order requests with completed frames calculation awaiting further processing. Date: " + newLastRunTime);
            }


            // We're all finished - update the last run time in the database and clear the RUNNING status
            timerData.timerStatus = TimerStatus.IDLE;
            timerData.lastRunTime = newLastRunTime;
            timerData.save(flush: true);
            
        } else {
            // Orders already being processed - don't do anything
            log.debug("Already processing manufacturing orders with completed frames calculations so not starting again");
        }
        
    }
    
    def processOrder(OrderRequest order, uploadDir, designDir) {
        log.debug("Processing order: " + order.id);
                
        // Work out the paths to various files used here, etc.
        def jobBaseDir = uploadDir + order.id;
        def designBaseDir = designDir + order.linkedDesignId;
        
        def customerChoicesFilePath = jobBaseDir + File.separator + "inputFiles" + File.separator + "customerChoices";
        def prescriptionFilePath = designBaseDir + File.separator + "inputFiles" + File.separator + "opticalPrescription"

        def frameManufacturingDir = jobBaseDir + File.separator + "frameManufacturingCalcFiles";
        def outputFilename = "manufactureOutput.xml";
        
        def lensManufacturingDir = jobBaseDir + File.separator + "lensManufacturingCalcFiles";
        def actualLensDir = new File(lensManufacturingDir);
        actualLensDir.mkdir();
        
        def coatingsDir = jobBaseDir + File.separator + "coatingFiles";
        def actualCoatingsDir = new File(coatingsDir);
        actualCoatingsDir.mkdir();

        def wcsDir = jobBaseDir + File.separator + "wcsFiles";
        def actualWcsDir = new File(wcsDir);
        actualWcsDir.mkdir();

        
        // Process the customer choices and their prescription
        def customerChoices;
        def prescriptionData;
        
        try {
            customerChoices = CustomerChoices.parseFile(new File(customerChoicesFilePath));
            prescriptionData = OpticalPrescription.parsePrescription(new File(prescriptionFilePath));
            prescriptionData.setJob(customerChoices.getJobNumber());
            log.debug("Straight after parsing.. prescript output = " + prescriptionData.toOutput());
        } catch (Exception e) {
            log.error("Exception caught when processing the customer choices and prescription: " + e.getMessage());
            e.printStackTrace();
            
            order.requestStatus = OrderStatus.ERROR;
            order.statusMessages.add("Exception caught when processing the customer choices and prescription: " + e.getMessage());
            order.save(flush: true);
        }
        
        if ( order.requestStatus != OrderStatus.ERROR ) {
            // Parse the output file from the frame calculation to retrieve the stl location
            def parsedFrameCalc = parseFrameCalculationOutput(order, frameManufacturingDir, outputFilename);

            if ( order.requestStatus != OrderStatus.ERROR && parsedFrameCalc != null ) {

                // No error marked and we have frame details, so OK to continue..

                // Set up the lens calculation input
                def lensCalcInput = setupLensCalcInput(order, parsedFrameCalc, customerChoices, prescriptionData)

                // Perform the lens calculation
                def lensCalcOutputs = performLensCalculation(order, lensCalcInput, lensManufacturingDir, "manufacturingInput.json", "manufacturingOutput.json", "lms.txt");

                // Process the output returned from the lens calculation
                processLensCalcOutput(order, lensCalcOutputs);

                if ( order.requestStatus != OrderStatus.ERROR ) {
                    // Set up the coatings file
                    setupCoatings(order, customerChoices, coatingsDir, "lensCoatings"); 


                    // Set up the input arguments to the WCS
                    def wcsInputFilename = "wcsInput.json";
                    def wcsInput = setupWCSInput(order, prescriptionData, customerChoices, parsedFrameCalc, lensManufacturingDir, "lms.txt", wcsDir, wcsInputFilename);

                    // Save the fact that we've got to here so that the final job can pick it up..
                    order.requestStatus = OrderStatus.AWAITING_WCS_SUBMISSION;
                    order.save(flush:true);
                }

            } else {
                // Error found so don't do anything
                log.error("Error returned when processing frame calculation output - not doing any more processing");

            }
        } else {
            // Error parsing customer choices or prescription - can't do anything
        }
    }
    
    
    def parseFrameCalculationOutput(order, frameDir, outputFilename) {
        
        // Set that we're in this phase of processing
        order.framesCalcPostProcessingStartTime = new Date();
        order.requestStatus = OrderStatus.FRAME_CALCULATION_POST_PROCESSING;
        order.save(flush:true);

        def stl_location = null;
        def retval = null;

        try {
            // Open and parse the file for the information we want
            def responseData = new XmlSlurper().parseText(new File(frameDir, outputFilename).text).declareNamespace(made4u: 'http://made4u.info/',soap: 'http://www.w3.org/2001/12/soap-envelope');

            responseData.'soap:Body'.'made4u:made4u'.each {

                // First get the STL location
                stl_location = it.'made4u:frame_request'.'made4u:stl_location'.text();

                log.debug("stl location contents: " + stl_location);
                
                // Now get all of the rest of the information required
                def designName = it.'made4u:frame_fbm_input_data'.'made4u:design_name'.text().trim();
                def boxingHorizontal = new Float(it.'made4u:frame_fbm_input_data'.'made4u:boxing_horizontal'.text().trim());
                def boxingVertical = new Float(it.'made4u:frame_fbm_input_data'.'made4u:boxing_vertical'.text().trim());
                def leftTemplePantoAngle = new Float(it.'made4u:frame_fbm_input_data'.'made4u:left_temple_pantoscopic_angle'.text().trim());
                def rightTemplePantoAngle = new Float(it.'made4u:frame_fbm_input_data'.'made4u:right_temple_pantoscopic_angle'.text().trim());
                def bridgeWidth = new Float(it.'made4u:frame_fbm_input_data'.'made4u:bridge_width'.text().trim());
                def bridgeHeight = new Float(it.'made4u:frame_fbm_input_data'.'made4u:bridge_height'.text().trim());
                def heelWidth = new Float(it.'made4u:frame_fbm_input_data'.'made4u:heel_width'.text().trim());
                def internalRimReduction = new Float(it.'made4u:frame_fbm_input_data'.'made4u:internal_rim_reduction'.text().trim());
                def baseOfFrame = new Float(it.'made4u:frame_fbm_input_data'.'made4u:base_of_frame'.text().trim());
                def wrapAngle = new Float(it.'made4u:frame_fbm_input_data'.'made4u:wrap_angle'.text().trim());
                def totalLength = new Float(it.'made4u:frame_fbm_input_data'.'made4u:total_length'.text().trim());
                def leftTempleLength = new Float(it.'made4u:frame_fbm_input_data'.'made4u:left_temple_length'.text().trim());
                def rightTempleLength = new Float(it.'made4u:frame_fbm_input_data'.'made4u:right_temple_length'.text().trim());
                def leftTempleOpeningAngle = new Float(it.'made4u:frame_fbm_input_data'.'made4u:left_temple_opening_angle'.text().trim());
                def rightTempleOpeningAngle = new Float(it.'made4u:frame_fbm_input_data'.'made4u:right_temple_opening_angle'.text().trim());
                def rightTempleMainCurvature = new Float(it.'made4u:frame_fbm_input_data'.'made4u:right_temple_main_curvature'.text().trim());
                def leftTempleMainCurvature = new Float(it.'made4u:frame_fbm_input_data'.'made4u:left_temple_main_curvature'.text().trim());
                def rightTempleTerminalAngle = new Float(it.'made4u:frame_fbm_input_data'.'made4u:right_temple_terminal_angle'.text().trim());
                def leftTempleTerminalAngle = new Float(it.'made4u:frame_fbm_input_data'.'made4u:left_temple_terminal_angle'.text().trim());
                def minEdgeThickness = new Float(it.'made4u:frame_fbm_input_data'.'made4u:minimum_edge_thickness'.text().trim());

                def rightTopPupilFrameDistance = new Float(it.'made4u:frame_fbm_input_data'.'made4u:right_top_pupil_frame_distance'.text().trim());
                def leftTopPupilFrameDistance = new Float(it.'made4u:frame_fbm_input_data'.'made4u:left_top_pupil_frame_distance'.text().trim());
                def rightLowPupilFrameDistanceMF = new Float(it.'made4u:frame_fbm_input_data'.'made4u:right_low_pupil_frame_distance_mf'.text().trim());
                def rightLowPupilFrameDistancePSV = new Float(it.'made4u:frame_fbm_input_data'.'made4u:right_low_pupil_frame_distance_psv'.text().trim());
                def leftLowPupilFrameDistanceMF = new Float(it.'made4u:frame_fbm_input_data'.'made4u:left_low_pupil_frame_distance_mf'.text().trim());
                def leftLowPupilFrameDistancePSV = new Float(it.'made4u:frame_fbm_input_data'.'made4u:left_low_pupil_frame_distance_psv'.text().trim());

                def returnedOma = it.'made4u:frame_fbm_input_data'.'made4u:oma'.text().trim();
                
                // Get the texture information
                def leftTextureRotationAngle = it.'made4u:frame_request'.'made4u:left_temple_texture'.@rotation_angle;
                def leftTextureOrigin = it.'made4u:frame_request'.'made4u:left_temple_texture'.@origin;
                def leftTextureScaleX = it.'made4u:frame_request'.'made4u:left_temple_texture'.@scale_x;
                def leftTextureScaleY = it.'made4u:frame_request'.'made4u:left_temple_texture'.@scale_y;
                def leftTextureLink = it.'made4u:frame_request'.'made4u:left_temple_texture'.@href;

                def rightTextureRotationAngle = it.'made4u:frame_request'.'made4u:right_temple_texture'.@rotation_angle;
                def rightTextureOrigin = it.'made4u:frame_request'.'made4u:right_temple_texture'.@origin;
                def rightTextureScaleX = it.'made4u:frame_request'.'made4u:right_temple_texture'.@scale_x;
                def rightTextureScaleY = it.'made4u:frame_request'.'made4u:right_temple_texture'.@scale_y;
                def rightTextureLink = it.'made4u:frame_request'.'made4u:right_temple_texture'.@href;
     
                
                
                if ( leftTextureLink != null && !"".equals(leftTextureLink) ) {
                    // We have a link - clear up the other data if required (in case it doesn't exist in the XML)
                    if ( !leftTextureRotationAngle || "".equals(leftTextureRotationAngle) )
                        leftTextureRotationAngle = "0.0";
                    if ( !leftTextureOrigin || "".equals(leftTextureOrigin) )
                        leftTextureOrigin = "0.0;0.0;0.0";
                    if ( !leftTextureScaleX || "".equals(leftTextureScaleX) )
                        leftTextureScaleX = "1.0";
                    if ( !leftTextureScaleY || "".equals(leftTextureScaleY) )
                        leftTextureScaleY = "1.0";
                }

                if ( rightTextureLink != null && !"".equals(rightTextureLink) ) {
                    // We have a link - clear up the other data if required (in case it doesn't exist in the XML)
                    if ( !rightTextureRotationAngle || "".equals(rightTextureRotationAngle) )
                        rightTextureRotationAngle = "0.0";
                    if ( !rightTextureOrigin || "".equals(rightTextureOrigin) )
                        rightTextureOrigin = "0.0;0.0;0.0";
                    if ( !rightTextureScaleX || "".equals(rightTextureScaleX) )
                        rightTextureScaleX = "1.0";
                    if ( !rightTextureScaleY || "".equals(rightTextureScaleY) )
                        rightTextureScaleY = "1.0";
                }
                
                def leftTexture = [rotationAngle: leftTextureRotationAngle, origin: leftTextureOrigin, scaleX: leftTextureScaleX, scaleY: leftTextureScaleY, href: leftTextureLink];
                def rightTexture = [rotationAngle: rightTextureRotationAngle, origin: rightTextureOrigin, scaleX: rightTextureScaleX, scaleY: rightTextureScaleY, href: rightTextureLink];


                // Lookup the frame within our system so we can access it later and don't remember the details if they
                // don't exist in our catalogue
                def ourFrame = Frame.findByIdentifier(designName);
                def thisFrame = [];

                if ( ourFrame ) {
                    thisFrame = [designName: designName, boxingHorizontal: boxingHorizontal, leftTemplePantoAngle: leftTemplePantoAngle, rightTemplePantoAngle: rightTemplePantoAngle,
                        bridgeWidth: bridgeWidth, bridgeHeight: bridgeHeight, heelWidth: heelWidth, internalRimReduction: internalRimReduction, baseOfFrame: baseOfFrame,
                        wrapAngle: wrapAngle, totalLength: totalLength, leftTempleLength: leftTempleLength, rightTempleLength: rightTempleLength,
                        leftTempleOpeningAngle: leftTempleOpeningAngle, rightTempleOpeningAngle: rightTempleOpeningAngle, rightTempleMainCurvature: rightTempleMainCurvature,
                        leftTempleMainCurvature: leftTempleMainCurvature, rightTempleTerminalAngle: rightTempleTerminalAngle, leftTempleTerminalAngle: leftTempleTerminalAngle,
                        minEdgeThickness: minEdgeThickness, boxingVertical: boxingVertical,
                        rightTopPupilFrameDistance: rightTopPupilFrameDistance, leftTopPupilFrameDistance: leftTopPupilFrameDistance,
                        rightLowPupilFrameDistanceMF: rightLowPupilFrameDistanceMF, rightLowPupilFrameDistancePSV: rightLowPupilFrameDistancePSV,
                        leftLowPupilFrameDistanceMF: leftLowPupilFrameDistanceMF, leftLowPupilFrameDistancePSV: leftLowPupilFrameDistancePSV,
                        oma: returnedOma, id: ourFrame.id, rimType: ourFrame.rimType, stlLocation: stl_location, leftTexture: leftTexture, rightTexture: rightTexture]

                    retval = thisFrame
                } else {
                    log.error("Unable to find returned frame in the catalogue so not adding it into the set of frames to consider. Design name: " + designName);
                }

            }
            
        } catch (Exception e ) {
            // Exception thrown when trying to read the frame output file
            log.error("Exception thrown when reading the frames calculation output file: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Remember the information and set that we're finished processing
        if ( stl_location != null ) {
            // We have a location - as a final sanity check - make sure that we have the rest of the frame information too
            if ( retval != null ) {
                order.stlLocation = stl_location;
                order.framesCalcPostProcessingEndTime = new Date();
                order.save(flush: true);
            } else {
                order.requestStatus = OrderStatus.ERROR;
                order.statusMessages.add("Unable to find the frame in the database to match the one returned from the frames calculation!");
                order.framesCalcPostProcessingEndTime = new Date();
                order.save(flush: true);
            }
            
        } else {
            // No location found
            order.requestStatus = OrderStatus.ERROR;
            order.statusMessages.add("Unable to find the stl location in the frames calculation output file");
            order.framesCalcPostProcessingEndTime = new Date();
            order.save(flush:true);
        }
        
        log.debug("About to return from processing the frame calculation output with retval: " + retval);
        return retval;
    }
    
    
    def setupLensCalcInput(order, frameData, customerChoices, prescriptionData) {
        order.requestStatus = OrderStatus.LENS_CALCULATION_INPUT_GENERATION;
        order.lensCalcInputCreationStartTime = new Date();
        order.save(flush: true);
        
        log.debug("In the setupLensCalcInput method");
       
        // Work out the OMA for this frame / lens combination
        def omaContent = "";
        
        
        log.debug("***********OMA from prescrip: " + prescriptionData.toOmaOutput());
        
        StringBuilder newOmaBuilder = new StringBuilder();
        newOmaBuilder.append(frameData.oma);
        newOmaBuilder.append(prescriptionData.toOmaOutput());
        newOmaBuilder.append("\nTYPE=H");
        newOmaBuilder.append("\nREQ=BRS");
        newOmaBuilder.append("\nDO=B");
        newOmaBuilder.append("\n_BRANCH=FF10");
        newOmaBuilder.append("\nFRNT=-999.0;-999.0");
        newOmaBuilder.append("\nDIA=-999.0;-999.0");
        newOmaBuilder.append("\n_OPTIM=Y;Y");
        newOmaBuilder.append("\n_THPROT=Y");
        newOmaBuilder.append("\nNOD=0.40");
        newOmaBuilder.append("\nMINEDG=").append(customerChoices.getLensMEThickness()).append(";").append(customerChoices.getLensMEThickness());
        newOmaBuilder.append("\n_MTHDRI=").append(customerChoices.getLensMDThickness()).append(";").append(customerChoices.getLensMDThickness()); 
        newOmaBuilder.append("\nMINCTR=").append(customerChoices.getLensMCThickness()).append(";").append(customerChoices.getLensMCThickness());

        omaContent = newOmaBuilder.toString();
        
        // Remember all of the relevant information in a form we can use later
        LensCalcInput inputObj = new LensCalcInput();
        inputObj.setJobId(customerChoices.getJobNumber());
        inputObj.setDesignId(customerChoices.getLensDesignId());
        inputObj.setDesignName(customerChoices.getFrameId());
        inputObj.setMaterialId(customerChoices.getLensMaterialId());
        inputObj.setSchema(omaContent);
        
        
        // Remember the time when we finished this stage and then return
        order.lensCalcInputCreationEndTime = new Date();
        order.save(flush: true);
        
        return [inputObj];
    }
    
    /**
     * Wrap the communications with the Lens calculation service parsing the output and returning it in a useful format
     */
    def performLensCalculation(order, calcInput, dirPath, inputFileName, outputFileName, lmsOutputFileName) {
        
        order.requestStatus = OrderStatus.LENS_CALCULATION;
        order.lensCalcSubmissionTime = new Date();
        order.save(flush: true)
        
        log.debug("in the performLensCalculation method");
        
        // First save the inputs to disk for debugging, etc.
        def lensCalcInputsFile = new File(dirPath, inputFileName);
        StringBuilder inputBuilder = new StringBuilder();
        
        // Loop through the one input and write it using the same format as the FBM calculation
        calcInput.eachWithIndex() { thisInput, index ->
            
            String wrapperString = "Lens calculation input " + index + "\n";
            String inputData = thisInput.toOutput() + "\n";
            
            inputBuilder.append(wrapperString);
            inputBuilder.append(inputData);
        }
        
        lensCalcInputsFile.write(inputBuilder.toString());
        
        // Loop calling the lens calculation service every 2 minutes until we get an output (or we've failed 20 times)
        def needsRetry = true;
        int ctr = 0;
        def calcRetVal = null;
        def retryInterval = 60000;

        while(needsRetry && ctr <= 20) {
            ctr++;
            log.debug("About to call the lens calculation service - ctr = " + ctr);

            calcRetVal = lensCalculationService.calcManufacturing(calcInput);
            
            needsRetry = calcRetVal.needsRetry;

            if ( needsRetry ) {
                if ( ctr == 0 ) {
                    // First time through that's failed - change status so it's clear what's happening
                    order.requestStatus = DesignStatus.LENS_CALCULATION_RETRY;
                    order.save(flush:true);
                }
                if ( ctr < 20 ) {
                    log.debug("Need to retry connecting - sleep for 1 minute.. ctr = " + ctr);
                    
                    order.statusMessages.add("Lens calculation service already active waiting and trying again. Counter: " + ctr);
                    order.save(flush: true);
                    
                    sleep(retryInterval);
                    log.debug("Sleep finished..");
                }
            }
        }

                
        // Remember the time when the calculation returned
        order.lensCalcReturnTime = new Date();
        order.save(flush: true);

        if ( calcRetVal.success ) {
            // Successful calculation..

            // Remember the time when the calculation returned
            order.lensCalcReturnTime = new Date();
            order.save(flush: true);

            // Output the final return from the run to disk for debug, etc.
            def lensCalcOutputsFile = new File(dirPath, outputFileName);
            def lmsOutputsFile = new File(dirPath, lmsOutputFileName);

            StringBuilder outputBuilder = new StringBuilder();
            StringBuilder lmsContentsBuilder = new StringBuilder();

            calcRetVal.calcOutputs.eachWithIndex() { output,index ->
                String headerString = "Lens calculation output " + index;
                String outputString = output.toOutput();
                outputBuilder.append(headerString);
                outputBuilder.append(outputString);

                lmsContentsBuilder.append(output.getLms());
            }

            lensCalcOutputsFile.write(outputBuilder.toString());

            // Output the LMS information from the run to the disk for access
            lmsOutputsFile.write(lmsContentsBuilder.toString());

            log.debug("Back from calculating the lens details. calcRetVal.calcOutputs.size = " + calcRetVal.calcOutputs);
        } else {
            // Unsuccessful calculation - remember that
            order.requestStatus = OrderStatus.ERROR;
            
            if ( "ALREADY ACTIVE".equals(calcRetVal.message) )
                order.statusMessages.add("Lens calculation service reporting that it is already active after " + ctr + " attempts to run with gaps of " + retryInterval + "ms");
            else 
                order.statusMessages.add(calcRetVal.message);
                
            order.save(flush:true);
        }        
        
        
        return calcRetVal.calcOutputs;
    }
    
    def processLensCalcOutput(order, lensCalcOutputs) {
        
        if ( order.requestStatus != OrderStatus.ERROR ) {
            order.requestStatus = OrderStatus.ORDER_POST_PROCESSING;
            order.postProcessingStartTime = new Date();
            order.save(flush:true);
            
            
            // Actually process the output from the calculation
            lensCalcOutputs.each() {
                
                realOutput ->
                    
                    if (  !"OK".equals(realOutput.getMessage()) ) {
                        // The calculation failed..
                        order.requestStatus = OrderStatus.ERROR;
                        order.statusMessages.add(realOutput.getMessage());
                        order.save(flush: true);
                    } else {
                        // The calculation worked - don't need to do anything else at this stage
                    }
                
            }            
            
            order.postProcessingEndTime = new Date();
            order.save(flush:true);
        }
    }
    
    def setupCoatings(order, customerChoices, coatingDir, outputFilename) {
        
        order.requestStatus = OrderStatus.COATING_DATA_CREATION;
        order.coatingCreationStartTime = new Date();
        order.save(flush: true);
        
        def coatingsData = customerChoices.toCoatingsFile();
        
        def outputFile = new File(coatingDir, outputFilename);
        outputFile.write(coatingsData);
        
        order.coatingCreationEndTime = new Date();
        order.save(flush: true);
    }

    def setupWCSInput(order, prescriptionData, customerChoices, frameCalcOutputData, lensManufacturingDir, lmsFilename, wcsDir, wcsInputFilename) {
        
        order.requestStatus = OrderStatus.WCS_INPUT_GENERATION;
        order.wcsInputCreationStartTime = new Date();
        order.save(flush: true);
        
        log.debug("In the setupWCS input method");
        
        // Set up the texture data based on the information from the frame calculation
        def leftTempleTextureData;
        def rightTempleTextureData;
        
        if ( frameCalcOutputData.leftTexture.href != null && !"".equals(frameCalcOutputData.leftTexture.href)) {
            StringBuilder textureBuilder = new StringBuilder();
            textureBuilder.append("{\"rotation_angle\":\"").append(frameCalcOutputData.leftTexture.rotationAngle).append("\",");
            textureBuilder.append("\"origin\":\"").append(frameCalcOutputData.leftTexture.origin).append("\",");
            textureBuilder.append("\"scale_x\":\"").append(frameCalcOutputData.leftTexture.scaleX).append("\",");
            textureBuilder.append("\"scale_y\":\"").append(frameCalcOutputData.leftTexture.scaleY).append("\",");
            textureBuilder.append("\"href\":\"").append(frameCalcOutputData.leftTexture.href).append("\"}");
            
            leftTempleTextureData = textureBuilder.toString();
        } else {
            leftTempleTextureData = "{}";
        }
        if ( frameCalcOutputData.rightTexture.href != null && !"".equals(frameCalcOutputData.rightTexture.href)) {
            StringBuilder textureBuilder = new StringBuilder();
            textureBuilder.append("{\"rotation_angle\":\"").append(frameCalcOutputData.rightTexture.rotationAngle).append("\",");
            textureBuilder.append("\"origin\":\"").append(frameCalcOutputData.rightTexture.origin).append("\",");
            textureBuilder.append("\"scale_x\":\"").append(frameCalcOutputData.rightTexture.scaleX).append("\",");
            textureBuilder.append("\"scale_y\":\"").append(frameCalcOutputData.rightTexture.scaleY).append("\",");
            textureBuilder.append("\"href\":\"").append(frameCalcOutputData.rightTexture.href).append("\"}");

            rightTempleTextureData = textureBuilder.toString();
        } else {
            rightTempleTextureData = "{}";
        }
        
        // Get the coating data we need
        def coatingsData = customerChoices.toCoatingsWCSData();
        // Get the LMS data back from the file
        def lmsData = new File(lensManufacturingDir, lmsFilename).text
            
        StringBuilder retvalBuilder = new StringBuilder();
        retvalBuilder.append("{\"job_number\":\"").append(prescriptionData.job).append("\",");
        retvalBuilder.append("\"optical_prescription\":").append(prescriptionData.toWCSInput()).append(",");
        retvalBuilder.append("\"frames_configuration\":{");
            retvalBuilder.append("\"zipped_stls\":\"").append(order.stlLocation).append("\",");
            retvalBuilder.append("\"right_temple_base_color\":\"").append(customerChoices.getRightTempleBaseColour()).append("\",");
            retvalBuilder.append("\"left_temple_base_color\":\"").append(customerChoices.getLeftTempleBaseColour()).append("\",");
            retvalBuilder.append("\"frontal_base_color\":\"").append(customerChoices.getFrontalBaseColour()).append("\",");
            retvalBuilder.append("\"right_temple_finishing\":\"").append(customerChoices.getRightTempleFinishingAspect()).append("\",");
            retvalBuilder.append("\"left_temple_finishing\":\"").append(customerChoices.getLeftTempleFinishingAspect()).append("\",");
            retvalBuilder.append("\"frontal_finishing\":\"").append(customerChoices.getFrontalFinishingAspect()).append("\",");
            retvalBuilder.append("\"right_temple_texture\":").append(rightTempleTextureData).append(",");
            retvalBuilder.append("\"left_temple_texture\":").append(leftTempleTextureData);
        retvalBuilder.append("},");
        retvalBuilder.append("\"lenses_configuration\":{");
            retvalBuilder.append("\"specifications\":{");
                if ( customerChoices.getLensMirrorFilter() )
                    retvalBuilder.append("\"MirrorFilter\":\"Yes\",");
                else
                    retvalBuilder.append("\"MirrorFilter\":\"No\",");
                retvalBuilder.append("\"FocalType\":\"").append(customerChoices.getLensFocalType()).append("\",");
                retvalBuilder.append("\"DesignName\":\"").append(customerChoices.getLensDesignName()).append("\",");
                retvalBuilder.append("\"MaterialName\":\"").append(customerChoices.getLensMaterialName()).append("\",");
                retvalBuilder.append("\"ND\":\"").append(customerChoices.getLensND()).append("\",");
                if ( customerChoices.getLensPolarMaterial() )
                    retvalBuilder.append("\"PolarMaterial\":\"Yes\",");
                else
                    retvalBuilder.append("\"PolarMaterial\":\"No\",");
                retvalBuilder.append("\"PolarColorName\":\"").append(customerChoices.getLensPolarColourName()).append("\",");
                if ( customerChoices.getLensPhotochromicMaterial() )
                    retvalBuilder.append("\"PhotochromicMaterial\":\"Yes\",");
                else
                    retvalBuilder.append("\"PhotochromicMaterial\":\"No\",");
                retvalBuilder.append("\"PhotochromicColorName\":\"").append(customerChoices.getLensPhotochromicColourName()).append("\"");
            retvalBuilder.append("},");
            retvalBuilder.append("\"oma_out\":\"").append(lmsData).append("\",");
            retvalBuilder.append("\"oma_coating\":{");
                retvalBuilder.append(coatingsData);
            retvalBuilder.append("},");
            retvalBuilder.append("\"data2print\":\"\"");
        retvalBuilder.append("}");
        retvalBuilder.append("}");
        
        def retval = retvalBuilder.toString();
        
        order.wcsInputCreationEndTime = new Date();
        order.save(flush: true);
        
        // Save the WCS input to disk ready for use later
        StringBuilder wcsInputFileBuilder = new StringBuilder();
        wcsInputFileBuilder.append("orderNumber:");
        wcsInputFileBuilder.append(customerChoices.getJobNumber()).append("\n");
        wcsInputFileBuilder.append(retval.toString());
        
        def actualDir = new File(wcsDir);
        if ( !actualDir.exists() ) 
            actualDir.mkdirs();
            
        def actualInputFile = new File(wcsDir, wcsInputFilename);
        actualInputFile.write(wcsInputFileBuilder.toString());

        
        log.debug("All finished in the setupWCS input method - about to return: " + retval.toString());
        return retval;
    }

}
