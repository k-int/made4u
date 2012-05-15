<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.lens.colour.create.page.head.title', default: 'Lens colour details - create new')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <jqui:resources />
    
  </head>
  <body>

 
        <div id="pageBody">
            <h1>${message(code: 'lens.database.lens.colour.create.title', default: 'Individual lens colour')}</h1>

            <g:if test="${colourInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${colourInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.database.lens.colour.create.properties.heading', default: 'Lens colour properties:')}</h2>


            <g:form method="post" name="colourForm">
              <g:hiddenField name="id" value="${colourInstance.id}"/>
              <g:hiddenField name="version" value="${colourInstance.version}"/>

              <div class="propertiesTable">
                <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.id.label', default: 'Id')}:</td>
                    <td class="value">${colourInstance.id}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.name.label', default: 'Name')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="name" value="${colourInstance.name}" class="required" title="${message(code: 'lens.colour.name.required.message', default: 'A name is required for the colour')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.type.label', default: 'Colour type')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="colourType" name="colourType" value="${colourInstance.colourType}" class="required" title="${message(code: 'lens.colour.type.required.message', default: 'A colour type is required')}" noSelection="${['': message(code: 'lens.colour.type.please.choose.message', default: 'Please choose') ] }" from="${availableColourTypes}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.accept.mirror.label', default: 'Accept mirror?')}:</td>
                    <td>
                      <g:checkBox id="acceptMirror" name="acceptMirror" value="${colourInstance.acceptMirror}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.price.label', default: 'Price')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="price" value="${formatNumber(number:colourInstance.price, format:"0.00")}" class="required" titl${message(code: 'lens.colour.price.required.message', default: 'A price is required for the colour')}"/>
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

              <input type="hidden" id="LGradient" name="LGradient" value="${formatNumber(number:colourInstance.LGradient, format:"0.0")}"/>
              <input type="hidden" id="aGradient" name="aGradient" value="${formatNumber(number:colourInstance.aGradient, format:"0.0")}"/>
              <input type="hidden" id="bGradient" name="bGradient" value="${formatNumber(number:colourInstance.bGradient, format:"0.0")}"/>
              <input type="hidden" id="TVGradient" name="TVGradient" value="${formatNumber(number:colourInstance.TVGradient, format:"0.0")}"/>
              <input type="hidden" id="blackGradient" name="blackGradient" value="${formatNumber(number:colourInstance.blackGradient, format:"0.0")}"/>
              <input type="hidden" id="yellowGradient" name="yellowGradient" value="${formatNumber(number:colourInstance.yellowGradient, format:"0.0")}"/>
              <input type="hidden" id="blueGradient" name="blueGradient" value="${formatNumber(number:colourInstance.blueGradient, format:"0.0")}"/>
              <input type="hidden" id="redGradient" name="redGradient" value="${formatNumber(number:colourInstance.redGradient, format:"0.0")}"/>
              <input type="hidden" id="rCodeGradient" name="rCodeGradient" value="${colourInstance.rCodeGradient}"/>
              <input type="hidden" id="gCodeGradient" name="gCodeGradient" value="${colourInstance.gCodeGradient}"/>
              <input type="hidden" id="bCodeGradient" name="bCodeGradient" value="${colourInstance.bCodeGradient}"/>
              <input type="hidden" id="transparencyIndexGradient" name="transparencyIndexGradient" value="${formatNumber(number:colourInstance.transparencyIndexGradient, format:"0.0")}"/>
              
              <div class="extraConfigOptions">
                <div class="extraButtonArea">
                  <ul>
                    <li><a class="button" href='#' id="mainColourOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'lens.database.colour.main.colour.options.button.label', default: 'Main colour configuration')}"/> ${message(code: 'lens.database.colour.main.colour.options.button.text', default: 'Main colour configuration')}</a></li>
                    <li><a class="button" href="#" id="gradientColourOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'lens.database.colour.gradient.colour.options.button.label', default: 'Gradient colour configuration')}"/> ${message(code: 'lens.database.colour.gradient.colour.options.button.text', default: 'Gradient configuration')}</a></li>
                  </ul>
                </div>
              </div>



                <div class="buttonArea">
                  <button type="submit" name="_action_save" class="button" controller="lensColour"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save changes')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                  <g:link class="button" action="show" id="${colourInstance.id}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
                </div>
              </g:form>
       </div>
    
        <div id="mainColourConfigPopup" class="popup">                
           <table class="popupTable">
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'lens.colour.main.colour.heading.label', default: 'Main colour:')}</h3>
                    </td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.l.label', default: 'L')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupL" id="popupL" value="${formatNumber(number:colourInstance.L, format:"0.0")}" class="required" title="${message(code: 'lens.colour.l.required.message', default: 'An L value is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.a.label', default: 'a')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupA" id="popupA" value="${formatNumber(number:colourInstance.a, format:"0.0")}" class="required" title="${message(code: 'lens.colour.a.required.message', default: 'An a value is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.b.label', default: 'b')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupB" id="popupB" value="${formatNumber(number:colourInstance.b, format:"0.0")}" class="required" title="${message(code: 'lens.colour.b.required.message', default: 'A b value is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.tv.label', default: 'TV')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupTV" id="popupTV" value="${formatNumber(number:colourInstance.TV, format:"0.0")}" class="required" title="${message(code: 'lens.colour.tv.required.message', default: 'A TV value is required')}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.black.label', default: 'Black')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupBlack" id="popupBlack" value="${formatNumber(number:colourInstance.black, format:"0.0")}" title="${message(code: 'lens.colour.black.required.label', default: 'A black colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.yellow.label', default: 'Yellow')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupYellow" id="popupYellow" value="${formatNumber(number:colourInstance.yellow, format:"0.0")}" title="${message(code: 'lens.colour.yellow.required.label', default: 'A yellow colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.blue.label', default: 'Blue')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupBlue" id="popupBlue" value="${formatNumber(number:colourInstance.blue, format:"0.0")}" title="${message(code: 'lens.colour.blue.required.label', default: 'A blue colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.red.label', default: 'Red')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupRed" id="popupRed" value="${formatNumber(number:colourInstance.red, format:"0.0")}" title="${message(code: 'lens.colour.red.required.label', default: 'A red colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.r.code.label', default: 'R code')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupRCode" id="popupRCode" value="${colourInstance.rCode}" title="${message(code: 'lens.colour.r.code.required.label', default: 'An R code component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.g.code.label', default: 'G code')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupGCode" id="popupGCode" value="${colourInstance.gCode}" title="${message(code: 'lens.colour.g.code.required.label', default: 'A G code component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.b.code.label', default: 'B code')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupBCode" id="popupBCode" value="${colourInstance.bCode}" title="${message(code: 'lens.colour.b.code.required.label', default: 'A B code component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.transparency.index.label', default: 'Transparency index')}: <em class="required">*</em></td>
                    <td>
                      <input type="text" name="popupTransparencyIndex" id="popupTransparencyIndex" value="${formatNumber(number:colourInstance.transparencyIndex, format:"0.0")}" title="${message(code: 'lens.colour.transparency.index.required.label', default: 'A transparency index colour component is required when creating a colour')}" class="required"/>
                    </td>
                  </tr>

           </table>
        </div>

        <div id="gradientColourConfigPopup" class="popup">                
           <table class="popupTable">
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'lens.colour.gradient.colour.heading.label', default: 'Gradient colour:')}</h3>
                    </td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.l.gradient.label', default: 'L')}:</td>
                    <td>
                      <input type="text" name="popupLGradient" id="popupLGradient" value="${formatNumber(number:colourInstance.LGradient, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.a.gradient.label', default: 'a')}:</td>
                    <td>
                      <input type="text" name="popupAGradient" id="popupAGradient" value="${formatNumber(number:colourInstance.aGradient, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.b.gradient.label', default: 'b')}:</td>
                    <td>
                      <input type="text" name="popupBGradient" id="popupBGradient" value="${formatNumber(number:colourInstance.bGradient, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.tv.gradient.label', default: 'TV')}:</td>
                    <td>
                      <input type="text" name="popupTVGradient" id="popupTVGradient" value="${formatNumber(number:colourInstance.TVGradient, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.black.gradient.label', default: 'Black')}:</td>
                    <td>
                      <input type="text" name="popupBlackGradient" id="popupBlackGradient" value="${formatNumber(number:colourInstance.blackGradient, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.yellow.gradient.label', default: 'Yellow')}:</td>
                    <td>
                      <input type="text" name="popupYellowGradient" id="popupYellowGradient" value="${formatNumber(number:colourInstance.yellowGradient, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.blue.gradient.label', default: 'Blue')}:</td>
                    <td>
                      <input type="text" name="popupBlueGradient" id="popupBlueGradient" value="${formatNumber(number:colourInstance.blueGradient, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.red.gradient.label', default: 'Red')}:</td>
                    <td>
                      <input type="text" name="popupRedGradient" id="popupRedGradient" value="${formatNumber(number:colourInstance.redGradient, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.r.code.gradient.label', default: 'R code')}:</td>
                    <td>
                      <input type="text" name="popupRCodeGradient" id="popupRCodeGradient" value="${colourInstance.rCodeGradient}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.g.code.gradient.label', default: 'G code')}:</td>
                    <td>
                      <input type="text" name="popupGCodeGradient" id="popupGCodeGradient" value="${colourInstance.gCodeGradient}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.b.code.gradient.label', default: 'B code')}:</td>
                    <td>
                      <input type="text" name="popupBCodeGradient" id="popupBCodeGradient" value="${colourInstance.bCodeGradient}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.transparency.index.gradient.label', default: 'Transparency index')}:</td>
                    <td>
                      <input type="text" name="popupTransparencyIndexGradient" id="popupTransparencyIndexGradient" value="${formatNumber(number:colourInstance.transparencyIndexGradient, format:"0.0")}"/>
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
              title: "${message(code: 'lens.database.colour.main.colour.options.popup.title', default: 'Main colour configuration')}",
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
          
          // Gradient colour config popup
          var gradientColourOptions = $('#gradientColourConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'lens.database.colour.gradient.colour.options.popup.title', default: 'Gradient colour configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 500,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#LGradient').val($('#popupLGradient').val());
                $('#aGradient').val($('#popupAGradient').val());
                $('#bGradient').val($('#popupBGradient').val());
                $('#TVGradient').val($('#popupTVGradient').val());
                $('#blackGradient').val($('#popupBlackGradient').val());
                $('#yellowGradient').val($('#popupYellowGradient').val());
                $('#blueGradient').val($('#popupBlueGradient').val());
                $('#redGradient').val($('#popupRedGradient').val());
                $('#rCodeGradient').val($('#popupRCodeGradient').val());
                $('#gCodeGradient').val($('#popupGCodeGradient').val());
                $('#bCodeGradient').val($('#popupBCodeGradient').val());
                $('#transparencyIndexGradient').val($('#popupTransparencyIndexGradient').val());
              }
          });
            
          $('#gradientColourOptionsButton').click(function() {
            
            gradientColourOptions.dialog('open');
            
            return false;
          });

        </script>

  </body>
</html>
