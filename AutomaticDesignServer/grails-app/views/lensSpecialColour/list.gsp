<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.lens.special.colour.list.page.head.title', default: 'Lens special colours')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'lens.database.lens.special.colour.list.title', default: 'Lens special colours')}</h1>
            <h2>${message(code: 'lens.database.lens.special.colour.list.table.heading', default: 'Available lens special colours:')}</h2>
            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'lens.special.colour.list.id.label', default: 'Id')}" />
                      <g:sortableColumn property="name" title="${message(code: 'lens.special.colour.list.name.label', default: 'Name')}" />
                      <g:sortableColumn property="specialColourType" title="${message(code: 'lens.special.colour.list.special.colour.type.label', default: 'Type')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${lensSpecialColourList}" status="i" var="colourInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${colourInstance.id}">${colourInstance.id}</g:link></td>
                        <td><g:link action="show" id="${colourInstance.id}">${colourInstance.name}</g:link></td>
                        <td>${colourInstance.specialColourType.value}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${lensSpecialColourTotal}" />
            </div>

            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'lens.special.colour.create.coating.button.label', default: 'Add new special colour')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>

       </div>

  </body>
</html>
