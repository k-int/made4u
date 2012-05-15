<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.colour.show.page.head.title', default: 'Frame colour details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.colour.show.title', default: 'Individual frame colour')}</h1>

            <g:if test="${flash.error}">
              <div class="errors">
                <ul>
                  <li>${flash.error}</li>
                </ul>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.colour.show.properties.heading', default: 'Frame colour properties:')}</h2>
            <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: colourInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.name.label', default: 'Name')}:</td>
                    <td>${fieldValue(bean: colourInstance, field: "name")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.red.label', default: 'Red')}:</td>
                    <td>${fieldValue(bean: colourInstance, field: "red")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.green.label', default: 'Green')}:</td>
                    <td>${fieldValue(bean: colourInstance, field: "green")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.blue.label', default: 'Blue')}:</td>
                    <td>${fieldValue(bean: colourInstance, field: "blue")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.price.label', default: 'Price')} ${message(code: 'price.currency.symbol')}:</td>
                    <td><g:formatNumber number="${colourInstance.price}" format="0.00" /></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.time.label', default: 'Time (days)')}:</td>
                    <td>${fieldValue(bean: colourInstance, field: "productionTime")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.example.label', default: 'Example')}:</td>
                    <td style='background: rgba(${fieldValue(bean: colourInstance, field: "red")}, ${fieldValue(bean: colourInstance, field: "green")}, ${fieldValue(bean: colourInstance, field: "blue")}, 1)'>&nbsp; &nbsp;</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.colour.linked.frames.label', default: 'Linked frames')}:</td>
                    <td>
                      <g:if test="${colourInstance.frames?.size() != 0}">
                        <ul>
                          <g:each in="${colourInstance.frames}" status="i" var="frameInstance">
                            <li><g:link controller="frameCatalogueColour" action="show" id="${frameInstance.id}" title="${message(code: 'frame.colour.linked.frame.link', default: 'View this frame')}">${fieldValue(bean: frameInstance, field: "identifier")}</g:link>
                          </g:each>
                        </ul>
                      </g:if>
                      <g:else>
                         ${message(code: 'frame.colour.no.linked.frames.text', default: 'No frames currently linked')}
                      </g:else>
                    </td>
                  </tr>
            </table>

            <div class="buttonArea">
              <g:form method="post" name="colourForm">
                <g:hiddenField name="id" value="${colourInstance.id}"/>

                <g:link class="button" controller="frameBaseColour" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'frame.colour.showall.label', default: 'Show all frame colours')}"/> ${message(code: 'frame.colour.showall.label', default: 'Show all frame colours')}</g:link>
                <g:link class="button" action="edit" id="${colourInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'frame.colour.edit.button.label', default: 'Edit this colour')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <a class="button" value="${message(code: 'frame.colour.delete.button.label', default: 'Delete this colour')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'frame.colour.delete.button.label', default: 'Delete this colour')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>

            </div>
       </div>

  </body>
</html>
