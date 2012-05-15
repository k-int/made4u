<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.colour.edit.page.head.title', default: 'Frame colour details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'frame.colour.edit.title', default: 'Individual frame colour')}</h1>

            <g:if test="${colourInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${colourInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.colour.edit.properties.heading', default: 'Frame colour properties:')}</h2>
            
            <g:form method="post" name="colourForm">
              <g:hiddenField name="id" value="${colourInstance.id}"/>
              <g:hiddenField name="version" value="${colourInstance.version}"/>
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.id.label', default: 'Id')}:</td>
                    <td class="value" colspan="2">
                      ${fieldValue(bean: colourInstance, field: "id")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.name.label', default: 'Reference')}: <em class="required">*</em></td>
                    <td  colspan="2"><g:textField name="name" value="${colourInstance.name}" title="${message(code: 'frame.colour.name.required.label', default: 'A name for the colour is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.red.label', default: 'Red')}: <em class="required">*</em></td>
                    <td colspan="2"><input type="text" name="red" id="red" value="${colourInstance.red}" title="${message(code: 'frame.colour.red.required.label', default: 'A value must be specified for the red component')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.green.label', default: 'Green')}: <em class="required">*</em></td>
                    <td colspan="2"><input type="text" name="green" id="green" value="${colourInstance.green}" title="${message(code: 'frame.colour.green.required.label', default: 'A value must be specified for the green component')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.blue.label', default: 'Blue')}: <em class="required">*</em></td>
                    <td colspan="2"><input type="text" name="blue" id="blue" value="${colourInstance.blue}" title="${message(code: 'frame.colour.blue.required.label', default: 'A value must be specified for the blue component')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.price.label', default: 'Price')} ${message(code: 'price.currency.symbol')}:</td>
                    <td colspan="2"><input type="text" name="price" id="price" value="${formatNumber(number:colourInstance.price, format:"0.00")}"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.time.label', default: 'Time')} ${message(code: 'time.day.symbol')}:</td>
                    <td colspan="2"><input type="text" name="productionTime" id="productionTime" value="${colourInstance.productionTime}"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.description.label', default: 'Description')}:</td>
                    <td colspan="2"><g:textField name="description" value="${colourInstance.description}"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.example.label', default: 'Example')}:</td>
                    <td id="exampleColourBox" name="exampleColourBox" style='background: rgba(${colourInstance.red}, ${colourInstance.green}, ${colourInstance.blue}, ${alphaValue})'>&nbsp; &nbsp;</td>
                    <td><a class="button" onclick="javascript:updateExampleColour()"><img src="${createLinkTo(dir, '/images')}/refresh.png" alt="${message(code: 'frame.colour.example.update.image.label', default: 'Update example colour')}"/> ${message(code: 'frame.colour.example.update.button.label', default: 'Update example')}</a></td>
                  </tr>
              </table>

              <div class="buttonArea">
                <button type="submit" name="_action_update" class="button" controller="frameBaseColour"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save changes')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="show" id="${colourInstance.id}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

            </g:form>

       </div>

    <script type="text/javascript">
      function updateExampleColour() {

        var transparencyIndex = $("#transparencyIndex").val()
        var alpha = 1;
        if ( transparencyIndex != null && transparencyIndex != 0 ) {
          alpha = transparencyIndex / 100;
        }

        var rgba =  "rgba(" + $("#red").val() + ", " + $("#green").val() + ", " + $("#blue").val() + ", " + alpha + ")";

        $('#exampleColourBox').css('background-color', rgba);
      
      }

      $(document).ready(function () {
      
        updateExampleColour();

        $('form[name="colourForm"]').validate( {
            rules: {
              'red': {
                required: true
              },
              'green': {
                required: true
              },
              'blue': {
                required: true
              }
            }
          });
        });

    </script>
  </body>
</html>
