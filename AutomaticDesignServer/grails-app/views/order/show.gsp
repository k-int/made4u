<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'place.order.show.page.head.title', default: 'Submitted order details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'place.order.show.title', default: 'Individual Submitted order')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'place.order.id.label', default: 'Id')}:</h2>
            <p>${fieldValue(bean: order, field: "id")}</p>

            <h2>${message(code: 'place.order.order.id.label', default: 'Order ID')}:</h2>
            <p>${fieldValue(bean: order, field: "orderId")}</p>

            <h2>${message(code: 'place.order.request.status.label', default: 'Request status')}:</h2>
            <p>${fieldValue(bean: order, field: 'requestStatus.value')}</p>

            <h2>${message(code: 'place.order.request.timeline.heading', default: 'Order timeline')}:</h2>
            <ul>
              <li>${message(code: 'place.order.request.timeline.submitted.time', default: 'Order submitted')}: ${order.orderRequestSubmittedTime}</li>
              <g:if test="${order.inputParsingStartTime}">
                <li>${message(code: 'place.order.request.timeline.input.parsing.start.time', default: 'Input parsing start')}: ${order.inputParsingStartTime}</li>
              </g:if>
              <g:if test="${order.inputParsingEndTime}">
                <li>${message(code: 'place.order.request.timeline.input.parsing.end.time', default: 'Input parsing end')}: ${order.inputParsingEndTime}</li>
              </g:if>
              <g:if test="${order.framesCalcSubmissionTime}">
                <li>${message(code: 'place.order.request.timeline.frame.calculation.submission.time', default: 'Frame calculation submission time')}: ${order.framesCalcSubmissionTime}</li>
              </g:if>
              <g:if test="${order.framesCalcSubmissionEndTime}">
                <li>${message(code: 'place.order.request.timeline.frame.calculation.submission.end.time', default: 'Frame calculation submission end time')}: ${order.framesCalcSubmissionEndTime}</li>
              </g:if>
              <g:if test="${order.framesCalcMarkedAsCompletedTime}">
                <li>${message(code: 'place.order.request.timeline.frame.calculation.marked.completed.time', default: 'Frame calculation marked as complete time')}: ${order.framesCalcMarkedAsCompletedTime}</li>
              </g:if>
              <g:if test="${order.framesCalcPostProcessingStartTime}">
                <li>${message(code: 'place.order.request.timeline.frame.calculation.post.processing.start.time', default: 'Frame calculation post processing start time')}: ${order.framesCalcPostProcessingStartTime}</li>
              </g:if>
              <g:if test="${order.framesCalcPostProcessingEndTime}">
                <li>${message(code: 'place.order.request.timeline.frame.calculation.post.processing.end.time', default: 'Frame calculation post processing end time')}: ${order.framesCalcPostProcessingEndTime}</li>
              </g:if>
              <g:if test="${order.lensCalcInputCreationStartTime}">
                <li>${message(code: 'place.order.request.timeline.lens.calculation.input.creation.start.time', default: 'Lens calculation input creation start time')}: ${order.lensCalcInputCreationStartTime}</li>
              </g:if>
              <g:if test="${order.lensCalcInputCreationEndTime}">
                <li>${message(code: 'place.order.request.timeline.lens.calculation.input.creation.end.time', default: 'Lens calculation input creation end time')}: ${order.lensCalcInputCreationEndTime}</li>
              </g:if>
              <g:if test="${order.lensCalcSubmissionTime}">
                <li>${message(code: 'place.order.request.timeline.lens.calculation.submission.time', default: 'Lens calculation submission time')}: ${order.lensCalcSubmissionTime}</li>
              </g:if>
              <g:if test="${order.lensCalcReturnTime}">
                <li>${message(code: 'place.order.request.timeline.lens.calculation.return.time', default: 'Lens calculation return time')}: ${order.lensCalcReturnTime}</li>
              </g:if>
              <g:if test="${order.postProcessingStartTime}">
                <li>${message(code: 'place.order.request.timeline.post.processing.start.time', default: 'Post processing start time')}: ${order.postProcessingStartTime}</li>
              </g:if>
              <g:if test="${order.postProcessingEndTime}">
                <li>${message(code: 'place.order.request.timeline.post.processing.end.time', default: 'Post processing end time')}: ${order.postProcessingEndTime}</li>
              </g:if>
              <g:if test="${order.coatingCreationStartTime}">
                <li>${message(code: 'place.order.request.timeline.coating.creation.start.time', default: 'Coating details creation start time')}: ${order.coatingCreationStartTime}</li>
              </g:if>
              <g:if test="${order.coatingCreationEndTime}">
                <li>${message(code: 'place.order.request.timeline.coating.creation.end.time', default: 'Coating details creation end time')}: ${order.coatingCreationEndTime}</li>
              </g:if>
              <g:if test="${order.wcsInputCreationStartTime}">
                <li>${message(code: 'place.order.request.timeline.wcs.input.creation.start.time', default: 'WCS input creation start time')}: ${order.wcsInputCreationStartTime}</li>
              </g:if>
              <g:if test="${order.wcsInputCreationEndTime}">
                <li>${message(code: 'place.order.request.timeline.wcs.input.creation.end.time', default: 'WCS input creation end time')}: ${order.wcsInputCreationEndTime}</li>
              </g:if>
              <g:if test="${order.wcsSubmissionTime}">
                <li>${message(code: 'place.order.request.timeline.wcs.submission.start.time', default: 'WCS submission time')}: ${order.wcsSubmissionTime}</li>
              </g:if>
            </ul>
            
            <h2>${message(code: 'place.order.request.status.messages.heading', default: 'Status messages')}:</h2>
            <ul>
              <g:each in="${order.statusMessages}" var="thisMessage">
                <li>${thisMessage}</li>
              </g:each>
            </ul>
            
            <h2>${message(code: 'place.order.request.initial.inputs.heading', default: 'Initial inputs')}:</h2>
            <ul>
              <li><a href="${designDirPath}${order.linkedDesignId}/inputFiles/morphologicalData">${message(code: 'get.design.show.morphological.file.name', default: 'Morphological inputs')}</a></li>
              <li><a href="${designDirPath}${order.linkedDesignId}/inputFiles/nasalMesh">${message(code: 'get.design.show.nasal.mesh.file.name', default: 'Nasal mesh')}</a></li>
              <li><a href="${designDirPath}${order.linkedDesignId}/inputFiles/opticalPrescription">${message(code: 'get.design.show.optical.prescription.file.name', default: 'Optical prescription')}</a></li>
              <li><a href="${orderDirPath}${order.id}/inputFiles/customerChoices">${message(code: 'place.order.customer.choices.file.name', default: 'Customer choices')}</a></li>
            </ul>

            <h2>${message(code: 'place.order.request.frame.calculation.heading', default: 'Frames calculation')}:</h2>
            <ul>
              <g:if test="${order.framesCalcSubmissionTime}">
                <li><a href="${orderDirPath}${order.id}/frameManufacturingCalcFiles/manufactureInput.xml">${message(code: 'place.order.show.frame.calculation.input.file', default: 'Input file (SOAP)')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'place.order.show.not.yet.created.message', default: 'Not yet performed')}</li>                
              </g:else>
              <g:if test="${order.framesCalcMarkedAsCompletedTime}">
                <li><a href="${orderDirPath}${order.id}/frameManufacturingCalcFiles/manufactureOutput.xml">${message(code: 'place.order.show.frame.calculation.output.file', default: 'Output file (SOAP)')}</a></li>
              </g:if>
              <g:else>
                <li><a href="${orderDirPath}${order.id}/frameManufacturingCalcFiles/manufactureProgress.xml">${message(code: 'place.order.show.frame.calculation.progress.output.file', default: 'Progress file (SOAP)')}</a></li>
              </g:else>
            </ul>
            
            <h2>${message(code: 'place.order.request.frame.calculation.post.processing.label', default: 'Frames calculation post processing')}:</h2>
            <ul>
              <g:if test="${order.framesCalcPostProcessingEndTime}">
                <g:if test="${order.stlLocation}">
                  <li><a href="${order.stlLocation}">${message(code: 'place.order.request.frame.calculation.stl.location.label', default: 'STL file (ZIP)')}</a></li>
                </g:if>
                <g:else>
                  <li>${message(code: 'place.order.request.frame.calculation.no.stl.location.label', default: 'No STL location found')}</li>
                </g:else>
              </g:if>
            </ul>
            
            <h2>${message(code: 'place.order.request.lens.calculation.label', default: 'Lens calculation')}:</h2>
            <ul>
              <g:if test="${order.lensCalcSubmissionTime}">
                <li><a href="${orderDirPath}${order.id}/lensManufacturingCalcFiles/manufacturingInput.json">${message(code: 'place.order.show.lens.calculation.input.file', default: 'Input file (JSON ish)')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'place.order.show.not.yet.created.message', default: 'Not yet performed')}</li>
              </g:else>
              <g:if test="${order.lensCalcReturnTime}">
                <li><a href="${orderDirPath}${order.id}/lensManufacturingCalcFiles/manufacturingOutput.json">${message(code: 'place.order.show.lens.calculation.output.file', default: 'Output file (JSON ish)')}</a></li>
                <li><a href="${orderDirPath}${order.id}/lensManufacturingCalcFiles/lms.txt">${message(code: 'place.order.show.lens.calculation.lms.output.file', default: 'LMS file (encoded)')}</a></li>
              </g:if>
            </ul>
            
            <h2>${message(code: 'place.order.request.lens.coatings.label', default: 'Lens coatings')}:</h2>
            <ul>
              <g:if test="${order.coatingCreationEndTime}">
                <li><a href="${orderDirPath}${order.id}/coatingFiles/lensCoatings">${message(code: 'place.order.show.lens.coatings.file', default: 'Coatings file')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'place.order.show.not.yet.created.message', default: 'Not yet performed')}</li>
              </g:else>
            </ul>
            
            <h2>${message(code: 'place.order.request.wcs.label', default: 'Web Control System')}:</h2>
            <ul>
              <g:if test="${order.wcsInputCreationEndTime}">
                <li><a href="${orderDirPath}${order.id}/wcsFiles/wcsInput.json">${message(code: 'place.order.show.wcs.input.file', default: 'WCS Input (json)')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'place.order.show.not.yet.created.message', default: 'Not yet performed')}</li>
              </g:else>
            </ul>
            
            <div class="buttonArea">
                <g:link class="button" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'place.order.showall.label', default: 'Show all placed orders')}"/> ${message(code: 'place.order.showall.label', default: 'Show all placed orders')}</g:link>
                                <g:link class="button" action="edit" id="${order.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'place.order.edit.button.label', default: 'Edit this order')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
            </div>
       </div>

  </body>
</html>
