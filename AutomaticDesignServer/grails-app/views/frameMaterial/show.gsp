<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.material.show.page.head.title', default: 'Frame material details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.material.show.title', default: 'Individual frame material')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.material.properties.heading', default: 'Material properties:')}</h2>
            <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'material.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: materialInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'material.name.label', default: 'Name')}:</td>
                    <td>${fieldValue(bean: materialInstance, field: "name")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'material.description.label', default: 'Description')}:</td>
                    <td>${fieldValue(bean: materialInstance, field: "description")}</td>
                  </tr>
            </table>

            <div class="buttonArea">
              <g:form method="post" name="materialForm">
                <g:hiddenField name="id" value="${materialInstance.id}"/>

                <g:link class="button" controller="frameMaterial" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'material.showall.label', default: 'Show all frame materials')}"/> ${message(code: 'material.showall.label', default: 'Show all frame materials')}</g:link>
                <g:link class="button" action="edit" id="${materialInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'material.edit.button.label', default: 'Edit this material')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'material.delete.button.label', default: 'Delete this material')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'material.delete.button.label', default: 'Delete this material')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>
            </div>
       </div>

  </body>
</html>
