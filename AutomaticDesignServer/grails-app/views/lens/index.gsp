<html>
    <head>
        <title>${message(code: 'lens.database.index.page.head.title', default: 'Lens database homepage')} &lt;&lt; Made4U</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="pageBody">
            <h1>${message(code: 'lens.database.index.page.heading', default: 'Welcome to the Made4U Lens Database')}</h1>

            <p>${message(code: 'lens.database.index.page.para', default: 'Choose the section of the database that you are interested in below.')}</p>

            <div class="generalButtonArea">
              <h2>${message(code: 'lens.database.index.page.material.heading', default: 'Material')}</h2>
              <div>
                <a class="button" href="${createLinkTo(dir: 'lensMaterial/')}" title="${message(code: 'lens.database.material.link.title', default: 'View lens materials')}"><img src="${createLinkTo(dir, '/')}images/lens2.png" alt="${message(code: 'lens.database.material.link.text', default: 'View lens materials')}"/><br/>${message(code: 'lens.database.material.link.text', default: 'View lens materials')}</a>
              </div>
            </div>
            <div class="generalButtonArea">
              <h2>${message(code: 'lens.database.index.page.coating.heading', default: 'Coatings')}</h2>
              <div class="left">
                <a class="button" href="${createLinkTo(dir:'lensCoating/')}" title="${message(code: 'lens.database.coatings.link.title', default: 'View lens coatings')}"><img src="${createLinkTo(dir, '/')}images/coating.png" alt="${message(code: 'lens.database.coatings.link.text', default: 'View lens coatings')}"/><br/>${message(code: 'lens.database.coatings.link.text', default: 'View lens coatings')}</a>
              </div>
              <div class="right">
                <a class="button" href="${createLinkTo(dir:'lensTopcoat/')}" title="${message(code: 'lens.database.topcoat.link.title', default: 'View lens topcoats')}"><img src="${createLinkTo(dir, '/')}images/topcoat.png" alt="${message(code: 'lens.database.topcoat.link.text', default: 'View lens topcoats')}"/><br/>${message(code: 'lens.database.topcoat.link.text', default: 'View lens topcoats')}</a>
              </div>
            </div>
            <div class="generalButtonArea">
              <h2>${message(code: 'lens.database.index.page.colours.heading', default: 'Colours')}</h2>
              <div class="left">
                <a class="button" href="${createLinkTo(dir: 'lensColour/')}" title="${message(code: 'lens.database.colour.link.title', default: 'View lens colours')}"><img src="${createLinkTo(dir, '/')}images/colour.png" alt="${message(code: 'lens.database.colour.link.text', default: 'View lens colours')}"/><br/>${message(code: 'lens.database.colour.link.text', default: 'View lens colours')}</a>
              </div>
              <div class="right">
                <a class="button" href="${createLinkTo(dir: 'lensSpecialColour/')}" title="${message(code: 'lens.database.special.colour.link.title', default: 'View lens special colours')}"><img src="${createLinkTo(dir, '/')}images/specialColour.png" alt="${message(code: 'lens.database.special.colour.link.text', default: 'View lens special colours')}"/><br/>${message(code: 'lens.database.special.colour.link.text', default: 'View lens special colours')}</a>
              </div>
            </div>
            <div class="generalButtonArea">
              <h2>${message(code: 'lens.database.index.page.system.heading', default: 'System')}</h2>
              <div class="left">
                <a class="button" href="${createLinkTo(dir:'lensSystemEnum/')}" title="${message(code: 'lens.database.enumeration.link.title', default: 'View lens system enumerations')}"><img src="${createLinkTo(dir, '/')}images/enumeration.png" alt="${message(code: 'lens.database.system.enumeration.link.text', default: 'View lens system enumerations')}"/><br/>${message(code: 'lens.database.system.enumeration.link.text', default: 'View lens system enumerations')}</a>
              </div>
              <div class="right">
                <div class="emptyButton"/>
              </div>
            </div>
        </div>
    </body>
</html>
