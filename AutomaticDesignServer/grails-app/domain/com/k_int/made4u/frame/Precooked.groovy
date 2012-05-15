package com.k_int.made4u.frame


/**
 * A class to model precooked files associated with frames
 * @author rpb rich@k-int.com
 * @version 1.0 11.07.11
 */
class Precooked {

    String          path;
    String          reference;
    Float           heelWidth;
    Float           bridgeWidth;

    static constraints = {
        path (blank: false, nullable: false, unique: true)
        reference (blank: false, nullable: false)
    }

    static mapping = {
        table 'M4U_FRAME_PRECOOKED'
    }

    boolean equals(Object otherObj) {
        boolean retval = false;
        
        if ( otherObj instanceof Precooked ) {
            Precooked testObj = (Precooked)otherObj;
            if ( this.id == otherObj.id ) 
                retval = true;
        }
    }
    
    
    String toXML() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<m4u:Pre-Cooked Reference=\"").append(reference).append("\" BridgeWidth=\"").append(bridgeWidth).append("\" HeelWidth=\"").append(heelWidth).append("\"/>");

        return xml.toString();
    }
}

