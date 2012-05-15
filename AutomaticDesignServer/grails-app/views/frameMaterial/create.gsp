<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.material.create.page.head.title', default: 'Frame material details - create new')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'frame.database.material.create.page.title', default: 'Frame material')}</h1>

            <g:if test="${materialInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${materialInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            
            <h2>${message(code: 'frame.database.material.create.properties.title', default: 'Material properties:')}</h2>

            <g:form method="post" name="materialForm" action="save">
              <g:hiddenField name="version" value="${materialInstance.version}"/>
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'material.name.label', default: 'Name')}: <em class="required">*</em></td>
                    <td  colspan="2"><g:textField name="name" value="${materialInstance.name}" title="${message(code: 'material.name.required.label', default: 'A name for the material is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'material.description.label', default: 'Description')}:</td>
                    <td colspan="2"><input type="text" name="description" id="description" value="${fieldValue(bean: materialInstance, field: "description")}"/></td>
                  </tr>
              </table>

              <div class="buttonArea">
                <button type="submit" name="_action_save" class="button" controller="FrameMaterial"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

            </g:form>

       </div>

    <script type="text/javascript">

      $(document).ready(function () {
      
        $('form[name="materialForm"]').validate( {
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
