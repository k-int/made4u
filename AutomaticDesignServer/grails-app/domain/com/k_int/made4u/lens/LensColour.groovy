package com.k_int.made4u.lens

class LensColour {

    String          name;
    LensColourType  colourType                  = LensColourType.BASIC;
    boolean         acceptMirror                = false;
    Float           L                           = 0.0f;
    Float           a                           = 0.0f;
    Float           b                           = 0.0f;
    Float           TV                          = 0.0f;
    Float           black                       = 0.0f;
    Float           yellow                      = 0.0f;
    Float           blue                        = 0.0f;
    Float           red                         = 0.0f;
    Integer         rCode                       = 0;
    Integer         gCode                       = 0;
    Integer         bCode                       = 0;
    Float           transparencyIndex           = 0.0f;
    Float           LGradient                   = 0.0f;
    Float           aGradient                   = 0.0f;
    Float           bGradient                   = 0.0f;
    Float           TVGradient                  = 0.0f;
    Float           blackGradient               = 0.0f;
    Float           yellowGradient              = 0.0f;
    Float           blueGradient                = 0.0f;
    Float           redGradient                 = 0.0f;
    Integer         rCodeGradient               = 0;
    Integer         gCodeGradient               = 0;
    Integer         bCodeGradient               = 0;
    Float           transparencyIndexGradient   = 0.0f;
    Float           price                       = 0.0f;
    
        
    static constraints = {
        name                        (blank:false, nullable: false, unique: true)
        colourType                  (nullable: false)
        acceptMirror                (nullable: false)
        L                           (nullable: false, min: 0.0f)
        a                           (nullable: false)
        b                           (nullable: false)
        TV                          (nullable: false, min: 0.0f)
        black                       (nullable: false, min: 0.0f)
        yellow                      (nullable: false, min: 0.0f)
        blue                        (nullable: false, min: 0.0f)
        red                         (nullable: false, min: 0.0f)
        rCode                       (nullable: false, min: 0, max: 255)
        gCode                       (nullable: false, min: 0, max: 255)
        bCode                       (nullable: false, min: 0, max: 255)
        transparencyIndex           (nullable: false, min: 0.0f)
        LGradient                   (nullable: true, min: 0.0f)
        aGradient                   (nullable: true)
        bGradient                   (nullable: true)
        TVGradient                  (nullable: true, min: 0.0f)
        blackGradient               (nullable: true, min: 0.0f)
        yellowGradient              (nullable: true, min: 0.0f)
        blueGradient                (nullable: true, min: 0.0f)
        redGradient                 (nullable: true, min: 0.0f)
        rCodeGradient               (nullable: true, min: 0, max: 255)
        gCodeGradient               (nullable: true, min: 0, max: 255)
        bCodeGradient               (nullable: true, min: 0, max: 255)
        transparencyIndexGradient   (nullable: true, min: 0.0f)
        price                       (nullable: false, min: 0.0f)
    }

    static mapping = {
        table 'M4U_LENS_COLOUR'
    }

    // Convert the object to the XML used in our document format
    String toXML() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<m4u:colour subType=\"").append(colourType.value).append("\">");
        xml.append("<m4u:primaryColor>");
        xml.append("<m4u:name>").append(name).append("</m4u:name>");
        xml.append("<m4u:rCode>").append(rCode).append("</m4u:rCode>");
        xml.append("<m4u:gCode>").append(gCode).append("</m4u:gCode>");
        xml.append("<m4u:bCode>").append(bCode).append("</m4u:bCode>");
        xml.append("<m4u:TransparencyIndex>").append(transparencyIndex).append("</m4u:TransparencyIndex>");
        xml.append("</m4u:primaryColor>");
        
        if ( rCodeGradient == null || "".equals(rCodeGradient) 
            || gCodeGradient == null || "".equals(gCodeGradient) 
            || bCodeGradient == null || "".equals(bCodeGradient) ) {
            // No gradient information to add..
        } else {
            xml.append("<m4u:gradientColor>");
            xml.append("<m4u:rCode>").append(rCodeGradient).append("</m4u:rCode>");
            xml.append("<m4u:gCode>").append(gCodeGradient).append("</m4u:gCode>");
            xml.append("<m4u:bCode>").append(bCodeGradient).append("</m4u:bCode>");
            xml.append("<m4u:TransparencyIndex>").append(transparencyIndexGradient).append("</m4u:TransparencyIndex>");
            xml.append("</m4u:gradientColor>")
        }
        
        xml.append("<m4u:Price>").append(price).append("</m4u:Price>");
        xml.append("</m4u:colour>");
        
        return StringBuilder.toString();
    }
}
