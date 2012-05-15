package com.k_int.made4u.frame

enum Use implements org.springframework.context.MessageSourceResolvable {

    SOLAR('Solar'),
    OPHTHALMIC('Ophthalmic'),
    BOTH('Both')
    
    private String value;
    
    public Use(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}" }
    
    Object[] getArguments() { [] as Object[] }

    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }

    String getDefaultMessage() { name() }
}
