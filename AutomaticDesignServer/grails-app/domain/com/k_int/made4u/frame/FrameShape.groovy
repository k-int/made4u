package com.k_int.made4u.frame

/**
 * Domain class for frame shape definitions
 * @author rpb rich@k-int.com
 * @version 1.0 03.05.11
 */
class FrameShape {
    
    String name;
    String description;

    static constraints = {
        name (blank: false, nullable: false, unique: true)
    }

    static mapping = {
        table 'M4U_FRAME_SHAPE'
    }
}

