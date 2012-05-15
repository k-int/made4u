package com.k_int.made4u.lens

enum LensColourType implements org.springframework.context.MessageSourceResolvable {

    BASIC('Basic'),
    FASHION('Fashion')
    
    private String value;
    
    public LensColourType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
