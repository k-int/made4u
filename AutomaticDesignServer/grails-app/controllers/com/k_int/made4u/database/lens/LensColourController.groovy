package com.k_int.made4u.database.lens

import com.k_int.made4u.lens.LensColour
import com.k_int.made4u.lens.LensColourType;

import org.springframework.dao.DataIntegrityViolationException;

class LensColourController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [lensColourList: LensColour.list(params), lensColourTotal: LensColour.count()]

    }

    def show = {
        def colourInstance = LensColour.get(params.id)
        if (!colourInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lens.colour.label', default: 'Lens colour'), params.id])}"
            redirect(action: "list")
        }
        else {
            [colourInstance: colourInstance]
        }

    }

    def create = {
        def colourInstance = new LensColour()
        colourInstance.properties = params;
        
        def possibleColourTypes = LensColourType.values();
        
        [colourInstance: colourInstance, availableColourTypes: possibleColourTypes]
    }

    def save = {
        def colourInstance = new LensColour();
        
        colourInstance.properties = params;

        if ( colourInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'lens.colour.label', default: 'Lens colour'), colourInstance.name])}"
            redirect(action: "show", id: colourInstance.id);
        } else {
            render(view: "create", model: [colourInstance: colourInstance, availableColourTypes: LensColourType.values()]);
        }
    }

    def edit = {
        def colourInstance = LensColour.get(params.id)
        if (!colourInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lens.colour.label', default: 'Lens colour'), params.id])}"
            redirect(action: "list")
        }
        else {
            // We have the coating - now go and get other available values to be chosen from
            def possibleColourTypes = LensColourType.values()

            [colourInstance: colourInstance, availableColourTypes: possibleColourTypes]
        }
    }

    def update = {
        def colourInstance = LensColour.get(params.id)
        if ( colourInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( colourInstance.version > version ) {
                    colourInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'lens.colour.label', default: 'Lens colour')] as Object[], message(code: 'lens.colour.update.conflict', default: 'Another user has updated this lens colour while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }

            colourInstance.properties = params
            if ( !colourInstance.hasErrors() && colourInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'lens.colour.label', default: 'Lens colour'), colourInstance.name])}"
                redirect(action: "show", id: colourInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, colourInstance: colourInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'lens.colour.label', default: 'Lens colour'), params.id])}"
            redirect(action: "list")
        }
    }


    def delete = {
        def colourInstance = LensColour.get(params.id)
        if ( colourInstance ) {
            // We have something to delete - do so
            try {
                colourInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'lens.colour.label', default: 'Lens colour'), colourInstance.name])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();

                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'lens.colour.label', default: 'Lens colour'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'lens.colour.label', default: 'Lens colour'), params.id])}"
            redirect(action: "list")
        }
    }


}
