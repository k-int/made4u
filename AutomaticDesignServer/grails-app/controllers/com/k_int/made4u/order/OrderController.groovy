package com.k_int.made4u.order

import com.k_int.made4u.job.DesignRequest;
import com.k_int.made4u.job.DesignStatus;
import com.k_int.made4u.job.OrderRequest;
import com.k_int.made4u.job.OrderStatus;

import com.k_int.made4u.customer.CustomerChoices;

import com.k_int.made4u.oma.OMA;

import grails.converters.JSON
import grails.converters.XML

class OrderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        def orders = OrderRequest.list(params);
        
        def responseVal = [orderList: orders, totalNum: OrderRequest.count()];
        
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
        def order = OrderRequest.findById(params.id);
        def retVal
        
        // Get the path to the order and design files so that we can link to them easily
        def designDirPath = grailsApplication.config.com.k_int.made4u.publicDesign.directory;
        if ( !designDirPath.endsWith("/") ) 
            designDirPath = designDirPath + "/";
            
        def orderDirPath = grailsApplication.config.com.k_int.made4u.publicOrder.directory;
        if ( !orderDirPath.endsWith("/") )
            orderDirPath = orderDirPath + "/";
        
        if ( !order ) {
            // No order with the specified id (or no id given)
            def errorMessage = message(code: 'retrieve.order.non.existent.id.error.message', default: 'The specified job number does not relate to an order in the system.');
            response.sendError(404, errorMessage);
        } else {
            retVal = [order: order, designDirPath: designDirPath, orderDirPath: orderDirPath]
        }
                
        withFormat {
            html retVal
            xml  { render retVal as XML }
            json { render retVal as JSON }
        }
    }
    
    def edit = {
        def order = OrderRequest.findById(params.id);
        def retVal;
        
        // Get the path to the order and design files so that we can link to them easily
        def designDirPath = grailsApplication.config.com.k_int.made4u.publicDesign.directory;
        if ( !designDirPath.endsWith("/") ) 
            designDirPath = designDirPath + "/";
            
        def orderDirPath = grailsApplication.config.com.k_int.made4u.publicOrder.directory;
        if ( !orderDirPath.endsWith("/") )
            orderDirPath = orderDirPath + "/";

        // Setup the list of possible values that the request status can be reset to
        def possibleNewRequestStatus = [[OrderStatus.REQUEST_SUBMITTED, "${message(code: 'retrieve.order.reset.start.from.scratch.message', default: 'Start order from scratch')}"], [OrderStatus.FRAME_CALCULATION_SUBMITTED, "${message(code: 'retrieve.order.reset.start.by.polling.frame.calc', default: 'Start order by checking for frame calculation output')}"], [OrderStatus.FRAME_CALCULATION_COMPLETED,"${message(code: 'retrieve.order.reset.start.after.frame.calc.message', default: 'Start order processing after frames calculation')}"], [OrderStatus.AWAITING_WCS_SUBMISSION,"${message(code: 'retrieve.order.reset.start.before.wcs.submission.message', default: 'Submit order to the WCS')}"]];
        
        if ( !order ) {
            // No order with the specified id (or no id given)
            def errorMessage = message(code: 'retrieve.order.non.existent.id.error.message', default: 'The specified job number does not relate to an order in the system.');
            response.sendError(404, errorMessage);
        } else {
            retVal = [order: order, designDirPath: designDirPath, orderDirPath: orderDirPath, possibleNewRequestStatus: possibleNewRequestStatus]
        }
        
        return retVal;
    }
    
    def update = {
        def order = OrderRequest.findById(params.id);
        def newStatus = OrderStatus.parseFromString(params.newRequestStatus);
        
        if ( !order ) {
            // No order to update
            def errorMessage = message(code: 'retrieve.order.non.existent.id.error.message', default: 'The specified job number does not relate to an order in the system.');
            response.sendError(404, errorMessage);
        } else {
            if ( !newStatus ) {
                // No status to update to
                flash.error = "${message(code: 'retrieve.order.non.existent.new.status.message', default: 'The specified new status was not recognised')}"
                redirect(action: "edit", id: params.id)

            } else {
                // Everything's ready - make the change
                
                order.requestStatus = newStatus;
                if ( newStatus == OrderStatus.REQUEST_SUBMITTED) {
                    // Clear all but the submitted time
                    order.inputParsingStartTime = null;
                    order.inputParsingEndTime = null;
                    order.framesCalcSubmissionTime = null;
                    order.framesCalcSubmissionEndTime = null;
                    order.framesCalcMarkedAsCompletedTime = null;
                    order.framesCalcPostProcessingStartTime = null;
                    order.framesCalcPostProcessingEndTime = null;
                    order.lensCalcInputCreationStartTime = null;
                    order.lensCalcInputCreationEndTime = null;
                    order.lensCalcSubmissionTime = null;
                    order.lensCalcReturnTime = null;
                    order.coatingCreationStartTime = null;
                    order.coatingCreationEndTime = null;
                    order.statusMessages.clear();
                    
                    order.save(flush: true);
                } else if ( newStatus == OrderStatus.FRAME_CALCULATION_SUBMITTED) {
                    // Clear everything after the frame calculation submission
                    order.framesCalcMarkedAsCompletedTime = null;
                    order.framesCalcPostProcessingStartTime = null;
                    order.framesCalcPostProcessingEndTime = null;
                    order.lensCalcInputCreationStartTime = null;
                    order.lensCalcInputCreationEndTime = null;
                    order.lensCalcSubmissionTime = null;
                    order.lensCalcReturnTime = null;
                    order.coatingCreationStartTime = null;
                    order.coatingCreationEndTime = null;
                    order.statusMessages.clear();
                    
                    order.save(flush: true);
                
                } else if ( newStatus == OrderStatus.FRAME_CALCULATION_COMPLETED) {
                    // Clear everything after the frame calculation
                    order.framesCalcPostProcessingStartTime = null;
                    order.framesCalcPostProcessingEndTime = null;
                    order.lensCalcInputCreationStartTime = null;
                    order.lensCalcInputCreationEndTime = null;
                    order.lensCalcSubmissionTime = null;
                    order.lensCalcReturnTime = null;
                    order.coatingCreationStartTime = null;
                    order.coatingCreationEndTime = null;
                    order.statusMessages.clear();
                    
                    order.save(flush: true);
                } else if ( newStatus == OrderStatus.AWAITING_WCS_SUBMISSION) {
                    // Clear everything about actual submission to the WCS
                    order.statusMessages.clear();
                    
                    order.save(flush: true);
                }
                
                // Now redirect to the show page
                flash.message = "${message(code: 'retrieve.order.status.changed.message', default: 'The new status has been set for the order')}";
                redirect(action: "show", id: params.id);
            }
        }
        
    }
    
    def save = {
        def custChoiceFile = request.getFile('customerChoices');
        def custChoiceFileString = params.customerChoicesString;
        def orderId = params.orderId;

        def retVal = null;
        

        // Decide whether we've been given a file or a string
        def workingWithFiles = false;
        def workingWithStrings = false;
        
        if ( custChoiceFile != null && !custChoiceFile.empty ) {
            workingWithFiles = true;
        } else if ( custChoiceFileString && !"".equals(custChoiceFileString) ) {
            workingWithStrings = true;
        } 

        if ( (workingWithFiles || workingWithStrings) && orderId ) {

            // We have the required files - check that we have a design request for this order Id
            // and that we have are ready to actually place an order!
            
            def designReq = DesignRequest.findByOrderId(orderId);
            
            if ( designReq ) {
                // We have received a design request - have we got everything on disk that we need?
                
                if ( designReq.requestStatus == DesignStatus.DESIGN_RETURNED || designReq.requestStatus == DesignStatus.ORDER_PLACED) {
                    // Design has been returned - we should have everything that we need so we're OK to continue..
                    log.debug("Manufacturing order submitted for an order ID that has previously had viable designs returned. Order ID: " + orderId);
                    
                    // Check that we don't already have an order being submitted / previously submitted
                    def orderReq = OrderRequest.findByOrderId(orderId);
                    
                    //if ( !orderReq ) {
                        // No previous order - ok to continue
                        
                        // Check that the provided customer choices are valid before accepting the order
                        log.debug("About to parse the customer choices");
                        
                        // First write the file to the temp directory
                        def tempDir = grailsApplication.config.com.k_int.made4u.tmp.directory
                        if ( !tempDir.endsWith(File.separator) )
                            tempDir = tempDir + File.separator;
                        File actualTempDir = new File(tempDir);
                        if ( !actualTempDir.exists() ) 
                            actualTempDir.mkdirs();
                            
                        def tempFilename = "tempCustChoice-" + System.currentTimeMillis();
                        
                        if ( workingWithFiles ) {
                            // Files as input - just save it
                            custChoiceFile.transferTo( new File( tempDir, tempFilename));
                        } else {
                            // String as input - write it to the file
                            def custChoiceFileOnDisk = new File(tempDir, tempFilename);
                            custChoiceFileOnDisk.write(custChoiceFileString);
                        }
                        
                        // Now do the actual parsing and validating
                        def customerChoices;
                        def customerChoicesValidation;
                        def customerChoicesOK = true;
                        def customerChoicesMessage = "";
                        
                        try {
                            customerChoices = CustomerChoices.parseFile(new File(tempDir, tempFilename));
                            customerChoicesValidation = customerChoices.validateChoices();
                            log.debug("Back from parsing the customer choices - success: " + customerChoicesValidation.success);
                        } catch (Exception e) {
                            log.error("Exception thrown when parsing the customer choices data: " + e.getMessage());
                            e.printStackTrace();
                            
                            customerChoicesOK = false;
                            customerChoicesMessage = "Error thrown when parsing the customer choices data: " + e.getMessage();
                        }
                        
                        // Clear up the temp file
                        def fileToDel = new File(tempDir, tempFilename);
                        fileToDel.delete();
                        
                        if ( customerChoicesOK ) {

                            if ( customerChoicesValidation.success ) {

                                // Update the design request to say that the order has been submitted
                                designReq.requestStatus = DesignStatus.ORDER_PLACED;
                                designReq.save(flush:true);

                                // Save the provided file to disk
                                def uploadDir = grailsApplication.config.com.k_int.made4u.order.directory
                                if ( uploadDir != null && !"".equals(uploadDir) ) {

                                    if ( !uploadDir.endsWith(File.separator) )
                                        uploadDir = uploadDir + File.separator;

                                    // Now set up and save the order request so that any further resubmissions are blocked
                                    orderReq = new OrderRequest(uuid: designReq.uuid, orderId: orderId, orderRequestSubmittedTime: new Date(), linkedDesignId: designReq.id);
                                    orderReq.save(flush: true);

                                    if ( orderReq.errors ) {
                                        orderReq.errors.each() {
                                            log.error("Error: " + it);
                                        }
                                    }

                                    def jobBaseDir = uploadDir + orderReq.id;
                                    def initialInputDir = jobBaseDir + File.separator + "inputFiles";

                                    // Make the upload directory
                                    log.error("jobBaseDir = " + jobBaseDir)
                                    log.error("initialInputDir = " + initialInputDir);
                                    new File(initialInputDir).mkdirs();

                                    // Now write the file out to the directory
                                    if ( workingWithFiles ) {
                                        // Files as input - just save it
                                        custChoiceFile.transferTo( new File( initialInputDir, "customerChoices"));
                                    } else {
                                        // String as input - write it to the file
                                        def custChoiceFileOnDisk = new File(initialInputDir, "customerChoices");
                                        custChoiceFileOnDisk.write(custChoiceFileString);
                                    }

                                    // All done for here - just return to the user that we've submitted..
                                    retVal = [id: orderReq.id, orderId: orderId, message: "Order submitted ready to be processed", success: true, shouldRetry: false]

                                } else {
                                    log.error("Manufacturing order submitted but unable to determine where to store the uploaded file. Unable to continue");
                                    response.sendError(500, "Manufacturing order submitted but unable to determine where to store the uploaded file. Unable to continue");
                                }
                            } else {
                                // Invalid customer choices..
                                log.error("Manufacturing order submitted with invalid customer choices. Order ID: " + orderId);
                                log.debug("customerChoices: " + customerChoices.toDebugString());
                                StringBuilder messageBuilder = new StringBuilder();
                                customerChoicesValidation.messages.each() {
                                    messageBuilder.append(it).append(".\n");
                                }
                                log.debug("customerChoices validation messages: " + messageBuilder.toString());

                                StringBuilder retvalMessageBuilder = new StringBuilder();
                                retvalMessageBuilder.append(message(code: 'order.request.invalid.customer.choices.message', default: 'Manufacturing order submitted with an invalid customer choices file.'));
                                retvalMessageBuilder.append("\n").append(messageBuilder.toString());

                                retVal = [orderId: orderId, message: retvalMessageBuilder.toString(), success: false, shouldRetry: false ]
                            }                        
                        } else {
                            // Unable to process the customer choices at all - error thrown and caught
                            retVal = [orderId: orderId, message: customerChoicesMessage, success: false, shouldRetry: false];
                        }
                    //} else {
                        //// Order previously submitted - can't continue as an order can only be submitted once
                        //log.error("Manufacturing order submitted for an order ID that has already had an order submitted. Can't resubmit. Order ID: " + orderId);
                        //retVal = [orderId: orderId, message: message(code: 'order.request.order.id.conflict.message', default: 'Manufacturing order submitted with an order ID that has already had an order submitted.'), success: false, shouldRetry: false]
                    //}
                //} else if ( designReq.requestStatus == DesignStatus.ORDER_PLACED ) {
                    //// Submitting an order for a design that's already been ordered..
                    //log.error("Manufacturing order submitted for an order ID that has already had an order submitted. Order ID: " + orderId);
                    //retVal = [orderId: orderId, message: message(code: 'order.request.order.id.conflict.message', default: 'Manufacturing order submitted with an order ID that has already had an order submitted.'), success: false, shouldRetry: false]
                } else if ( designReq.requestStatus == DesignStatus.ERROR ) {
                    // An error occurred when submitting the design - can't continue with placing an order
                    log.error("Manufacturing order submitted for an order ID that failed the get design phase. Order ID: " + orderId);
                    retVal = [orderId: orderId, message: message(code: 'order.request.order.id.design.failure.message', default: 'Manufacturing order submitted with an order ID that could not successfully produce a design'), success: false, shouldRetry: false]
                } else {
                    // The order is still being processed as part of the codesign - can't place a manufacturing order
                    // until the previous stage is finished!
                    log.debug("Manufacturing order submitted for an order ID that is still being processed by the get design stage. Order ID: " + orderId);
                    retVal = [orderId: orderId, message: message(code: 'order.request.order.id.design.still.processing.message', default: 'Manufacturing order submitted with an order ID that is still being processed by the design system. Please try again later'), success: false, shouldRetry: true]

                }
            } else {
                // No design request for this ID - return an error
                log.error("Manufacturing order submitted for an unrecognised order ID: " + orderId);
                retVal = [orderId: orderId, message: message(code: 'order.request.order.id.not.recognised.message', default: 'Manufacturing order submitted with an order ID that is not recognised. Has a design request already been submitted with this order ID?'), success: false, shouldRetry: false]
            }
            
        } else {
            log.error("Manufacturing order submitted without all of the required parameters");
            retVal = [orderId: orderId, message: message(code: 'order.request.missing.parameters.message', default: 'Not all of the expected parameters were specified in order to place a manufacturing order, or the files provided were empty. Expected: (customerChoices or customerChoicesString) and orderId'), success: false, shouldRetry: false]
        }

        withFormat {
            multipartForm { render retVal as XML }
            xml { render retVal as XML }
            json { render retVal as JSON }
        }
        
        return retVal;

    }
    
}
