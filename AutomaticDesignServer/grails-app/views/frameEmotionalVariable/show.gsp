<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.emotional.variable.show.page.head.title', default: 'Frame emotional variable details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.emotional.variable.show.title', default: 'Individual Emotional variable')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.emotional.variable.properties.heading', default: 'Emotional variable properties:')}</h2>
            <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'emotional.variable.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: emotionalInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'emotional.variable.name.label', default: 'Name')}:</td>
                    <td>${fieldValue(bean: emotionalInstance, field: "name")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'emotional.description.label', default: 'Description')}:</td>
                    <td>${fieldValue(bean: emotionalInstance, field: "description")}</td>
                  </tr>
            </table>

            <div class="buttonArea">
              <g:form method="post" name="emotionalForm">
                <g:hiddenField name="id" value="${emotionalInstance.id}"/>

                <g:link class="button" controller="frameEmotionalVariable" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'emotional.variable.showall.label', default: 'Show all emotional variables')}"/> ${message(code: 'emotional.variable.showall.label', default: 'Show all emotional variables')}</g:link>
                <g:link class="button" action="edit" id="${emotionalInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'emotional.variable.edit.button.label', default: 'Edit this emotional variable')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'emotional.variable.delete.button.label', default: 'Delete this emotional variable')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'emotional.variable.delete.button.label', default: 'Delete this emotional variable')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>
            </div>
       </div>

  </body>
</html>
