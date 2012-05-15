package com.k_int.made4u.oma

import java.io.File;

/**
 * A class to handle access to and representation of OMA files
 * @author rpb rich@k-int.com
 * @version 1.0 08.09.11
 */
class OMA {
	
    def leftSphere;
    def rightSphere;
    def leftCylinder;
    def rightCylinder;
    def leftAddition;
    def rightAddition;
    def leftPrism;
    def rightPrism;
    def leftLensType;
    def rightLensType;
    
    
    def getLeftSphere() {
        return leftSphere;
    }
    
    def getRightSphere() {
        return rightSphere;
    }
    
    def getLeftCylinder() {
        return leftCylinder;
    }

    def getRightCylinder() {
        return rightCylinder;
    }
    
    def getLeftAddition() {
        return leftAddition;
    }

    def getRightAddition() {
        return rightAddition;
    }

    def getLeftPrism() {
        return leftPrism;
    }

    def getRightPrism() {
        return rightPrism;
    }
    
    def getLeftLensType() {
        return leftLensType;
    }
    
    def getRightLensType() {
        return rightLensType;
    }

    static OMA parseOMA(File omaFile) {
    
        def retVal = new OMA();
        
        // Read the file line by line and parse out what we want from it
        if ( omaFile && omaFile.exists() ) {
            omaFile.eachLine {
                line -> 
                    if ( line =~ /^SPH/ ) {
                        // Sphere values
                        int index = line.indexOf("=");
                        String bothVals = line.substring(index+1);
                        String[] separatedVals = bothVals.split(";");
                        retVal.leftSphere = new Float(separatedVals[0]);
                        retVal.rightSphere = new Float(separatedVals[1]);
                    } else if ( line =~ /^CYL/ ) {
                        // Cylinder values
                        int index = line.indexOf("=");
                        String bothVals = line.substring(index+1);
                        String[] separatedVals = bothVals.split(";");
                        retVal.leftCylinder = new Float(separatedVals[0]);
                        retVal.rightCylinder = new Float(separatedVals[1]);
                    } else if ( line =~ /^ADD/ ) {
                        // Addition values
                        int index = line.indexOf("=");
                        String bothVals = line.substring(index+1);
                        String[] separatedVals = bothVals.split(";");
                        retVal.leftAddition = new Float(separatedVals[0]);
                        retVal.rightAddition = new Float(separatedVals[1]);
                    } else if ( line =~ /^PRVM/ ) {
                        // Prism values
                        int index = line.indexOf("=");
                        String bothVals = line.substring(index+1);
                        String[] separatedVals = bothVals.split(";");
                        retVal.leftPrism = new Float(separatedVals[0]);
                        retVal.rightPrism = new Float(separatedVals[1]);
                    } else if ( line =~ /^LTYP/ ) {
                        // Lens type values
                        int index = line.indexOf("=");
                        String bothVals = line.substring(index+1);
                        String[] separatedVals = bothVals.split(";");
                        retVal.leftLensType = separatedVals[0];
                        retVal.rightLensType = separatedVals[1];
                    }
            }
        }
        
        return retVal;
    }
    
}

