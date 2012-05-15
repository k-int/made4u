<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.image.show.head.title', default: 'Frame image details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.image.show.page.title', default: 'Individual frame image')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.image.preview.heading', default: 'Preview:')}</h2>
            <img class="preview" src="${fieldValue(bean: frameImageInstance, field: "path")}" alt="${message(code: 'frame.image.preview.alt', default: 'Image preview')}"/>

            <h2>${message(code: 'frame.image.show.properties.heading', default: 'Frame image properties:')}</h2>
            <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: frameImageInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.reference.label', default: 'Reference')}:</td>
                    <td>${fieldValue(bean: frameImageInstance, field: "reference")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.path.label', default: 'Path')}:</td>
                    <td>${fieldValue(bean: frameImageInstance, field: "path")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.type.label', default: 'Image type')}:</td>
                    <td>${fieldValue(bean: frameImageInstance, field: "imageType")}</td>
                  </tr>
            </table>

            <div class="buttonArea">
              <g:form method="post" name="frameImageForm">
                <g:hiddenField name="id" value="${frameImageInstance.id}"/>

                <g:link class="button" controller="frameImage" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'frame.image.showall.label', default: 'Show all frame images')}"/> ${message(code: 'frame.image.showall.label', default: 'Show all frame images')}</g:link>
                <g:link class="button" action="edit" id="${frameImageInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'frame.image.edit.button.label', default: 'Edit this image')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'frame.image.delete.button.label', default: 'Delete this frame image')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'frame.image.delete.button.label', default: 'Delete this frame image')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>
            </div>
       </div>

  </body>
</html>
