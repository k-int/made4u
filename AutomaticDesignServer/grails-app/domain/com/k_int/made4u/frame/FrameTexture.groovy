package com.k_int.made4u.frame

/**
 * A class to represent textures to  be applied to a frame
 * @author rpb rich@k-int.com
 * @version 1.0 09.08.11
 */
class FrameTexture {
    
    String      reference;
    int         x;
    int         y;
    float       scaleFactor;
    float       price;
    int         productionTime;
    FrameImage  image;
    
    Set<Frame> frames;
    
    static hasMany = [ frames: com.k_int.made4u.frame.Frame ]
    static belongsTo = Frame;
    
    static constraints = {
        reference       (blank: false, nullable: false)
        x               (min:0,max:255,nullable: false)
        y               (min:0,max:255,nullable: false)
        scaleFactor     (min:0.0f,max:10.0f,nullable: false)
        productionTime  (min:0, max:30, nullable: false)
    }

    static mapping = {
        table 'M4U_FRAME_TEXTURE'
    }
    
    boolean equals(Object otherTexture) {
        boolean retval = false;
        
        if ( otherTexture instanceof FrameTexture ) {
            FrameTexture testObj = (FrameTexture)otherTexture;
            if ( this.id == testObj.id ) {
                retval = true;
            }
        }
        
        return retval;
    }
    
    
}

