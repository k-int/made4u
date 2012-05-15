<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.colour.show.page.head.title', default: 'Lens colour details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <jqui:resources />
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.colour.show.title', default: 'Individual lens colour')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.colour.show.properties.heading', default: 'Lens colour properties:')}</h2>
            <div class="propertiesTable">
                <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.id.label', default: 'Id')}:</td>
                    <td class="value">${colourInstance.id}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.name.label', default: 'Name')}:</td>
                    <td>
                      ${colourInstance.name}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.type.label', default: 'Colour type')}:</td>
                    <td>
                      ${colourInstance.colourType.value}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.accept.mirror.label', default: 'Accept mirror?')}:</td>
                    <td>
                      ${colourInstance.acceptMirror}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.price.label', default: 'Price')}: <em class="required">*</em></td>
                    <td>
                      ${formatNumber(number:colourInstance.price, format:"0.00")}
                    </td>
                  </tr>
              </table>
            </div>

            <div class="extraConfigOptions">
                <div class="extraButtonArea">
                  <ul>
                    <li><a class="button" href='#' id="mainColourOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'lens.database.colour.main.colour.options.button.label', default: 'Main colour configuration')}"/> ${message(code: 'lens.database.colour.main.colour.options.button.text', default: 'Main colour configuration')}</a></li>
                    <li><a class="button" href="#" id="gradientColourOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'lens.database.colour.gradient.colour.options.button.label', default: 'Gradient colour configuration')}"/> ${message(code: 'lens.database.colour.gradient.colour.options.button.text', default: 'Gradient configuration')}</a></li>
                  </ul>
                </div>
            </div>
            
            <div class="buttonArea">
              <g:form method="post" name="colourForm">
                <g:hiddenField name="id" value="${colourInstance.id}"/>

                <g:link class="button" controller="lensColour" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'lens.colour.showall.label', default: 'Show all lens colours')}"/> ${message(code: 'lens.colour.showall.label', default: 'Show all lens colours')}</g:link>
                <g:link class="button" action="edit" id="${colourInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'lens.colour.edit.button.label', default: 'Edit this colour')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'lens.colour.delete.button.label', default: 'Delete this colour')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'lens.colour.delete.button.label', default: 'Delete this colour')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>

            </div>
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
                    <td class="tableHeading">${message(code: 'lens.colour.l.label', default: 'L')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.L, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.a.label', default: 'a')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.a, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.b.label', default: 'b')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.b, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.tv.label', default: 'TV')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.TV, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.black.label', default: 'Black')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.black, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.yellow.label', default: 'Yellow')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.yellow, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.blue.label', default: 'Blue')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.blue, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.red.label', default: 'Red')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.red, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.r.code.label', default: 'R code')}:</td>
                    <td>
                      ${colourInstance.rCode}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.g.code.label', default: 'G code')}:</td>
                    <td>
                      ${colourInstance.gCode}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.b.code.label', default: 'B code')}:</td>
                    <td>
                      ${colourInstance.bCode}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.transparency.index.label', default: 'Transparency index')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.transparencyIndex, format:"0.0")}
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
                      ${formatNumber(number:colourInstance.LGradient, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.a.gradient.label', default: 'a')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.aGradient, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.b.gradient.label', default: 'b')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.bGradient, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.tv.gradient.label', default: 'TV')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.TVGradient, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.black.gradient.label', default: 'Black')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.blackGradient, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.yellow.gradient.label', default: 'Yellow')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.yellowGradient, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.blue.gradient.label', default: 'Blue')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.blueGradient, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.red.gradient.label', default: 'Red')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.redGradient, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.r.code.gradient.label', default: 'R code')}:</td>
                    <td>
                      ${colourInstance.rCodeGradient}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.g.code.gradient.label', default: 'G code')}:</td>
                    <td>
                      ${colourInstance.gCodeGradient}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.b.code.gradient.label', default: 'B code')}:</td>
                    <td>
                      ${colourInstance.bCodeGradient}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.colour.transparency.index.gradient.label', default: 'Transparency index')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.transparencyIndexGradient, format:"0.0")}
                    </td>
                  </tr>

         </table>
       </div>

    <script type="text/javascript">
                // Main colour config popup
          var mainColourOptions = $('#mainColourConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'lens.database.colour.main.colour.options.popup.title', default: 'Main colour configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 500
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
              width: 500
          });
            
          $('#gradientColourOptionsButton').click(function() {
            
            gradientColourOptions.dialog('open');
            
            return false;
          });

    </script>
  </body>
</html>
