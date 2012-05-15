package com.k_int.made4u.services

import javax.annotation.PostConstruct
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.ContentType.TEXT


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.MultipartPostMethod;

import java.net.ConnectException;


import static groovyx.net.http.ContentType.XML

class FramesManufacturingCalculationService {

    static transactional = true
 
    @PostConstruct
    def init() {
      log.debug( "FramesManufacturingCalculationService initialising");
    }

    def serviceMethod() {

    }
    
    
    def submitCalc(optPreSection, userPointsPath, nasalMeshPath, customerChoicesSection, dirPath, inputFileName, outputFileName) {
        
        def inputFileContents = constructSoapInputFile(optPreSection, userPointsPath, nasalMeshPath, customerChoicesSection);
        
        def actualInputFile = new File(dirPath, inputFileName);
        actualInputFile.write(inputFileContents);
        
        def retval = [success: false, message: null];

        try {
            HttpClient postClient = new HttpClient();
            MultipartPostMethod postMethod = new MultipartPostMethod('http://192.168.68.18:80/cgi-bin/framefit/ws-manuf-order.pl');
            postClient.setConnectionTimeout(300000);


            postMethod.addParameter("upload",actualInputFile);

            int status = postClient.executeMethod(postMethod);
            def data = postMethod.getResponseBodyAsString();
            postMethod.releaseConnection();


            if ( status != 200 ) {
                // Problem with the service!!
                log.error("Problem communicating with the frame manufacturing order calculation service - status code: " + status);
                log.error("Response body: " + data);
                
                retval.success = false;
                retval.message = "Unable to talk to the frame manufacturing order calculation service";

            } else {
                log.debug("Communication with frames manufacturing calculation server completed successfully");
                def returnedFile = new File(dirPath, outputFileName);
                returnedFile.write(data);

                retval.success = true;
                retval.message = "Calculation submitted";
            }
        } catch (java.net.ConnectException ce) {
            // A connection exception - log and return failure
            log.error("Connection exception thrown when talking to the frames manufacturing calculation service: " + ce.getMessage());
            ce.printStackTrace();
            retval.success = false;
            retval.message = "Connection exception thrown when submitting frame manufacturing order calculation" + ce.getMessage();
        } catch (Exception e) {
            // Generic catch all..
            log.error("Unhandled exception thrown when talking to the frames manufacturing calculation service: " + e.getMessage());
            e.printStackTrace();
            retval.success = false;
            retval.message = "Unhandled exception thrown when submitting frame manufacturing order calculation: " + e.getMessage();
        }
        
        return retval;
    }
    
    def getCalcStatus(dirPath, inputFilename, outputFilename) {
        
        def retval = [success: false, message: null];
        def actualInputFile = new File(dirPath, inputFilename);

        if ( actualInputFile.exists() ) {

            try {
                HttpClient postClient = new HttpClient();
                MultipartPostMethod postMethod = new MultipartPostMethod('http://192.168.68.18:80/cgi-bin/framefit/ws-manuf-order.pl');
                postClient.setConnectionTimeout(300000);


                postMethod.addParameter("upload",actualInputFile);

                int status = postClient.executeMethod(postMethod);
                def data = postMethod.getResponseBodyAsString();
                postMethod.releaseConnection();


                if ( status != 200 ) {
                    // Problem with the service!!
                    log.error("Problem communicating with the frame manufacturing order calculation service - status code: " + status);
                    log.error("Response body: " + data);

                } else {
                    log.debug("Communication with frames manufacturing calculation server completed successfully");
                    def returnedFile = new File(dirPath, outputFilename);
                    returnedFile.write(data);

                    retval.success = true;
                }
            } catch (java.net.ConnectException ce) {
                // A connection exception - log and return failure
                log.error("Connection exception thrown when talking to the frames manufacturing calculation service: " + ce.getMessage());
                ce.printStackTrace();
                retval.success = false;
                retval.message = "Unable to connect to the frames manufacturing calculation service";
            } catch (Exception e) {
                // A generic exception
                log.error("Unhandled exception thrown when talking to the frames manufacturing calculation service: " + e.getMessage());
                e.printStackTrace();
                retval.success = false;
                retval.message = "Unhandled exception thrown when connecting to the frames manufacturing calculation service: " + e.getMessage();
            }
        } else {
            // Input file doesn't exist - can't query the service!!
            def fullFileName = dirPath + File.separator + inputFilename;
            log.error("Unable to query the frames manufacturing calculation service for the status of a calculation as we don't have the calculation input file: " + fullFileName );
            retval.success = false;
            retval.message = "No input file found for the frames calculation - unable to query the status of the calculation";
        }        
        return retval;
    }
    
   
    def constructSoapInputFile(optPreSection, userPointsPath, nasalMeshPath, customerChoicesSection) {
        StringBuilder inputFileBuilder = new StringBuilder();
        
       inputFileBuilder.append('<?xml version="1.0"?>');
       inputFileBuilder.append('<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">');
       inputFileBuilder.append('<soap:Header></soap:Header>');
       inputFileBuilder.append('<soap:Body>');
       inputFileBuilder.append('<made4u:made4u xmlns:made4u="http://made4u.info/">');
       inputFileBuilder.append('<made4u:optical_prescription>');
       inputFileBuilder.append(optPreSection);
       inputFileBuilder.append('</made4u:optical_prescription>');
       inputFileBuilder.append('<made4u:user_points>');
       inputFileBuilder.append(new File(userPointsPath).text);
       inputFileBuilder.append('</made4u:user_points>');
       inputFileBuilder.append('<made4u:user_nose>');
       inputFileBuilder.append(new File(nasalMeshPath).text);
       inputFileBuilder.append('</made4u:user_nose>');
       inputFileBuilder.append(customerChoicesSection);
       inputFileBuilder.append('</made4u:made4u>');
       inputFileBuilder.append('<soap:Fault></soap:Fault>');
       inputFileBuilder.append('</soap:Body>');
       inputFileBuilder.append('</soap:Envelope>');
       
       
       return inputFileBuilder.toString();
    }
}
