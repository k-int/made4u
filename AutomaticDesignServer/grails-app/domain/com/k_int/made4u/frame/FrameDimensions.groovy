package com.k_int.made4u.frame

/**
 * Frame dimensions
 * @author rpb rich@k-int.com
 * @version 1.0 06.07.11
 */
class FrameDimensions {

    
        Float           boxingHorizontalStandard = 0.0f;
        Float           boxingHorizontalMin = 0.0f;
        Float           boxingHorizontalMax = 0.0f;
        Float           boxingVerticalStandard = 0.0f;
        Float           boxingVerticalMin = 0.0f;
        Float           boxingVerticalMax = 0.0f;
        Float           pantoscopicAngleLeftStandard = 0.0f;
        Float           pantoscopicAngleLeftMin = 0.0f;
        Float           pantoscopicAngleLeftMax = 0.0f;
        Float           pantoscopicAngleRightStandard = 0.0f;
        Float           pantoscopicAngleRightMin = 0.0f;
        Float           pantoscopicAngleRightMax = 0.0f;
        Float           bridgeWidthStandard = 0.0f;
        Float           bridgeWidthMin = 0.0f;
        Float           bridgeWidthMax = 0.0f;
        Float           bridgeHeightStandard = 0.0f;
        Float           bridgeHeightMin = 0.0f;
        Float           bridgeHeightMax = 0.0f;
        Float           heelWidthStandard = 0.0f;
        Float           heelWidthMin      = 0.0f;
        Float           heelWidthMax      = 0.0f;
        Float           internalRimReductionStandard = 0.0f;
        Float           internalRimReductionMin = 0.0f;
        Float           internalRimReductionMax = 0.0f;
        Float           baseOfFrameStandard = 0.0f;
        Float           baseOfFrameMin      = 0.0f;
        Float           baseOfFrameMax      = 0.0f;
        Float           wrapAngleStandard   = 0.0f;
        Float           wrapAngleMin        = 0.0f;
        Float           wrapAngleMax        = 0.0f;
        Float           totalLengthStandard = 0.0f;

        FrameFrontalTotalDimensions totalLength = new FrameFrontalTotalDimensions();
        
    static mapping = {
        table 'M4U_FRAME_DIMENSIONS'
    }
        
}

