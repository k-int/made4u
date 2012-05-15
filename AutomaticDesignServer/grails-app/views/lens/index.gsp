<html>
    <head>
        <title>${message(code: 'lens.database.index.page.head.title', default: 'Lens database homepage')} &lt;&lt; Made4U</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="pageBody">
            <h1>${message(code: 'lens.database.index.page.heading', default: 'Welcome to the Made4U Lens Database')}</h1>

            <p>${message(code: 'lens.database.index.page.para', default: 'Choose the section of the database that you are interested in below.')}</p>

            <ul>
              <li><a href="${createLinkTo(dir:'lensCoating/')}" title="${message(code: 'lens.database.coatings.link.title', default: 'View lens coatings')}">${message(code: 'lens.database.coatings.link.text', default: 'View lens coatings')}</a></li>
              <li><a href="${createLinkTo(dir:'lensSystemEnum/')}" title="${message(code: 'lens.database.enumeration.link.title', default: 'View lens system enumerations')}">${message(code: 'lens.database.system.enumeration.link.text', default: 'View lens system enumerations')}</a></li>
              <li><a href="${createLinkTo(dir:'lensTopcoat/')}" title="${message(code: 'lens.database.topcoat.link.title', default: 'View lens topcoats')}">${message(code: 'lens.database.topcoat.link.text', default: 'View lens topcoats')}</a></li>
              <li><a href="${createLinkTo(dir: 'lensMaterial/')}" title="${message(code: 'lens.database.material.link.title', default: 'View lens materials')}">${message(code: 'lens.database.material.link.text', default: 'View lens materials')}</a></li>
              <li><a href="${createLinkTo(dir: 'lensColour/')}" title="${message(code: 'lens.database.colour.link.title', default: 'View lens colours')}">${message(code: 'lens.database.colour.link.text', default: 'View lens colours')}</a></li>
              <li><a href="${createLinkTo(dir: 'lensSpecialColour/')}" title="${message(code: 'lens.database.special.colour.link.title', default: 'View lens special colours')}">${message(code: 'lens.database.special.colour.link.text', default: 'View lens special colours')}</a></li>
            </ul>
        </div>
    </body>
</html>
