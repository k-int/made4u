<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.standard.component.create.page.head.title', default: 'Frame standard component details - create new')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'frame.database.standard.component.create.page.title', default: 'Standard component')}</h1>

            <g:if test="${standardInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${standardInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            
            <h2>${message(code: 'frame.database.standard.component.create.properties.title', default: 'Standard component properties:')}</h2>

            <g:form method="post" name="standardForm" action="save">
              <g:hiddenField name="version" value="${standardInstance.version}"/>
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'standard.component.reference.label', default: 'Reference')}: <em class="required">*</em></td>
                    <td  colspan="2"><g:textField name="reference" value="${standardInstance.reference}" title="${message(code: 'standard.component.reference.required.label', default: 'A reference for the standard component is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'standard.component.description.label', default: 'Description')}:</td>
                    <td colspan="2"><input type="text" name="description" id="description" value="${fieldValue(bean: standardInstance, field: "description")}"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'standard.component.type.label', default: 'Type')}:</td>
                    <td colspan="2">
                      <g:select id="standardType" name="standardType" value="${standardInstance.standardType}" class="required" title="${message(code: 'standard.component.type.required.message', default: 'A standard component type must be selected')}" noSelection="${['': message(code: 'standard.component.type.please.choose.message', default: 'Please choose')]}" from="${standardTypes}"/>
                    </td>
                  </tr>
              </table>

              <div class="buttonArea">
                <button type="submit" name="_action_save" class="button" controller="FrameStandardComponent"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

            </g:form>

       </div>

    <script type="text/javascript">

      $(document).ready(function () {
      
        $('form[name="standardForm"]').validate( {
            rules: {
              'name': {
                required: true
              },
              'standardType': {
                required: true
              }
            }
          });
        });

    </script>
  </body>
</html>
