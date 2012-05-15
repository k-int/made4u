package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.FrameImage;
import com.k_int.made4u.frame.FrameImageType;
import com.k_int.made4u.frame.Frame;
import com.k_int.made4u.frame.FrameAestheticConfiguration;
import com.k_int.made4u.frame.FrameTexture;
import org.springframework.dao.DataIntegrityViolationException;


class FrameImageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of frame images.. count: " + FrameImage.count());

        [frameImageList: FrameImage.list(params), frameImageTotal: FrameImage.count()]

    }

    def show = {
        def frameImageInstance = FrameImage.get(params.id)
        if (!frameImageInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.image.label', default: 'Frame image'), params.id])}"
            redirect(action: "list")
        }
        else {
            [frameImageInstance: frameImageInstance]
        }

    }

    def create = {
        def frameImageInstance = new FrameImage();
        frameImageInstance.properties = params;
        def availableImageTypes = FrameImageType.values()
        return [frameImageInstance: frameImageInstance, availableImageTypes: availableImageTypes]
    }

    def save = {
        def frameImageInstance = new FrameImage();
        frameImageInstance.properties = params;

        def imageFile = request.getFile('imageFile');
        def availableImageTypes = FrameImageType.values()

        if ( imageFile != null && !imageFile.empty ) {
            // We have a file - write it to disk..

            def uploadDir = grailsApplication.config.com.k_int.made4u.frame.image.uploadDirectory
            def publicUploadDir = grailsApplication.config.com.k_int.made4u.frame.image.publicUploadDirectory
            
            if ( uploadDir != null && !"".equals(uploadDir) && publicUploadDir != null && !"".equals(publicUploadDir) ) {

                if ( !uploadDir.endsWith(File.separator) )
                    uploadDir = uploadDir + File.separator;
                if ( !publicUploadDir.endsWith(File.separator) )
                    publicUploadDir = publicUploadDir + File.separator;

                def timestamp = System.currentTimeMillis();
                uploadDir = uploadDir + timestamp;
                publicUploadDir = publicUploadDir + timestamp;

                // Make the upload directory
                log.error("uploadDir = " + uploadDir)
                new File(uploadDir).mkdirs();

                // Now write the file out to the directory
                imageFile.transferTo( new File ( uploadDir, imageFile.originalFilename))
                def path = publicUploadDir + File.separator + imageFile.originalFilename;
                frameImageInstance.path = path;

                if ( frameImageInstance.save(flush: true) ) {
                    flash.message = "${message(code: 'default.created.message', args: [message(code: 'frame.image.label', default: 'Frame image'), frameImageInstance.reference])}"
                    redirect(action: "show", id: frameImageInstance.id)
                } else {
                    render(view: "create", model: [frameImageInstance: frameImageInstance, availableImageTypes: availableImageTypes])
                }
            } else {
                // No upload directory configuration!
                log.error("No upload directory specified - unable to upload the file")
                frameImageInstance.errors.rejectValue("imageFile", "frame.image.file.upload.dir.not.specified", message('frame.image.file.upload.dir.not.specified', default: 'No configuration specified for image file upload - please check configuration'))
                render(view: "create", model: [frameImageInstance: frameImageInstance, availableImageTypes: availableImageTypes])
            }
        } else {
            // No file - return to the create screen
            frameImageInstance.errors.rejectValue("imageFile", "frame.image.file.not.specified", message('frame.image.file.not.specified', default: 'A non-empty image file must be specified'))
            render(view: "create", model: [frameImageInstance: frameImageInstance, availableImageTypes: availableImageTypes])
        }
    }

    def edit = {
        def frameImageInstance = FrameImage.get(params.id)
        if ( !frameImageInstance ) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'frame.image.label', default: 'Frame image'), params.id])}"
            redirect(action: "list")
        } else {
            def availableImageTypes = FrameImageType.values();

            return [frameImageInstance: frameImageInstance, availableImageTypes: availableImageTypes]
        }
    }

    def update = {
        def frameImageInstance = FrameImage.get(params.id)
        if ( frameImageInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( frameImageInstance.version > version ) {
                    frameImageInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'frame.image.label', default: 'Frame image')] as Object[], message(code: 'frame.image.update.conflict', default: 'Another user has updated this frame image while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            frameImageInstance.properties = params
            if ( !frameImageInstance.hasErrors() && frameImageInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'frame.image.label', default: 'Frame image'), frameImageInstance.reference])}"
                redirect(action: "show", id: frameImageInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, frameImageInstance: frameImageInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.image.label', default: 'Frame image'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def frameImageInstance = FrameImage.get(params.id)
        if ( frameImageInstance ) {
            // We have something to delete - do so
            
            // First remove any textures that refer to this image..
            def frameTextures = FrameTexture.findAllByImage(frameImageInstance);
            for ( FrameTexture textureInstance: frameTextures) {
               
                // Find all references to this texture and remove them from the frames
                def allFrames = Frame.list();
                
                for(Frame aFrame: allFrames) {
                    
                    def removed = false;
                    
                    if ( aFrame.frontalTexture.contains(textureInstance) ) {
                        aFrame.frontalTexture.remove(textureInstance);
                        removed = true;
                    }
                    if ( aFrame.leftTempleTexture.contains(textureInstance) ) {
                        aFrame.leftTempleTexture.remove(textureInstance);
                        removed = true;
                    }
                    if ( aFrame.rightTempleTexture.contains(textureInstance) ) {
                        aFrame.rightTempleTexture.remove(textureInstance);
                        removed = true;
                    }
                    
                    if ( removed )
                        aFrame.save(flush: true);
                        
                }
                
                // Now find all references to the texture in any aesthetic configurations
                def aestheticConfigFront = FrameAestheticConfiguration.findAllByFrontalTexture(textureInstance);
                def aestheticConfigLeft = FrameAestheticConfiguration.findAllByLeftTempleTexture(textureInstance);
                def aestheticConfigRight = FrameAestheticConfiguration.findAllByRightTempleTexture(textureInstance);
                
                for(FrameAestheticConfiguration aConfig: aestheticConfigFront) {
                    aConfig.frontalTexture = null;
                    aConfig.save(flush: true);
                }
                for(FrameAestheticConfiguration aConfig: aestheticConfigLeft) {
                    aConfig.leftTempleTexture = null;
                    aConfig.save(flush: true);
                }
                for(FrameAestheticConfiguration aConfig: aestheticConfigRight) {
                    aConfig.rightTempleTexture = null;
                    aConfig.save(flush: true);
                }
                
                
                // TODO
                
                // Now delete this texture
                try {
                    textureInstance.delete(flush:true);
                } catch (DataIntegrityViolationException dive) {
                    log.error(dive.message);
                    dive.printStackTrace();
                    
                    flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'frame.image.label', default: 'Frame image'), params.id])}"
                    redirect(action: "show", id: params.id);
                }
                
            }
            
            
            // Now remove the image itself
            try {
                frameImageInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'frame.image.label', default: 'Frame image'), frameImageInstance.reference])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();

                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'frame.image.label', default: 'Frame image'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.image.label', default: 'Frame image'), params.id])}"
            redirect(action: "list")
        }
    }


}
