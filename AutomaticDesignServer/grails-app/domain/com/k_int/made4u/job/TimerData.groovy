package com.k_int.made4u.job

/**
 * A domain class to represent the manufacturing order and get design timer information
 * @author rpb rich@k-int.com
 * @version 1.0 04.10.11
 */
class TimerData {
    
    Date            lastRunTime;
    TimerStatus     timerStatus = TimerStatus.IDLE;
    TimerType       timerType;
    Long            runInterval;
    
    static constraints = {
        lastRunTime                     (nullable: true)
        timerStatus                     (nullable: false)
        timerType                       (nullable: false)
        runInterval                     (nullable: false)
    }

    static mapping = {
        table 'M4U_TIMER_DATA'
    }
    
	
}

