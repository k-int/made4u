<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.material.create.page.head.title', default: 'Lens material details - create new')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 
        <div id="pageBody">
            <h1>${message(code: 'lens.database.material.create.title', default: 'Individual lens material')}</h1>

            <g:if test="${materialInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${materialInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.database.material.create.properties.heading', default: 'Lens material properties:')}</h2>

            <g:form method="post" name="materialForm">
              <g:hiddenField name="version" value="${materialInstance.version}"/>

              <table class="propertiesTable">
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
                      <input type="text" name="minimumEdgeThicknessFull" value="${formatNumber(number:materialInstance.minimumEdgeThicknessFull, format:"0.0")}" class="required" title="${message(code: 'lens.material.minimum.edge.thickness.full.required.message', default: 'A minimum edge thickness for full rimmed frames is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.minimum.edge.thickness.semi.label', default: 'Minimum edge thickness (semi-rimless)')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="minimumEdgeThicknessSemi" value="${formatNumber(number: materialInstance.minimumEdgeThicknessSemi, format: "0.0")}" class="required" title="${message(code: 'lens.material.minimum.edge.thickness.semi.required.message', default: 'A minimum edge thickness for semi-rimless frames is required')}"/>
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
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.material.prescription.label', default: 'Prescription')}:</td>
                    <td>
                      ${message(code: 'lens.material.prescription.create.page.message', default: 'A material must be saved before you can specify any prescription properties')}</td>
                    </td>
                  </tr>
                </table>


                <div class="buttonArea">
                  <button type="submit" name="_action_save" class="button" controller="lensMaterial"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'lens.material.create.save.button.label', default: 'Save material')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                  <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
                </div>
              </g:form>
       </div>
    
    <script type="text/javascript">
      $(document).ready(function () {

          $('form[name="materialForm"]').validate();
        });  
    </script>

  </body>
</html>
