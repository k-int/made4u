package com.k_int.made4u.database.lens

import com.k_int.made4u.lens.LensTopcoat;
import org.springframework.dao.DataIntegrityViolationException

class LensTopcoatController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of lens top coats.. count: " + LensTopcoat.count());

        [lensTopcoatList: LensTopcoat.list(params), lensTopcoatTotal: LensTopcoat.count()]

    }

    def show = {
        def topcoatInstance = LensTopcoat.get(params.id)
        if (!topcoatInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'lens.topcoat.label', default: 'Lens topcoat'), params.id])}"
            redirect(action: "list")
        }
        else {
            [topcoatInstance: topcoatInstance]
        }

    }

    def create = {
        def topcoatInstance = new LensTopcoat();
        topcoatInstance.properties = params;
        return [topcoatInstance: topcoatInstance]
    }

    def save = {
        def topcoatInstance = new LensTopcoat();
        topcoatInstance.properties = params;
        if ( topcoatInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'lens.topcoat.label', default: 'Lens topcoat'), topcoatInstance.name])}"
            redirect(action: "show", id: topcoatInstance.id)
        } else {
            render(view: "create", topcoatInstance: topcoatInstance)
        }
    }

    def edit = {
        def topcoatInstance = LensTopcoat.get(params.id)
        if ( !topcoatInstance ) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lens.topcoat.label', default: 'Lens topcoat'), params.id])}"
            redirect(action: "list")
        } else {
            return [topcoatInstance: topcoatInstance]
        }
    }

    def update = {
        def topcoatInstance = LensTopcoat.get(params.id)
        if ( topcoatInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( topcoatInstance.version > version ) {
                    topcoatInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'lens.topcoat.label', default: 'Lens topcoat')] as Object[], message(code: 'lens.topcoat.update.conflict', default: 'Another user has updated this lens topcoat while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            topcoatInstance.properties = params
            if ( !topcoatInstance.hasErrors() && topcoatInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'lens.topcoat.label', default: 'Lens topcoat'), topcoatInstance.name])}"
                redirect(action: "show", id: topcoatInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, topcoatInstance: topcoatInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'lens.topcoat.label', default: 'Lens topcoat'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def topcoatInstance = LensTopcoat.get(params.id)
        if ( topcoatInstance ) {
            // We have something to delete - do so
            try {
                topcoatInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'lens.topcoat.label', default: 'Lens topcoat'), topcoatInstance.name])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();
                
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'lens.topcoat.label', default: 'Lens topcoat'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'lens.topcoat.label', default: 'Lens topcoat'), params.id])}"
            redirect(action: "list")
        }
    }


}
