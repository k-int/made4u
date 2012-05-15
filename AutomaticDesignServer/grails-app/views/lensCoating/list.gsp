<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.coatings.list.page.head.title', default: 'Lens coatings')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.coatings.list.title', default: 'Lens coating')}</h1>
            <h2>${message(code: 'lens.coatings.list.table.heading', default: 'Available lens coatings:')}</h2>
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'coating.list.id.label', default: 'Id')}" />
                      <g:sortableColumn property="reference" title="${message(code: 'coating.list.reference.label', default: 'Reference')}" />
                      <g:sortableColumn property="coatingType" title="${message(code: 'coating.list.type.label', default: 'Type')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${lensCoatingList}" status="i" var="coatingInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${coatingInstance.id}">${fieldValue(bean: coatingInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${coatingInstance.id}">${fieldValue(bean: coatingInstance, field: "reference")}</g:link></td>
                        <td>${coatingInstance.coatingType.value}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${lensCoatingTotal}" />
            </div>

            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'coating.create.coating.button.label', default: 'Add new coating')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>

       </div>

  </body>
</html>
