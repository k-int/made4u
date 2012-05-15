package com.k_int.made4u.services

import grails.test.*
import org.springframework.core.io.*

import java.net.ConnectException;



import com.k_int.made4u.serviceInputs.LensCalcInput;

class WCSServiceTests extends GrailsUnitTestCase {

    def wcsService = new WebControlSystemService()

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    
    void testManufacturingOrderSubmission() {
        
        log.debug("WCS manufacturing order submission service test...");
        Resource resource = new ClassPathResource('testWCS.json');
        
        def f = resource.getFile();
        
        def inputData = [f.text];
  

        def result = wcsService.submitOrder(inputData, "rich test submission", "/tmp/", "wscInput.json", "wcsOutput.json");
        
        log.debug( "result = ${result}");
        
        
    }

}
