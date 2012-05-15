<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.emotional.variables.list.page.head.title', default: 'Emotional variables')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.emotional.variables.list.heading', default: 'Emotional variables')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.emotional.variables.list', default: 'Available emotional variables:')}</h2>
            
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'emotional.variable.id.label', default: 'Id')}" />
                      <g:sortableColumn property="name" title="${message(code: 'emotional.variable.name.label', default: 'Name')}" />
                      <g:sortableColumn property="description" title="${message(code: 'emotional.variable.description.label', default: 'Description')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${emotionalVariableList}" status="i" var="emotionalInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${emotionalInstance.id}">${fieldValue(bean: emotionalInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${emotionalInstance.id}">${fieldValue(bean: emotionalInstance, field: "name")}</g:link></td>
                        <td>${fieldValue(bean: emotionalInstance, field: "description")}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${emotionalVariableTotal}" />
            </div>
            
            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'emotional.variable.create.image.button.label', default: 'Add new variable')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>
       </div>

  </body>
</html>
