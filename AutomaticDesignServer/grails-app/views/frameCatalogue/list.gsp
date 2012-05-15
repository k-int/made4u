<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.frame.list.page.head.title', default: 'Frame catalogue')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.frame.list.heading', default: 'Frame catalogue')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.frame.list', default: 'Available frames:')}</h2>
            
              <table>
                  <thead>
                      <tr>
                        <g:sortableColumn property="id" title="${message(code: 'frame.id.label', default: 'Id')}" />
                        <g:sortableColumn property="identifier" title="${message(code: 'frame.identifier.label', default: 'Identifier')}" />
                        <g:sortableColumn property="material" title="${message(code: 'frame.material.label', default: 'Material')}" />
                        <g:sortableColumn property="rimType" title="${message(code: 'frame.rim.type.label', default: 'Rim type')}" />
                        <g:sortableColumn property="displayOrder" title="${message(code: 'frame.display.order.label', default: 'Display order')}"/>
                      </tr>
                  </thead>
                  <tbody>
                      <g:each in="${frameList}" status="i" var="frameInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <td><g:link action="show" id="${frameInstance.id}">${fieldValue(bean: frameInstance, field: "id")}</g:link></td>
                          <td><g:link action="show" id="${frameInstance.id}">${fieldValue(bean: frameInstance, field: "identifier")}</g:link></td>
                          <td>${fieldValue(bean: frameInstance, field: "material.name")}</td>
                          <td>${fieldValue(bean: frameInstance, field: "rimType")}</td>
                          <td>${fieldValue(bean: frameInstance, field: "displayOrder")}</td>
                        </tr>
                      </g:each>
                  </tbody>
              </table>
              <div class="pagination">
                <g:paginate action="list" total="${frameTotal}" />
              </div>

              <div class="buttonArea">
                <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.frame.create.button.label', default: 'Add new frame')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>                
              </div>

            </form>
       </div>

  </body>
</html>
