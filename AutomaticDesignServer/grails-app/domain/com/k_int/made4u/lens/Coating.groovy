package com.k_int.made4u.lens

class Coating {

    String      reference
    CoatingType coatingType
    int         red = 0;
    int         green = 0;
    int         blue = 0;
    float       transparencyIndex = 0.0f;
    float       dominantWavelength = 0.0f;
    float       saturationTransparencyIndex = 0.0f;
    float       rv = 0.0f;
    float       price = 0.0f;
    

    static constraints = {
        reference                   (blank: false, nullable: false, unique:['coatingType'])
        coatingType                 (nullable: false)
        red                         (nullable: false, range: 0..255)
        green                       (nullable: false, range: 0..255)
        blue                        (nullable: false, range: 0..255)
        transparencyIndex           (nullable: false)
        dominantWavelength          (nullable: false)
        saturationTransparencyIndex (nullable: false)
        rv                          (nullable: false)
        price                       (nullable: false, min: 0.0f)
    }

    static mapping = {
        table 'M4U_LENS_COATING'
        coatingType enumType:"ordinal"
    }
    
    String toXML() {
        StringBuilder xml = new StringBuilder();
        
        if ( coatingType == CoatingType.AR ) {
            xml.append("<m4u:ARColor>");
            xml.append("<m4u:name>").append(reference).append("</m4u:name>");
            xml.append("<m4u:rCode>").append(red).append("</m4u:rCode>");
            xml.append("<m4u:gCode>").append(green).append("</m4u:gCode>");
            xml.append("<m4u:bCode>").append(blue).append("</m4u:bCode>");
            xml.append("<m4u:Price/>");
            xml.append("</m4u:ARColor>");
        } else if ( coatingType == CoatingType.MIRROR ) {
            xml.append("<m4u:MirrorBaseColor>");
            xml.append("<m4u:name>").append(reference).append("</m4u:name>");
            xml.append("<m4u:rCode>").append(red).append("</m4u:rCode>");
            xml.append("<m4u:gCode>").append(green).append("</m4u:gCode>");
            xml.append("<m4u:bCode>").append(blue).append("</m4u:bCode>");
            xml.append("<m4u:TransparencyIndex>").append(transparencyIndex).append("</m4u:TransparencyIndex>");
            xml.append("<m4u:Price/>");            
            xml.append("</m4u:MirrorBaseColor>");
        }
        
        return xml.toString();
    }
}
