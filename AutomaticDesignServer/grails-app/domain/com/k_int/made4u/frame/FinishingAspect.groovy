package com.k_int.made4u.frame

enum FinishingAspect implements org.springframework.context.MessageSourceResolvable {

    BRILLIANT('Brilliant'),
    MATT('Matt'),
    BOTH('Both')
    
    private String value;
    
    public FinishingAspect(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
    
    public static FinishingAspect parseFromString(String toParse) {
        
        FinishingAspect retval = null;
        
        // Remove any whitespace from the string being looked for
        if ( toParse )
            toParse = toParse.trim();
        
        // Check for the possible values one by one
        if ( "BRILLIANT".equalsIgnoreCase(toParse) )
            retval = FinishingAspect.BRILLIANT;
        else if ( "MATT".equalsIgnoreCase(toParse) || "MATTE".equalsIgnoreCase(toParse) )
            retval = FinishingAspect.MATT;
        else if ( "BOTH".equalsIgnoreCase(toParse) )
            retval = FinishingAspect.BOTH;
            
        
        return retval;
    }
}
