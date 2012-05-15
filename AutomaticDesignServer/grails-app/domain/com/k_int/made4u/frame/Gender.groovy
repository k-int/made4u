package com.k_int.made4u.frame

enum Gender implements org.springframework.context.MessageSourceResolvable {

    MALE('Male'),
    FEMALE('Female'),
    UNISEX('Unisex')
    
    private String value;
    
    public Gender(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
