package com.k_int.made4u.services

import javax.annotation.PostConstruct

import com.k_int.made4u.serviceInputs.LensCalcInput;
import com.k_int.made4u.serviceInputs.LensCalcOutput;
import com.k_int.made4u.serviceInputs.LensCalcManufacturingOutput;


import groovyx.net.ws.WSClient
import org.apache.cxf.service.factory.ServiceConstructionException
import java.net.ConnectException
import org.apache.cxf.interceptor.Fault

// More info here http://groovy.codehaus.org/GroovyWS
      
class LensCalculationService {

    static transactional = true

    @PostConstruct
    def init() {
      log.debug("LensCalculationService initialising");
    }

    /**
     * Handle FBM calculations
     */
    def calcFBM(calcInputs) {
      // http://192.168.68.18/axis2/services/staff.wsdl.Wsdl/get/samples.oma/Made4uWSF.wsdl
      // INFO: Created classes: org.tempui.omaskeleton.CalculateOmas, org.tempui.omaskeleton.CenterThickness, org.tempui.omaskeleton.MaxEdgeThickness, org.tempui.omaskeleton.MinEdgeThickness, org.tempui.omaskeleton.ObjectFactory, org.tempui.omaskeleton.OmaIn, org.tempui.omaskeleton.OmaList, org.tempui.omaskeleton.OmaOut, org.tempui.omaskeleton.OmaResultList, org.tempui.omaskeleton.Omas, org.tempui.omaskeleton.OmasIn

      def result;
      def message = null;

      def myServicePort;
      try { 
          myServicePort = new WSClient("http://192.168.68.18/axis2/services/staff.wsdl.Wsdl/get/samples.oma/omaskeleton.OmaManager.wsdl", this.class.classLoader)
          myServicePort.initialize()
          myServicePort.setConnectionTimeout(300000);
          
          //      log.debug("Service Port Info")
//      myServicePort.class.methods.each{  log.debug(it); }
//      myServicePort.class.fields.each{  log.debug(it); }

          // myServicePort.changeEndpointAddress(new java.net.URL("http://192.168.68.18/axis2/services/omaskeleton.OmaManager"));
          groovyx.net.ws.AbstractCXFWSClient proxyCXF = (groovyx.net.ws.AbstractCXFWSClient)myServicePort

          proxyCXF.client.getEndpoint().getEndpointInfo().setAddress("http://192.168.68.18/axis2/services/omaskeleton.OmaManager")
          // org.apache.cxf.endpoint.Client client = proxyCXF.client
          // Endpoint endpoint = client.getEndpoint()
          // EndpointInfo epInfo = endpoint.getEndpointInfo()
          // epInfo.setAddress(new java.net.URL("http://192.168.68.18/axis2/services/omaskeleton.OmaManager"))



          // def myServicePort = new WSClient("http://192.168.68.18/axis2/services/staff.wsdl.Wsdl/get/samples.oma/Made4uWSF.wsdl", this.class.classLoader)
          // def ms = myServicePort.metaClass.methods*.name.sort().unique()
          // log.debug("Methods available on proxy are ${ms}");

          def calculate_omas_request = myServicePort.create("org.tempui.omaskeleton.CalculateOmas")
//      def ms = calculate_omas_request.metaClass.methods*.name.sort().unique()
//      log.debug("Methods available on calculate_omas_request are ${ms}");
//      log.debug("** CaclulateOmas info**");
//      calculate_omas_request.class.methods.each{  log.debug(it); }
//      calculate_omas_request.class.fields.each{ log.debug(it); }

          calculate_omas_request.omalist = myServicePort.create("org.tempui.omaskeleton.Omas")
          calculate_omas_request.omalist.demo = true
          calculate_omas_request.omalist.omalist = myServicePort.create("org.tempui.omaskeleton.OmaList")

//      log.debug("inspect1 : ${calculate_omas_request.omalist.omalist.items.inspect()} ${calculate_omas_request.omalist.omalist.item}")
//      ms = calculate_omas_request.omalist.omalist.metaClass.methods*.name.sort().unique()
//      log.debug("Methods available on OmaList are ${ms}")

//      log.debug("calcInputs.size  = " + calcInputs.size());

          for(LensCalcInput anInput: calcInputs) {
              def new_item = myServicePort.create("org.tempui.omaskeleton.OmaIn");
              new_item.jobId=anInput.jobId
              new_item.designId=Integer.parseInt(anInput.designId);
              new_item.designName=anInput.designName;
              new_item.materialId=Integer.parseInt(anInput.materialId);
              new_item.schema=anInput.schema
    //                    log.debug("About to add oma item with job id: " + new_item.jobId + ", designId: " + new_item.designId + ", designName: " + new_item.designName + ", materialId: " + new_item.materialId + ", schema: " + new_item.schema);

              calculate_omas_request.omalist.omalist.items.add(new_item);
              // calculate_omas_request.omalist.item.omalist.item.add(myServicePort.create("org.tempui.omaskeleton.OmaIn"));
              // calculate_omas_request.omalist.item.omalist.item.add(myServicePort.create("org.tempui.omaskeleton.OmaIn"));
          }

    //      log.debug("inspect2 : ${calculate_omas_request.inspect()}")
    //
    //      log.debug("Dump...");
    //      log.debug(calculate_omas_request.dump());
    //      log.debug(calculate_omas_request.omalist.dump());
    //      log.debug(calculate_omas_request.omalist.omalist.dump())
    //      log.debug(new_item.dump())

          try {
    //      log.debug("Calling Service...");
            result = myServicePort.calculateOmas(calculate_omas_request.omalist);
    //      log.debug("Call Completed... result: ${result}");
    //      log.debug("result.size = ${result.size()}");

          } catch (ConnectException ce) {
              log.error("ConnectException thrown when running the lens calculation: " + ce.getMessage());
              ce.printStackTrace();
              result = null;
              message = "Exception thrown when running the lens calculation: " + ce.getMessage();
          }

      } catch (ServiceConstructionException sce) {
          
          log.error("ServiceConstructionException thrown when connecting to lens calculation service: " + sce.getMessage());
          sce.printStackTrace();
          result = null;
          message = "Exception thrown when connecting to the lens calculation service: " + sce.getMessage();
      } catch (Fault f) {
          log.error("Interceptor fault thrown when connecting to the lens calculation service: " + f.getMessage());
          f.printStackTrace();
          result = null;
          message = "Fault exception thrown when connecting to the lens calculation service: " + f.getMessage();
      } catch (Exception e) {
          log.error("Another exception thrown that wasn't specifically caught when talking to the lens calculation service: " + e.getMessage());
          e.printStackTrace();
          result = null;
          message = "Unhandled exception thrown when connecting to the lens calculation service: " + e.getMessage();
      }

      
      // Finished actually talking to the service - now process the outputs if we can
      def calcOutputs = [];
      def success = false;
      def needsRetry = false;

      if ( result ) {
          // Only process the output if we've been successful in calling the service
          success = true;

          result.each { oma_result ->
          
          
              LensCalcOutput nextOutput = new LensCalcOutput();
              nextOutput.setJobId(oma_result.jobId);
              nextOutput.setDesignId(oma_result.designId);
              nextOutput.setMaterialId(oma_result.materialId);
              nextOutput.setDesignName(oma_result.designName);
              nextOutput.setLeftCentreThickness(oma_result.ec.centerThicknessL);
              nextOutput.setRightCentreThickness(oma_result.ec.centerThicknessR);
              nextOutput.setLeftEdgeThicknessMin(oma_result.ebmin.minEdgeThicknessL);
              nextOutput.setRightEdgeThicknessMin(oma_result.ebmin.minEdgeThicknessR);
              nextOutput.setLeftEdgeThicknessMax(oma_result.ebmax.maxEdgeThicknessL);
              nextOutput.setRightEdgeThicknessMax(oma_result.ebmax.maxEdgeThicknessR);
              nextOutput.setMessage(oma_result.message);

    //          log.debug("About to add result with contents: " + nextOutput.toOutput());

              if ("Error Another instance Active (FBM)".equalsIgnoreCase(nextOutput.getMessage()) 
                || "Error Another instance Active".equalsIgnoreCase(nextOutput.getMessage())) {
                  log.info("Lens calculation service reporting that another process is already active..");
                  success = false;
                  needsRetry = true;
              }
              calcOutputs.add(nextOutput);
          }
      }
      
      
      
      def retVal = [needsRetry: needsRetry, success: success, calcOutputs: calcOutputs, message: message];

//      log.debug("About to return from the lens calculation service - retVal.calcOutputs.size = " + retVal.calcOutputs.size());
      return retVal;
    }

  
    /**
     * Handle manufacturing order calculations
     */
    def calcManufacturing(calcInputs) {
      // http://192.168.68.18/axis2/services/staff.wsdl.Wsdl/get/samples.oma/Made4uWSF.wsdl
      // INFO: Created classes: org.tempui.omaskeleton.CalculateOmas, org.tempui.omaskeleton.CenterThickness, org.tempui.omaskeleton.MaxEdgeThickness, org.tempui.omaskeleton.MinEdgeThickness, org.tempui.omaskeleton.ObjectFactory, org.tempui.omaskeleton.OmaIn, org.tempui.omaskeleton.OmaList, org.tempui.omaskeleton.OmaOut, org.tempui.omaskeleton.OmaResultList, org.tempui.omaskeleton.Omas, org.tempui.omaskeleton.OmasIn

      def result;
      def message = null;

      def myServicePort;
      try { 
          myServicePort = new WSClient("http://192.168.68.18/axis2/services/staff.wsdl.Wsdl/get/samples.oma/omaskeleton.OmaManager.wsdl", this.class.classLoader)
          myServicePort.initialize()
          myServicePort.setConnectionTimeout(300000);
          
          //      log.debug("Service Port Info");
//      myServicePort.class.methods.each{  log.debug(it); }
//      myServicePort.class.fields.each{  log.debug(it); }

          // myServicePort.changeEndpointAddress(new java.net.URL("http://192.168.68.18/axis2/services/omaskeleton.OmaManager"));
          groovyx.net.ws.AbstractCXFWSClient proxyCXF = (groovyx.net.ws.AbstractCXFWSClient)myServicePort

          proxyCXF.client.getEndpoint().getEndpointInfo().setAddress("http://192.168.68.18/axis2/services/omaskeleton.OmaManager")
          // org.apache.cxf.endpoint.Client client = proxyCXF.client
          // Endpoint endpoint = client.getEndpoint()
          // EndpointInfo epInfo = endpoint.getEndpointInfo()
          // epInfo.setAddress(new java.net.URL("http://192.168.68.18/axis2/services/omaskeleton.OmaManager"))



//           def myServicePort = new WSClient("http://192.168.68.18/axis2/services/staff.wsdl.Wsdl/get/samples.oma/Made4uWSF.wsdl", this.class.classLoader)
//           def ms = myServicePort.metaClass.methods*.name.sort().unique()
//           log.debug("Methods available on proxy are ${ms}");

          def calculate_omas_request = myServicePort.create("org.tempui.omaskeleton.CalculateLMS")
//       ms = calculate_omas_request.metaClass.methods*.name.sort().unique()
//      log.debug("Methods available on calculate_omas_request are ${ms}");
//      log.debug("** CaclulateOmas info**");
//      calculate_omas_request.class.methods.each{  log.debug(it); }
//      calculate_omas_request.class.fields.each{ log.debug(it); }

          calculate_omas_request.omalist = myServicePort.create("org.tempui.omaskeleton.Omas")
          calculate_omas_request.omalist.demo = false
          calculate_omas_request.omalist.omalist = myServicePort.create("org.tempui.omaskeleton.OmaList")

//      log.debug("inspect1 : ${calculate_omas_request.omalist.omalist.items.inspect()} ${calculate_omas_request.omalist.omalist.item}");
//      def ms = calculate_omas_request.omalist.omalist.metaClass.methods*.name.sort().unique()
//      log.debug( "Methods available on OmaList are ${ms}");

      log.debug("calcInputs.size  = " + calcInputs.size());

          for(LensCalcInput anInput: calcInputs) {
              def new_item = myServicePort.create("org.tempui.omaskeleton.OmaIn");
              new_item.jobId=anInput.jobId
              new_item.designId=Integer.parseInt(anInput.designId);
              new_item.designName=anInput.designName;
              new_item.materialId=Integer.parseInt(anInput.materialId);
              new_item.schema=anInput.schema
    //                    log.debug("About to add oma item with job id: " + new_item.jobId + ", designId: " + new_item.designId + ", designName: " + new_item.designName + ", materialId: " + new_item.materialId + ", schema: " + new_item.schema);

              calculate_omas_request.omalist.omalist.items.add(new_item);
              // calculate_omas_request.omalist.item.omalist.item.add(myServicePort.create("org.tempui.omaskeleton.OmaIn"));
              // calculate_omas_request.omalist.item.omalist.item.add(myServicePort.create("org.tempui.omaskeleton.OmaIn"));
          }

    //      log.debug("inspect2 : ${calculate_omas_request.inspect()}");
    //
    //      log.debug("Dump...");
    //      log.debug( calculate_omas_request.dump());
    //      log.debug( calculate_omas_request.omalist.dump());
    //      log.debug( calculate_omas_request.omalist.omalist.dump());
    //      log.debug( new_item.dump());

          try {
    //      log.debug("Calling Service...");
            result = myServicePort.calculateLMS(calculate_omas_request.omalist);
            log.debug("Call Completed... result: ${result}");
//          log.debug("result.size = ${result.size()}");
          
//          def decoded = result.decodeBase64();
//          log.debug("decoded = ${decoded}");
//          String decodedString = new String(decoded);
//          log.debug("decodedString = ${decodedString}");

          } catch (ConnectException ce) {
              log.error("ConnectException thrown when running the lens calculation: " + ce.getMessage());
              ce.printStackTrace();
              result = null;
              message = "Exception thrown when running the lens calculation: " + ce.getMessage();
          }

      } catch (ServiceConstructionException sce) {
          
          log.error("ServiceConstructionException thrown when connecting to lens calculation service: " + sce.getMessage());
          sce.printStackTrace();
          result = null;
          message = "Exception thrown when connecting to the lens calculation service: " + sce.getMessage();
      } catch (Fault f) {
          log.error("Interceptor fault thrown when connecting to the lens calculation service: " + f.getMessage());
          f.printStackTrace();
          result = null;
          message = "Fault exception thrown when connecting to the lens calculation service: " + f.getMessage();
      } catch (Exception e) {
          log.error("Another exception thrown that wasn't specifically caught when talking to the lens calculation service: " + e.getMessage());
          e.printStackTrace();
          result = null;
          message = "Unhandled exception thrown when connecting to the lens calculation service: " + e.getMessage();
      }

      
      // Finished actually talking to the service - now process the outputs if we can
      def calcOutputs = [];
      def success = false;
      def needsRetry = false;

      if ( result ) {
          // Only process the output if we've been successful in calling the service
          success = true;

              LensCalcManufacturingOutput nextOutput = new LensCalcManufacturingOutput();
              nextOutput.setJobId(result[0]);
              nextOutput.setDesignId(result[1]);
              nextOutput.setMaterialId(result[2]);
              nextOutput.setDesignName(result[3]);
              // Save the LMS in the encoded form so that we can write it out, etc.
              nextOutput.setLms(result[4]);
              nextOutput.setMessage(result[5]);

              log.debug("About to add result with contents: " + nextOutput.toOutput());

              if ("Another instance tries to invoke service (OM)".equalsIgnoreCase(nextOutput.getMessage()) ) {
                  log.info("Lens calculation service reporting that another process is already active..");
                  success = false;
                  needsRetry = true;
                  message = "ALREADY ACTIVE";
              }
              calcOutputs.add(nextOutput);
      }
      
      
      
      def retVal = [needsRetry: needsRetry, success: success, calcOutputs: calcOutputs, message: message];

//      log.debug("About to return from the lens calculation service - retVal.calcOutputs.size = " + retVal.calcOutputs.size());
      return retVal;
    }

}
