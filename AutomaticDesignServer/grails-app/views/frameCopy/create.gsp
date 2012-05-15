<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.copy.page.head.title', default: 'Frame - copy')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'frame.copy.title', default: 'Copy frame')}</h1>

            <g:if test="${targetFrame.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${targetFrame}"/>
              </div>
            </g:if>
            <g:if test="${flash.error}">
                <div class="message">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.copy.properties.heading', default: 'New frame properties:')}</h2>
            
            <g:form method="post" name="frameCopyForm" action="save">
              <g:hiddenField name="version" value="${targetFrame.version}"/>
              <g:hiddenField name="sourceId" value="${sourceId}"/>
              
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.identifier.label', default: 'Identifier')}: <em class="required">*</em></td>
                    <td  colspan="2"><g:textField name="identifier" value="${targetFrame.identifier}" title="${message(code: 'frame.identifier.required.label', default: 'An identifier for the frame is required')}" class="required"/></td>
                  </tr>
              </table>
              
              <div class="clear">
                <p>Please note: This operation is not yet implemented</p>
              </div>
              
              <div class="buttonArea">
                <button type="submit" name="_action_save" class="button" controller="frameCopy"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" controller="frameCatalogue" action="show" id="${sourceId}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

            </g:form>

       </div>
  </body>
</html>
