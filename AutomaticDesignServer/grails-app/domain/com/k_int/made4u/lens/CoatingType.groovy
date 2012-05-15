package com.k_int.made4u.lens

enum CoatingType {

    MIRROR('Mirror'),
    AR('Anti-reflective')
    
    private String value;
    
    public CoatingType(String value) {
        this.value = value;
    }
    
    public String value() { return value; }
}
