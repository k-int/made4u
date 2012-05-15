<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.image.list.head.title', default: 'Frame images')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.image.list.title', default: 'Frame images')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.image.list.subtitle', default: 'Available frame images:')}</h2>
            
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'frame.image.id.label', default: 'Id')}" />
                      <g:sortableColumn property="reference" title="${message(code: 'frame.image.reference.label', default: 'Reference')}" />
                      <g:sortableColumn property="type" title="${message(code: 'frame.image.type.label', default: 'Image type')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${frameImageList}" status="i" var="frameImageInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${frameImageInstance.id}">${fieldValue(bean: frameImageInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${frameImageInstance.id}">${fieldValue(bean: frameImageInstance, field: "reference")}</g:link></td>
                        <td>${fieldValue(bean: frameImageInstance, field: "imageType.value")}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${frameImageTotal}" />
            </div>
            
            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.image.create.button.label', default: 'Add new image')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>
       </div>

  </body>
</html>
