package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.FrameShape;
import org.springframework.dao.DataIntegrityViolationException;


class FrameShapeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of frame shapes.. count: " + FrameShape.count())

        [frameShapeList: FrameShape.list(params), frameShapeTotal: FrameShape.count()]

    }

    def show = {
        def shapeInstance = FrameShape.get(params.id)
        if (!shapeInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'shape.label', default: 'Frame shape'), params.id])}"
            redirect(action: "list")
        }
        else {
            [shapeInstance: shapeInstance]
        }

    }

    def create = {
        def shapeInstance = new FrameShape();
        shapeInstance.properties = params;
        return [shapeInstance: shapeInstance]
    }

    def save = {
        def shapeInstance = new FrameShape();
        shapeInstance.properties = params;
        if ( shapeInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'shape.label', default: 'Frame shape'), shapeInstance.name])}"
            redirect(action: "show", id: shapeInstance.id)
        } else {
            render(view: "create", shapeInstance: shapeInstance)
        }
    }

    def edit = {
        def shapeInstance = FrameShape.get(params.id)
        if ( !shapeInstance ) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'shape.label', default: 'Frame shape'), params.id])}"
            redirect(action: "list")
        } else {
            return [shapeInstance: shapeInstance]
        }
    }

    def update = {
        def shapeInstance = FrameShape.get(params.id)
        if ( shapeInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( shapeInstance.version > version ) {
                    shapeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'shape.label', default: 'Frame shape')] as Object[], message(code: 'shape.update.conflict', default: 'Another user has updated this frame shape while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            shapeInstance.properties = params
            if ( !shapeInstance.hasErrors() && shapeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'shape.label', default: 'Frame shape'), shapeInstance.name])}"
                redirect(action: "show", id: shapeInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, shapeInstance: shapeInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'shape.label', default: 'Frame shape'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def shapeInstance = FrameShape.get(params.id)
        if ( shapeInstance ) {
            // We have something to delete - do so
            try {
                shapeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'shape.label', default: 'Frame shape'), shapeInstance.name])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();

                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'shape.label', default: 'Frame shape'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'shape.label', default: 'Frame shape'), params.id])}"
            redirect(action: "list")
        }
    }

}
