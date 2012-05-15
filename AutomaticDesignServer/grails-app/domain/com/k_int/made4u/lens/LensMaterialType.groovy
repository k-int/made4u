package com.k_int.made4u.lens

enum LensMaterialType implements org.springframework.context.MessageSourceResolvable {

    WHITE('White'),
    PHOTOCHROMIC('Photochromic'),
    POLARIZING('Polarizing')
    
    private String value;
    
    public LensMaterialType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
