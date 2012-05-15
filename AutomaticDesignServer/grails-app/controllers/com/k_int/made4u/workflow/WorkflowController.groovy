package com.k_int.made4u.workflow

import com.k_int.made4u.workflow.WorkflowType;

class WorkflowController {

    def static workflows = [[WorkflowType.DESIGN_REQUEST,"fbm.png"],[WorkflowType.MANUFACTURING_ORDER,"mo.png"]]
    
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        
        return [workflows: workflows];
        
    }
    
    
    def show = {

        def workflowIndex = params.id;
        int workflowNum = Integer.parseInt(workflowIndex);
        log.debug("workflowIndex: " + workflowIndex + " workflowNum = " + workflowNum + " params.id = " + params.id);

        def toReturn = workflows[workflowNum];
        
        log.debug("About to return: " + toReturn);
        return [workflow: toReturn];
    }

}
