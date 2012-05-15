package com.k_int.made4u.frame

/**
 * A class to represent the base colour of frames
 * @author rpb rich@k-int.com
 * @version 1.0 19.05.11
 */
class FrameBaseColour {
    
    String  name;
    int     red;
    int     green;
    int     blue;
    float   price;
    int     productionTime;
    String  description;
    
    Set<Frame> frames;
    
    static hasMany = [ frames: com.k_int.made4u.frame.Frame ]
    static belongsTo = Frame;
    
    static constraints = {
        name    (blank: false, nullable: false, unique: true)
        red     (min:0,max:255,nullable: false)
        green   (min:0,max:255,nullable: false)
        blue    (min:0,max:255,nullable: false)
        productionTime    (min:0, max:30, nullable: false)
    }

    static mapping = {
        table 'M4U_FRAME_BASE_COLOUR'
    }
    
    boolean equals(Object otherColour) {
        boolean retval = false;
        
        if ( otherColour instanceof FrameBaseColour ) {
            FrameBaseColour testObj = (FrameBaseColour)otherColour;
            if ( this.id == testObj.id ) {
                retval = true;
            }
        }
        
        return retval;
    }
    
    String toXML() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<m4u:BaseColour>");
        xml.append("<m4u:name>").append(name).append("</m4u:name>");
        xml.append("<m4u:rCode>").append(red).append("</m4u:rCode>");
        xml.append("<m4u:gCode>").append(green).append("</m4u:gCode>");
        xml.append("<m4u:bCode>").append(blue).append("</m4u:bCode>");
        xml.append("</m4u:BaseColour>");
                
        return xml.toString();
    }
    
}

