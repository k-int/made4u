package com.k_int.made4u.frame

/**
 * A class to model images associated with frames
 * @author rpb
 */
class FrameImage {

    String          path;
    String          reference;
    FrameImageType  imageType;

    static constraints = {
        path (blank: false, nullable: false, unique: true)
        reference (blank: false, nullable: false, unique: true)
        imageType (blank: false, nullable: false)
    }

    static mapping = {
        table 'M4U_FRAME_IMAGE'
    }
    
    boolean equals(Object otherObj) {
        boolean retval = false;
        
        if ( otherObj instanceof FrameImage ) {
            FrameImage otherImage = (FrameImage)otherObj;
            if ( this.id == otherImage.id )
                retval = true;
        }
        
        return retval;
    }
}
