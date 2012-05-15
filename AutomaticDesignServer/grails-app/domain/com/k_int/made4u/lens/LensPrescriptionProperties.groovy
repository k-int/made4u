package com.k_int.made4u.lens

/**
 * The prescription properties for a lens material
 * @author rpb rich@k-int.com
 * @version 1.0 18.04.11
 */
class LensPrescriptionProperties {
    Float   sphereMin = 0.0f;
    Float   sphereMax = 0.0f;
    Float   cylinderMin = 0.0f;
    Float   cylinderMax = 0.0f;
    Float   additionMin = 0.0f;
    Float   additionMax = 0.0f;
    Float   prismMin = 0.0f;
    Float   prismMax = 0.0f;
    
    
    static constraints = {
        sphereMin   (nullable: false)
        sphereMax   (nullable: false)
        cylinderMin (nullable: false)
        cylinderMax (nullable: false)
        additionMin (nullable: false)
        additionMax (nullable: false)
        prismMin    (nullable: false)
        prismMax    (nullable: false)
    }

    static mapping = {
        table 'M4U_LENS_PRESCRIPTION_PROPERTIES'
    }

    boolean equals(Object otherObj) {
        boolean retVal = false;
        
        if ( otherObj instanceof LensPrescriptionProperties ) {
            LensPrescriptionProperties otherPrescrip = (LensPrescriptionProperties)otherObj;
            if ( this.id == otherPrescrip.id ) {
                retVal = true;
            }
        }
        
        return retVal;
    }
    
    String toOutput() {
        StringBuilder prescriptionBuilder = new StringBuilder();
        
        prescriptionBuilder.append("sphereMin: ").append(sphereMin);
        prescriptionBuilder.append("sphereMax: ").append(sphereMax);
        prescriptionBuilder.append("cylinderMin: ").append(cylinderMin);
        prescriptionBuilder.append("cylinderMax: ").append(cylinderMax);
        prescriptionBuilder.append("additionMin: ").append(additionMin);
        prescriptionBuilder.append("additionMax: ").append(additionMax);
        prescriptionBuilder.append("prismMin: ").append(prismMin);
        prescriptionBuilder.append("prismMax: ").append(prismMax);
        
        return prescriptionBuilder.toString();
    }
}

