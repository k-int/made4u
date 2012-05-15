package com.k_int.made4u.frame

enum FrameImageType implements org.springframework.context.MessageSourceResolvable {

    FRONTAL('Frontal'),
    TEMPLE('Temple')
    
    private String value;
    
    public FrameImageType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }

    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }

    String getDefaultMessage() { name() }
}
