package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.Frame;
import com.k_int.made4u.frame.Precooked;
import com.k_int.made4u.frame.FrameBaseColour;
import com.k_int.made4u.frame.FrameTexture;
import com.k_int.made4u.frame.EmotionalVariable;
import com.k_int.made4u.frame.FrameAestheticConfiguration;

class FrameCopyController {

    static allowedMethods = [save: "POST"]

    def create = {
        // Get the frame to copy from
        def frameInstance = Frame.get(params.id);
        
        if ( !frameInstance ) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
            redirect(controller: 'FrameCatalogue', action: "list")
        } else {
            
            def targetFrame = new Frame();
            targetFrame.identifier = frameInstance.identifier + "-COPY";
            
            return [targetFrame: targetFrame, sourceId: params.id]
        }
    }

    def save = {
        
        // Get the frame that we're going to copy
        def sourceFrame = Frame.get(params.sourceId);
        
        def newFrameId = 0;
        
        if ( !sourceFrame ) {
            // No frame to copy from - error
            System.err.println("HERE params.id: " + params.sourceId);
            // TODO
        } else {
            // We have a frame to copy from
            System.err.println("HERE 2");
            // Check that there isn't already a frame with the specified identifier
            def existingFrame = Frame.findByIdentifier(params.identifier);
            if ( existingFrame ) {
                // There's already a frame with this identifier - complain..
                def targetFrame = new Frame();
                targetFrame.identifier = params.identifier;
                
                System.err.println("HERE 3");
                
                flash.message = "The chosen identifier already exists within the frame catalogue. Please choose another identifier and try again";
                render(view: "create", model: [targetFrame: targetFrame, sourceId: params.sourceId])
            } else {
                // No existing frame with this identifier - good to go with the copying..
                
                System.err.println("HERE 4");
                // Set up the new frame with the bare essentials to allow it to be saved
                def newFrame = new Frame();
                newFrame.identifier = params.identifier;
                newFrame.material = sourceFrame.material;
                newFrame.rimShape = sourceFrame.rimShape;
                
                // Save the frame straight away to make sure other submissions can't get mixed in with this operation
                newFrame.dimensions.totalLength.save();
                newFrame.dimensions.save();
                newFrame.leftTempleDims.save();
                newFrame.rightTempleDims.save();
                if ( !newFrame.save() ) {
                    newFrame.errors.each {
                        System.err.println("Saving new frame error:" + it);
                    }
                }
                
                System.err.println("HERE 5");
                
                newFrameId = newFrame.id;
                
                System.err.println("newFrameId = " + newFrameId);
                
                // Set up some variables to track mappings from old textures to new, etc.
                Map<Long,Long> textureChangeMap = new HashMap<Long,Long>();
                // TODO - what else?
                
                // Now work through the frame and copy everything into it..
                
                // Copy general properties
                newFrame.material                           = sourceFrame.material;
                newFrame.rimType                            = sourceFrame.rimType;
                newFrame.gender                             = sourceFrame.gender;
                newFrame.rimShape                           = sourceFrame.rimShape;
                newFrame.endpieceHeight                     = sourceFrame.endpieceHeight;
                newFrame.frameUse                           = sourceFrame.frameUse;
                newFrame.fullyPersonalisedBasePrice         = sourceFrame.fullyPersonalisedBasePrice;
                newFrame.semiPersonalisedBasePrice          = sourceFrame.semiPersonalisedBasePrice;
                newFrame.standardBasePrice                  = sourceFrame.standardBasePrice;
                newFrame.fullyPersonalisedProductionTime    = sourceFrame.fullyPersonalisedProductionTime;
                newFrame.semiPersonalisedProductionTime     = sourceFrame.semiPersonalisedProductionTime;
                newFrame.finishingAspect                    = sourceFrame.finishingAspect;
                newFrame.leftTempleFinishingAspect          = sourceFrame.leftTempleFinishingAspect;
                newFrame.rightTempleFinishingAspect         = sourceFrame.rightTempleFinishingAspect;
                newFrame.mattePrice                         = sourceFrame.mattePrice;
                newFrame.leftTempleMattePrice               = sourceFrame.leftTempleMattePrice;
                newFrame.rightTempleMattePrice              = sourceFrame.rightTempleMattePrice;
                newFrame.matteProductionTime                = sourceFrame.matteProductionTime;
                newFrame.leftTempleMatteProductionTime      = sourceFrame.leftTempleMatteProductionTime;
                newFrame.rightTempleMatteProductionTime     = sourceFrame.rightTempleMatteProductionTime;
                newFrame.brilliantPrice                     = sourceFrame.brilliantPrice;
                newFrame.leftTempleBrilliantPrice           = sourceFrame.leftTempleBrilliantPrice;
                newFrame.rightTempleBrilliantPrice          = sourceFrame.rightTempleBrilliantPrice;
                newFrame.brilliantProductionTime            = sourceFrame.brilliantProductionTime;
                newFrame.minLensThickness                   = sourceFrame.minLensThickness;
                newFrame.leftTopPupilFrameDistance          = sourceFrame.leftTopPupilFrameDistance;
                newFrame.leftBottomPupilFrameDistancePSV    = sourceFrame.leftBottomPupilFrameDistancePSV
                newFrame.leftBottomPupilFrameDistanceMF     = sourceFrame.leftBottomPupilFrameDistanceMF
                newFrame.rightTopPupilFrameDistance         = sourceFrame.rightTopPupilFrameDistance;
                newFrame.rightBottomPupilFrameDistancePSV   = sourceFrame.rightBottomPupilFrameDistancePSV
                newFrame.rightBottomPupilFrameDistanceMF    = sourceFrame.rightBottomPupilFrameDistanceMF
                
                newFrame.hinge                              = sourceFrame.hinge;
                newFrame.nylon                              = sourceFrame.nylon;
                newFrame.screw                              = sourceFrame.screw;
                
                
                // Frame dimensions
                // Frontal
                if ( sourceFrame.dimensions ) {
                    newFrame.dimensions.totalLength.save();
                    newFrame.dimensions.save();
                    
                    newFrame.dimensions.boxingHorizontalStandard        = sourceFrame.dimensions.boxingHorizontalStandard;
                    newFrame.dimensions.boxingHorizontalMin             = sourceFrame.dimensions.boxingHorizontalMin;
                    newFrame.dimensions.boxingHorizontalMax             = sourceFrame.dimensions.boxingHorizontalMax;
                    newFrame.dimensions.boxingVerticalStandard          = sourceFrame.dimensions.boxingVerticalStandard;
                    newFrame.dimensions.boxingVerticalMin               = sourceFrame.dimensions.boxingVerticalMin;
                    newFrame.dimensions.boxingVerticalMax               = sourceFrame.dimensions.boxingVerticalMax;
                    newFrame.dimensions.pantoscopicAngleLeftStandard    = sourceFrame.dimensions.pantoscopicAngleLeftStandard;
                    newFrame.dimensions.pantoscopicAngleLeftMin         = sourceFrame.dimensions.pantoscopicAngleLeftMin;
                    newFrame.dimensions.pantoscopicAngleLeftMax         = sourceFrame.dimensions.pantoscopicAngleLeftMax;
                    newFrame.dimensions.pantoscopicAngleRightStandard   = sourceFrame.dimensions.pantoscopicAngleRightStandard;
                    newFrame.dimensions.pantoscopicAngleRightMin        = sourceFrame.dimensions.pantoscopicAngleRightMin;
                    newFrame.dimensions.pantoscopicAngleRightMax        = sourceFrame.dimensions.pantoscopicAngleRightMax;
                    newFrame.dimensions.bridgeWidthStandard             = sourceFrame.dimensions.bridgeWidthStandard;
                    newFrame.dimensions.bridgeWidthMin                  = sourceFrame.dimensions.bridgeWidthMin;
                    newFrame.dimensions.bridgeWidthMax                  = sourceFrame.dimensions.bridgeWidthMax;
                    newFrame.dimensions.bridgeHeightStandard            = sourceFrame.dimensions.bridgeHeightStandard;
                    newFrame.dimensions.bridgeHeightMin                 = sourceFrame.dimensions.bridgeHeightMin;
                    newFrame.dimensions.bridgeHeightMax                 = sourceFrame.dimensions.bridgeHeightMax;
                    newFrame.dimensions.heelWidthStandard               = sourceFrame.dimensions.heelWidthStandard;
                    newFrame.dimensions.heelWidthMin                    = sourceFrame.dimensions.heelWidthMin;
                    newFrame.dimensions.heelWidthMax                    = sourceFrame.dimensions.heelWidthMax;
                    newFrame.dimensions.internalRimReductionStandard    = sourceFrame.dimensions.internalRimReductionStandard;
                    newFrame.dimensions.internalRimReductionMin         = sourceFrame.dimensions.internalRimReductionMin;
                    newFrame.dimensions.internalRimReductionMax         = sourceFrame.dimensions.internalRimReductionMax;
                    newFrame.dimensions.baseOfFrameStandard             = sourceFrame.dimensions.baseOfFrameStandard;
                    newFrame.dimensions.baseOfFrameMin                  = sourceFrame.dimensions.baseOfFrameMin;
                    newFrame.dimensions.baseOfFrameMax                  = sourceFrame.dimensions.baseOfFrameMax;
                    newFrame.dimensions.wrapAngleStandard               = sourceFrame.dimensions.wrapAngleStandard;
                    newFrame.dimensions.wrapAngleMin                    = sourceFrame.dimensions.wrapAngleMin;
                    newFrame.dimensions.wrapAngleMax                    = sourceFrame.dimensions.wrapAngleMax;
                    newFrame.dimensions.totalLengthStandard             = sourceFrame.dimensions.totalLengthStandard;
                    
                    // Frontal total length information
                    if ( sourceFrame.dimensions.totalLength ) {
                        newFrame.dimensions.totalLength.bridgeWidth1    = sourceFrame.dimensions.totalLength.bridgeWidth1;
                        newFrame.dimensions.totalLength.min1            = sourceFrame.dimensions.totalLength.min1;
                        newFrame.dimensions.totalLength.max1            = sourceFrame.dimensions.totalLength.max1;
                        newFrame.dimensions.totalLength.bridgeWidth2    = sourceFrame.dimensions.totalLength.bridgeWidth2;
                        newFrame.dimensions.totalLength.min2            = sourceFrame.dimensions.totalLength.min2;
                        newFrame.dimensions.totalLength.max2            = sourceFrame.dimensions.totalLength.max2;
                        newFrame.dimensions.totalLength.bridgeWidth3    = sourceFrame.dimensions.totalLength.bridgeWidth3;
                        newFrame.dimensions.totalLength.min3            = sourceFrame.dimensions.totalLength.min3;
                        newFrame.dimensions.totalLength.max3            = sourceFrame.dimensions.totalLength.max3;
                        newFrame.dimensions.totalLength.bridgeWidth4    = sourceFrame.dimensions.totalLength.bridgeWidth4;
                        newFrame.dimensions.totalLength.min4            = sourceFrame.dimensions.totalLength.min4;
                        newFrame.dimensions.totalLength.max4            = sourceFrame.dimensions.totalLength.max4;
                        newFrame.dimensions.totalLength.bridgeWidth5    = sourceFrame.dimensions.totalLength.bridgeWidth5;
                        newFrame.dimensions.totalLength.min5            = sourceFrame.dimensions.totalLength.min5;
                        newFrame.dimensions.totalLength.max5            = sourceFrame.dimensions.totalLength.max5;
                        newFrame.dimensions.totalLength.bridgeWidth6    = sourceFrame.dimensions.totalLength.bridgeWidth6;
                        newFrame.dimensions.totalLength.min6            = sourceFrame.dimensions.totalLength.min6;
                        newFrame.dimensions.totalLength.max6            = sourceFrame.dimensions.totalLength.max6;
                        newFrame.dimensions.totalLength.bridgeWidth7    = sourceFrame.dimensions.totalLength.bridgeWidth7;
                        newFrame.dimensions.totalLength.min7            = sourceFrame.dimensions.totalLength.min7;
                        newFrame.dimensions.totalLength.max7            = sourceFrame.dimensions.totalLength.max7;
                        newFrame.dimensions.totalLength.bridgeWidth8    = sourceFrame.dimensions.totalLength.bridgeWidth8;
                        newFrame.dimensions.totalLength.min8            = sourceFrame.dimensions.totalLength.min8;
                        newFrame.dimensions.totalLength.max8            = sourceFrame.dimensions.totalLength.max8;
                        newFrame.dimensions.totalLength.bridgeWidth9    = sourceFrame.dimensions.totalLength.bridgeWidth9;
                        newFrame.dimensions.totalLength.min9            = sourceFrame.dimensions.totalLength.min9;
                        newFrame.dimensions.totalLength.max9            = sourceFrame.dimensions.totalLength.max9;
                        newFrame.dimensions.totalLength.bridgeWidth10   = sourceFrame.dimensions.totalLength.bridgeWidth10;
                        newFrame.dimensions.totalLength.min10           = sourceFrame.dimensions.totalLength.min10;
                        newFrame.dimensions.totalLength.max10           = sourceFrame.dimensions.totalLength.max10;
                        newFrame.dimensions.totalLength.bridgeWidth11   = sourceFrame.dimensions.totalLength.bridgeWidth11;
                        newFrame.dimensions.totalLength.min11           = sourceFrame.dimensions.totalLength.min11;
                        newFrame.dimensions.totalLength.max11           = sourceFrame.dimensions.totalLength.max11;
                        newFrame.dimensions.totalLength.bridgeWidth12   = sourceFrame.dimensions.totalLength.bridgeWidth12;
                        newFrame.dimensions.totalLength.min12           = sourceFrame.dimensions.totalLength.min12;
                        newFrame.dimensions.totalLength.max12           = sourceFrame.dimensions.totalLength.max12;
                        newFrame.dimensions.totalLength.bridgeWidth13   = sourceFrame.dimensions.totalLength.bridgeWidth13;
                        newFrame.dimensions.totalLength.min13           = sourceFrame.dimensions.totalLength.min13;
                        newFrame.dimensions.totalLength.max13           = sourceFrame.dimensions.totalLength.max13;
                        newFrame.dimensions.totalLength.bridgeWidth14   = sourceFrame.dimensions.totalLength.bridgeWidth14;
                        newFrame.dimensions.totalLength.min14           = sourceFrame.dimensions.totalLength.min14;
                        newFrame.dimensions.totalLength.max14           = sourceFrame.dimensions.totalLength.max14;
                        newFrame.dimensions.totalLength.bridgeWidth15   = sourceFrame.dimensions.totalLength.bridgeWidth15;
                        newFrame.dimensions.totalLength.min15           = sourceFrame.dimensions.totalLength.min15;
                        newFrame.dimensions.totalLength.max15           = sourceFrame.dimensions.totalLength.max15;
                    }
                    
                }
                // Left temple
                newFrame.leftTempleDims.save();
                if ( sourceFrame.leftTempleDims ) {
                    newFrame.leftTempleDims.lengthStandard          = sourceFrame.leftTempleDims.lengthStandard;
                    newFrame.leftTempleDims.lengthMin               = sourceFrame.leftTempleDims.lengthMin;
                    newFrame.leftTempleDims.lengthMax               = sourceFrame.leftTempleDims.lengthMax;
                    newFrame.leftTempleDims.openingAngleStandard    = sourceFrame.leftTempleDims.openingAngleStandard;
                    newFrame.leftTempleDims.openingAngleMin         = sourceFrame.leftTempleDims.openingAngleMin;
                    newFrame.leftTempleDims.openingAngleMax         = sourceFrame.leftTempleDims.openingAngleMax;
                    newFrame.leftTempleDims.mainCurvatureStandard   = sourceFrame.leftTempleDims.mainCurvatureStandard;
                    newFrame.leftTempleDims.mainCurvatureMin        = sourceFrame.leftTempleDims.mainCurvatureMin;
                    newFrame.leftTempleDims.mainCurvatureMax        = sourceFrame.leftTempleDims.mainCurvatureMax;
                    newFrame.leftTempleDims.terminalAngleStandard   = sourceFrame.leftTempleDims.terminalAngleStandard;
                    newFrame.leftTempleDims.terminalAngleMin        = sourceFrame.leftTempleDims.terminalAngleMin;
                    newFrame.leftTempleDims.terminalAngleMax        = sourceFrame.leftTempleDims.terminalAngleMax;
                }
                // Right temple
                newFrame.rightTempleDims.save();
                if ( sourceFrame.rightTempleDims ) {
                    newFrame.rightTempleDims.lengthStandard         = sourceFrame.rightTempleDims.lengthStandard;
                    newFrame.rightTempleDims.lengthMin              = sourceFrame.rightTempleDims.lengthMin;
                    newFrame.rightTempleDims.lengthMax              = sourceFrame.rightTempleDims.lengthMax;
                    newFrame.rightTempleDims.openingAngleStandard   = sourceFrame.rightTempleDims.openingAngleStandard;
                    newFrame.rightTempleDims.openingAngleMin        = sourceFrame.rightTempleDims.openingAngleMin;
                    newFrame.rightTempleDims.openingAngleMax        = sourceFrame.rightTempleDims.openingAngleMax;
                    newFrame.rightTempleDims.mainCurvatureStandard  = sourceFrame.rightTempleDims.mainCurvatureStandard;
                    newFrame.rightTempleDims.mainCurvatureMin       = sourceFrame.rightTempleDims.mainCurvatureMin;
                    newFrame.rightTempleDims.mainCurvatureMax       = sourceFrame.rightTempleDims.mainCurvatureMax;
                    newFrame.rightTempleDims.terminalAngleStandard  = sourceFrame.rightTempleDims.terminalAngleStandard;
                    newFrame.rightTempleDims.terminalAngleMin       = sourceFrame.rightTempleDims.terminalAngleMin;
                    newFrame.rightTempleDims.terminalAngleMax       = sourceFrame.rightTempleDims.terminalAngleMax;
                }
                
                // Precookeds
                def precookedDir = grailsApplication.config.com.k_int.made4u.frame.precooked.uploadDirectory;
                if ( !precookedDir.endsWith(File.separator) ) 
                    precookedDir = precookedDir + File.separator;
                def publicPrecookedDir = grailsApplication.config.com.k_int.made4u.frame.precooked.publicUploadDirectory;
                if ( !publicPrecookedDir.endsWith(File.separator) ) 
                    publicPrecookedDir = publicPrecookedDir + File.separator;

                if ( sourceFrame.precookeds && !sourceFrame.precookeds.isEmpty() ) {
                    for(Precooked aPrecooked: sourceFrame.precookeds) {
                        
                        def newPrecooked = new Precooked();
                        newPrecooked.reference                      = aPrecooked.reference;
                        newPrecooked.heelWidth                      = aPrecooked.heelWidth;
                        newPrecooked.bridgeWidth                    = aPrecooked.bridgeWidth;
                        newPrecooked.path                           = "temp-"+System.currentTimeMillis();
                        
                        // Save the precooked to get the id and then copy the file to the relevant place
                        // and update the precooked with this new path
                        def filename = aPrecooked.path;
                        def indexOfSlash = filename.lastIndexOf("/");
                        filename = filename.substring(indexOfSlash+1);
                        File oldPrecookedFile = new File(precookedDir + aPrecooked.id , filename);

                        if ( oldPrecookedFile.exists() ) {

                            newPrecooked.save(flush:true);


                            System.err.println("oldPrecookedFile path = " + precookedDir + aPrecooked.id + "/" + filename);
                            File newDir = new File(precookedDir + newPrecooked.id);
                            newDir.mkdirs();
                            System.err.println("newPrecookedFile path = " + precookedDir + newPrecooked.id + "/" + filename);
                            File destFile = new File(precookedDir + newPrecooked.id, filename);

                            oldPrecookedFile.withInputStream { is -> 
                                destFile << is 
                            }

                            newPrecooked.path                           = publicPrecookedDir + newPrecooked.id + "/" + filename;

                            newPrecooked.save(flush: true);
                            newFrame.precookeds.add(newPrecooked);
                        }
                    }
                }
                
                // Frame base colours
                // Frontal
                if ( sourceFrame.frontalBaseColours && !sourceFrame.frontalBaseColours.isEmpty() ) {
                    for(FrameBaseColour aColour: sourceFrame.frontalBaseColours) {
                        newFrame.frontalBaseColours.add(aColour);
                    }
                }
                // Left temple
                if ( sourceFrame.leftTempleBaseColours && !sourceFrame.leftTempleBaseColours.isEmpty() ) {
                    for(FrameBaseColour aColour: sourceFrame.leftTempleBaseColours) {
                        newFrame.leftTempleBaseColours.add(aColour);
                    }
                }
                // Right temple
                if ( sourceFrame.rightTempleBaseColours && !sourceFrame.rightTempleBaseColours.isEmpty() ) {
                    for(FrameBaseColour aColour: sourceFrame.rightTempleBaseColours) {
                        newFrame.rightTempleBaseColours.add(aColour);
                    }
                }

                // Frame textures
                // Frontal
                if ( sourceFrame.frontalTexture && !sourceFrame.frontalTexture.isEmpty() ) {
                    for(FrameTexture aTexture: sourceFrame.frontalTexture) {
                        def newFrameTexture = new FrameTexture();
                        newFrameTexture.reference       = aTexture.reference;
                        newFrameTexture.x               = aTexture.x;
                        newFrameTexture.y               = aTexture.y;
                        newFrameTexture.scaleFactor     = aTexture.scaleFactor;
                        newFrameTexture.price           = aTexture.price;
                        newFrameTexture.productionTime  = aTexture.productionTime;
                        newFrameTexture.image           = aTexture.image;
                        
                        newFrameTexture.save(flush: true);
                        
                        newFrame.frontalTexture.add(newFrameTexture);
                        // Remember the mapping from old id to new so that we can reference it later
                        textureChangeMap.put(aTexture.id, newFrameTexture.id);
                    }
                }
                // Left temple
                if ( sourceFrame.leftTempleTexture && !sourceFrame.leftTempleTexture.isEmpty() ) {
                    for(FrameTexture aTexture: sourceFrame.leftTempleTexture) {
                        def newFrameTexture = new FrameTexture();
                        newFrameTexture.reference       = aTexture.reference;
                        newFrameTexture.x               = aTexture.x;
                        newFrameTexture.y               = aTexture.y;
                        newFrameTexture.scaleFactor     = aTexture.scaleFactor;
                        newFrameTexture.price           = aTexture.price;
                        newFrameTexture.productionTime  = aTexture.productionTime;
                        newFrameTexture.image           = aTexture.image;
                        
                        newFrameTexture.save(flush: true);
                        
                        newFrame.leftTempleTexture.add(newFrameTexture);
                        // Remember the mapping from old id to new so that we can reference it later
                        textureChangeMap.put(aTexture.id, newFrameTexture.id);
                    }
                }
                // Right temple
                if ( sourceFrame.rightTempleTexture && !sourceFrame.rightTempleTexture.isEmpty() ) {
                    for(FrameTexture aTexture: sourceFrame.rightTempleTexture) {
                        def newFrameTexture = new FrameTexture();
                        newFrameTexture.reference       = aTexture.reference;
                        newFrameTexture.x               = aTexture.x;
                        newFrameTexture.y               = aTexture.y;
                        newFrameTexture.scaleFactor     = aTexture.scaleFactor;
                        newFrameTexture.price           = aTexture.price;
                        newFrameTexture.productionTime  = aTexture.productionTime;
                        newFrameTexture.image           = aTexture.image;
                        
                        newFrameTexture.save(flush: true);
                        
                        newFrame.rightTempleTexture.add(newFrameTexture);
                        // Remember the mapping from old id to new so that we can reference it later
                        textureChangeMap.put(aTexture.id, newFrameTexture.id);
                    }
                }
                
                // Aesthetic configurations

                def aestheticImageUploadDir = grailsApplication.config.com.k_int.made4u.frame.aesthetic.config.image.uploadDirectory
                def aestheticImagePublicUploadDir = grailsApplication.config.com.k_int.made4u.frame.aesthetic.config.image.publicUploadDirectory
                if ( !aestheticImageUploadDir.endsWith("/") )
                    aestheticImageUploadDir = aestheticImageUploadDir + "/";
                if ( !aestheticImagePublicUploadDir.endsWith("/") )
                    aestheticImagePublicUploadDir = aestheticImagePublicUploadDir + "/";

                newFrame.config1 = new FrameAestheticConfiguration().save();
                newFrame.config2 = new FrameAestheticConfiguration().save();
                newFrame.config3 = new FrameAestheticConfiguration().save();

                
                if ( sourceFrame.config1 ) {
                    
                    newFrame.config1.leftTempleFinishingAspect      = sourceFrame.config1.leftTempleFinishingAspect;
                    newFrame.config1.leftTempleBaseColour           = sourceFrame.config1.leftTempleBaseColour;
                    newFrame.config1.rightTempleFinishingAspect     = sourceFrame.config1.rightTempleFinishingAspect;
                    newFrame.config1.rightTempleBaseColour          = sourceFrame.config1.rightTempleBaseColour;
                    newFrame.config1.frontalFinishingAspect         = sourceFrame.config1.frontalFinishingAspect;
                    newFrame.config1.frontalBaseColour              = sourceFrame.config1.frontalBaseColour;

                    if ( sourceFrame.config1.leftTempleTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config1.leftTempleTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config1.leftTempleTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config1.leftTempleTexture.id);
                        }
                        
                    }
                    if ( sourceFrame.config1.rightTempleTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config1.rightTempleTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config1.rightTempleTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config1.rightTempleTexture.id);
                        }
                        
                    }
                    if ( sourceFrame.config1.frontalTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config1.frontalTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config1.frontalTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config1.frontalTexture.id);
                        }
                        
                    }
                    
                    newFrame.config1.reference                      = sourceFrame.config1.reference;
                    
                    if ( sourceFrame.config1.emotionalVariables && !sourceFrame.config1.emotionalVariables.isEmpty() ) {
                        for(EmotionalVariable anEmo: sourceFrame.config1.emotionalVariables) {
                            newFrame.config1.emotionalVariables.add(anEmo);
                        }
                    }
                    if ( sourceFrame.config1.imagePath ) {

                        def indexOfSlash = sourceFrame.config1.imagePath.lastIndexOf("/");
                        def filename = sourceFrame.config1.imagePath.substring(indexOfSlash+1);

                        File oldImageFile = new File(sourceFrame.config1.privateImagePath);
                        System.err.println("oldImageFile path = " + sourceFrame.config1.privateImagePath);
                        File newDir = new File(aestheticImageUploadDir + newFrame.id);
                        newDir.mkdirs();
                        System.err.println("newImageFile path = " + aestheticImageUploadDir + newFrame.id + "/" + filename);

                        File destFile = new File(aestheticImageUploadDir + newFrame.id, filename);
                        if ( oldImageFile.exists() ) {
                            oldImageFile.withInputStream { is -> 
                                destFile << is 
                            }

                            newFrame.config1.imagePath                      = aestheticImagePublicUploadDir + newFrame.id + "/" + filename;
                            newFrame.config1.privateImagePath               = aestheticImageUploadDir + newFrame.id + "/" + filename;
                        } 
                    }
                }
                if ( sourceFrame.config2 ) {
                    newFrame.config2.leftTempleFinishingAspect      = sourceFrame.config2.leftTempleFinishingAspect;
                    newFrame.config2.leftTempleBaseColour           = sourceFrame.config2.leftTempleBaseColour;
                    newFrame.config2.rightTempleFinishingAspect     = sourceFrame.config2.rightTempleFinishingAspect;
                    newFrame.config2.rightTempleBaseColour          = sourceFrame.config2.rightTempleBaseColour;
                    newFrame.config2.frontalFinishingAspect         = sourceFrame.config2.frontalFinishingAspect;
                    newFrame.config2.frontalBaseColour              = sourceFrame.config2.frontalBaseColour;

                    if ( sourceFrame.config2.leftTempleTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config2.leftTempleTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config2.leftTempleTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config2.leftTempleTexture.id);
                        }
                        
                    }
                    if ( sourceFrame.config2.rightTempleTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config2.rightTempleTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config2.rightTempleTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config2.rightTempleTexture.id);
                        }
                        
                    }
                    if ( sourceFrame.config2.frontalTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config2.frontalTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config2.frontalTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config2.frontalTexture.id);
                        }
                        
                    }
                    
                    newFrame.config2.reference                      = sourceFrame.config2.reference;
                    
                    if ( sourceFrame.config2.emotionalVariables && !sourceFrame.config2.emotionalVariables.isEmpty() ) {
                        for(EmotionalVariable anEmo: sourceFrame.config2.emotionalVariables) {
                            newFrame.config2.emotionalVariables.add(anEmo);
                        }
                    }
                    
                    if ( sourceFrame.config2.imagePath ) {

                        def indexOfSlash = sourceFrame.config2.imagePath.lastIndexOf("/");
                        def filename = sourceFrame.config2.imagePath.substring(indexOfSlash+1);

                        File oldImageFile = new File(sourceFrame.config2.privateImagePath);
                        System.err.println("oldImageFile path = " + sourceFrame.config2.privateImagePath);
                        File newDir = new File(aestheticImageUploadDir + newFrame.id);
                        newDir.mkdirs();
                        System.err.println("newImageFile path = " + aestheticImageUploadDir + newFrame.id + "/" + filename);
                        File destFile = new File(aestheticImageUploadDir + newFrame.id, filename);
                        if ( oldImageFile.exists() ) {
                            oldImageFile.withInputStream { is -> 
                                destFile << is 
                            }
                        }                      

                        newFrame.config2.imagePath                      = aestheticImagePublicUploadDir + newFrame.id + "/" + filename;
                        newFrame.config2.privateImagePath               = aestheticImageUploadDir + newFrame.id + "/" + filename;
                        
                    }
                }
                if ( sourceFrame.config3 ) {
                    newFrame.config3.leftTempleFinishingAspect      = sourceFrame.config3.leftTempleFinishingAspect;
                    newFrame.config3.leftTempleBaseColour           = sourceFrame.config3.leftTempleBaseColour;
                    newFrame.config3.rightTempleFinishingAspect     = sourceFrame.config3.rightTempleFinishingAspect;
                    newFrame.config3.rightTempleBaseColour          = sourceFrame.config3.rightTempleBaseColour;
                    newFrame.config3.frontalFinishingAspect         = sourceFrame.config3.frontalFinishingAspect;
                    newFrame.config3.frontalBaseColour              = sourceFrame.config3.frontalBaseColour;

                    if ( sourceFrame.config3.leftTempleTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config3.leftTempleTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config3.leftTempleTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config3.leftTempleTexture.id);
                        }
                        
                    }
                    if ( sourceFrame.config3.rightTempleTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config3.rightTempleTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config3.rightTempleTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config3.rightTempleTexture.id);
                        }
                        
                    }
                    if ( sourceFrame.config3.frontalTexture ) {
                        def tempTextureId = textureChangeMap.get(sourceFrame.config3.frontalTexture.id);
                        if ( tempTextureId ) {
                            def tempTexture = FrameTexture.get(tempTextureId);
                            newFrame.config3.frontalTexture = tempTexture;
                        } else {
                            log.error("No new texture id found in the texture map for original id: " + sourceFrame.config3.frontalTexture.id);
                        }
                        
                    }
                    
                    newFrame.config3.reference                      = sourceFrame.config3.reference;
                    
                    if ( sourceFrame.config3.emotionalVariables && !sourceFrame.config3.emotionalVariables.isEmpty() ) {
                        for(EmotionalVariable anEmo: sourceFrame.config3.emotionalVariables) {
                            newFrame.config3.emotionalVariables.add(anEmo);
                        }
                    }
                    
                    if ( sourceFrame.config3.imagePath ) {

                        def indexOfSlash = sourceFrame.config3.imagePath.lastIndexOf("/");
                        def filename = sourceFrame.config3.imagePath.substring(indexOfSlash+1);

                        File oldImageFile = new File(sourceFrame.config3.privateImagePath);
                        System.err.println("oldImageFile path = " + sourceFrame.config1.privateImagePath);
                        File newDir = new File(aestheticImageUploadDir + newFrame.id);
                        newDir.mkdirs();
                        System.err.println("newImageFile path = " + aestheticImageUploadDir + newFrame.id + "/" + filename);
                        File destFile = new File(aestheticImageUploadDir + newFrame.id, filename);
                        if ( oldImageFile.exists() ) {
                            oldImageFile.withInputStream { is -> 
                                destFile << is 
                            }
                            newFrame.config3.imagePath                      = aestheticImagePublicUploadDir + newFrame.id + "/" + filename;
                            newFrame.config3.privateImagePath               = aestheticImageUploadDir + newFrame.id + "/" + filename;
                        }
                        
                    }
                }
                
                // Save the frame now we're all finished..
                newFrame.save(flush:true);
            }
        }
        
        System.err.println("All finished about to redirect to show frame with id: " + newFrameId);
        
        flash.message = "Frame copied successfully"; // TODO - make this a message..
        redirect(controller: "frameCatalogue", action: "show", id: newFrameId);
    }


}
