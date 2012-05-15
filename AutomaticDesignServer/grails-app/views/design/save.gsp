<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'get.design.submit.response.page.head.title', default: 'Design request - submission response')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'get.design.submit.response.title', default: 'Design request')}</h1>
            
            <g:if test="${success}">
              <h2>${message(code: 'get.design.submit.response.successful', default: 'Request submitted successfully')}</h2>
              
              <h3>${message(code: 'get.design.submit.response.id.heading', default: 'Job number:')}</h3>
              <p>${id}</p>
              
              <h3>${message(code: 'get.design.submit.response.order.id.heading', default: 'Order ID:')}</h3>
              <p>${requestId}</p>
              
              <h3>${message(code: 'get.design.submit.response.request.status.heading', default: 'Request status:')}</h3>
              <p>${status}</p>
              
              <h3>${message(code: 'get.design.submit.response.message.heading', default: 'Message:')}</h3>
              <p>${message}</p>

              <div class="buttonArea">
                <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'get.design.showall.label', default: 'Show all design requests')}"/> ${message(code: 'get.design.showall.label', default: 'Show all design requests')}</g:link>
                <g:link class="button" action="show" id="${id}">${message(code: 'get.design.show.button.text', default: 'Show full details')}</g:link>
              </div>

            </g:if>
            <g:else>
              <h2>${message(code: 'get.design.submit.response.failure', default: 'Request submission failure')}</h2>
              
              <h3>${message(code: 'get.design.submit.response.order.id.heading', default: 'Order ID:')}</h3>
              <p>${requestId}</p>
                            
              <h3>${message(code: 'get.design.submit.response.message.heading', default: 'Message:')}</h3>
              <p>${message}</p>
  
              <div class="buttonArea">
                <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'get.design.showall.label', default: 'Show all design requests')}"/> ${message(code: 'get.design.showall.label', default: 'Show all design requests')}</g:link>
              </div>

            </g:else>
            
            

        </div>

  </body>
</html>
