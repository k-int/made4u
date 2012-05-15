<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.material.show.page.head.title', default: 'Lens material details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <jqui:resources />    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.material.show.title', default: 'Individual lens material')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.material.show.properties.heading', default: 'Lens material properties:')}</h2>
            <div class="propertiesTable">
            <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.identifier.label', default: 'Identifier')}:</td>
                    <td>
                      ${materialInstance.identifier}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.reference.label', default: 'Reference')}:</td>
                    <td>
                      ${materialInstance.reference}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.material.type.label', default: 'Material type')}:</td>
                    <td>
                      ${materialInstance.materialType.value}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.focal.type.label', default: 'Focal type')}:</td>
                    <td>
                      ${materialInstance.focalType.value}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.refraction.index.label', default: 'ND')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.refractionIndex, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.minimum.drilling.thickness.label', default: 'Minimum drilling thickness')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.minimumDrillingThickness, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.centre.drilling.thickness.label', default: 'Centre drilling thickness')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.centreDrillingThickness, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.minimum.edge.thickness.full.label', default: 'Minimum edge thickness (full rim)')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.minimumEdgeThicknessFull, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.minimum.edge.thickness.semi.label', default: 'Minimum edge thickness (semi rimless)')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.minimumEdgeThicknessSemi, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.minimum.edge.thickness.rimless.label', default: 'Minimum edge thickness (rimless)')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.minimumEdgeThicknessRimless, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'lens.material.prices.heading', default: 'Prices')}</h3></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.price.eye.made.label', default: 'Eye made')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.priceEyeMade, format:"0.00")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.price.life.made.expert.label', default: 'Life made expert')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.priceLifeMadeExpert, format:"0.00")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.price.life.made.inicia.label', default: 'Life made inicia')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.priceLifeMadeInicia, format:"0.00")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.price.single.vision.label', default: 'Single vision')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      ${formatNumber(number:materialInstance.priceSingleVision, format:"0.00")}
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

            <div class="buttonArea">
              <g:form method="post" name="materialForm">
                <g:hiddenField name="id" value="${materialInstance.id}"/>

                <g:link class="button" controller="lensMaterial" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'lens.material.showall.label', default: 'Show all lens materials')}"/> ${message(code: 'lens.material.showall.label', default: 'Show all lens materials')}</g:link>
                <g:link class="button" action="edit" id="${materialInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'lens.material.edit.button.label', default: 'Edit this material')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'lens.material.delete.button.label', default: 'Delete this material')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'lens.material.delete.button.label', default: 'Delete this material')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>

            </div>
       </div>
    
        <div id="prescriptionConfigPopup" class="popup">
          
          <table class="popupTable" id="prescriptionsTable">
              <tr id="prescriptionsTableHeading">
                 <th>${message(code: 'lens.prescription.config.id.heading', default: 'Id')}</th>
                 <th>${message(code: 'lens.prescription.config.sphere.min.heading', default: 'Sphere Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.sphere.max.heading', default: 'Sphere Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.cylinder.min.heading', default: 'Cylinder Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.cylinder.max.heading', default: 'Cylinder Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.addition.min.heading', default: 'Addition Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.addition.max.heading', default: 'Addition Maximum')}</th>
                 <th>${message(code: 'lens.prescription.config.prism.min.heading', default: 'Prism Minimum')}</th>
                 <th>${message(code: 'lens.prescription.config.prism.max.heading', default: 'Prism Maximum')}</th>
               </tr>
               <g:if test="${materialInstance.prescriptions.size() != 0}">
                 <g:each in="${materialInstance.prescriptions}" status="i" var="prescriptionInstance">
                   <tr>
                     <td>${prescriptionInstance.id}</td>
                     <td>${formatNumber(number: prescriptionInstance.sphereMin, format:"0.0")}</td>
                     <td>${formatNumber(number: prescriptionInstance.sphereMax, format:"0.0")}</td>
                     <td>${formatNumber(number: prescriptionInstance.cylinderMin, format:"0.0")}</td>
                     <td>${formatNumber(number: prescriptionInstance.cylinderMax, format:"0.0")}</td>
                     <td>${formatNumber(number: prescriptionInstance.additionMin, format:"0.0")}</td>
                     <td>${formatNumber(number: prescriptionInstance.additionMax, format:"0.0")}</td>
                     <td>${formatNumber(number: prescriptionInstance.prismMin, format:"0.0")}</td>
                     <td>${formatNumber(number: prescriptionInstance.prismMax, format:"0.0")}</td>
                   </tr>
                 </g:each>
               </g:if>
               <g:else>
                 <tr id="rightTempleTextureListNoEntry">
                    <td colspan="10">
                      ${message(code: 'lens.database.material.config.popup.prescription.none.entered.message', default: 'No prescriptions currently entered')}
                    </td>
                 </tr>
               </g:else>
           </table>

        </div>

            <script type="text/javascript">
          
          // Prescription config popup
          var prescriptionOptions = $('#prescriptionConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'lens.database.material.prescription.options.popup.title', default: 'Prescription configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 950,
          });
            
          $('#prescriptionOptionsButton').click(function() {
            prescriptionOptions.dialog('open');
            return false;
          });

          </script>

  </body>
</html>
