package com.k_int.made4u.services

import javax.annotation.PostConstruct
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.ContentType.TEXT


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.MultipartPostMethod;

import java.net.ConnectException;


import static groovyx.net.http.ContentType.XML

class FramesCalculationService {

    static transactional = true
 
    @PostConstruct
    def init() {
      log.debug("FramesCalculationService initialising");
    }

    def serviceMethod() {
      // http://192.168.68.18/axis2/services/staff.wsdl.Wsdl/get/samples.oma/Made4uWSF.wsdl
      // def myServicePort = new WSClient("http://localhost:9091/myservice/services/my?wsdl", this.class.classLoader)
      // myServicePort.initialize()
      // final List resultVals = myServicePort.aMethod();
      // assertEquals(4, resultVals.size());
    }
    
    
    def calc(optPrePath, userPointsPath, nasalMeshPath, dirPath, inputFileName, outputFileName) {
        
        def inputFileContents = constructSoapInputFile(optPrePath, userPointsPath, nasalMeshPath);
        
        def actualInputFile = new File(dirPath, inputFileName);
        actualInputFile.write(inputFileContents);
        
        def retvalSuccess = false;
        def retvalMessage = "";

        try {
            HttpClient postClient = new HttpClient();
            MultipartPostMethod postMethod = new MultipartPostMethod('http://192.168.68.18:80/cgi-bin/framefit/ws-fis2fbm.pl');
            postClient.setConnectionTimeout(300000);


            postMethod.addParameter("upload",actualInputFile);

            int status = postClient.executeMethod(postMethod);
            def data = postMethod.getResponseBodyAsString();
            postMethod.releaseConnection();


            if ( status != 200 ) {
                // Problem with the service!!
                log.error("Problem communicating with the frame calculation service  - status code: " + status);
                log.error("Response body: " + data);
                
                retvalSuccess = false;
                retvalMessage = "Frame calculation response code other than 200. Code: " + status;

            } else {
                log.debug("Communication with frames calculation server completed successfully");
                def returnedFile = new File(dirPath, outputFileName);
                returnedFile.write(data);

                retvalSuccess = true;
            }
        } catch (java.net.ConnectException ce) {
            // A connection exception - log and return failure
            log.error("Connection exception thrown when talking to the frames calculation service: " + ce.getMessage());
            ce.printStackTrace();
            retvalSuccess = false;
            retvalMessage = "Connection exception when talking to the frames calculation service";
        } catch (Exception e) {
            // General unexpected exception
            log.error("Unhandled exception thrown when talking to the frames calculation service: " + e.getMessage());
            e.printStackTrace();
            retvalSuccess = false;
            retvalMessage = "Unhandled exception when talking to the frames calculation service";
        }
        
        def retval = [success: retvalSuccess, message: retvalMessage];
        return retval;
    }
    
   
    def constructSoapInputFile(optPrePath, userPointsPath, nasalMeshPath) {
        StringBuilder inputFileBuilder = new StringBuilder();
        
       inputFileBuilder.append('<?xml version="1.0"?>');
       inputFileBuilder.append('<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">');
       inputFileBuilder.append('<soap:Header></soap:Header>');
       inputFileBuilder.append('<soap:Body>');
       inputFileBuilder.append('<made4u:made4u xmlns:made4u="http://made4u.info/">');
       inputFileBuilder.append('<made4u:optical_prescription>');
       inputFileBuilder.append(new File(optPrePath).text);
       inputFileBuilder.append('</made4u:optical_prescription>');
       inputFileBuilder.append('<made4u:user_points>');
       inputFileBuilder.append(new File(userPointsPath).text);
       inputFileBuilder.append('</made4u:user_points>');
       inputFileBuilder.append('<made4u:user_nose>');
       inputFileBuilder.append(new File(nasalMeshPath).text);
       inputFileBuilder.append('</made4u:user_nose>');
       inputFileBuilder.append('</made4u:made4u>');
       inputFileBuilder.append('<soap:Fault></soap:Fault>');
       inputFileBuilder.append('</soap:Body>');
       inputFileBuilder.append('</soap:Envelope>');
       
       
//       log.debug("About to return the soapInputFile: " + inputFileBuilder.toString());
       
       return inputFileBuilder.toString();
    }
}
