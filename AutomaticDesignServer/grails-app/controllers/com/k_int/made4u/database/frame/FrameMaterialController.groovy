package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.Material;
import org.springframework.dao.DataIntegrityViolationException;

class FrameMaterialController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of materials.. count: " + Material.count());

        [frameMaterialList: Material.list(params), frameMaterialTotal: Material.count()]

    }

    def show = {
        def materialInstance = Material.get(params.id)
        if (!materialInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'material.label', default: 'Material'), params.id])}"
            redirect(action: "list")
        }
        else {
            [materialInstance: materialInstance]
        }

    }

    def create = {
        def materialInstance = new Material();
        materialInstance.properties = params;
        return [materialInstance: materialInstance]
    }

    def save = {
        def materialInstance = new Material();
        materialInstance.properties = params;
        if ( materialInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'material.label', default: 'Material'), materialInstance.name])}"
            redirect(action: "show", id: materialInstance.id)
        } else {
            render(view: "create", materialInstance: materialInstance)
        }
    }

    def edit = {
        def materialInstance = Material.get(params.id)
        if ( !materialInstance ) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'material.label', default: 'Material'), params.id])}"
            redirect(action: "list")
        } else {
            return [materialInstance: materialInstance]
        }
    }

    def update = {
        def materialInstance = Material.get(params.id)
        if ( materialInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( materialInstance.version > version ) {
                    materialInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'material.label', default: 'Material')] as Object[], message(code: 'material.update.conflict', default: 'Another user has updated this material while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            materialInstance.properties = params
            if ( !materialInstance.hasErrors() && materialInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'material.label', default: 'Material'), materialInstance.name])}"
                redirect(action: "show", id: materialInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, materialInstance: materialInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'material.label', default: 'Material'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def frameMaterialInstance = FrameMaterial.get(params.id)
        if ( frameMaterialInstance ) {
            // We have something to delete - do so
            try {
                frameMaterialInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'material.label', default: 'Material'), frameMaterialInstance.name])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();

                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'material.label', default: 'Material'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'material.label', default: 'Material'), params.id])}"
            redirect(action: "list")
        }
    }

}
