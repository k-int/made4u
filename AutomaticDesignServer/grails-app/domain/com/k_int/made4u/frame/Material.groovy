package com.k_int.made4u.frame

/**
 * Domain class for material definitions
 * @author rpb rich@k-int.com
 * @version 1.0 20.04.11
 */
class Material {
    
    String name;
    String description;

    static constraints = {
        name (blank: false, nullable: false, unique: true)
    }

    static mapping = {
        table 'M4U_FRAME_MATERIAL'
    }
}

