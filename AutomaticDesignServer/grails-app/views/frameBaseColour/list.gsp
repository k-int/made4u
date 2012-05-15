<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.colour.list.page.head.title', default: 'Frame colours')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.colour.list.title', default: 'Frame colour')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.colour.list.table.heading', default: 'Available frame colours:')}</h2>
            
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'frame.colour.id.label', default: 'Id')}" />
                      <g:sortableColumn property="name" title="${message(code: 'frame.colour.name.label', default: 'Name')}" />
                      <g:sortableColumn property="red" title="${message(code: 'frame.colour.red.label', default: 'Red')}" />
                      <g:sortableColumn property="green" title="${message(code: 'frame.colour.green.label', default: 'Green')}" />
                      <g:sortableColumn property="blue" title="${message(code: 'frame.colour.blue.label', default: 'Blue')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${baseColourList}" status="i" var="colourInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${colourInstance.id}">${fieldValue(bean: colourInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${colourInstance.id}">${fieldValue(bean: colourInstance, field: "name")}</g:link></td>
                        <td>${fieldValue(bean: colourInstance, field: "red")}</td>
                        <td>${fieldValue(bean: colourInstance, field: "green")}</td>
                        <td>${fieldValue(bean: colourInstance, field: "blue")}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${baseColourListTotal}" />
            </div>

            
            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.colour.create.button.label', default: 'Add new colour')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>
       </div>

  </body>
</html>
