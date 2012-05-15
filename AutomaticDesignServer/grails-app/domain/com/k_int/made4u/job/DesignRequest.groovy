package com.k_int.made4u.job

/**
 * A domain class to represent design requests and the associated orders
 * @author rpb
 */
class DesignRequest {
    
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
    Date            postProcessingStartTime;
    Date            postProcessingEndTime;
    Date            responseGivenTime;
    Integer         numOfFramesFromFrameCalc;
    Integer         numOfFramesFromFrameCalcInCatalogue;
    Integer         numOfFramesAfterFrameCalcFilter;
    Integer         numOfInputsToLensCalc;
    Integer         numOfResultsFromLensCalc;
    Integer         numOfViableCombinations;
    DesignStatus    requestStatus = DesignStatus.REQUEST_SUBMITTED;
    Set<String>     statusMessages = new LinkedHashSet<String>();
    Set<String>     frameValidationMessages = new LinkedHashSet<String>();
    Set<String>     lensValidationMessages = new LinkedHashSet<String>();
    Set<String>     frameLensValidationMessages = new LinkedHashSet<String>();
    
    
    static hasMany = [statusMessages:String, frameValidationMessages: String, frameLensValidationMessages: String, lensValidationMessages: String]
    
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
        postProcessingStartTime         (nullable: true)
        postProcessingEndTime           (nullable: true)
        responseGivenTime               (nullable: true)
        numOfFramesFromFrameCalc        (nullable: true)
        numOfFramesFromFrameCalcInCatalogue (nullable: true)
        numOfFramesAfterFrameCalcFilter (nullable: true)
        numOfInputsToLensCalc           (nullable: true)
        numOfResultsFromLensCalc        (nullable: true)
        numOfViableCombinations         (nullable: true)
    }

    static mapping = {
        table 'M4U_DESIGN_REQUEST'
    }
    
	
}

