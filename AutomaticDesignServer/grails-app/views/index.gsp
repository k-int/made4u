<html>
    <head>
        <title>${message(code: 'system.index.page.title', default: 'Homepage')} &lt;&lt; Made4U</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="pageBody">
            <h1>${message(code: 'system.index.page.heading', default: 'Welcome to  the Made4U Automatic Design server')}</h1>

            <p>${message(code: 'system.index.page.text', default: 'Choose the service you require below.')}</p>

            <ul>
              <li><a href="design" title="${message(code: 'system.index.page.get.design.link.title', default: 'Get design configurations')}">${message(code: 'system.index.page.get.design.link.text', default: 'Get viable design configurations')}</a></li>
              <li><a href="order" title="${message(code: 'system.index.page.place.order.link.title', default: 'Place order')}">${message(code: 'system.index.page.place.order.link.text', default: 'Place a manufacturing order')}</a></li>
              <li><a href="lens" title="${message(code: 'system.index.page.lens.database.link.title', default: 'View the lens database')}">${message(code: 'system.index.page.lens.database.link.text', default: 'View the lens database')}</a></li>
              <li><a href="frame" title="${message(code: 'system.index.page.frame.database.link.title', default: 'View the frame database')}">${message(code: 'system.index.frame.link', default: 'View the frame database')}</a></li>
            </ul>
        </div>
    </body>
</html>
