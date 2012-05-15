<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'place.order.page.head.title', default: 'Place order')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'place.order.title', default: 'Place order')}</h1>

            <g:uploadForm action="save" method="POST" name="orderForm">
              <p>
                ${message(code: 'place.order.para.1', default: "Specify the required input files for the order and then click 'Place order' to place the order. This form simulates a RESTful call for an individual order which can also be performed by POSTing to the ")}
                ${createLinkTo(dir:'')}/order/create
                ${message(code: 'place.order.para.2', default: "URL with values for each of the fields listed in brackets below.")}
              </p>
              <div class="inputForm">
                <label>${message(code: 'place.order.order.id.label', default: 'Order ID')} (orderId):<input type="text" name="orderId" id="orderId" title="${message(code: 'place.order.id.required.message', default: 'An order ID is required')}"/></label>
                <label>${message(code: 'place.order.customer.selection.file.label', default: 'Customer choices')} (customerChoices):<input type="file" name="customerChoices" id="customerChoices" /></label>
                <label>${message(code: 'place.order.customer.selection.string.label', default: 'Customer choices file contents')} (customerChoicesString):<input type="text" name="customerChoicesString" id="customerChoicesString"/></label>
                <label>&nbsp;
                  <input type="submit" value="${message(code: 'place.order.submit.text', default: 'Place order')}"/>
                </label>
              </div>
            </g:uploadForm>
        </div>

           <script type="text/javascript">
      $(document).ready(function () {
        $('form[name="orderForm"]').validate( {
          rules: {
            'orderId': {
              required: true
            }
          }
        });
      });
    
    </script>
  </body>
</html>
