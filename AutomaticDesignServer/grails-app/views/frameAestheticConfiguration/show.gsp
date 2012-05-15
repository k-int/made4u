<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'frame.database.aesthetic.configuration.show.page.head.title', default: 'Frame aesthetic configuration details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.aesthetic.configuration.show.title', default: 'Individual frame aesthetic configurations')}</h1>

            <g:if test="${flash.error}">
                <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            
            <h2>${message(code: 'frame.database.aesthetic.configuration.one.heading', default: 'Configuration 1:')}</h2>

              <div class="aestheticRowShow">
                <div class="aestheticConfigImageDivShow">
                  <g:if test="${config1?.imagePath}">
                    <a href="${config1?.imagePath}" target="_new"><img src="${config1?.imagePath}" class="smallAestheticImage" title="${message(code: 'frame.database.aesthetic.configuration.image.click.to.view', default: 'Click to view larger image')}"/></a>
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.image.none.linked', default: 'No image currently given')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.reference.label', default: 'Reference:')}</h4>
                  <g:if test="${config1?.reference}">
                    ${config1.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
                <div class="aestheticConfigEmotionalVariableDivShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.heading', default: 'Emotional variables:')}</h3>
                  <ul>
                    <g:if test="${config1?.emotionalVariables?.size() != 0}">
                      <g:each in="${config1?.emotionalVariables}" var="emoVar">
                        <li>${emoVar.name}</li>
                      </g:each>
                    </g:if>
                    <g:else>
                      <li>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.none.linked', default: 'None specified')}</li>
                    </g:else>
                  </ul>
                </div>
              </div>
              <div class="aestheticRowShow">
                <div class="aestheticConfigLeftTempleShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.left.temple.heading', default: 'Left temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4> 
                  <g:if test="${config1?.leftTempleFinishingAspect}">
                    ${config1?.leftTempleFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                                      
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config1?.leftTempleTexture}">
                    ${config1?.leftTempleTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config1?.leftTempleBaseColour}">
                    ${config1?.leftTempleBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                </div>
                <div class="aestheticConfigFrontalShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.frontal.heading', default: 'Frontal')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:if test="${config1?.frontalFinishingAspect}">
                    ${config1?.frontalFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config1?.frontalTexture}">
                    ${config1?.frontalTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}                    
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config1?.frontalBaseColour}">
                    ${config1?.frontalBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
                
                <div class="aestheticConfigRightTempleShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.right.temple.heading', default: 'Right temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4> 
                  <g:if test="${config1?.rightTempleFinishingAspect}">
                    ${config1?.rightTempleFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config1?.rightTempleTexture}">
                    ${config1?.rightTempleTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config1?.rightTempleBaseColour}">
                    ${config1?.rightTempleBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
              </div>


            <h2>${message(code: 'frame.database.aesthetic.configuration.two.heading', default: 'Configuration 2:')}</h2>
            
              <div class="aestheticRowShow">
                <div class="aestheticConfigImageDivShow">
                  <g:if test="${config2?.imagePath}">
                    <a href="${config2?.imagePath}" target="_new"><img src="${config2?.imagePath}" class="smallAestheticImage" title="${message(code: 'frame.database.aesthetic.configuration.image.click.to.view', default: 'Click to view larger image')}"/></a>
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.image.none.linked', default: 'No image currently given')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.reference.label', default: 'Reference:')}</h4>
                  <g:if test="${config2?.reference}">
                    ${config2.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
                <div class="aestheticConfigEmotionalVariableDivShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.heading', default: 'Emotional variables:')}</h3>
                  <ul>
                    <g:if test="${config2?.emotionalVariables?.size() != 0}">
                      <g:each in="${config2?.emotionalVariables}" var="emoVar">
                        <li>${emoVar.name}</li>
                      </g:each>
                    </g:if>
                    <g:else>
                      <li>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.none.linked', default: 'None specified')}</li>
                    </g:else>
                  </ul>
                </div>
              </div>
              <div class="aestheticRowShow">
                <div class="aestheticConfigLeftTempleShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.left.temple.heading', default: 'Left temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4> 
                  <g:if test="${config2?.leftTempleFinishingAspect}">
                    ${config2?.leftTempleFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                                      
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config2?.leftTempleTexture}">
                    ${config2?.leftTempleTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config2?.leftTempleBaseColour}">
                    ${config2?.leftTempleBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                </div>
                <div class="aestheticConfigFrontalShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.frontal.heading', default: 'Frontal')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:if test="${config2?.frontalFinishingAspect}">
                    ${config2?.frontalFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config2?.frontalTexture}">
                    ${config2?.frontalTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}                    
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config2?.frontalBaseColour}">
                    ${config2?.frontalBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
                
                <div class="aestheticConfigRightTempleShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.right.temple.heading', default: 'Right temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4> 
                  <g:if test="${config2?.rightTempleFinishingAspect}">
                    ${config2?.rightTempleFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config2?.rightTempleTexture}">
                    ${config2?.rightTempleTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config2?.rightTempleBaseColour}">
                    ${config2?.rightTempleBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
              </div>

            <h2>${message(code: 'frame.database.aesthetic.configuration.three.heading', default: 'Configuration 3:')}</h2>

              <div class="aestheticRowShow">
                <div class="aestheticConfigImageDivShow">
                  <g:if test="${config3?.imagePath}">
                    <a href="${config3?.imagePath}" target="_new"><img src="${config3?.imagePath}" class="smallAestheticImage" title="${message(code: 'frame.database.aesthetic.configuration.image.click.to.view', default: 'Click to view larger image')}"/></a>
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.image.none.linked', default: 'No image currently given')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.reference.label', default: 'Reference:')}</h4>
                  <g:if test="${config3?.reference}">
                    ${config3.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
                <div class="aestheticConfigEmotionalVariableDivShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.heading', default: 'Emotional variables:')}</h3>
                  <ul>
                    <g:if test="${config3?.emotionalVariables?.size() != 0}">
                      <g:each in="${config3?.emotionalVariables}" var="emoVar">
                        <li>${emoVar.name}</li>
                      </g:each>
                    </g:if>
                    <g:else>
                      <li>${message(code: 'frame.database.aesthetic.configuration.emotional.variable.none.linked', default: 'None specified')}</li>
                    </g:else>
                  </ul>
                </div>
              </div>
              <div class="aestheticRowShow">
                <div class="aestheticConfigLeftTempleShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.left.temple.heading', default: 'Left temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4> 
                  <g:if test="${config3?.leftTempleFinishingAspect}">
                    ${config3?.leftTempleFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                                      
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config3?.leftTempleTexture}">
                    ${config3?.leftTempleTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.left.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config3?.leftTempleBaseColour}">
                    ${config3?.leftTempleBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                </div>
                <div class="aestheticConfigFrontalShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.frontal.heading', default: 'Frontal')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.finishing.aspect.label', default: 'Finishing aspect:')}</h4>
                  <g:if test="${config3?.frontalFinishingAspect}">
                    ${config3?.frontalFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config3?.frontalTexture}">
                    ${config3?.frontalTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}                    
                  </g:else>
                  
                  <h4>${message(code: 'frame.database.aesthetic.configuration.frontal.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config3?.frontalBaseColour}">
                    ${config3?.frontalBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
                
                <div class="aestheticConfigRightTempleShow">
                  <h3>${message(code: 'frame.database.aesthetic.configuration.right.temple.heading', default: 'Right temple')}</h3>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.finishing.aspect.label', default: 'Finishing aspect:')}</h4> 
                  <g:if test="${config3?.rightTempleFinishingAspect}">
                    ${config3?.rightTempleFinishingAspect}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.texture.label', default: 'Texture:')}</h4>
                  <g:if test="${config3?.rightTempleTexture}">
                    ${config3?.rightTempleTexture?.reference}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                  <h4>${message(code: 'frame.database.aesthetic.configuration.right.temple.base.colour.label', default: 'Base colour:')}</h4>
                  <g:if test="${config3?.rightTempleBaseColour}">
                    ${config3?.rightTempleBaseColour?.name}
                  </g:if>
                  <g:else>
                    ${message(code: 'frame.database.aesthetic.configuration.not.specified.message', default: 'Not yet specified')}
                  </g:else>
                </div>
              </div>

            <div class="buttonArea">
              
              <g:link class="button" action="edit" id="${frameId}"><img src="${createLinkTo(dir, '/')}images/edit.png" alt="${message(code: 'frame.database.aesthetic.configuration.edit.button.label', default: 'Edit these configurations')}"/> ${message(code: 'edit.button.text', default: 'Edit')}</g:link>
              <g:link class="button" controller="frameCatalogue" action="show" id="${frameId}"><img src="${createLinkTo(dir, '/')}images/back.jpg" alt="${message(code: 'frame.database.aesthetic.configuration.show.frame.button.label', default: 'Return to the frame details')}"/> ${message(code: 'frame.database.aesthetic.configuration.show.frame.button.text', default: 'Show frame')}</g:link>

            </div>
       </div>

  </body>
</html>
