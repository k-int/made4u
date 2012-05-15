package com.k_int.made4u.services

import javax.annotation.PostConstruct
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.ContentType.TEXT

import groovyx.net.http.URIBuilder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.MultipartPostMethod;

import java.net.ConnectException;


import static groovyx.net.http.ContentType.XML

class WebControlSystemService {

    static transactional = true
 
    @PostConstruct
    def init() {
      log.debug("FramesCalculationService initialising");
    }

    def serviceMethod() {

    }
    
    
    def submitOrder(inputStructure, orderNumber, dirPath, outputFileName) {
        
        
        
        // Now actually set up the connection to the WCS and submit the order
        def retvalSuccess = false;
        def retvalMessage = null;

        try {
            def uri = new URIBuilder("http://made4u.plastia.com/index.php?m=restmode");
            def httpBuilder = new HTTPBuilder(uri);
            def postBody = ["m":"restmode","method":"jobs.create","format":"json","jobOrder":orderNumber,"dataSupply":inputStructure];

            def status;
            def headers;
            
            httpBuilder.post(body:postBody) { 
                resp ->
                    status = resp.statusLine.statusCode;
                    headers = resp.headers;
            }


            if ( status != 200 && status != 201 ) {
                // Problem with the service!!
                log.debug("Problem communicating with the web control system - status code: " + status);

                headers.each() {
                    log.debug("Header: " + it);
                }
                
                retvalSuccess = false;
                retvalMessage = "Error returned when communicating with the web control system. Status code: " + status;

            } else {
                log.debug("Communication with web control system completed successfully");
                                
                retvalSuccess = true;
                retvalMessage = "Order submitted successfully";
            }
        } catch (java.net.ConnectException ce) {
            // A connection exception - log and return failure
            log.error("Connection exception thrown when talking to the web control system: " + ce.getMessage());
            ce.printStackTrace();
            retvalSuccess = false;
            retvalMessage = "Error when talking to the web control system: " + ce.getMessage();
        } catch (Exception e) {
            // General unexpected exception
            log.error("Unhandled exception thrown when talking to the web control system: " + e.getMessage());
            e.printStackTrace();
            retvalSuccess = false;
            retvalMessage = "Unhandled error when talking to the web control system";
        }
        
        def retval = [success: retvalSuccess, message: retvalMessage]
        
        return retval;
    }
    
}
