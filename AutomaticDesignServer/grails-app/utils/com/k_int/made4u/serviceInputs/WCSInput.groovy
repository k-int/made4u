package com.k_int.made4u.serviceInputs

import com.k_int.made4u.lens.LensType;
import java.text.ParseException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log

/**
 * Class to represent and parse optical prescription information
 * @author rpb rich@k-int.com
 * @version 1.0 28.10.11
 */
class WCSInput {
	
    def jobNumber;
    def inputStructure;
    
    private static Log log = LogFactory.getLog(WCSInput.class);
    
    def WCSInput() {}
    
    static WCSInput parseInput(File wcsInputFile) throws ParseException {
        def retVal = new WCSInput();
        
        // Read the file line by line and parse out what we want from it
        if ( wcsInputFile && wcsInputFile.exists() ) {
            wcsInputFile.eachLine {
                line -> 
                    def trimmed = line.trim();

                    log.debug("trimmed wcs input file line = " + trimmed);
                    System.err.println("trimmed wcs input file line = " + trimmed);
                    
                    if ( trimmed =~ /^orderNumber/ ) {
                        int index = trimmed.indexOf(":");
                        retVal.jobNumber = trimmed.substring(index+1);
                    } else {
                        retVal.inputStructure = line;
                    }
            }
        } else {
            System.err.println("No input file specified or it doesn't exist!");
        }
        
        log.debug("jobNumber parsed to be: " + retVal.jobNumber + " and inputStructure: " + retVal.inputStructure);
        System.err.println("jobNumber parsed to be: " + retVal.jobNumber + " and inputStructure: " + retVal.inputStructure);
        
        return retVal;

    }
}

