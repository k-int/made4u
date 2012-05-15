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
import com.k_int.made4u.frame.FrameAestheticConfiguration;
import com.k_int.made4u.frame.EmotionalVariable;

import org.springframework.dao.DataIntegrityViolationException;

class FrameAestheticConfigurationController {

    static allowedMethods = [update: "POST"]


    def show = {
        def frameInstance = Frame.get(params.id)
        if (!frameInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
            redirect(controller: "FrameCatalogue", action: "show", params: params)
        }
        else {
            [config1: frameInstance.config1, config2: frameInstance.config2, config3: frameInstance.config3, frameId: params.id]
        }
    }

    def edit = {
        def frameInstance = Frame.get(params.id)
        if (!frameInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
            redirect(controller: "FrameCatalogue", action: "show", params: params) 
        }
        else {
            def shouldSave = false;
            if ( frameInstance.config1 == null ) {
                frameInstance.config1 = new FrameAestheticConfiguration().save();
                shouldSave = true;
            }
            if ( frameInstance.config2 == null ) {
                frameInstance.config2 = new FrameAestheticConfiguration().save();
                shouldSave = true;
            }
            if ( frameInstance.config3 == null ) {
                frameInstance.config3 = new FrameAestheticConfiguration().save();
                shouldSave = true;
            }
            
            if ( shouldSave )
                frameInstance.save(flush:true);
                
            
            // Work out emotional variables for the configurations
            def config1AvailableEmotionalVars = EmotionalVariable.list();
            def config2AvailableEmotionalVars = EmotionalVariable.list();
            def config3AvailableEmotionalVars = EmotionalVariable.list();
            
            if ( frameInstance.config1.emotionalVariables ) {
                for(EmotionalVariable anEmo: frameInstance.config1.emotionalVariables)
                    config1AvailableEmotionalVars.remove(anEmo);
            }
            if ( frameInstance.config2.emotionalVariables ) {
                for(EmotionalVariable anEmo: frameInstance.config2.emotionalVariables)
                    config2AvailableEmotionalVars.remove(anEmo);
            }
            if ( frameInstance.config3.emotionalVariables ) {
                for(EmotionalVariable anEmo: frameInstance.config3.emotionalVariables)
                    config3AvailableEmotionalVars.remove(anEmo);
            }

            // Work out left temple possible values
            
            // Finishing aspect
            def availableLeftTempleFinishingAspects = new HashSet<FinishingAspect>();
            if ( frameInstance.leftTempleFinishingAspect == FinishingAspect.BOTH ) {
                availableLeftTempleFinishingAspects.add(FinishingAspect.MATT);
                availableLeftTempleFinishingAspects.add(FinishingAspect.BRILLIANT);
            } else
                availableLeftTempleFinishingAspects.add(frameInstance.leftTempleFinishingAspect);
            
            def availableFrontalFinishingAspects = new HashSet<FinishingAspect>();
            if ( frameInstance.finishingAspect == FinishingAspect.BOTH ) {
                availableFrontalFinishingAspects.add(FinishingAspect.MATT);
                availableFrontalFinishingAspects.add(FinishingAspect.BRILLIANT);
            } else
                availableFrontalFinishingAspects.add(frameInstance.leftTempleFinishingAspect);
            
            def availableRightTempleFinishingAspects = new HashSet<FinishingAspect>();
            if ( frameInstance.rightTempleFinishingAspect == FinishingAspect.BOTH ) {
                availableRightTempleFinishingAspects.add(FinishingAspect.MATT);
                availableRightTempleFinishingAspects.add(FinishingAspect.BRILLIANT);
            } else
                availableRightTempleFinishingAspects.add(frameInstance.leftTempleFinishingAspect);
                
                        
            
            [config1: frameInstance.config1, config2: frameInstance.config2, config3: frameInstance.config3, config1AvailableEmotionalVars: config1AvailableEmotionalVars,
                config2AvailableEmotionalVars: config2AvailableEmotionalVars, config3AvailableEmotionalVars: config3AvailableEmotionalVars,
                frameId: params.id, 
                availableLeftTempleFinishingAspects: availableLeftTempleFinishingAspects, availableLeftTempleTextures: frameInstance.leftTempleTexture, availableLeftTempleBaseColours: frameInstance.leftTempleBaseColours,
                availableFrontalFinishingAspects: availableFrontalFinishingAspects, availableFrontalTextures: frameInstance.frontalTexture, availableFrontalBaseColours: frameInstance.frontalBaseColours,
                availableRightTempleFinishingAspects: availableRightTempleFinishingAspects, availableRightTempleTextures: frameInstance.rightTempleTexture, availableRightTempleBaseColours: frameInstance.rightTempleBaseColours]
        }
    }

    def update = {
        
        def frameInstance = Frame.get(params.id);
        if ( !frameInstance ) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.label', default: 'Frame'), params.id])}"
            redirect(controller: "FrameCatalogue", action: "show", params: params) // TODO - where to go.. - test...
        } else {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( frameInstance.version > version ) {
                    frameInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'frame.label', default: 'Frame')] as Object[], message(code: 'frame.update.conflict', default: 'Another user has updated this frame while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            
            // Now fill out the different configuration information
            
            // General, simple properties
            frameInstance.config1.properties = params.config1;
            frameInstance.config2.properties = params.config2;
            frameInstance.config3.properties = params.config3;
            
            
            // Emotional variables
            frameInstance.config1.emotionalVariables = handleEmoVars(frameInstance.config1.emotionalVariables, params.config1AllNewEmoVars, params.config1AllDeleteEmoVars);
            frameInstance.config2.emotionalVariables = handleEmoVars(frameInstance.config2.emotionalVariables, params.config2AllNewEmoVars, params.config2AllDeleteEmoVars);
            frameInstance.config3.emotionalVariables = handleEmoVars(frameInstance.config3.emotionalVariables, params.config3AllNewEmoVars, params.config3AllDeleteEmoVars);
            
            // Textures
            def conf1LeftTexture = FrameTexture.get(params.config1LeftTempleTexture);
            frameInstance.config1.leftTempleTexture = conf1LeftTexture;
            def conf2LeftTexture = FrameTexture.get(params.config2LeftTempleTexture);
            frameInstance.config2.leftTempleTexture = conf2LeftTexture;
            def conf3LeftTexture = FrameTexture.get(params.config3LeftTempleTexture);
            frameInstance.config3.leftTempleTexture = conf3LeftTexture;

            def conf1RightTexture = FrameTexture.get(params.config1RightTempleTexture);
            frameInstance.config1.rightTempleTexture = conf1RightTexture;
            def conf2RightTexture = FrameTexture.get(params.config2RightTempleTexture);
            frameInstance.config2.rightTempleTexture = conf2RightTexture;
            def conf3RightTexture = FrameTexture.get(params.config3RightTempleTexture);
            frameInstance.config3.rightTempleTexture = conf3RightTexture;

            def conf1FrontalTexture = FrameTexture.get(params.config1FrontalTexture);
            frameInstance.config1.frontalTexture = conf1FrontalTexture;
            def conf2FrontalTexture = FrameTexture.get(params.config2FrontalTexture);
            frameInstance.config2.frontalTexture = conf2FrontalTexture;
            def conf3FrontalTexture = FrameTexture.get(params.config3FrontalTexture);
            frameInstance.config3.frontalTexture = conf3FrontalTexture;
            
            // Base colours
            def conf1LeftColour = FrameBaseColour.get(params.config1LeftTempleBaseColour);
            frameInstance.config1.leftTempleBaseColour = conf1LeftColour;
            def conf2LeftColour = FrameBaseColour.get(params.config2LeftTempleBaseColour);
            frameInstance.config2.leftTempleBaseColour = conf2LeftColour;
            def conf3LeftColour = FrameBaseColour.get(params.config3LeftTempleBaseColour);
            frameInstance.config3.leftTempleBaseColour = conf3LeftColour;

            def conf1RightColour = FrameBaseColour.get(params.config1RightTempleBaseColour);
            frameInstance.config1.rightTempleBaseColour = conf1RightColour;
            def conf2RightColour = FrameBaseColour.get(params.config2RightTempleBaseColour);
            frameInstance.config2.rightTempleBaseColour = conf2RightColour;
            def conf3RightColour = FrameBaseColour.get(params.config3RightTempleBaseColour);
            frameInstance.config3.rightTempleBaseColour = conf3RightColour;

            def conf1FrontalColour = FrameBaseColour.get(params.config1FrontalBaseColour);
            frameInstance.config1.frontalBaseColour = conf1FrontalColour;
            def conf2FrontalColour = FrameBaseColour.get(params.config2FrontalBaseColour);
            frameInstance.config2.frontalBaseColour = conf2FrontalColour;
            def conf3FrontalColour = FrameBaseColour.get(params.config3FrontalBaseColour);
            frameInstance.config3.frontalBaseColour = conf3FrontalColour;
            
            
            // Images
            def uploadDir = grailsApplication.config.com.k_int.made4u.frame.aesthetic.config.image.uploadDirectory
            def publicUploadDir = grailsApplication.config.com.k_int.made4u.frame.aesthetic.config.image.publicUploadDirectory

            def imageFile = request.getFile('config1Image');
            frameInstance.config1 = handleImageAttachment(frameInstance.config1, imageFile, uploadDir, publicUploadDir, frameInstance.id, params.config1ImageDelete);
            def imageFile2 = request.getFile('config2Image');
            frameInstance.config2 = handleImageAttachment(frameInstance.config2, imageFile2, uploadDir, publicUploadDir, frameInstance.id, params.config2ImageDelete);
            def imageFile3 = request.getFile('config3Image');
            frameInstance.config3 = handleImageAttachment(frameInstance.config3, imageFile3, uploadDir, publicUploadDir, frameInstance.id, params.config3ImageDelete);
            
            
            
            if ( !frameInstance.hasErrors() && frameInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'frame.label', default: 'Frame'), frameInstance.identifier])}"
                redirect(action: "show", id: frameInstance.id)
            } else {
                render(view: "edit", model: [config1: frameInstance.config1, config2: frameInstance.config2, config3: frameInstance.config3, availableEmotionalVars: EmotionalVariable.list(), frameId: params.id])
            }

        }
    }
    
    private Set<EmotionalVariable> handleEmoVars(Set<EmotionalVariable> variables, String newVarIds, String removeVarIds) {
        
        if ( newVarIds != null && !"".equals(newVarIds) ) {
            log.debug("newVarIds = " + newVarIds);
            String[] allNewVarIds = newVarIds.split("_BREAK_");
            for(String anId: allNewVarIds) {
                log.debug("anId = " + anId);
                Long realId = new Long(anId);
                EmotionalVariable newVar = EmotionalVariable.get(realId);
                
                if ( !variables.contains(newVar) )
                    variables.add(newVar);
            }
        }
        
        if ( removeVarIds != null && !"".equals(removeVarIds) ) {
            log.debug("removeVarIds = " + removeVarIds);
            String[] allRemoveVarIds = removeVarIds.split("_BREAK_");
            for(String anId: allRemoveVarIds) {
                log.debug("remove anId = " + anId);
                Long realId = new Long(anId);
                EmotionalVariable toRemove = EmotionalVariable.get(realId);
                if ( variables.contains(toRemove) ) 
                    variables.remove(toRemove);
            }
        }
        
        return variables;
    }


    def handleImageAttachment(configuration, imageFile, uploadDir, publicUploadDir, frameId, shouldDelete) {
        
        if ( shouldDelete && configuration.privateImagePath) {
            // Need to do a deletion..
            File toRemove = new File(configuration.privateImagePath);
            if ( toRemove.exists() ) 
                toRemove.delete();
                
            configuration.imagePath = null;
            configuration.privateImagePath = null;
        } else {
            if ( imageFile != null && !imageFile.empty ) {
                // We have a file - write it to disk..
                if ( uploadDir != null && !"".equals(uploadDir) && publicUploadDir != null && !"".equals(publicUploadDir) ) {

                    if ( !uploadDir.endsWith(File.separator) )
                        uploadDir = uploadDir + File.separator;
                    if ( !publicUploadDir.endsWith(File.separator) )
                        publicUploadDir = publicUploadDir + File.separator;

                    uploadDir = uploadDir + frameId;
                    publicUploadDir = publicUploadDir + frameId;

                    // Make the upload directory
                    new File(uploadDir).mkdirs();

                    // Now write the file out to the directory
                    imageFile.transferTo( new File ( uploadDir, imageFile.originalFilename))
                    def privatePath = uploadDir + File.separator + imageFile.originalFilename;
                    def path = publicUploadDir + File.separator + imageFile.originalFilename;
                    configuration.imagePath = path;
                    configuration.privateImagePath = privatePath;
                }
            }
        }
        
        return configuration;
    }
}
