package com.k_int.made4u.lens

enum LensType implements org.springframework.context.MessageSourceResolvable {

    PROGRESSIVE('Progressive'),
    MONOFOCAL('Monofocal')
    
    private String value;
    
    public LensType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
    
     public static LensType parseFromString(toParse) {
        
        LensType retval = null;

        // Remove any extra whitespace from the value that shouldn't be there
        if ( toParse )
            toParse = toParse.trim();
            
        // Look for each possible value one by one
            
        if ( "MONOFOCAL".equalsIgnoreCase(toParse) )
            retval = LensType.MONOFOCAL;
        else if ( "SINGLEVISION".equalsIgnoreCase(toParse) )
            retval = LensType.MONOFOCAL;
        else if ( "MF".equalsIgnoreCase(toParse) )
            retval = LensType.MONOFOCAL;
        else if ( "SV".equalsIgnoreCase(toParse) )
            retval = LensType.MONOFOCAL;
        else if ( "PROGRESSIVE".equalsIgnoreCase(toParse) )
            retval = LensType.PROGRESSIVE;
        else if ( "PSV".equalsIgnoreCase(toParse) )
            retval = LensType.PROGRESSIVE;
        else if ( "PR".equalsIgnoreCase(toParse) )
            retval = LensType.PROGRESSIVE;
        else if ( "PROGRESSIVO".equalsIgnoreCase(toParse) )
            retval = LensType.PROGRESSIVE;
        else if ( "PROGRESIVO".equalsIgnoreCase(toParse) )
            retval = LensType.PROGRESSIVE;
                    
        return retval;
    }
}
