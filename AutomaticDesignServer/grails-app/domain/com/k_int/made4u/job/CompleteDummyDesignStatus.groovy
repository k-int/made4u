package com.k_int.made4u.job

enum CompleteDummyDesignStatus implements org.springframework.context.MessageSourceResolvable {

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
    ORDER_PLACED('Order placed')
    
    
    private String value;
    
    public CompleteDummyDesignStatus(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
