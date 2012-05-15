<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.image.edit.page.head.title', default: 'Frame image details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'frame.database.image.edit.page.title', default: 'Individual Frame image')}</h1>

            <g:if test="${frameImageInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${frameImageInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.image.preview.heading', default: 'Preview:')}</h2>
            <img class="preview" src="${fieldValue(bean: frameImageInstance, field: "path")}" alt="${message(code: 'frame.image.preview.alt', default: 'Image preview')}"/>

            <h2>${message(code: 'frame.database.image.edit.properties.title', default: 'Frame image properties:')}</h2>

            <g:form method="post" name="frameImageForm">
              <g:hiddenField name="id" value="${frameImageInstance.id}"/>
              <g:hiddenField name="version" value="${frameImageInstance.version}"/>
              <g:hiddenField name="path" value="${frameImageInstance.path}"/>
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.id.label', default: 'Id')}:</td>
                    <td class="value" colspan="2">
                      ${fieldValue(bean: frameImageInstance, field: "id")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.reference.label', default: 'Reference')}: <em class="required">*</em></td>
                    <td  colspan="2"><g:textField name="reference" value="${frameImageInstance.reference}" title="${message(code: 'frame.image.reference.required.label', default: 'A reference for the frame image is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.path.label', default: 'Path')}:</td>
                    <td colspan="2">
                      <a href="${fieldValue(bean: frameImageInstance, field: 'path')}" title="${message(code: 'frame.image.link.title', default: 'View the image')}">${fieldValue(bean: frameImageInstance, field: 'path')}</a>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.type.label', default: 'Image type')}: <em class="required">*</em></td>
                    <td colspan="2">
                      <g:select id="imageType" name="imageType" value="${fieldValue(bean: frameImageInstance, field: 'imageType')}" class="required" title="${message(code: 'frame.image.imageType.required.message', default: 'An image type is required')}" noSelection="${['null': 'Please choose' ] }" from="${availableImageTypes}"/>
                    </td>
                  </tr>
              </table>

              <div class="buttonArea">
                <button type="submit" name="_action_update" class="button" controller="frameImage"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save changes')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="show" id="${frameImageInstance.id}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

            </g:form>

       </div>

    <script type="text/javascript">

      $(document).ready(function () {

        $('form[name="frameImageForm"]').validate( {
            rules: {
              'name': {
                required: true
              }
            }
          });
        });
    </script>
  </body>
</html>
