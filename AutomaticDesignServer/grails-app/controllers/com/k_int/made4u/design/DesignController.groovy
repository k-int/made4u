package com.k_int.made4u.design

import com.k_int.made4u.job.DesignRequest;
import com.k_int.made4u.job.DesignStatus;
import com.k_int.made4u.job.OrderRequest;
import com.k_int.made4u.job.OrderStatus;

import grails.converters.JSON
import grails.converters.XML


import com.k_int.made4u.oma.OMA;

class DesignController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of all design requests.. count: " + DesignRequest.count());

        def responseVal = [designRequests: DesignRequest.list(params), totalNum : DesignRequest.count()]
        
        withFormat {
            html responseVal
            xml { render responseVal as XML }
            json { render responseVal as JSON }
        }
    }
    
    def create = {
        // Don't need to do anything
    }
    
    def show = {
        def designReq = DesignRequest.get(params.id);
        
        if ( !designReq ) {
            // No design with the specified id (or no id given)
            def errorMessage = message(code: 'retrieve.design.non.existent.id.error.message', default: 'The specified job number does not relate to a job in the system. Unable to return a design');
            response.sendError(404, errorMessage);
        } else {
            def hasConfigsAvailable = false;
            def configFilePath = "";
            def configDirPath = grailsApplication.config.com.k_int.made4u.publicDesign.directory;
            if ( !configDirPath.endsWith("/") ) 
                    configDirPath = configDirPath + "/";
            
            if ( designReq.requestStatus == DesignStatus.DESIGN_RETURNED || designReq.requestStatus == DesignStatus.ORDER_PLACED ) {
                hasConfigsAvailable = true;
                    
                configFilePath = configDirPath + params.id + "/returnedDesigns/designResponse.xml";
            }
            
            def responseVal = [jobNumber: params.id, hasConfigsAvailable: hasConfigsAvailable, configFilePath: configFilePath, designReq: designReq, configDirPath: configDirPath]
            def api_responseVal = [id: params.id, orderId: designReq.orderId, requestStatus: designReq.requestStatus, hasConfigsAvailable: hasConfigsAvailable, configFilePath: configFilePath]
            
            
            log.debug("responseVal = " + responseVal);
            def fileDir = 
            withFormat {
                html responseVal
                xml { render api_responseVal as XML }
                json { render api_responseVal as JSON }
            }
        }
    }
    
    def edit = {
        def designReq = DesignRequest.get(params.id);
        
        if ( !designReq ) {
            // No design with the specified id (or no id given)
            def errorMessage = message(code: 'retrieve.design.non.existent.id.error.message', default: 'The specified job number does not relate to a job in the system. Unable to return a design');
            response.sendError(404, errorMessage);
        } else {
            def hasConfigsAvailable = false;
            def configFilePath = "";
            def configDirPath = grailsApplication.config.com.k_int.made4u.publicDesign.directory;
            if ( !configDirPath.endsWith("/") ) 
                    configDirPath = configDirPath + "/";
            
            if ( designReq.requestStatus == DesignStatus.DESIGN_RETURNED || designReq.requestStatus == DesignStatus.ORDER_PLACED ) {
                hasConfigsAvailable = true;
                    
                configFilePath = configDirPath + params.id + "/returnedDesigns/designResponse.xml";
            }
            
            // Setup the list of possible values that the request status can be reset to
            def possibleNewRequestStatus = [[DesignStatus.REQUEST_SUBMITTED, "${message(code: 'retrieve.design.reset.start.from.scratch.message', default: 'Start design request from scratch')}"], [DesignStatus.AWAITING_PROCESSING_PART_TWO,"${message(code: 'retrieve.design.reset.start.after.frame.calc.message', default: 'Start design request processing after frames calculation')}"]];

            
            def responseVal = [jobNumber: params.id, hasConfigsAvailable: hasConfigsAvailable, configFilePath: configFilePath, designReq: designReq, configDirPath: configDirPath, possibleNewRequestStatus: possibleNewRequestStatus]
            def api_responseVal = [id: params.id, orderId: designReq.orderId, requestStatus: designReq.requestStatus, hasConfigsAvailable: hasConfigsAvailable, configFilePath: configFilePath]
            
            
            log.debug("responseVal = " + responseVal);
            def fileDir = 
            withFormat {
                html responseVal
                xml { render api_responseVal as XML }
                json { render api_responseVal as JSON }
            }
        }
    }
    
    
    def update = {
        def designReq = DesignRequest.findById(params.id);
        def newStatus = DesignStatus.parseFromString(params.newRequestStatus);
        
        if ( !designReq ) {
            // No design request to update
            def errorMessage = message(code: 'retrieve.design.non.existent.id.error.message', default: 'The specified job number does not relate to a job in the system. Unable to return a design');
            response.sendError(404, errorMessage);
        } else {
            if ( !newStatus ) {
                // No status to update to
                flash.error = "${message(code: 'retrieve.design.non.existent.new.status.message', default: 'The specified new status was not recognised')}"
                redirect(action: "edit", id: params.id)

            } else {
                // Everything's ready - make the change
                
                designReq.requestStatus = newStatus;
                if ( newStatus == DesignStatus.REQUEST_SUBMITTED) {
                    // Clear all but the submitted time
                    designReq.framesCalcSubmissionTime = null;
                    designReq.framesCalcReturnTime = null;
                    designReq.frameRefinementStartTime = null;
                    designReq.frameRefinementEndTime = null;
                    designReq.lensRefinementStartTime = null;
                    designReq.lensRefinementEndTime = null;
                    designReq.lensCalcInputCreationStartTime = null;
                    designReq.lensCalcInputCreationEndTime = null;
                    designReq.lensCalcSubmissionTime = null;
                    designReq.lensCalcReturnTime = null;
                    designReq.postProcessingStartTime = null;
                    designReq.postProcessingEndTime = null;
                    designReq.responseGivenTime = null;
                    designReq.numOfFramesFromFrameCalc = null;
                    designReq.numOfFramesFromFrameCalcInCatalogue = null;
                    designReq.numOfFramesAfterFrameCalcFilter = null;
                    designReq.numOfInputsToLensCalc = null;
                    designReq.numOfResultsFromLensCalc = null;
                    designReq.numOfViableCombinations = null;
                    designReq.statusMessages.clear();
                    designReq.frameValidationMessages.clear();
                    designReq.lensValidationMessages.clear();
                    designReq.frameLensValidationMessages.clear();

                    designReq.save(flush: true);
                } else if ( newStatus == DesignStatus.AWAITING_PROCESSING_PART_TWO) {
                    // Clear everything after the frame calculation
                    designReq.frameRefinementStartTime = null;
                    designReq.frameRefinementEndTime = null;
                    designReq.lensRefinementStartTime = null;
                    designReq.lensRefinementEndTime = null;
                    designReq.lensCalcInputCreationStartTime = null;
                    designReq.lensCalcInputCreationEndTime = null;
                    designReq.lensCalcSubmissionTime = null;
                    designReq.lensCalcReturnTime = null;
                    designReq.postProcessingStartTime = null;
                    designReq.postProcessingEndTime = null;
                    designReq.responseGivenTime = null;
                    designReq.numOfFramesFromFrameCalc = null;
                    designReq.numOfFramesFromFrameCalcInCatalogue = null;
                    designReq.numOfFramesAfterFrameCalcFilter = null;
                    designReq.numOfInputsToLensCalc = null;
                    designReq.numOfResultsFromLensCalc = null;
                    designReq.numOfViableCombinations = null;
                    designReq.statusMessages.clear();
                    designReq.frameValidationMessages.clear();
                    designReq.lensValidationMessages.clear();
                    designReq.frameLensValidationMessages.clear();
                    
                    designReq.save(flush: true);
                }
                
                // Now redirect to the show page
                flash.message = "${message(code: 'retrieve.design.status.changed.message', default: 'The new status has been set for the design')}";
                redirect(action: "show", id: params.id);
            }
        }
        
    }
    
    
    def save = {
        def morphoFile = request.getFile('morphoFile');
        def prescripFile = request.getFile('optPreFile');
        def meshFile = request.getFile('nasalFile');
        
        def morphoFileString = params.morphoFileString;
        def prescripFileString = params.optPreFileString;
        def meshFileString = params.nasalFileString;
        
        def orderId = params.orderId;
        def returnId = null;
        
        def retVal = null;

        def workingWithFiles = false;
        def workingWithStrings = false;
        
        
        if ( morphoFile != null && !morphoFile.empty
            && prescripFile != null && !prescripFile.empty
            && meshFile != null && !meshFile.empty && orderId ) {
            
            workingWithFiles = true;
        } else if ( morphoFileString && prescripFileString && meshFileString && orderId ) {
            workingWithStrings = true;
        } 

        if ( workingWithFiles || workingWithStrings ) {
            // We have the required files - set up a design request in the database to assign things to
            def uuid = java.util.UUID.randomUUID().toString();
            
            def designReq = DesignRequest.findByOrderId(orderId);
            
            if ( designReq == null ) {
                // There's not already a design request with this UUID - continue and process
                designReq = new DesignRequest(uuid: uuid, requestSubmittedTime: new Date(), orderId: orderId);
                designReq.save(flush: true);
                
                def jobNum = designReq.id;
//                def jobNum = orderId;
    
                // Save the input files to disk for use in later stages
                def uploadDir = grailsApplication.config.com.k_int.made4u.design.directory
                if ( uploadDir != null && !"".equals(uploadDir) ) {

                    log.error("File.separatorChar = " + File.separatorChar + " pathSeparator: " + File.pathSeparator);

                    if ( !uploadDir.endsWith(File.separator) )
                        uploadDir = uploadDir + File.separator;


                    def jobBaseDir = uploadDir + jobNum;
                    def initialInputDir = jobBaseDir + File.separator + "inputFiles";
                    
                    // Make the upload directory
                    log.error("jobBaseDir = " + jobBaseDir)
                    log.error("initialInputDir = " + initialInputDir);
                    new File(initialInputDir).mkdirs();

                    // Now write the files out to the directory
                    if ( workingWithFiles ) {
                        // Files as input - just save them
                        morphoFile.transferTo( new File( initialInputDir, "morphologicalData"));
                        prescripFile.transferTo( new File( initialInputDir, "opticalPrescription"));
                        meshFile.transferTo( new File (initialInputDir, "nasalMesh"));
                    } else {
                        // Strings as input - write them to the files
                        def morphoFileOnDisk = new File(initialInputDir, "morphologicalData");
                        morphoFileOnDisk.write(morphoFileString);
                        def prescripFileOnDisk = new File(initialInputDir, "opticalPrescription");
                        prescripFileOnDisk.write(prescripFileString);
                        def meshFileOnDisk = new File(initialInputDir, "nasalMesh");
                        meshFileOnDisk.write(meshFileString);
                    }
                    
                    // Return to the user so that they can follow the request elsewhere..
                    retVal = [id: designReq.id, requestId: orderId, status: designReq.requestStatus, message: message(code: 'design.controller.submitted.successfully.message', default: "Design request submitted successfully"), success: true] // TODO - change this to a message
                    
                    log.debug("retVal now set to " + retVal);
                    returnId = designReq.id;
                    
                } else {
                    log.error("Unable to determine where to place uploaded files");
                    response.sendError(500, "Unable to determine where to place uploaded files");
                }
            } else {
                // Already got a design request with this UUID - problem!
                retVal = [requestId: orderId, status: DesignStatus.ERROR, message: message(code: 'design.controller.submitted.failure.conflict.message', default: "Unable to create the new design as it conflicts with a previous submission"), success: false]
                log.error("Attempted to create a design request in the database using the same UUID as a previous request. UUID: " + uuid);
//                response.sendError(500, "Unable to create the new design as it conflicts with a previous submission");
            }
        } else {
            def errorMessage = "Request for designs received without all of the required parameters: orderId: " + orderId + " morphoFile: " + morphoFile + " optPreFile: " + prescripFile + " nasalFile: " + meshFile + " morphoFileString: " + morphoFileString + " optPreFileString: " + prescripFileString + " nasalFileString: " + meshFileString;
            
            log.error("Request for designs received without all of the required parameters: orderId: " + orderId + " morphoFile: " + morphoFile + " optPreFile: " + prescripFile + " nasalFile: " + meshFile + " morphoFileString: " + morphoFileString + " optPreFileString: " + prescripFileString + " nasalFileString: " + meshFileString);
//            retVal = [requestId: orderId, status: DesignStatus.ERROR, message: message(code: 'design.controller.submitted.failure.missing.parameters.message', default: "Unable to create the new design as not all of the required information was provided. Expected: (morphoFile, optPreFile, nasalFile and orderId) or (morphoFileString, optPreFileString, nasalFileString and orderId)"), success: false]
            retVal = [requestId: orderId, status: DesignStatus.ERROR, message: errorMessage, success: false]
//            response.sendError(400, "Not all of the expected parameters were specified in order to return a file, or the files provided were empty. Expected: "
//                    + "morphoFile, optPreFile, nasalFile, orderId");
        }
        
        withFormat {
            multipartForm { render retVal as XML }
            xml { render retVal as XML }
            json { render retVal as JSON }
        }

        
    }
    
}
