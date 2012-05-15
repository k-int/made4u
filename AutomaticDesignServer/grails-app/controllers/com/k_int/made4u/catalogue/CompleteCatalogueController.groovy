package com.k_int.made4u.catalogue

import com.k_int.made4u.job.CompleteDummyDesignRequest;
import com.k_int.made4u.job.CompleteDummyDesignStatus;

import grails.converters.JSON
import grails.converters.XML

import com.k_int.made4u.frame.Frame;
import com.k_int.made4u.frame.FrameBaseColour;
import com.k_int.made4u.frame.FrameAestheticConfiguration;
import com.k_int.made4u.frame.FrameTexture;
import com.k_int.made4u.frame.Precooked;
import com.k_int.made4u.frame.Use;
import com.k_int.made4u.frame.EmotionalVariable;
import com.k_int.made4u.frame.RimType;

import com.k_int.made4u.lens.LensMaterial;
import com.k_int.made4u.lens.LensMaterialType;
import com.k_int.made4u.lens.LensColour;
import com.k_int.made4u.lens.LensColourType;
import com.k_int.made4u.lens.LensSpecialColour;
import com.k_int.made4u.lens.LensSpecialColourType;
import com.k_int.made4u.lens.Coating;
import com.k_int.made4u.lens.CoatingType;
import com.k_int.made4u.lens.LensTopcoat;


class CompleteCatalogueController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of all complete catalogue requests.. count: " + CompleteDummyDesignRequest.count());

        def responseVal = [designRequests: CompleteDummyDesignRequest.list(params), totalNum : CompleteDummyDesignRequest.count()]
        
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
        // TODO - to here..
        def designReq = CompleteDummyDesignRequest.get(params.id);
        
        if ( !designReq ) {
            // No design with the specified id (or no id given)
            def errorMessage = message(code: 'retrieve.design.non.existent.id.error.message', default: 'The specified job number does not relate to a job in the system. Unable to return a design');
            response.sendError(404, errorMessage);
        } else {
            def hasConfigsAvailable = false;
            def configFilePath = "";
            def configDirPath = grailsApplication.config.com.k_int.made4u.dummy.publicDesign.directory;
            if ( !configDirPath.endsWith("/") ) 
                    configDirPath = configDirPath + "/";
            
            if ( designReq.requestStatus == CompleteDummyDesignStatus.DESIGN_RETURNED || designReq.requestStatus == CompleteDummyDesignStatus.ORDER_PLACED ) {
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
            
            def designReq = CompleteDummyDesignRequest.findByOrderId(orderId);
            
            if ( designReq == null ) {
                // There's not already a design request with this UUID - continue and process
                designReq = new CompleteDummyDesignRequest(uuid: uuid, requestSubmittedTime: new Date(), orderId: orderId);
                designReq.save(flush: true);
                
                def jobNum = designReq.id;
//                def jobNum = orderId;
    
                // Save the input files to disk for use in later stages
                def uploadDir = grailsApplication.config.com.k_int.made4u.dummy.design.directory
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
                    
                    // Assemble the XML file to be returned to the user when they ask for it..
                    def returnedDesignsDir = jobBaseDir + File.separator + "returnedDesigns";
                    new File(returnedDesignsDir).mkdir();
                    createCompleteCatalogueXMl(returnedDesignsDir, "designResponse.xml");
                    
                    // Update the status to say that the design is ready to return
                    designReq.requestStatus = CompleteDummyDesignStatus.DESIGN_RETURNED;
                    designReq.save(flush: true);
                    
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
                retVal = [requestId: orderId, status: CompleteDummyDesignStatus.ERROR, message: message(code: 'design.controller.submitted.failure.conflict.message', default: "Unable to create the new design as it conflicts with a previous submission"), success: false]
                log.error("Attempted to create a design request in the database using the same UUID as a previous request. UUID: " + uuid);
//                response.sendError(500, "Unable to create the new design as it conflicts with a previous submission");
            }
        } else {
            def errorMessage = "Request for dummy designs received without all of the required parameters: orderId: " + orderId + " morphoFile: " + morphoFile + " optPreFile: " + prescripFile + " nasalFile: " + meshFile + " morphoFileString: " + morphoFileString + " optPreFileString: " + prescripFileString + " nasalFileString: " + meshFileString;
            
            log.error("Request for designs received without all of the required parameters: orderId: " + orderId + " morphoFile: " + morphoFile + " optPreFile: " + prescripFile + " nasalFile: " + meshFile + " morphoFileString: " + morphoFileString + " optPreFileString: " + prescripFileString + " nasalFileString: " + meshFileString);
//            retVal = [requestId: orderId, status: DesignStatus.ERROR, message: message(code: 'design.controller.submitted.failure.missing.parameters.message', default: "Unable to create the new design as not all of the required information was provided. Expected: (morphoFile, optPreFile, nasalFile and orderId) or (morphoFileString, optPreFileString, nasalFileString and orderId)"), success: false]
            retVal = [requestId: orderId, status: CompleteDummyDesignStatus.ERROR, message: errorMessage, success: false]
//            response.sendError(400, "Not all of the expected parameters were specified in order to return a file, or the files provided were empty. Expected: "
//                    + "morphoFile, optPreFile, nasalFile, orderId");
        }
        
        withFormat {
            multipartForm { render retVal as XML }
            xml { render retVal as XML }
            json { render retVal as JSON }
        }

        
    }
    
    private void createCompleteCatalogueXMl(returnedDesignsDir, dummyDesignResponseFileName) {
        
        // First go and get all of the Frame and lens information - make sure they are ordered by display order..
        def listParams = [sort:"displayOrder",order:"asc"];
        
        def allFrames = Frame.list(listParams);
        
        def allWhiteLensMaterials = LensMaterial.findAllByMaterialType(LensMaterialType.WHITE, [sort: "id", order: "asc"]);
        def allPolarizingLensMaterials = LensMaterial.findAllByMaterialType(LensMaterialType.POLARIZING, [sort: "id", order: "asc"]);
        def allPhotochromicLensMaterials = LensMaterial.findAllByMaterialType(LensMaterialType.PHOTOCHROMIC, [sort: "id", order: "asc"]);
        
        def allLensColours = LensColour.list([sort: "colourType", order: "asc"]);
        def allPolarizingLensColours = LensSpecialColour.findAllBySpecialColourType(LensSpecialColourType.POLARIZING);
        def allPhotochromicLensColours = LensSpecialColour.findAllBySpecialColourType(LensSpecialColourType.PHOTOCHROMIC);
        
        def allARCoatings = Coating.findAllByCoatingType(CoatingType.AR);
        def allMirrorCoatings = Coating.findAllByCoatingType(CoatingType.MIRROR);
        
        def allTopcoats = LensTopcoat.list();
        
        // Set up the head of the file
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<m4u:AutomaticDesignResponse xmlns:m4u=\"http://www.made4u.info/schema/automaticDesignResponse/\" ");
        xmlBuilder.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        xmlBuilder.append("xsi:schemaLocation=\"http://www.made4u.info/schema/automaticDesignResponse/ http://www.k-int.com/schema/made4u/m4uAutoDesign.xsd\">");
        
        // Loop through each frame outputting it
        for( Frame viableFrame: allFrames ) {
            
            xmlBuilder.append("<m4u:viableFrame>");
            
            // General frame information 
            xmlBuilder.append("<m4u:General FrameID=\"").append(viableFrame.identifier).append("\">");
            xmlBuilder.append("<m4u:Gender>").append(viableFrame.gender).append("</m4u:Gender>");
            xmlBuilder.append("<m4u:RimType>").append(viableFrame.rimType).append("</m4u:RimType>");
            xmlBuilder.append("<m4u:RimShape>").append(viableFrame.rimShape?.name?.toUpperCase()).append("</m4u:RimShape>");
            xmlBuilder.append("<m4u:EndpieceHeight>").append(viableFrame.endpieceHeight).append("</m4u:EndpieceHeight>");
            xmlBuilder.append("<m4u:Use>").append(viableFrame.frameUse).append("</m4u:Use>");
            xmlBuilder.append("<m4u:Material>").append(viableFrame.material?.name.toUpperCase()).append("</m4u:Material>");
            xmlBuilder.append("<m4u:BasePriceFullyPersonalised>").append(viableFrame.fullyPersonalisedBasePrice).append("</m4u:BasePriceFullyPersonalised>");
            xmlBuilder.append("<m4u:BasePriceSemiPersonalised>").append(viableFrame.semiPersonalisedBasePrice).append("</m4u:BasePriceSemiPersonalised>");
            xmlBuilder.append("<m4u:BasePriceStandard>").append(viableFrame.standardBasePrice).append("</m4u:BasePriceStandard>");
            xmlBuilder.append("<m4u:BoxingHorizontal>").append(viableFrame.dimensions.boxingHorizontalStandard).append("</m4u:BoxingHorizontal>");
            xmlBuilder.append("<m4u:BoxingVertical>").append(viableFrame.dimensions.boxingVerticalStandard).append("</m4u:BoxingVertical>");
            xmlBuilder.append("<m4u:PantoscopicAngleLeft>").append(viableFrame.dimensions.pantoscopicAngleLeftStandard).append("</m4u:PantoscopicAngleLeft>");
            xmlBuilder.append("<m4u:PantoscopicAngleRight>").append(viableFrame.dimensions.pantoscopicAngleRightStandard).append("</m4u:PantoscopicAngleRight>");
            xmlBuilder.append("<m4u:BridgeWidth>").append(viableFrame.dimensions.bridgeWidthStandard).append("</m4u:BridgeWidth>");
            xmlBuilder.append("<m4u:BridgeHeight>").append(viableFrame.dimensions.bridgeHeightStandard).append("</m4u:BridgeHeight>");
            xmlBuilder.append("<m4u:TotalLength>").append(viableFrame.dimensions.totalLengthStandard).append("</m4u:TotalLength>");
            xmlBuilder.append("<m4u:LeftTempleLength>").append(viableFrame.leftTempleDims.lengthStandard).append("</m4u:LeftTempleLength>");
            xmlBuilder.append("<m4u:LeftTempleOpeningAngle>").append(viableFrame.leftTempleDims.openingAngleStandard).append("</m4u:LeftTempleOpeningAngle>");
            xmlBuilder.append("<m4u:RightTempleLength>").append(viableFrame.rightTempleDims.lengthStandard).append("</m4u:RightTempleLength>");
            xmlBuilder.append("<m4u:RightTempleOpeningAngle>").append(viableFrame.rightTempleDims.openingAngleStandard).append("</m4u:RightTempleOpeningAngle>");

//            xmlBuilder.append("<m4u:Standard>").append(viableFrame.standard).append("</m4u:Standard>");
            xmlBuilder.append("</m4u:General>");

            // Aesthetic configurations
            xmlBuilder.append("<m4u:AestheticConfiguration>");
            // Config 1
            xmlBuilder.append(workoutAestheticConfigXml(viableFrame.config1, "configuration1"));
            // Config 2
            xmlBuilder.append(workoutAestheticConfigXml(viableFrame.config2, "configuration2"));
            // Config 3
            xmlBuilder.append(workoutAestheticConfigXml(viableFrame.config3, "configuration3"));
            xmlBuilder.append("</m4u:AestheticConfiguration>");
            
            // Frontal information
            xmlBuilder.append("<m4u:FrontalInformation>");
            xmlBuilder.append("<m4u:FinishingAspect>").append(viableFrame.finishingAspect).append("</m4u:FinishingAspect>");
            xmlBuilder.append("<m4u:MattePrice>").append(viableFrame.mattePrice).append("</m4u:MattePrice>");
            xmlBuilder.append("<m4u:BrilliantPrice>").append(viableFrame.brilliantPrice).append("</m4u:BrilliantPrice>");
            xmlBuilder.append("<m4u:BoxingHorizontal>").append(viableFrame.dimensions.boxingHorizontalStandard).append("</m4u:BoxingHorizontal>");
            xmlBuilder.append("<m4u:BoxingVertical>").append(viableFrame.dimensions.boxingVerticalStandard).append("</m4u:BoxingVertical>");
            xmlBuilder.append("<m4u:PantoscopicAngleLeft>").append(viableFrame.dimensions.pantoscopicAngleLeftStandard).append("</m4u:PantoscopicAngleLeft>");
            xmlBuilder.append("<m4u:PantoscopicAngleRight>").append(viableFrame.dimensions.pantoscopicAngleRightStandard).append("</m4u:PantoscopicAngleRight>");
            xmlBuilder.append("<m4u:BridgeWidth>").append(viableFrame.dimensions.bridgeWidthStandard).append("</m4u:BridgeWidth>");
            xmlBuilder.append("<m4u:BridgeHeight>").append(viableFrame.dimensions.bridgeHeightStandard).append("</m4u:BridgeHeight>");
            xmlBuilder.append("<m4u:HeelWidth>").append(viableFrame.dimensions.heelWidthStandard).append("</m4u:HeelWidth>");
            xmlBuilder.append("<m4u:BaseOfFrame>").append(viableFrame.dimensions.baseOfFrameStandard).append("</m4u:BaseOfFrame>");
            xmlBuilder.append("<m4u:WrapAngle>").append(viableFrame.dimensions.wrapAngleStandard).append("</m4u:WrapAngle>");
            xmlBuilder.append("<m4u:TotalLength>").append(viableFrame.dimensions.totalLength.min1).append("</m4u:TotalLength>");
            xmlBuilder.append("<m4u:InternalRimReduction>").append(viableFrame.dimensions.internalRimReductionStandard).append("</m4u:InternalRimReduction>");            
            if ( viableFrame.frontalBaseColours ) {
                for(FrameBaseColour aColour: viableFrame.frontalBaseColours) {
                    xmlBuilder.append(workoutBaseColourXml(aColour));
                }
            }
            if ( viableFrame.frontalTexture ) {
                for(FrameTexture aTexture: viableFrame.frontalTexture) {
                    xmlBuilder.append("<m4u:Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                }
            }
            xmlBuilder.append("</m4u:FrontalInformation>");

            // Left temple information
            xmlBuilder.append("<m4u:LeftTempleInformation>");
            xmlBuilder.append("<m4u:FinishingAspect>").append(viableFrame.leftTempleFinishingAspect).append("</m4u:FinishingAspect>");
            xmlBuilder.append("<m4u:MattePrice>").append(viableFrame.leftTempleMattePrice).append("</m4u:MattePrice>");
            xmlBuilder.append("<m4u:BrilliantPrice>").append(viableFrame.leftTempleBrilliantPrice).append("</m4u:BrilliantPrice>");
            xmlBuilder.append("<m4u:Length>").append(viableFrame.leftTempleDims.lengthStandard).append("</m4u:Length>");
            xmlBuilder.append("<m4u:OpeningAngle>").append(viableFrame.leftTempleDims.openingAngleStandard).append("</m4u:OpeningAngle>");
            xmlBuilder.append("<m4u:MainCurvature>").append(viableFrame.leftTempleDims.mainCurvatureStandard).append("</m4u:MainCurvature>");
            xmlBuilder.append("<m4u:TempleTerminalAngle>").append(viableFrame.leftTempleDims.terminalAngleStandard).append("</m4u:TempleTerminalAngle>");

            if ( viableFrame.leftTempleBaseColours ) {
                for(FrameBaseColour aColour: viableFrame.leftTempleBaseColours) {
                    xmlBuilder.append(workoutBaseColourXml(aColour));
                }
            }
            if ( viableFrame.leftTempleTexture ) {
                for(FrameTexture aTexture: viableFrame.leftTempleTexture) {
                    xmlBuilder.append("<m4u:Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                }
            }
            xmlBuilder.append("</m4u:LeftTempleInformation>");
            
            // Right temple information
            xmlBuilder.append("<m4u:RightTempleInformation>");
            xmlBuilder.append("<m4u:FinishingAspect>").append(viableFrame.rightTempleFinishingAspect).append("</m4u:FinishingAspect>");
            xmlBuilder.append("<m4u:MattePrice>").append(viableFrame.rightTempleMattePrice).append("</m4u:MattePrice>");
            xmlBuilder.append("<m4u:BrilliantPrice>").append(viableFrame.rightTempleBrilliantPrice).append("</m4u:BrilliantPrice>");
            xmlBuilder.append("<m4u:Length>").append(viableFrame.rightTempleDims.lengthStandard).append("</m4u:Length>");
            xmlBuilder.append("<m4u:OpeningAngle>").append(viableFrame.rightTempleDims.openingAngleStandard).append("</m4u:OpeningAngle>");
            xmlBuilder.append("<m4u:MainCurvature>").append(viableFrame.rightTempleDims.mainCurvatureStandard).append("</m4u:MainCurvature>");
            xmlBuilder.append("<m4u:TempleTerminalAngle>").append(viableFrame.rightTempleDims.terminalAngleStandard).append("</m4u:TempleTerminalAngle>");

            if ( viableFrame.rightTempleBaseColours ) {
                for(FrameBaseColour aColour: viableFrame.rightTempleBaseColours) {
                    xmlBuilder.append(workoutBaseColourXml(aColour));
                }
            }
            if ( viableFrame.rightTempleTexture ) {
                for(FrameTexture aTexture: viableFrame.rightTempleTexture) {
                    xmlBuilder.append("<m4u:Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                }
            }
            xmlBuilder.append("</m4u:RightTempleInformation>");
            
            // Standard components
            xmlBuilder.append("<m4u:StandardComponents>");
            xmlBuilder.append("<m4u:Hinge>").append(viableFrame.hinge?.reference).append("</m4u:Hinge>");
            xmlBuilder.append("<m4u:Nylon>").append(viableFrame.nylon?.reference).append("</m4u:Nylon>");
            xmlBuilder.append("<m4u:Screw>").append(viableFrame.screw?.reference).append("</m4u:Screw>");
            xmlBuilder.append("</m4u:StandardComponents>");
            
            
            // Precooked files
            xmlBuilder.append("<m4u:PreCookedFiles>");
            for (Precooked aPrecooked: viableFrame.precookeds) {
                xmlBuilder.append("<m4u:Pre-Cooked Reference=\"").append(aPrecooked.reference).append("\" BridgeWidth=\"").append(aPrecooked.bridgeWidth).append("\" HeelWidth=\"").append(aPrecooked.heelWidth).append("\"/>")
            }
            xmlBuilder.append("</m4u:PreCookedFiles>");
            
            // Lenses
            xmlBuilder.append("<m4u:AvailableLenses>");

            if ( viableFrame.frameUse == Use.SOLAR ) {
                // For Solar frames we want to output colour, white and polarizing lenses
                xmlBuilder.append(workoutColourLensesXml(allWhiteLensMaterials, allPolarizingLensMaterials, allLensColours, allPolarizingLensColours, allARCoatings, allMirrorCoatings, allTopcoats, viableFrame.rimType));
            } else if ( viableFrame.frameUse == Use.OPHTHALMIC ) {
                // For Ophthalmic frames we only want to output white and colour lenses
                xmlBuilder.append(workoutColourLensesXml(allWhiteLensMaterials, allPolarizingLensMaterials, allLensColours, allPolarizingLensColours, allARCoatings, allMirrorCoatings, allTopcoats, viableFrame.rimType));
                xmlBuilder.append(workoutWhiteLensesXml(allWhiteLensMaterials, allARCoatings, allTopcoats, viableFrame.rimType));
                xmlBuilder.append(workoutPhotochromicLensesXml(allPhotochromicLensMaterials, allPhotochromicLensColours, allARCoatings, allTopcoats, viableFrame.rimType));
            } else if ( viableFrame.frameUse == Use.BOTH ) {
                // For frames that allow ophthalmic and solar we want to output white, colour and polarizing lenses
                xmlBuilder.append(workoutColourLensesXml(allWhiteLensMaterials, allPolarizingLensMaterials, allLensColours, allPolarizingLensColours, allARCoatings, allMirrorCoatings, allTopcoats, viableFrame.rimType));
                xmlBuilder.append(workoutWhiteLensesXml(allWhiteLensMaterials, allARCoatings, allTopcoats, viableFrame.rimType));
                xmlBuilder.append(workoutPhotochromicLensesXml(allPhotochromicLensMaterials, allPhotochromicLensColours, allARCoatings, allTopcoats, viableFrame.rimType));
            }
            
            xmlBuilder.append("</m4u:AvailableLenses>");
            
            xmlBuilder.append("</m4u:viableFrame>")
            
        }
        
        
        xmlBuilder.append("</m4u:AutomaticDesignResponse>");
        

        // Write the information to disk
        log.debug("About to write the XML out to disk");
        
        new File(returnedDesignsDir).mkdir();
        def outputFile = new File(returnedDesignsDir,dummyDesignResponseFileName);
        outputFile.write(xmlBuilder.toString());

        log.debug("Just finished writing the XML to disk at: " + returnedDesignsDir + "/" + dummyDesignResponseFileName);
        
    }

    
    def workoutAestheticConfigXml(config, id) {
        StringBuilder configBuilder = new StringBuilder();
        
        if ( config && config.frontalFinishingAspect ) {
            configBuilder.append("<m4u:Configuration id=\"").append(id).append("\">");
            configBuilder.append("<m4u:Image>").append(config.reference).append("</m4u:Image>");
            configBuilder.append("<m4u:LeftTemple>");
            configBuilder.append("<m4u:FinishingAspect>").append(config.leftTempleFinishingAspect).append("</m4u:FinishingAspect>");
            if ( config.leftTempleTexture )
                configBuilder.append("<m4u:Texture>").append(config.leftTempleTexture?.reference).append("</m4u:Texture>");
            else
                configBuilder.append("<m4u:Texture/>");
            if ( config.leftTempleBaseColour ) {
                configBuilder.append("<m4u:BaseColour>")
                configBuilder.append("<m4u:name>").append(config.leftTempleBaseColour?.name).append("</m4u:name>");
                configBuilder.append("<m4u:rCode>").append(config.leftTempleBaseColour?.red).append("</m4u:rCode>");
                configBuilder.append("<m4u:gCode>").append(config.leftTempleBaseColour?.green).append("</m4u:gCode>");
                configBuilder.append("<m4u:bCode>").append(config.leftTempleBaseColour?.blue).append("</m4u:bCode>");
                configBuilder.append("</m4u:BaseColour>")
            } else {
                configBuilder.append("<m4u:BaseColour/>");
            }
            configBuilder.append("</m4u:LeftTemple>");
            configBuilder.append("<m4u:RightTemple>");
            configBuilder.append("<m4u:FinishingAspect>").append(config.rightTempleFinishingAspect).append("</m4u:FinishingAspect>");
            if ( config.rightTempleTexture )
                configBuilder.append("<m4u:Texture>").append(config.rightTempleTexture?.reference).append("</m4u:Texture>");
            else
                configBuilder.append("<m4u:Texture/>")
            if ( config.rightTempleBaseColour ) {    
                configBuilder.append("<m4u:BaseColour>")
                configBuilder.append("<m4u:name>").append(config.rightTempleBaseColour?.name).append("</m4u:name>");
                configBuilder.append("<m4u:rCode>").append(config.rightTempleBaseColour?.red).append("</m4u:rCode>");
                configBuilder.append("<m4u:gCode>").append(config.rightTempleBaseColour?.green).append("</m4u:gCode>");
                configBuilder.append("<m4u:bCode>").append(config.rightTempleBaseColour?.blue).append("</m4u:bCode>");
                configBuilder.append("</m4u:BaseColour>")
            } else {
                configBuilder.append("<m4u:BaseColour/>")
            }
            configBuilder.append("</m4u:RightTemple>");
            configBuilder.append("<m4u:Frontal>");
            configBuilder.append("<m4u:FinishingAspect>").append(config.frontalFinishingAspect).append("</m4u:FinishingAspect>");
            if ( config.frontalTexture )
                configBuilder.append("<m4u:Texture>").append(config.frontalTexture?.reference).append("</m4u:Texture>");
            else
                configBuilder.append("<m4u:Texture/>");
            if ( config.frontalBaseColour ) {
                configBuilder.append("<m4u:BaseColour>")
                configBuilder.append("<m4u:name>").append(config.frontalBaseColour?.name).append("</m4u:name>");
                configBuilder.append("<m4u:rCode>").append(config.frontalBaseColour?.red).append("</m4u:rCode>");
                configBuilder.append("<m4u:gCode>").append(config.frontalBaseColour?.green).append("</m4u:gCode>");
                configBuilder.append("<m4u:bCode>").append(config.frontalBaseColour?.blue).append("</m4u:bCode>");
                configBuilder.append("</m4u:BaseColour>")
            } else {
                configBuilder.append("<m4u:BaseColour/>");
            }
            configBuilder.append("</m4u:Frontal>");
            if ( config.emotionalVariables ) {
                def emoVars = config.emotionalVariables;
                for(EmotionalVariable anEmoVar: emoVars) {
                    configBuilder.append("<m4u:EmotionalVariable>").append(anEmoVar.name.toUpperCase()).append("</m4u:EmotionalVariable>");
                }
            } 
            
            configBuilder.append("</m4u:Configuration>");
        }

        return configBuilder.toString();
    }

    def workoutBaseColourXml(FrameBaseColour colour) {
        StringBuilder colourBuilder = new StringBuilder();
        
        if ( colour ) {
            colourBuilder.append("<m4u:BaseColour>");
            colourBuilder.append("<m4u:name>").append(colour.name).append("</m4u:name>");
            colourBuilder.append("<m4u:rCode>").append(colour.red).append("</m4u:rCode>");
            colourBuilder.append("<m4u:gCode>").append(colour.green).append("</m4u:gCode>");
            colourBuilder.append("<m4u:bCode>").append(colour.blue).append("</m4u:bCode>");
            colourBuilder.append("<m4u:price>").append(colour.price).append("</m4u:price>");
            colourBuilder.append("</m4u:BaseColour>");
        }
        return colourBuilder.toString();
    }

    def workoutColourLensesXml(allWhiteLensMaterials, allPolarizingLensMaterials, allLensColours, allPolarizingLensColours, allARCoatings, allMirrorCoatings, allTopcoats, edgeThicknessType) {
        
        StringBuilder colourLensBuilder = new StringBuilder();
        
        colourLensBuilder.append("<m4u:ColourLens>");
        
        // Materials
        colourLensBuilder.append("<m4u:ViableMaterials>");
        colourLensBuilder.append(workoutViableMaterialsXml(allWhiteLensMaterials, edgeThicknessType));
        colourLensBuilder.append("</m4u:ViableMaterials>");
        colourLensBuilder.append("<m4u:ViablePolarizingMaterials>");
        colourLensBuilder.append(workoutViableMaterialsXml(allPolarizingLensMaterials, edgeThicknessType));
        colourLensBuilder.append("</m4u:ViablePolarizingMaterials>");
        
        // Colours
        colourLensBuilder.append(workoutLensColourXml(allLensColours, allMirrorCoatings));
        colourLensBuilder.append(workoutPolarizingSpecialLensColourXml(allPolarizingLensColours));
        
        // AR
        colourLensBuilder.append(workoutArCoatingXml(allARCoatings));
        
        // Topcoats
        colourLensBuilder.append(workoutTopcoatXml(allTopcoats));
        
        // Mirror coatings
        colourLensBuilder.append(workoutMirrorCoatingXml(allLensColours));
        
        colourLensBuilder.append("</m4u:ColourLens>");
        
        return colourLensBuilder.toString();
    }
    
    def workoutWhiteLensesXml(allWhiteLensMaterials, allARCoatings, allTopcoats, edgeThicknessType) {
        
        StringBuilder whiteLensBuilder = new StringBuilder();
        whiteLensBuilder.append("<m4u:WhiteLens>");

        // Materials
        whiteLensBuilder.append("<m4u:ViableMaterials>");
        whiteLensBuilder.append(workoutViableMaterialsXml(allWhiteLensMaterials, edgeThicknessType));
        whiteLensBuilder.append("</m4u:ViableMaterials>");
        
        // AR coatings
        whiteLensBuilder.append(workoutArCoatingXml(allARCoatings));
        
        // Topcoats
        whiteLensBuilder.append(workoutTopcoatXml(allTopcoats));
        
        whiteLensBuilder.append("</m4u:WhiteLens>");
        
        return whiteLensBuilder.toString();
    }

    def workoutPhotochromicLensesXml(allPhotochromicLensMaterials, allPhotochromicLensColours, allARCoatings, allTopcoats, edgeThicknessType) {
        
        StringBuilder photochromicLensBuilder = new StringBuilder();
        photochromicLensBuilder.append("<m4u:PhotoChromicLens>");
        
        // Materials
        photochromicLensBuilder.append("<m4u:ViableMaterials>");
        photochromicLensBuilder.append(workoutViableMaterialsXml(allPhotochromicLensMaterials, edgeThicknessType));
        photochromicLensBuilder.append("</m4u:ViableMaterials>");
        
        // Colours
        photochromicLensBuilder.append(workoutPhotochromicSpecialLensColourXml(allPhotochromicLensColours));
        
        // AR colour
        photochromicLensBuilder.append(workoutArCoatingXml(allARCoatings));
        
        // Topcoats
        photochromicLensBuilder.append(workoutTopcoatXml(allTopcoats));
        
        photochromicLensBuilder.append("</m4u:PhotoChromicLens>");
        
        return photochromicLensBuilder.toString();
    }
    
    def workoutViableMaterialsXml(allMaterials, edgeThicknessType) {
        
        StringBuilder viableMaterialsBuilder = new StringBuilder();
        
        for(LensMaterial aMaterial: allMaterials) {
            
            // Work out which edge thickness we want from the material
            def edgeThickness;
            if ( edgeThicknessType == RimType.RIMLESS )
                edgeThickness = aMaterial.minimumEdgeThicknessRimless;
            else if ( edgeThicknessType == RimType.SEMIRIMLESS )
                edgeThickness = aMaterial.minimumEdgeThicknessSemi;
            else
                edgeThickness = aMaterial.minimumEdgeThicknessFull;
                        
            viableMaterialsBuilder.append("<m4u:ViableMaterial>");
            viableMaterialsBuilder.append("<m4u:name>").append(aMaterial.reference).append("</m4u:name>");
            viableMaterialsBuilder.append("<m4u:IdMaterial>").append(aMaterial.identifier).append("</m4u:IdMaterial>");
            viableMaterialsBuilder.append("<m4u:EyeMadePrice>").append(aMaterial.priceEyeMade).append("</m4u:EyeMadePrice>");
            viableMaterialsBuilder.append("<m4u:LifeMadeExpertPrice>").append(aMaterial.priceLifeMadeExpert).append("</m4u:LifeMadeExpertPrice>");
            viableMaterialsBuilder.append("<m4u:LifeMadeIniciaPrice>").append(aMaterial.priceLifeMadeInicia).append("</m4u:LifeMadeIniciaPrice>");
            viableMaterialsBuilder.append("<m4u:SingleVisionPrice>").append(aMaterial.priceSingleVision).append("</m4u:SingleVisionPrice>");
            viableMaterialsBuilder.append("<m4u:MinimumDrillingThickness>").append(aMaterial.minimumDrillingThickness).append("</m4u:MinimumDrillingThickness>");
            viableMaterialsBuilder.append("<m4u:MinimumCentreThickness>").append(aMaterial.centreDrillingThickness).append("</m4u:MinimumCentreThickness>");
            viableMaterialsBuilder.append("<m4u:MinimumEdgeThickness>").append(edgeThickness).append("</m4u:MinimumEdgeThickness>");
            viableMaterialsBuilder.append("<m4u:ThicknessSimulation>");
            viableMaterialsBuilder.append("<m4u:REThickness Minimum=\"250\" Central=\"250\" Maximum=\"250\"/>");
            viableMaterialsBuilder.append("<m4u:LEThickness Minimum=\"250\" Central=\"250\" Maximum=\"250\"/>");
            viableMaterialsBuilder.append("</m4u:ThicknessSimulation>");
            viableMaterialsBuilder.append("<m4u:ND>").append(aMaterial.refractionIndex).append("</m4u:ND>");
            viableMaterialsBuilder.append("</m4u:ViableMaterial>");
            
        }
        
        return viableMaterialsBuilder.toString();
    }
    
    def workoutArCoatingXml(allARCoatings) {

        StringBuilder coatingBuilder = new StringBuilder();
        
        for(Coating anAr: allARCoatings ) {
            
            coatingBuilder.append("<m4u:ARColor>");
            coatingBuilder.append("<m4u:name>").append(anAr.reference).append("</m4u:name>");
            coatingBuilder.append("<m4u:rCode>").append(anAr.red).append("</m4u:rCode>");
            coatingBuilder.append("<m4u:gCode>").append(anAr.green).append("</m4u:gCode>");
            coatingBuilder.append("<m4u:bCode>").append(anAr.blue).append("</m4u:bCode>");
            coatingBuilder.append("<m4u:transparencyIndex>").append(anAr.transparencyIndex).append("</m4u:transparencyIndex>");
            coatingBuilder.append("<m4u:Price>").append(anAr.price).append("</m4u:Price>");            
            coatingBuilder.append("</m4u:ARColor>");
        }
        
        return coatingBuilder.toString();

    }
    
    def workoutTopcoatXml(allTopcoats) {

        StringBuilder topcoatBuilder = new StringBuilder();
        
        for(LensTopcoat aTopcoat: allTopcoats ) {
            
            topcoatBuilder.append("<m4u:Topcoat>");
            topcoatBuilder.append("<m4u:name>").append(aTopcoat.name).append("</m4u:name>");
            topcoatBuilder.append("<m4u:Price>").append(aTopcoat.price).append("</m4u:Price>");
            topcoatBuilder.append("</m4u:Topcoat>");
        }
        
        return topcoatBuilder.toString();

    }
    
    def workoutMirrorCoatingXml(allColours) {
        
        StringBuilder mirrorBuilder = new StringBuilder();
        
        for(LensColour aColour: allColours) {
            
            if ( aColour.acceptMirror ) {
                // This is a colour which can have mirror on top of it - output it
            
                mirrorBuilder.append("<m4u:MirrorBaseColor>");
                mirrorBuilder.append("<m4u:name>").append(aColour.name).append("</m4u:name>");
                mirrorBuilder.append("<m4u:rCode>").append(aColour.rCode).append("</m4u:rCode>");
                mirrorBuilder.append("<m4u:gCode>").append(aColour.gCode).append("</m4u:gCode>");
                mirrorBuilder.append("<m4u:bCode>").append(aColour.bCode).append("</m4u:bCode>");
                mirrorBuilder.append("<m4u:transparencyIndex>").append(aColour.transparencyIndex).append("</m4u:transparencyIndex>");
                mirrorBuilder.append("<m4u:Price>").append(aColour.price).append("</m4u:Price>");            
                mirrorBuilder.append("</m4u:MirrorBaseColor>");
                
            }
        }
        
        return mirrorBuilder.toString();
    }
    
    def workoutLensColourXml(allLensColours, allMirrorCoatings) {
        StringBuilder colourBuilder = new StringBuilder();

        // Output all basic and fashion colours
        for(LensColour aColour: allLensColours) {
            
            if ( aColour.colourType == LensColourType.BASIC )
                colourBuilder.append("<m4u:colour subType=\"BASIC\">");
            else if ( aColour.colourType == LensColourType.FASHION )
                colourBuilder.append("<m4u:colour subType=\"FASHION\">");
            else 
                colourBuilder.append("<m4u:colour subType=\"").append(aColour.colourType).append("\">");
            colourBuilder.append("<m4u:primaryColor>");
            colourBuilder.append("<m4u:name>").append(aColour.name).append("</m4u:name>");
            colourBuilder.append("<m4u:rCode>").append(aColour.rCode).append("</m4u:rCode>");
            colourBuilder.append("<m4u:gCode>").append(aColour.gCode).append("</m4u:gCode>");
            colourBuilder.append("<m4u:bCode>").append(aColour.bCode).append("</m4u:bCode>");
            colourBuilder.append("<m4u:TransparencyIndex>").append(aColour.transparencyIndex).append("</m4u:TransparencyIndex>");
            colourBuilder.append("</m4u:primaryColor>");

            // Add in the gradient information if there is any
            if ( aColour.rCodeGradient == null 
                || aColour.gCodeGradient == null
                || aColour.bCodeGradient == null 
                || (aColour.rCodeGradient == 0 && aColour.gCodeGradient == 0 && aColour.bCodeGradient == 0 ) ) {
                // No gradient information to add..
            } else {
                colourBuilder.append("<m4u:gradientColor>");
                colourBuilder.append("<m4u:rCode>").append(aColour.rCodeGradient).append("</m4u:rCode>");
                colourBuilder.append("<m4u:gCode>").append(aColour.gCodeGradient).append("</m4u:gCode>");
                colourBuilder.append("<m4u:bCode>").append(aColour.bCodeGradient).append("</m4u:bCode>");
                colourBuilder.append("<m4u:TransparencyIndex>").append(aColour.transparencyIndexGradient).append("</m4u:TransparencyIndex>");
                colourBuilder.append("</m4u:gradientColor>")
            }

            colourBuilder.append("<m4u:Price>").append(aColour.price).append("</m4u:Price>");
            colourBuilder.append("</m4u:colour>");
            
        }
        
        // Now output all mirror coatings
        for(Coating aCoating: allMirrorCoatings) {
            
                colourBuilder.append("<m4u:colour subType=\"MIRROR\">");
                colourBuilder.append("<m4u:primaryColor>");
                colourBuilder.append("<m4u:name>").append(aCoating.reference).append("</m4u:name>");
                colourBuilder.append("<m4u:rCode>").append(aCoating.red).append("</m4u:rCode>");
                colourBuilder.append("<m4u:gCode>").append(aCoating.green).append("</m4u:gCode>");
                colourBuilder.append("<m4u:bCode>").append(aCoating.blue).append("</m4u:bCode>");
                colourBuilder.append("<m4u:TransparencyIndex>").append(aCoating.transparencyIndex).append("</m4u:TransparencyIndex>");
                colourBuilder.append("</m4u:primaryColor>");
                colourBuilder.append("<m4u:Price>").append(aCoating.price).append("</m4u:Price>");
                colourBuilder.append("</m4u:colour>");
            
        }
        
        return colourBuilder.toString();
    }
    
    def workoutPolarizingSpecialLensColourXml(allLensColours) {
        
        StringBuilder colourBuilder = new StringBuilder();
        
        for(LensSpecialColour aColour: allLensColours) {

            colourBuilder.append("<m4u:colour subType=\"POLARIZING\">");
            colourBuilder.append("<m4u:primaryColor>");
            colourBuilder.append("<m4u:name>").append(aColour.name).append("</m4u:name>");
            colourBuilder.append("<m4u:rCode>").append(aColour.rCode).append("</m4u:rCode>");
            colourBuilder.append("<m4u:gCode>").append(aColour.gCode).append("</m4u:gCode>");
            colourBuilder.append("<m4u:bCode>").append(aColour.bCode).append("</m4u:bCode>");
            colourBuilder.append("<m4u:TransparencyIndex>").append(aColour.transparencyIndex).append("</m4u:TransparencyIndex>");
            colourBuilder.append("</m4u:primaryColor>");
            colourBuilder.append("<m4u:Price>").append(aColour.price).append("</m4u:Price>");
            colourBuilder.append("</m4u:colour>");
        }
        
        return colourBuilder.toString();
    }


    def workoutPhotochromicSpecialLensColourXml(allLensColours) {
        
        StringBuilder colourBuilder = new StringBuilder();
        
        for(LensSpecialColour aColour: allLensColours) {

            colourBuilder.append("<m4u:PhotochromicColour>");
            colourBuilder.append("<m4u:name>").append(aColour.name).append("</m4u:name>");
            colourBuilder.append("<m4u:rCode>").append(aColour.rCode).append("</m4u:rCode>");
            colourBuilder.append("<m4u:gCode>").append(aColour.gCode).append("</m4u:gCode>");
            colourBuilder.append("<m4u:bCode>").append(aColour.bCode).append("</m4u:bCode>");
            colourBuilder.append("<m4u:TransparencyIndex>").append(aColour.transparencyIndex).append("</m4u:TransparencyIndex>");
            colourBuilder.append("<m4u:Price>").append(aColour.price).append("</m4u:Price>");
            colourBuilder.append("</m4u:PhotochromicColour>");
        }
        
        return colourBuilder.toString();
    }
}
