package com.k_int.made4u.frame

/**
 * Domain class for emotional variable definitions
 * @author rpb rich@k-int.com
 * @version 1.0 04.05.11
 */
class EmotionalVariable {
    
    String name;
    String description;

    static constraints = {
        name (blank: false, nullable: false, unique: true)
    }

    static mapping = {
        table 'M4U_FRAME_EMOTIONAL_VARIABLE'
    }
    
    boolean equals(Object otherVar) {
        boolean retVal = false;
        
        if ( otherVar instanceof EmotionalVariable ) {
            EmotionalVariable otherEmoVar = (EmotionalVariable)otherVar;
            if ( this.id == otherEmoVar.id )
                retVal = true;
        } 
        
        return retVal;
    }
    
    String toXML() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<m4u:EmotionalVariable>").append(name).append("</m4u:EmotionalVariable>");
        
        return xml.toString();
    }
}

