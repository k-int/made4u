package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.StandardComponent;
import com.k_int.made4u.frame.StandardType;
import org.springframework.dao.DataIntegrityViolationException

class FrameStandardComponentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of standard components.. count: " + StandardComponent.count());

        [standardComponentList: StandardComponent.list(params), standardComponentTotal: StandardComponent.count()]

    }

    def show = {
        def standardInstance = StandardComponent.get(params.id)
        if (!standardInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'standard.component.label', default: 'Standard component'), params.id])}"
            redirect(action: "list")
        }
        else {
            [standardInstance: standardInstance]
        }

    }

    def create = {
        def standardInstance = new StandardComponent();
        standardInstance.properties = params;
        return [standardInstance: standardInstance, standardTypes: StandardType.values()]
    }

    def save = {
        def standardInstance = new StandardComponent();
        standardInstance.properties = params;
        if ( standardInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'standard.component.label', default: 'Standard component'), standardInstance.reference])}"
            redirect(action: "show", id: standardInstance.id)
        } else {
            render(view: "create", standardInstance: standardInstance)
        }
    }

    def edit = {
        def standardInstance = StandardComponent.get(params.id)
        if ( !standardInstance ) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'standard.component.label', default: 'Standard component'), params.id])}"
            redirect(action: "list")
        } else {
            return [standardInstance: standardInstance, standardTypes: StandardType.values()]
        }
    }

    def update = {
        def standardInstance = StandardComponent.get(params.id)
        if ( standardInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( standardInstance.version > version ) {
                    standardInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'stasndard.component.label', default: 'Standard component')] as Object[], message(code: 'standard.component.update.conflict', default: 'Another user has updated this standard component while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            standardInstance.properties = params
            if ( !standardInstance.hasErrors() && standardInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'standard.component.label', default: 'Standard component'), standardInstance.reference])}"
                redirect(action: "show", id: standardInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, standardInstance: standardInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'standard.component.label', default: 'Standard component'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def standardInstance = StandardComponent.get(params.id)
        if ( standardInstance ) {
            // We have something to delete - do so
            try {
                standardInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'standard.component.label', default: 'Standard component'), standardInstance.reference])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();
                
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'standard.component.label', default: 'Standard component'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'standard.component.label', default: 'Standard component'), params.id])}"
            redirect(action: "list")
        }
    }


}
