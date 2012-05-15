package com.k_int.made4u.job

enum DesignStatus implements org.springframework.context.MessageSourceResolvable {

    REQUEST_SUBMITTED('Request submitted'),
    PROCESSING_DATA('Processing data'),
    FRAMES_CALCULATION('Frames calculation'),
    FRAME_CATALOGUE_FILTRATION('Frame catalogue filtration'),
    LENS_CATALOGUE_FILTRATION('Lens catalogue filtration'),
    GENERATING_LENS_CALC_INPUT('Lens calculation input generation'),
    LENS_CALCULATION('Lens calculation'),
    DESIGN_CONFIGURATION('Design configuration'),
    DESIGN_RETURNED('Design returned'),
    ERROR('Error'),
    ORDER_PLACED('Order placed'),
    COMPLETED_CALCULATION_POST_PROCESSING('Completed calculation post processing'),
    NO_VIABLE_FRAMES('No viable frames'),
    NO_VIABLE_FRAME_LENS_COMBINATIONS('No viable frame and lens combinations'),
    AWAITING_PROCESSING_PART_TWO('Awaiting part two of processing'),
    LENS_CALCULATION_RETRY('Retrying lens calculation')
   
    
    private String value;
    
    public DesignStatus(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
    
    public static DesignStatus parseFromString(String toParse) {
        
        DesignStatus retval = null;
        
        // Remove any whitespace from the string being looked for
        if ( toParse )
            toParse = toParse.trim();
        
        // Check for the possible values one by one
        if ( "REQUEST_SUBMITTED".equalsIgnoreCase(toParse) )
            retval = DesignStatus.REQUEST_SUBMITTED;
        else if ( "PROCESSING_DATA".equalsIgnoreCase(toParse) )
            retval = DesignStatus.PROCESSING_DATA;
        else if ( "FRAMES_CALCULATION".equalsIgnoreCase(toParse) )
            retval = DesignStatus.FRAMES_CALCULATION;
        else if ( "FRAME_CATALOGUE_FILTRATION".equalsIgnoreCase(toParse) )
            retval = DesignStatus.FRAME_CATALOGUE_FILTRATION;
        else if ( "LENS_CATALOGUE_FILTRATION".equalsIgnoreCase(toParse) )
            retval = DesignStatus.LENS_CATALOGUE_FILTRATION;
        else if ( "GENERATING_LENS_CALC_INPUT".equalsIgnoreCase(toParse) )
            retval = DesignStatus.GENERATING_LENS_CALC_INPUT;
        else if ( "LENS_CALCULATION".equalsIgnoreCase(toParse) )
            retval = DesignStatus.LENS_CALCULATION;
        else if ( "DESIGN_CONFIGURATION".equalsIgnoreCase(toParse) )
            retval = DesignStatus.DESIGN_CONFIGURATION;
        else if ( "DESIGN_RETURNED".equalsIgnoreCase(toParse) )
            retval = DesignStatus.DESIGN_RETURNED;
        else if ( "ORDER_PLACED".equalsIgnoreCase(toParse) )
            retval = DesignStatus.ORDER_PLACED;
        else if ( "COMPLETED_CALCULATION_POST_PROCESSING".equalsIgnoreCase(toParse) )
            retval = DesignStatus.COMPLETED_CALCULATION_POST_PROCESSING;
        else if ( "ERROR".equalsIgnoreCase(toParse) )
            retval = DesignStatus.ERROR;
        else if ( "NO_VIABLE_FRAMES".equalsIgnoreCase(toParse) )
            retval = DesignStatus.NO_VIABLE_FRAMES;
        else if ( "NO_VIABLE_FRAME_LENS_COMBINATIONS".equalsIgnoreCase(toParse) )
            retval = DesignStatus.NO_VIABLE_FRAME_LENS_COMBINATIONS;
        else if ( "AWAITING_PROCESSING_PART_TWO".equalsIgnoreCase(toParse) )
            retval = DesignStatus.AWAITING_PROCESSING_PART_TWO;
        else if ( "LENS_CALCULATION_RETRY".equalsIgnoreCase(toParse) )
            retval = DesignStatus.LENS_CALCULATION_RETRY;
        
        return retval;
    }
}
