<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.aesthetic.configuration.edit.page.head.title', default: 'Frame aesthetic configuration details - edit')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

        <div id="pageBody">
            <h1>${message(code: 'frame.database.aesthetic.configuration.edit.page.title', default: 'Individual frame aesthetic configuration details')}</h1>

            <g:if test="${config1.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${config1}"/>
              </div>
            </g:if>
            <g:if test="${config2.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${config2}"/>
              </div>
            </g:if>
            <g:if test="${config3.hasErrors()}">
              <div class="errors">
                <g:renderErrors bean="${config3}"/>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>

            <h2>${message(code: 'frame.database.aesthetic.configuration.edit.page.properties.heading', default: 'Aesthetic configuration properties:')}</h2>
            
            <form id="aestheticConfigForm" method="post" enctype="multipart/form-data">

              <h2>${message(code: 'frame.database.aesthetic.configuration.one.heading', default: 'Configuration 1:')}</h2>
              <div class="aestheticRowEdit">
                <div class="aestheticConfigImageDivEdit">
                  <g:if test="${config1.imagePath}">
                    <a href="${config1.imagePath}" target="_new"><img src="${config1.imagePath}" class="aestheticImage" title="${message(code: 'frame.database.aesthetic.configuration.image.click.to.view', default: 'Click to view larger image')}"/></a>
                    ${message(code: 'frame.database.aesthetic.configuration.image.delete', default: 'Delete?')} <input type="checkbox" id="config1ImageDelete" name="config1ImageDelete"/>
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.choose.image', default: 'Image:')}
                    <input type="file" name="config1Image" id="config1Image"/>
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.reference.label', default: 'Reference:')}</h4>
                  <input type="text" id="config1.reference" name="config1.reference" value="${config1.reference}"/>
                </div>
                <div class="aestheticConfigEmotionalVariableDivEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.heading', default: 'Emotional variables:')}</h3>
                  <table id="config1EmoList" class="popupTable">
                    <g:if test="${config1.emotionalVariables?.size() != 0}">
                      <tr>
                        <th>Variable</th>
                        <th>Delete?</th>
                      </tr>
                      <g:each in="${config1.emotionalVariables}" var="emoVar" status="i">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <td>${emoVar.name}</td>
                          <td><input type="checkbox" class="config1DeleteEmoVar" value="${emoVar.id}"/></td>
                        </tr>
                      </g:each>
                    </g:if>
                    <g:else>
                      <tr class="hidden" id="config1EmoVarTableHeading">
                        <th>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.table.variable.heading', default: 'Variable')}</th>
                        <th>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.table.delete.heading', default: 'Delete?')}</th>
                      </tr>
                      <tr id="config1EmoListNoneLinkedRow">
                        <td colspan="2">${message(code: 'frame.database.aesthetic.configuration.emotional.variable.none.linked', default: 'None specified')}</td>
                      </tr>
                    </g:else>
                  </table>
                  
                  <input type="hidden" id="numOfConfig1EmoVarRows" value="${config1.emotionalVariables?.size()}"/>

                  <select id="config1NewEmotionalVars" name="config1NewEmotionalVars">
                    <option value="">${message(code: 'frame.database.aesthetic.configuration.emotional.variable.please.choose', default: 'Please choose')}</option>
                    <g:each in="${config1AvailableEmotionalVars}" var="thisEmo">
                      <option value="${thisEmo.id}--${thisEmo.name}">${thisEmo.name}</option>
                    </g:each>
                  </select>

                  <a class="button" href='#' id="addConfig1EmoVarButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.aesthetic.configuration.emotional.variable.add.button.label', default: 'Add emotional variable')}"/> ${message(code: 'frame.database.aesthetic.configuration.emotional.variable.add.button.text', default: 'Add')}</a>

                </div>
              </div>
              <div class="aestheticRowEdit">
                <div class="aestheticConfigLeftTempleEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.left.temple.heading', default: 'Left temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config1LeftTempleFinishingAspect" name="config1.leftTempleFinishingAspect" from="${availableLeftTempleFinishingAspects}" value="${config1.leftTempleFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.texture.label', default: 'Texture:')}</h4>
                  <g:select id="config1LeftTempleTexture" name="config1LeftTempleTexture" from="${availableLeftTempleTextures}" value="${config1.leftTempleTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:select id="config1LeftTempleBaseColour" name="config1LeftTempleBaseColour" from="${availableLeftTempleBaseColours}" value="${config1.leftTempleBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                </div>
                <div class="aestheticConfigFrontalEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.frontal.heading', default: 'Frontal')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config1FrontalFinishingAspect" name="config1.frontalFinishingAspect" from="${availableFrontalFinishingAspects}" value="${config1.frontalFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.texture.label', default: 'Texture:')}</h4> 
                  <g:select id="config1FrontalTexture" name="config1FrontalTexture" from="${availableFrontalTextures}" value="${config1.frontalTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.base.colour.label', default: 'Base colour:')}</h4> 
                  <g:select id="config1FrontalBaseColour" name="config1FrontalBaseColour" from="${availableFrontalBaseColours}" value="${config1.frontalBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  
                </div>
                <div class="aestheticConfigRightTempleEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.right.temple.heading', default: 'Right temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config1RightTempleFinishingAspect" name="config1.rightTempleFinishingAspect" from="${availableRightTempleFinishingAspects}" value="${config1.rightTempleFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.texture.label', default: 'Texture:')}</h4>
                  <g:select id="config1RightTempleTexture" name="config1RightTempleTexture" from="${availableRightTempleTextures}" value="${config1.rightTempleTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:select id="config1RightTempleBaseColour" name="config1RightTempleBaseColour" from="${availableRightTempleBaseColours}" value="${config1.rightTempleBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                </div>
              </div>

              <h2>${message(code: 'frame.database.aesthetic.configuration.two.heading', default: 'Configuration 2:')}</h2>
              <div class="aestheticRowEdit">
                <div class="aestheticConfigImageDivEdit">
                  <g:if test="${config2.imagePath}">
                    <a href="${config2.imagePath}" target="_new"><img src="${config2.imagePath}" class="aestheticImage" title="${message(code: 'frame.database.aesthetic.configuration.image.click.to.view', default: 'Click to view larger image')}"/></a>
                    ${message(code: 'frame.database.aesthetic.configuration.image.delete', default: 'Delete?')} <input type="checkbox" id="config2ImageDelete" name="config2ImageDelete"/>
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.choose.image', default: 'Image:')}
                    <input type="file" name="config2Image" id="config1Image"/>
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.reference.label', default: 'Reference:')}</h4>
                  <input type="text" id="config2.reference" name="config2.reference" value="${config2.reference}"/>
                </div>
                <div class="aestheticConfigEmotionalVariableDivEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.heading', default: 'Emotional variables:')}</h3>
                  <table id="config2EmoList" class="popupTable">
                    <g:if test="${config2.emotionalVariables?.size() != 0}">
                      <tr>
                        <th>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.table.variable.heading', default: 'Variable')}</th>
                        <th>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.table.delete.heading', default: 'Delete?')}</th>
                      </tr>
                      <g:each in="${config2.emotionalVariables}" var="emoVar" status="i">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <td>${emoVar.name}</td>
                          <td><input type="checkbox" class="config2DeleteEmoVar" value="${emoVar.id}"/></td>
                        </tr>
                      </g:each>
                    </g:if>
                    <g:else>
                      <tr class="hidden" id="config2EmoVarTableHeading">
                        <th>Variable</th>
                        <th>Delete?</th>
                      </tr>
                      <tr id="config2EmoListNoneLinkedRow">
                        <td colspan="2">${message(code: 'frame.database.aesthetic.configuration.emotional.variable.none.linked', default: 'None specified')}</td>
                      </tr>
                    </g:else>
                  </table>
                  
                  <input type="hidden" id="numOfConfig2EmoVarRows" value="${config2.emotionalVariables?.size()}"/>

                  <select id="config2NewEmotionalVars" name="config2NewEmotionalVars">
                    <option value="">${message(code: 'frame.database.aesthetic.configuration.emotional.variable.please.choose', default: 'Please choose')}</option>
                    <g:each in="${config2AvailableEmotionalVars}" var="thisEmo">
                      <option value="${thisEmo.id}--${thisEmo.name}">${thisEmo.name}</option>
                    </g:each>
                  </select>

                  <a class="button" href='#' id="addConfig2EmoVarButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.aesthetic.configuration.emotional.variable.add.button.label', default: 'Add emotional variable')}"/> ${message(code: 'frame.database.aesthetic.configuration.emotional.variable.add.button.text', default: 'Add')}</a>

                </div>
              </div>
              <div class="aestheticRowEdit">
                <div class="aestheticConfigLeftTempleEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.left.temple.heading', default: 'Left temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config2LeftTempleFinishingAspect" name="config2.leftTempleFinishingAspect" from="${availableLeftTempleFinishingAspects}" value="${config2.leftTempleFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.texture.label', default: 'Texture:')}</h4>
                  <g:select id="config2LeftTempleTexture" name="config2LeftTempleTexture" from="${availableLeftTempleTextures}" value="${config2.leftTempleTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:select id="config2LeftTempleBaseColour" name="config2LeftTempleBaseColour" from="${availableLeftTempleBaseColours}" value="${config2.leftTempleBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                </div>
                <div class="aestheticConfigFrontalEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.frontal.heading', default: 'Frontal')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config2FrontalFinishingAspect" name="config2.frontalFinishingAspect" from="${availableFrontalFinishingAspects}" value="${config2.frontalFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.texture.label', default: 'Texture:')}</h4> 
                  <g:select id="config2FrontalTexture" name="config2FrontalTexture" from="${availableFrontalTextures}" value="${config2.frontalTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.base.colour.label', default: 'Base colour:')}</h4> 
                  <g:select id="config2FrontalBaseColour" name="config2FrontalBaseColour" from="${availableFrontalBaseColours}" value="${config2.frontalBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  
                </div>
                <div class="aestheticConfigRightTempleEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.right.temple.heading', default: 'Right temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config2RightTempleFinishingAspect" name="config2.rightTempleFinishingAspect" from="${availableRightTempleFinishingAspects}" value="${config2.rightTempleFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.texture.label', default: 'Texture:')}</h4>
                  <g:select id="config2RightTempleTexture" name="config2RightTempleTexture" from="${availableRightTempleTextures}" value="${config2.rightTempleTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:select id="config2RightTempleBaseColour" name="config2RightTempleBaseColour" from="${availableRightTempleBaseColours}" value="${config2.rightTempleBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                </div>
              </div>

              <h2>${message(code: 'frame.database.aesthetic.configuration.three.heading', default: 'Configuration 3:')}</h2>
              <div class="aestheticRowEdit">
                <div class="aestheticConfigImageDivEdit">
                  <g:if test="${config3.imagePath}">
                    <a href="${config3.imagePath}" target="_new"><img src="${config3.imagePath}" class="aestheticImage" title="${message(code: 'frame.database.aesthetic.configuration.image.click.to.view', default: 'Click to view larger image')}"/></a>
                    ${message(code: 'frame.database.aesthetic.configuration.image.delete', default: 'Delete?')} <input type="checkbox" id="config3ImageDelete" name="config3ImageDelete"/>
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.choose.image', default: 'Image:')}
                    <input type="file" name="config3Image" id="config3Image"/>
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.reference.label', default: 'Reference:')}</h4>
                  <input type="text" id="config3.reference" name="config3.reference" value="${config3.reference}"/>
                </div>
                <div class="aestheticConfigEmotionalVariableDivEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.heading', default: 'Emotional variables:')}</h3>
                  <table id="config3EmoList" class="popupTable">
                    <g:if test="${config3.emotionalVariables?.size() != 0}">
                      <tr>
                        <th>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.table.variable.heading', default: 'Variable')}</th>
                        <th>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.table.delete.heading', default: 'Delete?')}</th>
                      </tr>
                      <g:each in="${config3.emotionalVariables}" var="emoVar" status="i">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <td>${emoVar.name}</td>
                          <td><input type="checkbox" class="config3DeleteEmoVar" value="${emoVar.id}"/></td>
                        </tr>
                      </g:each>
                    </g:if>
                    <g:else>
                      <tr class="hidden" id="config3EmoVarTableHeading">
                        <th>Variable</th>
                        <th>Delete?</th>
                      </tr>
                      <tr id="config3EmoListNoneLinkedRow">
                        <td colspan="2">${message(code: 'frame.database.aesthetic.configuration.emotional.variable.none.linked', default: 'None specified')}</td>
                      </tr>
                    </g:else>
                  </table>
                  
                  <input type="hidden" id="numOfConfig3EmoVarRows" value="${config3.emotionalVariables?.size()}"/>

                  <select id="config3NewEmotionalVars" name="config3NewEmotionalVars">
                    <option value="">${message(code: 'frame.database.aesthetic.configuration.emotional.variable.please.choose', default: 'Please choose')}</option>
                    <g:each in="${config3AvailableEmotionalVars}" var="thisEmo">
                      <option value="${thisEmo.id}--${thisEmo.name}">${thisEmo.name}</option>
                    </g:each>
                  </select>

                  <a class="button" href='#' id="addConfig3EmoVarButton"><img src="${createLinkTo(dir, '/')}images/add.jpg" alt="${message(code: 'frame.database.aesthetic.configuration.emotional.variable.add.button.label', default: 'Add emotional variable')}"/> ${message(code: 'frame.database.aesthetic.configuration.emotional.variable.add.button.text', default: 'Add')}</a>

                </div>
              </div>
              <div class="aestheticRowEdit">
                <div class="aestheticConfigLeftTempleEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.left.temple.heading', default: 'Left temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config3LeftTempleFinishingAspect" name="config3.leftTempleFinishingAspect" from="${availableLeftTempleFinishingAspects}" value="${config3.leftTempleFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.texture.label', default: 'Texture:')}</h4>
                  <g:select id="config3LeftTempleTexture" name="config3LeftTempleTexture" from="${availableLeftTempleTextures}" value="${config3.leftTempleTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:select id="config3LeftTempleBaseColour" name="config3LeftTempleBaseColour" from="${availableLeftTempleBaseColours}" value="${config3.leftTempleBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                </div>
                <div class="aestheticConfigFrontalEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.frontal.heading', default: 'Frontal')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config3FrontalFinishingAspect" name="config3.frontalFinishingAspect" from="${availableFrontalFinishingAspects}" value="${config3.frontalFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.texture.label', default: 'Texture:')}</h4> 
                  <g:select id="config3FrontalTexture" name="config3FrontalTexture" from="${availableFrontalTextures}" value="${config3.frontalTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.base.colour.label', default: 'Base colour:')}</h4> 
                  <g:select id="config3FrontalBaseColour" name="config3FrontalBaseColour" from="${availableFrontalBaseColours}" value="${config3.frontalBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>
                  
                </div>
                <div class="aestheticConfigRightTempleEdit">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.right.temple.heading', default: 'Right temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:select id="config3RightTempleFinishingAspect" name="config3.rightTempleFinishingAspect" from="${availableRightTempleFinishingAspects}" value="${config3.rightTempleFinishingAspect}" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.texture.label', default: 'Texture:')}</h4>
                  <g:select id="config3RightTempleTexture" name="config3RightTempleTexture" from="${availableRightTempleTextures}" value="${config3.rightTempleTexture?.id}" optionKey="id" optionValue="reference" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:select id="config3RightTempleBaseColour" name="config3RightTempleBaseColour" from="${availableRightTempleBaseColours}" value="${config3.rightTempleBaseColour?.id}" optionKey="id" optionValue="name" noSelection="${['':message(code: 'frame.database.aesthetic.configuration.vals.please.choose.message', default: 'Please choose')]}"/>

                </div>
              </div>
              
              <input type="hidden" id="config1AllNewEmoVars" name="config1AllNewEmoVars"/>
              <input type="hidden" id="config2AllNewEmoVars" name="config2AllNewEmoVars"/>
              <input type="hidden" id="config3AllNewEmoVars" name="config3AllNewEmoVars"/>
              <input type="hidden" id="config1AllDeleteEmoVars" name="config1AllDeleteEmoVars"/>
              <input type="hidden" id="config2AllDeleteEmoVars" name="config2AllDeleteEmoVars"/>
              <input type="hidden" id="config3AllDeleteEmoVars" name="config3AllDeleteEmoVars"/>

              <div class="buttonArea">
                <button type="submit" name="_action_update" class="button" controller="frameAestheticConfiguration" onclick="javascript:submitForm()"><img src="${createLinkTo(dir, '/images/')}/save.jpg" alt="${message(code: 'save.button.label', default: 'Save')}"/> ${message(code: 'save.button.text', default: 'Save')}</button>
                <g:link class="button" action="show" id="${frameId}"><img src="${createLinkTo(dir, '/')}images/cancel.png" alt="${message(code: 'cancel.button.label', default: 'Cancel')}"/> ${message(code: 'cancel.button.text', default: 'Cancel')}</g:link>
              </div>

              
            </form>
       </div>

    <script type="text/javascript">

      $(document).ready(function () {

        $('form[name="emotionalForm"]').validate( {
            rules: {
              'name': {
                required: true
              }
            }
          });
          
          
          $('#addConfig1EmoVarButton').click(function() {
            
            var newVarSelect = $('#config1NewEmotionalVars');
            var selectedVal = newVarSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var varList = $('#config1EmoList');
 
              var numOfRows = parseInt($('#numOfConfig1EmoVarRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              varList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="config1EmoVarNewVal" value="'+selectedNum+'"></input>')));
               
              $('#config1EmoListNoneLinkedRow').hide();
              $('#config1EmoVarTableHeading').show();
              
              // Remove the colour from the list
              $('#config1NewEmotionalVars option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfConfig1EmoVarRows').val(numOfRows+1);
              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });
        
        $('#addConfig2EmoVarButton').click(function() {

            var newVarSelect = $('#config2NewEmotionalVars');
            var selectedVal = newVarSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var varList = $('#config2EmoList');
 
              var numOfRows = parseInt($('#numOfConfig2EmoVarRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              varList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="config2EmoVarNewVal" value="'+selectedNum+'"></input>')));
               
              $('#config2EmoListNoneLinkedRow').hide();
              $('#config2EmoVarTableHeading').show();
              
              // Remove the colour from the list
              $('#config2NewEmotionalVars option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfConfig2EmoVarRows').val(numOfRows+1);
              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });
        
        $('#addConfig3EmoVarButton').click(function() {
            
            var newVarSelect = $('#config3NewEmotionalVars');
            var selectedVal = newVarSelect.val();
            var selectedNum = selectedVal.substring(0, selectedVal.indexOf("--"));
            var selectedName = selectedVal.substring(selectedVal.indexOf("--")+2)
            
            if ( selectedVal != "" ) {
              var varList = $('#config3EmoList');
 
              var numOfRows = parseInt($('#numOfConfig3EmoVarRows').val());
              var rowClass = "even";
              if ( numOfRows % 2 == 0 )
                  rowClass = "odd";
                
              varList.append($('<tr class="'+rowClass+'"></tr>').append($('<td></td>').append(selectedName)).append($('<td></td>').append('<input type="hidden" class="config3EmoVarNewVal" value="'+selectedNum+'"></input>')));
               
              $('#config3EmoListNoneLinkedRow').hide();
              $('#config3EmoVarTableHeading').show();
              
              // Remove the colour from the list
              $('#config3NewEmotionalVars option:selected').remove();
              
              // Update the number of rows so we can stripe the cells
              $('#numOfConfig3EmoVarRows').val(numOfRows+1);
              
            } else {
              alert("${message(code: 'frame.database.config.popup.no.choice.made.message', default: 'Please choose a value to add and try again')}");
            }

            return false;
          });
        });
        
        function submitForm() {
          var formToSubmit = $('#aestheticConfigForm');
          
          // Combine all of the new config 1 emo var fields into one field separated by _BREAK_
          var newConfig1EmoVarIds = "";
          var newConf1EmoVarFields = $("input[class='config1EmoVarNewVal']").each(function() {
            
            if ( newConfig1EmoVarIds != "" )
              newConfig1EmoVarIds = newConfig1EmoVarIds + "_BREAK_";
            
            newConfig1EmoVarIds = newConfig1EmoVarIds + $(this).val();
          });
                    
          $('#config1AllNewEmoVars').val(newConfig1EmoVarIds);

          // Combine all of the new config 2 emo var fields into one field separated by _BREAK_
          var newConfig2EmoVarIds = "";
          var newConf2EmoVarFields = $("input[class='config2EmoVarNewVal']").each(function() {
            
            if ( newConfig2EmoVarIds != "" )
              newConfig2EmoVarIds = newConfig2EmoVarIds + "_BREAK_";
            
            newConfig2EmoVarIds = newConfig2EmoVarIds + $(this).val();
          });
                    
          $('#config2AllNewEmoVars').val(newConfig2EmoVarIds);

          // Combine all of the new config 3 emo var fields into one field separated by _BREAK_
          var newConfig3EmoVarIds = "";
          var newConf3EmoVarFields = $("input[class='config3EmoVarNewVal']").each(function() {
            
            if ( newConfig3EmoVarIds != "" )
              newConfig3EmoVarIds = newConfig3EmoVarIds + "_BREAK_";
            
            newConfig3EmoVarIds = newConfig3EmoVarIds + $(this).val();
          });
                    
          $('#config3AllNewEmoVars').val(newConfig3EmoVarIds);

          // Combine all of the config 1 emo var fields to remove into one field separated by _BREAK_
          var config1EmoVarDeleteVals = "";
          $("input[class='config1DeleteEmoVar']").each(function() {
            if ( $(this).attr("checked") ) {
               if ( config1EmoVarDeleteVals != "" ) {
                 config1EmoVarDeleteVals = config1EmoVarDeleteVals + "_BREAK_";
               }
               config1EmoVarDeleteVals = config1EmoVarDeleteVals + $(this).val();
            }
          });

          $('#config1AllDeleteEmoVars').val(config1EmoVarDeleteVals);

          // Combine all of the config 2 emo var fields to remove into one field separated by _BREAK_
          var config2EmoVarDeleteVals = "";
          $("input[class='config2DeleteEmoVar']").each(function() {
            if ( $(this).attr("checked") ) {
               if ( config2EmoVarDeleteVals != "" ) {
                 config2EmoVarDeleteVals = config2EmoVarDeleteVals + "_BREAK_";
               }
               config2EmoVarDeleteVals = config2EmoVarDeleteVals + $(this).val();
            }
          });

          $('#config2AllDeleteEmoVars').val(config2EmoVarDeleteVals);

          // Combine all of the config 3 emo var fields to remove into one field separated by _BREAK_
          var config3EmoVarDeleteVals = "";
          $("input[class='config3DeleteEmoVar']").each(function() {
            if ( $(this).attr("checked") ) {
               if ( config3EmoVarDeleteVals != "" ) {
                 config3EmoVarDeleteVals = config3EmoVarDeleteVals + "_BREAK_";
               }
               config3EmoVarDeleteVals = config3EmoVarDeleteVals + $(this).val();
            }
          });

          $('#config3AllDeleteEmoVars').val(config3EmoVarDeleteVals);

        }

    </script>
  </body>
</html>
