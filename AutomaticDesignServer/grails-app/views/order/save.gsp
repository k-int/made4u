<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'place.order.created.page.head.title', default: 'Place order - placed')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'place.order.placed.title', default: 'Place order - placed')}</h1>
            <ul>
              <li>${message(code: 'place.order.id.label', default: 'ID')}: ${id}</li>
              <li>${message(code: 'place.order.order.id.label', default: 'Order ID')}: ${orderId}</li>
              <li>${message}</li>
            </ul>
            
            <div class="buttonArea">
              <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'place.order.placed.order.showall.label', default: 'Show all placed orders')}"/> ${message(code: 'place.order.placed.order.showall.label', default: 'Show all placed orders')}</g:link>
              <g:link class="button" action="show" id="${id}">${message(code: 'place.order.placed.order.show.button.text', default: 'Show full order details')}</g:link>
            </div>
        </div>

  </body>
</html>
