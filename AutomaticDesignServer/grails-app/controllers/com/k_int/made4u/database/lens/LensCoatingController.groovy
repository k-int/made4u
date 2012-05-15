package com.k_int.made4u.database.lens

import com.k_int.made4u.lens.Coating;
import com.k_int.made4u.lens.CoatingType;

import org.springframework.dao.DataIntegrityViolationException;

class LensCoatingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of lens coatings.. count: " + Coating.count());

        [lensCoatingList: Coating.list(params), lensCoatingTotal: Coating.count()]

    }

    def show = {
        def coatingInstance = Coating.get(params.id)
        if (!coatingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'coating.label', default: 'Coating'), params.id])}"
            redirect(action: "list")
        }
        else {
            [coatingInstance: coatingInstance]
        }

    }

    def create = {
        def coatingInstance = new Coating()
        coatingInstance.properties = params;
        
        def possibleCoatingTypes = CoatingType.values();
        
        [coatingInstance: coatingInstance, availableCoatingTypes: possibleCoatingTypes]
    }

    def save = {
        def coatingInstance = new Coating();
        //def coatingType = CoatingType.get(params.coatingType);
        
        coatingInstance.properties = params;

        if ( coatingInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'coating.label', default: 'Coating'), coatingInstance.reference])}"
            redirect(action: "show", id: coatingInstance.id);
        } else {
            render(view: "create", [coatingInstance: coatingInstance, availableCoatingTypes: CoatingType.values()]);
        }
    }

    def edit = {
        def coatingInstance = Coating.get(params.id)
        if (!coatingInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'coating.label', default: 'Coating'), params.id])}"
            redirect(action: "list")
        }
        else {
            // We have the coating - now go and get other available values to be chosen from
            def possibleCoatingTypes = CoatingType.values()

            [coatingInstance: coatingInstance, availableCoatingTypes: possibleCoatingTypes]
        }
    }

    def update = {
        def coatingInstance = Coating.get(params.id)
        if ( coatingInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( coatingInstance.version > version ) {
                    coatingInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'coating.label', default: 'Coating')] as Object[], message(code: 'coating.update.conflict', default: 'Another user has updated this coating while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }

            coatingInstance.properties = params
            if ( !coatingInstance.hasErrors() && coatingInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'coating.label', default: 'Coating'), coatingInstance.reference])}"
                redirect(action: "show", id: coatingInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, coatingInstance: coatingInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'coating.label', default: 'Coating'), params.id])}"
            redirect(action: "list")
        }
    }


    def delete = {
        def coatingInstance = Coating.get(params.id)
        if ( coatingInstance ) {
            // We have something to delete - do so
            try {
                coatingInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'coating.label', default: 'Coating'), coatingInstance.reference])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();

                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'coating.label', default: 'Coating'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'coating.label', default: 'Coating'), params.id])}"
            redirect(action: "list")
        }
    }


}
