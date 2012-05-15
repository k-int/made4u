package com.k_int.made4u.job

/**
 * A domain class to represent order requests and the associated information
 * up until the point an order is submitted onto the web control system
 * @author rpb rich@k-int.com
 * @version 1.0 04.10.11
 */
class OrderRequest {
    
    String          uuid;
    String          orderId;
    Date            orderRequestSubmittedTime;
    Date            inputParsingStartTime;
    Date            inputParsingEndTime;
    Date            framesCalcSubmissionTime;
    Date            framesCalcSubmissionEndTime;
    Date            framesCalcMarkedAsCompletedTime;
    Date            framesCalcPostProcessingStartTime;
    Date            framesCalcPostProcessingEndTime;
    Date            lensCalcInputCreationStartTime;
    Date            lensCalcInputCreationEndTime;
    Date            lensCalcSubmissionTime;
    Date            lensCalcReturnTime;
    Date            postProcessingStartTime;
    Date            postProcessingEndTime;
    Date            coatingCreationStartTime;
    Date            coatingCreationEndTime;
    Date            wcsInputCreationStartTime;
    Date            wcsInputCreationEndTime;
    Date            wcsSubmissionTime;
    String          stlLocation;
    

    OrderStatus     requestStatus = OrderStatus.REQUEST_SUBMITTED;
    Set<String>     statusMessages = new LinkedHashSet<String>();
    
    Long            linkedDesignId;
    
    static hasMany = [statusMessages:String]
    
    static constraints = {
        uuid                            (blank: false, nullable: false)
        orderId                         (blank: false, nullable: false)
        orderRequestSubmittedTime       (nullable: true)
        inputParsingStartTime           (nullable: true)
        inputParsingEndTime             (nullable: true)
        framesCalcSubmissionTime        (nullable: true)
        framesCalcSubmissionEndTime     (nullable: true)
        framesCalcMarkedAsCompletedTime (nullable: true)
        framesCalcPostProcessingStartTime (nullable: true)
        framesCalcPostProcessingEndTime (nullable: true)
        lensCalcInputCreationStartTime  (nullable: true)
        lensCalcInputCreationEndTime    (nullable: true)
        lensCalcSubmissionTime          (nullable: true)
        lensCalcReturnTime              (nullable: true)
        postProcessingStartTime         (nullable: true)
        postProcessingEndTime           (nullable: true)
        coatingCreationStartTime        (nullable: true)
        coatingCreationEndTime          (nullable: true)
        wcsInputCreationStartTime       (nullable: true)
        wcsInputCreationEndTime         (nullable: true)
        wcsSubmissionTime               (nullable: true)
        
        linkedDesignId                    (nullable: false)
        stlLocation                     (nullable: true)
        
    }

    static mapping = {
        table 'M4U_ORDER_REQUEST'
    }
    
	
}

