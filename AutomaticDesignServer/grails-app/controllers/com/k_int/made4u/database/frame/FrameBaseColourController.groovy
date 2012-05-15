package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.FrameBaseColour;
import org.springframework.dao.DataIntegrityViolationException

class FrameBaseColourController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of frame base colours variables.. count: " + FrameBaseColour.count());

        [baseColourList: FrameBaseColour.list(params), baseColourListTotal: FrameBaseColour.count()]

    }

    def show = {
        def colourInstance = FrameBaseColour.get(params.id)
        if (!colourInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.base.colour.label', default: 'Frame base colour'), params.id])}"
            redirect(action: "list")
        }
        else {
            [colourInstance: colourInstance]
        }

    }

    def create = {
        def colourInstance = new FrameBaseColour();
        colourInstance.properties = params;
        return [colourInstance: colourInstance]
    }

    def save = {
        def colourInstance = new FrameBaseColour();
        colourInstance.properties = params;
        if ( colourInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'frame.base.colour.label', default: 'Frame base colour'), colourInstance.name])}"
            redirect(action: "show", id: colourInstance.id)
        } else {
            render(view: "create", colourInstance: colourInstance)
        }
    }

    def edit = {
        def colourInstance = FrameBaseColour.get(params.id)
        if ( !colourInstance ) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'frame.base.colour.label', default: 'Frame base colour'), params.id])}"
            redirect(action: "list")
        } else {
            return [colourInstance: colourInstance]
        }
    }

    def update = {
        def colourInstance = FrameBaseColour.get(params.id)
        if ( colourInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( colourInstance.version > version ) {
                    colourInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'frame.base.colour.label', default: 'Frame base colour')] as Object[], message(code: 'frame.base.colour.update.conflict', default: 'Another user has updated this base colour while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            colourInstance.properties = params
            if ( !colourInstance.hasErrors() && colourInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'frame.base.colour.label', default: 'Frame base colour'), colourInstance.name])}"
                redirect(action: "show", id: colourInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, colourInstance: colourInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.base.colour.label', default: 'Frame base colour'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def colourInstance = FrameBaseColour.get(params.id)
        if ( colourInstance ) {
            // We have something to delete - do so
            try {
                colourInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'frame.base.colour.label', default: 'Frame base colour'), colourInstance.name])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();
                
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'frame.base.colour.label', default: 'Frame base colour'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'frame.base.colour.label', default: 'Frame base colour'), params.id])}"
            redirect(action: "list")
        }
    }


}
