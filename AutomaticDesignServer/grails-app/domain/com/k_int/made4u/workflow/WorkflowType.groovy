package com.k_int.made4u.workflow

enum WorkflowType implements org.springframework.context.MessageSourceResolvable {

    DESIGN_REQUEST('Design request'),
    MANUFACTURING_ORDER('Manufacturing order')
    
    private String value;
    
    public WorkflowType(String value) {
        this.value = value;
    }
    
    public String value() { return "${getClass().name}.${name()}"; }
    
    Object[] getArguments() { [] as Object[] }
    
    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }
    
    String getDefaultMessage() { name() }
}
