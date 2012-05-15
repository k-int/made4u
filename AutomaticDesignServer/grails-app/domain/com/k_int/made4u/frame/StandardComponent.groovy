package com.k_int.made4u.frame

/**
 * Domain class for standard component definitions
 * @author rpb rich@k-int.com
 * @version 1.0 06.07.11
 */
class StandardComponent {
    
    String reference;
    String description;
    StandardType standardType = StandardType.HINGE;

    static constraints = {
        reference (blank: false, nullable: false, unique: true)
        standardType (nullable: false)
    }

    static mapping = {
        table 'M4U_FRAME_STANDARD_COMPONENT'
    }
    
    String toXML() {
        StringBuilder xml = new StringBuilder();
        
        if ( standardType == StandardType.HINGE )
            xml.append("<m4u:Hinge>").append(reference).append("</m4u:Hinge>");
        
        return xml.toString();
    }
}

