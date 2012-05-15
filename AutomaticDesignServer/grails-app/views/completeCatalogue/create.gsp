<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'get.complete.catalogue.page.head.title', default: 'Get Complete Catalogue')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'get.complete.catalogue.title', default: 'Get complete catalogue')}</h1>

            <g:uploadForm action="save" method="POST" name="designForm">
              <p>
                ${message(code: 'get.design.para.1', default: "Specify the required input files for the design and then click 'Request catalogue' to receive the possible design choices. This form simulates a RESTful call for an individual design which can also be performed by POSTing to the ")}
                ${createLinkTo(dir:'')}/completeCatalogue/show
                ${message(code: 'get.design.para.2', default: "URL with values for each of the fields listed in brackets below.")}
              </p>
              <div class="inputForm">
                <label>${message(code: 'get.complete.catalogue.order.id.label', default: 'Order ID')} (orderId):<input type="text" name="orderId" id="orderId" title="${message(code: 'get.complete.catalogue.order.id.required.message', default: 'An order ID is required')}"/></label>
                <label>${message(code: 'get.complete.catalogue.morpho.file.label', default: 'Morphological data')} (morphoFile):<input type="file" name="morphoFile" id="morphoFile" title="${message(code: 'get.complete.catalogue.morphoFile.required.message', default: 'Select a morphological data file or enter the string contents')}"/></label>
                <label>${message(code: 'get.complete.catalogue.morpho.file.string.label', default: 'Morphological data file contents')} (morphoFileString):<input type="text" name="morphoFileString" id="morphoFileString" title="${message(code: 'get.complete.catalogue.morphoFile.required.message', default: 'Select a morphological data file or enter the string contents')}"/></label>
                <label>${message(code: 'get.complete.catalogue.optical.prescription.file.label', default: 'Optical prescription')} (optPreFile):<input type="file" name="optPreFile" id="optPreFile" title="${message(code: 'get.complete.catalogue.optical.prescription.file.required.message', default: 'Select an optical prescription file or enter the string contents')}"/></label>
                <label>${message(code: 'get.complete.catalogue.optical.prescription.file.string.label', default: 'Optical prescription file contents')} (optPreFileString):<input type="text" name="optPreFileString" id="optPreFileString" title="${message(code: 'get.complete.catalogue.optical.prescription.file.required.message', default: 'Select an optical prescription file or enter the string contents')}"/></label>
                <label>${message(code: 'get.complete.catalogue.nasal.mesh.file.label', default: 'Nasal mesh')} (nasalFile):<input type="file" name="nasalFile" id="nasalFile" title="${message(code: 'get.complete.catalogue.nasal.mesh.file.required.message', default: 'Select a nasal mesh file or enter the string contents')}"/></label>
                <label>${message(code: 'get.complete.catalogue.nasal.mesh.file.string.label', default: 'Nasal mesh file contents')} (nasalFileString):<input type="text" name="nasalFileString" id="nasalFileString" title="${message(code: 'get.complete.catalogue.nasal.mesh.file.required.message', default: 'Select a nasal mesh file or enter the string contents')}"/></label>
                <label>&nbsp;
                  <input type="submit" value="${message(code: 'get.complete.catalogue.submit.text', default: 'Request catalogue')}"/>
                </label>
              </div>
            </g:uploadForm>
        </div>

  </body>
</html>
