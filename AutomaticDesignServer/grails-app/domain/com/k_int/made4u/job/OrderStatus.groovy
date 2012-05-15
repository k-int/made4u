package com.k_int.made4u.job

enum OrderStatus implements org.springframework.context.MessageSourceResolvable {

    REQUEST_SUBMITTED('Request submitted'),
    PROCESSING_INPUT_FILES('Processing input files'),
    FRAME_CALCULATION('Frame calculation'),
    FRAME_CALCULATION_SUBMITTED('Frame calculation submitted'),
    FRAME_CALCULATION_COMPLETED('Frame calculation completed'),
    FRAME_CALCULATION_POST_PROCESSING('Frame calculation post processing'),
    LENS_CALCULATION_INPUT_GENERATION('Lens calculation input generation'),
    LENS_CALCULATION('Lens calculation'),
    COATING_DATA_CREATION('Coating file creation'),
    ORDER_POST_PROCESSING('Order post processing'),
    WCS_INPUT_GENERATION('WCS input generation'),
    SUBMITTING_ORDER_TO_WCS('Submitting order to WCS'),
    ORDER_SUBMITTED_TO_WCS('Order submitted to WCS'),
    ERROR('Error'),
    AWAITING_WCS_SUBMISSION('Awaiting WCS submission')
    
    
    private String value;
    
    public OrderStatus(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
    
    public static OrderStatus parseFromString(String toParse) {
        
        OrderStatus retval = null;
        
        // Remove any whitespace from the string being looked for
        if ( toParse )
            toParse = toParse.trim();
        
        // Check for the possible values one by one
        if ( "REQUEST_SUBMITTED".equalsIgnoreCase(toParse) )
            retval = OrderStatus.REQUEST_SUBMITTED;
        else if ( "PROCESSING_INPUT_FILES".equalsIgnoreCase(toParse) )
            retval = OrderStatus.PROCESSING_INPUT_FILES;
        else if ( "FRAME_CALCULATION".equalsIgnoreCase(toParse) )
            retval = OrderStatus.FRAME_CALCULATION;
        else if ( "FRAME_CALCULATION_SUBMITTED".equalsIgnoreCase(toParse) )
            retval = OrderStatus.FRAME_CALCULATION_SUBMITTED;
        else if ( "FRAME_CALCULATION_COMPLETED".equalsIgnoreCase(toParse) )
            retval = OrderStatus.FRAME_CALCULATION_COMPLETED;
        else if ( "FRAME_CALCULATION_POST_PROCESSING".equalsIgnoreCase(toParse) )
            retval = OrderStatus.FRAME_CALCULATION_POST_PROCESSING;
        else if ( "LENS_CALCULATION_INPUT_GENERATION".equalsIgnoreCase(toParse) )
            retval = OrderStatus.LENS_CALCULATION_INPUT_GENERATION;
        else if ( "LENS_CALCULATION".equalsIgnoreCase(toParse) )
            retval = OrderStatus.LENS_CALCULATION;
        else if ( "COATING_DATA_CREATION".equalsIgnoreCase(toParse) )
            retval = OrderStatus.COATING_DATA_CREATION;
        else if ( "ORDER_POST_PROCESSING".equalsIgnoreCase(toParse) )
            retval = OrderStatus.ORDER_POST_PROCESSING;
        else if ( "WCS_INPUT_GENERATION".equalsIgnoreCase(toParse) )
            retval = OrderStatus.WCS_INPUT_GENERATION;
        else if ( "SUBMITTING_ORDER_TO_WCS".equalsIgnoreCase(toParse) )
            retval = OrderStatus.SUBMITTING_ORDER_TO_WCS;
        else if ( "ORDER_SUBMITTED_TO_WCS".equalsIgnoreCase(toParse) )
            retval = OrderStatus.ORDER_SUBMITTED_TO_WCS;
        else if ( "ERROR".equalsIgnoreCase(toParse) )
            retval = OrderStatus.ERROR;
        else if ( "AWAITING_WCS_SUBMISSION".equalsIgnoreCase(toParse) )
            retval = OrderStatus.AWAITING_WCS_SUBMISSION;
            
        
        return retval;
    }
}
