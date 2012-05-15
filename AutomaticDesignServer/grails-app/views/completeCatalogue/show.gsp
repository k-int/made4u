<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'get.complete.catalogue.show.page.head.title', default: 'Complete catalogue Request details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'get.complete.catalogue.show.title', default: 'Complete catalogue request')}</h1>
            

            <h2>${message(code: 'get.complete.catalogue.show.id.heading', default: 'Job number:')}</h2>
            <p>${jobNumber}</p>

            <h2>${message(code: 'get.complete.catalogue.show.order.id.heading', default: 'Order ID:')}</h2>
            <p>${designReq.orderId}</p>

            <h2>${message(code: 'get.complete.catalogue.show.uuid.heading', default: 'Job UUID:')}</h2>
            <p>${designReq.uuid}</p>
            
            <h2>${message(code: 'get.complete.catalogue.show.status.heading', default: 'Job status:')}</h2>
            <p>${designReq.requestStatus.value}</p>
            
            <h2>${message(code: 'get.completecatalogue.show.timeline.heading', default: 'Job timeline:')}</h2>
            <ul>
              <li>${message(code: 'get.complete.catalogue.show.timeline.request.submission.time', default: 'Request submitted: ')} ${designReq.requestSubmittedTime}</li>
            </ul>
            
            
            <h2>${message(code: 'get.complete.catalogue.show.status.messages.heading', default: 'Status messages:')}</h2>
            <ul>
              <g:each in="${designReq.statusMessages}" var="thisMessage">
                <li>${thisMessage}</li>
              </g:each>
            </ul>
            
            <h2>${message(code: 'get.complete.catalogue.show.files.heading', default: 'Associated files')}</h2>
            <h3>${message(code: 'get.complete.catalogue.show.initial.input.files.heading', default: 'Initial inputs:')}</h3>
            <ul>
              <li><a href="${configDirPath}${jobNumber}/inputFiles/morphologicalData">${message(code: 'get.complete.catalogue.show.morphological.file.name', default: 'Morphological inputs')}</a></li>
              <li><a href="${configDirPath}${jobNumber}/inputFiles/nasalMesh">${message(code: 'get.complete.catalogue.show.nasal.mesh.file.name', default: 'Nasal mesh')}</a></li>
              <li><a href="${configDirPath}${jobNumber}/inputFiles/opticalPrescription">${message(code: 'get.complete.catalogue.show.optical.prescription.file.name', default: 'Optical prescription')}</a></li>
            </ul>
            
            
            <h3>${message(code: 'get.complete.catalogue.show.available.configuration.heading', default: 'Available configurations:')}</h3>
            <ul>
              <g:if test="${hasConfigsAvailable}">
                <li><a href="${configFilePath}">${message(code: 'get.complete.catalogue.show.available.configurations.file.name', default: 'Complete catalogue')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'get.complete.catalogue.show.available.configurations.not.yet.available.message', default: 'Configurations file not yet available')}</li>
              </g:else>
            </ul>

        </div>

  </body>
</html>
