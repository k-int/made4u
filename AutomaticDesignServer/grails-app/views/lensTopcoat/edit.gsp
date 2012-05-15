<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.topcoat.edit.page.head.title', default: 'Lens topcoat details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.topcoat.edit.page.title', default: 'Individual Lens topcoat')}</h1>

            <g:if test="${topcoatInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${topcoatInstance}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.topcoat.edit.page.properties.heading', default: 'Lens topcoat properties:')}</h2>

            <g:form method="post" name="topcoatForm">
              <g:hiddenField name="id" value="${topcoatInstance.id}"/>
              <g:hiddenField name="version" value="${topcoatInstance.version}"/>
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.topcoat.id.label', default: 'Id')}:</td>
                    <td class="value" colspan="2">
                      ${fieldValue(bean: topcoatInstance, field: "id")}
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.topcoat.name.label', default: 'Name')}: <em class="required">*</em></td>
                    <td  colspan="2"><g:textField name="name" value="${topcoatInstance.name}" title="${message(code: 'lens.topcoat.name.required.label', default: 'A name for the lens topcoat is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.topcoat.description.label', default: 'Description')}:</td>
                    <td colspan="2"><input type="text" name="description" id="description" value="${fieldValue(bean: topcoatInstance, field: "description")}"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'lens.topcoat.price.label', default: 'Price')}: <em class="required">*</em></td>
                    <td colspan="2"><input type="text" name="price" id="price" value="${formatNumber(number: topcoatInstance.price, format:"0.00")}" class="required" title="${message(code: 'lens.topcoat.price.required.label', default: 'A price is required for a topcoat')}"/></td>
                  </tr>
              </table>

              <div class="buttonArea">
                <button type="submit" name="_action_update" class="button" controller="lensTopcoat"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="show" id="${topcoatInstance.id}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

            </g:form>

       </div>

    <script type="text/javascript">

      $(document).ready(function () {

        $('form[name="topcoatForm"]').validate( {
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
