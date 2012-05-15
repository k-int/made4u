package com.k_int.made4u.job

enum TimerType implements org.springframework.context.MessageSourceResolvable {

    MANUFACTURING_ORDER('Manufacturing order'),
    DESIGN_REQUEST('Design request'),
    DESIGN_REQUEST_PART_TWO('Design request part 2'),
    MANUFACTURING_ORDER_FRAME_CALC_PROGRESS('Manufacturing order frame calculation progress'),
    MANUFACTURING_ORDER_PART_TWO('Manufacturing order part 2'),
    MANUFACTURING_ORDER_WCS_SUBMISSION('Manufacturing order submission to the WCS')
    
    
    private String value;
    
    public TimerType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
