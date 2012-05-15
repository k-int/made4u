<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.shape.edit.page.head.title', default: 'Frame shape details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'frame.shape.edit.page.title', default: 'Individual Frame shape')}</h1>

            <g:if test="${shapeInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${shapeInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.shape.edit.page.properties.heading', default: 'Frame shape properties:')}</h2>

            <g:form method="post" name="shapeForm">
              <g:hiddenField name="id" value="${shapeInstance.id}"/>
              <g:hiddenField name="version" value="${shapeInstance.version}"/>
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'shape.id.label', default: 'Id')}:</td>
                    <td class="value" colspan="2">
                      ${fieldValue(bean: shapeInstance, field: "id")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'shape.name.label', default: 'Name')}: <em class="required">*</em></td>
                    <td  colspan="2"><g:textField name="name" value="${shapeInstance.name}" title="${message(code: 'shape.name.required.label', default: 'A name for the shape is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'shape.description.label', default: 'Description')}:</td>
                    <td colspan="2"><input type="text" name="description" id="description" value="${fieldValue(bean: shapeInstance, field: "description")}"/></td>
                  </tr>
              </table>

              <div class="buttonArea">
                <button type="submit" name="_action_update" class="button" controller="frameShape"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="show" id="${shapeInstance.id}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

            </g:form>

       </div>

    <script type="text/javascript">

      $(document).ready(function () {

        $('form[name="shapeForm"]').validate( {
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
