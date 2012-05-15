package com.k_int.made4u.customer

import com.k_int.made4u.frame.Frame;
import com.k_int.made4u.frame.FrameBaseColour;
import com.k_int.made4u.frame.FrameTexture;
import com.k_int.made4u.frame.FinishingAspect;
import com.k_int.made4u.lens.LensType;
import com.k_int.made4u.lens.LensMaterial;
import com.k_int.made4u.lens.LensMaterialType;
import com.k_int.made4u.lens.LensColour;
import com.k_int.made4u.lens.LensSpecialColour;
import com.k_int.made4u.lens.LensSpecialColourType;
import com.k_int.made4u.lens.Coating;
import com.k_int.made4u.lens.CoatingType;
import java.io.File;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * A class to handle access to and representation of customer choices
 * @author rpb rich@k-int.com
 * @version 1.0 18.10.11
 */
class CustomerChoices {

    private static final Log log = LogFactory.getLog(CustomerChoices.class);
    
    String frameId;
    String jobNumber;
    String frontalLensThickness;
    FinishingAspect frontalFinishingAspect;
    String frontalBaseColour;
    String frontalTexture;
    FinishingAspect leftTempleFinishingAspect;
    String leftTempleBaseColour;
    String leftTempleTexture;
    FinishingAspect rightTempleFinishingAspect;
    String rightTempleBaseColour;
    String rightTempleTexture;
    LensType lensFocalType;
    String lensDesignId;
    String lensDesignName;
    String lensMaterialId;
    String lensMaterialName;
    String lensND;
    String lensMDThickness;
    String lensMCThickness;
    String lensMEThickness;
    boolean lensARFilter;
    boolean lensPolarMaterial;
    boolean lensMirrorFilter;
    boolean lensPhotochromicMaterial;
    String lensColourName;
    String lensARColourName;
    String lensPolarColourName;
    String lensMRColourName;
    String lensPhotochromicColourName;
    String lensMirrorBaseColourName;
    String lensTopcoat;

    def getFrameId() { return frameId; }
    def setFrameId(frameId) { this.frameId = frameId; }
    def getJobNumber() { return jobNumber; }
    def setJobNumber(jobNumber) { this.jobNumber = jobNumber; }
    def getFrontalLensThickness() { return frontalLensThickness; }
    def setFrontalLensThickness(frontalLensThickness) { this.frontalLensThickness = frontalLensThickness; }
    def getFrontalFinishingAspect() { return frontalFinishingAspect; }
    def setFrontalFinishingAspect(frontalFinishingAspect) { this.frontalFinishingAspect = frontalFinishingAspect; }
    def getFrontalBaseColour() { return frontalBaseColour; }
    def setFrontalBaseColour(frontalBaseColour) { this.frontalBaseColour = frontalBaseColour; }
    def getFrontalTexture() { return frontalTexture; }
    def setFrontalTexture(frontalTexture) { this.frontalTexture = frontalTexture; }
    def getLeftTempleFinishingAspect() { return leftTempleFinishingAspect; }
    def setLeftTempleFinishingAspect(leftTempleFinishingAspect) { this.leftTempleFinishingAspect = leftTempleFinishingAspect; }
    def getLeftTempleBaseColour() { return leftTempleBaseColour; }
    def setLeftTempleBaseColour(leftTempleBaseColour) { this.leftTempleBaseColour = leftTempleBaseColour; }
    def getLeftTempleTexture() { return leftTempleTexture; }
    def setLeftTempleTexture(leftTempleTexture) { this.leftTempleTexture = leftTempleTexture; }
    def getRightTempleFinishingAspect() { return rightTempleFinishingAspect; }
    def setRightTempleFinishingAspect(rightTempleFinishingAspect) { this.rightTempleFinishingAspect = rightTempleFinishingAspect; }
    def getRightTempleBaseColour() { return rightTempleBaseColour; }
    def setRightTempleBaseColour(rightTempleBaseColour) { this.rightTempleBaseColour = rightTempleBaseColour; }
    def getRightTempleTexture() { return rightTempleTexture; }
    def setRightTempleTexture(rightTempleTexture) { this.rightTempleTexture = rightTempleTexture; }
    def getLensFocalType() { return lensFocalType; }
    def setLensFocalType(lensFocalType) { this.lensFocalType = lensFocalType; }
    def getLensDesignId() { return lensDesignId; }
    def setLensDesignId(lensDesignId) { this.lensDesignId = lensDesignId; }
    def getLensDesignName() { return lensDesignName; }
    def setLensDesignName(lensDesignName) { this.lensDesignName = lensDesignName; }
    def getLensMaterialId() { return lensMaterialId; }
    def setLensMaterialId(lensMaterialId) { this.lensMaterialId = lensMaterialId; }
    def getLensMaterialName() { return lensMaterialName; }
    def setLensMaterialName(lensMaterialName) { this.lensMaterialName = lensMaterialName; }
    def getLensND() { return lensND; }
    def setLensND(lensND) { this.lensND = lensND; }
    def getLensMDThickness() { return lensMDThickness; }
    def setLensMDThickness(lensMDThickness) { this.lensMDThickness = lensMDThickness; }
    def getLensMCThickness() { return lensMCThickness; }
    def setLensMCThickness(lensMCThickness) { this.lensMCThickness = lensMCThickness; }
    def getLensMEThickness() { return lensMEThickness; }
    def setLensMEThickness(lensMEThickness) { this.lensMEThickness = lensMEThickness; }
    def getLensARFilter() { return lensARFilter; }
    def setLensARFilter(lensARFilter) { this.lensARFilter = lensARFilter; }
    def getLensPolarMaterial() { return lensPolarMaterial; }
    def setLensPolarMaterial(lensPolarMaterial) { this.lensPolarMaterial = lensPolarMaterial; }
    def getLensMirrorFilter() { return lensMirrorFilter; }
    def setLensMirrorFilter(lensMirrorFilter) { this.lensMirrorFilter = lensMirrorFilter; }
    def getLensPhotochromicMaterial() { return lensPhotochromicMaterial; }
    def setLensPhotochromicMaterial(lensPhotochromicMaterial) { this.lensPhotochromicMaterial = lensPhotochromicMaterial; }
    def getLensColourName() { return lensColourName; }
    def setLensColourName(lensColourName) { this.lensColourName = lensColourName; }
    def getLensARColourName() { return lensARColourName; }
    def setLensARColourName(lensARColourName) { this.lensARColourName = lensARColourName; }
    def getLensPolarColourName() { return lensPolarColourName; }
    def setLensPolarColourName(lensPolarColourName) { this.lensPolarColourName = lensPolarColourName; }
    def getLensMRColourName() { return lensMRColourName; }
    def setLensMRColourName(lensMRColourName) { this.lensMRColourName = lensMRColourName; }
    def getLensPhotchromicColourName() { return lensPhotochromicColourName; }
    def setLensPhotochromicColourName(lensPhotochromicColourNameIn) { this.lensPhotochromicColourName = lensPhotochromicColourNameIn; }
    def getLensMirrorBaseColourName() { return lensMirrorBaseColourName; }
    def setLensMirrorBaseColourName(lensMirrorBaseColourName) { this.lensMirrorBaseColourName = lensMirrorBaseColourName; }
    def getLensTopcoat() { return lensTopcoat; }
    def setLensTopcoat(lensTopcoat) { this.lensTopcoat = lensTopcoat; }
    
    
    /**
     * Get the actual lens material from the database based on the information in the
     * customer choices 
     */
    def getLensMaterialFromDb() {
        
        def lensMaterial = LensMaterial.findByIdentifierAndFocalType(this.lensMaterialId, this.getActualFocalType());
        
        return lensMaterial;
    }
    
    /**
     * Work out the actual focal type based on the strings passed in as part of the customer choices
     */
    def getActualFocalType() {
        def returnVal = LensType.MONOFOCAL;
        
        if ( this.lensFocalType && "PROGRESSIVE".equalsIgnoreCase(this.lensFocalType) ) {
            returnVal = LensType.PROGRESSIVE;
        } 
        
        return returnVal;
    }
    
    static CustomerChoices parseFile(File customerFile) {
        
        def retval = parseText(customerFile.text);
        
        return retval;
    }
    
    static CustomerChoices parseText(String customerFileContents) {
        def retVal = new CustomerChoices();
        
        // Read the file and parse out all of the information we want
        def custData = new XmlSlurper().parseText(customerFileContents).declareNamespace(m4u: "http://www.made4u.info/schema/customerSelection");
        log.debug("About to loop through the customer XML file");
        
        log.debug("custData.size = " + custData.size());
        log.debug("custData.DesignSelection.size = " + custData.DesignSelection.size());
        log.debug("custData.DesignSelection.Frame.size = " + custData.Frame.size());
        
        custData.Frame.each {
            log.debug("it.size = " + it.size());
            
            retVal.setFrameId(it.General.@FrameID?.text());
            retVal.setJobNumber(it.General.@JobNumber.text());
            retVal.setFrontalLensThickness(it.FrontalInformation.@MLensThickness.text());
            retVal.setFrontalFinishingAspect(FinishingAspect.parseFromString(it.FrontalInformation.@FinishingAspect.text()));
            retVal.setFrontalBaseColour(it.FrontalInformation.@BaseColor.text());
            retVal.setFrontalTexture(it.FrontalInformation.@Texture.text());
            retVal.setLeftTempleFinishingAspect(FinishingAspect.parseFromString(it.LeftTempleInformation.@FinishingAspect.text()));
            retVal.setLeftTempleBaseColour(it.LeftTempleInformation.@BaseColor.text());
            retVal.setLeftTempleTexture(it.LeftTempleInformation.@Texture.text());
            retVal.setRightTempleFinishingAspect(FinishingAspect.parseFromString(it.RightTempleInformation.@FinishingAspect.text()));
            retVal.setRightTempleBaseColour(it.RightTempleInformation.@BaseColor.text());
            retVal.setRightTempleTexture(it.RightTempleInformation.@Texture.text());
            retVal.setLensFocalType(LensType.parseFromString(it.Lens.@FocalType.text()));
            retVal.setLensDesignId(it.Lens.@DesignID.text());
            retVal.setLensDesignName(it.Lens.@DesignName.text());
            retVal.setLensMaterialId(it.Lens.@MaterialID.text());
            retVal.setLensMaterialName(it.Lens.@MaterialName.text());
            retVal.setLensND(it.Lens.@ND.text());
            retVal.setLensMDThickness(it.Lens.@MDthickness.text());
            retVal.setLensMCThickness(it.Lens.@MCthickness.text());
            retVal.setLensMEThickness(it.Lens.@MEthickness.text());
            def arFilter = it.Lens.@ArFilter.text();
            if ( arFilter != null && "Yes".equalsIgnoreCase(arFilter) ) {
                retVal.setLensARFilter(true);
            } else {
                retVal.setLensARFilter(false);
            }                
            def polarMat = it.Lens.@PolarMaterial.text();
            if ( polarMat != null && "Yes".equalsIgnoreCase(polarMat) ) {
                retVal.setLensPolarMaterial(true);
            } else {
                retVal.setLensPolarMaterial(false);
            }                
            def mirrorFilter = it.Lens.@MirrorFilter.text();
            if ( mirrorFilter != null && "Yes".equalsIgnoreCase(mirrorFilter) ) {
                retVal.setLensMirrorFilter(true);
            } else {
                retVal.setLensMirrorFilter(false);
            }                
            def photoMat = it.Lens.@PhotochromicMaterial.text();
            if ( photoMat != null && "Yes".equalsIgnoreCase(photoMat) ) {
                retVal.setLensPhotochromicMaterial(true);
            } else {
                retVal.setLensPhotochromicMaterial(false);
            }                
            
            retVal.setLensColourName(it.Lens.@ColorName.text());
            retVal.setLensARColourName(it.Lens.@ARColorName.text());
            retVal.setLensPolarColourName(it.Lens.@PolarColorName.text());
            retVal.setLensPhotochromicColourName(it.Lens.@PhotochromicColorName.text());
            def mrCol = it.Lens.@MRColorName.text();
            def mirrorCol = it.Lens.@MirrorColorName.text();
            if ( mrCol && !"".equals(mrCol.trim()) )
                retVal.setLensMRColourName(mrCol);
            else if ( mirrorCol && !"".equals(mirrorCol.trim()) )
                retVal.setLensMRColourName(mirrorCol);

            retVal.setLensMirrorBaseColourName(it.Lens.@MirrorBaseColorName.text());
            def tempTc = it.Lens.@TopCoat.text();
            if ( tempTc && !"".equals(tempTc.trim()) )
                retVal.setLensTopcoat(tempTc.trim());
            
        }
        
        
        // Default in some values if they haven't been set in the customer choices file..
        if ( retVal.lensMirrorFilter ) {
            if ( retVal.lensARColourName == null || "".equals(retVal.lensARColourName) ) {
                
                if ( !"Red".equals(retVal.lensMRColourName) && !"Grey".equals(retVal.lensMRColourName) ) {
                    retVal.lensARColourName = retVal.lensMRColourName;
                } else {
                    retVal.lensARColourName = "Green";
                }
            }
            
        }
        
        
        log.debug("returning from parsing.. this.toDebugString = "  + retVal.toDebugString());

        return retVal;
    }

    public String toDebugString() {
        StringBuilder retValBuilder = new StringBuilder();
        
        retValBuilder.append("frame id: ").append(frameId).append(" job number: ").append(jobNumber);
        retValBuilder.append(" frontalLensThickness: ").append(frontalLensThickness );
        retValBuilder.append(" frontalFinishingAspect: ").append(frontalFinishingAspect );
        retValBuilder.append(" frontalBaseColour: ").append(frontalBaseColour );
        retValBuilder.append(" frontalTexture: ").append(frontalTexture);
        retValBuilder.append(" leftTempleFinishingAspect: ").append(leftTempleFinishingAspect);
        retValBuilder.append(" leftTempleBaseColour: ").append(leftTempleBaseColour);
        retValBuilder.append(" leftTempleTexture: ").append(leftTempleTexture);
        retValBuilder.append(" rightTempleFinsihingAspect: ").append(rightTempleFinishingAspect);
        retValBuilder.append(" rightTempleBaseColour: ").append(rightTempleBaseColour);
        retValBuilder.append(" rightTempleTexture: ").append(rightTempleTexture);
        retValBuilder.append(" lensFocalType: ").append(lensFocalType);
        retValBuilder.append(" lensDesignId: ").append(lensDesignId);
        retValBuilder.append(" lensDesignName: ").append(lensDesignName);
        retValBuilder.append(" lensMaterialId: ").append(lensMaterialId);
        retValBuilder.append(" lensMaterialName: ").append(lensMaterialName);
        retValBuilder.append(" lensND: ").append(lensND);
        retValBuilder.append(" lensMDThickness: ").append(lensMDThickness);
        retValBuilder.append(" lensMCThickness: ").append(lensMCThickness);
        retValBuilder.append(" lensMEThickenss: ").append(lensMEThickness);
        retValBuilder.append(" lensARFilter: ").append(lensARFilter);
        retValBuilder.append(" lensColourName: ").append(lensColourName);
        retValBuilder.append(" lensARColourName: ").append(lensARColourName);
        retValBuilder.append(" lensMRColourName: ").append(lensMRColourName);
        retValBuilder.append(" lensMirrorBaseColourName: ").append(lensMirrorBaseColourName);
        retValBuilder.append(" lensTopcoat: ").append(lensTopcoat);
        retValBuilder.append(" lensPolarMaterial: ").append(lensPolarMaterial);
        retValBuilder.append(" lensMirrorFilter: ").append(lensMirrorFilter);
        retValBuilder.append(" lensPhotochromicMaterial: ").append(lensPhotochromicMaterial);
        retValBuilder.append(" lensPolarColourName: ").append(lensPolarColourName);
        retValBuilder.append(" photochromicColourName: ").append(lensPhotochromicColourName);

        
        return retValBuilder.toString();
    }
    
    public String toFrameCalcInputString(namespacePrefix, ftpPrefix) {
        StringBuilder retValBuilder = new StringBuilder();
        
        retValBuilder.append("<").append(namespacePrefix).append(":frame_request>\n");

        retValBuilder.append("<").append(namespacePrefix).append(":design_name>").append(frameId).append("</").append(namespacePrefix).append(":design_name>\n");
        retValBuilder.append("<").append(namespacePrefix).append(":right_temple_base_color>").append(rightTempleBaseColour).append("</").append(namespacePrefix).append(":right_temple_base_color>\n");
        retValBuilder.append("<").append(namespacePrefix).append(":left_temple_base_color>").append(leftTempleBaseColour).append("</").append(namespacePrefix).append(":left_temple_base_color>\n");
        retValBuilder.append("<").append(namespacePrefix).append(":frontal_base_color>").append(frontalBaseColour).append("</").append(namespacePrefix).append(":frontal_base_color>\n");
        retValBuilder.append("<").append(namespacePrefix).append(":right_temple_finishing>").append(rightTempleFinishingAspect).append("</").append(namespacePrefix).append(":right_temple_finishing>\n");
        retValBuilder.append("<").append(namespacePrefix).append(":left_temple_finishing>").append(leftTempleFinishingAspect).append("</").append(namespacePrefix).append(":left_temple_finishing>\n");
        retValBuilder.append("<").append(namespacePrefix).append(":frontal_finishing>").append(frontalFinishingAspect).append("</").append(namespacePrefix).append(":frontal_finishing>\n");
        if ( rightTempleTexture && !"".equals(rightTempleTexture.trim()) ) {
            retValBuilder.append("<").append(namespacePrefix).append(":right_temple_texture href=\"").append(ftpPrefix).append(rightTempleTexture).append("\">");
            retValBuilder.append(ftpPrefix).append(rightTempleTexture).append("</").append(namespacePrefix).append(":right_temple_texture>\n");
        } else {
            retValBuilder.append("<").append(namespacePrefix).append(":right_temple_texture href=\"\">").append("</").append(namespacePrefix).append(":right_temple_texture>\n");
        }
        if ( leftTempleTexture && !"".equals(leftTempleTexture.trim()) ) {
            retValBuilder.append("<").append(namespacePrefix).append(":left_temple_texture href=\"").append(ftpPrefix).append(leftTempleTexture).append("\">");
            retValBuilder.append(ftpPrefix).append(leftTempleTexture).append("</").append(namespacePrefix).append(":left_temple_texture>\n");
        } else {
            retValBuilder.append("<").append(namespacePrefix).append(":left_temple_texture href=\"\">").append("</").append(namespacePrefix).append(":left_temple_texture>\n");
        }
        
        retValBuilder.append("</").append(namespacePrefix).append(":frame_request>\n");

        
        return retValBuilder.toString();
    }
    
    public String toCoatingsFile() {
        
        log.debug("About to set up the coatings from the customer choices.. This is what I have for customer choices: " + this.toDebugString());
        StringBuilder retvalBuilder = new StringBuilder();
        
        // Add in lens colour cc
        if ( this.lensMirrorFilter ) {
            // A mirror lens..
            retvalBuilder.append("_LENS_COLOR_CC=").append(this.lensMirrorBaseColourName);
        } else {
            // Not a mirror lens - do we have a colour for the lens?
            if ( this.lensColourName && !"".equals(this.lensColourName) ) {
                // We have a colour
                retvalBuilder.append("_LENS_COLOR_CC=").append(this.lensColourName);
            } else {
                // No colour
                retvalBuilder.append("_LENS_COLOR_CC=Uncoated");
            }
        }
        
        // Lens coating CC
        if ( this.lensARFilter ) {
            // We have an AR coating..
            retvalBuilder.append("\n").append("_LENS_COATING_CC=AR_").append(this.lensARColourName);
        } else {
            if ( this.lensMirrorFilter ) {
                // we have a mirror finish - rely on the default being set already
                retvalBuilder.append("\n").append("_LENS_COATING_CC=AR_").append(this.lensARColourName); 
            } else {
                // Not mirror coated - shouldn't ever get here, but just in case..
                retvalBuilder.append("\n").append("_LENS_COATING_CC=AR_Green");
            }
        }
        
        // Lens coating CX
        if ( this.lensMirrorFilter ) {
            // A mirror lens..
            retvalBuilder.append("\n_LENS_COATING_CX=MR_").append(this.lensMRColourName); 
        } else {
            // Not a mirror lens - are we using an AR coating?
            if ( this.lensARFilter ) {
                // We have an AR coating..
                retvalBuilder.append("\n").append("_LENS_COATING_CX=AR_").append(this.lensARColourName);
            } else {
                retvalBuilder.append("\n").append("_LENS_COATING_CX=Uncoated");
            }
        }
        
        // Lens topcoat
        if ( this.lensTopcoat )
            retvalBuilder.append("\n_LENS_TOPCOAT=TC_").append(this.lensTopcoat);
        else
            retvalBuilder.append("\n_LENS_TOPCOAT=TC_1");
        
        return retvalBuilder.toString();
        
    }

    public String toCoatingsWCSData() {
        
        StringBuilder retvalBuilder = new StringBuilder();
        
        // Add in lens colour cc
        if ( this.lensMirrorFilter ) {
            // A mirror lens..
            retvalBuilder.append("\"_LENS_COLOR_CC\":\"").append(this.lensMirrorBaseColourName).append("\"");
        } else {
            // Not a mirror lens - do we have a colour for the lens?
            if ( this.lensColourName && !"".equals(this.lensColourName) ) {
                // We have a colour
                retvalBuilder.append("\"_LENS_COLOR_CC\":\"").append(this.lensColourName).append("\"");
            } else {
                // No colour
                retvalBuilder.append("\"_LENS_COLOR_CC\":\"Uncoated\"");
            }
        }
        
        // Lens coating CC
        if ( this.lensARFilter ) {
            // We have an AR coating..
            retvalBuilder.append(",\"_LENS_COATING_CC\":\"AR_").append(this.lensARColourName).append("\"");
        } else {
            if ( this.lensMirrorFilter ) {
                // we have a mirror finish - rely on the default being set already
                retvalBuilder.append(",\"_LENS_COATING_CC\":\"AR_").append(this.lensARColourName).append("\""); 
            } else {
                // Not mirror coated - shouldn't ever get here, but just in case..
                retvalBuilder.append(",\"_LENS_COATING_CC\":\"AR_Green\"");
            }
        }
        
        // Lens coating CX
        if ( this.lensMirrorFilter ) {
            // A mirror lens..
            retvalBuilder.append(",\"_LENS_COATING_CX\":\"MR_").append(this.lensMRColourName).append("\""); 
        } else {
            // Not a mirror lens - are we using an AR coating?
            if ( this.lensARFilter ) {
                // We have an AR coating..
                retvalBuilder.append(",\"_LENS_COATING_CX\":\"AR_").append(this.lensARColourName).append("\"");
            } else {
                retvalBuilder.append(",\"_LENS_COATING_CX\":\"Uncoated\"");
            }
        }
        
        // Lens topcoat
        if ( this.lensTopcoat )
            retvalBuilder.append(",\"_LENS_TOPCOAT\":\"TC_").append(this.lensTopcoat).append("\"");
        else
            retvalBuilder.append(",\"_LENS_TOPCOAT\":\"TC_1\"");
        
        return retvalBuilder.toString();
        
    }
    
    /**
     * Validate the customer's choices against the catalogue to ensure the chosen frame
     * is available and that the specified textures can be applied to that frame, etc.
     */
    def validateChoices() {
        
        log.debug("In the CustomerChoices validateChoices method");
        
        def retvalSuccess = true;
        def retvalMessages = [];
        
        def chosenFrame = getRealFrame();
        if ( !chosenFrame ) {
            retvalMessages.add("The specified frame could not be found in the catalogue");
            retvalSuccess = false;
        } else {
            // We have a frame in the catalogue - check the other choices
            
            // Get the base colours from the database
            def chosenLeftBaseColour = FrameBaseColour.findByName(this.leftTempleBaseColour);
            def chosenRightBaseColour = FrameBaseColour.findByName(this.rightTempleBaseColour);
            def chosenFrontalBaseColour = FrameBaseColour.findByName(this.frontalBaseColour);
            
            // First check the base colours exist and are tied to this frame
            if ( this.leftTempleBaseColour ) {
                if (!chosenLeftBaseColour ) {
                    retvalSuccess = false;
                    retvalMessages.add("The chosen left temple base color could not be found in the catalogue");
                } else {
                    // Colour exists - tied to frame?
                    if ( !chosenFrame.leftTempleBaseColours?.contains(chosenLeftBaseColour) ) {
                        retvalSuccess = false;
                        retvalMessages.add("The chosen left temple base color is not connected to the chosen frame in the catalogue");                        
                    }
                }
            }
            
            if ( this.rightTempleBaseColour ) {
                if ( !chosenRightBaseColour ) {
                    retvalSuccess = false;
                    retvalMessages.add("The chosen right temple base color could not be found in the catalogue");
                } else {
                    // Colour exists - tied to frame?
                    if ( !chosenFrame.rightTempleBaseColours?.contains(chosenRightBaseColour) ) {
                        retvalSuccess = false;
                        retvalMessages.add("The chosen right temple base color is not connected to the chosen frame in the catalogue");
                    }
                }
            }
            if ( this.frontalBaseColour ) {
                if ( !chosenFrontalBaseColour ) {
                    retvalSuccess = false;
                    retvalMessages.add("The chosen frontal base color could not be found in the catalogue")
                } else {
                    // Colour exists - tied to frame?
                    if ( !chosenFrame.frontalBaseColours?.contains(chosenFrontalBaseColour) ) {
                        retvalSuccess = false;
                        retvalMessages.add("The chosen frontal base color is not connected to the chosen frame in the catalogue");
                    }
                }
            }
            
            // Check that the textures are tied to the frame
            def chosenLeftTexture = getRealTexture(chosenFrame, "leftTemple");
            def chosenRightTexture = getRealTexture(chosenFrame, "rightTemple");
            
            if ( this.leftTempleTexture && !"".equals(this.leftTempleTexture.trim()) && !chosenLeftTexture ) {
                retvalSuccess = false;
                retvalMessages.add("The chosen left temple texture is not associated with the chosen frame in the catalogue");
            }
            
            if ( this.rightTempleTexture && !"".equals(this.rightTempleTexture.trim()) && !chosenRightTexture ) {
                retvalSuccess = false;
                retvalMessages.add("The chosen right temple texture is not associated with the chosen frame in the catalogue");
            }
            
            // Finally check the specified finishes
            if ( !this.frontalFinishingAspect ) {
                retvalSuccess = false;
                retvalMessages.add("There is no frontal finishing aspect specified");
            } else {
                // Check the specified finish against the allowed finishes
                if ( (chosenFrame.finishingAspect == FinishingAspect.MATT && this.frontalFinishingAspect == FinishingAspect.BRILLIANT)
                    || (chosenFrame.finishingAspect == FinishingAspect.BRILLIANT && this.frontalFinishingAspect == FinishingAspect.MATT)) {
                    retvalSuccess = false;
                    retvalMessages.add("The specified frontal finishing aspect is not allowed by the frame catalogue");
                }
            }
            
            if ( !this.leftTempleFinishingAspect ) {
                retvalSuccess = false;
                retvalMessages.add("There is no left temple finishing aspect specified");
            } else {
                // Check the specified finish against the allowed finishes
                if ( (chosenFrame.leftTempleFinishingAspect == FinishingAspect.MATT && this.leftTempleFinishingAspect == FinishingAspect.BRILLIANT)
                    || (chosenFrame.leftTempleFinishingAspect == FinishingAspect.BRILLIANT && this.leftTempleFinishingAspect == FinishingAspect.MATT)) {
                    retvalSuccess = false;
                    retvalMessages.add("The specified left temple finishing aspect is not allowed by the frame catalogue");
                }
            }

            if ( !this.rightTempleFinishingAspect ) {
                retvalSuccess = false;
                retvalMessages.add("There is no right temple finishing aspect specified");
            } else {
                // Check the specified finish against the allowed finishes
                if ( (chosenFrame.rightTempleFinishingAspect == FinishingAspect.MATT && this.rightTempleFinishingAspect == FinishingAspect.BRILLIANT)
                    || (chosenFrame.rightTempleFinishingAspect == FinishingAspect.BRILLIANT && this.rightTempleFinishingAspect == FinishingAspect.MATT)) {
                    retvalSuccess = false;
                    retvalMessages.add("The specified right temple finishing aspect is not allowed by the frame catalogue");
                }
            }
            
            
            // Now check for all of the specific combinations that need testing
            if ( retvalSuccess ) {
                
                // Get the lens material for use in the following tests..
                def actualMaterial = LensMaterial.findByIdentifierAndFocalType(this.lensMaterialId, this.lensFocalType);
                if ( actualMaterial ) {

                    if ( actualMaterial.getMaterialType() == LensMaterialType.WHITE ) {
                        
                        if ( this.lensMirrorFilter ) {
                            // Mirror lenses
                            // Check that when mirror is specified that we have a mirror colour and mirror base colour and no ar colour, etc.
                            
                            if ( this.lensARFilter ) {
                                // AR specified - problem
                                retvalSuccess = false;
                                retvalMessages.add("A mirror lens has been specified to have an AR coating which is not allowed");
                            }
                            
                            // Check that we have a mirror colour specified, etc.
                            if ( this.lensMRColourName && !"".equals(this.lensMRColourName.trim()) ) {
                                // A mirror colour is specified - check it exists
                                def mirrorCol = Coating.findByReferenceAndCoatingType(this.lensMRColourName.trim(), CoatingType.MIRROR);
                                if ( mirrorCol ) {
                                    // Colour exists
                                } else {
                                    // Colour doesn't exist or isn't a mirror - problem
                                    retvalSuccess = false;
                                    retvalMessages.add("A mirror lens colour has been specified but it doesn't exist in the catalogue");
                                }
                            } else {
                                // Colour is not specified - it should default to Green..
                                this.lensMRColourName = "Green";
                            }
                            
                            // Check that we have a mirror base colour specified, etc.
                            if ( this.lensMirrorBaseColourName && !"".equals(this.lensMirrorBaseColourName.trim()) ) {
                                // A mirror colour is specified - check it exists
                                def mirrorCol = LensColour.findByName(this.lensMirrorBaseColourName.trim());
                                if ( mirrorCol ) {
                                    // Colour exists
                                } else {
                                    // Colour doesn't exist
                                    retvalSuccess = false;
                                    retvalMessages.add("A mirror base colour has been specified but it doesn't exist in the catalogue");
                                }
                            } else {
                                retvalSuccess = false;
                                retvalMessages.add("A mirror lens has been specified but no mirror base colour name has been specified");
                            }
                        } else if ( this.lensColourName && !"".equals(this.lensColourName.trim()) ) {
                            // Colour lenses
                            
                            // If an AR coating is specified then check that it exists, etc.
                            if ( this.lensARFilter ) {
                                if ( this.lensARColourName && !"".equals(this.lensARColourName.trim()) ) {
                                    // Colour specified - check it exists in the database
                                    def arColour = Coating.findByReferenceAndCoatingType(this.lensARColourName.trim(), CoatingType.AR);
                                    if ( arColour ) {
                                        // Colour exists
                                    } else {
                                        // Colour doesn't exist or isn't an AR coating - complain
                                        retvalSuccess = false;
                                        retvalMessages.add("Lens AR coating colour specified but the specified colour does not exist in the catalogue");
                                    }
                                } else {
                                    // No colour specified - problem
                                    retvalSuccess = false;
                                    retvalMessages.add("Lens specified with ARFilter flag set but no AR colour specified");
                                }
                            }
                        } else {
                            // Clear lenses
                            // If we have a white material and no colour then we must have an AR filter / colour
                            if ( this.lensARFilter ) {
                                // We have a filter - do we have a colour?
                                if ( this.lensARColourName && !"".equals(this.lensARColourName.trim()) ) {
                                    // Colour specified - check it exists in the database
                                    def arColour = Coating.findByReferenceAndCoatingType(this.lensARColourName.trim(), CoatingType.AR);
                                    if ( arColour ) {
                                        // Colour exists
                                    } else {
                                        // Colour doesn't exist or isn't an AR coating - complain
                                        retvalSuccess = false;
                                        retvalMessages.add("Lens AR coating specified but the specified colour does not exist in the catalogue");
                                    }
                                } else {
                                    // No colour specified - problem
                                    retvalSuccess = false;
                                    retvalMessages.add("Lens specified with ARFilter flag set but no AR colour specified");
                                }
                            } else {
                                // No AR filter flag - problem
                                retvalSuccess = false;
                                retvalMessages.add("White lens with no colour specified but the required AR Filter flag is also not set");
                            }
                        }
            
                    } else if ( actualMaterial.getMaterialType() == LensMaterialType.POLARIZING ) {
                        // Test polar lenses
                        // For polar materials check that the material specified is actual polar
                        
                        // Check that the flag in the input says we are making a polarizing lens and that we have a polarizing colour
                        if ( this.lensPolarMaterial ) {
                            // Polar material is set - check the colour
                            if ( this.lensPolarColourName && !"".equals(this.lensPolarColourName) ) {
                                // A colour has been specified - check it exists in the database
                                def polarCol = LensSpecialColour.findByNameAndSpecialColourType(this.lensPolarColourName.trim(), LensSpecialColourType.POLARIZING);
                                if ( polarCol ) {
                                    // The colour exists - all OK
                                } else {
                                    // The colour doesn't exist - problem
                                    retvalSuccess = false;
                                    retvalMessages.add("The specified polarizing colour does not exist in the catalogue or isn't a polarizing colour");
                                }
                            } else {
                                // A polarizing lens, but no colour specified
                                retvalSuccess = false;
                                retvalMessages.add("A polarizing lens is specified, but no colour name is specified");
                            }
                        } else {
                            // A polarizing material but without the polarizing flag!
                            retvalSuccess = false;
                            retvalMessages.add("A polarizing material has been specified, but the polarizing material flag has not been set");
                        }

                    } else if ( actualMaterial.getMaterialType() == LensMaterialType.PHOTOCHROMIC ) {
                        // Test photochromic lenses
                        // For photochromic lens check that we have a photochromic material and AR filter / colour is set
                        if ( this.lensPhotochromicMaterial ) {
                            // Photochromic material is set - check the colour
                            if ( this.lensPhotochromicColourName && !"".equals(this.lensPhotochromicColourName.trim()) ) {
                                // A colour has been specified - check it exists in the database
                                def photoOnCol = LensSpecialColour.findByNameAndSpecialColourType(this.lensPhotochromicColourName.trim() + " ON", LensSpecialColourType.PHOTOCHROMIC);
                                def photoOffCol = LensSpecialColour.findByNameAndSpecialColourType(this.lensPhotochromicColourName.trim() + " OFF", LensSpecialColourType.PHOTOCHROMIC);
                                if ( photoOnCol && photoOffCol  ) {
                                    // The colour exists - all ok
                                } else {
                                    // The colour doesn't exist - problem
                                    retvalSuccess = false;
                                    retvalMessages.add("The specified photochromic colour does not exist in the catalogue or it isn't a photochromic colour (searching for '" + this.lensPhotochromicColourName.trim() + " ON' and '" + this.lensPhotochromicColourName.trim() + " OFF')");
                                }
                            } else {
                                // A photochromic lens, but no colour specified
                                retvalSuccess = false;
                                retvalMessages.add("A photochromic lens is specified, but not colour name is specified");
                            }
                        } else {
                            // A photochromic material, but without the photochromic flag
                            retvalSuccess = false;
                            retvalMessages.add("A photochromic material has been specified, but the photochromic material flag has not been set");
                        }
                    } else {
                        // Unrecognised type - can't do any checking!
                        log.error("An unrecognised material type when trying to validate the customer choices - can't do any lens validation!");
                        retvalMessages.add("An unrecognised material type when trying to validate the customer choices - can't do any lens validation");
                    }

                } else {
                    // Unable to find the material - complain about this!
                    retvalSuccess = false;
                    retvalMessages.add("Unable to find the specified lens material in the catalogue");
                }
            }
            
        }
        
        def retval = [success: retvalSuccess, messages: retvalMessages];
        
        log.debug("About to return from the validate choices method with retval: " + retval);
        return retval;
    }
    
    def getRealFrame() {
        def retval = null;
        
        if ( this.frameId ) 
            retval = Frame.findByIdentifier(this.frameId);
        
        return retval;
    }
    
    def getRealTexture(realFrame, textureType) {
        
        def retval = null;
        
        if ( realFrame ) {
            // We have a frame to get the texture from..
            
            if ( "leftTemple".equalsIgnoreCase(textureType) ) {
                // Want to lookup the left temple texture
                
                // Loop through all of the textures until a match is found or we run out
                for(FrameTexture aTexture: realFrame.leftTempleTexture) {
                    if ( this.leftTempleTexture.equals(aTexture.reference) ) {
                        // We've got a match
                        retval = aTexture;
                        break;
                    }
                }
            } else if ( "rightTemple".equalsIgnoreCase(textureType) ) {
                // Want to lookup the right temple texture

                // Loop through all of the textures until a match is found or we run out
                for(FrameTexture aTexture: realFrame.rightTempleTexture) {
                    if ( this.rightTempleTexture.equals(aTexture.reference) ) {
                        // We've got a match
                        retval = aTexture;
                        break;
                    }
                }
            }
        } else {
            // No frame - can't get the texture, so don't do anything
        }
        
        
        return retval;
    }
        
}

