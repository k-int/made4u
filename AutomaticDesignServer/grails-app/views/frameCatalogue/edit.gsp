<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.frame.edit.page.head.title', default: 'Frame details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <jqui:resources />
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.frame.edit.title', default: 'Individual frame')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${frameInstance.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${frameInstance}"/>            
              </div>
            </g:if>

            <g:form name="frameForm" method="post" enctype="multipart/form-data">

              
              <g:hiddenField name="id" value="${frameInstance.id}"/>
              <g:hiddenField name="version" value="${frameInstance.version}"/>
    
              <div class="propertiesTable">
                <h2>${message(code: 'frame.database.frame.properties.heading', default: 'Frame properties:')}</h2>
                <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: frameInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.identifier.label', default: 'Identifier')}: <em class="required">*</em></td>
                    <td><input type="text" name="identifier" value="${frameInstance.identifier}" title="${message(code: 'frame.identifier.required.label', default: 'An identifier is required')}" class="required"/></td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.material.label', default: 'Material')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="materialId" name="materialId" value="${frameInstance.material.id}" class="required" title="${message(code: 'frame.material.required.message', default: 'A material is required')}" noSelection="${['': message(code: 'frame.material.please.choose.message', default: 'Please choose') ] }" from="${materials}" optionKey="id" optionValue="name"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.rim.type.label', default: 'Rim type')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="rimType" name="rimType" value="${frameInstance.rimType}" class="required" title="${message(code: 'frame.rim.type.required.message', default: 'A rim type is required')}" noSelection="${['': message(code: 'frame.rim.type.please.choose.message', default: 'Please choose') ] }" from="${rimTypes}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.gender.label', default: 'Gender')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="gender" name="gender" value="${frameInstance.gender}" class="required" title="${message(code: 'frame.gender.required.message', default: 'A gender is required')}" noSelection="${['': message(code: 'frame.gender.please.choose.message', default: 'Please choose') ] }" from="${genders}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.rim.shape.label', default: 'Rim shape')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="rimShapeId" name="rimShapeId" value="${frameInstance.rimShape.id}" class="required" title="${message(code: 'frame.rim.shape.required.message', default: 'A rim shape is required')}" noSelection="${['': message(code: 'frame.rim.shape.please.choose.message', default: 'Please choose') ] }" from="${rimShapes}" optionKey="id" optionValue="name"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.endpiece.height.label', default: 'Endpiece height')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="endpieceHeight" name="endpieceHeight" value="${frameInstance.endpieceHeight}" class="required" title="${message(code: 'frame.endpiece.height.required.message', default: 'An endpiece height is required')}" noSelection="${['': message(code: 'frame.endpiece.height.please.choose.message', default: 'Please choose') ] }" from="${endpieceHeights}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.use.label', default: 'Use')}: <em class="required">*</em></td>
                    <td>
                      <g:select id="frameUse" name="frameUse" value="${frameInstance.frameUse}" class="required" title="${message(code: 'frame.use.required.message', default: 'A frame use is required')}" noSelection="${['': message(code: 'frame.use.please.choose.message', default: 'Please choose')]}" from="${frameUses}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'frame.prices.label', default: 'Prices')}:</h3>
                    </td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.fully.personalised.base.price.label', default: 'Fully personalised')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="fullyPersonalisedBasePrice" name="fullyPersonalisedBasePrice" value="${formatNumber(number:frameInstance.fullyPersonalisedBasePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.semi.personalised.base.price.label', default: 'Semi-personalised')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="semiPersonalisedBasePrice" name="semiPersonalisedBasePrice" value="${formatNumber(number:frameInstance.semiPersonalisedBasePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.standard.base.price.label', default: 'Standard')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="standardBasePrice" name="standardBasePrice" value="${formatNumber(number:frameInstance.standardBasePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'frame.time.label', default: 'Times')}:</h3>
                    </td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.fully.personalised.time.label', default: 'Fully personalised')} ${message(code: 'time.day.symbol')}:</td>
                    <td>
                      <input type="text" id="fullyPersonalisedProductionTime" name="fullyPersonalisedProductionTime" value="${frameInstance.fullyPersonalisedProductionTime}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.semi.personalised.time.label', default: 'Semi personalised')} ${message(code: 'time.day.symbol')}:</td>
                    <td>
                      <input type="text" id="semiPersonalisedProductionTime" name="semiPersonalisedProductionTime" value="${frameInstance.semiPersonalisedProductionTime}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.display.order.label', default: 'Display order')}:</td>
                    <td>
                      <input type="text" id="displayOrder" name="displayOrder" value="${frameInstance.displayOrder}"/>
                    </td>
                  </tr>
              </table>
              </div>
              
              <div class="extraConfigOptions">
                <h2>${message(code: 'frame.database.frame.components.heading', default: 'Frame components:')}</h2>
                <div class="extraButtonArea">
                  <ul>
                    <li><a class="button" href='#' id="frontalOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.frontal.options.button.label', default: 'Frontal configuration')}"/> ${message(code: 'frame.database.frontal.options.button.text', default: 'Frontal configuration')}</a></li>
                    <li><a class="button" href="#" id="leftTempleOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.left.temple.options.button.label', default: 'Left temple configuration')}"/> ${message(code: 'frame.database.left.temple.options.button.text', default: 'Left temple configuration')}</a></li>
                    <li><a class="button" href="#" id="rightTempleOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.right.temple.options.button.label', default: 'Right temple configuration')}"/> ${message(code: 'frame.database.right.temple.options.button.text', default: 'Right temple configuration')}</a></li>
                    <li><h3>Extra configuration</h3></li>
                    <li><a class="button" href="#" id="standardsPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.standard.component.button.label', default: 'Edit standards')}"/> ${message(code: 'frame.database.standard.component.button.text', default: 'Edit standards configuration')}</a></li>
                    <li><a class="button" href="#" id="precookedPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.precooked.button.label', default: 'Edit precookeds')}"/> ${message(code: 'frame.database.precooked.button.text', default: 'Edit precookeds')}</a></li>
                  </ul>
                </div>
              </div>
               
              <div class="buttonArea">
                <button type="submit" name="_action_update" class="button" controller="frameCatalogue" onclick="javascript:submitForm()"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="show" id="${frameInstance.id}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

              <input type="hidden" id="finishingAspect" name="finishingAspect" value="${frameInstance.finishingAspect}"/>
              <input type="hidden" id="mattePrice" name="mattePrice" value="${formatNumber(number:frameInstance.mattePrice, format:"0.00")}"/>
              <input type="hidden" id="matteProductionTime" name="matteProductionTime" value="${frameInstance.matteProductionTime}"/>
              <input type="hidden" id="brilliantPrice" name="mattePrice" value="${formatNumber(number:frameInstance.brilliantPrice, format:"0.00")}"/>
              <input type="hidden" id="brilliantProductionTime" name="brilliantProductionTime" value="${frameInstance.brilliantProductionTime}"/>
              <input type="hidden" id="minLensThickness" name="minLensThickness" value="${formatNumber(number:frameInstance.minLensThickness, format:"0.0")}"/>
              <input type="hidden" id="leftTopPupilFrameDistance" name="leftTopPupilFrameDistance" value="${formatNumber(number:frameInstance.leftTopPupilFrameDistance, format:"0.0")}"/>
              <input type="hidden" id="leftBottomPupilFrameDistancePSV" name="leftBottomPupilFrameDistancePSV" value="${formatNumber(number:frameInstance.leftBottomPupilFrameDistancePSV, format:"0.0")}"/>
              <input type="hidden" id="leftBottomPupilFrameDistanceMF" name="leftBottomPupilFrameDistanceMF" value="${formatNumber(number:frameInstance.leftBottomPupilFrameDistanceMF, format:"0.0")}"/>
              <input type="hidden" id="rightTopPupilFrameDistance" name="rightTopPupilFrameDistance" value="${formatNumber(number:frameInstance.rightTopPupilFrameDistance, format:"0.0")}"/>
              <input type="hidden" id="rightBottomPupilFrameDistancePSV" name="rightBottomPupilFrameDistancePSV" value="${formatNumber(number:frameInstance.rightBottomPupilFrameDistancePSV, format:"0.0")}"/>
              <input type="hidden" id="rightBottomPupilFrameDistanceMF" name="rightBottomPupilFrameDistanceMF" value="${formatNumber(number:frameInstance.rightBottomPupilFrameDistanceMF, format:"0.0")}"/>

              <input type="hidden" id="boxingHorizontalStandard" name="dimensions.boxingHorizontalStandard" value="${formatNumber(number:frameInstance.dimensions.boxingHorizontalStandard, format:"0.0")}"/>
              <input type="hidden" id="boxingHorizontalMin" name="dimensions.boxingHorizontalMin" value="${formatNumber(number:frameInstance.dimensions.boxingHorizontalMin, format:"0.0")}"/>
              <input type="hidden" id="boxingHorizontalMax" name="dimensions.boxingHorizontalMax" value="${formatNumber(number:frameInstance.dimensions.boxingHorizontalMax, format:"0.0")}"/>
              <input type="hidden" id="boxingVerticalStandard" name="dimensions.boxingVerticalStandard" value="${formatNumber(number:frameInstance.dimensions.boxingHorizontalStandard, format:"0.0")}"/>
              <input type="hidden" id="boxingVerticalMin" name="dimensions.boxingVerticalMin" value="${formatNumber(number:frameInstance.dimensions.boxingVerticalMin, format:"0.0")}"/>
              <input type="hidden" id="boxingVerticalMax" name="dimensions.boxingVerticalMax" value="${formatNumber(number:frameInstance.dimensions.boxingVerticalMax, format:"0.0")}"/>
              <input type="hidden" id="pantoscopicAngleLeftStandard" name="dimensions.pantoscopicAngleLeftStandard" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftStandard, format:"0.0")}"/>
              <input type="hidden" id="pantoscopicAngleLeftMin" name="dimensions.pantoscopicAngleLeftMin" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftMin, format:"0.0")}"/>
              <input type="hidden" id="pantoscopicAngleLeftMax" name="dimensions.pantoscopicAngleLeftMax" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftMax, format:"0.0")}"/>
              <input type="hidden" id="pantoscopicAngleRightStandard" name="dimensions.pantoscopicAngleRightStandard" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightStandard, format:"0.0")}"/>
              <input type="hidden" id="pantoscopicAngleRightMin" name="dimensions.pantoscopicAngleRightMin" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightMin, format:"0.0")}"/>
              <input type="hidden" id="pantoscopicAngleRightMax" name="dimensions.pantoscopicAngleRightMax" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightMax, format:"0.0")}"/>
              <input type="hidden" id="bridgeWidthStandard" name="dimensions.bridgeWidthStandard" value="${formatNumber(number:frameInstance.dimensions.bridgeWidthStandard, format:"0.0")}"/>
              <input type="hidden" id="bridgeWidthMin" name="dimensions.bridgeWidthMin" value="${formatNumber(number:frameInstance.dimensions.bridgeWidthMin, format:"0.0")}"/>
              <input type="hidden" id="bridgeWidthMax" name="dimensions.bridgeWidthMax" value="${formatNumber(number:frameInstance.dimensions.bridgeWidthMax, format:"0.0")}"/>
              <input type="hidden" id="bridgeHeightStandard" name="dimensions.bridgeHeightStandard" value="${formatNumber(number:frameInstance.dimensions.bridgeHeightStandard, format:"0.0")}"/>
              <input type="hidden" id="bridgeHeightMin" name="dimensions.bridgeHeightMin" value="${formatNumber(number:frameInstance.dimensions.bridgeHeightMin, format:"0.0")}"/>
              <input type="hidden" id="bridgeHeightMax" name="dimensions.bridgeHeightMax" value="${formatNumber(number:frameInstance.dimensions.bridgeHeightMax, format:"0.0")}"/>
              <input type="hidden" id="heelWidthStandard" name="dimensions.heelWidthStandard" value="${formatNumber(number:frameInstance.dimensions.heelWidthStandard, format:"0.0")}"/>
              <input type="hidden" id="heelWidthMin" name="dimensions.heelWidthMin" value="${formatNumber(number:frameInstance.dimensions.heelWidthMin, format:"0.0")}"/>
              <input type="hidden" id="heelWidthMax" name="dimensions.heelWidthMax" value="${formatNumber(number:frameInstance.dimensions.heelWidthMax, format:"0.0")}"/>
              <input type="hidden" id="internalRimReductionStandard" name="dimensions.internalRimReductionStandard" value="${formatNumber(number:frameInstance.dimensions.internalRimReductionStandard, format:"0.0")}"/>
              <input type="hidden" id="internalRimReductionMin" name="dimensions.internalRimReductionMin" value="${formatNumber(number:frameInstance.dimensions.internalRimReductionMin, format:"0.0")}"/>
              <input type="hidden" id="internalRimReductionMax" name="dimensions.internalRimReductionMax" value="${formatNumber(number:frameInstance.dimensions.internalRimReductionMax, format:"0.0")}"/>
              <input type="hidden" id="baseOfFrameStandard" name="dimensions.baseOfFrameStandard" value="${formatNumber(number:frameInstance.dimensions.baseOfFrameStandard, format:"0.0")}"/>
              <input type="hidden" id="baseOfFrameMin" name="dimensions.baseOfFrameMin" value="${formatNumber(number:frameInstance.dimensions.baseOfFrameMin, format:"0.0")}"/>
              <input type="hidden" id="baseOfFrameMax" name="dimensions.baseOfFrameMax" value="${formatNumber(number:frameInstance.dimensions.baseOfFrameMax, format:"0.0")}"/>
              <input type="hidden" id="wrapAngleStandard" name="dimensions.wrapAngleStandard" value="${formatNumber(number:frameInstance.dimensions.wrapAngleStandard, format:"0.0")}"/>
              <input type="hidden" id="wrapAngleMin" name="dimensions.wrapAngleMin" value="${formatNumber(number:frameInstance.dimensions.wrapAngleMin, format:"0.0")}"/>
              <input type="hidden" id="wrapAngleMax" name="dimensions.wrapAngleMax" value="${formatNumber(number:frameInstance.dimensions.wrapAngleMax, format:"0.0")}"/>
              <input type="hidden" id="totalLengthStandard" name="dimensions.totalLengthStandard" value="${formatNumber(number:frameInstance.dimensions.totalLengthStandard, format:"0.0")}"/>
              
              <input type="hidden" id="leftTempleFinishingAspect" name="leftTempleFinishingAspect" value="${frameInstance.leftTempleFinishingAspect}"/>
              <input type="hidden" id="leftTempleMattePrice" name="leftTempleMattePrice" value="${formatNumber(number:frameInstance.leftTempleMattePrice, format:"0.00")}"/>
              <input type="hidden" id="leftTempleMatteProductionTime" name="leftTempleMatteProductionTime" value="${frameInstance.leftTempleMatteProductionTime}"/>
              <input type="hidden" id="leftTempleBrilliantPrice" name="leftTempleMattePrice" value="${formatNumber(number:frameInstance.leftTempleBrilliantPrice, format:"0.00")}"/>
              <input type="hidden" id="leftTempleBrilliantProductionTime" name="leftTempleBrilliantProductionTime" value="${frameInstance.leftTempleBrilliantProductionTime}"/>

              <input type="hidden" id="leftTempleLengthStandard" name="leftTempleDims.lengthStandard" value="${formatNumber(number:frameInstance.leftTempleDims.lengthStandard, format:"0.0")}"/>
              <input type="hidden" id="leftTempleLengthMin" name="leftTempleDims.lengthMin" value="${formatNumber(number:frameInstance.leftTempleDims.lengthMin, format:"0.0")}"/>
              <input type="hidden" id="leftTempleLengthMax" name="leftTempleDims.lengthMax" value="${formatNumber(number:frameInstance.leftTempleDims.lengthMax, format:"0.0")}"/>
              <input type="hidden" id="leftTempleOpeningAngleStandard" name="leftTempleDims.openingAngleStandard" value="${formatNumber(number:frameInstance.leftTempleDims.openingAngleStandard, format:"0.0")}"/>
              <input type="hidden" id="leftTempleOpeningAngleMin" name="leftTempleDims.openingAngleMin" value="${formatNumber(number:frameInstance.leftTempleDims.openingAngleMin, format:"0.0")}"/>
              <input type="hidden" id="leftTempleOpeningAngleMax" name="leftTempleDims.openingAngleMax" value="${formatNumber(number:frameInstance.leftTempleDims.openingAngleMax, format:"0.0")}"/>
              <input type="hidden" id="leftTempleMainCurvatureStandard" name="leftTempleDims.mainCurvatureStandard" value="${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureStandard, format:"0.0")}"/>
              <input type="hidden" id="leftTempleMainCurvatureMin" name="leftTempleDims.mainCurvatureMin" value="${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureMin, format:"0.0")}"/>
              <input type="hidden" id="leftTempleMainCurvatureMax" name="leftTempleDims.mainCurvatureMax" value="${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureMax, format:"0.0")}"/>
              <input type="hidden" id="leftTempleTerminalAngleStandard" name="leftTempleDims.terminalAngleStandard" value="${formatNumber(number:frameInstance.leftTempleDims.terminalAngleStandard, format:"0.0")}"/>
              <input type="hidden" id="leftTempleTerminalAngleMin" name="leftTempleDims.terminalAngleMin" value="${formatNumber(number:frameInstance.leftTempleDims.terminalAngleMin, format:"0.0")}"/>
              <input type="hidden" id="leftTempleTerminalAngleMax" name="leftTempleDims.terminalAngleMax" value="${formatNumber(number:frameInstance.leftTempleDims.terminalAngleMax, format:"0.0")}"/>
              
              <input type="hidden" id="rightTempleFinishingAspect" name="rightTempleFinishingAspect" value="${frameInstance.leftTempleFinishingAspect}"/>
              <input type="hidden" id="rightTempleMattePrice" name="rightTempleMattePrice" value="${formatNumber(number:frameInstance.leftTempleMattePrice, format:"0.00")}"/>
              <input type="hidden" id="rightTempleMatteProductionTime" name="rightTempleMatteProductionTime" value="${frameInstance.leftTempleMatteProductionTime}"/>
              <input type="hidden" id="rightTempleBrilliantPrice" name="rightTempleMattePrice" value="${formatNumber(number:frameInstance.leftTempleBrilliantPrice, format:"0.00")}"/>
              <input type="hidden" id="rightTempleBrilliantProductionTime" name="rightTempleBrilliantProductionTime" value="${frameInstance.leftTempleBrilliantProductionTime}"/>

              <input type="hidden" id="rightTempleLengthStandard" name="rightTempleDims.lengthStandard" value="${formatNumber(number:frameInstance.rightTempleDims.lengthStandard, format:"0.0")}"/>
              <input type="hidden" id="rightTempleLengthMin" name="rightTempleDims.lengthMin" value="${formatNumber(number:frameInstance.rightTempleDims.lengthMin, format:"0.0")}"/>
              <input type="hidden" id="rightTempleLengthMax" name="rightTempleDims.lengthMax" value="${formatNumber(number:frameInstance.rightTempleDims.lengthMax, format:"0.0")}"/>
              <input type="hidden" id="rightTempleOpeningAngleStandard" name="rightTempleDims.openingAngleStandard" value="${formatNumber(number:frameInstance.rightTempleDims.openingAngleStandard, format:"0.0")}"/>
              <input type="hidden" id="rightTempleOpeningAngleMin" name="rightTempleDims.openingAngleMin" value="${formatNumber(number:frameInstance.rightTempleDims.openingAngleMin, format:"0.0")}"/>
              <input type="hidden" id="rightTempleOpeningAngleMax" name="rightTempleDims.openingAngleMax" value="${formatNumber(number:frameInstance.rightTempleDims.openingAngleMax, format:"0.0")}"/>
              <input type="hidden" id="rightTempleMainCurvatureStandard" name="rightTempleDims.mainCurvatureStandard" value="${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureStandard, format:"0.0")}"/>
              <input type="hidden" id="rightTempleMainCurvatureMin" name="rightTempleDims.mainCurvatureMin" value="${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureMin, format:"0.0")}"/>
              <input type="hidden" id="rightTempleMainCurvatureMax" name="rightTempleDims.mainCurvatureMax" value="${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureMax, format:"0.0")}"/>
              <input type="hidden" id="rightTempleTerminalAngleStandard" name="rightTempleDims.terminalAngleStandard" value="${formatNumber(number:frameInstance.rightTempleDims.terminalAngleStandard, format:"0.0")}"/>
              <input type="hidden" id="rightTempleTerminalAngleMin" name="rightTempleDims.terminalAngleMin" value="${formatNumber(number:frameInstance.rightTempleDims.terminalAngleMin, format:"0.0")}"/>
              <input type="hidden" id="rightTempleTerminalAngleMax" name="rightTempleDims.terminalAngleMax" value="${formatNumber(number:frameInstance.rightTempleDims.terminalAngleMax, format:"0.0")}"/>
              
              <input type="hidden" id="hingeId" name="hingeId" value="${frameInstance.hinge?.id}"/>
              <input type="hidden" id="nylonId" name="nylonId" value="${frameInstance.nylon?.id}"/>
              <input type="hidden" id="screwId" name="screwId" value="${frameInstance.screw?.id}"/>
              
              <input type="hidden" id="precookedDeleteVals" name="precookedDeleteVals"/>
              <input type="hidden" id="precookedReferenceVals" name="precookedReferenceVals"/>
              <input type="hidden" id="precookedBridgeWidthVals" name="precookedBridgeWidthVals"/>
              <input type="hidden" id="precookedHeelWidthVals" name="precookedHeelWidthVals"/>
              
              <input type="hidden" id="frontalNewBaseColourIds" name="frontalNewBaseColourIds"/>
              <input type="hidden" id="frontalRemoveBaseColourIds" name="frontalRemoveBaseColourIds"/>
              <input type="hidden" id="frontalNewTextureImageIds" name="frontalNewTextureImageIds"/>
              <input type="hidden" id="frontalNewTextureReferences" name="frontalNewTextureReferences"/>
              <input type="hidden" id="frontalNewTextureXVals" name="frontalNewTextureXVals"/>
              <input type="hidden" id="frontalNewTextureYVals" name="frontalNewTextureYVals"/>
              <input type="hidden" id="frontalNewTextureScaleFactors" name="frontalNewTextureScaleFactors"/>
              <input type="hidden" id="frontalNewTexturePrices" name="frontalNewTexturePrices"/>
              <input type="hidden" id="frontalNewTextureProductionTimes" name="frontalNewTextureProductionTimes"/>
              <input type="hidden" id="frontalTextureDeleteVals" name="frontalTextureDeleteVals"/>

              <input type="hidden" id="leftTempleNewBaseColourIds" name="leftTempleNewBaseColourIds"/>
              <input type="hidden" id="leftTempleRemoveBaseColourIds" name="leftTempleRemoveBaseColourIds"/>
              <input type="hidden" id="leftTempleNewTextureImageIds" name="leftTempleNewTextureImageIds"/>
              <input type="hidden" id="leftTempleNewTextureReferences" name="leftTempleNewTextureReferences"/>
              <input type="hidden" id="leftTempleNewTextureXVals" name="leftTempleNewTextureXVals"/>
              <input type="hidden" id="leftTempleNewTextureYVals" name="leftTempleNewTextureYVals"/>
              <input type="hidden" id="leftTempleNewTextureScaleFactors" name="leftTempleNewTextureScaleFactors"/>
              <input type="hidden" id="leftTempleNewTexturePrices" name="leftTempleNewTexturePrices"/>
              <input type="hidden" id="leftTempleNewTextureProductionTimes" name="leftTempleNewTextureProductionTimes"/>
              <input type="hidden" id="leftTempleTextureDeleteVals" name="leftTempleTextureDeleteVals"/>

              <input type="hidden" id="rightTempleNewBaseColourIds" name="rightTempleNewBaseColourIds"/>
              <input type="hidden" id="rightTempleRemoveBaseColourIds" name="rightTempleRemoveBaseColourIds"/>
              <input type="hidden" id="rightTempleNewTextureImageIds" name="rightTempleNewTextureImageIds"/>
              <input type="hidden" id="rightTempleNewTextureReferences" name="rightTempleNewTextureReferences"/>
              <input type="hidden" id="rightTempleNewTextureXVals" name="rightTempleNewTextureXVals"/>
              <input type="hidden" id="rightTempleNewTextureYVals" name="rightTempleNewTextureYVals"/>
              <input type="hidden" id="rightTempleNewTextureScaleFactors" name="rightTempleNewTextureScaleFactors"/>
              <input type="hidden" id="rightTempleNewTexturePrices" name="rightTempleNewTexturePrices"/>
              <input type="hidden" id="rightTempleNewTextureProductionTimes" name="rightTempleNewTextureProductionTimes"/>
              <input type="hidden" id="rightTempleTextureDeleteVals" name="rightTempleTextureDeleteVals"/>
              
              <input type="hidden" id="totalSizeBridgeWidth1" name="dimensions.totalLength.bridgeWidth1" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth1, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin1" name="dimensions.totalLength.min1" value="${formatNumber(number:frameInstance.dimensions.totalLength.min1, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax1" name="dimensions.totalLength.max1" value="${formatNumber(number:frameInstance.dimensions.totalLength.max1, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth2" name="dimensions.totalLength.bridgeWidth2" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth2, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin2" name="dimensions.totalLength.min2" value="${formatNumber(number:frameInstance.dimensions.totalLength.min2, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax2" name="dimensions.totalLength.max2" value="${formatNumber(number:frameInstance.dimensions.totalLength.max2, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth3" name="dimensions.totalLength.bridgeWidth3" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth3, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin3" name="dimensions.totalLength.min3" value="${formatNumber(number:frameInstance.dimensions.totalLength.min3, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax3" name="dimensions.totalLength.max3" value="${formatNumber(number:frameInstance.dimensions.totalLength.max3, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth4" name="dimensions.totalLength.bridgeWidth4" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth4, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin4" name="dimensions.totalLength.min4" value="${formatNumber(number:frameInstance.dimensions.totalLength.min4, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax4" name="dimensions.totalLength.max4" value="${formatNumber(number:frameInstance.dimensions.totalLength.max4, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth5" name="dimensions.totalLength.bridgeWidth5" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth5, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin5" name="dimensions.totalLength.min5" value="${formatNumber(number:frameInstance.dimensions.totalLength.min5, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax5" name="dimensions.totalLength.max5" value="${formatNumber(number:frameInstance.dimensions.totalLength.max5, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth6" name="dimensions.totalLength.bridgeWidth6" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth6, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin6" name="dimensions.totalLength.min6" value="${formatNumber(number:frameInstance.dimensions.totalLength.min6, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax6" name="dimensions.totalLength.max6" value="${formatNumber(number:frameInstance.dimensions.totalLength.max6, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth7" name="dimensions.totalLength.bridgeWidth7" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth7, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin7" name="dimensions.totalLength.min7" value="${formatNumber(number:frameInstance.dimensions.totalLength.min7, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax7" name="dimensions.totalLength.max7" value="${formatNumber(number:frameInstance.dimensions.totalLength.max7, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth8" name="dimensions.totalLength.bridgeWidth8" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth8, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin8" name="dimensions.totalLength.min8" value="${formatNumber(number:frameInstance.dimensions.totalLength.min8, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax8" name="dimensions.totalLength.max8" value="${formatNumber(number:frameInstance.dimensions.totalLength.max8, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth9" name="dimensions.totalLength.bridgeWidth9" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth9, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin9" name="dimensions.totalLength.min9" value="${formatNumber(number:frameInstance.dimensions.totalLength.min9, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax9" name="dimensions.totalLength.max9" value="${formatNumber(number:frameInstance.dimensions.totalLength.max9, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth10" name="dimensions.totalLength.bridgeWidth10" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth10, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin10" name="dimensions.totalLength.min10" value="${formatNumber(number:frameInstance.dimensions.totalLength.min10, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax10" name="dimensions.totalLength.max10" value="${formatNumber(number:frameInstance.dimensions.totalLength.max10, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth11" name="dimensions.totalLength.bridgeWidth11" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth11, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin11" name="dimensions.totalLength.min11" value="${formatNumber(number:frameInstance.dimensions.totalLength.min11, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax11" name="dimensions.totalLength.max11" value="${formatNumber(number:frameInstance.dimensions.totalLength.max11, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth12" name="dimensions.totalLength.bridgeWidth12" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth12, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin12" name="dimensions.totalLength.min12" value="${formatNumber(number:frameInstance.dimensions.totalLength.min12, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax12" name="dimensions.totalLength.max12" value="${formatNumber(number:frameInstance.dimensions.totalLength.max12, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth13" name="dimensions.totalLength.bridgeWidth13" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth13, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin13" name="dimensions.totalLength.min13" value="${formatNumber(number:frameInstance.dimensions.totalLength.min13, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax13" name="dimensions.totalLength.max13" value="${formatNumber(number:frameInstance.dimensions.totalLength.max13, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth14" name="dimensions.totalLength.bridgeWidth14" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth14, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin14" name="dimensions.totalLength.min14" value="${formatNumber(number:frameInstance.dimensions.totalLength.min14, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax14" name="dimensions.totalLength.max14" value="${formatNumber(number:frameInstance.dimensions.totalLength.max14, format:"0.0")}"/>
              <input type="hidden" id="totalSizeBridgeWidth15" name="dimensions.totalLength.bridgeWidth15" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth15, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMin15" name="dimensions.totalLength.min15" value="${formatNumber(number:frameInstance.dimensions.totalLength.min15, format:"0.0")}"/>
              <input type="hidden" id="totalSizeMax15" name="dimensions.totalLength.max15" value="${formatNumber(number:frameInstance.dimensions.totalLength.max15, format:"0.0")}"/>
              

              
            </g:form>

            
                        
              <div id="frontalOptionsPopup" class="extraButtonArea popup">
                <p>${message(code: 'frame.database.frontal.options.popup.text')}</p>
                <ul>
                  <li><a class="button" href="#" id="frontalPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.frontal.component.button.label', default: 'Edit frontal')}"/> ${message(code: 'frame.database.frontal.component.button.text', default: 'Frontal configuration')}</a></li>
                  <li><a class="button" href="#" id="frontalDimsPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.frontal.dimensions.button.label', default: 'Edit frontal dimensions')}"/> ${message(code: 'frame.database.frontal.dimensions.button.text', default: 'Frontal dimensions')}</a></li>
                  <li><a class="button" href="#" id="frontalDecorationPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.frontal.decoration.button.label', default: 'Edit frontal decoration')}"/> ${message(code: 'frame.database.frontal.decoration.button.text', default: 'Frontal decoration')}</a></li>
                </ul>
              </div>
            
              <div id="leftTempleOptionsPopup" class="extraButtonArea popup">
                <p>${message(code: 'frame.database.left.temple.options.popup.text')}</p>
                <ul>
                  <li><a class="button" href="#" id="leftTemplePopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.left.temple.component.button.label', default: 'Edit left temple')}"/> ${message(code: 'frame.database.left.temple.component.button.text', default: 'Left temple configuration')}</a></li>
                  <li><a class="button" href="#" id="leftTempleDimsPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.left.temple.dimensions.button.label', default: 'Edit left temple dimensions')}"/> ${message(code: 'frame.database.left.temple.dimensions.button.text', default: 'Left temple dimensions')}</a></li>
                  <li><a class="button" href="#" id="leftTempleDecorationPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.left.temple.decoration.button.label', default: 'Edit left temple decoration')}"/> ${message(code: 'frame.database.left.temple.decoration.button.text', default: 'Left temple decoration')}</a></li>
                </ul>
              </div>

            <div id="rightTempleOptionsPopup" class="extraButtonArea popup">
                <p>${message(code: 'frame.database.right.temple.options.popup.text')}</p>
                <ul>
                  <li><a class="button" href="#" id="rightTemplePopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.right.temple.component.button.label', default: 'Edit right temple')}"/> ${message(code: 'frame.database.right.temple.component.button.text', default: 'Right temple configuration')}</a></li>
                  <li><a class="button" href="#" id="rightTempleDimsPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.right.temple.dimensions.button.label', default: 'Edit right temple dimensions')}"/> ${message(code: 'frame.database.right.temple.dimensions.button.text', default: 'Right temple dimensions')}</a></li>
                  <li><a class="button" href="#" id="rightTempleDecorationPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.right.temple.decoration.button.label', default: 'Edit right temple decoration')}"/> ${message(code: 'frame.database.right.temple.decoration.button.text', default: 'Right temple decoration')}</a></li>
                </ul>
              </div>
                
              <div id="frontalConfigPopup" class="popup">                
                <table class="popupTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.finishing.aspect', default: 'Finishing aspect')}:</td>
                    <td>
                      <g:select id="popupFinishingAspect" name="popupFinishingAspect" value="${frameInstance.finishingAspect}" class="required" title="${message(code: 'frame.finishing.aspect.required.message', default: 'A finishing aspect is required')}" noSelection="${['': message(code: 'frame.finishing.aspect.please.choose.message', default: 'Please choose')]}" from="${finishingAspects}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'frame.database.frontal.config.popup.matte.heading', default: 'Matte finish:')}</td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.matte.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="popupMattePrice" name="popupMattePrice" value="${formatNumber(number:frameInstance.mattePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.matte.time', default: 'Production time')}:</td>
                    <td>
                      <input type="text" id="popupMatteProductionTime" name="popupMatteProductionTime" value="${frameInstance.matteProductionTime}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'frame.database.frontal.config.popup.brilliant.heading', default: 'Brilliant finish:')}</td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.brilliant.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="popupBrilliantPrice" name="popupBrilliantPrice" value="${formatNumber(number:frameInstance.brilliantPrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.brilliant.time', default: 'Production time')}:</td>
                    <td>
                      <input type="text" id="popupBrilliantProductionTime" name="popupBrilliantProductionTime" value="${frameInstance.brilliantProductionTime}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.min.lens.thickness', default: 'Minimum lens thickness')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupMinLensThickness" name="popupMinLensThickness" value="${formatNumber(number:frameInstance.minLensThickness, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'frame.database.frontal.config.popup.left.eye.heading', default: 'Left eye')}:</h3></td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.left.eye.top.pupil.frame.distance', default: 'Top pupil-frame distance')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupLeftTopPupilFrameDistance" name="popupLeftTopPupilFrameDistance" value="${formatNumber(number:frameInstance.leftTopPupilFrameDistance, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.left.eye.bottom.pupil.frame.distance.psv', default: 'Bottom pupil-frame distance (PSV)')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupLeftBottomPupilFrameDistancePSV" name="popupLeftBottomPupilFrameDistancePSV" value="${formatNumber(number:frameInstance.leftBottomPupilFrameDistancePSV, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.left.eye.bottom.pupil.frame.distance.mf', default: 'Bottom pupil-frame distance (MF)')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupLeftBottomPupilFrameDistanceMF" name="popupLeftBottomPupilFrameDistanceMF" value="${formatNumber(number:frameInstance.leftBottomPupilFrameDistanceMF, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'frame.database.frontal.config.popup.right.eye.heading', default: 'Right eye')}:</h3></td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.right.eye.top.pupil.frame.distance', default: 'Top pupil-frame distance')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupRightTopPupilFrameDistance" name="popupRightTopPupilFrameDistance" value="${formatNumber(number:frameInstance.rightTopPupilFrameDistance, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.right.eye.bottom.pupil.frame.distance.psv', default: 'Bottom pupil-frame distance (PSV)')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupRightBottomPupilFrameDistancePSV" name="popupRightBottomPupilFrameDistancePSV" value="${formatNumber(number:frameInstance.rightBottomPupilFrameDistancePSV, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.right.eye.bottom.pupil.frame.distance.mf', default: 'Bottom pupil-frame distance (MF)')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupRightBottomPupilFrameDistanceMF" name="popupRightBottomPupilFrameDistanceMF" value="${formatNumber(number:frameInstance.rightBottomPupilFrameDistanceMF, format:"0.0")}"/>
                    </td>
                  </tr>
                </table>
              </div>
              
              <div id="frontalDimsConfigPopup" class="popup">
                <table class="popupTable">
                  <tr>
                    <td/>
                    <th>${message(code: 'frame.database.frontal.dimensions.config.popup.standard.heading', default: 'Standard')}</th>
                    <th>${message(code: 'frame.database.frontal.dimensions.config.popup.min.heading', default: 'Minimum')}</th>
                    <th>${message(code: 'frame.database.frontal.dimensions.config.popup.max.heading', default: 'Maximum')}</th>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.boxing.horizontal', default: 'Boxing horizontal')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupBoxingHorizontalStandard" name="popupBoxingHorizontalStandard" value="${formatNumber(number:frameInstance.dimensions.boxingHorizontalStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBoxingHorizontalMin" name="popupBoxingHorizontalMin" value="${formatNumber(number:frameInstance.dimensions.boxingHorizontalMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBoxingHorizontalMax" name="popupBoxingHorizontalMax" value="${formatNumber(number:frameInstance.dimensions.boxingHorizontalMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.boxing.vertical', default: 'Boxing vertical')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupBoxingVerticalStandard" name="popupBoxingVerticalStandard" value="${formatNumber(number:frameInstance.dimensions.boxingVerticalStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBoxingVerticalMin" name="popupBoxingVerticalMin" value="${formatNumber(number:frameInstance.dimensions.boxingVerticalMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBoxingVerticalMax" name="popupBoxingVerticalMax" value="${formatNumber(number:frameInstance.dimensions.boxingVerticalMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.pantoscopic.angle.left', default: 'Left pantoscopic angle')} ${message(code: 'angle.degree.symbol')}:</td>
                    <td>
                      <input type="text" id="popupPantoscopicAngleLeftStandard" name="popupPantoscopicAngleLeftStandard" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupPantoscopicAngleLeftMin" name="popupPantoscopicAngleLeftMin" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupPantoscopicAngleLeftMax" name="popupPantoscopicAngleLeftMax" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.pantascopic.angle.right', default: 'Right pantoscopic angle')} ${message(code: 'angle.degree.symbol')}:</td>
                    <td>
                      <input type="text" id="popupPantoscopicAngleRightStandard" name="popupPantoscopicAngleRightStandard" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupPantoscopicAngleRightMin" name="popupPantoscopicAngleRightMin" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupPantoscopicAngleRightMax" name="popupPantoscopicAngleRightMax" value="${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.bridge,width', default: 'Bridge width')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupBridgeWidthStandard" name="popupBridgeWidthStandard" value="${formatNumber(number:frameInstance.dimensions.bridgeWidthStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBridgeWidthMin" name="popupBridgeWidthMin" value="${formatNumber(number:frameInstance.dimensions.bridgeWidthMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBridgeWidthMax" name="popupBridgeWidthMax" value="${formatNumber(number:frameInstance.dimensions.bridgeWidthMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.bridge.height', default: 'Bridge height')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupBridgeHeightStandard" name="popupBridgeHeightStandard" value="${formatNumber(number:frameInstance.dimensions.bridgeHeightStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBridgeHeightMin" name="popupBridgeHeightMin" value="${formatNumber(number:frameInstance.dimensions.bridgeHeightMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBridgeHeightMax" name="popupBridgeHeightMax" value="${formatNumber(number:frameInstance.dimensions.bridgeHeightMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.heel.width', default: 'Heel width')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupHeelWidthStandard" name="popupHeelWidthStandard" value="${formatNumber(number:frameInstance.dimensions.heelWidthStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupHeelWidthMin" name="popupHeelWidthMin" value="${formatNumber(number:frameInstance.dimensions.heelWidthMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupHeelWidthMax" name="popupHeelWidthMax" value="${formatNumber(number:frameInstance.dimensions.heelWidthMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.internal.rim.reduction', default: 'Internal rim reduction')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupInternalRimReductionStandard" name="popupInternalRimReductionStandard" value="${formatNumber(number:frameInstance.dimensions.internalRimReductionStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupInternalRimReductionMin" name="popupInternalRimReductionMin" value="${formatNumber(number:frameInstance.dimensions.internalRimReductionMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupInternalRimReductionMax" name="popupInternalRimReductionMax" value="${formatNumber(number:frameInstance.dimensions.internalRimReductionMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.base.of.frame', default: 'Base of frame')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupBaseOfFrameStandard" name="popupBaseOfFrameStandard" value="${formatNumber(number:frameInstance.dimensions.baseOfFrameStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBaseOfFrameMin" name="popupBaseOfFrameMin" value="${formatNumber(number:frameInstance.dimensions.baseOfFrameMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupBaseOfFrameMax" name="popupBaseOfFrameMax" value="${formatNumber(number:frameInstance.dimensions.baseOfFrameMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.wrap angle', default: 'Wrap angle')} ${message(code: 'angle.degree.symbol')}:</td>
                    <td>
                      <input type="text" id="popupWrapAngleStandard" name="popupWrapAngleStandard" value="${formatNumber(number:frameInstance.dimensions.wrapAngleStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupWrapAngleMin" name="popupWrapAngleMin" value="${formatNumber(number:frameInstance.dimensions.wrapAngleMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupWrapAngleMax" name="popupWrapAngleMax" value="${formatNumber(number:frameInstance.dimensions.wrapAngleMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.total.length.standard', default: 'Total length')} ${message(code: 'distance.mm.symbol')}:</td>
                    <td>
                      <input type="text" id="popupTotalLengthStandard" name="popupTotalLengthStandard" value="${formatNumber(number:frameInstance.dimensions.totalLengthStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      
                    </td>
                    <td>
                      
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"></td>
                    <td colspan="3">
                      <a class="button" href="#" id="totalSizePopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.total.size.button.label', default: 'Edit total sizes')}"/> ${message(code: 'frame.database.total.size.button.text', default: 'Total size configuration')}</a>
                    </td>
                  </tr>
                </table>
              </div>

              <div id="totalSizeConfigPopup" class="popup">
                <table class="popupTable">
                  <tr>
                    <th>${message(code: 'frame.database.total.size.config.popup.bridge.width.heading', default: 'Bridge width')} ${message(code: 'distance.mm.symbol')}</th>
                    <th>${message(code: 'frame.database.total.size.config.popup.min.heading', default: 'Minimum')} ${message(code: 'distance.mm.symbol')}</th>
                    <th>${message(code: 'frame.database.total.size.config.popup.max.heading', default: 'Maximum')} ${message(code: 'distance.mm.symbol')}</th>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth1" name="popupTotalSizeBridgeWidth1" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth1, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin1" name="popupTotalSizeMin1" value="${formatNumber(number:frameInstance.dimensions.totalLength.min1, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax1" name="popupTotalSizeMax1" value="${formatNumber(number:frameInstance.dimensions.totalLength.max1, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth2" name="popupTotalSizeBridgeWidth2" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth2, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin2" name="popupTotalSizeMin2" value="${formatNumber(number:frameInstance.dimensions.totalLength.min2, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax2" name="popupTotalSizeMax2" value="${formatNumber(number:frameInstance.dimensions.totalLength.max2, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth3" name="popupTotalSizeBridgeWidth3" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth3, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin3" name="popupTotalSizeMin3" value="${formatNumber(number:frameInstance.dimensions.totalLength.min3, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax3" name="popupTotalSizeMax3" value="${formatNumber(number:frameInstance.dimensions.totalLength.max3, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth4" name="popupTotalSizeBridgeWidth4" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth4, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin4" name="popupTotalSizeMin4" value="${formatNumber(number:frameInstance.dimensions.totalLength.min4, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax4" name="popupTotalSizeMax4" value="${formatNumber(number:frameInstance.dimensions.totalLength.max4, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth5" name="popupTotalSizeBridgeWidth5" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth5, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin5" name="popupTotalSizeMin5" value="${formatNumber(number:frameInstance.dimensions.totalLength.min5, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax5" name="popupTotalSizeMax5" value="${formatNumber(number:frameInstance.dimensions.totalLength.max5, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth6" name="popupTotalSizeBridgeWidth6" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth6, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin6" name="popupTotalSizeMin6" value="${formatNumber(number:frameInstance.dimensions.totalLength.min6, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax6" name="popupTotalSizeMax6" value="${formatNumber(number:frameInstance.dimensions.totalLength.max6, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth7" name="popupTotalSizeBridgeWidth7" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth7, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin7" name="popupTotalSizeMin7" value="${formatNumber(number:frameInstance.dimensions.totalLength.min7, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax7" name="popupTotalSizeMax7" value="${formatNumber(number:frameInstance.dimensions.totalLength.max7, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth8" name="popupTotalSizeBridgeWidth8" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth8, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin8" name="popupTotalSizeMin8" value="${formatNumber(number:frameInstance.dimensions.totalLength.min8, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax8" name="popupTotalSizeMax8" value="${formatNumber(number:frameInstance.dimensions.totalLength.max8, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth9" name="popupTotalSizeBridgeWidth9" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth9, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin9" name="popupTotalSizeMin9" value="${formatNumber(number:frameInstance.dimensions.totalLength.min9, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax9" name="popupTotalSizeMax9" value="${formatNumber(number:frameInstance.dimensions.totalLength.max9, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth10" name="popupTotalSizeBridgeWidth10" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth10, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin10" name="popupTotalSizeMin10" value="${formatNumber(number:frameInstance.dimensions.totalLength.min10, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax10" name="popupTotalSizeMax10" value="${formatNumber(number:frameInstance.dimensions.totalLength.max10, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth11" name="popupTotalSizeBridgeWidth11" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth11, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin11" name="popupTotalSizeMin11" value="${formatNumber(number:frameInstance.dimensions.totalLength.min11, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax11" name="popupTotalSizeMax11" value="${formatNumber(number:frameInstance.dimensions.totalLength.max11, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth12" name="popupTotalSizeBridgeWidth12" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth12, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin12" name="popupTotalSizeMin12" value="${formatNumber(number:frameInstance.dimensions.totalLength.min12, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax12" name="popupTotalSizeMax12" value="${formatNumber(number:frameInstance.dimensions.totalLength.max12, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth13" name="popupTotalSizeBridgeWidth13" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth13, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin13" name="popupTotalSizeMin13" value="${formatNumber(number:frameInstance.dimensions.totalLength.min13, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax13" name="popupTotalSizeMax13" value="${formatNumber(number:frameInstance.dimensions.totalLength.max13, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth14" name="popupTotalSizeBridgeWidth14" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth14, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin14" name="popupTotalSizeMin14" value="${formatNumber(number:frameInstance.dimensions.totalLength.min14, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax14" name="popupTotalSizeMax14" value="${formatNumber(number:frameInstance.dimensions.totalLength.max14, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" id="popupTotalSizeBridgeWidth15" name="popupTotalSizeBridgeWidth15" value="${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth15, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMin15" name="popupTotalSizeMin15" value="${formatNumber(number:frameInstance.dimensions.totalLength.min15, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupTotalSizeMax15" name="popupTotalSizeMax15" value="${formatNumber(number:frameInstance.dimensions.totalLength.max15, format:"0.0")}"/>
                    </td>
                  </tr>
                </table>
              </div>
            
              <div id="frontalDecorationConfigPopup" class="popup">
                <h2>${message(code: 'frame.database.frontal.decoration.config.popup.colour.heading', default: 'Base colour')}</h2>
                <table id="frontalBaseColourList" class="popupTable">
                  <g:if test="${frameInstance.frontalBaseColours?.size() != 0}">
                    <tr id="frontalBaseColourListTableHeading">
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.colour.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <g:each in="${frameInstance.frontalBaseColours}" status="i" var="colourInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>${colourInstance.name}</td>
                        <td><input type="checkbox" class="popupFrontalBaseColourDelVal" value="${colourInstance.id}"/></td>
                      </tr>
                    </g:each>
                  </g:if>
                  <g:else>
                    <tr class="hidden" id="frontalBaseColourListTableHeading">
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.colour.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <tr>
                      <td colspan="2" id="frontalBaseColourNoneLinkedEntry">
                        ${message(code: 'frame.database.frontal.decoration.config.popup.linked.colour.none.linked.message', default: 'None linked')}
                      </td>
                    </tr>
                  </g:else>
                </table>
                
                <select id="popupFrontalNewBaseColour" name="popupFrontalNewBaseColour">
                  <option value="">${message(code: 'frame.database.frontal.decoration.config.popup.base.colour.please.choose.message', default: 'Please choose')}</option>
                  <g:each in="${availableFrontalBaseColours}" var="thisColour">
                    <option value="${thisColour.id}--${thisColour.name}">${thisColour.name}</option>
                  </g:each>
                </select>
                
                <a class="button" href='#' id="addFrontalColourButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.frontal.config.popup.add.base.colour.button.label', default: 'Add colour')}"/> ${message(code: 'frame.database.frontal.config.popup.add.base.colour.button.text', default: 'Add')}</a>
                
                
                <h2>${message(code: 'frame.database.frontal.decoration.config.popup.texture.heading', default: 'Texture (inkjet printed to front)')}</h2>
                <table class="popupTable" id="frontalTextureListTable">
                  
                  <g:if test="${frameInstance.frontalTexture?.size() != 0}">
                    <tr id="frontalTextureListTableHeading">
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.image.heading', default: 'Image')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.reference.heading', default: 'Reference')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.x.heading', default: 'X')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.y.heading', default: 'Y')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.scale.heading', default: 'Scale factor')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.price.heading', default: 'Price')} ${message(code: 'price.currency.symbol')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.production.time.heading', default: 'Production time')} ${message(code: 'time.day.symbol')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <g:each in="${frameInstance.frontalTexture}" status="i" var="textureInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><a href="${textureInstance.image?.path}" target="_blank" title="${message(code: 'frame.database.frontal.decoration.config.popup.texture.image.link.label', default: 'View image')}">${textureInstance.image?.reference}</a></td>
                        <td>${textureInstance.reference}</td>
                        <td>${textureInstance.x}</td>
                        <td>${textureInstance.y}</td>
                        <td>${formatNumber(number:textureInstance.scaleFactor, format:"0.0")}</td>
                        <td>${formatNumber(number:textureInstance.price, format:"0.00")}</td>
                        <td>${textureInstance.productionTime}</td>
                        <td><input type="checkbox" name="popupFrontalTextureDelete-${i}" id="popupFrontalTextureDelete-${i}" value="${textureInstance.id}"</td>
                      </tr>
                    </g:each>
                  </g:if>
                  <g:else>
                    <tr id="frontalTextureListTableHeading" class="hidden">
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.image.heading', default: 'Image')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.reference.heading', default: 'Reference')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.x.heading', default: 'X')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.y.heading', default: 'Y')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.scale.heading', default: 'Scale factor')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.price.heading', default: 'Price')} ${message(code: 'price.currency.symbol')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.production.time.heading', default: 'Production time')} ${message(code: 'time.day.symbol')}</th>
                      <th>${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <tr id="frontalTextureListNoEntry">
                      <td colspan="8">
                        ${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.none.linked.message', default: 'No textures currently linked')}
                      </td>
                    </tr>
                  </g:else>
                </table>

                <select id="popupFrontalNewTextureImage" name="popupFrontalNewTextureImage">
                  <option value="">${message(code: 'frame.database.frontal.decoration.config.popup.frontal.texture.image.please.choose.message', default: 'Please choose')}</option>
                  <g:each in="${availableFrontalTextureImages}" var="thisImage">
                    <option value="${thisImage.id}--${thisImage.reference}">${thisImage.reference}</option>
                  </g:each>
                </select>

                <a class="button" href='#' id="addFrontalTextureButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.frontal.config.popup.add.frontal.texture.button.label', default: 'Add texture')}"/> ${message(code: 'frame.database.frontal.config.popup.add.frontal.texture.button.text', default: 'Add')}</a>

                <input type="hidden" id="numOfFrontalBaseColourRows" value="${frameInstance.frontalBaseColours.size()}"/>
                <input type="hidden" id="numOfFrontalTextureRows" value="${frameInstance.frontalTexture.size()}"/>
              </div>

              <div id="leftTempleDecorationConfigPopup" class="popup">
                                <form id="leftTextureForm">

                <h2>${message(code: 'frame.database.temple.decoration.config.popup.colour.heading', default: 'Base colour')}</h2>
                <table id="leftTempleBaseColourList" class="popupTable">
                  <g:if test="${frameInstance.leftTempleBaseColours?.size() != 0}">
                    <tr id="leftTempleBaseColourListTableHeading">
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.colour.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <g:each in="${frameInstance.leftTempleBaseColours}" status="i" var="colourInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>${colourInstance.name}</td>
                        <td><input type="checkbox" class="popupLeftTempleBaseColourDelVal" value="${colourInstance.id}"/></td>
                      </tr>
                    </g:each>
                  </g:if>
                  <g:else>
                    <tr class="hidden" id="leftTempleBaseColourListTableHeading">
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.colour.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <tr>
                      <td colspan="2" id="leftTempleBaseColourNoneLinkedEntry">
                        ${message(code: 'frame.database.left.temple.decoration.config.popup.linked.colour.none.linked.message', default: 'None linked')}
                      </td>
                    </tr>
                  </g:else>
                </table>
                
                <select id="popupLeftTempleNewBaseColour" name="popupLeftTempleNewBaseColour">
                  <option value="">${message(code: 'frame.database.left.temple.decoration.config.popup.base.colour.please.choose.message', default: 'Please choose')}</option>
                  <g:each in="${availableLeftTempleBaseColours}" var="thisColour">
                    <option value="${thisColour.id}--${thisColour.name}">${thisColour.name}</option>
                  </g:each>
                </select>
                
                <a class="button" href='#' id="addLeftTempleColourButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.left.temple.config.popup.add.base.colour.button.label', default: 'Add colour')}"/> ${message(code: 'frame.database.left.temple.config.popup.add.base.colour.button.text', default: 'Add')}</a>
                
                
                <h2>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.heading', default: 'Texture (inkjet printed to front)')}</h2>
                <table class="popupTable" id="leftTempleTextureListTable">
                  
                  <g:if test="${frameInstance.leftTempleTexture?.size() != 0}">
                    <tr id="leftTempleTextureListTableHeading">
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.image.heading', default: 'Image')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.reference.heading', default: 'Reference')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.x.heading', default: 'X')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.y.heading', default: 'Y')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.scale.heading', default: 'Scale factor')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.price.heading', default: 'Price')} ${message(code: 'price.currency.symbol')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.production.time.heading', default: 'Production time')} ${message(code: 'time.day.symbol')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <g:each in="${frameInstance.leftTempleTexture}" status="i" var="textureInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><a href="${textureInstance.image?.path}" target="_blank" title="${message(code: 'frame.database.left.temple.decoration.config.popup.texture.image.link.label', default: 'View image')}">${textureInstance.image?.reference}</a></td>
                        <td>${textureInstance.reference}</td>
                        <td>${textureInstance.x}</td>
                        <td>${textureInstance.y}</td>
                        <td>${formatNumber(number:textureInstance.scaleFactor, format:"0.0")}</td>
                        <td>${formatNumber(number:textureInstance.price, format:"0.00")}</td>
                        <td>${textureInstance.productionTime}</td>
                        <td><input type="checkbox" name="popupLeftTempleTextureDelete-${i}" id="popupLeftTempleTextureDelete-${i}" value="${textureInstance.id}"</td>
                      </tr>
                    </g:each>
                  </g:if>
                  <g:else>
                    <tr id="leftTempleTextureListTableHeading" class="hidden">
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.image.heading', default: 'Image')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.reference.heading', default: 'Reference')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.x.heading', default: 'X')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.y.heading', default: 'Y')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.scale.heading', default: 'Scale factor')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.price.heading', default: 'Price')} ${message(code: 'price.currency.symbol')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.production.time.heading', default: 'Production time')} ${message(code: 'time.day.symbol')}</th>
                      <th>${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <tr id="leftTempleTextureListNoEntry">
                      <td colspan="8">
                        ${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.none.linked.message', default: 'No textures currently linked')}
                      </td>
                    </tr>
                  </g:else>
                </table>

                <select id="popupLeftTempleNewTextureImage" name="popupLeftTempleNewTextureImage">
                  <option value="">${message(code: 'frame.database.left.temple.decoration.config.popup.frontal.texture.image.please.choose.message', default: 'Please choose')}</option>
                  <g:each in="${availableLeftTempleTextureImages}" var="thisImage">
                    <option value="${thisImage.id}--${thisImage.reference}">${thisImage.reference}</option>
                  </g:each>
                </select>

                <a class="button" href='#' id="addLeftTempleTextureButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.left.temple.config.popup.add.frontal.texture.button.label', default: 'Add texture')}"/> ${message(code: 'frame.database.left.temple.config.popup.add.frontal.texture.button.text', default: 'Add')}</a>

                <input type="hidden" id="numOfLeftTempleBaseColourRows" value="${frameInstance.leftTempleBaseColours.size()}"/>
                <input type="hidden" id="numOfLeftTempleTextureRows" value="${frameInstance.leftTempleTexture.size()}"/>
                
                                </form>

              </div>

              <div id="rightTempleDecorationConfigPopup" class="popup">
                <h2>${message(code: 'frame.database.temple.decoration.config.popup.colour.heading', default: 'Base colour')}</h2>
                <table id="rightTempleBaseColourList" class="popupTable">
                  <g:if test="${frameInstance.rightTempleBaseColours?.size() != 0}">
                    <tr id="rightTempleBaseColourListTableHeading">
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.colour.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <g:each in="${frameInstance.rightTempleBaseColours}" status="i" var="colourInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>${colourInstance.name}</td>
                        <td><input type="checkbox" class="popupRightTempleBaseColourDelVal" value="${colourInstance.id}"/></td>
                      </tr>
                    </g:each>
                  </g:if>
                  <g:else>
                    <tr class="hidden" id="rightTempleBaseColourListTableHeading">
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.colour.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <tr>
                      <td colspan="2" id="rightTempleBaseColourNoneLinkedEntry">
                        ${message(code: 'frame.database.right.temple.decoration.config.popup.linked.colour.none.linked.message', default: 'None linked')}
                      </td>
                    </tr>
                  </g:else>
                </table>
                
                <select id="popupRightTempleNewBaseColour" name="popupRightTempleNewBaseColour">
                  <option value="">${message(code: 'frame.database.right.temple.decoration.config.popup.base.colour.please.choose.message', default: 'Please choose')}</option>
                  <g:each in="${availableRightTempleBaseColours}" var="thisColour">
                    <option value="${thisColour.id}--${thisColour.name}">${thisColour.name}</option>
                  </g:each>
                </select>
                
                <a class="button" href='#' id="addRightTempleColourButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.right.temple.config.popup.add.base.colour.button.label', default: 'Add colour')}"/> ${message(code: 'frame.database.right.temple.config.popup.add.base.colour.button.text', default: 'Add')}</a>
                
                
                <h2>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.heading', default: 'Texture (inkjet printed to front)')}</h2>
                <table class="popupTable" id="rightTempleTextureListTable">
                  
                  <g:if test="${frameInstance.rightTempleTexture?.size() != 0}">
                    <tr id="rightTempleTextureListTableHeading">
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.image.heading', default: 'Image')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.reference.heading', default: 'Reference')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.x.heading', default: 'X')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.y.heading', default: 'Y')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.scale.heading', default: 'Scale factor')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.price.heading', default: 'Price')} ${message(code: 'price.currency.symbol')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.production.time.heading', default: 'Production time')} ${message(code: 'time.day.symbol')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <g:each in="${frameInstance.rightTempleTexture}" status="i" var="textureInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><a href="${textureInstance.image?.path}" target="_blank" title="${message(code: 'frame.database.right.temple.decoration.config.popup.texture.image.link.label', default: 'View image')}">${textureInstance.image?.reference}</a></td>
                        <td>${textureInstance.reference}</td>
                        <td>${textureInstance.x}</td>
                        <td>${textureInstance.y}</td>
                        <td>${formatNumber(number:textureInstance.scaleFactor, format:"0.0")}</td>
                        <td>${formatNumber(number:textureInstance.price, format:"0.00")}</td>
                        <td>${textureInstance.productionTime}</td>
                        <td><input type="checkbox" name="popupRightTempleTextureDelete-${i}" id="popupRightTempleTextureDelete-${i}" value="${textureInstance.id}"</td>
                      </tr>
                    </g:each>
                  </g:if>
                  <g:else>
                    <tr id="rightTempleTextureListTableHeading" class="hidden">
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.image.heading', default: 'Image')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.reference.heading', default: 'Reference')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.x.heading', default: 'X')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.y.heading', default: 'Y')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.scale.heading', default: 'Scale factor')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.price.heading', default: 'Price')} ${message(code: 'price.currency.symbol')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.production.time.heading', default: 'Production time')} ${message(code: 'time.day.symbol')}</th>
                      <th>${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.delete.heading', default: 'Delete?')}</th>
                    </tr>
                    <tr id="rightTempleTextureListNoEntry">
                      <td colspan="8">
                        ${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.none.linked.message', default: 'No textures currently linked')}
                      </td>
                    </tr>
                  </g:else>
                </table>

                <select id="popupRightTempleNewTextureImage" name="popupRightTempleNewTextureImage">
                  <option value="">${message(code: 'frame.database.right.temple.decoration.config.popup.frontal.texture.image.please.choose.message', default: 'Please choose')}</option>
                  <g:each in="${availableRightTempleTextureImages}" var="thisImage">
                    <option value="${thisImage.id}--${thisImage.reference}">${thisImage.reference}</option>
                  </g:each>
                </select>

                <a class="button" href='#' id="addRightTempleTextureButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.right.temple.config.popup.add.texture.button.label', default: 'Add texture')}"/> ${message(code: 'frame.database.right.temple.config.popup.add.texture.button.text', default: 'Add')}</a>

                <input type="hidden" id="numOfRightTempleBaseColourRows" value="${frameInstance.rightTempleBaseColours.size()}"/>
                <input type="hidden" id="numOfRightTempleTextureRows" value="${frameInstance.rightTempleTexture.size()}"/>

              </div>

              <div id="leftTempleConfigPopup" class="popup">
                <table class="popupTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.finishing.aspect', default: 'Finishing aspect')}:</td>
                    <td>
                      <g:select id="popupLeftTempleFinishingAspect" name="popupLeftTempleFinishingAspect" value="${frameInstance.leftTempleFinishingAspect}" class="required" title="${message(code: 'frame.left.temple.finishing.aspect.required.message', default: 'A finishing aspect is required')}" noSelection="${['': message(code: 'frame.left.temple.finishing.aspect.please.choose.message', default: 'Please choose')]}" from="${finishingAspects}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'frame.database.left.temple.config.popup.matte.heading', default: 'Matte finish:')}</td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.matte.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="popupLeftTempleMattePrice" name="popupLeftTempleMattePrice" value="${formatNumber(number:frameInstance.leftTempleMattePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.matte.time', default: 'Production time')}:</td>
                    <td>
                      <input type="text" id="popupLeftTempleMatteProductionTime" name="popupLeftTempleMatteProductionTime" value="${frameInstance.leftTempleMatteProductionTime}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'frame.database.left.temple.config.popup.brilliant.heading', default: 'Brilliant finish:')}</td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.brilliant.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="popupLeftTempleBrilliantPrice" name="popupLeftTempleBrilliantPrice" value="${formatNumber(number:frameInstance.leftTempleBrilliantPrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.brilliant.time', default: 'Production time')}:</td>
                    <td>
                      <input type="text" id="popupLeftTempleBrilliantProductionTime" name="popupLeftTempleBrilliantProductionTime" value="${frameInstance.leftTempleBrilliantProductionTime}"/>
                    </td>
                  </tr>
                </table>
              </div>

              <div id="leftTempleDimsPopup" class="popup">
                <table class="popupTable">
                  <tr>
                    <td/>
                    <th>${message(code: 'frame.database.left.temple.dimensions.config.popup.standard.heading', default: 'Standard')}</th>
                    <th>${message(code: 'frame.database.left.temple.dimensions.config.popup.min.heading', default: 'Minimum')}</th>
                    <th>${message(code: 'frame.database.left.temple.dimensions.config.popup.max.heading', default: 'Maximum')}</th>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      ${message(code: 'frame.database.left.temple.dimensions.config.popup.length', default: 'Length')} ${message(code: 'distance.mm.symbol')}: 
                      <img src="${createLinkTo(dir, '/')}images/help.png" alt="${message(code: 'frame.database.left.temple.dimensions.config.popup.length.explanation')}" title="${message(code: 'frame.database.left.temple.dimensions.config.popup.length.explanation')}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleLengthStandard" name="popupLeftTempleLengthStandard" value="${formatNumber(number:frameInstance.leftTempleDims.lengthStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleLengthMin" name="popupLeftTempleLengthMin" value="${formatNumber(number:frameInstance.leftTempleDims.lengthMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleLengthMax" name="popupLeftTempleLengthMax" value="${formatNumber(number:frameInstance.leftTempleDims.lengthMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.left.temple.dimensions.config.popup.opening.angle', default: 'Opening angle')} ${message(code: 'angle.degree.symbol')}:</td>
                    <td>
                      <input type="text" id="popupLeftTempleOpeningAngleStandard" name="popupLeftTempleOpeningAngleStandard" value="${formatNumber(number:frameInstance.leftTempleDims.openingAngleStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleOpeningAngleMin" name="popupLeftTempleOpeningAngleMin" value="${formatNumber(number:frameInstance.leftTempleDims.openingAngleMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleOpeningAngleMax" name="popupLeftTempleOpeningAngleMax" value="${formatNumber(number:frameInstance.leftTempleDims.openingAngleMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      ${message(code: 'frame.database.left.temple.dimensions.config.popup.main.curvature', default: 'Main curvature (top view)')} ${message(code: 'distance.mm.symbol')}:
                      <img src="${createLinkTo(dir, '/')}images/help.png" alt="${message(code: 'frame.database.left.temple.dimensions.config.popup.main.curvature.explanation')}" title="${message(code: 'frame.database.left.temple.dimensions.config.popup.main.curvature.explanation')}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleMainCurvatureStandard" name="popupLeftTempleMainCurvatureStandard" value="${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleMainCurvatureMin" name="popupLeftTempleMainCurvatureMin" value="${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleMainCurvatureMax" name="popupLeftTempleMainCurvatureMax" value="${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      ${message(code: 'frame.database.left.temple.dimensions.config.popup.terminal.angle', default: 'Terminal angle')} ${message(code: 'angle.degree.symbol')}:
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleTerminalAngleStandard" name="popupLeftTempleTerminalAngleStandard" value="${formatNumber(number:frameInstance.leftTempleDims.terminalAngleStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleTerminalAngleMin" name="popupLeftTempleTerminalAngleMin" value="${formatNumber(number:frameInstance.leftTempleDims.terminalAngleMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupLeftTempleTerminalAngleMax" name="popupLeftTempleTerminalAngleMax" value="${formatNumber(number:frameInstance.leftTempleDims.terminalAngleMax, format:"0.0")}"/>
                    </td>
                  </tr>
                </table>
              </div>

              <div id="rightTempleConfigPopup" class="popup">
                <table class="popupTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.finishing.aspect', default: 'Finishing aspect')}:</td>
                    <td>
                      <g:select id="popupRightTempleFinishingAspect" name="popupRightTempleFinishingAspect" value="${frameInstance.rightTempleFinishingAspect}" class="required" title="${message(code: 'frame.right.temple.finishing.aspect.required.message', default: 'A finishing aspect is required')}" noSelection="${['': message(code: 'frame.right.temple.finishing.aspect.please.choose.message', default: 'Please choose')]}" from="${finishingAspects}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'frame.database.right.temple.config.popup.matte.heading', default: 'Matte finish:')}</td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.matte.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="popupRightTempleMattePrice" name="popupRightTempleMattePrice" value="${formatNumber(number:frameInstance.rightTempleMattePrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.matte.time', default: 'Production time')}:</td>
                    <td>
                      <input type="text" id="popupRightTempleMatteProductionTime" name="popupRightTempleMatteProductionTime" value="${frameInstance.rightTempleMatteProductionTime}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading"><h3>${message(code: 'frame.database.right.temple.config.popup.brilliant.heading', default: 'Brilliant finish:')}</td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.brilliant.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
                    <td>
                      <input type="text" id="popupRightTempleBrilliantPrice" name="popupRightTempleBrilliantPrice" value="${formatNumber(number:frameInstance.rightTempleBrilliantPrice, format:"0.00")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.brilliant.time', default: 'Production time')}:</td>
                    <td>
                      <input type="text" id="popupRightTempleBrilliantProductionTime" name="popupRightTempleBrilliantProductionTime" value="${frameInstance.rightTempleBrilliantProductionTime}"/>
                    </td>
                  </tr>
                </table>
              </div>

              <div id="rightTempleDimsPopup" class="popup">
                <table class="popupTable">
                  <tr>
                    <td/>
                    <th>${message(code: 'frame.database.right.temple.dimensions.config.popup.standard.heading', default: 'Standard')}</th>
                    <th>${message(code: 'frame.database.right.temple.dimensions.config.popup.min.heading', default: 'Minimum')}</th>
                    <th>${message(code: 'frame.database.right.temple.dimensions.config.popup.max.heading', default: 'Maximum')}</th>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      ${message(code: 'frame.database.right.temple.dimensions.config.popup.length', default: 'Length')} ${message(code: 'distance.mm.symbol')}: 
                      <img src="${createLinkTo(dir, '/')}images/help.png" alt="${message(code: 'frame.database.right.temple.dimensions.config.popup.length.explanation')}" title="${message(code: 'frame.database.right.temple.dimensions.config.popup.length.explanation')}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleLengthStandard" name="popupRightTempleLengthStandard" value="${formatNumber(number:frameInstance.rightTempleDims.lengthStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleLengthMin" name="popupRightTempleLengthMin" value="${formatNumber(number:frameInstance.rightTempleDims.lengthMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleLengthMax" name="popupRightTempleLengthMax" value="${formatNumber(number:frameInstance.rightTempleDims.lengthMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.right.temple.dimensions.config.popup.opening.angle', default: 'Opening angle')} ${message(code: 'angle.degree.symbol')}:</td>
                    <td>
                      <input type="text" id="popupRightTempleOpeningAngleStandard" name="popupRightTempleOpeningAngleStandard" value="${formatNumber(number:frameInstance.rightTempleDims.openingAngleStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleOpeningAngleMin" name="popupRightTempleOpeningAngleMin" value="${formatNumber(number:frameInstance.rightTempleDims.openingAngleMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleOpeningAngleMax" name="popupRightTempleOpeningAngleMax" value="${formatNumber(number:frameInstance.rightTempleDims.openingAngleMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      ${message(code: 'frame.database.right.temple.dimensions.config.popup.main.curvature', default: 'Main curvature (top view)')} ${message(code: 'distance.mm.symbol')}:
                      <img src="${createLinkTo(dir, '/')}images/help.png" alt="${message(code: 'frame.database.right.temple.dimensions.config.popup.main.curvature.explanation')}" title="${message(code: 'frame.database.right.temple.dimensions.config.popup.main.curvature.explanation')}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleMainCurvatureStandard" name="popupRightTempleMainCurvatureStandard" value="${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleMainCurvatureMin" name="popupRightTempleMainCurvatureMin" value="${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleMainCurvatureMax" name="popupRightTempleMainCurvatureMax" value="${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureMax, format:"0.0")}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      ${message(code: 'frame.database.right.temple.dimensions.config.popup.terminal.angle', default: 'Terminal angle')} ${message(code: 'angle.degree.symbol')}:
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleTerminalAngleStandard" name="popupRightTempleTerminalAngleStandard" value="${formatNumber(number:frameInstance.rightTempleDims.terminalAngleStandard, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleTerminalAngleMin" name="popupRightTempleTerminalAngleMin" value="${formatNumber(number:frameInstance.rightTempleDims.terminalAngleMin, format:"0.0")}"/>
                    </td>
                    <td>
                      <input type="text" id="popupRightTempleTerminalAngleMax" name="popupRightTempleTerminalAngleMax" value="${formatNumber(number:frameInstance.rightTempleDims.terminalAngleMax, format:"0.0")}"/>
                    </td>
                  </tr>
                </table>
              </div>

              <div id="standardsConfigPopup" class="popup">
                 <table class="popupTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.standards.config.popup.hinge', default: 'Hinge')}:</td>
                    <td>
                      <g:select id="popupStandardsHingeId" name="popupStandardsHingeId" value="${frameInstance.hinge?.id}" noSelection="${['': message(code: 'frame.standards.hinge.please.choose.message', default: 'Please choose')]}" from="${hinges}" optionKey="id" optionValue="reference"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.standards.config.popup.nylon', default: 'Nylon')}:</td>
                    <td>
                      <g:select id="popupStandardsNylonId" name="popupStandardsNylonId" value="${frameInstance.nylon?.id}" noSelection="${['': message(code: 'frame.standards.nylon.please.choose.message', default: 'Please choose')]}" from="${nylons}" optionKey="id" optionValue="reference"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.database.standards.config.popup.hinge', default: 'Hinge')}:</td>
                    <td>
                      <g:select id="popupStandardsScrewId" name="popupStandardsScrewId" value="${frameInstance.screw?.id}" noSelection="${['': message(code: 'frame.standards.screw.please.choose.message', default: 'Please choose')]}" from="${screws}" optionKey="id" optionValue="reference"/>
                    </td>
                  </tr>
                 </table>
              </div>

              <div id="precookedConfigPopup" class="popup">
                <table class="popupTable" id="precookedTable">
                  <thead>
                    <tr>
                      <th>${message(code: 'frame.database.precooked.config.popup.id.heading', default: 'Id')}</th>
                      <th>${message(code: 'frame.database.precooked.config.popup.reference.heading', default: 'Reference')}</th>
                      <th>${message(code: 'frame.database.precooked.config.popup.bridge.width.heading', default: 'Bridge width')} ${message(code: 'distance.mm.symbol')}</th>
                      <th>${message(code: 'frame.database.precooked.config.popup.heel.width.heading', default: 'Heel width')} ${message(code: 'distance.mm.symbol')}</th>
                      <th>${message(code: 'frame.database.precooked.config.popup.file.heading', default: 'File')}</th>
                      <th>${message(code: 'frame.database.precooked.config.popup.delete.heading', default: 'Delete')}</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr class="hidden">
                      <td>Hidden row to ensure that more rows can be added</td>
                    </tr>
                    <g:each in="${frameInstance.precookeds}" status="i" var="precookedInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>${precookedInstance.id}</td>
                        <td>${precookedInstance.reference}</td>
                        <td>${formatNumber(number:precookedInstance.bridgeWidth, format:"0.0")}</td>
                        <td>${formatNumber(number:precookedInstance.heelWidth, format:"0.0")}</td>
                        <td><a href="${precookedInstance.path}" title="${message(code: 'frame.database.precooked.config.path.title', default: 'View the precooked file')}" target="_new">${message(code: 'frame.database.precooked.config.path.link.text', default: 'View file')}</a></td>
                        <td><input type="checkbox" name="popupPrecookedDelete-${i}" id="popupPrecookedDelete-${i}" value="${precookedInstance.id}"></input></td>
                      </tr>
                    </g:each>
                  </tbody>
                </table>
                
                <input type="hidden" id="numOfPrecookedRows" value="${frameInstance.precookeds.size()}"/>
                
                <div class="buttonArea">
                <a class="button" href='#' id="addPrecookedButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.precooked.config.popup.add.button.label', default: 'Add precooked configuration')}"/> ${message(code: 'frame.database.precooked.config.popup.add.button.text', default: 'Add')}</a>
                </div>
              </div>


       </div>
    
    <script type="text/javascript">

        $(document).ready(function () {

        
          $('form[name="frameForm"]').validate( {
            rules: {
              'identifier': {
                required: true
              },
              'materialId': {
                required: true,
                
              },
              'rimType': {
                required: true
              },
              'gender': {
                required: true
              },
              'rimShapeId': {
                required: true
              },
              'endpieceHeight': {
                required: true
              },
              'use': {
                required: true
              },
              'fullyPersonalisedBasePrice': {
                range: [0.0,2000.0]
              },
              'semiPersonalisedBasePrice': {
                range: [0.0,2000.0]
              },
              'standardBasePrice': {
                range: [0.0,2000.0]
              },
              'fullyPersonalisedProductionTime': {
                range: [0,30]
              },
              'semiPersonalisedProductionTime': {
                range: [0,30]
              }

            }
          });
          
          // Frontal options popup
          var frontalOptions = $('#frontalOptionsPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.frontal.options.popup.title', default: 'Frontal configuration')}",
              closeOnEscape: true,
              modal: true
          });
            
          $('#frontalOptionsButton').click(function() {
            frontalOptions.dialog('open');
            return false;
          });
          
          // Frontal popup
          var frontalConfig = $('#frontalConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.frontal.config.popup.title', default: 'Frontal configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 450,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#finishingAspect').val($('#popupFinishingAspect').val());
                $('#mattePrice').val($('#popupMattePrice').val());
                $('#matteProductionTime').val($('#popupMatteProductionTime').val());
                $('#brilliantPrice').val($('#popupBrilliantPrice').val());
                $('#brilliantProductionTime').val($('#popupBrilliantProductionTime').val());
                $('#minLensThickness').val($('#popupMinLensThickness').val());
                $('#leftTopPupilFrameDistance').val($('#popupLeftTopPupilFrameDistance').val());
                $('#leftBottomPupilFrameDistancePSV').val($('#popupLeftBottomPupilFrameDistancePSV').val());
                $('#leftBottomPupilFrameDistanceMF').val($('#popupLeftBottomPupilFrameDistanceMF').val());
                $('#rightTopPupilFrameDistance').val($('#popupRightTopPupilFrameDistance').val());
                $('#rightBottomPupilFrameDistancePSV').val($('#popupRightBottomPupilFrameDistancePSV').val());
                $('#rightBottomPupilFrameDistanceMF').val($('#popupRightBottomPupilFrameDistanceMF').val());
              }
          });
            
          $('#frontalPopupButton').click(function() {
            frontalConfig.dialog('open');
            return false;
          });

          // Frontal dimensions popup
          var frontalDimsConfig = $('#frontalDimsConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.frontal.dimensions.config.popup.title', default: 'Frontal dimensions')}",
              closeOnEscape: true,
              modal: true,
              width: 600,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#boxingHorizontalStandard').val($('#popupBoxingHorizontalStandard').val());
                $('#boxingHorizontalMin').val($('#popupBoxingHorizontalMin').val());
                $('#boxingHorizontalMax').val($('#popupBoxingHorizontalMax').val());
                $('#boxingVerticalStandard').val($('#popupBoxingVerticalStandard').val());
                $('#boxingVerticalMin').val($('#popupBoxingVerticalMin').val());
                $('#boxingVerticalMax').val($('#popupBoxingVerticalMax').val());
                $('#pantoscopicAngleLeftStandard').val($('#popupPantoscopicAngleLeftStandard').val());
                $('#pantoscopicAngleLeftMin').val($('#popupPantoscopicAngleLeftMin').val());
                $('#pantoscopicAngleLeftMax').val($('#popupPantoscopicAngleLeftMax').val());
                $('#pantoscopicAngleRightStandard').val($('#popupPantoscopicAngleRightStandard').val());
                $('#pantoscopicAngleRightMin').val($('#popupPantoscopicAngleRightMin').val());
                $('#pantoscopicAngleRightMax').val($('#popupPantoscopicAngleRightMax').val());
                $('#bridgeWidthStandard').val($('#popupBridgeWidthStandard').val());
                $('#bridgeWidthMin').val($('#popupBridgeWidthMin').val());
                $('#bridgeWidthMax').val($('#popupBridgeWidthMax').val());
                $('#bridgeHeightStandard').val($('#popupBridgeHeightStandard').val());
                $('#bridgeHeightMin').val($('#popupBridgeHeightMin').val());
                $('#bridgeHeightMax').val($('#popupBridgeHeightMax').val());
                $('#heelWidthStandard').val($('#popupHeelWidthStandard').val());
                $('#heelWidthMin').val($('#popupHeelWidthMin').val());
                $('#heelWidthMax').val($('#popupHeelWidthMax').val());
                $('#internalRimReductionStandard').val($('#popupInternalRimReductionStandard').val());
                $('#internalRimReductionMin').val($('#popupInternalRimReductionMin').val());
                $('#internalRimReductionMax').val($('#popupInternalRimReductionMax').val());
                $('#baseOfFrameStandard').val($('#popupBaseOfFrameStandard').val());
                $('#baseOfFrameMin').val($('#popupBaseOfFrameMin').val());
                $('#baseOfFrameMax').val($('#popupBaseOfFrameMax').val());
                $('#wrapAngleStandard').val($('#popupWrapAngleStandard').val());
                $('#wrapAngleMin').val($('#popupWrapAngleMin').val());
                $('#wrapAngleMax').val($('#popupWrapAngleMax').val());
                $('#totalLengthStandard').val($('#popupTotalLengthStandard').val());
                
              }

          });
          
          $('#frontalDimsPopupButton').click(function() {
            frontalDimsConfig.dialog('open');
            return false;
          });
          
          // Frontal total dimensions popup
          var frontalTotalSizeConfig = $('#totalSizeConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.total.size.config.popup.title', default: 'Total frame size')}",
              closeOnEscape: true,
              modal: true,
              width: 600,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#totalSizeBridgeWidth1').val($('#popupTotalSizeBridgeWidth1').val());
                $('#totalSizeMin1').val($('#popupTotalSizeMin1').val());
                $('#totalSizeMax1').val($('#popupTotalSizeMax1').val());
                $('#totalSizeBridgeWidth2').val($('#popupTotalSizeBridgeWidth2').val());
                $('#totalSizeMin2').val($('#popupTotalSizeMin2').val());
                $('#totalSizeMax2').val($('#popupTotalSizeMax2').val());
                $('#totalSizeBridgeWidth3').val($('#popupTotalSizeBridgeWidth3').val());
                $('#totalSizeMin3').val($('#popupTotalSizeMin3').val());
                $('#totalSizeMax3').val($('#popupTotalSizeMax3').val());
                $('#totalSizeBridgeWidth4').val($('#popupTotalSizeBridgeWidth4').val());
                $('#totalSizeMin4').val($('#popupTotalSizeMin4').val());
                $('#totalSizeMax4').val($('#popupTotalSizeMax4').val());
                $('#totalSizeBridgeWidth5').val($('#popupTotalSizeBridgeWidth5').val());
                $('#totalSizeMin5').val($('#popupTotalSizeMin5').val());
                $('#totalSizeMax5').val($('#popupTotalSizeMax5').val());
                $('#totalSizeBridgeWidth6').val($('#popupTotalSizeBridgeWidth6').val());
                $('#totalSizeMin6').val($('#popupTotalSizeMin6').val());
                $('#totalSizeMax6').val($('#popupTotalSizeMax6').val());
                $('#totalSizeBridgeWidth7').val($('#popupTotalSizeBridgeWidth7').val());
                $('#totalSizeMin7').val($('#popupTotalSizeMin7').val());
                $('#totalSizeMax7').val($('#popupTotalSizeMax7').val());
                $('#totalSizeBridgeWidth8').val($('#popupTotalSizeBridgeWidth8').val());
                $('#totalSizeMin8').val($('#popupTotalSizeMin8').val());
                $('#totalSizeMax8').val($('#popupTotalSizeMax8').val());
                $('#totalSizeBridgeWidth9').val($('#popupTotalSizeBridgeWidth9').val());
                $('#totalSizeMin9').val($('#popupTotalSizeMin9').val());
                $('#totalSizeMax9').val($('#popupTotalSizeMax9').val());
                $('#totalSizeBridgeWidth10').val($('#popupTotalSizeBridgeWidth10').val());
                $('#totalSizeMin10').val($('#popupTotalSizeMin10').val());
                $('#totalSizeMax10').val($('#popupTotalSizeMax10').val());
                $('#totalSizeBridgeWidth11').val($('#popupTotalSizeBridgeWidth11').val());
                $('#totalSizeMin11').val($('#popupTotalSizeMin11').val());
                $('#totalSizeMax11').val($('#popupTotalSizeMax11').val());
                $('#totalSizeBridgeWidth12').val($('#popupTotalSizeBridgeWidth12').val());
                $('#totalSizeMin12').val($('#popupTotalSizeMin12').val());
                $('#totalSizeMax12').val($('#popupTotalSizeMax12').val());
                $('#totalSizeBridgeWidth13').val($('#popupTotalSizeBridgeWidth13').val());
                $('#totalSizeMin13').val($('#popupTotalSizeMin13').val());
                $('#totalSizeMax13').val($('#popupTotalSizeMax13').val());
                $('#totalSizeBridgeWidth14').val($('#popupTotalSizeBridgeWidth14').val());
                $('#totalSizeMin14').val($('#popupTotalSizeMin14').val());
                $('#totalSizeMax14').val($('#popupTotalSizeMax14').val());
                $('#totalSizeBridgeWidth15').val($('#popupTotalSizeBridgeWidth15').val());
                $('#totalSizeMin15').val($('#popupTotalSizeMin15').val());
                $('#totalSizeMax15').val($('#popupTotalSizeMax15').val());
              }

          });
          
          $('#totalSizePopupButton').click(function() {
            frontalTotalSizeConfig.dialog('open');
            return false;
          });

          // Frontal decoration popup
          var frontalDecorationConfig = $('#frontalDecorationConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.frontal.decoration.config.popup.title', default: 'Frontal decoration')}",
              closeOnEscape: true,
              modal: true,
              height: 500,
              width: 1000,
              beforeClose: function(event, ui) {
                // Copy various fields from the popup into hidden fields in the form
                
                // Combine each of the new base colour fields separated by _BREAK_
                var newBaseColourIds = "";
                $("input[class='popupFrontalBaseColourNewVal']").each(function() {
                  if ( newBaseColourIds != "" ) {
                    newBaseColourIds = newBaseColourIds + "_BREAK_";
                  }
                  newBaseColourIds = newBaseColourIds + $(this).val();
                });

                $('#frontalNewBaseColourIds').val(newBaseColourIds);
                
                // Combine each of the base colour fields that are being deleted, separated by _BREAK_
                var removeBaseColourIds = "";
                $("input[class='popupFrontalBaseColourDelVal']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( removeBaseColourIds != "" ) {
                      removeBaseColourIds = removeBaseColourIds + "_BREAK_";
                    }
                    removeBaseColourIds = removeBaseColourIds + $(this).val();
                  }
                });

                $('#frontalRemoveBaseColourIds').val(removeBaseColourIds);
                
                // Combine each of the new texture fields as required
                
                // image file ids
                var newTextureImageIds = "";
                $("input[id*='frontalTextureImageId-']").each(function() {
                  if ( newTextureImageIds != "" ) {
                    newTextureImageIds = newTextureImageIds + "_BREAK_";
                  }
                  newTextureImageIds = newTextureImageIds + $(this).val();
                });
                $('#frontalNewTextureImageIds').val(newTextureImageIds);

                // references
                var newTextureReferences = "";
                $("input[id*='frontalTextureReference-']").each(function() {
                  if ( newTextureReferences != "" ) {
                    newTextureReferences = newTextureReferences + "_BREAK_";
                  }
                  newTextureReferences = newTextureReferences + $(this).val();
                });
                $('#frontalNewTextureReferences').val(newTextureReferences);

                // x coord
                var newTextureXVals = "";
                $("input[id*='frontalTextureX-']").each(function() {
                  if ( newTextureXVals != "" ) {
                    newTextureXVals = newTextureXVals + "_BREAK_";
                  }
                  newTextureXVals = newTextureXVals + $(this).val();
                });
                $('#frontalNewTextureXVals').val(newTextureXVals);
                
                // y coord
                var newTextureYVals = "";
                $("input[id*='frontalTextureY-']").each(function() {
                  if ( newTextureYVals != "" ) {
                    newTextureYVals = newTextureYVals + "_BREAK_";
                  }
                  newTextureYVals = newTextureYVals + $(this).val();
                });
                $('#frontalNewTextureYVals').val(newTextureYVals);

                // scale factor
                var newTextureScaleFactors = "";
                $("input[id*='frontalTextureScaleFactor-']").each(function() {
                  if ( newTextureScaleFactors != "" ) {
                    newTextureScaleFactors = newTextureScaleFactors + "_BREAK_";
                  }
                  newTextureScaleFactors = newTextureScaleFactors + $(this).val();
                });
                $('#frontalNewTextureScaleFactors').val(newTextureScaleFactors);
                
                // price
                var newTexturePrices = "";
                $("input[id*='frontalTexturePrice-']").each(function() {
                  if ( newTexturePrices != "" ) {
                    newTexturePrices = newTexturePrices + "_BREAK_";
                  }
                  newTexturePrices = newTexturePrices + $(this).val();
                });
                $('#frontalNewTexturePrices').val(newTexturePrices);

                // production times
                var newTextureProdTimes = "";
                $("input[id*='frontalTextureProductionTime-']").each(function() {
                  if ( newTextureProdTimes != "" ) {
                    newTextureProdTimes = newTextureProdTimes + "_BREAK_";
                  }
                  newTextureProdTimes = newTextureProdTimes + $(this).val();
                });
                $('#frontalNewTextureProductionTimes').val(newTextureProdTimes);

                // Combine each of the texture deletion fields separated by _BREAK_
                var deleteVals = "";
                $("input[name*='popupFrontalTextureDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#frontalTextureDeleteVals').val(deleteVals);
                
              }
          });
          
          $('#frontalDecorationPopupButton').click(function() {
            frontalDecorationConfig.dialog('open');
            return false;
          });


          // Left temple options popup
          var leftTempleOptions = $('#leftTempleOptionsPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.left.temple.options.popup.title', default: 'Left temple configuration')}",
              closeOnEscape: true,
              modal: true
          });
            
          $('#leftTempleOptionsButton').click(function() {
            leftTempleOptions.dialog('open');
            return false;
          });

          // Left temple popup
          var leftTempleConfig = $('#leftTempleConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.left.temple.config.popup.title', default: 'Left temple configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 450,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#leftTempleFinishingAspect').val($('#popupLeftTempleFinishingAspect').val());
                $('#leftTempleMattePrice').val($('#popupLeftTempleMattePrice').val());
                $('#leftTempleMatteProductionTime').val($('#popupLeftTempleMatteProductionTime').val());
                $('#leftTempleBrilliantPrice').val($('#popupLeftTempleBrilliantPrice').val());
                $('#leftTempleBrilliantProductionTime').val($('#popupLeftTempleBrilliantProductionTime').val());
              }
          });
          
          $('#leftTemplePopupButton').click(function() {
            leftTempleConfig.dialog('open');
            return false;
          });
          
          // Left temple dimensions popup
          var leftTempleDimsConfig = $('#leftTempleDimsPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.left.temple.dimensions.popup.title', default: 'Left temple dimensions')}",
              closeOnEscape: true,
              modal: true,
              width: 650,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#leftTempleLengthStandard').val($('#popupLeftTempleLengthStandard').val());
                $('#leftTempleLengthMin').val($('#popupLeftTempleLengthMin').val());
                $('#leftTempleLengthMax').val($('#popupLeftTempleLengthMax').val());
                $('#leftTempleOpeningAngleStandard').val($('#popupLeftTempleOpeningAngleStandard').val());
                $('#leftTempleOpeningAngleMin').val($('#popupLeftTempleOpeningAngleMin').val());
                $('#leftTempleOpeningAngleMax').val($('#popupLeftTempleOpeningAngleMax').val());
                $('#leftTempleMainCurvatureStandard').val($('#popupLeftTempleMainCurvatureStandard').val());
                $('#leftTempleMainCurvatureMin').val($('#popupLeftTempleMainCurvatureMin').val());
                $('#leftTempleMainCurvatureMax').val($('#popupLeftTempleMainCurvatureMax').val());
                $('#leftTempleTerminalAngleStandard').val($('#popupLeftTempleTerminalAngleStandard').val());
                $('#leftTempleTerminalAngleMin').val($('#popupLeftTempleTerminalAngleMin').val());
                $('#leftTempleTerminalAngleMax').val($('#popupLeftTempleTerminalAngleMax').val());
              }
          });
          
          $('#leftTempleDimsPopupButton').click(function() {
            leftTempleDimsConfig.dialog('open');
            return false;
          });
          
          // Left temple decoration popup
          var leftTempleDecorationConfig = $('#leftTempleDecorationConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.left.temple.decoration.config.popup.title', default: 'Left temple decoration')}",
              closeOnEscape: true,
              modal: true,
              height: 500,
              width: 1000,
              beforeClose: function(event, ui) {
                
                // Copy various fields from the popup into hidden fields in the form
                
                // Combine each of the new base colour fields separated by _BREAK_
                var newBaseColourIds = "";
                $("input[class='popupLeftTempleBaseColourNewVal']").each(function() {
                  if ( newBaseColourIds != "" ) {
                    newBaseColourIds = newBaseColourIds + "_BREAK_";
                  }
                  newBaseColourIds = newBaseColourIds + $(this).val();
                });

                $('#leftTempleNewBaseColourIds').val(newBaseColourIds);
                
                // Combine each of the base colour fields that are being deleted, separated by _BREAK_
                var removeBaseColourIds = "";
                $("input[class='popupLeftTempleBaseColourDelVal']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( removeBaseColourIds != "" ) {
                      removeBaseColourIds = removeBaseColourIds + "_BREAK_";
                    }
                    removeBaseColourIds = removeBaseColourIds + $(this).val();
                  }
                });

                $('#leftTempleRemoveBaseColourIds').val(removeBaseColourIds);
                
                // Combine each of the new texture fields as required
                
                // image file ids
                var newTextureImageIds = "";
                $("input[id*='leftTempleTextureImageId-']").each(function() {
                  if ( newTextureImageIds != "" ) {
                    newTextureImageIds = newTextureImageIds + "_BREAK_";
                  }
                  newTextureImageIds = newTextureImageIds + $(this).val();
                });
                $('#leftTempleNewTextureImageIds').val(newTextureImageIds);

                // references
                var newTextureReferences = "";
                $("input[id*='leftTempleTextureReference-']").each(function() {
                  if ( newTextureReferences != "" ) {
                    newTextureReferences = newTextureReferences + "_BREAK_";
                  }
                  newTextureReferences = newTextureReferences + $(this).val();
                });
                $('#leftTempleNewTextureReferences').val(newTextureReferences);

                // x coord
                var newTextureXVals = "";
                $("input[id*='leftTempleTextureX-']").each(function() {
                  if ( newTextureXVals != "" ) {
                    newTextureXVals = newTextureXVals + "_BREAK_";
                  }
                  newTextureXVals = newTextureXVals + $(this).val();
                });
                $('#leftTempleNewTextureXVals').val(newTextureXVals);
                
                // y coord
                var newTextureYVals = "";
                $("input[id*='leftTempleTextureY-']").each(function() {
                  if ( newTextureYVals != "" ) {
                    newTextureYVals = newTextureYVals + "_BREAK_";
                  }
                  newTextureYVals = newTextureYVals + $(this).val();
                });
                $('#leftTempleNewTextureYVals').val(newTextureYVals);

                // scale factor
                var newTextureScaleFactors = "";
                $("input[id*='leftTempleTextureScaleFactor-']").each(function() {
                  if ( newTextureScaleFactors != "" ) {
                    newTextureScaleFactors = newTextureScaleFactors + "_BREAK_";
                  }
                  newTextureScaleFactors = newTextureScaleFactors + $(this).val();
                });
                $('#leftTempleNewTextureScaleFactors').val(newTextureScaleFactors);
                
                // price
                var newTexturePrices = "";
                $("input[id*='leftTempleTexturePrice-']").each(function() {
                  if ( newTexturePrices != "" ) {
                    newTexturePrices = newTexturePrices + "_BREAK_";
                  }
                  newTexturePrices = newTexturePrices + $(this).val();
                });
                $('#leftTempleNewTexturePrices').val(newTexturePrices);

                // production times
                var newTextureProdTimes = "";
                $("input[id*='leftTempleTextureProductionTime-']").each(function() {
                  if ( newTextureProdTimes != "" ) {
                    newTextureProdTimes = newTextureProdTimes + "_BREAK_";
                  }
                  newTextureProdTimes = newTextureProdTimes + $(this).val();
                });
                $('#leftTempleNewTextureProductionTimes').val(newTextureProdTimes);

                // Combine each of the texture deletion fields separated by _BREAK_
                var deleteVals = "";
                $("input[name*='popupLeftTempleTextureDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#leftTempleTextureDeleteVals').val(deleteVals);
                
              }
          });
          
          $('#leftTempleDecorationPopupButton').click(function() {
            leftTempleDecorationConfig.dialog('open');
            return false;
          });

          // Right temple options popup
          var rightTempleOptions = $('#rightTempleOptionsPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.right.temple.options.popup.title', default: 'Right temple configuration')}",
              closeOnEscape: true,
              modal: true
          });
            
          $('#rightTempleOptionsButton').click(function() {
            rightTempleOptions.dialog('open');
            return false;
          });

          // Right temple popup
          var rightTempleConfig = $('#rightTempleConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.right.temple.config.popup.title', default: 'Right temple configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 450,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#rightTempleFinishingAspect').val($('#popupRightTempleFinishingAspect').val());
                $('#rightTempleMattePrice').val($('#popupRightTempleMattePrice').val());
                $('#rightTempleMatteProductionTime').val($('#popupRightTempleMatteProductionTime').val());
                $('#rightTempleBrilliantPrice').val($('#popupRightTempleBrilliantPrice').val());
                $('#rightTempleBrilliantProductionTime').val($('#popupRightTempleBrilliantProductionTime').val());
              }
          });
          
          $('#rightTemplePopupButton').click(function() {
            rightTempleConfig.dialog('open');
            return false;
          });
          
          // Right temple dimensions popup
          var rightTempleDimsConfig = $('#rightTempleDimsPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.right.temple.dimensions.popup.title', default: 'Right temple dimensions')}",
              closeOnEscape: true,
              modal: true,
              width: 650,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#rightTempleLengthStandard').val($('#popupRightTempleLengthStandard').val());
                $('#rightTempleLengthMin').val($('#popupRightTempleLengthMin').val());
                $('#rightTempleLengthMax').val($('#popupRightTempleLengthMax').val());
                $('#rightTempleOpeningAngleStandard').val($('#popupRightTempleOpeningAngleStandard').val());
                $('#rightTempleOpeningAngleMin').val($('#popupRightTempleOpeningAngleMin').val());
                $('#rightTempleOpeningAngleMax').val($('#popupRightTempleOpeningAngleMax').val());
                $('#rightTempleMainCurvatureStandard').val($('#popupRightTempleMainCurvatureStandard').val());
                $('#rightTempleMainCurvatureMin').val($('#popupRightTempleMainCurvatureMin').val());
                $('#rightTempleMainCurvatureMax').val($('#popupRightTempleMainCurvatureMax').val());
                $('#rightTempleTerminalAngleStandard').val($('#popupRightTempleTerminalAngleStandard').val());
                $('#rightTempleTerminalAngleMin').val($('#popupRightTempleTerminalAngleMin').val());
                $('#rightTempleTerminalAngleMax').val($('#popupRightTempleTerminalAngleMax').val());
              }
          });
          
          $('#rightTempleDimsPopupButton').click(function() {
            rightTempleDimsConfig.dialog('open');
            return false;
          });
          
          // Right temple decoration popup
          var rightTempleDecorationConfig = $('#rightTempleDecorationConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.right.temple.decoration.config.popup.title', default: 'Right temple decoration')}",
              closeOnEscape: true,
              modal: true,
              height: 500,
              width: 1000,
              beforeClose: function(event, ui) {
                // Copy various fields from the popup into hidden fields in the form
                
                // Combine each of the new base colour fields separated by _BREAK_
                var newBaseColourIds = "";
                $("input[class='popupRightTempleBaseColourNewVal']").each(function() {
                  if ( newBaseColourIds != "" ) {
                    newBaseColourIds = newBaseColourIds + "_BREAK_";
                  }
                  newBaseColourIds = newBaseColourIds + $(this).val();
                });

                $('#rightTempleNewBaseColourIds').val(newBaseColourIds);
                
                // Combine each of the base colour fields that are being deleted, separated by _BREAK_
                var removeBaseColourIds = "";
                $("input[class='popupRightTempleBaseColourDelVal']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( removeBaseColourIds != "" ) {
                      removeBaseColourIds = removeBaseColourIds + "_BREAK_";
                    }
                    removeBaseColourIds = removeBaseColourIds + $(this).val();
                  }
                });

                $('#rightTempleRemoveBaseColourIds').val(removeBaseColourIds);
                
                // Combine each of the new texture fields as required
                
                // image file ids
                var newTextureImageIds = "";
                $("input[id*='rightTempleTextureImageId-']").each(function() {
                  if ( newTextureImageIds != "" ) {
                    newTextureImageIds = newTextureImageIds + "_BREAK_";
                  }
                  newTextureImageIds = newTextureImageIds + $(this).val();
                });
                $('#rightTempleNewTextureImageIds').val(newTextureImageIds);

                // references
                var newTextureReferences = "";
                $("input[id*='rightTempleTextureReference-']").each(function() {
                  if ( newTextureReferences != "" ) {
                    newTextureReferences = newTextureReferences + "_BREAK_";
                  }
                  newTextureReferences = newTextureReferences + $(this).val();
                });
                $('#rightTempleNewTextureReferences').val(newTextureReferences);

                // x coord
                var newTextureXVals = "";
                $("input[id*='rightTempleTextureX-']").each(function() {
                  if ( newTextureXVals != "" ) {
                    newTextureXVals = newTextureXVals + "_BREAK_";
                  }
                  newTextureXVals = newTextureXVals + $(this).val();
                });
                $('#rightTempleNewTextureXVals').val(newTextureXVals);
                
                // y coord
                var newTextureYVals = "";
                $("input[id*='rightTempleTextureY-']").each(function() {
                  if ( newTextureYVals != "" ) {
                    newTextureYVals = newTextureYVals + "_BREAK_";
                  }
                  newTextureYVals = newTextureYVals + $(this).val();
                });
                $('#rightTempleNewTextureYVals').val(newTextureYVals);

                // scale factor
                var newTextureScaleFactors = "";
                $("input[id*='rightTempleTextureScaleFactor-']").each(function() {
                  if ( newTextureScaleFactors != "" ) {
                    newTextureScaleFactors = newTextureScaleFactors + "_BREAK_";
                  }
                  newTextureScaleFactors = newTextureScaleFactors + $(this).val();
                });
                $('#rightTempleNewTextureScaleFactors').val(newTextureScaleFactors);
                
                // price
                var newTexturePrices = "";
                $("input[id*='rightTempleTexturePrice-']").each(function() {
                  if ( newTexturePrices != "" ) {
                    newTexturePrices = newTexturePrices + "_BREAK_";
                  }
                  newTexturePrices = newTexturePrices + $(this).val();
                });
                $('#rightTempleNewTexturePrices').val(newTexturePrices);

                // production times
                var newTextureProdTimes = "";
                $("input[id*='rightTempleTextureProductionTime-']").each(function() {
                  if ( newTextureProdTimes != "" ) {
                    newTextureProdTimes = newTextureProdTimes + "_BREAK_";
                  }
                  newTextureProdTimes = newTextureProdTimes + $(this).val();
                });
                $('#rightTempleNewTextureProductionTimes').val(newTextureProdTimes);

                // Combine each of the texture deletion fields separated by _BREAK_
                var deleteVals = "";
                $("input[name*='popupRightTempleTextureDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#rightTempleTextureDeleteVals').val(deleteVals);
                
              }
          });
          
          $('#rightTempleDecorationPopupButton').click(function() {
            rightTempleDecorationConfig.dialog('open');
            return false;
          });

          // Standards popup
          var standardsConfig = $('#standardsConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.standard.config.popup.title', default: 'Standards configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 400,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                $('#hingeId').val($('#popupStandardsHingeId').val());
                $('#nylonId').val($('#popupStandardsNylonId').val());
                $('#screwId').val($('#popupStandardsScrewId').val());
              }
          });
          
          $('#standardsPopupButton').click(function() {
            standardsConfig.dialog('open');
            return false;
          });

          // Precookeds popup
          var precookedConfig = $('#precookedConfigPopup').dialog({
              autoOpen: false,
              title: "${message(code: 'frame.database.precooked.config.popup.title', default: 'Precooked configuration')}",
              closeOnEscape: true,
              modal: true,
              width: 800,
              beforeClose: function(event, ui) { 
                // Copy various fields from the popup into hidden fields in the form
                // Combine each of the reference fields separated by _BREAK_
                var refVals = "";
                $("input[name*='popupPrecookedReference']").each(function() {
                  if ( refVals != "" ) {
                    refVals = refVals + "_BREAK_";
                  }
                  refVals = refVals + $(this).val();
                });

                $('#precookedReferenceVals').val(refVals);
                
                // Combine each of the bridge width fields separated by _BREAK_
                var bridgeWidthVals = "";
                $("input[name*='popupPrecookedBridgeWidth']").each(function() {
                  if ( bridgeWidthVals != "" ) {
                    bridgeWidthVals = bridgeWidthVals + "_BREAK_";
                  }
                  bridgeWidthVals = bridgeWidthVals + $(this).val();
                });

                $('#precookedBridgeWidthVals').val(bridgeWidthVals);

                // Combine each of the heel width fields separated by _BREAK_
                var heelWidthVals = "";
                $("input[name*='popupPrecookedHeelWidth']").each(function() {
                  if ( heelWidthVals != "" ) {
                    heelWidthVals = heelWidthVals + "_BREAK_";
                  }
                  heelWidthVals = heelWidthVals + $(this).val();
                });

                $('#precookedHeelWidthVals').val(heelWidthVals);

                // Combine each of the precooked deletion fields separated by _BREAK_
                var deleteVals = "";
                $("input[name*='popupPrecookedDelete']").each(function() {
                  if ( $(this).attr("checked") ) {
                    if ( deleteVals != "" ) {
                      deleteVals = deleteVals + "_BREAK_";
                    }
                    deleteVals = deleteVals + $(this).val();
                  }
                });

                $('#precookedDeleteVals').val(deleteVals);
              }
          });
          
          $('#precookedPopupButton').click(function() {
            precookedConfig.dialog('open');
            return false;
          });
          
          $('#addPrecookedButton').click(function() {
            $('#rightTempleLengthStandard').val($('#popupRightTempleLengthStandard').val());
            var numb = parseInt($('#numOfPrecookedRows').val());

            // Create a new row to insert into the table
            var newRowNum = numb + 1;
            
            var newRow = $('<tr></tr>').append('<td></td>')
            var refInputCell = $('<td></td>').append('<input type="text" id="popupPrecookedReference-' + newRowNum + '" name="popupPrecookedReference-' + newRowNum + '"></input>');
            var bridgeWidthInputCell = $('<td></td>').append('<input type="text" id="popupPrecookedBridgeWidth-' + newRowNum + '" name="popupPrecookedBridgeWidth-' + newRowNum + '"></input>');
            var heelWidthInputCell = $('<td></td>').append('<input type="text" id="popupPrecookedHeelWidth-' + newRowNum + '" name="popupPrecookedHeelWidth-' + newRowNum + '"></input>');
            var fileInputCell = $('<td></td>').append('<input type="file" id="popupPrecookedFile-' + newRowNum + '" name="popupPrecookedFile-' + newRowNum + '"></input>');
            var className = "even";
            if ( numb % 2 == 0 )
                className = "odd";
              
            newRow.append(refInputCell).append(bridgeWidthInputCell).append(heelWidthInputCell).append(fileInputCell);
            newRow.addClass(className);
            
            
            newRow.insertAfter($('#precookedTable tbody>tr:last'));
            

            $('#numOfPrecookedRows').val(numb+1);

            return false;
          });
          
          $('#addFrontalColourButton').click(function() {
            
            var newColourSelect = $('#popupFrontalNewBaseColour');
            var selectedVal = newColourSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var colourList = $('#frontalBaseColourList');
              var numOfRows = parseInt($('#numOfFrontalBaseColourRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              colourList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="popupFrontalBaseColourNewVal" value="'+selectedNum+'"></input>').append('<input type="checkbox" class="popupFrontalBaseColourDelVal" value="' + selectedNum + '"/>')));
              
              $('#frontalBaseColourNoneLinkedEntry').hide();
              $('#frontalBaseColourListTableHeading').show();
              
              // Remove the colour from the list
              $('#popupFrontalNewBaseColour option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfFrontalBaseColourRows').val(numOfRows+1);
              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });
          
          
          $('#addFrontalTextureButton').click(function() {
            
            var newImageSelect = $('#popupFrontalNewTextureImage');
            var selectedVal = newImageSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var textureList = $('#frontalTextureListTable');
              var numOfRows = parseInt($('#numOfFrontalTextureRows').val());
              
              var rowClass = "even";
              if ( (numOfRows % 2) == 0 )
                  rowClass = "odd";
                
              
                
              var newRow = $('<tr class="'+rowClass+'"></tr>');
              
              newRow.append($('<td></td>').append('<input type="hidden" id="frontalTextureImageId-' + numOfRows + '" value="'+selectedNum+'"/>').append(selectedName));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureReference-' + numOfRows + '" value=""/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureX-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureY-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureScaleFactor-' + numOfRows + '" value="${formatNumber(number:0, format:"0.0")}"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTexturePrice-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.00")}"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="frontalTextureProductionTime-' + numOfRows + '" value="0"/>'));
              newRow.append('<td></td>');
              
              textureList.append(newRow);
              
              $('#frontalTextureListNoEntry').hide();
              $('#frontalTextureListTableHeading').show();
              
              // Remove the image from the list
              $('#popupFrontalNewTextureImage option:selected').remove();

              
              // Update the number of rows so we can stripe the cells
              $('#numOfFrontalTextureRows').val(numOfRows+1);

              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });

          $('#addLeftTempleColourButton').click(function() {
            
            var newColourSelect = $('#popupLeftTempleNewBaseColour');
            var selectedVal = newColourSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var colourList = $('#leftTempleBaseColourList');
              var numOfRows = parseInt($('#numOfLeftTempleBaseColourRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              colourList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="popupLeftTempleBaseColourNewVal" value="'+selectedNum+'"></input>').append('<input type="checkbox" class="popupLeftTempleBaseColourDelVal" value="' + selectedNum + '"/>')));
              
              $('#leftTempleBaseColourNoneLinkedEntry').hide();
              $('#leftTempleBaseColourListTableHeading').show();
              
              // Remove the colour from the list
              $('#popupLeftTempleNewBaseColour option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfLeftTempleBaseColourRows').val(numOfRows+1);
              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });
          
          
          $('#addLeftTempleTextureButton').click(function() {
            
            var newImageSelect = $('#popupLeftTempleNewTextureImage');
            var selectedVal = newImageSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var textureList = $('#leftTempleTextureListTable');
              var numOfRows = parseInt($('#numOfLeftTempleTextureRows').val());
              
              var rowClass = "even";
              if ( (numOfRows % 2) == 0 )
                  rowClass = "odd";
                
                
                
              var newRow = $('<tr class="'+rowClass+'"></tr>');
              
              newRow.append($('<td></td>').append('<input type="hidden" id="leftTempleTextureImageId-' + numOfRows + '" value="'+selectedNum+'"/>').append(selectedName));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTextureReference-' + numOfRows + '" value=""/>'));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTextureX-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTextureY-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="numeric" name="leftTempleTextureScaleFactor-' + numOfRows + '" id="leftTempleTextureScaleFactor-' + numOfRows + '" class="required" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTexturePrice-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.00")}"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="leftTempleTextureProductionTime-' + numOfRows + '" value="0"/>'));
              newRow.append('<td></td>');
              
              textureList.append(newRow);
              
              $('#leftTempleTextureListNoEntry').hide();
              $('#leftTempleTextureListTableHeading').show();
              
              // Remove the image from the list
              $('#popupLeftTempleNewTextureImage option:selected').remove();

              
              // Update the number of rows so we can stripe the cells
              $('#numOfLeftTempleTextureRows').val(numOfRows+1);

              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });

          $('#addRightTempleColourButton').click(function() {
            
            var newColourSelect = $('#popupRightTempleNewBaseColour');
            var selectedVal = newColourSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var colourList = $('#rightTempleBaseColourList');
              var numOfRows = parseInt($('#numOfRightTempleBaseColourRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              colourList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="popupRightTempleBaseColourNewVal" value="'+selectedNum+'"></input>').append('<input type="checkbox" class="popupRightTempleBaseColourDelVal" value="' + selectedNum + '"/>')));
              
              $('#rightTempleBaseColourNoneLinkedEntry').hide();
              $('#rightTempleBaseColourListTableHeading').show();
              
              // Remove the colour from the list
              $('#popupRightTempleNewBaseColour option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfRightTempleBaseColourRows').val(numOfRows+1);
              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });
          
          
          $('#addRightTempleTextureButton').click(function() {
            
            var newImageSelect = $('#popupRightTempleNewTextureImage');
            var selectedVal = newImageSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var textureList = $('#rightTempleTextureListTable');
              var numOfRows = parseInt($('#numOfRightTempleTextureRows').val());
              
              var rowClass = "even";
              if ( (numOfRows % 2) == 0 )
                  rowClass = "odd";
                
                
                
              var newRow = $('<tr class="'+rowClass+'"></tr>');
              
              newRow.append($('<td></td>').append('<input type="hidden" id="rightTempleTextureImageId-' + numOfRows + '" value="'+selectedNum+'"/>').append(selectedName));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureReference-' + numOfRows + '" value=""/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureX-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureY-' + numOfRows + '" value="0"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureScaleFactor-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.0")}"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTexturePrice-' + numOfRows + '" value="${formatNumber(number:0.0, format:"0.00")}"/>'));
              newRow.append($('<td></td>').append('<input type="text" id="rightTempleTextureProductionTime-' + numOfRows + '" value="0"/>'));
              newRow.append('<td></td>');
              
              textureList.append(newRow);
              
              $('#rightTempleTextureListNoEntry').hide();
              $('#rightTempleTextureListTableHeading').show();
              
              // Remove the image from the list
              $('#popupRightTempleNewTextureImage option:selected').remove();

              
              // Update the number of rows so we can stripe the cells
              $('#numOfRightTempleTextureRows').val(numOfRows+1);

              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });

        });

        function submitForm() {
          var formToSubmit = $('#frameForm');
          
          var precookedFileInputs = $('input[name*=popupPrecookedFile]').each(function() {

            formToSubmit.append($(this));
          });

        }
    </script>
  </body>
</html>
