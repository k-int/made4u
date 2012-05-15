<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'place.order.list.page.head.title', default: 'Submitted orders')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'place.order.list.heading', default: 'Submitted orders')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <table>
                <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'place.order.id.label', default: 'ID')}" />
                      <g:sortableColumn property="orderId" title="${message(code: 'place.order.order.id.label', default: 'Order ID')}" />
                      <g:sortableColumn property="requestStatus" title="${message(code: 'place.order.request.status.label', default: 'Request Status')}" />
                      <g:sortableColumn property="orderRequestSubmittedTime" title="${message(code: 'place.order.submitted.time.label', default: 'Submission time')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${orderList}" status="i" var="orderInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${orderInstance.id}">${fieldValue(bean: orderInstance, field: "id")}</g:link></td>
                        <td><g:link action="show" id="${orderInstance.id}">${fieldValue(bean: orderInstance, field: "orderId")}</g:link></td>
                        <td><g:link action="show" id="${orderInstance.id}">${fieldValue(bean: orderInstance, field: "requestStatus")}</g:link></td>
                        <td>${fieldValue(bean: orderInstance, field: "orderRequestSubmittedTime")}</td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${totalNum}" />
            </div>

            
            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'place.order.create.button.label', default: 'Place new order')}"/> ${message(code: 'create.button.text', default: 'Add')}</g:link>
            </div>
       </div>

  </body>
</html>
