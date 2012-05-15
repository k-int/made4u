package com.k_int.made4u.job

/**
 * A domain class to represent design requests and the associated orders
 * @author rpb
 */
class CompleteDummyDesignRequest {
    
    String          uuid;
    String          orderId;
    Date            requestSubmittedTime;
    Date            framesCalcSubmissionTime;
    Date            framesCalcReturnTime;
    Date            frameRefinementStartTime;
    Date            frameRefinementEndTime;
    Date            lensRefinementStartTime;
    Date            lensRefinementEndTime;
    Date            lensCalcInputCreationStartTime;
    Date            lensCalcInputCreationEndTime;
    Date            lensCalcSubmissionTime;
    Date            lensCalcReturnTime;
    Date            responseGivenTime;
    CompleteDummyDesignStatus    requestStatus = CompleteDummyDesignStatus.REQUEST_SUBMITTED;
    Set<String>     statusMessages = new LinkedHashSet<String>();
    
    
    static constraints = {
        uuid                            (blank: false, nullable: false, unique: true)
        orderId                         (blank: false, nullable: false, unique: true)
        requestSubmittedTime            (nullable: true)
        framesCalcSubmissionTime        (nullable: true)
        framesCalcReturnTime            (nullable: true)
        frameRefinementStartTime        (nullable: true)
        frameRefinementEndTime          (nullable: true)
        lensRefinementStartTime         (nullable: true)
        lensRefinementEndTime           (nullable: true)
        lensCalcInputCreationStartTime  (nullable: true)
        lensCalcInputCreationEndTime    (nullable: true)
        lensCalcSubmissionTime          (nullable: true)
        lensCalcReturnTime              (nullable: true)
        responseGivenTime               (nullable: true);
    }

    static mapping = {
        table 'M4U_DUMMY_DESIGN_REQUEST'
    }
    
	
}

