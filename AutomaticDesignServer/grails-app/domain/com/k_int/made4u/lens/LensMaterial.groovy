package com.k_int.made4u.lens

class LensMaterial {

    String      identifier
    String      reference
    Float       refractionIndex = 0.0f;
    Float       minimumDrillingThickness = 0.0f;
    Float       centreDrillingThickness = 0.0f;
    Float       minimumEdgeThicknessFull = 0.0f;
    Float       minimumEdgeThicknessSemi = 0.0f;
    Float       minimumEdgeThicknessRimless = 0.0f;
    Float       priceEyeMade = 0.0f;
    Float       priceLifeMadeExpert = 0.0f;
    Float       priceLifeMadeInicia = 0.0f;
    Float       priceSingleVision = 0.0f;
    
    
    LensMaterialType    materialType;
    LensType            focalType;
    Set<LensPrescriptionProperties> prescriptions;
    
    static hasMany = [  prescriptions : com.k_int.made4u.lens.LensPrescriptionProperties ]
        
    static constraints = {
        identifier                  (blank:false, nullable: false)
        reference                   (blank: false, nullable: false, unique:['focalType','materialType'])
        refractionIndex             (nullable: false)
        minimumDrillingThickness    (nullable: false)
        centreDrillingThickness     (nullable: false)
        minimumEdgeThicknessFull    (nullable: false)
        minimumEdgeThicknessSemi    (nullable: false)
        minimumEdgeThicknessRimless (nullable: false)
        priceEyeMade                (nullable: false)
        priceLifeMadeExpert         (nullable: false)
        priceLifeMadeInicia         (nullable: false)
        priceSingleVision           (nullable: false)
        materialType                (nullable: false)
        focalType                   (nullable: false)
        
    }

    static mapping = {
        table 'M4U_LENS_MATERIAL'
        prescriptions joinTable: 'M4U_LENS_MATERIAL_PRESCRIPTION_JOIN'
        focalType enumType:"ordinal"
        materialType enumType:"ordinal"
    }
    
    String toXML() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<m4u:viableMaterial>");
        xml.append("<m4u:name>").append(reference).append("</m4u:name>");
        xml.append("<m4u:EyeMadePrice>").append(priceEyeMade).append("</m4u:EyeMadePrice>");
        xml.append("<m4u:LifeMadeExpertPrice>").append(priceLifeMadeExpert).append("</m4u:LifeMadeExpertPrice>");
        xml.append("<m4u:LifeMadeIniciaPrice>").append(priceLifeMadeInicia).append("</m4u:LifeMadeIniciaPrice>");
        xml.append("<m4u:SphericalSinglePrice>").append(priceSingleVision).append("</m4u:SphericalSinglePrice>");
        // TODO - thickness simulation data goes here...
        xml.append("</m4u:viableMaterial>");
        
        return xml.toString();
        
    }
}
