package com.k_int.made4u.frame

enum EndpieceHeight implements org.springframework.context.MessageSourceResolvable {

    DOWN('Down'),
    MEDIUM('Medium'),
    UP('Up')
    
    private String value;
    
    public EndpieceHeight(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
