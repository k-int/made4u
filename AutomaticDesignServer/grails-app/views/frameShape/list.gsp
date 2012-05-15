<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.shapes.list.page.head.title', default: 'Frame shapes')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.shapes.list.heading', default: 'Frame shapes')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.shapes.list', default: 'Available frame shapes:')}</h2>
            
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'shape.id.label', default: 'Id')}" />
                      <g:sortableColumn property="name" title="${message(code: 'shape.name.label', default: 'Name')}" />
                      <g:sortableColumn property="description" title="${message(code: 'shape.description.label', default: 'Description')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${frameShapeList}" status="i" var="shapeInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${shapeInstance.id}">${fieldValue(bean: shapeInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${shapeInstance.id}">${fieldValue(bean: shapeInstance, field: "name")}</g:link></td>
                        <td>${fieldValue(bean: shapeInstance, field: "description")}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${frameShapeTotal}" />
            </div>
            
            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'shape.create.image.button.label', default: 'Add new shape')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>
       </div>

  </body>
</html>
