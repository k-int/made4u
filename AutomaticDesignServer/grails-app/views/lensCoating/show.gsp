<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.coating.show.page.head.title', default: 'Lens coating details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.coating.show.title', default: 'Individual lens coating')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.coating.show.properties.heading', default: 'Lens coating properties:')}</h2>
            <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'coating.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: coatingInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'coating.reference.label', default: 'Reference')}:</td>
                    <td>${fieldValue(bean: coatingInstance, field: "reference")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'coating.type.label', default: 'Coating type')}:</td>
                    <td>${coatingInstance.coatingType.value}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.red.label', default: 'Red')}:</td>
                    <td>${coatingInstance.red}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.green.label', default: 'Green')}:</td>
                    <td>${coatingInstance.green}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.blue.label', default: 'Blue')}:</td>
                    <td>${coatingInstance.blue}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.transparency.index.label', default: 'Transparency index')}:</td>
                    <td>${formatNumber(number:coatingInstance.transparencyIndex, format:"0.0")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.colour.saturation.transparency.index.label', default: 'Saturation transparency index')}:</td>
                    <td>${formatNumber(number:coatingInstance.saturationTransparencyIndex, format:"0.0")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.rv.label', default: 'RV')}:</td>
                    <td>${formatNumber(number: coatingInstance.rv, format: "0.0")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.dominant.wavelength.label', default: 'Dominant wavelength')}:</td>
                    <td>${formatNumber(number: coatingInstance.dominantWavelength, format: "0.0")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.coating.price.label', default: 'Price')}: <em class="required">*</em></td>
                    <td colspan="2">${formatNumber(number: coatingInstance.price, format: "0.00")}</td>
                  </tr>
            </table>


            <div class="buttonArea">
              <g:form method="post" name="coatingForm">
                <g:hiddenField name="id" value="${coatingInstance.id}"/>

                <g:link class="button" controller="lensCoating" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'coating.showall.label', default: 'Show all lens coatings')}"/> ${message(code: 'coating.showall.label', default: 'Show all lens coatings')}</g:link>
                <g:link class="button" action="edit" id="${coatingInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'coating.edit.button.label', default: 'Edit this coating')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'lens.coating.delete.button.label', default: 'Delete this coating')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'lens.coating.delete.button.label', default: 'Delete this coating')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>

            </div>
       </div>

  </body>
</html>
