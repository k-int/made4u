package com.k_int.made4u.frame

enum StandardType implements org.springframework.context.MessageSourceResolvable {

    HINGE('Hinge'),
    NYLON('Nylon'),
    SCREW('Screw')
    
    private String value;
    
    public StandardType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
