package com.k_int.made4u.lens

class LensSpecialColour {

    String                  name;
    LensSpecialColourType   specialColourType           = LensSpecialColourType.POLARIZING;
    Float                   L                           = 0.0f;
    Float                   a                           = 0.0f;
    Float                   b                           = 0.0f;
    Float                   TV                          = 0.0f;
    Float                   black                       = 0.0f;
    Float                   yellow                      = 0.0f;
    Float                   blue                        = 0.0f;
    Float                   red                         = 0.0f;
    Integer                 rCode                       = 0;
    Integer                 gCode                       = 0;
    Integer                 bCode                       = 0;
    Float                   transparencyIndex           = 0.0f;
    Float                   price                       = 0.0f;
    
        
    static constraints = {
        name                        (blank:false, nullable: false, unique: true)
        specialColourType           (nullable: false)
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
        price                       (nullable: false, min: 0.0f)
    }

    static mapping = {
        table 'M4U_LENS_SPECIAL_COLOUR'
    }

    // Convert the object to the XML used in our document format
    String toXML() {
        StringBuilder xml = new StringBuilder();
        
        // TODO
        
        xml.append("Lens special colour - TODO");
        
        return StringBuilder.toString();
    }
}
