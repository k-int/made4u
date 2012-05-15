package com.k_int.made4u.design

/**
 * A class to combine together frame data, lens data and lens calculation output for easy
 * access as part of the design request system
 * 
 * @author rpb rich@k-int.com
 * @version 1.0 17.11.11
 */
class DesignData {
    def frameIdentifier;
    def lensMaterialId;
    def lensMaterialType;
    def lensCalcOutputData;

    def getFrameIdentifier() {
        return frameIdentifier;
    }
        
    def getLensMaterialId() {
        return lensMaterialId;
    }

    def getLensMaterialType() {
        return lensMaterialType;
    }

    def getLensCalcOutputData() {
        return lensCalcOutputData;
    }

    /**
     * Arbitrary equals code that only checks the frame identifier and the lens material id
     * @param otherObject the object to compare against
     * @return true if the objects have the same frame id and lens material id, false otherwise
     */
    boolean equals(Object otherObject) {
        boolean retVal = false;
        
        if ( otherObject instanceof DesignData ) {
            DesignData otherData = (DesignData)otherObject;
            
            if ( this.frameIdentifier == otherData.frameIdentifier &&  this.lensMaterialId == otherData.lensMaterialId ) 
                retVal = true;
        }
        
        return retVal;
    }
}

