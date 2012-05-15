<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.coating.create.page.head.title', default: 'Lens coating details - create new')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 
        <div id="pageBody">
            <h1>${message(code: 'lens.database.coating.create.title', default: 'Individual lens coating')}</h1>

            <g:if test="${coatingInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${coatingInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.database.coating.create.properties.heading', default: 'Lens coating properties:')}</h2>

            <g:form method="post" name="coatingForm">
              <g:hiddenField name="version" value="${coatingInstance.version}"/>

              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'coating.reference.label', default: 'Reference')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="reference" value="${fieldValue(bean: coatingInstance, field: 'reference')}" class="required" title="${message(code: 'coating.reference.required.message', default: 'A reference is required for the coating')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'coating.type.label', default: 'Coating type')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="coatingType" name="coatingType" value="${fieldValue(bean: coatingInstance, field: 'coatingType')}" class="required" title="${message(code: 'coating.coatingType.required.message', default: 'A coating type is required')}" noSelection="${['': message(code: 'coating.type.please.choose.message', default: 'Please choose') ] }" from="${availableCoatingTypes}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.red.label', default: 'Red')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="red" value="${coatingInstance.red}" title="${message(code: 'lens.coating.colour.red.required.label', default: 'A red colour component is required when creating a coating')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.green.label', default: 'Green')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="green" value="${coatingInstance.green}" title="${message(code: 'lens.coating.colour.green.required.label', default: 'A green colour component is required when creating a coating')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.blue.label', default: 'Blue')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="blue" value="${coatingInstance.blue}" title="${message(code: 'lens.coating.colour.blue.required.label', default: 'A blue colour component is required when creating a coating')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.transparency.index.label', default: 'Transparency index')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="transparencyIndex" value="${formatNumber(number:coatingInstance.transparencyIndex, format:"0.0")}" title="${message(code: 'lens.coating.colour.transparency.index.required.label', default: 'A transparency index colour component is required when creating a coating')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.saturation.transparency.index.label', default: 'Saturation transparency index')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="saturationTransparencyIndex" value="${formatNumber(number:coatingInstance.saturationTransparencyIndex, format:"0.0")}" title="${message(code: 'lens.coating.colour.saturation.transparency.index.required.label', default: 'A saturation transparency index colour component is required when creating a coating')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.rv.label', default: 'RV')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="rv" value="${formatNumber(number:coatingInstance.rv, format:"0.0")}" title="${message(code: 'lens.coating.rv.required.label', default: 'An RV component is required when creating a coating')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.dominant.wavelength.label', default: 'Dominant wavelength')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="dominantWavelength" value="${formatNumber(number:coatingInstance.dominantWavelength, format:"0.0")}" title="${message(code: 'lens.coating.dominant.wavelength.required.label', default: 'A dominant wavelength value is required when creating a coating')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.price.label', default: 'Price')}: <em class="required">*</em></td>
                    <td colspan="2"><input type="text" name="price" id="price" value="${formatNumber(number:coatingInstance.price, format:"0.00")}" class="required" title="${message(code: 'lens.coating.price.required.label', default: 'A price is required')}"/></td>
                  </tr>
                </table>


                <div class="buttonArea">
                  <button type="submit" name="_action_save" class="button" controller="lensCoating"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'coating.create.save.button.label', default: 'Save coating')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                  <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
                </div>
              </g:form>
       </div>
    
    <script type="text/javascript">
      $(document).ready(function () {

          $('form[name="coatingForm"]').validate();
        });  
    </script>

  </body>
</html>
