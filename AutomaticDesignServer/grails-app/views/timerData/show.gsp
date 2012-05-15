<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>${message(code: 'timer.show.page.head.title', default: 'Timer details')} &lt;&lt; Made4U</title>

    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript library="jquery-validation/jquery.validate" plugin="jquery-validation"/>
    
  </head>
  <body>

 

        <div id="pageBody">
            <h1>${message(code: 'timer.show.title', default: 'Timer details')}</h1>
            
            <g:if test="${flash.error}">
              <div class="errors">
                <ul>
                  <li>${flash.error}</li>
                </ul>
              </div>
            </g:if>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>


            
            <h2>${message(code: 'timer.show.id.heading', default: 'ID:')}</h2>
            <p>${timer.id}</p>
            
            <h2>${message(code: 'timer.show.timer.type.heading', default: 'Timer type:')}</h2>
            <p>${timer.timerType.value}</p>
            
            <h2>${message(code: 'timer.show.timer.status.heading', default: 'Timer status:')}</h2>
            <p>${timer.timerStatus.value}</p>
                        
            <h2>${message(code: 'timer.show.last.run.time.heading', default: 'Last run time:')}</h2>
            <p>${timer.lastRunTime}</p>
            
            <h2>${message(code: 'timer.show.timer.run.interval.heading', default: 'Timer interval:')}</h2>
            <p>${timer.runInterval} (ms)</p>
            
            <div class="buttonArea">
              <a class="button" title="${message(code: 'timer.show.reset.button.label', default: 'Reset timer')}" href="update?status=IDLE">${message(code: 'timer.show.reset.button.text', default: 'Reset timer')}</a>
            </div>
            
        </div>

  </body>
</html>
