<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.system.enum.list.head.title', default: 'Frame system enumerations')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.system.enum.list.title', default: 'Frame system enumerations')}</h1>

            <g:if test="${flash.error}">
                <div class="errors"><ul><li>${flash.error}</li></ul></div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.system.enum.list.subtitle', default: 'Available system enumerations')}</h2>
            
            <p>${message(code: 'frame.database.system.enum.list.introduction', default: 'This page lists the various controlled system values that cannot be changed directly but which can be used throughout the frame database.')}</p>
            
            <h3>${message(code: 'frame.database.system.enum.endpiece.height.list.heading', default: 'Endpiece heights:')}</h3>
            
            <ul>
              <g:each in="${endpieceHeights}" status="i" var="endpieceHeightInstance">
                <li>${endpieceHeightInstance.value}</li>
              </g:each>
            </ul>
            
            <h3>${message(code: 'frame.database.system.enum.finishing.aspect.list.heading', default: 'Finishing aspects:')}</h3>
            
            <ul>
              <g:each in="${finishingAspects}" status="i" var="finishingAspectInstance">
                <li>${finishingAspectInstance.value}</li>
              </g:each>
            </ul>
            
            <h3>${message(code: 'frame.database.system.enum.gender.list.heading', default: 'Genders:')}</h3>
            
            <ul>
              <g:each in="${genders}" status="i" var="genderInstance">
                <li>${genderInstance.value}</li>
              </g:each>
            </ul>

            <h3>${message(code: 'frame.database.system.enum.image.type.list.heading', default: 'Image types:')}</h3>
            
            <ul>
              <g:each in="${imageTypes}" status="i" var="imageTypeInstance">
                <li>${imageTypeInstance.value}</li>
              </g:each>
            </ul>

            <h3>${message(code: 'frame.database.system.enum.rim.type.list.heading', default: 'Rim types:')}</h3>
            
            <ul>
              <g:each in="${rimTypes}" status="i" var="rimTypeInstance">
                <li>${rimTypeInstance.value}</li>
              </g:each>
            </ul>
            
            <h3>${message(code: 'frame.database.system.enum.standard.type.list.heading', default: 'Standard types:')}</h3>
            
            <ul>
              <g:each in="${standardTypes}" status="i" var="standardTypeInstance">
                <li>${standardTypeInstance.value}</li>
              </g:each>
            </ul>

            <h3>${message(code: 'frame.database.system.enum.use.list.heading', default: 'Uses:')}</h3>
            
            <ul>
              <g:each in="${uses}" status="i" var="useInstance">
                <li>${useInstance.value}</li>
              </g:each>
            </ul>
            
            <h3>${message(code: 'frame.database.system.enum.personalisation.type.list.heading', default: 'Personalisation types:')}</h3>
            
            <ul>
              <g:each in="${personalisationTypes}" status="i" var="personalisationInstance">
                <li>${personalisationInstance.value}</li>
              </g:each>
            </ul>


       </div>

  </body>
</html>
