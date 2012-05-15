<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.material.list.page.head.title', default: 'Lens materials')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.materials.list.title', default: 'Lens materials')}</h1>
            <h2>${message(code: 'lens.materials.list.table.heading', default: 'Available lens materials:')}</h2>
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'lens.material.id.label', default: 'Id')}" />
                      <g:sortableColumn property="identifier" title="${message(code: 'lens.material.identifier.label', default: 'Identifier')}"/>
                      <g:sortableColumn property="reference" title="${message(code: 'lens.material.reference.label', default: 'Reference')}" />
                      <g:sortableColumn property="materialType" title="${message(code: 'lens.material.material.type.label', default: 'Material type')}" />
                      <g:sortableColumn property="focalType" title="${message(code: 'lens.material.focal.type.label', default: 'Focal type')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${lensMaterialList}" status="i" var="materialInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${materialInstance.id}">${materialInstance.id}</g:link></td>
                        <td><g:link action="show" id="${materialInstance.id}">${materialInstance.identifier}</g:link></td>
                        <td><g:link action="show" id="${materialInstance.id}">${materialInstance.reference}</g:link></td>
                        <td>${materialInstance.materialType.value}</td>
                        <td>${materialInstance.focalType.value}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${lensMaterialTotal}" />
            </div>

            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'lens.material.create.material.button.label', default: 'Add new material')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>

       </div>

  </body>
</html>
