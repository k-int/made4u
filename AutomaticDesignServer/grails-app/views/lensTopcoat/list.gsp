<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.topcoat.list.page.head.title', default: 'Lens topcoats')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'lens.database.topcoat.list.heading', default: 'Lens topcoats')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.database.available.topcoat.list', default: 'Available lens topcoats:')}</h2>
            
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'lens.topcoat.id.label', default: 'Id')}" />
                      <g:sortableColumn property="name" title="${message(code: 'lens.topcoat.name.label', default: 'Name')}" />
                      <g:sortableColumn property="description" title="${message(code: 'lens.topcoat.description.label', default: 'Description')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${lensTopcoatList}" status="i" var="topcoatInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${topcoatInstance.id}">${fieldValue(bean: topcoatInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${topcoatInstance.id}">${fieldValue(bean: topcoatInstance, field: "name")}</g:link></td>
                        <td>${fieldValue(bean: topcoatInstance, field: "description")}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${lensTopcoatTotal}" />
            </div>
            
            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'lens.database.topcoat.create.image.button.label', default: 'Add new lens topcoat')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>
       </div>

  </body>
</html>
