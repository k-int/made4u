package com.k_int.made4u.job

enum TimerStatus implements org.springframework.context.MessageSourceResolvable {

    IDLE('Idle'),
    RUNNING('Running')
    
    
    private String value;
    
    public TimerStatus(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
    
    public static TimerStatus parseFromString(String toParse) {
        
        TimerStatus retval = null;
        
        // Remove any whitespace from the string being looked for
        if ( toParse )
            toParse = toParse.trim();
        
        // Check for the possible values one by one
        if ( "IDLE".equalsIgnoreCase(toParse) )
            retval = TimerStatus.IDLE;
        else if ( "RUNNING".equalsIgnoreCase(toParse) )
            retval = TimerStatus.RUNNING;

        return retval;
    }

}
