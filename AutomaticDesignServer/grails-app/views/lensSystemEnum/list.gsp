<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'lens.database.system.enum.list.head.title', default: 'Lens system enumerations')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'lens.database.system.enum.list.title', default: 'Lens system enumerations')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'lens.database.system.enum.list.subtitle', default: 'Available system enumerations')}</h2>
            
            <p>${message(code: 'lens.database.system.enum.list.introduction', default: 'This page lists the various controlled system values that cannot be changed directly but which can be used throughout the lens database.')}</p>
            
            <h3>${message(code: 'lens.database.system.enum.coating.type.list.heading', default: 'Coating types:')}</h3>
            
            <ul>
              <g:each in="${coatingTypes}" status="i" var="coatingTypeInstance">
                <li>${coatingTypeInstance.value}</li>
              </g:each>
            </ul>
            
            <h3>${message(code: 'lens.database.system.enum.colour.type.list.heading', default: 'Colour types:')}</h3>
            
            <ul>
              <g:each in="${lensColourTypes}" status="i" var="colourTypeInstance">
                <li>${colourTypeInstance.value}</li>
              </g:each>
            </ul>

            <h3>${message(code: 'lens.database.system.enum.special.colour.type.list.heading', default: 'Special colour types:')}</h3>
            
            <ul>
              <g:each in="${specialColourTypes}" status="i" var="specialColourTypeInstance">
                <li>${specialColourTypeInstance.value}</li>
              </g:each>
            </ul>

            <h3>${message(code: 'lens.database.system.enum.material.type.list.heading', default: 'Material types:')}</h3>
            
            <ul>
              <g:each in="${lensMaterialTypes}" status="i" var="materialTypeInstance">
                <li>${materialTypeInstance.value}</li>
              </g:each>
            </ul>

            <h3>${message(code: 'lens.database.system.enum.lens.type.list.heading', default: 'Lens types:')}</h3>
            
            <ul>
              <g:each in="${lensTypes}" status="i" var="lensTypeInstance">
                <li>${lensTypeInstance.value}</li>
              </g:each>
            </ul>


       </div>

  </body>
</html>
