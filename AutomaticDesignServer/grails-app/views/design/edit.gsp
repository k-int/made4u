<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'get.design.edit.page.head.title', default: 'Design Request details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'get.design.edit.title', default: 'Design request - edit')}</h1>
            

            <h2>${message(code: 'get.design.edit.id.heading', default: 'Job number:')}</h2>
            <p>${jobNumber}</p>

            <h2>${message(code: 'get.design.edit.order.id.heading', default: 'Order ID:')}</h2>
            <p>${designReq.orderId}</p>
            
            <h2>${message(code: 'get.design.edit.status.heading', default: 'Job status:')}</h2>
            <p>${designReq.requestStatus.value}</p>
            
            <h2>${message(code: 'get.design.edit.timeline.heading', default: 'Job timeline:')}</h2>
            <ul>
              <li>${message(code: 'get.design.edit.timeline.request.submission.time', default: 'Request submitted: ')} ${designReq.requestSubmittedTime}</li>
              <g:if test="${designReq.framesCalcSubmissionTime}">
                <li>${message(code: 'get.design.edit.timeline.frames.calculation.submission.time', default: 'Frame calculation submission time: ')} ${designReq.framesCalcSubmissionTime}</li>
              </g:if>
              <g:if test="${designReq.framesCalcReturnTime}">
                <li>${message(code: 'get.design.edit.timeline.frames.calculation.return.time', default: 'Frame calculation return time: ')} ${designReq.framesCalcReturnTime}</li>
              </g:if>
              <g:if test="${designReq.frameRefinementStartTime}">
                <li>${message(code: 'get.design.edit.timeline.frames.refinement.start.time', default: 'Frames refinement start time: ')} ${designReq.frameRefinementStartTime}</li>
              </g:if>
              <g:if test="${designReq.frameRefinementEndTime}">
                <li>${message(code: 'get.design.edit.timeline.frames.refinement.end.time', default: 'Frames refinement end time: ')} ${designReq.frameRefinementEndTime}</li>
              </g:if>
              <g:if test="${designReq.lensRefinementStartTime}">
                <li>${message(code: 'get.design.edit.timeline.lens.refinement.start.time', default: 'Lens refinement start time: ')} ${designReq.lensRefinementStartTime}</li>
              </g:if>
              <g:if test="${designReq.lensRefinementEndTime}">
                <li>${message(code: 'get.design.edit.timeline.lens.refinement.end.time', default: 'Lens refinement end time: ')} ${designReq.lensRefinementEndTime}</li>
              </g:if>
              <g:if test="${designReq.lensCalcInputCreationStartTime}">
                <li>${message(code: 'get.design.edit.timeline.lens.calculation.input.creation.start.time', default: 'Lens calculation input creation start time: ')} ${designReq.lensCalcInputCreationStartTime}</li>
              </g:if>
              <g:if test="${designReq.lensCalcInputCreationEndTime}">
                <li>${message(code: 'get.design.edit.timeline.lens.calculation.input.creation.end.time', default: 'Lens calculation input creation end time: ')} ${designReq.lensCalcInputCreationEndTime}</li>
              </g:if>
              <g:if test="${designReq.lensCalcSubmissionTime}">
                <li>${message(code: 'get.design.edit.timeline.lens.calculation.submission.time', default: 'Lens calculation submission time: ')} ${designReq.lensCalcSubmissionTime}</li>
              </g:if>
              <g:if test="${designReq.lensCalcReturnTime}">
                <li>${message(code: 'get.design.edit.timeline.lens.calculation.return.time', default: 'Lens calculation return time: ')} ${designReq.lensCalcReturnTime}</li>
              </g:if>
              <g:if test="${designReq.postProcessingStartTime}">
                <li>${message(code: 'get.design.edit.timeline.post.processing.start.time', default: 'Post processing start time: ')} ${designReq.postProcessingStartTime}</li>
              </g:if>
              <g:if test="${designReq.postProcessingEndTime}">
                <li>${message(code: 'get.design.edit.timeline.post.processing.return.time', default: 'Post processing return time: ')} ${designReq.postProcessingEndTime}</li>
              </g:if>
              <g:if test="${designReq.responseGivenTime}">
                <li>${message(code: 'get.design.edit.timeline.response.created.time', default: 'Response XML creation time: ')} ${designReq.responseGivenTime}</li>
              </g:if>

            </ul>
            
            
            <h2>${message(code: 'get.design.edit.status.messages.heading', default: 'Status messages:')}</h2>
            <ul>
              <g:each in="${designReq.statusMessages}" var="thisMessage">
                <li>${thisMessage}</li>
              </g:each>
            </ul>
            
            <h3>${message(code: 'get.design.edit.initial.input.files.heading', default: 'Initial inputs:')}</h3>
            <ul>
              <li><a href="${configDirPath}${jobNumber}/inputFiles/morphologicalData">${message(code: 'get.design.edit.morphological.file.name', default: 'Morphological inputs')}</a></li>
              <li><a href="${configDirPath}${jobNumber}/inputFiles/nasalMesh">${message(code: 'get.design.edit.nasal.mesh.file.name', default: 'Nasal mesh')}</a></li>
              <li><a href="${configDirPath}${jobNumber}/inputFiles/opticalPrescription">${message(code: 'get.design.edit.optical.prescription.file.name', default: 'Optical prescription')}</a></li>
            </ul>
            
            <h3>${message(code: 'get.design.edit.frame.calculation.outputs.heading', default: 'Frame calculation:')}</h3>
            <ul>
              <g:if test="${designReq.framesCalcSubmissionTime}">
                <li><a href="${configDirPath}${jobNumber}/frameCalcFiles/input.xml">${message(code: 'get.design.edit.frame.calculation.input.file', default: 'Input file (SOAP)')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'get.design.edit.not.yet.created.message', default: 'Not yet performed')}</li>                
              </g:else>
              <g:if test="${designReq.framesCalcReturnTime}">
                <li><a href="${configDirPath}${jobNumber}/frameCalcFiles/output.xml">${message(code: 'get.design.edit.frame.calculation.output.file', default: 'Output file (SOAP)')}</a></li>
              </g:if>
              <g:if test="${designReq.numOfFramesFromFrameCalc}">
                <li>${message(code: 'get.design.edit.frame.calculation.num.of.frames.message', default: 'Number of frames returned from frame calculation')}: ${designReq.numOfFramesFromFrameCalc}</li>
              </g:if>
            </ul>
            
            <h3>${message(code: 'get.design.edit.frame.filtration.outputs.heading', default: 'Frame filtration outputs:')}</h3>
            <ul>
              <g:if test="${designReq.numOfFramesFromFrameCalcInCatalogue}">
                <li>${message(code: 'get.design.edit.frame.calculation.num.of.frames.found.in.catalogue.message', default: 'Number of frames returned from frame calculation found in catalogue')}: ${designReq.numOfFramesFromFrameCalcInCatalogue}</li>
              </g:if>
              <g:if test="${designReq.numOfFramesAfterFrameCalcFilter}">
                <li>${message(code: 'get.design.edit.frame.calculation.num.of.frames.after.filtering.message', default: 'Number of viable frames after filtering')}: ${designReq.numOfFramesAfterFrameCalcFilter}</li>
              </g:if>
              <g:else>
                <li>${message(code: 'get.design.edit.not.yet.created.message', default: 'Not yet performed')}</li>
              </g:else>
            </ul>

            <h2>${message(code: 'get.design.edit.frame.validation.messages.heading', default: 'Frame filtering messages:')}</h2>
            <ul>
              <g:each in="${designReq.frameValidationMessages}" var="thisMessage">
                <li>${thisMessage}</li>
              </g:each>
            </ul>

            <h2>${message(code: 'get.design.edit.lens.validation.messages.heading', default: 'Lens filtering messages:')}</h2>
            <ul>
              <g:each in="${designReq.lensValidationMessages}" var="thisMessage">
                <li>${thisMessage}</li>
              </g:each>
            </ul>


            <h3>${message(code: 'get.design.edit.lens.calculation.inputs.heading', default: 'Lens calculation inputs:')}</h3>
            <ul>
              <g:if test="${designReq.numOfInputsToLensCalc != null}">
                <li>${message(code: 'get.design.edit.frame.calculation.num.of.inputs.to.lens.calc.message', default: 'Number of inputs created to send to lens calculation')}: ${designReq.numOfInputsToLensCalc}</li>
              </g:if>
              <g:if test="${designReq.numOfInputsToLensCalc > 0}">
                <li><a href="${configDirPath}${jobNumber}/lensCalcFiles/input.json">${message(code: 'get.design.edit.lens.calculation.input.file', default: 'Input file (JSON ish)')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'get.design.edit.not.yet.created.message', default: 'Not yet performed')}</li>
              </g:else>
            </ul>
            
            <h3>${message(code: 'get.design.edit.lens.calculation.outputs.heading', default: 'Lens calculation outputs:')}</h3>
            <ul>
              <g:if test="${designReq.lensCalcReturnTime}">
                <li>${message(code: 'get.design.edit.frame.calculation.num.of.outputs.from.lens.calc.message', default: 'Number of outputs from lens calculation')}: ${designReq.numOfResultsFromLensCalc}</li>
                <li>${message(code: 'get.design.edit.frame.calculation.num.of.viable.outputs.from.lens.calc.message', default: 'Number of viable outputs from lens calculation')}: ${designReq.numOfViableCombinations}</li>
                <li><a href="${configDirPath}${jobNumber}/lensCalcFiles/output.json">${message(code: 'get.design.edit.lens.calculation.output.file', default: 'Output file (JSON ish)')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'get.design.edit.not.yet.created.message', default: 'Not yet performed')}</li>
              </g:else>
            </ul>
            
            <h2>${message(code: 'get.design.edit.frame.lens.validation.messages.heading', default: 'Frame-Lens combination filtering messages:')}</h2>
            <ul>
              <g:each in="${designReq.frameLensValidationMessages}" var="thisMessage">
                <li>${thisMessage}</li>
              </g:each>
            </ul>
            
            <h3>${message(code: 'get.design.edit.available.configuration.heading', default: 'Available configurations:')}</h3>
            <ul>
              <g:if test="${hasConfigsAvailable}">
                <li><a href="${configFilePath}">${message(code: 'get.design.edit.available.configurations.file.name', default: 'Available configurations')}</a></li>
              </g:if>
              <g:else>
                <li>${message(code: 'get.design.edit.available.configurations.not.yet.available.message', default: 'Configurations file not yet available')}</li>
              </g:else>
            </ul>

            <h1>${message(code: 'get.design.edit.reset.request.heading', default: 'Reset design request')}</h1>
            <form id="updateForm" name="updateForm" action="update" method="POST">
            <input type="hidden" id="id" name="id" value="${designReq.id}"/>
             <select name="newRequestStatus" id="newRequestStatus">
                <g:each in="${possibleNewRequestStatus}" var="reqStatus">
                  <option value="${reqStatus[0]}">${reqStatus[0]} - ${reqStatus[1]}</option> 
                </g:each>
              </select>
              <input type="submit" name="submitButton" id="submitButton" class="button" value="${message(code: 'get.design.edit.reset.request.button.label', default: 'Reset status')}"/>
            </form>

        </div>

  </body>
</html>
