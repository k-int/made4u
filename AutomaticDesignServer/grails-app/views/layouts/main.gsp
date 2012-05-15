<html>
    <head>
        <title><g:layoutTitle default="Made4U" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />

        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon2.png')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
        <g:javascript library="global"/>
        <g:javascript library="formSubmit"/>
    </head>
    <body>
      <div id="container">
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt', default:'Loading...')}" />
        </div>
          <div id="appLogo">
            <a href="http://www.made4u.info"><img src="${resource(dir:'images',file:'hdr.png')}" alt="${message(code: 'main.homepage.link', default: 'Made4U Homepage')}" border="0" /></a> 
          </div>
          <div id="userInfo">
            <shiro:isLoggedIn>
              <g:message code="login.welcome.message" /> <n:principalName /> | <g:link controller="auth" action="signOut" class=""><g:message code="logout.link.text" /></g:link>
            </shiro:isLoggedIn>
            <shiro:isNotLoggedIn>
              <g:link controller="auth" action="login" class=""><g:message code="login.link.text" default="Login"/></g:link>
            </shiro:isNotLoggedIn>
          </div>
        <div class="clear"></div>
        
        <g:render template="/layouts/navigation" />

        <g:layoutBody />
      </div>
      <g:render template="/layouts/footer"/> 
    </body>
</html>
