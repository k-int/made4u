package com.k_int.made4u.frame

enum RimType implements org.springframework.context.MessageSourceResolvable {

    FULL('Full'),
    SEMIRIMLESS('Semi-rimless'),
    RIMLESS('Rimless')
    
    private String value;
    
    public RimType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
