package com.k_int.made4u.services

import grails.test.*
import org.springframework.core.io.*

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.ContentType.TEXT


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.MultipartPostMethod;

import java.net.ConnectException;


import static groovyx.net.http.ContentType.XML


import com.k_int.made4u.serviceInputs.LensCalcInput;

class LensCalculationServiceTests extends GrailsUnitTestCase {

    def lensCalculationService = new LensCalculationService()

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
//      log.debug("Lens Calculation Service Test.....")
//      Resource resource = new ClassPathResource('/examplePR.brs')
//      File f = resource.getFile()
//      def example_brs = f.text
//      // log.debug("Example brs : ${example_brs}");
//      def result = lensCalculationService.calc(example_brs, "Job00001", 1, 1)
//      result.each { oma_result ->
//        log.debug("result for job: ${oma_result.jobId} design ${oma_result.designId} ${oma_result.materialId}");
//        log.debug("ebmax entry ${oma_result.ebmax.maxEdgeThicknessR} ${oma_result.ebmax.maxEdgeThicknessL}");
//        log.debug("ebmin entry ${oma_result.ebmin.minEdgeThicknessR} ${oma_result.ebmin.minEdgeThicknessL}");
//        log.debug("ecthk entry ${oma_result.ec.centerThicknessR} ${oma_result.ec.centerThicknessL}");
//      }
//      log.debug("Done");
    }
    
    void testManufacturingOrder() {
        
//        log.debug("Lens manufacturing order service test..."):
////        Resource resource = new ClassPathResource('albertoInput.json');
//        Resource resource = new ClassPathResource('shouldWorkFromAlberto.json');
//        
//        File f = resource.getFile();
//        
//        def inputData = [LensCalcInput.parseMyDebugFile(f)];
//  
//
//        def result = lensCalculationService.calcManufacturing(inputData);
//        
//        log.debug("result = ${result}");
//        
//        
    }
    
    void testManualOrder() {

        
//        try {
//            HttpClient postClient = new HttpClient();
//            MultipartPostMethod postMethod = new MultipartPostMethod('http://192.168.68.18/axis2/services/omaskeleton.OmaManager');
//            postClient.setConnectionTimeout(300000);
//
//
//            File actualInputFile = new File("/home/rpb/Desktop/lensCalcManualSoap.xml");
//            postMethod.addParameter("upload",actualInputFile);
//
//            int status = postClient.executeMethod(postMethod);
//            def data = postMethod.getResponseBodyAsString();
//            postMethod.releaseConnection();
//
//
//            if ( status != 200 ) {
//                // Problem with the service!!
//                log.error("Problem communicating with the frame manufacturing order calculation service - status code: " + status);
//                log.error("Response body: " + data);
//                
//
//            } else {
//                log.error("Communication with frames manufacturing calculation server completed successfully");
//
//                log.error(data);
//
//            
//            }
//        } catch (java.net.ConnectException ce) {
//            // A connection exception - log and return failure
//            ce.printStackTrace();
//        } catch (Exception e) {
//            // Generic catch all..
//            e.printStackTrace();
//        }
//        

    }
}
