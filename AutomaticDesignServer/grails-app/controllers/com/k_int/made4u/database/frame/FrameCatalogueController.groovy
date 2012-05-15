package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.Frame
import com.k_int.made4u.frame.Gender
import com.k_int.made4u.frame.RimType
import com.k_int.made4u.frame.EndpieceHeight
import com.k_int.made4u.frame.Use
import com.k_int.made4u.frame.Material
import com.k_int.made4u.frame.FrameShape
import com.k_int.made4u.frame.FinishingAspect
import com.k_int.made4u.frame.StandardComponent;
import com.k_int.made4u.frame.StandardType;
import com.k_int.made4u.frame.Precooked;
import com.k_int.made4u.frame.FrameBaseColour;
import com.k_int.made4u.frame.FrameDimensions;
import com.k_int.made4u.frame.FrameTempleDimensions;
import com.k_int.made4u.frame.FrameImage;
import com.k_int.made4u.frame.FrameImageType;
import com.k_int.made4u.frame.FrameTexture;
import com.k_int.made4u.frame.FrameFrontalTotalDimensions;
import com.k_int.made4u.frame.FrameAestheticConfiguration;

import org.springframework.dao.DataIntegrityViolationException;

class FrameCatalogueController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.sort = params.sort ? params.sort : 'displayOrder';
        params.order = params.order ? params.order : 'asc';
        
        log.debug("About to return with the list of frames.. count: " + Frame.count());

        [frameList: Frame.list(params), frameTotal: Frame.count()]

    }

    def show = {
        def frameInstance = Frame.get(params.id)
        if (!frameInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
            redirect(action: "list")
        }
        else {
            [frameInstance: frameInstance]
        }
    }

    def create = {
        return [frameInstance: new Frame(), genders: Gender.values(), rimTypes: RimType.values(), endpieceHeights: EndpieceHeight.values(), frameUses: Use.values(), materials: Material.list(), rimShapes: FrameShape.list()]
    }

    def save = {
        def frameInstance = new Frame();
        frameInstance.properties = params;
        
        def material = Material.get(params.materialId);
        def rimShape = FrameShape.get(params.rimShapeId);
        
        if ( !material ) {
            frameInstance.errors.rejectValue("material", message(code: 'frame.material.not.found.message', default: 'The selected frame material could not be found'));
        } else {
            frameInstance.material = material;
        }
        
        if ( !rimShape ) 
            frameInstance.errors.rejectValue("rimShape", message(code: "frame.rim.shape.not.found.message", default: 'The selected rim shape could not be found'));
        else
           frameInstance.rimShape = rimShape; 
           
        // Fill out the various linked objects
        if ( !frameInstance.hasErrors() ) {
            frameInstance.dimensions = (new FrameDimensions(totalLength: new FrameFrontalTotalDimensions().save())).save();
            frameInstance.leftTempleDims = (new FrameTempleDimensions()).save();
            frameInstance.rightTempleDims = (new FrameTempleDimensions()).save();
        }
           
        if ( !frameInstance.hasErrors() && frameInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'frame.label', default: 'Frame'), frameInstance.identifier])}"
                redirect(action: "show", id: frameInstance.id)
        } else {
            render(view: "create", model: [frameInstance: frameInstance, genders: Gender.values(), rimTypes: RimType.values(), endpieceHeights: EndpieceHeight.values(), frameUses: Use.values(), materials: Material.list(), rimShapes: FrameShape.list()])
        }
    }

    def edit = {
        def frameInstance = Frame.get(params.id)
        if (!frameInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
            redirect(action: "list")
        }
        else {
            def hinges = StandardComponent.findByStandardType(StandardType.HINGE);
            def nylons = StandardComponent.findByStandardType(StandardType.NYLON);
            def screws = StandardComponent.findByStandardType(StandardType.SCREW);
            
            def availableFrontalBaseColours = FrameBaseColour.list();
            def availableFrontalImages = FrameImage.findAllByImageType(FrameImageType.FRONTAL);
            def availableLeftTempleBaseColours = FrameBaseColour.list();
            def availableLeftTempleImages = FrameImage.findAllByImageType(FrameImageType.TEMPLE);
            def availableRightTempleBaseColours = FrameBaseColour.list();
            def availableRightTempleImages = FrameImage.findAllByImageType(FrameImageType.TEMPLE);
            
            // Workout what colours could be added to the different parts of the frame
            for ( aColour in frameInstance.frontalBaseColours ) 
                availableFrontalBaseColours.remove(aColour);
            for ( aColour in frameInstance.leftTempleBaseColours ) 
                availableLeftTempleBaseColours.remove(aColour);
            for ( aColour in frameInstance.rightTempleBaseColours ) 
                availableRightTempleBaseColours.remove(aColour);
            
            // Workout what images could be added to the different parts as a texture
            for ( aTexture in frameInstance.frontalTexture ) 
                availableFrontalImages.remove(aTexture.image);
            for ( aTexture in frameInstance.leftTempleTexture ) 
                availableLeftTempleImages.remove(aTexture.image);
            for ( aTexture in frameInstance.rightTempleTexture ) 
                availableRightTempleImages.remove(aTexture.image);
             
            [frameInstance: frameInstance, genders: Gender.values(), rimTypes: RimType.values(), endpieceHeights: EndpieceHeight.values(), frameUses: Use.values(), 
                materials: Material.list(), rimShapes: FrameShape.list(), finishingAspects: FinishingAspect.values(), hinges: hinges,
                availableFrontalBaseColours: availableFrontalBaseColours, availableFrontalTextureImages: availableFrontalImages,
                availableLeftTempleBaseColours: availableLeftTempleBaseColours, availableLeftTempleTextureImages: availableLeftTempleImages,
                availableRightTempleBaseColours: availableRightTempleBaseColours, availableRightTempleTextureImages: availableRightTempleImages,
                nylons: nylons, screws: screws]
        }
    }

    def update = {
        def frameInstance = Frame.get(params.id)
        if ( frameInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( frameInstance.version > version ) {
                    frameInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'frame.label', default: 'Frame')] as Object[], message(code: 'frame.update.conflict', default: 'Another user has updated this frame while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            
            frameInstance.properties = params
            
            
            // Now fill in the links to other domain objects..
            def newMaterial = Material.get(params.materialId);
            def newRimShape = FrameShape.get(params.rimShapeId);
            def hinge = StandardComponent.get(params.hingeId);
            def nylon = StandardComponent.get(params.nylonId);
            def screw = StandardComponent.get(params.screwId);

            if ( !newMaterial ) {                
                frameInstance.errors.rejectValue("material", message(code: 'frame.material.not.found.message', default: 'The selected frame material could not be found'));
            } else {
                frameInstance.material = newMaterial;
            }
            
            if ( !newRimShape ) {
                frameInstance.errors.rejectValue("rimShape", message(code: "frame.rim.shape.not.found.message", default: 'The selected rim shape could not be found'));
            } else {
                frameInstance.rimShape = newRimShape;
            }
            
            if ( !hinge )
                frameInstance.errors.rejectValue("hinge", message(code: "frame.hinge.not.found.message", default: 'The select hinge could not be found'));
            else 
                frameInstance.hinge = hinge;

            if ( !nylon )
                frameInstance.errors.rejectValue("nylon", message(code: "frame.nylon.not.found.message", default: 'The select nylon could not be found'));
            else 
                frameInstance.nylon = nylon;

            if ( !screw )
                frameInstance.errors.rejectValue("screw", message(code: "frame.screw.not.found.message", default: 'The select screw could not be found'));
            else 
                frameInstance.screw = screw;

            
            // Handle the changes (if any) to precooked information
            Set<Precooked> precookedList = frameInstance.precookeds;
            Set<Precooked> toRemove = new HashSet<Precooked>();
            
            // Loop through all of the params and find any precooked files being uploaded
            Set<String> paramKeyset = params.keySet();
            List<Integer> precookedFileIds = new ArrayList<Integer>();
            for(String key: paramKeyset) {
                if ( key.startsWith("popupPrecookedFile") ) {
                    int dashIdx = key.indexOf("-");
                    String rowNumStr = key.substring(dashIdx+1);
                    Integer rowNum = new Integer(rowNumStr);
                    precookedFileIds.add(rowNum);
                    
                }
            }
            
            Collections.sort(precookedFileIds);
                        
            
            if ( params.precookedDeleteVals ) {
                // Some deletes have been specified - perform the required removals
                String delVals = params.precookedDeleteVals;
                String[] separatedDelIds = delVals.split("_BREAK_");
                
                for(String delId: separatedDelIds) {
                    
                    Long delIdNum = new Long(delId);
                    Precooked toDel = Precooked.get(delIdNum);
                    
                    if ( toDel ) {
                        precookedList.remove(toDel);
                        toRemove.add(toDel);
                    }
                }
            }
            
            if ( params.precookedReferenceVals ) {
                // Some new precooked vals have been specified - add them in if OK to do so
                String refVals = params.precookedReferenceVals;
                String bridgeWidthVals = params.precookedBridgeWidthVals;
                String heelWidthVals = params.precookedHeelWidthVals;
                
                String[] separateRefVals = refVals.split("_BREAK_");
                String[] separateHeelWidthVals = heelWidthVals.split("_BREAK_");
                String[] separateBridgeWidthVals = bridgeWidthVals.split("_BREAK_");
                
                if ( separateRefVals.length != separateBridgeWidthVals.length || separateRefVals.length != separateHeelWidthVals.length || separateRefVals.length != precookedFileIds.size()) {
                    // Non-matching numbers of fields..
                    flash.error = "${message(code: 'frame.precooked.non.matching.numbers.message', default: 'A reference, bridge width, heel width and file must be specified for all precooked values')}"
                    redirect(action: "show", id: params.id);
                } else {
                    // Matching numbers - create the new ones..
                    def uploadDir = grailsApplication.config.com.k_int.made4u.frame.precooked.uploadDirectory;
                    def publicUploadDir = grailsApplication.config.com.k_int.made4u.frame.precooked.publicUploadDirectory;

                    if ( !uploadDir.endsWith(File.separator) ) 
                        uploadDir = uploadDir + File.separator;
                    if ( !publicUploadDir.endsWith("/") ) 
                        publicUploadDir = publicUploadDir + "/";
                    
                    Iterator<Integer> precookedFileIdIter = precookedFileIds.iterator();
                    
                    for(int ctr = 0; ctr < separateRefVals.length; ctr++) {
                        String thisRef = separateRefVals[ctr];
                        Float thisHeelWidth = new Float(separateHeelWidthVals[ctr]);
                        Float thisBridgeWidth = new Float(separateBridgeWidthVals[ctr]);
                        Integer thisFileKey = precookedFileIdIter.next();
                        
                        Precooked newPrecooked = new Precooked();
                        newPrecooked.setReference(thisRef);
                        newPrecooked.setHeelWidth(thisHeelWidth);
                        newPrecooked.setBridgeWidth(thisBridgeWidth);
                        newPrecooked.setPath("temp-" + System.currentTimeMillis());
                        
                        newPrecooked.save(flush: true);
                        
                        // We now have an ID for the new precooked entry which means we can create the file in the 
                        // appropriate place on the filesystem
                        def thisPrecookedDir = uploadDir + newPrecooked.id;
                        def thisPublicPrecookedDir = publicUploadDir + newPrecooked.id;
                        new File(thisPrecookedDir).mkdirs();
                        
                        String requestFileId = "popupPrecookedFile-" + thisFileKey;
                        def precookedFile = request.getFile(requestFileId);
                        precookedFile.transferTo( new File ( thisPrecookedDir, precookedFile.originalFilename))
                        def path = thisPublicPrecookedDir + "/" + precookedFile.originalFilename;
                        newPrecooked.path = path;
                        
                        newPrecooked.save(flush:true);
                        
                        precookedList.add(newPrecooked);
                    }
                    
                }
            }
            
            // Now save the precooked list back again
            frameInstance.precookeds = precookedList;
            
            frameInstance.save(flush:true);
            for(Precooked toDel: toRemove) {
                try {
                    toDel.delete(flush: true);
                } catch (DataIntegrityViolationException dive) {
                    log.error dive.message;
                    dive.printStackTrace();

                    flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'frame.precooked.label', default: 'Precooked'), params.id])}"
                    redirect(action: "show", id: params.id);
                }
            }
            
            
            // If we have some information about adding / deleting base colours then manipulate that information..
            
            handleBaseColours(frameInstance, params.frontalNewBaseColourIds, params.frontalRemoveBaseColourIds, "frontal");
            handleBaseColours(frameInstance, params.leftTempleNewBaseColourIds, params.leftTempleRemoveBaseColourIds, "leftTemple");
            handleBaseColours(frameInstance, params.rightTempleNewBaseColourIds, params.rightTempleRemoveBaseColourIds, "rightTemple");
            
            // If we have some information about adding / deleting textures then manipulate that information
            handleTextures(frameInstance, "frontal", frameInstance.frontalTexture, params.frontalNewTextureImageIds, params.frontalNewTextureReferences,
                           params.frontalNewTextureXVals, params.frontalNewTextureYVals, params.frontalNewTextureScaleFactors,
                           params.frontalNewTexturePrices, params.frontalNewTextureProductionTimes, params.frontalTextureDeleteVals);
            handleTextures(frameInstance, "leftTemple", frameInstance.leftTempleTexture, params.leftTempleNewTextureImageIds, params.leftTempleNewTextureReferences,
                           params.leftTempleNewTextureXVals, params.leftTempleNewTextureYVals, params.leftTempleNewTextureScaleFactors,
                           params.leftTempleNewTexturePrices, params.leftTempleNewTextureProductionTimes, params.leftTempleTextureDeleteVals);
            handleTextures(frameInstance, "rightTemple", frameInstance.rightTempleTexture, params.rightTempleNewTextureImageIds, params.rightTempleNewTextureReferences,
                           params.rightTempleNewTextureXVals, params.rightTempleNewTextureYVals, params.rightTempleNewTextureScaleFactors,
                           params.rightTempleNewTexturePrices, params.rightTempleNewTextureProductionTimes, params.rightTempleTextureDeleteVals);

            
            
            if ( !frameInstance.hasErrors() && frameInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'frame.label', default: 'Frame'), frameInstance.identifier])}"
                redirect(action: "show", id: frameInstance.id)
            } else {
                def hinges = StandardComponent.findByStandardType(StandardType.HINGE);
                def nylons = StandardComponent.findByStandardType(StandardType.NYLON);
                def screws = StandardComponent.findByStandardType(StandardType.SCREW);
                render(view: "edit", model: [id: params.id, frameInstance: frameInstance, genders: Gender.values(), rimTypes: RimType.values(), endpieceHeights: EndpieceHeight.values(), frameUses: Use.values(), materials: Material.list(), rimShapes: FrameShape.list(), finishingAspects: FinishingAspect.values(), hinges: hinges, nylons: nylons, screws: screws])
            }
            
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {

        def frameInstance = Frame.get(params.id);
        if ( frameInstance ) {
            // We have something to delete - do so
            try {
                frameInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'frame.label', default: 'Frame'), frameInstance.identifier])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();

                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
            redirect(action: "list")
        }
    }

    
    def handleBaseColours(Frame frameInstance, String newBaseColourIds, String removeBaseColourIds, String component) {
        
        log.debug("handleBaseColours called with newBaseColourIds = " + newBaseColourIds);
        
        def baseColours = new HashSet<FrameBaseColour>();
        def config1Colour;
        def config2Colour;
        def config3Colour;
        
        if ( "frontal".equals(component) ) {
            baseColours = frameInstance?.frontalBaseColours;
            config1Colour = frameInstance.config1?.frontalBaseColour;
            config2Colour = frameInstance.config2?.frontalBaseColour;
            config3Colour = frameInstance.config3?.frontalBaseColour;
        } else if ( "leftTemple".equals(component) ) {
            baseColours = frameInstance?.leftTempleBaseColours;
            config1Colour = frameInstance.config1?.leftTempleBaseColour;
            config2Colour = frameInstance.config2?.leftTempleBaseColour;
            config3Colour = frameInstance.config3?.leftTempleBaseColour;
        } else if ( "rightTemple".equals(component) ) {
            baseColours = frameInstance?.rightTempleBaseColours;
            config1Colour = frameInstance.config1?.rightTempleBaseColour;
            config2Colour = frameInstance.config2?.rightTempleBaseColour;
            config3Colour = frameInstance.config3?.rightTempleBaseColour;
        }
            
        if ( newBaseColourIds ) {
            // We have some new IDs to add..
            String[] allNewBaseColourIds = newBaseColourIds.split("_BREAK_");
            for(String thisId: allNewBaseColourIds) {
                Long thisIdNum = new Long(thisId);
                FrameBaseColour toAdd = FrameBaseColour.get(thisIdNum);

                if ( !baseColours.contains(toAdd) )
                    baseColours.add(toAdd);
            }
        }

        if ( removeBaseColourIds ) {
            // We have some IDs to remove..
            log.debug("removeBaseColourIds = " + removeBaseColourIds);
            String[] allRemoveBaseColourIds = removeBaseColourIds.split("_BREAK_");
            for(String thisId: allRemoveBaseColourIds) {
                Long thisIdNum = new Long(thisId);
                FrameBaseColour colourToRemove = FrameBaseColour.get(thisIdNum);
                log.debug("thisId = " + thisId);
                if ( baseColours.contains(colourToRemove) ) {
                    log.debug("about to remove thisIdNum: " + thisIdNum);
                    baseColours.remove(colourToRemove);
                    if ( colourToRemove.equals(config1Colour) )
                        config1Colour = null;
                    if ( colourToRemove.equals(config2Colour) )
                        config2Colour = null;
                    if ( colourToRemove.equals(config3Colour) )
                        config3Colour = null;
                }
            }
        }

        if ( "frontal".equals(component) ) {
            frameInstance.frontalBaseColours = baseColours;
            if ( !frameInstance.config1 )
                frameInstance.config1 = new FrameAestheticConfiguration().save();

            if ( config1Colour == null )
                frameInstance.config1.frontalBaseColour = null;
            else if ( config2Colour == null )
                frameInstance.config2.frontalBaseColour = null;
            else if ( config3Colour == null )
                frameInstance.config3.frontalBaseColour = null;
        } else if ( "leftTemple".equals(component) ) {
            frameInstance.leftTempleBaseColours = baseColours;
            if ( !frameInstance.config2 )
                frameInstance.config2 = new FrameAestheticConfiguration().save();

            if ( config1Colour == null )
                frameInstance.config1.leftTempleBaseColour = null;
            else if ( config2Colour == null )
                frameInstance.config2.leftTempleBaseColour = null;
            else if ( config3Colour == null )
                frameInstance.config3.leftTempleBaseColour = null;
        } else if ( "rightTemple".equals(component) ) {
            frameInstance.rightTempleBaseColours = baseColours;
            if ( !frameInstance.config3 )
                frameInstance.config3 = new FrameAestheticConfiguration().save();

            if ( config1Colour == null )
                frameInstance.config1.rightTempleBaseColour = null;
            else if ( config2Colour == null )
                frameInstance.config2.rightTempleBaseColour = null;
            else if ( config3Colour == null )
                frameInstance.config3.rightTempleBaseColour = null;
        }
        
        frameInstance.save(flush:true);
    }
    
    Set<FrameTexture> handleTextures(Frame frameInstance, String textureType, Set<FrameTexture> frameTextures, String newTextureImageIds, String newTextureRefs, String newTextureX, String newTextureY, String newTextureScale, String newTexturePrice, String newTextureProdTime, String textureDeleteVals) {
        
        def config1Texture = null;
        def config2Texture = null;
        def config3Texture = null;
        
        if ( "frontal".equals(textureType) ) {
            config1Texture = frameInstance.config1?.frontalTexture;
            config2Texture = frameInstance.config2?.frontalTexture;
            config3Texture = frameInstance.config3?.frontalTexture;
        } else if ( "leftTemple".equals(textureType) ) {
            config1Texture = frameInstance.config1?.leftTempleTexture;
            config2Texture = frameInstance.config2?.leftTempleTexture;
            config3Texture = frameInstance.config3?.leftTempleTexture;
        } else if ( "rightTemple".equals(textureType) ) {
            config1Texture = frameInstance.config1?.rightTempleTexture;
            config2Texture = frameInstance.config2?.rightTempleTexture;
            config3Texture = frameInstance.config3?.rightTempleTexture;
        }
        
            
            if ( newTextureImageIds ) {
                // We have new texture information - check that we have the same amount of all bits of information
                String allImageIds = newTextureImageIds;
                String allRefs = newTextureRefs;
                String allXVals = newTextureX;
                String allYVals = newTextureY;
                String allScaleFactors = newTextureScale;
                String allPrices = newTexturePrice;
                String allProdTimes = newTextureProdTime;
                
                log.debug("allImageIds = " + allImageIds + ", allRefs = " + allRefs + ", allXVals = " + allXVals + ", allYVals = " + allYVals
                        + ", allScaleFactors = " + allScaleFactors + ", allPrices = " + allPrices + ", allProdTimes = " + allProdTimes + ", textureType = " + textureType);
                String[] imageIds = allImageIds.split("_BREAK_");
                String[] references = allRefs.split("_BREAK_");
                String[] xVals = allXVals.split("_BREAK_");
                String[] yVals = allYVals.split("_BREAK_");
                String[] scaleFactors = allScaleFactors.split("_BREAK_");
                String[] prices = allPrices.split("_BREAK_");
                String[] prodTimes = allProdTimes.split("_BREAK_");
                
                if ( imageIds.size() != references.size() || imageIds.size() != xVals.size() || imageIds.size() != yVals.size() 
                    || imageIds.size() != scaleFactors.size() || imageIds.size() != prices.size() || imageIds.size() != prodTimes.size() ) {
                    // Incomplete information...
                    log.error("Incomplete information specified!! imageIds.size = " + imageIds.size() + " references.size = " + references.size() + ", xVals.size = " + xVals.size()
                            + ", yVals.size = " + yVals.size() + ", scaleFactors.size = " + scaleFactors.size() + ", prices.size = " + prices.size() + ", prodTimes.size = " + prodTimes.size());
                    // TODO
                } else {
                    // We have correct numbers of each field - process them
                    log.debug("Correct numbers of each of the fields specified - now processing");
                    
                    // Get the list of all of the image Ids referenced currently to make later checks easier
                    Set<Long> existingImageIds = new HashSet<Long>();
                    for(FrameTexture aTexture: frameTextures) {
                        Long imageId = aTexture.image.id;
                        existingImageIds.add(imageId);
                    }
                    
                    log.debug("Have finished getting the list of all image ids")
                    
                    for(int ctr = 0; ctr < imageIds.size(); ctr++ ) {
                        Long thisImageId = new Long(imageIds[ctr]);
                        String thisRef = references[ctr];
                        int thisX = Integer.parseInt(xVals[ctr]);
                        int thisY = Integer.parseInt(yVals[ctr]);
                        float thisScaleFactor = new Float(scaleFactors[ctr]);
                        float thisPrice = new Float(prices[ctr]);
                        int thisProdTime = Integer.parseInt(prodTimes[ctr]);

                        // Check to see whether there's an existing texture using this image for this frame
                        log.debug("About to check if this image is already linked");
                        
                        if ( existingImageIds.contains(thisImageId) ) {
                            // Existing image - don't process this one
                            log.debug("existing image in the list so not adding..");
                            // TODO
                        } else {
                            log.debug("referenced image not linked to already so going to do it now");
                            
                            FrameImage referencedImage = FrameImage.get(thisImageId);
                            if ( !referencedImage ) {
                                // The image can't be found..
                                log.error("Unable to find the image that we want to link");
                                // TODO
                            } else {
                                log.debug("About to add in the image..");
                                
                                FrameTexture newTexture = new FrameTexture(reference: thisRef, x: thisX, y: thisY, scaleFactor: thisScaleFactor, price: thisPrice, productionTime: thisProdTime, image: referencedImage).save();
                                frameTextures.add(newTexture);
                                log.debug("newTexture = " + newTexture);
                                log.debug("newTexture.id = " + newTexture.id);
                            }
                        }
                    }
                }
                
                
            }
            
            log.debug("Finished adding in image links - about to look at removals");
        
            // Check for any texture removals
            Set<FrameTexture> texturesToRemove = new HashSet<FrameTexture>();
            
            if ( textureDeleteVals ) {
                String allDelVals = textureDeleteVals;
                String[] delVals = allDelVals.split("_BREAK_");
                for(String thisId: delVals) {
                    Long thisIdNum = new Long(thisId);
                    
                    FrameTexture thisTexture = FrameTexture.get(thisIdNum);
                    if ( frameTextures.contains(thisTexture) ) {
                        frameTextures.remove(thisTexture);
                        texturesToRemove.add(thisTexture);
                        if ( thisTexture.equals(config1Texture) )
                            config1Texture = null;
                        if ( thisTexture.equals(config2Texture) )
                            config2Texture = null;
                        if ( thisTexture.equals(config3Texture) )
                            config3Texture = null;
                    }
                }
            }
        
            if ( !frameInstance.config1 )
                frameInstance.config1 = new FrameAestheticConfiguration().save();
            if ( !frameInstance.config2 )
                frameInstance.config2 = new FrameAestheticConfiguration().save();
            if ( !frameInstance.config3 )
                frameInstance.config3 = new FrameAestheticConfiguration().save();
        
            if ( textureType.equals("leftTemple") ) {
                frameInstance.leftTempleTexture = frameTextures;
                if ( config1Texture == null )
                    frameInstance.config1.leftTempleTexture = null;
                if ( config2Texture == null )
                    frameInstance.config2.leftTempleTexture = null;
                if ( config3Texture == null )
                    frameInstance.config3.leftTempleTexture = null;
            }
            else if ( textureType.equals("rightTemple") ) {
                frameInstance.rightTempleTexture = frameTextures;
                if ( config1Texture == null )
                    frameInstance.config1.rightTempleTexture = null;
                if ( config2Texture == null )
                    frameInstance.config2.rightTempleTexture = null;
                if ( config3Texture == null )
                    frameInstance.config3.rightTempleTexture = null;
            }
            else if ( textureType.equals("frontal") ) {
                frameInstance.frontalTexture = frameTextures;
                if ( config1Texture == null )
                    frameInstance.config1.frontalTexture = null;
                if ( config2Texture == null )
                    frameInstance.config2.frontalTexture = null;
                if ( config3Texture == null )
                    frameInstance.config3.frontalTexture = null;
            }
                
        
            frameInstance.save(flush: true);
            
            for(FrameTexture texture: texturesToRemove) {
                try {
                    texture.delete(flush:true);
                } catch (DataIntegrityViolationException dive) {
                    log.error dive.message;
                    dive.printStackTrace();

                    flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'frame.texture.label', default: 'Texture'), texture.id])}"
                    redirect(action: "show", id: params.id);
                }
            }
            
    }
    
}
