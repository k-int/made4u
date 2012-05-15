<html>
    <head>
        <title>${message(code: 'frame.database.welcome.page.head.title', default: 'Frame database homepage')} &lt;&lt; Made4U</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="pageBody">
            <h1>${message(code: 'frame.database.welcome.page.title', default: 'Welcome to the Made4U Frame Database')}</h1>

            <p>${message(code: 'frame.database.welcome.page.choose.para', default: 'Choose the section of the database that you are interested in below.')}</p>

            <ul>
              <li><a href="${createLinkTo(dir:'frameMaterial/')}" title="${message(code: 'frame.database.welcome.page.material.link', default: 'View frame materials')}">${message(code: 'frame.database.welcome.page.material.link', default: 'View frame materials')}</a></li>
              <li><a href="${createLinkTo(dir:'frameImage/')}" title="${message(code: 'frame.database.welcome.page.image.link', default: 'View frame images')}">${message(code: 'frame.database.welcome.page.image.link', default: 'View frame images')}</a></li>
              <li><a href="${createLinkTo(dir:'frameBaseColour/')}" title="${message(code: 'frame.database.welcome.page.base.colour.link', default: 'View frame base colours')}">${message(code: 'frame.database.welcome.page.base.colour.link', default: 'View frame base colours')}</a></li>
              <li><a href="${createLinkTo(dir:'frameShape/')}" title="${message(code: 'frame.database.welcome.page.shape.link', default: 'View frame shapes')}">${message(code: 'frame.database.welcome.page.shape.link', default: 'View frame shapes')}</a></li>
              <li><a href="${createLinkTo(dir:'frameEmotionalVariable/')}" title="${message(code: 'frame.database.welcome.page.emotional.variable.link', default: 'View frame emotional variables')}">${message(code: 'frame.database.welcome.page.emotional.variable.link', default: 'View frame emotional variables')}</a></li>
              <li><a href="${createLinkTo(dir:'frameStandardComponent/')}" title="${message(code: 'frame.database.welcome.page.standard.component.link', default: 'View frame standard components')}">${message(code: 'frame.database.welcome.page.standard.component.link', default: 'View frame standard components')}</a></li>
              <li><a href="${createLinkTo(dir:'frameSystemEnum/')}" title="${message(code: 'frame.database.welcome.page.system.enum.link', default: 'View frame system enumerations')}">${message(code: 'frame.database.welcome.page.system.enum.link', default: 'View frame system enumerations')}</a></li>
              <li><a href="${createLinkTo(dir:'frameCatalogue/')}" title="${message(code: 'frame.database.welcome.page.frame.catalogue.link', default: 'View frame catalogue')}">${message(code: 'frame.database.welcome.page.frame.catalogue.link', default: 'View frame catalogue')}</a></li>
            </ul>
            
        </div>
    </body>
</html>
