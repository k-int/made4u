<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.frame.create.page.head.title', default: 'Frame details - create new')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-localization"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.frame.create.title', default: 'Individual frame')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${frameInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${frameInstance}"/>            
              </div>
            </g:if>

            <h2>${message(code: 'frame.database.frame.properties.heading', default: 'Frame properties:')}</h2>
            <g:form name="frameForm" method="post">
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.identifier.label', default: 'Identifier')}: <em class="required">*</em></td>
                    <td><g:textField name="identifier" value="${frameInstance.identifier}" title="${message(code: 'frame.identifier.required.label', default: 'An identifier is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.material.label', default: 'Material')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="materialId" name="materialId" value="${frameInstance.material?.id}" class="required" title="${message(code: 'frame.material.required.message', default: 'A material is required')}" noSelection="${['': message(code: 'frame.material.please.choose.message', default: 'Please choose') ] }" from="${materials}" optionKey="id" optionValue="name"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.rim.type.label', default: 'Rim type')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="rimType" name="rimType" value="${frameInstance.rimType}" class="required" title="${message(code: 'frame.rim.type.required.message', default: 'A rim type is required')}" noSelection="${['': message(code: 'frame.rim.type.please.choose.message', default: 'Please choose') ] }" from="${rimTypes}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.gender.label', default: 'Gender')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="gender" name="gender" value="${frameInstance.gender}" class="required" title="${message(code: 'frame.gender.required.message', default: 'A gender is required')}" noSelection="${['': message(code: 'frame.gender.please.choose.message', default: 'Please choose') ] }" from="${genders}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.rim.shape.label', default: 'Rim shape')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="rimShapeId" name="rimShapeId" value="${frameInstance.rimShape?.id}" class="required" title="${message(code: 'frame.rim.shape.required.message', default: 'A rim shape is required')}" noSelection="${['': message(code: 'frame.rim.shape.please.choose.message', default: 'Please choose') ] }" from="${rimShapes}" optionKey="id" optionValue="name"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.endpiece.height.label', default: 'Endpiece height')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="endpieceHeight" name="endpieceHeight" value="${frameInstance.endpieceHeight}" class="required" title="${message(code: 'frame.endpiece.height.required.message', default: 'An endpiece height is required')}" noSelection="${['': message(code: 'frame.endpiece.height.please.choose.message', default: 'Please choose') ] }" from="${endpieceHeights}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.use.label', default: 'Use')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="frameUse" name="frameUse" value="${frameInstance.frameUse}" class="required" title="${message(code: 'frame.use.required.message', default: 'A frame use is required')}" noSelection="${['': message(code: 'frame.use.please.choose.message', default: 'Please choose')]}" from="${frameUses}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'frame.prices.label', default: 'Prices')}:</h3>
                    </td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.fully.personalised.base.price.label', default: 'Fully personalised')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="fullyPersonalisedBasePrice" name="fullyPersonalisedBasePrice" value="${formatNumber(number:frameInstance.fullyPersonalisedBasePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.semi.personalised.base.price.label', default: 'Semi-personalised')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="semiPersonalisedBasePrice" name="semiPersonalisedBasePrice" value="${formatNumber(number:frameInstance.semiPersonalisedBasePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.standard.base.price.label', default: 'Standard')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="standardBasePrice" name="standardBasePrice" value="${formatNumber(number:frameInstance.standardBasePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'frame.time.label', default: 'Times')}:</h3>
                    </td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.fully.personalised.time.label', default: 'Fully personalised')} ${message(code: 'time.day.symbol')}:</td>
                    <td>
                      <g:textField id="fullyPersonalisedProductionTime" name="fullyPersonalisedProductionTime" value="${frameInstance.fullyPersonalisedProductionTime}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.semi.personalised.time.label', default: 'Semi personalised')} ${message(code: 'time.day.symbol')}:</td>
                    <td>
                      <g:textField id="semiPersonalisedProductionTime" name="semiPersonalisedProductionTime" value="${frameInstance.semiPersonalisedProductionTime}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.display.order.label', default: 'Display order')}:</td>
                    <td>
                      <g:textField id="displayOrder" name="displayOrder" value="${frameInstance.displayOrder}"/>
                    </td>
                  </tr>
              </table>
              
              <div class="buttonArea">
                <button type="submit" name="_action_save" class="button" controller="FrameCatalogue"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>
            
            </g:form>

       </div>

    
        <script type="text/javascript">

        $(document).ready(function () {

          $('form[name="frameForm"]').validate( {
            rules: {
              'identifier': {
                required: true
              },
              'materialId': {
                required: true,
                
              },
              'rimType': {
                required: true
              },
              'gender': {
                required: true
              },
              'rimShapeId': {
                required: true
              },
              'endpieceHeight': {
                required: true
              },
              'use': {
                required: true
              },
              'fullyPersonalisedBasePrice': {
                range: [0.0,2000.0]
              },
              'semiPersonalisedBasePrice': {
                range: [0.0,2000.0]
              },
              'standardBasePrice': {
                range: [0.0,2000.0]
              },
              'fullyPersonalisedProductionTime': {
                range: [0,30]
              },
              'semiPersonalisedProductionTime': {
                range: [0,30]
              }

            }
          });
        });
    </script>
  </body>
</html>
