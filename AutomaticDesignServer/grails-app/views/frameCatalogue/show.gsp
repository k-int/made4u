<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.frame.show.page.head.title', default: 'Frame details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    <jqui:resources />

  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.frame.show.title', default: 'Individual frame')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.frame.properties.heading', default: 'Frame properties:')}</h2>
            <div class="propertiesTable">
              <table class="propertiesTable">
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.id.label', default: 'Id')}:</td>
                    <td class="value">${fieldValue(bean: frameInstance, field: "id")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.identifier.label', default: 'Identifier')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "identifier")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.material.label', default: 'Material')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "material.name")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.rim.type.label', default: 'Rim type')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "rimType.value")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.gender.label', default: 'Gender')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "gender.value")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.rim.shape.label', default: 'Rim shape')}</td>
                    <td>${fieldValue(bean: frameInstance, field: "rimShape.name")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.endpiece.height.label', default: 'Endpiece height')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "endpieceHeight.value")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.use.label', default: 'Use')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "frameUse.value")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'frame.prices.label', default: 'Prices')}:</h3>
                    </td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.fully.personalised.base.price.label', default: 'Fully personalised')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>${formatNumber(number:frameInstance.fullyPersonalisedBasePrice, format:"0.00")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.semi.personalised.base.price.label', default: 'Semi-personalised')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>${formatNumber(number:frameInstance.semiPersonalisedBasePrice, format:"0.00")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.standard.base.price.label', default: 'Standard')} ${message(code: 'price.currency.symbol')}:</td>
                    <td>${formatNumber(number:frameInstance.standardBasePrice, format:"0.00")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">
                      <h3>${message(code: 'frame.time.label', default: 'Times')}:</h3>
                    </td>
                    <td/>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.fully.personalised.time.label', default: 'Fully personalised')} ${message(code: 'time.day.symbol')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "fullyPersonalisedProductionTime")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.semi.personalised.time.label', default: 'Semi personalised')} ${message(code: 'time.day.symbol')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "semiPersonalisedProductionTime")}</td>
                  </tr>
                  <tr>
                    <td class="tableHeading">${message(code: 'frame.display.order.label', default: 'Display order')}:</td>
                    <td>${fieldValue(bean: frameInstance, field: "displayOrder")}</td>
                  </tr>
              </table>
            </div>
            
              <div class="extraConfigOptions">
                <h2>${message(code: 'frame.database.frame.components.heading', default: 'Frame components:')}</h2>
                <div class="extraButtonArea">
                  <ul>
                    <li><a class="button" href='#' id="frontalOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.frontal.options.show.button.label', default: 'Frontal configuration')}"/> ${message(code: 'frame.database.frontal.options.show.button.text', default: 'Frontal configuration')}</a></li>
                    <li><a class="button" href="#" id="leftTempleOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.left.temple.options.show.button.label', default: 'Left temple configuration')}"/> ${message(code: 'frame.database.left.temple.options.show.button.text', default: 'Left temple configuration')}</a></li>
                    <li><a class="button" href="#" id="rightTempleOptionsButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.right.temple.options.show.button.label', default: 'Right temple configuration')}"/> ${message(code: 'frame.database.right.temple.options.show.button.text', default: 'Right temple configuration')}</a></li>
                    <li><h3>Extra configuration</h3></li>
                    <li><a class="button" href="#" id="standardsPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.standard.component.show.button.label', default: 'View standards')}"/> ${message(code: 'frame.database.standard.component.show.button.text', default: 'Standards configuration')}</a></li>
                    <li><a class="button" href="#" id="precookedPopupButton"><img src="${createLinkTo(dir, '/')}images/gear.png" alt="${message(code: 'frame.database.precooked.button.show.label', default: 'View precookeds')}"/> ${message(code: 'frame.database.precooked.show.button.text', default: 'Precookeds')}</a></li>
                  </ul>
                </div>
              </div>


            <div class="buttonArea">
              <g:form method="post" name="frameForm">
                <g:hiddenField name="id" value="${frameInstance.id}"/>

                <g:link class="button" controller="frameCatalogue" action="list"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'frame.showall.label', default: 'Show all frames')}"/> ${message(code: 'frame.showall.label', default: 'Show all frames')}</g:link>
                <g:link class="button" action="edit" id="${frameInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'frame.edit.button.label', default: 'Edit this frame')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
                <g:link class="button" controller="frameAestheticConfiguration" action="show" id="${frameInstance.id}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'frame.aesthetic.configuration.show.button.label', default: 'View this frame&apos;s aesthetic configuration')}"/> ${message(code: 'frame.aesthetic.configuration.show.button.text', default: 'Aesthetic configurations')}</g:link>
                <g:link class="button" controller="frameCopy" action="create" id="${frameInstance.id}"><img src="${createLinkTo(dir, '/')}images/copy.jpg" alt="${message(code: 'frame.database.frame.copy.button.label', default: 'Copy this frame')}"/> ${message(code: 'frame.database.frame.copy.button.text', default: 'Copy')}</g:link>
                <a class="button" value="${message(code: 'frame.delete.button.label', default: 'Delete this frame')}" onclick="javascript:confirmSubmitForm('frameForm', 'delete', ${message(code: 'confirm.delete.message', default: 'Are you sure?')}" ><img src="${createLinkTo(dir, '/')}images/delete.jpg" alt="${message(code: 'frame.delete.button.label', default: 'Delete this frame')}"/> ${message(code: 'delete.button.text', default: 'Delete')}</a>
              </g:form>
            </div>
       </div>
    
    
                            
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
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.min.lens.thickness', default: 'Minimum lens thickness')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.minLensThickness, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.finishing.aspect', default: 'Finishing aspect')}:</td>
            <td>
              ${frameInstance.finishingAspect.value}
            </td>
          </tr>
          <tr>
            <td class="tableHeading"><h3>${message(code: 'frame.database.frontal.config.popup.matte.heading', default: 'Matte finish:')}</td>
            <td/>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.matte.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.mattePrice, format:"0.00")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.matte.time', default: 'Production time')}:</td>
            <td>
              ${frameInstance.matteProductionTime}
            </td>
          </tr>
          <tr>
            <td class="tableHeading"><h3>${message(code: 'frame.database.frontal.config.popup.brilliant.heading', default: 'Brilliant finish:')}</td>
            <td/>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.brilliant.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.brilliantPrice, format:"0.00")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.brilliant.time', default: 'Production time')}:</td>
            <td>
              ${frameInstance.brilliantProductionTime}
            </td>
          </tr>
          <tr>
            <td class="tableHeading"><h3>${message(code: 'frame.database.frontal.config.popup.left.eye.heading', default: 'Left eye')}:</h3></td>
            <td/>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.left.eye.top.pupil.frame.distance', default: 'Top pupil-frame distance')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.leftTopPupilFrameDistance, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.left.eye.bottom.pupil.frame.distance.psv', default: 'Bottom pupil-frame distance (PSV)')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.leftBottomPupilFrameDistancePSV, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.left.eye.bottom.pupil.frame.distance.mf', default: 'Bottom pupil-frame distance (MF)')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.leftBottomPupilFrameDistanceMF, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading"><h3>${message(code: 'frame.database.frontal.config.popup.right.eye.heading', default: 'Right eye')}:</h3></td>
            <td/>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.right.eye.top.pupil.frame.distance', default: 'Top pupil-frame distance')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.rightTopPupilFrameDistance, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.right.eye.bottom.pupil.frame.distance.psv', default: 'Bottom pupil-frame distance (PSV)')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.rightBottomPupilFrameDistancePSV, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.config.popup.right.eye.bottom.pupil.frame.distance.mf', default: 'Bottom pupil-frame distance (MF)')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.rightBottomPupilFrameDistanceMF, format:"0.0")}
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
              ${formatNumber(number:frameInstance.dimensions.boxingHorizontalStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.boxingHorizontalMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.boxingHorizontalMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.boxing.vertical', default: 'Boxing vertical')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.boxingVerticalStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.boxingVerticalMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.boxingVerticalMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.pantoscopic.angle.left', default: 'Left pantoscopic angle')} ${message(code: 'angle.degree.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.pantoscopicAngleLeftMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.pantascopic.angle.right', default: 'Right pantoscopic angle')} ${message(code: 'angle.degree.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.pantoscopicAngleRightMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.bridge,width', default: 'Bridge width')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.bridgeWidthStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.bridgeWidthMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.bridgeWidthMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.bridge.height', default: 'Bridge height')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.bridgeHeightStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.bridgeHeightMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.bridgeHeightMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.heel.width', default: 'Heel width')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.heelWidthStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.heelWidthMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.heelWidthMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.internal.rim.reduction', default: 'Internal rim reduction')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.internalRimReductionStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.internalRimReductionMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.internalRimReductionMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.base.of.frame', default: 'Base of frame')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.baseOfFrameStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.baseOfFrameMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.baseOfFrameMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.wrap angle', default: 'Wrap angle')} ${message(code: 'angle.degree.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.wrapAngleStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.wrapAngleMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.wrapAngleMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.frontal.dimensions.config.popup.total.length.standard', default: 'Total length')} ${message(code: 'distance.mm.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLengthStandard, format:"0.0")}
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
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth1, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min1, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max1, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth2, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min2, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max2, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth3, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min3, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max3, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth4, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min4, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max4, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth5, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min5, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max5, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth6, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min6, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max6, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth7, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min7, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max7, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth8, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min8, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max8, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth9, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min9, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max9, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth10, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min10, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max10, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth11, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min11, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max11, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth12, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min12, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max12, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth13, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min13, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max13, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth14, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min14, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max14, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.bridgeWidth15, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.min15, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.dimensions.totalLength.max15, format:"0.0")}
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
            </tr>
            <g:each in="${frameInstance.frontalBaseColours}" status="i" var="colourInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>${colourInstance.name}</td>
              </tr>
            </g:each>
          </g:if>
          <g:else>
            <tr>
              <td id="frontalBaseColourNoneLinkedEntry">
                ${message(code: 'frame.database.frontal.decoration.config.popup.linked.colour.none.linked.message', default: 'None linked')}
              </td>
            </tr>
          </g:else>
        </table>

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
              </tr>
            </g:each>
          </g:if>
          <g:else>
            <tr id="frontalTextureListNoEntry">
              <td colspan="7">
                ${message(code: 'frame.database.frontal.decoration.config.popup.texture.table.none.linked.message', default: 'No textures currently linked')}
              </td>
            </tr>
          </g:else>
        </table>
      </div>

      <div id="leftTempleDecorationConfigPopup" class="popup">
        <h2>${message(code: 'frame.database.temple.decoration.config.popup.colour.heading', default: 'Base colour')}</h2>
        <table id="leftTempleBaseColourList" class="popupTable">
          <g:if test="${frameInstance.leftTempleBaseColours?.size() != 0}">
            <tr id="leftTempleBaseColourListTableHeading">
              <th>${message(code: 'frame.database.left.temple.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
            </tr>
            <g:each in="${frameInstance.leftTempleBaseColours}" status="i" var="colourInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>${colourInstance.name}</td>
              </tr>
            </g:each>
          </g:if>
          <g:else>
            <tr>
              <td id="leftTempleBaseColourNoneLinkedEntry">
                ${message(code: 'frame.database.left.temple.decoration.config.popup.linked.colour.none.linked.message', default: 'None linked')}
              </td>
            </tr>
          </g:else>
        </table>

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
              </tr>
            </g:each>
          </g:if>
          <g:else>
            <tr id="leftTempleTextureListNoEntry">
              <td colspan="7">
                ${message(code: 'frame.database.left.temple.decoration.config.popup.texture.table.none.linked.message', default: 'No textures currently linked')}
              </td>
            </tr>
          </g:else>
        </table>
      </div>

      <div id="rightTempleDecorationConfigPopup" class="popup">
        <h2>${message(code: 'frame.database.temple.decoration.config.popup.colour.heading', default: 'Base colour')}</h2>
        <table id="rightTempleBaseColourList" class="popupTable">
          <g:if test="${frameInstance.rightTempleBaseColours?.size() != 0}">
            <tr id="rightTempleBaseColourListTableHeading">
              <th>${message(code: 'frame.database.right.temple.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
            </tr>
            <g:each in="${frameInstance.rightTempleBaseColours}" status="i" var="colourInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>${colourInstance.name}</td>
              </tr>
            </g:each>
          </g:if>
          <g:else>
            <tr class="hidden" id="rightTempleBaseColourListTableHeading">
              <th>${message(code: 'frame.database.right.temple.decoration.config.popup.colour.table.colour.heading', default: 'Colour')}</th>
            </tr>
            <tr>
              <td id="rightTempleBaseColourNoneLinkedEntry">
                ${message(code: 'frame.database.right.temple.decoration.config.popup.linked.colour.none.linked.message', default: 'None linked')}
              </td>
            </tr>
          </g:else>
        </table>

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
              </tr>
            </g:each>
          </g:if>
          <g:else>
            <tr id="rightTempleTextureListNoEntry">
              <td colspan="7">
                ${message(code: 'frame.database.right.temple.decoration.config.popup.texture.table.none.linked.message', default: 'No textures currently linked')}
              </td>
            </tr>
          </g:else>
        </table>
      </div>

      <div id="leftTempleConfigPopup" class="popup">
        <table class="popupTable">
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.finishing.aspect', default: 'Finishing aspect')}:</td>
            <td>
              ${frameInstance.leftTempleFinishingAspect.value}
            </td>
          </tr>
          <tr>
            <td class="tableHeading"><h3>${message(code: 'frame.database.left.temple.config.popup.matte.heading', default: 'Matte finish:')}</td>
            <td/>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.matte.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleMattePrice, format:"0.00")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.matte.time', default: 'Production time')}:</td>
            <td>
              ${frameInstance.leftTempleMatteProductionTime}
            </td>
          </tr>
          <tr>
            <td class="tableHeading"><h3>${message(code: 'frame.database.left.temple.config.popup.brilliant.heading', default: 'Brilliant finish:')}</td>
            <td/>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.brilliant.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleBrilliantPrice, format:"0.00")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.left.temple.config.popup.brilliant.time', default: 'Production time')}:</td>
            <td>
              ${frameInstance.leftTempleBrilliantProductionTime}
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
              ${formatNumber(number:frameInstance.leftTempleDims.lengthStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.lengthMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.lengthMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.left.temple.dimensions.config.popup.opening.angle', default: 'Opening angle')} ${message(code: 'angle.degree.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.openingAngleStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.openingAngleMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.openingAngleMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">
              ${message(code: 'frame.database.left.temple.dimensions.config.popup.main.curvature', default: 'Main curvature (top view)')} ${message(code: 'distance.mm.symbol')}:
              <img src="${createLinkTo(dir, '/')}images/help.png" alt="${message(code: 'frame.database.left.temple.dimensions.config.popup.main.curvature.explanation')}" title="${message(code: 'frame.database.left.temple.dimensions.config.popup.main.curvature.explanation')}"/>
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.mainCurvatureMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">
              ${message(code: 'frame.database.left.temple.dimensions.config.popup.terminal.angle', default: 'Terminal angle')} ${message(code: 'angle.degree.symbol')}:
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.terminalAngleStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.terminalAngleMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.leftTempleDims.terminalAngleMax, format:"0.0")}
            </td>
          </tr>
        </table>
      </div>

      <div id="rightTempleConfigPopup" class="popup">
        <table class="popupTable">
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.finishing.aspect', default: 'Finishing aspect')}:</td>
            <td>
              ${frameInstance.rightTempleFinishingAspect.value}
            </td>
          </tr>
          <tr>
            <td class="tableHeading"><h3>${message(code: 'frame.database.right.temple.config.popup.matte.heading', default: 'Matte finish:')}</td>
            <td/>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.matte.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleMattePrice, format:"0.00")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.matte.time', default: 'Production time')}:</td>
            <td>
              ${frameInstance.rightTempleMatteProductionTime}
            </td>
          </tr>
          <tr>
            <td class="tableHeading"><h3>${message(code: 'frame.database.right.temple.config.popup.brilliant.heading', default: 'Brilliant finish:')}</td>
            <td/>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.brilliant.price', default: 'Price')}${message(code: 'price.currency.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleBrilliantPrice, format:"0.00")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.right.temple.config.popup.brilliant.time', default: 'Production time')}:</td>
            <td>
              ${frameInstance.rightTempleBrilliantProductionTime}
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
              ${formatNumber(number:frameInstance.rightTempleDims.lengthStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.lengthMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.lengthMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.right.temple.dimensions.config.popup.opening.angle', default: 'Opening angle')} ${message(code: 'angle.degree.symbol')}:</td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.openingAngleStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.openingAngleMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.openingAngleMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">
              ${message(code: 'frame.database.right.temple.dimensions.config.popup.main.curvature', default: 'Main curvature (top view)')} ${message(code: 'distance.mm.symbol')}:
              <img src="${createLinkTo(dir, '/')}images/help.png" alt="${message(code: 'frame.database.right.temple.dimensions.config.popup.main.curvature.explanation')}" title="${message(code: 'frame.database.right.temple.dimensions.config.popup.main.curvature.explanation')}"/>
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.mainCurvatureMax, format:"0.0")}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">
              ${message(code: 'frame.database.right.temple.dimensions.config.popup.terminal.angle', default: 'Terminal angle')} ${message(code: 'angle.degree.symbol')}:
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.terminalAngleStandard, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.terminalAngleMin, format:"0.0")}
            </td>
            <td>
              ${formatNumber(number:frameInstance.rightTempleDims.terminalAngleMax, format:"0.0")}
            </td>
          </tr>
        </table>
      </div>

      <div id="standardsConfigPopup" class="popup">
         <table class="popupTable">
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.standards.config.popup.hinge', default: 'Hinge')}:</td>
            <td>
              ${frameInstance.hinge?.reference}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.standards.config.popup.nylon', default: 'Nylon')}:</td>
            <td>
              ${frameInstance.nylon?.reference}
            </td>
          </tr>
          <tr>
            <td class="tableHeading">${message(code: 'frame.database.standards.config.popup.screw', default: 'Screw')}:</td>
            <td>
              ${frameInstance.screw?.reference}
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
            </tr>
          </thead>
          <tbody>
            <g:each in="${frameInstance.precookeds}" status="i" var="precookedInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>${precookedInstance.id}</td>
                <td>${precookedInstance.reference}</td>
                <td>${formatNumber(number:precookedInstance.bridgeWidth, format:"0.0")}</td>
                <td>${formatNumber(number:precookedInstance.heelWidth, format:"0.0")}</td>
                <td><a href="${precookedInstance.path}" title="${message(code: 'frame.database.precooked.config.path.title', default: 'View the precooked file')}" target="_new">${message(code: 'frame.database.precooked.config.path.link.text', default: 'View file')}</a></td>
              </tr>
            </g:each>
          </tbody>
        </table>
      </div>

    <script type="text/javascript">
    
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
              width: 450
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
              width: 600
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
              width: 600
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
              width: 1000
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
              width: 450
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
              width: 650
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
              width: 1000
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
              width: 450
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
              width: 650
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
              width: 1000
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
              width: 400
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
              width: 800
          });
          
          $('#precookedPopupButton').click(function() {
            precookedConfig.dialog('open');
            return false;
          });
    </script>
  </body>
</html>
