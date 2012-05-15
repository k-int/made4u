<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.standard.component.show.page.head.title', default: 'Frame standard component details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.standard.component.show.title', default: 'Individual standard component')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.standard.component.properties.heading', default: 'Standard component properties:')}</h2>
            <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'standard.component.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: standardInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'standard.component.reference.label', default: 'Reference')}:</td>
                    <td>${fieldValue(bean: standardInstance, field: "reference")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'standard.component.description.label', default: 'Description')}:</td>
                    <td>${fieldValue(bean: standardInstance, field: "description")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'standard.component.type.label', default: 'Type')}:</td>
                    <td>${fieldValue(bean: standardInstance, field: "standardType")}</td>
                  </tr>
            </table>

            <div class="buttonArea">
              <g:form method="post" name="standardForm">
                <g:hiddenField name="id" value="${standardInstance.id}"/>

                <g:link class="button" controller="frameStandardComponent" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'standard.component.showall.label', default: 'Show all standard components')}"/> ${message(code: 'standard.component.showall.label', default: 'Show all standard components')}</g:link>
                <g:link class="button" action="edit" id="${standardInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'standard.component.edit.button.label', default: 'Edit this standard component')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'standard.component.delete.button.label', default: 'Delete this standard component')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'standard.component.delete.button.label', default: 'Delete this standard component')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>
            </div>
       </div>

  </body>
</html>
