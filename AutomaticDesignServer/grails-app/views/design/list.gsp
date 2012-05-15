<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'get.design.list.page.head.title', default: 'Get Design - Available requests')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'get.design.list.title', default: 'Available design requests')}</h1>

            <table>
              <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'get.design.id.label', default: 'ID')}" />
                      <g:sortableColumn property="orderId" title="${message(code: 'get.design.order.id.label', default: 'Order ID')}"/>
                      <g:sortableColumn property="requestStatus" title="${message(code: 'get.design.request.status.label', default: 'Status')}" />
                      <g:sortableColumn property="requestSubmittedTime" title="${message(code: 'get.design.request.submitted.time.label', default: 'Request time')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${designRequests}" status="i" var="requestInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${requestInstance.id}">${requestInstance.id}</g:link></td>
                        <td><g:link action="show" id="${requestInstance.id}">${requestInstance.orderId}</g:link></td>
                        <td><g:link action="show" id="${requestInstance.id}">${fieldValue(bean: requestInstance, field:"requestStatus")}</g:link></td>
                        <td><g:link action="show" id="${requestInstance.id}">${requestInstance.requestSubmittedTime}</g:link></td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${totalNum}" />
            </div>
            
            <div class="buttonArea">
              <g:link class="button" action="create"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'get.design.create.button.label', default: 'Submit new design request')}"/> ${message(code: 'get.design.create.button.text', default: 'New design request')}</g:link>
            </div>

            
        </div>

  </body>
</html>
