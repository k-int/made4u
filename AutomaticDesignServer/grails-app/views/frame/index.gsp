<html>
    <head>
        <title>${message(code: 'frame.database.welcome.page.head.title', default: 'Frame database homepage')} &lt;&lt; Made4U</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.welcome.page.title', default: 'Welcome to the Made4U Frame Database')}</h1>

            <p>${message(code: 'frame.database.welcome.page.choose.para', default: 'Choose the section of the database that you are interested in below.')}</p>

            
            <div class="generalButtonArea">
              <h2>${message(code: 'frame.database.index.page.catalogue.heading', default: 'Catalogue')}</h2>
              <div>
                <a class="button" href="${createLinkTo(dir:'frameCatalogue/')}" title="${message(code: 'frame.database.welcome.page.frame.catalogue.link', default: 'View frame catalogue')}"><img src="${createLinkTo(dir, '/')}images/catalogue.png" alt="${message(code: 'frame.database.welcome.page.frame.catalogue.link', default: 'View frame catalogue')}"/> <br/>${message(code: 'frame.database.welcome.page.frame.catalogue.link', default: 'View frame catalogue')}</a>
              </div>
            </div>
            <div class="generalButtonArea">
              <h2>${message(code: 'frame.database.index.page.decoration.heading', default: 'Decoration')}</h2>
              <div class="left">
                <a class="button" href="${createLinkTo(dir:'frameImage/')}" title="${message(code: 'frame.database.welcome.page.image.link', default: 'View frame images')}"><img src="${createLinkTo(dir, '/')}images/images.png" alt="${message(code: 'frame.database.welcome.page.image.link', default: 'View frame images')}"/> <br/>${message(code: 'frame.database.welcome.page.image.link', default: 'View frame images')}</a>
              </div>
              <div class="right">
                <a class="button" href="${createLinkTo(dir:'frameBaseColour/')}" title="${message(code: 'frame.database.welcome.page.base.colour.link', default: 'View frame base colours')}"><img src="${createLinkTo(dir, '/')}images/colour.png" alt="${message(code: 'frame.database.welcome.page.base.colour.link', default: 'View frame base colours')}"/> <br/>${message(code: 'frame.database.welcome.page.base.colour.link', default: 'View frame base colours')}</a>
              </div>
            </div>
            <div class="generalButtonArea">
              <h2>${message(code: 'frame.database.index.page.frame.heading', default: 'Frame')}</h2>
              <div class="left">
                <a class="button" href="${createLinkTo(dir:'frameShape/')}" title="${message(code: 'frame.database.welcome.page.shape.link', default: 'View frame shapes')}"><img src="${createLinkTo(dir, '/')}images/shapes.png" alt="${message(code: 'frame.database.welcome.page.shape.link', default: 'View frame shapes')}"/> <br/>${message(code: 'frame.database.welcome.page.shape.link', default: 'View frame shapes')}</a>
              </div>
              <div class="right">
                <a class="button" href="${createLinkTo(dir:'frameEmotionalVariable/')}" title="${message(code: 'frame.database.welcome.page.emotional.variable.link', default: 'View frame emotional variables')}"><img src="${createLinkTo(dir, '/')}images/smiley.png" alt="${message(code: 'frame.database.welcome.page.emotional.variable.link', default: 'View frame emotional variables')}"/> <br/>${message(code: 'frame.database.welcome.page.emotional.variable.link', default: 'View frame emotional variables')}</a>
              </div>
            </div>
            <div class="generalButtonArea">
              <h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2>
              <div class="left">
                <a class="button" href="${createLinkTo(dir:'frameStandardComponent/')}" title="${message(code: 'frame.database.welcome.page.standard.component.link', default: 'View frame standard components')}"><img src="${createLinkTo(dir, '/')}images/hinge.png" alt="${message(code: 'frame.database.welcome.page.standard.component.link', default: 'View frame standard components')}"/> <br/>${message(code: 'frame.database.welcome.page.standard.component.link', default: 'View frame standard components')}</a>
              </div>
              <div class="right">
                <a class="button" href="${createLinkTo(dir:'frameMaterial/')}" title="${message(code: 'frame.database.welcome.page.material.link', default: 'View frame materials')}"><img src="${createLinkTo(dir, '/')}images/frame2.png" alt="${message(code: 'frame.database.welcome.page.material.link', default: 'View frame materials')}"/> <br/>${message(code: 'frame.database.welcome.page.material.link', default: 'View frame materials')}</a>
              </div>
            </div>
            <div class="generalButtonArea">
              <h2>${message(code: 'frame.database.index.page.system.heading', default: 'System')}</h2>
              <div class="left">
                <a class="button" href="${createLinkTo(dir:'frameSystemEnum/')}" title="${message(code: 'frame.database.welcome.page.system.enum.link', default: 'View frame system enumerations')}"><img src="${createLinkTo(dir, '/')}images/enumeration.png" alt="${message(code: 'frame.database.welcome.page.system.enum.link', default: 'View frame system enumerations')}"/> <br/>${message(code: 'frame.database.welcome.page.system.enum.link', default: 'View frame system enumerations')}</a>
              </div>
            </div>
            
            
        </div>
    </body>
</html>
