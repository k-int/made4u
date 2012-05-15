package com.k_int.made4u

/**
 * A simple representation of a time including its value and type
 * @author rpb rich@k-int.com
 * @version 1.0 10.06.11
 */
class Time {
	
        float               value;
        PersonalisationType type;
        
        static constraints = {
            value ( nullable: false, range: 0..2000 )
            type  ( nullable: false )
        }
        
        static mapping = {
            table 'M4U_TIME'
        }
}

