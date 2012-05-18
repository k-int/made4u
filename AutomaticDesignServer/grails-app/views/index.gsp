<html>
    <head>
        <title>${message(code: 'system.index.page.title', default: 'Homepage')} &lt;&lt; Made4U</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="pageBody">
            <h1>${message(code: 'system.index.page.heading', default: 'Welcome to  the Made4U Automatic Design server')}</h1>

            <p>${message(code: 'system.index.page.text', default: 'Choose the service you require below.')}</p>

            <div class="generalButtonArea">
              <h2>${message(code: 'system.index.page.database.heading', default: 'Database')}</h2>
              <div class="left">
                <a class="button" href="lens" title="${message(code: 'system.index.page.lens.database.link.title', default: 'View the lens database')}"><img src="${createLinkTo(dir, '/')}images/lens2.png" alt="${message(code: 'system.index.page.lens.database.link.text', default: 'View the lens database')}"/><br/>${message(code: 'system.index.page.lens.database.link.text', default: 'View the lens database')}</a>
              </div>
              <div class="right">
                <a class="button" href="frame" title="${message(code: 'system.index.page.frame.database.link.title', default: 'View the frame database')}"><img src="${createLinkTo(dir, '/')}images/frame2.png" alt="${message(code: 'system.index.frame.link', default: 'View the frame database')}"/><br/>${message(code: 'system.index.frame.link', default: 'View the frame database')}</a>
              </div>
            </div>
              
              
            <div class="generalButtonArea">
              <h2>${message(code: 'system.index.page.design.heading', default: 'Designs and Orders')}</h2>
              <div class="left">
                <a class="button" href="design" title="${message(code: 'system.index.page.get.design.link.title', default: 'Get design configurations')}"><img src="${createLinkTo(dir, '/')}images/design.png" alt="${message(code: 'system.index.page.get.design.link.text', default: 'Get viable design configurations')}"/><br/>${message(code: 'system.index.page.get.design.link.text', default: 'Get viable design configurations')}</a>
              </div>
              <div class="right">
                <a class="button" href="order" title="${message(code: 'system.index.page.place.order.link.title', default: 'Place order')}"><img src="${createLinkTo(dir, '/')}images/order.png" alt="${message(code: 'system.index.page.place.order.link.text', default: 'Place a manufacturing order')}"/><br/>${message(code: 'system.index.page.place.order.link.text', default: 'Place a manufacturing order')}</a>
              </div>
            </div>

        </div>
    </body>
</html>
