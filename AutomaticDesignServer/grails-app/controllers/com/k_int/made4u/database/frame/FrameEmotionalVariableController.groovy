package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.EmotionalVariable;
import org.springframework.dao.DataIntegrityViolationException

class FrameEmotionalVariableController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of emotional variables.. count: " + EmotionalVariable.count());

        [emotionalVariableList: EmotionalVariable.list(params), emotionalVariableTotal: EmotionalVariable.count()]

    }

    def show = {
        def emotionalInstance = EmotionalVariable.get(params.id)
        if (!emotionalInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'emotional.variable.label', default: 'Emotional variable'), params.id])}"
            redirect(action: "list")
        }
        else {
            [emotionalInstance: emotionalInstance]
        }

    }

    def create = {
        def emotionalInstance = new EmotionalVariable();
        emotionalInstance.properties = params;
        return [emotionalInstance: emotionalInstance]
    }

    def save = {
        def emotionalInstance = new EmotionalVariable();
        emotionalInstance.properties = params;
        if ( emotionalInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'emotional.variable.label', default: 'Emotional variable'), emotionalInstance.name])}"
            redirect(action: "show", id: emotionalInstance.id)
        } else {
            render(view: "create", emotionalInstance: emotionalInstance)
        }
    }

    def edit = {
        def emotionalInstance = EmotionalVariable.get(params.id)
        if ( !emotionalInstance ) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'emotional.variable.label', default: 'Emotional variable'), params.id])}"
            redirect(action: "list")
        } else {
            return [emotionalInstance: emotionalInstance]
        }
    }

    def update = {
        def emotionalInstance = EmotionalVariable.get(params.id)
        if ( emotionalInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( emotionalInstance.version > version ) {
                    emotionalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'emotional.variable.label', default: 'Emotional variable')] as Object[], message(code: 'emotional.variable.update.conflict', default: 'Another user has updated this emotional variable while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }
            emotionalInstance.properties = params
            if ( !emotionalInstance.hasErrors() && emotionalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'emotional.variable.label', default: 'Emotional variable'), emotionalInstance.name])}"
                redirect(action: "show", id: emotionalInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, emotionalInstance: emotionalInstance])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'emotional.variable.label', default: 'Emotional variable'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def emotionalInstance = EmotionalVariable.get(params.id)
        if ( emotionalInstance ) {
            // We have something to delete - do so
            try {
                emotionalInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'emotional.variable.label', default: 'Emotional Variable'), emotionalInstance.name])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();
                
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'emotional.variable.label', default: 'Emotional variable'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'emotional.variable.label', default: 'Emotional variable'), params.id])}"
            redirect(action: "list")
        }
    }


}
