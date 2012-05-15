package com.k_int.made4u

enum PersonalisationType implements org.springframework.context.MessageSourceResolvable {

    FULL('Fully personalised'),
    SEMI('Semi personalised'),
    STANDARD('Standard')
    
    private String value;
    
    public PersonalisationType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
