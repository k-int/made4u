<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.standard.component.list.page.head.title', default: 'Standard components')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.standard.component.list.heading', default: 'Standard components')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.standard.component.list', default: 'Available standard components:')}</h2>
            
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'standard.component.id.label', default: 'Id')}" />
                      <g:sortableColumn property="reference" title="${message(code: 'standard.component.reference.label', default: 'Reference')}" />
                      <g:sortableColumn property="description" title="${message(code: 'standard.component.description.label', default: 'Description')}" />
                      <g:sortableColumn property="type" title="${message(code: 'standard.component.type.label', default: 'Type')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${standardComponentList}" status="i" var="standardInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${standardInstance.id}">${fieldValue(bean: standardInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${standardInstance.id}">${fieldValue(bean: standardInstance, field: "reference")}</g:link></td>
                        <td>${fieldValue(bean: standardInstance, field: "description")}</td>
                        <td>${fieldValue(bean: standardInstance, field: "standardType")}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${standardComponentTotal}" />
            </div>

            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'standard.component.create.image.button.label', default: 'Add new standard component')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>
       </div>

  </body>
</html>
