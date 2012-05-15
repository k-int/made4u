package com.k_int.made4u.lens

enum LensSpecialColourType implements org.springframework.context.MessageSourceResolvable {

    POLARIZING('Polarizing'),
    PHOTOCHROMIC('Photochromic')
    
    private String value;
    
    public LensSpecialColourType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
