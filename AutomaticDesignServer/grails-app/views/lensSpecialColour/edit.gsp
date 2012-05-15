<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.special.colour.edit.page.head.title', default: 'Lens special colour details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <jqui:resources />
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.special.colour.edit.title', default: 'Individual lens special colour')}</h1>

            <g:if test="${colourInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${colourInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.special.colour.edit.properties.heading', default: 'Lens special colour properties:')}</h2>

            <g:form method="post" name="colourForm">
              <g:hiddenField name="id" value="${colourInstance.id}"/>
              <g:hiddenField name="version" value="${colourInstance.version}"/>

              <div class="propertiesTable">
                <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.id.label', default: 'Id')}:</td>
                    <td class="value">${colourInstance.id}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.name.label', default: 'Name')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="name" value="${colourInstance.name}" class="required" title="${message(code: 'lens.special.colour.name.required.message', default: 'A name is required for the colour')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.type.label', default: 'Colour type')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="specialColourType" name="specialColourType" value="${colourInstance.specialColourType}" class="required" title="${message(code: 'lens.special.colour.type.required.message', default: 'A colour type is required')}" noSelection="${['': message(code: 'lens.special.colour.type.please.choose.message', default: 'Please choose') ] }" from="${availableSpecialColourTypes}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.price.label', default: 'Price')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="price" value="${formatNumber(number:colourInstance.price, format:"0.00")}" class="required" titl${message(code: 'lens.special.colour.price.required.message', default: 'A price is required for the colour')}"/>
                    </td>
                  </tr>

                </table>
              </div>
              
              <input type="hidden" id="L" name="L" value="${formatNumber(number:colourInstance.L, format:"0.0")}"/>
              <input type="hidden" id="a" name="a" value="${formatNumber(number:colourInstance.a, format:"0.0")}"/>
              <input type="hidden" id="b" name="b" value="${formatNumber(number:colourInstance.b, format:"0.0")}"/>
              <input type="hidden" id="TV" name="TV" value="${formatNumber(number:colourInstance.TV, format:"0.0")}"/>
              <input type="hidden" id="black" name="black" value="${formatNumber(number:colourInstance.black, format:"0.0")}"/>
              <input type="hidden" id="yellow" name="yellow" value="${formatNumber(number:colourInstance.yellow, format:"0.0")}"/>
              <input type="hidden" id="blue" name="blue" value="${formatNumber(number:colourInstance.blue, format:"0.0")}"/>
              <input type="hidden" id="red" name="red" value="${formatNumber(number:colourInstance.red, format:"0.0")}"/>
              <input type="hidden" id="rCode" name="rCode" value="${colourInstance.rCode}"/>
              <input type="hidden" id="gCode" name="gCode" value="${colourInstance.gCode}"/>
              <input type="hidden" id="bCode" name="bCode" value="${colourInstance.bCode}"/>
              <input type="hidden" id="transparencyIndex" name="transparencyIndex" value="${formatNumber(number:colourInstance.transparencyIndex, format:"0.0")}"/>
              
              <div class="extraConfigOptions">
                <div class="extraButtonArea">
                  <ul>
                    <li><a class="button" href='#' id="mainColourOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'lens.database.special.colour.main.colour.options.button.label', default: 'Main colour configuration')}"/> ${message(code: 'lens.database.special.colour.main.colour.options.button.text', default: 'Main colour configuration')}</a></li>
                  </ul>
                </div>
              </div>



                <div class="buttonArea">
                  <button type="submit" name="_action_update" class="button" controller="lensSpecialColour"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save changes')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                  <g:link class="button" action="show" id="${colourInstance.id}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
                </div>
              </g:form>
       </div>
    
        <div id="mainColourConfigPopup" class="popup">                
           <table class="popupTable">
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'lens.special.colour.main.colour.heading.label', default: 'Main colour:')}</h3>
                    </td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.l.label', default: 'L')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupL" id="popupL" value="${formatNumber(number:colourInstance.L, format:"0.0")}" class="required" title="${message(code: 'lens.special.colour.l.required.message', default: 'An L value is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.a.label', default: 'a')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupA" id="popupA" value="${formatNumber(number:colourInstance.a, format:"0.0")}" class="required" title="${message(code: 'lens.special.colour.a.required.message', default: 'An a value is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.b.label', default: 'b')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupB" id="popupB" value="${formatNumber(number:colourInstance.b, format:"0.0")}" class="required" title="${message(code: 'lens.special.colour.b.required.message', default: 'A b value is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.tv.label', default: 'TV')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupTV" id="popupTV" value="${formatNumber(number:colourInstance.TV, format:"0.0")}" class="required" title="${message(code: 'lens.special.colour.tv.required.message', default: 'A TV value is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.black.label', default: 'Black')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupBlack" id="popupBlack" value="${formatNumber(number:colourInstance.black, format:"0.0")}" title="${message(code: 'lens.special.colour.black.required.label', default: 'A black colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.yellow.label', default: 'Yellow')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupYellow" id="popupYellow" value="${formatNumber(number:colourInstance.yellow, format:"0.0")}" title="${message(code: 'lens.special.colour.yellow.required.label', default: 'A yellow colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.blue.label', default: 'Blue')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupBlue" id="popupBlue" value="${formatNumber(number:colourInstance.blue, format:"0.0")}" title="${message(code: 'lens.special.colour.blue.required.label', default: 'A blue colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.red.label', default: 'Red')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupRed" id="popupRed" value="${formatNumber(number:colourInstance.red, format:"0.0")}" title="${message(code: 'lens.special.colour.red.required.label', default: 'A red colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.r.code.label', default: 'R code')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupRCode" id="popupRCode" value="${colourInstance.rCode}" title="${message(code: 'lens.special.colour.r.code.required.label', default: 'An R code component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.g.code.label', default: 'G code')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupGCode" id="popupGCode" value="${colourInstance.gCode}" title="${message(code: 'lens.special.colour.g.code.required.label', default: 'A G code component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.b.code.label', default: 'B code')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupBCode" id="popupBCode" value="${colourInstance.bCode}" title="${message(code: 'lens.special.colour.b.code.required.label', default: 'A B code component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.transparency.index.label', default: 'Transparency index')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupTransparencyIndex" id="popupTransparencyIndex" value="${formatNumber(number:colourInstance.transparencyIndex, format:"0.0")}" title="${message(code: 'lens.special.colour.transparency.index.required.label', default: 'A transparency index colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>

           </table>
        </div>

        <script type="text/javascript">
          $(document).ready(function () {

            $('form[name="colourForm"]').validate();
          });  

          // Main colour config popup
          var mainColourOptions = $('#mainColourConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'lens.database.special.colour.main.colour.options.popup.title', default: 'Main colour configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 500,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#L').val($('#popupL').val());
                $('#a').val($('#popupA').val());
                $('#b').val($('#popupB').val());
                $('#TV').val($('#popupTV').val());
                $('#black').val($('#popupBlack').val());
                $('#yellow').val($('#popupYellow').val());
                $('#blue').val($('#popupBlue').val());
                $('#red').val($('#popupRed').val());
                $('#rCode').val($('#popupRCode').val());
                $('#gCode').val($('#popupGCode').val());
                $('#bCode').val($('#popupBCode').val());
                $('#transparencyIndex').val($('#popupTransparencyIndex').val());
              }
          });
            
          $('#mainColourOptionsButton').click(function() {
            
            mainColourOptions.dialog('open');
            
            return false;
          });

        </script>

  </body>
</html>
