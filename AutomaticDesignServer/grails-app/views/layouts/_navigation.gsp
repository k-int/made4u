<!-- Navigation panel -->


<div id="nav">
    <div class="homePagePanel">
        <div class="panelTop"></div>
        <div class="panelBody">
          <h1>${message(code: 'navigation.panel.heading', default: 'Navigation')}</h1>
          <ul>
            <li class="navigationHeading">${message(code: 'navigation.home.heading', default: 'Home')}</li>
            <li><a class="home" href="${createLinkTo(dir:'')}" title="${message(code: 'navigation.home.link.title', default: 'Home page')}">${message(code: 'navigation.home.link.text', default: 'Home')}</a></li>
            <li class="navigationHeading">${message(code: 'navigation.fbm.heading', default: 'FBM')}</li>
            <li><a class="home" href="${createLinkTo(dir:'lens')}" title="${message(code: 'navigation.lens.database.link.title', default: 'Lens database')}">${message(code: 'navigation.lens.database.link.text', default: 'Lens database')}</a></li>
            <li><a class="home" href="${createLinkTo(dir:'frame')}" title="${message(code: 'navigation.frame.database.link.title', default: 'Frame database')}">${message(code: 'navigation.frame.database.link.text', default: 'Frame database')}</a></li>
            <li class="navigationHeading">${message(code: 'navigation.design.orders.heading', default: 'Designs/Orders')}</li>
            <li><a class="home" href="${createLinkTo(dir:'design')}" title="${message(code: 'navigation.retrieve.design.link.title', default: 'Designs')}">${message(code: 'navigation.retrieve.design.link.text', default: 'Designs')}</a></li>
            <li><a class="home" href="${createLinkTo(dir:'order')}" title="${message(code: 'navigation.place.order.link.title', default: 'Orders')}">${message(code: 'navigation.place.order.link.text', default: 'Orders')}</a></li>
            <li class="navigationHeading">${message(code: 'navigation.temp.heading', default: 'Temp')}</li>
            <li><a class="home" href="${createLinkTo(dir:'completeCatalogue')}" title="${message(code: 'navigation.complete.catalogue.link.title', default: 'Complete Catalogue (TEMP)')}">${message(code: 'navigation.complete.catalogue.link.text', default: 'Complete Catalogue (TEMP)')}</a></li>
            <li class="navigationHeading">${message(code: 'navigation.system.heading', default: 'System')}</li>
            <li><a class="home" href="${createLinkTo(dir:'timerData')}" title="${message(code: 'navigation.timer.data.link.title', default: 'Timer data')}">${message(code: 'navigation.timer.data.link.text', default: 'Timer data')}</a></li>
            <li><a class="home" href="${createLinkTo(dir:'workflow')}" title="${message(code: 'navigation.workflow.link.title', default: 'Workflows')}">${message(code: 'navigation.workflow.link.text', default: 'Workflows')}</a></li>
          </ul>
          
        </div>
        <div class="panelBtm"></div>
    </div>
  
  <div class="homePagePanel">
    <div class="panelTop"></div>
    <div class="panelBody">
      <h1>${message(code: 'status.panel.heading', default: 'Status')}</h1>
      <ul>
        <li>${message(code: 'status.panel.server.time.label', default: 'Server time')} - <g:formatDate date="${new Date()}" type="datetime" style="MEDIUM"/></li>
      </ul>
    </div>
    <div class="panelBtm"></div>
  </div>
</div>

