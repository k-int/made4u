<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'get.design.page.head.title', default: 'Get Design')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'get.design.title', default: 'Get design')}</h1>

            <g:uploadForm action="save" method="POST" name="designForm">
              <p>
                ${message(code: 'get.design.para.1', default: "Specify the required input files for the design and then click 'Request designs' to receive the possible design choices. This form simulates a RESTful call for an individual design which can also be performed by POSTing to the ")}
                ${createLinkTo(dir:'')}/getDesign/show
                ${message(code: 'get.design.para.2', default: "URL with values for each of the fields listed in brackets below.")}
              </p>
              <div class="inputForm">
                <label>${message(code: 'get.design.order.id.label', default: 'Order ID')} (orderId):<input type="text" name="orderId" id="orderId" title="${message(code: 'get.design.order.id.required.message', default: 'An order ID is required')}"/></label>
                <label>${message(code: 'get.design.morpho.file.label', default: 'Morphological data')} (morphoFile):<input type="file" name="morphoFile" id="morphoFile" title="${message(code: 'get.design.morphoFile.required.message', default: 'Select a morphological data file or enter the string contents')}"/></label>
                <label>${message(code: 'get.design.morpho.file.string.label', default: 'Morphological data file contents')} (morphoFileString):<input type="text" name="morphoFileString" id="morphoFileString" title="${message(code: 'get.design.morphoFile.required.message', default: 'Select a morphological data file or enter the string contents')}"/></label>
                <label>${message(code: 'get.design.optical.prescription.file.label', default: 'Optical prescription')} (optPreFile):<input type="file" name="optPreFile" id="optPreFile" title="${message(code: 'get.design.optical.prescription.file.required.message', default: 'Select an optical prescription file or enter the string contents')}"/></label>
                <label>${message(code: 'get.design.optical.prescription.file.string.label', default: 'Optical prescription file contents')} (optPreFileString):<input type="text" name="optPreFileString" id="optPreFileString" title="${message(code: 'get.design.optical.prescription.file.required.message', default: 'Select an optical prescription file or enter the string contents')}"/></label>
                <label>${message(code: 'get.design.nasal.mesh.file.label', default: 'Nasal mesh')} (nasalFile):<input type="file" name="nasalFile" id="nasalFile" title="${message(code: 'get.design.nasal.mesh.file.required.message', default: 'Select a nasal mesh file or enter the string contents')}"/></label>
                <label>${message(code: 'get.design.nasal.mesh.file.string.label', default: 'Nasal mesh file contents')} (nasalFileString):<input type="text" name="nasalFileString" id="nasalFileString" title="${message(code: 'get.design.nasal.mesh.file.required.message', default: 'Select a nasal mesh file or enter the string contents')}"/></label>
                <label>&nbsp;
                  <input type="submit" value="${message(code: 'get.design.submit.text', default: 'Request designs')}"/>
                </label>
              </div>
            </g:uploadForm>
        </div>

           <script type="text/javascript">
//      $(document).ready(function () {
//        $('form[name="designForm"]').validate( {
//          rules: {
//            'morphoFile': {
//              required: true
//            },
//            'optPreFile': {
//              required: true
//            },
//            'nasalFile': {
//              required: true
//            },
//            'orderId': {
//              required: true
//            }
//          }
//        });
//      });
    
    </script>
  </body>
</html>
