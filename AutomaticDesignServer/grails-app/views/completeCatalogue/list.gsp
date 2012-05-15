<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'get.complete.catalogue.list.page.head.title', default: 'Get Complete Catalogue - Previous requests')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'get.complete.catalogue.list.title', default: 'Previous complete catalogue submissions')}</h1>

            <table>
              <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'get.complete.catalogue.id.label', default: 'ID')}" />
                      <g:sortableColumn property="orderId" title="${message(code: 'get.complete.catalogue.order.id.label', default: 'Order ID')}"/>
                      <g:sortableColumn property="requestStatus" title="${message(code: 'get.complete.catalogue.request.status.label', default: 'Status')}" />
                      <g:sortableColumn property="requestSubmittedTime" title="${message(code: 'get.complete.catalogue.request.submitted.time.label', default: 'Request time')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${designRequests}" status="i" var="requestInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${requestInstance.id}">${requestInstance.id}</g:link></td>
                        <td><g:link action="show" id="${requestInstance.id}">${requestInstance.orderId}</g:link></td>
                        <td><g:link action="show" id="${requestInstance.id}">${requestInstance.requestStatus.value}</g:link></td>
                        <td><g:link action="show" id="${requestInstance.id}">${requestInstance.requestSubmittedTime}</g:link></td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${totalNum}" />
            </div>
            
            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'get.complete.catalogue.create.button.label', default: 'Submit new catalogue request')}"/> ${message(code: 'get.complete.catalogue.create.button.text', default: 'New catalogue request')}</g:link>
            </div>

            
        </div>

  </body>
</html>
