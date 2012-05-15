package com.k_int.made4u.lens

/**
 * Domain class for lens topcoat definitions
 * @author rpb rich@k-int.com
 * @version 1.0 23.08.11
 */
class LensTopcoat {
    
    String name;
    String description;
    Float  price        = 0.0f;

    static constraints = {
        name (blank: false, nullable: false, unique: true)
        price (nullable: false, min: 0.0f)
    }

    static mapping = {
        table 'M4U_LENS_TOP_COAT'
    }
    
    boolean equals(Object otherVar) {
        boolean retVal = false;
        
        if ( otherVar instanceof LensTopcoat ) {
            LensTopcoat otherTopcoat = (LensTopcoat)otherVar;
            if ( this.id == otherTopcoat.id )
                retVal = true;
        } 
        
        return retVal;
    }
}

