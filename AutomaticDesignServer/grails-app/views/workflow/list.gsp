<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'workflow.list.page.head.title', default: 'Workflows')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'workflow.list.title', default: 'Implemented workflows')}</h1>

            <ul>
                    <g:each in="${workflows}" status="i" var="workflowInstance">
                        <li><g:link action="show" id="${i}">${workflowInstance[0].value}</g:link></li>
                    </g:each>

            </ul>
            
        </div>

  </body>
</html>
