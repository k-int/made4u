<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.material.edit.page.head.title', default: 'Lens material details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <jqui:resources />
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.material.edit.title', default: 'Individual lens material')}</h1>

            <g:if test="${materialInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${materialInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.material.edit.properties.heading', default: 'Lens material properties:')}</h2>

            <g:form method="post" name="materialForm">
              <g:hiddenField name="id" value="${materialInstance.id}"/>
              <g:hiddenField name="version" value="${materialInstance.version}"/>

              <div class="propertiesTable">
                  <table class="propertiesTable">
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.id.label', default: 'Id')}:</td>
                        <td class="value">${materialInstance.id}</td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.identifier.label', default: 'Identifier')}: <em class="required">*</em></td>
                        <td>
                          <input type="text" name="identifier" value="${materialInstance.identifier}" class="required" title="${message(code: 'lens.material.identifier.required.message', default: 'An identifier is required for the material')}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.reference.label', default: 'Reference')}: <em class="required">*</em></td>
                        <td>
                          <input type="text" name="reference" value="${materialInstance.reference}" class="required" title="${message(code: 'lens.material.reference.required.message', default: 'A reference is required for the material')}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.material.type.label', default: 'Material type')}: <em class="required">*</em></td>
                        <td>
                          <g:select id="materialType" name="materialType" value="${materialInstance.materialType}" class="required" title="${message(code: 'lens.material.material.type.required.message', default: 'A material type is required')}" noSelection="${['': message(code: 'lens.material.material.type.please.choose.message', default: 'Please choose') ] }" from="${availableMaterialTypes}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.focal.type.label', default: 'Focal type')}: <em class="required">*</em></td>
                        <td>
                          <g:select id="focalType" name="focalType" value="${materialInstance.focalType}" class="required" title="${message(code: 'lens.material.focal.type.required.message', default: 'A focal type is required')}" noSelection="${['': message(code: 'lens.material.focal.type.please.choose.message', default: 'Please choose') ] }" from="${availableFocalTypes}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.refraction.index.label', default: 'ND')}: <em class="required">*</em></td>
                        <td>
                          <input type="text" name="refractionIndex" value="${formatNumber(number:materialInstance.refractionIndex, format:"0.0")}" class="required" title="${message(code: 'lens.material.refraction.index.required.message', default: 'A refraction index is required')}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.minimum.drilling.thickness.label', default: 'Minimum drilling thickness')}: <em class="required">*</em></td>
                        <td>
                          <input type="text" name="minimumDrillingThickness" value="${formatNumber(number:materialInstance.minimumDrillingThickness, format:"0.0")}" class="required" title="${message(code: 'lens.material.minimum.drilling.thickness.required.message', default: 'A minimum drilling thickness is required')}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.centre.drilling.thickness.label', default: 'Centre drilling thickness')}: <em class="required">*</em></td>
                        <td>
                          <input type="text" name="centreDrillingThickness" value="${formatNumber(number:materialInstance.centreDrillingThickness, format:"0.0")}" class="required" title="${message(code: 'lens.material.centre.drilling.thickness.required.message', default: 'A centre drilling thickness is required')}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.minimum.edge.thickness.full.label', default: 'Minimum edge thickness (full rim)')}: <em class="required">*</em></td>
                        <td>
                          <input type="text" name="minimumEdgeThicknessFull" value="${formatNumber(number:materialInstance.minimumEdgeThicknessFull, format:"0.0")}" class="required" title="${message(code: 'lens.material.minimum.edge.thickness.full.required.message', default: 'A minimum edge thickness for fully rimmed frames is required')}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.minimum.edge.thickness.semi.label', default: 'Minimum edge thickness (semi-rimless)')}: <em class="required">*</em></td>
                        <td>
                          <input type="text" name="minimumEdgeThicknessSemi" value="${formatNumber(number:materialInstance.minimumEdgeThicknessSemi, format:"0.0")}" class="required" title="${message(code: 'lens.material.minimum.edge.thickness.semi.required.message', default: 'A minimum edge thickness for semi-rimless frames is required')}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.minimum.edge.thickness.rimless.label', default: 'Minimum edge thickness (rimless)')}: <em class="required">*</em></td>
                        <td>
                          <input type="text" name="minimumEdgeThicknessRimless" value="${formatNumber(number:materialInstance.minimumEdgeThicknessRimless, format:"0.0")}" class="required" title="${message(code: 'lens.material.minimum.edge.thickness.rimless.required.message', default: 'A minimum edge thickness for rimless frames is required')}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading"><h3>${message(code: 'lens.material.prices.heading', default: 'Prices')}</h3></td>
                        <td></td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.price.eye.made.label', default: 'Eye made')} ${message(code: 'price.currency.symbol')}:</td>
                        <td>
                          <input type="text" name="priceEyeMade" value="${formatNumber(number:materialInstance.priceEyeMade, format:"0.00")}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.price.life.made.expert.label', default: 'Life made expert')} ${message(code: 'price.currency.symbol')}:</td>
                        <td>
                          <input type="text" name="priceLifeMadeExpert" value="${formatNumber(number:materialInstance.priceLifeMadeExpert, format:"0.00")}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.price.life.made.inicia.label', default: 'Life made inicia')} ${message(code: 'price.currency.symbol')}:</td>
                        <td>
                          <input type="text" name="priceLifeMadeInicia" value="${formatNumber(number:materialInstance.priceLifeMadeInicia, format:"0.00")}"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="tableHeading">${message(code: 'lens.material.price.single.vision.label', default: 'Single vision')} ${message(code: 'price.currency.symbol')}:</td>
                        <td>
                          <input type="text" name="priceSingleVision" value="${formatNumber(number:materialInstance.priceSingleVision, format:"0.00")}"/>
                        </td>
                      </tr>
                  </table>
            </div>
              
              <div class="extraConfigOptions">
                <div class="extraButtonArea">
                  <ul>
                    <li><a class="button" href="#" id="prescriptionOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'lens.database.material.prescription.options.button.label', default: 'Prescription configuration')}"/> ${message(code: 'lens.database.material.prescription.options.button.text', default: 'Prescription configuration')}</a></li>
                  </ul>
                </div>
              </div>

              <input type="hidden" id="sphereMinVals" name="sphereMinVals"/>
              <input type="hidden" id="sphereMaxVals" name="sphereMaxVals"/>
              <input type="hidden" id="cylinderMinVals" name="cylinderMinVals"/>
              <input type="hidden" id="cylinderMaxVals" name="cylinderMaxVals"/>
              <input type="hidden" id="additionMinVals" name="additionMinVals"/>
              <input type="hidden" id="additionMaxVals" name="additionMaxVals"/>
              <input type="hidden" id="prismMinVals" name="prismMinVals"/>
              <input type="hidden" id="prismMaxVals" name="prismMaxVals"/>
              <input type="hidden" id="prescriptionDeleteVals" name="prescriptionDeleteVals"/>

                <div class="buttonArea">
                  <button type="submit" name="_action_update" class="button" controller="lensMaterial"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save changes')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                  <g:link class="button" action="show" id="${materialInstance.id}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
                </div>
              </g:form>
       </div>
    
    
        <div id="prescriptionConfigPopup" class="popup">
          
          <table class="popupTable" id="prescriptionsTable">
            <g:if test="${materialInstance.prescriptions.size() != 0}">
              <tr id="prescriptionsTableHeading">
                 <th>${message(code: 'lens.prescription.config.id.heading', default: 'Id')}</th>
                 <th>${message(code: 'lens.prescription.config.sphere.min.heading', default: 'Sphere Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.sphere.max.heading', default: 'Sphere Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.cylinder.min.heading', default: 'Cylinder Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.cylinder.max.heading', default: 'Cylinder Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.addition.min.heading', default: 'Addition Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.addition.max.heading', default: 'Addition Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.prism.min.heading', default: 'Prism Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.prism.max.haeding', default: 'Prism Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.delete.heading', default: 'Delete?')}</th>
               </tr>
               <g:each in="${materialInstance.prescriptions}" status="i" var="prescriptionInstance">
                 <tr>
                   <td>${prescriptionInstance.id}</td>
                   <td>${formatNumber(number:prescriptionInstance.sphereMin, format:"0.0")}</td>
                   <td>${formatNumber(number:prescriptionInstance.sphereMax, format:"0.0")}</td>
                   <td>${formatNumber(number:prescriptionInstance.cylinderMin, format:"0.0")}</td>
                   <td>${formatNumber(number:prescriptionInstance.cylinderMax, format:"0.0")}</td>
                   <td>${formatNumber(number:prescriptionInstance.additionMin, format:"0.0")}</td>
                   <td>${formatNumber(number:prescriptionInstance.additionMax, format:"0.0")}</td>
                   <td>${formatNumber(number:prescriptionInstance.prismMin, format:"0.0")}</td>
                   <td>${formatNumber(number:prescriptionInstance.prismMax, format:"0.0")}</td>
                   <td><input type="checkbox" name="prescriptionDelete" value="${prescriptionInstance.id}"/></td>
                 </tr>
               </g:each>
            </g:if>
            <g:else>
              <tr id="prescriptionsTableHeading">
                 <th>${message(code: 'lens.prescription.config.id.heading', default: 'Id')}</th>
                 <th>${message(code: 'lens.prescription.config.sphere.min.heading', default: 'Sphere Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.sphere.max.heading', default: 'Sphere Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.cylinder.min.heading', default: 'Cylinder Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.cylinder.max.heading', default: 'Cylinder Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.addition.min.heading', default: 'Addition Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.addition.max.heading', default: 'Addition Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.prism.min.heading', default: 'Prism Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.prism.max.haeding', default: 'Prism Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.delete.heading', default: 'Delete?')}</th>
              </tr>
               <tr id="rightTempleTextureListNoEntry">
                  <td colspan="10">
                    ${message(code: 'lens.database.material.config.popup.prescription.none.entered.message', default: 'No prescriptions currently entered')}
                  </td>
               </tr>
            </g:else>
           </table>
          
           <a class="button" href='#' id="addPrescriptionButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'lens.database.material.config.popup.add.prescription.button.label', default: 'Add prescription')}"/> ${message(code: 'lens.database.material.config.popup.add.prescription.button.text', default: 'Add')}</a>

           <input type="hidden" id="numOfPrescriptionRows" value="${materialInstance.prescriptions.size()}"/>

        </div>


        <script type="text/javascript">
          $(document).ready(function () {

            $('form[name="materialForm"]').validate();
          });  

          
          // Prescription config popup
          var prescriptionOptions = $('#prescriptionConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'lens.database.material.prescription.options.popup.title', default: 'Prescription configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 950,
              beforeClose: function(event, ui) {
                // Merge the various fields into fewer in the main form
                
                // Merge the sphere minimum values
                var newSphereMinVals = "";
                $("input[id*='prescriptionSphereMin-']").each(function() {
                  if ( newSphereMinVals != "" ) {
                    newSphereMinVals = newSphereMinVals + "_BREAK_";
                  }
                  newSphereMinVals = newSphereMinVals + $(this).val();
                });
                $('#sphereMinVals').val(newSphereMinVals);

                // Merge the sphere maximum values
                var newSphereMaxVals = "";
                $("input[id*='prescriptionSphereMax-']").each(function() {
                  if ( newSphereMaxVals != "" ) {
                    newSphereMaxVals = newSphereMaxVals + "_BREAK_";
                  }
                  newSphereMaxVals = newSphereMaxVals + $(this).val();
                });
                $('#sphereMaxVals').val(newSphereMaxVals);

                // Merge the cylinder minimum values
                var newCylinderMinVals = "";
                $("input[id*='prescriptionCylinderMin-']").each(function() {
                  if ( newCylinderMinVals != "" ) {
                    newCylinderMinVals = newCylinderMinVals + "_BREAK_";
                  }
                  newCylinderMinVals = newCylinderMinVals + $(this).val();
                });
                $('#cylinderMinVals').val(newCylinderMinVals);

                // Merge the cylinder maximum values
                var newCylinderMaxVals = "";
                $("input[id*='prescriptionCylinderMax-']").each(function() {
                  if ( newCylinderMaxVals != "" ) {
                    newCylinderMaxVals = newCylinderMaxVals + "_BREAK_";
                  }
                  newCylinderMaxVals = newCylinderMaxVals + $(this).val();
                });
                $('#cylinderMaxVals').val(newCylinderMaxVals);

                // Merge the addition minimum values
                var newAdditionMinVals = "";
                $("input[id*='prescriptionAdditionMin-']").each(function() {
                  if ( newAdditionMinVals != "" ) {
                    newAdditionMinVals = newAdditionMinVals + "_BREAK_";
                  }
                  newAdditionMinVals = newAdditionMinVals + $(this).val();
                });
                $('#additionMinVals').val(newAdditionMinVals);

                // Merge the addition maximum values
                var newAdditionMaxVals = "";
                $("input[id*='prescriptionAdditionMax-']").each(function() {
                  if ( newAdditionMaxVals != "" ) {
                    newAdditionMaxVals = newAdditionMaxVals + "_BREAK_";
                  }
                  newAdditionMaxVals = newAdditionMaxVals + $(this).val();
                });
                $('#additionMaxVals').val(newAdditionMaxVals);

                // Merge the prism minimum values
                var newPrismMinVals = "";
                $("input[id*='prescriptionPrismMin-']").each(function() {
                  if ( newPrismMinVals != "" ) {
                    newPrismMinVals = newPrismMinVals + "_BREAK_";
                  }
                  newPrismMinVals = newPrismMinVals + $(this).val();
                });
                $('#prismMinVals').val(newPrismMinVals);

                // Merge the prism maximum values
                var newPrismMaxVals = "";
                $("input[id*='prescriptionPrismMax-']").each(function() {
                  if ( newPrismMaxVals != "" ) {
                    newPrismMaxVals = newPrismMaxVals + "_BREAK_";
                  }
                  newPrismMaxVals = newPrismMaxVals + $(this).val();
                });
                $('#prismMaxVals').val(newPrismMaxVals);

                // Merge the deletion id values
                var deleteVals = "";
                $("input[name*='prescriptionDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#prescriptionDeleteVals').val(deleteVals);


                
              }
          });
            
          $('#prescriptionOptionsButton').click(function() {
            prescriptionOptions.dialog('open');
            return false;
          });
          
          // Button to add more prescription data
          
          $('#addPrescriptionButton').click(function() {
            
            var numOfRows = parseInt($('#numOfPrescriptionRows').val());
            var prescriptionTable = $('#prescriptionsTable');

              
            var rowClass = "even";
            if ( (numOfRows % 2) == 0 )
                rowClass = "odd";
                
            var newRow = $('<tr class="'+rowClass+'"></tr>');
              
            newRow.append('<td></td>');
            newRow.append($('<td></td>').append('<input type="text" id="prescriptionSphereMin-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
            newRow.append($('<td></td>').append('<input type="text" id="prescriptionSphereMax-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
            newRow.append($('<td></td>').append('<input type="text" id="prescriptionCylinderMin-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
            newRow.append($('<td></td>').append('<input type="text" id="prescriptionCylinderMax-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
            newRow.append($('<td></td>').append('<input type="text" id="prescriptionAdditionMin-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
            newRow.append($('<td></td>').append('<input type="text" id="prescriptionAdditionMax-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
            newRow.append($('<td></td>').append('<input type="text" id="prescriptionPrismMin-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
            newRow.append($('<td></td>').append('<input type="text" id="prescriptionPrismMax-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
            newRow.append('<td></td>');
              
            prescriptionTable.append(newRow);
              
            $('#rightTempleTextureListNoEntry').hide();
            $('#prescriptionsTableHeading').show();
              
            // Update the number of rows so we can stripe the cells
            $('#numOfPrescriptionRows').val(numOfRows+1);

            return false;
          });

        </script>

  </body>
</html>
