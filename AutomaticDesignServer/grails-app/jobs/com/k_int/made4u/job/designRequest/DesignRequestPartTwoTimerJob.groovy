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


import java.util.TreeMap;
import java.util.HashSet;

import java.text.ParseException;

import com.k_int.made4u.oma.OMA;
import com.k_int.made4u.serviceInputs.LensCalcInput;
import com.k_int.made4u.serviceInputs.LensCalcOutput;
import com.k_int.made4u.serviceInputs.OpticalPrescription;
import com.k_int.made4u.design.DesignData;

import com.k_int.made4u.design.CreateDesignResponse;

import org.codehaus.groovy.grails.commons.ConfigurationHolder

import org.xml.sax.SAXParseException;

class DesignRequestPartTwoTimerJob {
    def timeout = 60000l // execute job once in 60 seconds
    def startDelay = 120000l // Only start after 2 mins

    // Lens calculation service
    def lensCalculationService

    def execute() {
        log.debug("Timer task due for design requests part two. Date: " + new Date());
        def newLastRunTime = new Date();
        
        
        // Go and get the timer data from the database so we can check that something's not running already
        def timerData = TimerData.findByTimerTypeAndTimerStatus(TimerType.DESIGN_REQUEST_PART_TWO, TimerStatus.IDLE);

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
            def allRelevantDesignRequests = DesignRequest.findAllByRequestStatus(DesignStatus.AWAITING_PROCESSING_PART_TWO);

            if ( allRelevantDesignRequests ) {
                log.debug("We have found " + allRelevantDesignRequests.size + " relevant design requests awaiting stage 2 of their processing");

                // Loop through each of the design requests and process them
                for ( def thisRequest in allRelevantDesignRequests ) {
                    def processOutput = processRequest(thisRequest, uploadDir);
                }
            }
            else {
                log.debug("No relevant design requests to process at stage 2. Date: " + newLastRunTime);
            }


            // We're all finished - update the last run time in the database and clear the RUNNING status]
            timerData.timerStatus = TimerStatus.IDLE;
            timerData.lastRunTime = newLastRunTime;
            timerData.save(flush: true);
            
        } else {
            // Orders already being processed - don't do anything
            log.debug("Already processing design requests at stage 2 so not starting again");
        }
        
        
    }
    
    def processRequest(designReq, uploadDir) {
        log.debug("Processing design request: " + designReq.id);
        
        // Make a directory to hold the files going to / from the server
        def jobBaseDir = uploadDir + designReq.id;
        def initialInputDir = jobBaseDir + File.separator + "inputFiles";
        def frameDir = jobBaseDir + File.separator + "frameCalcFiles";
        def lensDir = jobBaseDir + File.separator + "lensCalcFiles";

        // Parse the optical prescription to get the information we'll need later
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
        
        

        if ( designReq.requestStatus != DesignStatus.ERROR ) {
            
            log.debug("prescription = " + prescrip.toOutput());
            log.debug("prescription oma = " + prescrip.toOmaOutput());
            
            def framesRet = processFrameCalculation(designReq, frameDir, "output.xml");

            if ( designReq.requestStatus != DesignStatus.ERROR ) {
                // Refine the available frames and lenses
                def possibleFrames = performFrameRefinement(designReq, framesRet, prescrip);

                if ( designReq.requestStatus != DesignStatus.NO_VIABLE_FRAMES ) {

                    def possibleLenses = performInitialLensRefinement(designReq, prescrip);

                    def lensCalcInputs = combineRefinedFramesAndLenses(designReq, possibleFrames, possibleLenses, prescrip, true);

                    if ( lensCalcInputs && lensCalcInputs.size() > 0 ) {
                        // Talk to the lens calculation server
                        def lensCalcOutputs = performLensCalculation(designReq, lensCalcInputs, lensDir, "input.json", "output.json");


                        if ( designReq.requestStatus != DesignStatus.ERROR ) {
                            // Combine the lens calculation output information in with the set of possible frames and lenses to give us
                            // the complete set of information to be returned to the caller
                            def completeStructuredOutputInformation = combineAllFramesAndLensesWithLensCalcOutputs(designReq, lensCalcOutputs, prescrip );

                            if ( designReq.requestStatus != DesignStatus.ERROR && designReq.requestStatus != DesignStatus.NO_VIABLE_FRAME_LENS_COMBINATIONS ) {
                                // Set up the return XML file and save it to disk ready to be retrieved
                                def designFileDir = jobBaseDir + File.separator + "returnedDesigns";
                                CreateDesignResponse.setupReturnXml(designReq, possibleFrames, possibleLenses, completeStructuredOutputInformation, designFileDir, "designResponse.xml");

                                // Now the design is complete - update this fact in the db.

                                designReq.requestStatus = DesignStatus.DESIGN_RETURNED;
                                designReq.responseGivenTime = new Date();
                                designReq.save(flush:true);
                            } else {
                                // No viable frame / lens combinations - request information already set up so don't do anything more

                            }
                        } else {
                            // An error happened when running the lens calculation - return messages, etc. set up so don't want to do anything else..

                        }
                    } else {
                        // We don't have any viable designs - can't continue, just return an error..
                        designReq.requestStatus = DesignStatus.NO_VIABLE_FRAME_LENS_COMBINATIONS;
                        designReq.statusMessages.add("No viable frame designs available - no lens calculation performed");
                        designReq.save(flush:true);
                    }
                } else {
                    // No viable frames - request info set up already so don't do anything..
                }            
            } else {
                // Error occurred processing frame calc output - information already set up so don't do anything
            }
        } else {
            // Error occurred - request info already set up so don't do anything..
        }
    }
    
    def performFrameRefinement(designReq, frameCalcResult, prescrip) {
        designReq.requestStatus = DesignStatus.FRAME_CATALOGUE_FILTRATION;
        designReq.frameRefinementStartTime = new Date();
        designReq.save(flush: true);
        
        
        def retVal = [];
        
        def focalType = prescrip.getCombinedLensType();
        
        // Loop through the frames given as input and for each one decide whether it
        // is viable, etc.
        frameCalcResult.each() {
            
            def designName = it.designName;
            
            // Go and get the frame from the database that matches this..
            def fbmFrame = Frame.findByIdentifier(designName);
            
            if ( fbmFrame ) {
                // Frame found - continue.
                def viable = true;
                
                // Filter by the frontal dimensions
                if ( !fbmFrame.dimensions )
                    viable = false;
                else {
                    
                    def dims = fbmFrame.dimensions;
                    
                    viable = viable && testRangeConstraint(it.boxingHorizontal, dims.boxingHorizontalMin, dims.boxingHorizontalMax, fbmFrame.id, null, "frame", "boxingHorizontal", designReq);
                    viable = viable && testRangeConstraint(it.boxingVertical, dims.boxingVerticalMin, dims.boxingVerticalMax, fbmFrame.id, null, "frame", "boxingVertical", designReq);
                    viable = viable && testRangeConstraint(it.leftTemplePantoAngle, dims.pantoscopicAngleLeftMin, dims.pantoscopicAngleLeftMax, fbmFrame.id, null, "frame", "pantoscopicAngleLeft", designReq);
                    viable = viable && testRangeConstraint(it.rightTemplePantoAngle, dims.pantoscopicAngleRightMin, dims.pantoscopicAngleRightMax, fbmFrame.id, null, "frame", "pantoscopicAngleRight", designReq);
                    viable = viable && testRangeConstraint(it.bridgeWidth, dims.bridgeWidthMin, dims.bridgeWidthMax, fbmFrame.id, null, "frame", "bridgeWidth", designReq);
                    viable = viable && testRangeConstraint(it.bridgeHeight, dims.bridgeHeightMin, dims.bridgeHeightMax, fbmFrame.id, null, "frame", "bridgeHeight", designReq);
                    viable = viable && testRangeConstraint(it.heelWidth, dims.heelWidthMin, dims.heelWidthMax, fbmFrame.id, null, "frame", "heelWidth", designReq);
                    viable = viable && testRangeConstraint(it.internalRimReduction, dims.internalRimReductionMin, dims.internalRimReductionMax, fbmFrame.id, null, "frame", "internalRimReduction", designReq);
                    viable = viable && testRangeConstraint(it.baseOfFrame, dims.baseOfFrameMin, dims.baseOfFrameMax, fbmFrame.id, null, "frame", "baseOfFrame", designReq);
                    viable = viable && testRangeConstraint(it.wrapAngle, dims.wrapAngleMin, dims.wrapAngleMax, fbmFrame.id, null, "frame", "wrapAngle", designReq);
                    

                    // Only filter on total length if still viable after all previous tests
                    if ( viable ) {
                        // Retrieve the min and max total length for the specified bridge width
                        def minTotal = null;
                        def maxTotal = null;
                        def totalDims = dims.totalLength;

                        Float bridgeWidthToLookFor = new Float(it.bridgeWidth.round());

                        if ( bridgeWidthToLookFor == totalDims.bridgeWidth1 ) {
                            minTotal = totalDims.min1;
                            maxTotal = totalDims.max1;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth2 ) {
                            minTotal = totalDims.min2;
                            maxTotal = totalDims.max2;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth3 ) {
                            minTotal = totalDims.min3;
                            maxTotal = totalDims.max3;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth4 ) {
                            minTotal = totalDims.min4;
                            maxTotal = totalDims.max4;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth5 ) {
                            minTotal = totalDims.min5;
                            maxTotal = totalDims.max5;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth6 ) {
                            minTotal = totalDims.min6;
                            maxTotal = totalDims.max6;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth7 ) {
                            minTotal = totalDims.min7;
                            maxTotal = totalDims.max7;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth8 ) {
                            minTotal = totalDims.min8;
                            maxTotal = totalDims.max8;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth9 ) {
                            minTotal = totalDims.min9;
                            maxTotal = totalDims.max9;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth10 ) {
                            minTotal = totalDims.min10;
                            maxTotal = totalDims.max10;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth11 ) {
                            minTotal = totalDims.min11;
                            maxTotal = totalDims.max11;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth12 ) {
                            minTotal = totalDims.min12;
                            maxTotal = totalDims.max12;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth13 ) {
                            minTotal = totalDims.min13;
                            maxTotal = totalDims.max13;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth14 ) {
                            minTotal = totalDims.min14;
                            maxTotal = totalDims.max14;
                        } else if ( bridgeWidthToLookFor == totalDims.bridgeWidth15 ) {
                            minTotal = totalDims.min15;
                            maxTotal = totalDims.max15;
                        }
                    
                        if ( minTotal && maxTotal ) {
                            // We have a min / max size to test against
                            viable = viable && testRangeConstraint(it.totalLength, minTotal, maxTotal, fbmFrame.id, null, "frame", "totalLength", designReq);
                        } else {
                            log.debug("No min / max value to test against for the total size! Bridge width: " + it.bridgeWidth + " - looking for: " + bridgeWidthToLookFor + " design name: " + designName);
                            viable = false;

                            designReq.frameValidationMessages.add("Frame " + fbmFrame.id + " not viable for field 'totalLength' - no min or max value to filter by. BridgeWidth: " + bridgeWidthToLookFor);
                            designReq.save(flush: true);
                        }
                    }
                    
                    
                    // Pupil-frame distances
                    viable = viable && testMinConstraint(it.rightTopPupilFrameDistance, fbmFrame.rightTopPupilFrameDistance, fbmFrame.id, null, "frame", "rightTopPupilFrameDistance", designReq);
                    viable = viable && testMinConstraint(it.leftTopPupilFrameDistance, fbmFrame.leftTopPupilFrameDistance, fbmFrame.id, null, "frame", "rightTopPupilFrameDistance", designReq);
                    
                    if ( "PSV".equals(focalType) ) {
                        // Test progressive values
                        viable = viable && testMinConstraint(it.rightLowPupilFrameDistancePSV, fbmFrame.rightBottomPupilFrameDistancePSV, fbmFrame.id, null, "frame", "rightBottomPupilFrameDistancePSV", designReq);
                        viable = viable && testMinConstraint(it.leftLowPupilFrameDistancePSV, fbmFrame.leftBottomPupilFrameDistancePSV, fbmFrame.id, null, "frame", "leftBottomPupilFrameDistancePSV", designReq);
                    } else {
                        // Test monofocal 
                        viable = viable && testMinConstraint(it.rightLowPupilFrameDistanceMF, fbmFrame.rightBottomPupilFrameDistanceMF, fbmFrame.id, null, "frame", "rightBottomPupilFrameDistanceMF", designReq);
                        viable = viable && testMinConstraint(it.leftLowPupilFrameDistanceMF, fbmFrame.leftBottomPupilFrameDistanceMF, fbmFrame.id, null, "frame", "leftBottomPupilFrameDistanceMF", designReq);
                    }
                    
                }
                
                // Filter by the left temple
                if ( viable ) {
                    if ( !fbmFrame.leftTempleDims )
                    viable = false;
                    else {
                        def dims = fbmFrame.leftTempleDims;
                        
                        viable = viable && testRangeConstraint(it.leftTempleLength, dims.lengthMin, dims.lengthMax, fbmFrame.id, null, "frame", "leftTempleLength", designReq);
                        viable = viable && testRangeConstraint(it.leftTempleOpeningAngle, dims.openingAngleMin, dims.openingAngleMax, fbmFrame.id, null, "frame", "leftTempleOpeningAngle", designReq);
                        viable = viable && testRangeConstraint(it.leftTempleMainCurvature, dims.mainCurvatureMin, dims.mainCurvatureMax, fbmFrame.id, null, "frame", "leftTempleMainCurvature", designReq);
                        viable = viable && testRangeConstraint(it.leftTempleTerminalAngle, dims.terminalAngleMin, dims.terminalAngleMax, fbmFrame.id, null, "frame", "leftTempleTerminalAngle", designReq);
                    }
                }
                
                // Filter by the right temple
                if ( viable ) {
                    if ( !fbmFrame.rightTempleDims )
                    viable = false;
                    else {
                        def dims = fbmFrame.rightTempleDims;
                        
                        viable = viable && testRangeConstraint(it.rightTempleLength, dims.lengthMin, dims.lengthMax, fbmFrame.id, null, "frame", "rightTempleLength", designReq);
                        viable = viable && testRangeConstraint(it.rightTempleOpeningAngle, dims.openingAngleMin, dims.openingAngleMax, fbmFrame.id, null, "frame", "rightTempleOpeningAngle", designReq);
                        viable = viable && testRangeConstraint(it.rightTempleMainCurvature, dims.mainCurvatureMin, dims.mainCurvatureMax, fbmFrame.id, null, "frame", "righTempleMainCurvature", designReq);
                        viable = viable && testRangeConstraint(it.rightTempleTerminalAngle, dims.terminalAngleMin, dims.terminalAngleMax, fbmFrame.id, null, "frame", "rightTempleTerminalAngle", designReq);
                    }
                }                
                
                // If still viable then add the frame details back into the list to return
                if ( viable ) {
                    log.debug("Frame is viable - remembering (designName: " + designName + ")");
                    retVal.add(it);
                } else {
                    log.debug("Frame isn't viable - forgetting! (designName: " + designName + ")");
                }
            } else {
                // Frame not found!!!
                log.debug("Frame could not be found in the database - removing from the filtered list!")
            }
            
        }
        
        log.debug("At the end of the frame refinement - num of viable frames = " + retVal.size());
        
        if ( retVal.size() == 0 ) {
            designReq.requestStatus = DesignStatus.NO_VIABLE_FRAMES;
            designReq.statusMessages.add("No viable frames after filtering");
        }
        designReq.numOfFramesAfterFrameCalcFilter = retVal.size();
        designReq.frameRefinementEndTime = new Date();
        designReq.save(flush: true);
        
        return retVal;
    }
    
    def performInitialLensRefinement(designReq, prescriptionData) {
        designReq.requestStatus = DesignStatus.LENS_CATALOGUE_FILTRATION;
        designReq.lensRefinementStartTime = new Date();
        designReq.save(flush: true);

        def retVal = [];
        
        def allLensMaterials = LensMaterial.list();
        
        // Loop through all of the lens materials and check to see whether they have a prescription range
        // that fits with the customer's requirements
        allLensMaterials.each() {
           
            def viable = false;
            
            // First work out if this material has the correct focal type for the customer requirements
            def requiredFocalType = LensType.MONOFOCAL;
            if ( "PSV".equals(prescriptionData.getCombinedLensType()) ) {
                // At least one lens is progressive..
                requiredFocalType = LensType.PROGRESSIVE;
            }
            
            log.debug("required focal type = " + requiredFocalType);
            
            
            if ( it.focalType == requiredFocalType ) {
                
                // This lens type matches the required focal type - continue working out whether
                // the customer's prescription fits with the possible values for the material
                def prescriptions = it.prescriptions;

                if ( prescriptions ) {
                                        
                    def leftViable = false;
                    def rightViable = false;
                    
                    for(LensPrescriptionProperties aPrescrip: prescriptions) {
                        
                        log.debug("About to test against prescription: " + aPrescrip.toOutput());


                        if ( leftViable || 
                             (testRangeConstraint(prescriptionData.sphere[0], aPrescrip.sphereMin, aPrescrip.sphereMax) &&
                             testRangeConstraint(prescriptionData.cylinder[0], aPrescrip.cylinderMin, aPrescrip.cylinderMax) &&
                             testRangeConstraint(prescriptionData.addition[0], aPrescrip.additionMin, aPrescrip.additionMax) &&
                             testRangeConstraint(prescriptionData.prism[0], aPrescrip.prismMin, aPrescrip.prismMax) ) ) {

                                // Prescription within the required range - material is viable - stop searching
                                leftViable = true;
                        }

                        if ( rightViable || 
                             (testRangeConstraint(prescriptionData.sphere[1], aPrescrip.sphereMin, aPrescrip.sphereMax) &&
                             testRangeConstraint(prescriptionData.cylinder[1], aPrescrip.cylinderMin, aPrescrip.cylinderMax) &&
                             testRangeConstraint(prescriptionData.addition[1], aPrescrip.additionMin, aPrescrip.additionMax) &&
                             testRangeConstraint(prescriptionData.prism[1], aPrescrip.prismMin, aPrescrip.prismMax) ) ) {

                                // Prescription within the required range - material is viable - stop searching
                                rightViable = true;
                        }
                        
                        if ( leftViable && rightViable ) {
                            // Both lenses are viable - don't need to keep looking
                            viable = true;
                            break;
                        }
                        
                    }
                    
                }
                
                if ( !viable ) {
                    // Lens material isn't viable - don't remember it
                    log.debug("Lens material is not viable: " + it.id);
                    designReq.lensValidationMessages.add("Lens " + it.id + " not viable for prescription: " + prescriptionData.toOutput() );
                }
            }
            
            if ( viable ) {
                // Remember the lens material as being viable..
                log.debug("Lens material is viable: " + it.id);
                retVal.add(it);
            }
            
        }
        
        
        designReq.lensRefinementEndTime = new Date();
        designReq.save(flush: true);
        
        return retVal;
    }
        
    def combineRefinedFramesAndLenses(designReq, possibleFrames, possibleLenses, prescriptionData, restrict_materials) {
        designReq.requestStatus = DesignStatus.GENERATING_LENS_CALC_INPUT;
        designReq.lensCalcInputCreationStartTime = new Date();
        designReq.save(flush: true);
        
        log.debug("In the combineRefinedFramesAndLeses method");
        
        def retVal = [];
                
        // Loop through each of the frames that are possible and then loop through each
        // of the viable lens materials combining the two sets of information
        for ( def thisFrame in possibleFrames ) {
            
            def rimType = thisFrame.rimType;

            for(LensMaterial aMaterial: possibleLenses) {
                log.debug("frame design name: " + thisFrame.designName + " and lens ref: " + aMaterial.reference);
                
                def edgeThickness;
                if ( rimType == RimType.FULL )
                    edgeThickness = aMaterial.minimumEdgeThicknessFull;
                else if ( rimType == RimType.SEMIRIMLESS )
                    edgeThickness = aMaterial.minimumEdgeThicknessSemi;
                else
                    edgeThickness = aMaterial.minimumEdgeThicknessRimless;
                    
                
                if ( restrict_materials ) {
                    // We only want to simulate certain materials - ignore those that we don't care about for now..
                    def demoMaterial = false;
                    if ( "2".equals(aMaterial.identifier) || "02".equals(aMaterial.identifier) || "4".equals(aMaterial.identifier) 
                        || "04".equals(aMaterial.identifier) || "6".equals(aMaterial.identifier) || "06".equals(aMaterial.identifier) ) {
                    
                        demoMaterial = true;
                    }                    
                    if ( !demoMaterial ) {
                        // This isn't a material that we want to actually simulate now - skip it
                        log.debug("Skipping material with id: " + aMaterial.id);
                        continue;
                    }
                }
                
                // Update the OMA to include other labels that aren't in there from the frame calculation
                StringBuilder newOmaBuilder = new StringBuilder();
                newOmaBuilder.append(thisFrame.oma);
                newOmaBuilder.append(prescriptionData.toOmaOutput());
                newOmaBuilder.append("\nTYPE=H");
                newOmaBuilder.append("\nREQ=LDS");
                newOmaBuilder.append("\nDO=B");
                newOmaBuilder.append("\n_BRANCH=FF10");
                newOmaBuilder.append("\nFRNT=-999.0;-999.0");
                newOmaBuilder.append("\nDIA=-999.0;-999.0");
                newOmaBuilder.append("\n_OPTIM=Y;Y");
                newOmaBuilder.append("\n_THPROT=Y");
                newOmaBuilder.append("\nNOD=0.40");
                newOmaBuilder.append("\nMINEDG=").append(edgeThickness).append(";").append(edgeThickness);
                newOmaBuilder.append("\n_MTHDRI=").append(aMaterial.minimumDrillingThickness).append(";").append(aMaterial.minimumDrillingThickness); 
                newOmaBuilder.append("\nMINCTR=").append(aMaterial.centreDrillingThickness).append(";").append(aMaterial.centreDrillingThickness);
                
                // Now create the input file for this frame and lens combination..
                LensCalcInput calcInput = new LensCalcInput();
                calcInput.setJobId(prescriptionData.job);
                calcInput.setDesignName(new String(thisFrame.designName));
                calcInput.setDesignId(prescriptionData.getDesignId());
                calcInput.setMaterialId(aMaterial.identifier);
                calcInput.setSchema(newOmaBuilder.toString());
                
                retVal.add(calcInput);
            }
            
        }
        
        log.debug("After combining frames and lenses we have " + retVal.size() + " combinations");
        
        designReq.numOfInputsToLensCalc = retVal.size();
        designReq.lensCalcInputCreationEndTime = new Date();
        designReq.save(flush: true);
        
        return retVal;
    }
    
    /**
     * Process the output from the Frame calculation service parsing the output and returning it in a useful format
     */
    def processFrameCalculation(designRequest, frameCalcDir, outputFilename) {
        
        
        log.debug("in the processFrameCalculation method");
        
        
        def retVal = null;
        int totalNumOfFrames = 0;
        
        // Parse the information out of the response file
        try {
            def frameData = new XmlSlurper().parseText(new File(frameCalcDir, outputFilename).text).declareNamespace(made4u: 'http://made4u.info/',soap: 'http://www.w3.org/2001/12/soap-envelope');

            log.debug("About to loop through the xml data..");

            retVal = [];
            
            def hasDisplayOrderSet = false

            frameData.Body.made4u.frame_fbm_input_data.each {
                def designName = it.design_name.text().trim();
                def boxingHorizontal;
                def boxingVertical;
                def leftTemplePantoAngle;
                def rightTemplePantoAngle;
                def bridgeWidth;
                def bridgeHeight;
                def heelWidth;
                def internalRimReduction;
                def baseOfFrame;
                def wrapAngle;
                def totalLength;
                def leftTempleLength;
                def rightTempleLength;
                def leftTempleOpeningAngle;
                def rightTempleOpeningAngle;
                def leftTempleMainCurvature;
                def rightTempleMainCurvature;
                def leftTempleTerminalAngle;
                def rightTempleTerminalAngle;
                def minEdgeThickness;
                def rightTopPupilFrameDistance;
                def leftTopPupilFrameDistance;
                def rightLowPupilFrameDistanceMF;
                def rightLowPupilFrameDistancePSV;
                def leftLowPupilFrameDistanceMF;
                def leftLowPupilFrameDistancePSV;

                if ( !"".equals(it.boxing_horizontal?.text().trim()) )
                    boxingHorizontal = new Float(it.boxing_horizontal.text().trim());
                if ( !"".equals(it.boxing_vertical?.text().trim()) )
                    boxingVertical = new Float(it.boxing_vertical.text().trim());
                if ( !"".equals(it.left_temple_pantoscopic_angle?.text().trim()) )
                    leftTemplePantoAngle = new Float(it.left_temple_pantoscopic_angle.text().trim());
                if ( !"".equals(it.right_temple_pantoscopic_angle?.text().trim()) )
                    rightTemplePantoAngle = new Float(it.right_temple_pantoscopic_angle.text().trim());
                if ( !"".equals(it.bridge_width?.text().trim()) )
                    bridgeWidth = new Float(it.bridge_width.text().trim());
                if ( !"".equals(it.bridge_height?.text().trim()) ) 
                    bridgeHeight = new Float(it.bridge_height.text().trim());
                if ( !"".equals(it.heel_width?.text().trim()) )
                    heelWidth = new Float(it.heel_width.text().trim());
                if ( !"".equals(it.internal_rim_reduction?.text().trim()) )
                    internalRimReduction = new Float(it.internal_rim_reduction.text().trim());
                if ( !"".equals(it.base_of_frame?.text().trim()) )
                    baseOfFrame = new Float(it.base_of_frame.text().trim());
                if ( !"".equals(it.wrap_angle?.text().trim()) )
                    wrapAngle = new Float(it.wrap_angle.text().trim());
                if ( !"".equals(it.total_length?.text().trim()) ) 
                    totalLength = new Float(it.total_length.text().trim());
                if ( !"".equals(it.left_temple_length?.text().trim()) )
                    leftTempleLength = new Float(it.left_temple_length.text().trim());
                if ( !"".equals(it.right_temple_length?.text().trim()) )
                    rightTempleLength = new Float(it.right_temple_length.text().trim());
                if ( !"".equals(it.left_temple_opening_angle?.text().trim()) )
                    leftTempleOpeningAngle = new Float(it.left_temple_opening_angle.text().trim());
                if ( !"".equals(it.right_temple_opening_angle?.text().trim()) )
                    rightTempleOpeningAngle = new Float(it.right_temple_opening_angle.text().trim());
                if ( !"".equals(it.right_temple_main_curvature?.text().trim()) ) 
                    rightTempleMainCurvature = new Float(it.right_temple_main_curvature.text().trim());
                if ( !"".equals(it.left_temple_main_curvature?.text().trim()) )
                    leftTempleMainCurvature = new Float(it.left_temple_main_curvature.text().trim());
                if ( !"".equals(it.right_temple_terminal_angle?.text().trim()) )
                    rightTempleTerminalAngle = new Float(it.right_temple_terminal_angle.text().trim());
                if ( !"".equals(it.left_temple_terminal_angle?.text().trim()) )
                    leftTempleTerminalAngle = new Float(it.left_temple_terminal_angle.text().trim());
                if ( !"".equals(it.minimum_edge_thickness?.text().trim()) )
                    minEdgeThickness = new Float(it.minimum_edge_thickness.text().trim());
                if ( !"".equals(it.right_top_pupil_frame_distance?.text().trim()) )
                    rightTopPupilFrameDistance = new Float(it.right_top_pupil_frame_distance.text().trim());
                if ( !"".equals(it.left_top_pupil_frame_distance?.text().trim()) )
                    leftTopPupilFrameDistance = new Float(it.left_top_pupil_frame_distance.text().trim());
                if ( !"".equals(it.right_low_pupil_frame_distance_mf?.text().trim()) )
                    rightLowPupilFrameDistanceMF = new Float(it.right_low_pupil_frame_distance_mf.text().trim());
                if ( !"".equals(it.right_low_pupil_frame_distance_psv?.text().trim()) )
                    rightLowPupilFrameDistancePSV = new Float(it.right_low_pupil_frame_distance_psv.text().trim());
                if ( !"".equals(it.left_low_pupil_frame_distance_mf?.text().trim()) )
                    leftLowPupilFrameDistanceMF = new Float(it.left_low_pupil_frame_distance_mf.text().trim());
                if ( !"".equals(it.left_low_pupil_frame_distance_psv?.text().trim()) )
                    leftLowPupilFrameDistancePSV = new Float(it.left_low_pupil_frame_distance_psv.text().trim());


                def returnedOma = it.oma.text().trim();

                totalNumOfFrames++;

                // Lookup the frame within our system so we can access it later and don't remember the details if they
                // don't exist in our catalogue
                def ourFrame = Frame.findByIdentifier(designName);
                def thisFrame = [];

                if ( ourFrame ) {

                    def localDisplayOrder = -100000; // Set a default very low value so that those without ordering specified always come out first
                    if ( ourFrame.displayOrder != null ) {
                        localDisplayOrder = ourFrame.displayOrder;
                        hasDisplayOrderSet = true;
                    }
                        
                    log.error("designName: " + designName + " and displayOrder = " + localDisplayOrder);

                    thisFrame = [designName: designName, boxingHorizontal: boxingHorizontal, leftTemplePantoAngle: leftTemplePantoAngle, rightTemplePantoAngle: rightTemplePantoAngle,
                        bridgeWidth: bridgeWidth, bridgeHeight: bridgeHeight, heelWidth: heelWidth, internalRimReduction: internalRimReduction, baseOfFrame: baseOfFrame,
                        wrapAngle: wrapAngle, totalLength: totalLength, leftTempleLength: leftTempleLength, rightTempleLength: rightTempleLength,
                        leftTempleOpeningAngle: leftTempleOpeningAngle, rightTempleOpeningAngle: rightTempleOpeningAngle, rightTempleMainCurvature: rightTempleMainCurvature,
                        leftTempleMainCurvature: leftTempleMainCurvature, rightTempleTerminalAngle: rightTempleTerminalAngle, leftTempleTerminalAngle: leftTempleTerminalAngle,
                        minEdgeThickness: minEdgeThickness, boxingVertical: boxingVertical,
                        rightTopPupilFrameDistance: rightTopPupilFrameDistance, leftTopPupilFrameDistance: leftTopPupilFrameDistance,
                        rightLowPupilFrameDistanceMF: rightLowPupilFrameDistanceMF, rightLowPupilFrameDistancePSV: rightLowPupilFrameDistancePSV,
                        leftLowPupilFrameDistanceMF: leftLowPupilFrameDistanceMF, leftLowPupilFrameDistancePSV: leftLowPupilFrameDistancePSV,
                        oma: returnedOma, id: ourFrame.id, rimType: ourFrame.rimType, displayOrder: localDisplayOrder]

                    retVal.add(thisFrame);
                } else {
                    log.error("Unable to find returned frame in the catalogue so not adding it into the set of frames to consider. Design name: " + designName);
                }
            }

            log.debug("Finished looping - retVal size = " + retVal.size());
            
            // If none of the frames that we've encountered have had a display order specified then
            // override the display order for all of them to be the same as the frame's ID so that they
            // come out in the same order every time
            if ( !hasDisplayOrderSet ) {
                retVal.each {
                    it.displayOrder = it.id;
                    
                    log.error("Display order reset to " + it.displayOrder);
                }
            }

            designRequest.numOfFramesFromFrameCalc = totalNumOfFrames;
            designRequest.numOfFramesFromFrameCalcInCatalogue = retVal.size();
            designRequest.save(flush:true);
        } catch (SAXParseException spe) {
            log.error("SAX parse exception thrown when reading the frame calculation output: " + spe.getMessage());
            spe.printStackTrace();
            
            designRequest.requestStatus = DesignStatus.ERROR;
            designRequest.statusMessages.add("Unable to parse the output from the frame calculation. Error: " + spe.getMessage());
            designRequest.save(flush: true);
        } catch (Exception e) {
            log.error("Unexpected exception caught when reading the frame calculation output: " + e.getMessage());
            e.printStackTrace();
            
            designRequest.requestStatus = DesignStatus.ERROR;
            designRequest.statusMessages.add("Unexpected exception caught when reading the frame calculation output: " + e.getMessage());
            designRequest.save(flush:true);
        }   
        
        
        return retVal;
    }
    

    /**
     * Wrap the communications with the Lens calculation service parsing the output and returning it in a useful format
     */
    def performLensCalculation(designRequest, calcInputs, dirPath, inputFileName, outputFileName) {
        
        designRequest.requestStatus = DesignStatus.LENS_CALCULATION;
        designRequest.lensCalcSubmissionTime = new Date();
        designRequest.save(flush: true)
        
        log.debug("in the performLensCalculation method");
        
        // First save the inputs to disk for debugging, etc.
        def lensCalcInputsFile = new File(dirPath, inputFileName);
        StringBuilder inputBuilder = new StringBuilder();
        
        calcInputs.eachWithIndex() { thisInput, index ->
            
            String wrapperString = "Lens calculation input " + index + "\n";
            String inputData = thisInput.toOutput() + "\n";
            
            inputBuilder.append(wrapperString);
            inputBuilder.append(inputData);
        }
        
        lensCalcInputsFile.write(inputBuilder.toString());
        
        // Loop calling the lens calculation service every 2 minutes until we get an output
        def needsRetry = true;
        int ctr = 0;
        def calcRetVal = null;
        def retryInterval = 60000;
        
        while(needsRetry && ctr <= 20) {
            ctr++;
            log.debug("About to call the lens calculation service - ctr = " + ctr);

            calcRetVal = lensCalculationService.calcFBM(calcInputs);
            
            needsRetry = calcRetVal.needsRetry;

            if ( needsRetry ) {
                if ( ctr == 0 ) {
                    // First time through that's failed - change status so it's clear what's happening
                    designRequest.requestStatus = DesignStatus.LENS_CALCULATION_RETRY;
                    designRequest.save(flush:true);
                }
                if ( ctr < 20 ) {
                    log.debug("Need to retry connecting - sleep for 1 minute.. ctr = " + ctr);
                    
                    designRequest.statusMessages.add("Lens calculation service already active waiting and trying again. Counter: " + ctr);
                    designRequest.save(flush: true);
                    
                    sleep(retryInterval);
                    log.debug("Sleep finished..");
                }
            }
        }
        
        if ( calcRetVal.success ) {
            // Successful calculation..

            // Remember the time when the calculation returned
            designRequest.lensCalcReturnTime = new Date();
            designRequest.save(flush: true);

            // Output the final return from the run to disk for debug, etc.
            def lensCalcOutputsFile = new File(dirPath, outputFileName);
            StringBuilder outputBuilder = new StringBuilder();

            calcRetVal.calcOutputs.eachWithIndex() { output,index ->
                String headerString = "Lens calculation output " + index;
                String outputString = output.toOutput();
                outputBuilder.append(headerString);
                outputBuilder.append(outputString);
            }    

            lensCalcOutputsFile.write(outputBuilder.toString());

            log.debug("Back from calculating the lens details. calcRetVal.calcOutputs.size = " + calcRetVal.calcOutputs);
        } else {
            // Unsuccessful calculation - remember that
            designRequest.requestStatus = DesignStatus.ERROR;
            
            if ( "ALREADY ACTIVE".equals(calcRetVal.message) )
                designRequest.statusMessages.add("Lens calculation service reporting that it is already active after " + ctr + " attempts to run with gaps of " + retryInterval + "ms");
            else 
                designRequest.statusMessages.add(calcRetVal.message);
                
            designRequest.save(flush:true);
        }
        
        return calcRetVal.calcOutputs;
    }
    
    def combineAllFramesAndLensesWithLensCalcOutputs(designRequest, lensCalcOutputs, prescriptionData) {

        // Remember starting post processing
        designRequest.numOfResultsFromLensCalc = lensCalcOutputs.size();
        designRequest.requestStatus = DesignStatus.COMPLETED_CALCULATION_POST_PROCESSING;
        designRequest.postProcessingStartTime = new Date();
        designRequest.save(flush: true)

        log.debug("Starting to combine the lens calculation outputs into the other information");
        
        def combinedInfo = [];
        
        // Work out the customer's required focal type
        def requiredFocalType = LensType.MONOFOCAL;
        if ( "PSV".equals(prescriptionData.lensType[0]) || "PSV".equals(prescriptionData.lensType[1])
            || "PR".equals(prescriptionData.lensType[0]) || "PR".equals(prescriptionData.lensType[1])) {
            // At least one lens is progressive..
            requiredFocalType = LensType.PROGRESSIVE;
        }

        log.debug("required focal type = " + requiredFocalType);


        Map<String,Set<DesignData>> structuredInfo = new HashMap<String,HashSet<DesignData>>();
        
        // Loop through the lens calculation outputs and set things up..
        def okCtr = 0;
        
        for(LensCalcOutput anOutput: lensCalcOutputs) {
        
            // Only remember materials that have come back OK as if they've failed we can't offer them to the customer
            if ( "OK".equals(anOutput.getMessage()) ) {
                log.debug("Got a viable lens simulation..");
                okCtr++;
                
                // Get some useful information back from the output
                def frameIdentifier = anOutput.getDesignName();
                def lensMaterialIdentifier = anOutput.getMaterialId();
                
                log.debug("got identifiers: " + frameIdentifier + "," + lensMaterialIdentifier);
                
                // Go and get the actual lens material information from the database based on what's been returned
                LensMaterial actualMaterial = LensMaterial.findByIdentifierAndFocalType(lensMaterialIdentifier, requiredFocalType);
                
                log.debug("have searched for a lens material");
                
                if ( actualMaterial ) {
                    // We have a material - OK to continue..
                    log.debug("have a material!");
                
                    // Now remember the information we care about
                    DesignData thisDesignData = new DesignData();
                    thisDesignData.frameIdentifier = frameIdentifier;
                    thisDesignData.lensMaterialId = actualMaterial.getId();
                    thisDesignData.lensMaterialType = actualMaterial.getMaterialType();
                    thisDesignData.lensCalcOutputData = anOutput;
                    
                    log.debug("design data is set up.");
                    
                    // Store the info in a way we can get to it easily later..
                    Set<DesignData> dataSoFar;
                    if ( structuredInfo.containsKey(frameIdentifier) ) {
                        dataSoFar = structuredInfo.get(frameIdentifier);
                    } else {
                        dataSoFar = new HashSet<DesignData>();
                    }
                    
                    log.debug("Adding design data to the set.. ");
                    dataSoFar.add(thisDesignData);
                    
                    log.debug("Adding design data back into the map");
                    structuredInfo.put(frameIdentifier, dataSoFar);
                    
                } else {
                    // We're missing the lens material or both - log it but can't do anything..
                    log.debug("Unable to find the lens material with identifier and focal type: " + lensMaterialIdentifier + ", " + requiredFocalType);
                }
            } else {
                // The lens material has come back with an error of some sort - just log it for now
                
                // Look up the material referred to to give us more useful logging
                LensMaterial referencedMaterial = LensMaterial.findByIdentifierAndFocalType(anOutput.getMaterialId(), requiredFocalType);
                
                if ( referencedMaterial ) {
                    log.debug("Lens material with a message other than OK and found the material in the DB - " + anOutput.getMaterialId() + " message: " + anOutput.getMessage() + " database material id: " + referencedMaterial.id);
                    designRequest.frameLensValidationMessages.add("Lens material returned from lens calculation with error message. Material database id: " + referencedMaterial.id + " calc identifier: " + anOutput.getMaterialId() + ", message: " + anOutput.getMessage());                    
                } else {
                    log.debug("Lens material with a message other than OK and not found the material in the DB - " + anOutput.getMaterialId() + " message: " + anOutput.getMessage());
                    designRequest.frameLensValidationMessages.add("Lens material returned from lens calculation with error message. Calc identifier: " + anOutput.getMaterialId() + ", message: " + anOutput.getMessage());                    
                }
            }
            
        }
        log.debug("At the end of the for loop...");

        // Remember the time when we stopped post processing
        if ( okCtr == 0 ) {
            designRequest.requestStatus = DesignStatus.NO_VIABLE_FRAME_LENS_COMBINATIONS;
            designRequest.statusMessages.add("No viable frame and lens combinations returned from the lens calculation");
        }
        designRequest.numOfViableCombinations = okCtr;
        designRequest.postProcessingEndTime = new Date();
        designRequest.save(flush: true)

        
        log.debug("About to return");
        return structuredInfo;

    }
    

    def testRangeConstraint(testVal, min, max, frameId, lensId, refinementType, refinementField, designRequest) {

        def retval = testRangeConstraint(testVal, min, max);
            
        if ( !retval ) {
            if ( "frame".equals(refinementType) ) {
                designRequest.frameValidationMessages.add("Frame " + frameId + " not viable for field '" + refinementField + "'. Min: " + min + ", max: " + max + ", value: "  + testVal);
            } else {
                designRequest.frameLensValidationMessages.add("Frame + " + frameId + " with lens " + lensId + " not viable for field '" + refinementField + "'. Min: " + min + ", max: " + max + ", value: "  + testVal);
            }
            
            designRequest.save(flush:true);
        }
        
        return retval;
    }

    def testRangeConstraint(testVal, min, max) {
        def retval = true;
        
        if ( testVal < min || testVal > max )
            retval = false;
                    
        return retval;
    }

    def testMinConstraint(testVal, min, frameId, lensId, refinementType, refinementField, designRequest) {
        def retval = true;
        
        if ( testVal < min )
            retval = false;

        if ( !retval ) {
            if ( "frame".equals(refinementType) ) {
                designRequest.frameValidationMessages.add("Frame " + frameId + " not viable for field '" + refinementField + "'. Min: " + min + ", value: "  + testVal);
            } else {
                designRequest.frameLensValidationMessages.add("Frame + " + frameId + " with lens " + lensId + " not viable for field '" + refinementField + "'. Min: " + min + ", value: "  + testVal);
            }
            
            designRequest.save(flush:true);
        }

        return retval;
    }
    
    

}
