<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'timer.list.page.head.title', default: 'Timer data - Configured timers')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'timer.list.title', default: 'Configured timers')}</h1>

            <table>
              <thead>
                    <tr>
                      <g:sortableColumn property="id" title="${message(code: 'timer.id.label', default: 'ID')}" />
                      <g:sortableColumn property="timerType" title="${message(code: 'timer.timer.type.label', default: 'Timer type')}"/>
                      <g:sortableColumn property="timerStatus" title="${message(code: 'timer.timer.status.label', default: 'Status')}" />
                      <g:sortableColumn property="lastRunTime" title="${message(code: 'timer.last.run.time.label', default: 'Last run time')}" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${timers}" status="i" var="timerInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${timerInstance.id}">${timerInstance.id}</g:link></td>
                        <td><g:link action="show" id="${timerInstance.id}">${timerInstance.timerType.value}</g:link></td>
                        <td><g:link action="show" id="${timerInstance.id}">${timerInstance.timerStatus.value}</g:link></td>
                        <td><g:link action="show" id="${timerInstance.id}">${timerInstance.lastRunTime}</g:link></td>
                      </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
              <g:paginate action="list" total="${totalNum}" />
            </div>
            
            
        </div>

  </body>
</html>
