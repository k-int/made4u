<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'workflow.show.page.head.title', default: 'Workflow details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'workflow.show.title', default: 'Workflow details')}</h1>
            
            <h2>${message(code: 'workflow.show.type.heading', default: 'Workflow type')}</h2>
            ${workflow[0].value}
            
            <h2>${message(code: 'workflow.show.workflow.heading', default: 'Workflow')}</h2>
            <img src="${createLinkTo(dir: 'images/workflows', file: workflow[1])}" width="100%"/>
        </div>

  </body>
</html>
