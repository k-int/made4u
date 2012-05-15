package com.k_int.made4u.timerData

import grails.converters.JSON
import grails.converters.XML

import com.k_int.made4u.job.TimerData;
import com.k_int.made4u.job.TimerStatus;

import com.k_int.made4u.oma.OMA;

class TimerDataController {

    static allowedMethods = [save: "POST", update: "GET", delete: "POST"]


    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("About to return with the list of all timer data information.. count: " + TimerData.count());

        def responseVal = [timers: TimerData.list(params), totalNum : TimerData.count()]
        
        withFormat {
            html responseVal
            xml { render responseVal as XML }
            json { render responseVal as JSON }
        }
    }
    
    def create = {
        // Don't need to do anything
    }
    
    def show = {
        def timer = TimerData.get(params.id);
        
        if ( !timer ) {
            // No timer with the specified id (or no id given)
            def errorMessage = message(code: 'retrieve.timer.non.existent.id.error.message', default: 'The specified id does not relate to a timer in the system. Unable to return a timer');
            response.sendError(404, errorMessage);
        } else {
            
            def responseVal = [id: params.id, timer: timer]
            
            
            log.debug("responseVal = " + responseVal);

            withFormat {
                html responseVal
                xml { render responseVal as XML }
                json { render responseVal as JSON }
            }
        }
    }
    
    def update = {
        def timer = TimerData.get(params.id);
        
        if ( !timer ) {
            // No timer with the specified id (or no id given)
            def errorMessage = message(code: 'retrieve.timer.non.existent.id.error.message', default: 'The specified id does not relate to a timer in the system. Unable to return a timer');
            response.sendError(404, errorMessage);
        } else {
            
            if ( params.status ) {
                TimerStatus newStatus = TimerStatus.parseFromString(params.status);
                
                if ( newStatus ) {
                    timer.timerStatus = newStatus;
                    timer.save(flush:true);
                }
                
            }

            flash.message = "${message(code: 'timer.status.updated.message', args: [message(code: 'timer.data.label', default: 'Timer data'), timer.id])}"
            redirect(action: "show", id: timer.id)
            
        }
        
        
    }
    
}
