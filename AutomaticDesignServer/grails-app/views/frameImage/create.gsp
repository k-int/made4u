<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.image.create.page.head.title', default: 'Frame image details - create new')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'frame.database.image.create.page.title', default: 'Individual Frame image')}</h1>

            <g:if test="${frameImageInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${frameImageInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.image.create.properties.title', default: 'Frame image properties:')}</h2>

            <g:uploadForm method="post" name="frameImageForm">
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.reference.label', default: 'Reference')}: <em class="required">*</em></td>
                    <td  colspan="2"><g:textField name="reference" value="${frameImageInstance.reference}" title="${message(code: 'frame.image.reference.required.label', default: 'A reference for the frame image is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.browse.label', default: 'Image')}: <em class="required">*</em></td>
                    <td colspan="2">
                      <input type="file" name="imageFile" id="imageFile" title="${message(code: 'frame.image.upload.required.label', default: 'A frame image must be provided')}" class="required"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.image.type.label', default: 'Image type')}: <em class="required">*</em></td>
                    <td colspan="2">
                      <g:select id="imageType" name="imageType" value="${fieldValue(bean: frameImageInstance, field: 'imageType')}" class="required" title="${message(code: 'frame.image.imageType.required.message', default: 'An image type is required')}" noSelection="${['null': message(code: 'frame.image.imageType.pleaseChoose.message', default: 'Please choose') ] }" from="${availableImageTypes}"/>
                    </td>
                  </tr>
              </table>

              <div class="buttonArea">
                <button type="submit" name="_action_save" class="button" controller="frameImage"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

            </g:uploadForm>

       </div>

    <script type="text/javascript">

      $(document).ready(function () {

        $('form[name="frameImageForm"]').validate( {
            rules: {
              'reference': {
                required: true
              },
              'imageUpload': {
                required: true
              },
              'imageType': {
                required: true
              },
              'price': {
                required: true
              }

            }
          });
        });
    </script>
  </body>
</html>
