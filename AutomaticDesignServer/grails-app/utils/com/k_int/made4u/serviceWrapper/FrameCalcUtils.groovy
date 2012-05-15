package com.k_int.made4u.serviceWrapper

import com.k_int.made4u.job.DesignRequest;
import com.k_int.made4u.job.DesignStatus;

/**
 * Utility class to wrap the communications to the Frames calculation service and 
 * parse any output, etc.
 * 
 * @author rpb rich@k-int.com
 * @version 1.0 06.10.11
 */
class FrameCalcUtils {
	
    // Frame calculation service
    def framesCalculationService

    /**
     * Wrap the communications with the Frame calculation service parsing the output and returning it in a useful format
     */
    def performFrameCalculation(designRequest, morphoFilePath, meshFilePath, frameCalcDir, inputFilename, outputFilename) {
        
        designRequest.requestStatus = DesignStatus.FRAMES_CALCULATION;
        designRequest.framesCalcSubmissionTime = new Date();
        designRequest.save(flush: true)
        
        log.debug("in the performFrameCalculation method");
        
//        def calcSuccess = framesCalculationService.calc(morphoFilePath, meshFilePath, frameCalcDir, inputFilename, outputFilename);

        // Remember the time when the calculation returned
        designRequest.framesCalcReturnTime = new Date();
        designRequest.save(flush: true);
        
        def calcSuccess = true;
        frameCalcDir = "/home/rpb/Documents/netbeansWorkspace/made4u.git/AutomaticDesignServer/web-app/tmp/design/95/frameCalcFiles"; // TODO - change this back to actually talk to the service
        def retVal = null;
        
        if ( calcSuccess ) {
            // Parse the information out of the response file
            def frameData = new XmlSlurper().parseText(new File(frameCalcDir, outputFilename).text).declareNamespace(made4u: 'http://made4u.info',soap: 'http://www.w3.org/2001/12/soap-envelope');
            
            log.debug("About to loop through the xml data..");
            
            retVal = [];
            
            frameData.Body.made4u.frame_fbm_input_data.each {
                def designName = it.design_name.text().trim();
                def boxingHorizontal = new Float(it.boxing_horizontal.text().trim());
                def leftTemplePantoAngle = new Float(it.left_temple_pantoscopic_angle.text().trim());
                def rightTemplePantoAngle = new Float(it.right_temple_pantoscopic_angle.text().trim());
                def bridgeWidth = new Float(it.bridge_width.text().trim());
                def bridgeHeight = new Float(it.bridge_height.text().trim());
                def heelWidth = new Float(it.heel_width.text().trim());
                def internalRimReduction = new Float(it.internal_rim_reduction.text().trim());
                def baseOfFrame = new Float(it.base_of_frame.text().trim());
                def wrapAngle = new Float(it.wrap_angle.text().trim());
                def totalLength = new Float(it.total_length.text().trim());
                def leftTempleLength = new Float(it.left_temple_length.text().trim());
                def rightTempleLength = new Float(it.right_temple_length.text().trim());
                def leftTempleOpeningAngle = new Float(it.left_temple_opening_angle.text().trim());
                def rightTempleOpeningAngle = new Float(it.right_temple_opening_angle.text().trim());
                def rightTempleMainCurvature = new Float(it.right_temple_main_curvature.text().trim());
                def leftTempleMainCurvature = new Float(it.left_temple_main_curvature.text().trim());
                def rightTempleTerminalAngle = new Float(it.right_temple_terminal_angle.text().trim());
                def leftTempleTerminalAngle = new Float(it.left_temple_terminal_angle.text().trim());
                def returnedOma = it.oma.text().trim();
                
                def thisFrame = [designName: designName, boxingHorizontal: boxingHorizontal, leftTemplePantoAngle: leftTemplePantoAngle, rightTemplePantoAngle: rightTemplePantoAngle,
                        bridgeWidth: bridgeWidth, bridgeHeight: bridgeHeight, heelWidth: heelWidth, internalRimReduction: internalRimReduction, baseOfFrame: baseOfFrame,
                        wrapAngle: wrapAngle, totalLength: totalLength, leftTempleLength: leftTempleLength, rightTempleLength: rightTempleLength,
                        leftTempleOpeningAngle: leftTempleOpeningAngle, rightTempleOpeningAngle: rightTempleOpeningAngle, rightTempleMainCurvature: rightTempleMainCurvature,
                        leftTempleMainCurvature: leftTempleMainCurvature, rightTempleTerminalAngle: rightTempleTerminalAngle, leftTempleTerminalAngle: leftTempleTerminalAngle,
                        oma: returnedOma]
                
                retVal.add(thisFrame);
            }
            
            log.debug("Finished looping - retVal size = " + retVal.size());
            
        } else {
            designRequest.requestStatus = DesignStatus.ERROR;
            designRequest.statusMessages.add("An error occurred talking to the frames calculation service!"); // TODO - sort out language!!
            designRequest.save(flush: true);
        }
        
        
        return retVal;
    }
    
}

