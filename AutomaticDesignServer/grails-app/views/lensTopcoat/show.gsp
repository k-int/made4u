<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.topcoat.show.page.head.title', default: 'Lens topcoat details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'lens.database.topcoat.show.title', default: 'Individual Lens topcoat')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.database.topcoat.properties.heading', default: 'Lens topcoat properties:')}</h2>
            <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.topcoat.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: topcoatInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.topcoat.name.label', default: 'Name')}:</td>
                    <td>${fieldValue(bean: topcoatInstance, field: "name")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.topcoat.description.label', default: 'Description')}:</td>
                    <td>${fieldValue(bean: topcoatInstance, field: "description")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.topcoat.price.label', default: 'Price')}: <em class="required">*</em></td>
                    <td colspan="2">${formatNumber(number: topcoatInstance.price, format:"0.00")}</td>
                  </tr>
            </table>

            <div class="buttonArea">
              <g:form method="post" name="topcoatForm">
                <g:hiddenField name="id" value="${topcoatInstance.id}"/>

                <g:link class="button" controller="lensTopcoat" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'lens.topcoat.showall.label', default: 'Show all lens topcoats')}"/> ${message(code: 'lens.topcoat.showall.label', default: 'Show all lens topcoats')}</g:link>
                <g:link class="button" action="edit" id="${topcoatInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'lens.topcoat.edit.button.label', default: 'Edit this lens topcoat')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'lens.topcoat.delete.button.label', default: 'Delete this lens topcoat')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'lens.topcoat.delete.button.label', default: 'Delete this lens topcoat')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>
            </div>
       </div>

  </body>
</html>
