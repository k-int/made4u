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
class OpticalPrescription {
	
    def initials;
    def vmap;
    def lensType = [null,null];
    def ax = [null,null];
    def addition = [null,null];
    def cylinder = [null,null];
    def prism = [null,null]; 
    def prva = [null,null];
    def sphere = [null,null];
    def job;
    
    private static Log log = LogFactory.getLog(OpticalPrescription.class);
    
    def OpticalPrescription() {}
    
    def getDesignId() {
        def retval = "";
        
        if ( "MF".equals(getCombinedLensType()) ) {
            // A monofocal lens
            retval = "04";
        } else {
            // A progressive lens
            retval = "02";
        }
        
        return retval;
    }
    
    def getCombinedLensType() {
        
        def retval = "PSV";
        
        if ( lensType[0] == null || "MF".equals(lensType[0]) || "SV".equals(lensType[0]) 
            || lensType[1] == null || "MF".equals(lensType[1]) || "SV".equals(lensType[1]) ) {
            retval = "MF";
        }
        
        return retval;
    }
    
    static OpticalPrescription parsePrescription(File prescriptionFile) throws ParseException {
        def retVal = new OpticalPrescription();
        
        // Read the file line by line and parse out what we want from it
        if ( prescriptionFile && prescriptionFile.exists() ) {
            prescriptionFile.eachLine {
                line -> 
                    def trimmed = line.trim();
                    
                    if ( trimmed =~ /^_INITIALS/ ) {
                        int index = trimmed.indexOf("=");
                        retVal.initials = trimmed.substring(index+1);
                    } else if ( trimmed =~ /^_VMAP/ ) {
                        int index = trimmed.indexOf("=");
                        retVal.vmap = trimmed.substring(index+1);
                    } else if ( trimmed =~ /^LTYP/ ) {
                        int index = trimmed.indexOf("=");
                        String bothVals = trimmed.substring(index+1);
                        String[] separatedVals = separateVals(bothVals);
                        retVal.lensType[0] = separatedVals[0];
                        retVal.lensType[1] = separatedVals[1];
                    } else if ( trimmed =~ /^AX/ ) {
                        int index = trimmed.indexOf("=");
                        String bothVals = trimmed.substring(index+1);
                        String[] separatedVals = separateVals(bothVals);
                        retVal.ax[0] = new Float(separatedVals[0]);
                        retVal.ax[1] = new Float(separatedVals[1]);
                    } else if ( trimmed =~ /^ADD/ ) {
                        int index = trimmed.indexOf("=");
                        String bothVals = trimmed.substring(index+1);
                        String[] separatedVals = separateVals(bothVals);
                        retVal.addition[0] = new Float(separatedVals[0]);
                        retVal.addition[1] = new Float(separatedVals[1]);
                    } else if ( trimmed =~ /^CYL/ ) {
                        int index = trimmed.indexOf("=");
                        String bothVals = trimmed.substring(index+1);
                        String[] separatedVals = separateVals(bothVals);
                        retVal.cylinder[0] = new Float(separatedVals[0]);
                        retVal.cylinder[1] = new Float(separatedVals[1]);
                    } else if ( trimmed =~ /^PRVM/ ) {
                        int index = trimmed.indexOf("=");
                        String bothVals = trimmed.substring(index+1);
                        String[] separatedVals = separateVals(bothVals);
                        retVal.prism[0] = new Float(separatedVals[0]);
                        retVal.prism[1] = new Float(separatedVals[1]);
                    } else if ( trimmed =~ /^PRVA/ ) {
                        int index = trimmed.indexOf("=");
                        String bothVals = trimmed.substring(index+1);
                        String[] separatedVals = separateVals(bothVals);
                        retVal.prva[0] = new Float(separatedVals[0]);
                        retVal.prva[1] = new Float(separatedVals[1]);
                    } else if ( trimmed =~ /^SPH/ ) {
                        int index = trimmed.indexOf("=");
                        String bothVals = trimmed.substring(index+1);
                        String[] separatedVals = separateVals(bothVals);
                        retVal.sphere[0] = new Float(separatedVals[0]);
                        retVal.sphere[1] = new Float(separatedVals[1]);
                    } else if ( trimmed =~ /^JOB/ ) {
                        int index = trimmed.indexOf("=");
                        retVal.job = trimmed.substring(index+1);
                    }
            }
        }
        
        
        return retVal;

    }
    
    private static String[] separateVals(input) throws ParseException {
        String[] output = [null,null];
        if ( input.contains(";") ) {
            output = input.split(";");
        } else if ( input.contains(":") ) {
            output = input.split(":");
        } else {
            throw(new ParseException("Unable to separate a pair of values in a prescription. Input value: " + input))
        }
        
        return output;
    }
    
    def toOmaOutput() {
        
        StringBuilder omaBuilder = new StringBuilder();
        
        if ( this.initials )
            omaBuilder.append("\n_INITIALS=").append(this.initials);
        if ( this.vmap )
            omaBuilder.append("\n_VMAP=").append(this.vmap);
        if ( this.lensType && this.lensType[0] != null && this.lensType[1] != null )
            omaBuilder.append("\nLTYP=").append(this.lensType[0]).append(";").append(this.lensType[1]);
        if ( this.job )
            omaBuilder.append("\nJOB=").append(this.job);
        if ( this.sphere && this.sphere[0] != null && this.sphere[1] != null)
            omaBuilder.append("\nSPH=").append(this.sphere[0]).append(";").append(this.sphere[1]);
        if ( this.cylinder && this.cylinder[0] != null && this.cylinder[1] != null)
            omaBuilder.append("\nCYL=").append(this.cylinder[0]).append(";").append(this.cylinder[1]);
        if ( "PSV".equals(this.getCombinedLensType()) ) {
            if ( this.addition && this.addition[0] != null && this.addition[1] != null )
                omaBuilder.append("\nADD=").append(this.addition[0]).append(";").append(this.addition[1]);
        } else {
            omaBuilder.append("\nADD=0.00;0.00");
        }
        if ( this.ax && this.ax[0] != null && this.ax[1] != null )
            omaBuilder.append("\nAX=").append(this.ax[0]).append(";").append(this.ax[1]);
        if ( this.prva && this.prva[0] != null && this.prva[1] != null )
            omaBuilder.append("\nPRVA=").append(this.prva[0]).append(";").append(this.prva[1]);
        if ( this.prism && this.prism[0] != null && this.prism[1] != null )
            omaBuilder.append("\nPRVM=").append(this.prism[0]).append(";").append(this.prism[1]);
        
        
        log.debug("About to return OMA output: " + omaBuilder.toString());
        
        return omaBuilder.toString();
    }
    
    def toWCSInput() {
        
        StringBuilder retvalBuilder = new StringBuilder();
        retvalBuilder.append("{\"_INITIALS\":\"").append(this.initials).append("\",");
        retvalBuilder.append("\"_VMAP\":\"").append(this.vmap).append("\",");
        retvalBuilder.append("\"LTYP\":\"").append(this.lensType[0]).append(";").append(this.lensType[1]).append("\",");
        retvalBuilder.append("\"AX\":\"").append(this.ax[0]).append(";").append(this.ax[1]).append("\",");
        retvalBuilder.append("\"ADD\":\"").append(this.addition[0]).append(";").append(this.addition[1]).append("\",");
        retvalBuilder.append("\"CYL\":\"").append(this.cylinder[0]).append(";").append(this.cylinder[1]).append("\",");
        retvalBuilder.append("\"PRVA\":\"").append(this.prva[0]).append(";").append(this.prva[1]).append("\",");
        retvalBuilder.append("\"PRVM\":\"").append(this.prism[0]).append(";").append(this.prism[1]).append("\",");
        retvalBuilder.append("\"SPH\":\"").append(this.sphere[0]).append(";").append(this.sphere[1]).append("\",");
        retvalBuilder.append("\"JOB\":\"").append(this.job).append("\"}");
        
        
        
//        
//        def retval = ["_INITIALS":this.initials,"_VMAP":this.vmap,"LTYP":this.lensType[0] +";"+this.lensType[1],
//            "AX":this.ax,"ADD":this.addition[0]+":"+this.addition[1],"CYL":this.cylinder[0]+";"+this.cylinder[1],
//            "PRVA":this.prva[0]+";"+this.prva[1], "PRVM":this.prism[0]+";"+this.prism[1], 
//            "SPH":this.sphere[0]+";"+this.sphere[1], "JOB": this.job];
        
        return retvalBuilder.toString();
    }
    
    def toOutput() {
        
        StringBuilder outputBuilder = new StringBuilder();
        
        outputBuilder.append("sphere[0,1]: ").append(this.sphere[0]).append(",").append(this.sphere[1]);
        outputBuilder.append(" cylinder[0,1]: ").append(this.cylinder[0]).append(",").append(this.cylinder[1]);
        outputBuilder.append(" addition[0,1]: ").append(this.addition[0]).append(",").append(this.addition[1]);
        outputBuilder.append(" prism[0,1]: ").append(this.prism[0]).append(",").append(this.prism[1]);
        
        return outputBuilder.toString();
    }
}

