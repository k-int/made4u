<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.special.colour.show.page.head.title', default: 'Lens special colour details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <jqui:resources />
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.special.colour.show.title', default: 'Individual lens special colour')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.special.colour.show.properties.heading', default: 'Lens special colour properties:')}</h2>
            <div class="propertiesTable">
                <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.id.label', default: 'Id')}:</td>
                    <td class="value">${colourInstance.id}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.name.label', default: 'Name')}:</td>
                    <td>
                      ${colourInstance.name}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.type.label', default: 'Colour type')}:</td>
                    <td>
                      ${colourInstance.specialColourType.value}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.price.label', default: 'Price')}: <em class="required">*</em></td>
                    <td>
                      ${formatNumber(number:colourInstance.price, format:"0.00")}
                    </td>
                  </tr>
              </table>
            </div>

            <div class="extraConfigOptions">
                <div class="extraButtonArea">
                  <ul>
                    <li><a class="button" href='#' id="mainColourOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'lens.database.special colour.main.colour.options.button.label', default: 'Main colour configuration')}"/> ${message(code: 'lens.database.special.colour.main.colour.options.button.text', default: 'Main colour configuration')}</a></li>
                  </ul>
                </div>
            </div>
            
            <div class="buttonArea">
              <g:form method="post" name="colourForm">
                <g:hiddenField name="id" value="${colourInstance.id}"/>

                <g:link class="button" controller="lensSpecialColour" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'lens.special.colour.showall.label', default: 'Show all lens special colours')}"/> ${message(code: 'lens.special.colour.showall.label', default: 'Show all lens special colours')}</g:link>
                <g:link class="button" action="edit" id="${colourInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'lens.special.colour.edit.button.label', default: 'Edit this colour')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'lens.special.colour.delete.button.label', default: 'Delete this colour')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'lens.special.colour.delete.button.label', default: 'Delete this colour')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>

            </div>
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
                    <td class="tableHeading">${message(code: 'lens.special.colour.l.label', default: 'L')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.L, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.a.label', default: 'a')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.a, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.b.label', default: 'b')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.b, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.tv.label', default: 'TV')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.TV, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.black.label', default: 'Black')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.black, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.yellow.label', default: 'Yellow')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.yellow, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.blue.label', default: 'Blue')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.blue, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.red.label', default: 'Red')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.red, format:"0.0")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.r.code.label', default: 'R code')}:</td>
                    <td>
                      ${colourInstance.rCode}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.g.code.label', default: 'G code')}:</td>
                    <td>
                      ${colourInstance.gCode}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.b.code.label', default: 'B code')}:</td>
                    <td>
                      ${colourInstance.bCode}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.special.colour.transparency.index.label', default: 'Transparency index')}:</td>
                    <td>
                      ${formatNumber(number:colourInstance.transparencyIndex, format:"0.0")}
                    </td>
                  </tr>

         </table>
       </div>

    
    <script type="text/javascript">
                // Main colour config popup
          var mainColourOptions = $('#mainColourConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'lens.special.database.colour.main.colour.options.popup.title', default: 'Main colour configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 500
          });
            
          $('#mainColourOptionsButton').click(function() {
            
            mainColourOptions.dialog('open');
            
            return false;
          });

    </script>
  </body>
</html>
