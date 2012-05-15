package com.k_int.made4u.design

import com.k_int.made4u.frame.Frame;
import com.k_int.made4u.frame.EmotionalVariable;
import com.k_int.made4u.frame.FrameBaseColour;
import com.k_int.made4u.frame.FrameTexture;
import com.k_int.made4u.frame.Use;
import com.k_int.made4u.frame.Precooked;
import com.k_int.made4u.frame.RimType;


import com.k_int.made4u.lens.LensMaterial;
import com.k_int.made4u.lens.LensMaterialType;
import com.k_int.made4u.lens.Coating;
import com.k_int.made4u.lens.CoatingType;
import com.k_int.made4u.lens.LensTopcoat;
import com.k_int.made4u.lens.LensColour;
import com.k_int.made4u.lens.LensColourType;
import com.k_int.made4u.lens.LensSpecialColour;
import com.k_int.made4u.lens.LensSpecialColourType;

import com.k_int.made4u.design.DesignData;

import com.k_int.made4u.serviceInputs.LensCalcOutput;


import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * Utility class to output the design request XML given all of the filtered information, etc.
 * @author rpb rich@k-int.com
 * @version 1.0 17.11.11
 */
class CreateDesignResponse {
    
    private static final Log log = LogFactory.getLog(CreateDesignResponse.class);

    
    public static void setupReturnXml(designReq, possibleFrames, possibleLenses, lensCalcOutput, returnedDesignsDir, designResponseFileName) {
        
        log.debug("In the setupReturnXml method");
        
        // Get the lens colour and coatings information
        def allLensColours = LensColour.list([sort:"colourType", order: "asc"]);
        def allPolarizingLensColours = LensSpecialColour.findAllBySpecialColourType(LensSpecialColourType.POLARIZING);
        def allPhotochromicLensColours = LensSpecialColour.findAllBySpecialColourType(LensSpecialColourType.PHOTOCHROMIC);
        
        def allARCoatings = Coating.findAllByCoatingType(CoatingType.AR);
        def allMirrorCoatings = Coating.findAllByCoatingType(CoatingType.MIRROR);
        
        def allTopcoats = LensTopcoat.list();
        
        // Set up the head of the file
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<m4u:AutomaticDesignResponse xmlns:m4u=\"http://www.made4u.info/schema/automaticDesignResponse/\" ");
        xmlBuilder.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        xmlBuilder.append("xsi:schemaLocation=\"http://www.made4u.info/schema/automaticDesignResponse/ http://www.k-int.com/schema/made4u/m4uAutoDesign.xsd\">");
        
        
        // Sort the available frames into the correct order based on their display order before outputting them
        def frameComparator = [ 
            compare: {a,b-> a.displayOrder.equals(b.displayOrder)? 0: a.displayOrder < b.displayOrder ? -1: 1 }
        ] as Comparator
        
        def sortedFrames = possibleFrames.sort(frameComparator);
        
        // Loop through each frame outputting it
        sortedFrames.each() {
            
            def viableFrame = it;
            
            // Check to see that we have something in the lens calc output for this frame and only output it if we do
            
            def frameIdentifier = viableFrame.designName;
            def actualFrame = Frame.findByIdentifier(frameIdentifier);

            log.debug("frameIdentifier = " + frameIdentifier);
            
            if ( lensCalcOutput.containsKey(frameIdentifier) ) {
                
                // This frame really is viable - output it
                
                log.debug("Viable frame ready to output..." + frameIdentifier);
                
                
                // Get the relevant lens information to be passed on later
                def viableLenses = lensCalcOutput.get(frameIdentifier);

                // Actually output everything
                xmlBuilder.append("<m4u:viableFrame>");
            
                // General frame information - all comes from the catalogue not the calculation
                xmlBuilder.append("<m4u:General FrameID=\"").append(frameIdentifier).append("\">");
                xmlBuilder.append("<m4u:Gender>").append(actualFrame.gender).append("</m4u:Gender>");
                xmlBuilder.append("<m4u:RimType>").append(actualFrame.rimType).append("</m4u:RimType>");
                xmlBuilder.append("<m4u:RimShape>").append(actualFrame.rimShape?.name?.toUpperCase()).append("</m4u:RimShape>");
                xmlBuilder.append("<m4u:EndpieceHeight>").append(actualFrame.endpieceHeight).append("</m4u:EndpieceHeight>");
                xmlBuilder.append("<m4u:Use>").append(actualFrame.frameUse).append("</m4u:Use>");
                xmlBuilder.append("<m4u:Material>").append(actualFrame.material?.name.toUpperCase()).append("</m4u:Material>");
                xmlBuilder.append("<m4u:BasePriceFullyPersonalised>").append(actualFrame.fullyPersonalisedBasePrice).append("</m4u:BasePriceFullyPersonalised>");
                xmlBuilder.append("<m4u:BasePriceSemiPersonalised>").append(actualFrame.semiPersonalisedBasePrice).append("</m4u:BasePriceSemiPersonalised>");
                xmlBuilder.append("<m4u:BasePriceStandard>").append(actualFrame.standardBasePrice).append("</m4u:BasePriceStandard>");
                xmlBuilder.append("<m4u:BoxingHorizontal>").append(actualFrame.dimensions.boxingHorizontalStandard).append("</m4u:BoxingHorizontal>");
                xmlBuilder.append("<m4u:BoxingVertical>").append(actualFrame.dimensions.boxingVerticalStandard).append("</m4u:BoxingVertical>");
                xmlBuilder.append("<m4u:PantoscopicAngleLeft>").append(actualFrame.dimensions.pantoscopicAngleLeftStandard).append("</m4u:PantoscopicAngleLeft>");
                xmlBuilder.append("<m4u:PantoscopicAngleRight>").append(actualFrame.dimensions.pantoscopicAngleRightStandard).append("</m4u:PantoscopicAngleRight>");
                xmlBuilder.append("<m4u:BridgeWidth>").append(actualFrame.dimensions.bridgeWidthStandard).append("</m4u:BridgeWidth>");
                xmlBuilder.append("<m4u:BridgeHeight>").append(actualFrame.dimensions.bridgeHeightStandard).append("</m4u:BridgeHeight>");
                xmlBuilder.append("<m4u:TotalLength>").append(actualFrame.dimensions.totalLengthStandard).append("</m4u:TotalLength>");
                xmlBuilder.append("<m4u:LeftTempleLength>").append(actualFrame.leftTempleDims.lengthStandard).append("</m4u:LeftTempleLength>");
                xmlBuilder.append("<m4u:LeftTempleOpeningAngle>").append(actualFrame.leftTempleDims.openingAngleStandard).append("</m4u:LeftTempleOpeningAngle>");
                xmlBuilder.append("<m4u:RightTempleLength>").append(actualFrame.rightTempleDims.lengthStandard).append("</m4u:RightTempleLength>");
                xmlBuilder.append("<m4u:RightTempleOpeningAngle>").append(actualFrame.rightTempleDims.openingAngleStandard).append("</m4u:RightTempleOpeningAngle>");
    //            xmlBuilder.append("<m4u:Standard>").append(actualFrame.standard).append("</m4u:Standard>");
                xmlBuilder.append("</m4u:General>");
    
                // Aesthetic configurations - all from the catalogue
                xmlBuilder.append("<m4u:AestheticConfiguration>");
                // Config 1
                xmlBuilder.append(workoutAestheticConfigXml(actualFrame.config1, "configuration1"));
                // Config 2
                xmlBuilder.append(workoutAestheticConfigXml(actualFrame.config2, "configuration2"));
                // Config 3
                xmlBuilder.append(workoutAestheticConfigXml(actualFrame.config3, "configuration3"));
                xmlBuilder.append("</m4u:AestheticConfiguration>");
            
                // Frontal information - some data from catalogue, some from the calculations
                xmlBuilder.append("<m4u:FrontalInformation>");
                xmlBuilder.append("<m4u:FinishingAspect>").append(actualFrame.finishingAspect).append("</m4u:FinishingAspect>");
                xmlBuilder.append("<m4u:MattePrice>").append(actualFrame.mattePrice).append("</m4u:MattePrice>");
                xmlBuilder.append("<m4u:BrilliantPrice>").append(actualFrame.brilliantPrice).append("</m4u:BrilliantPrice>");
                
                xmlBuilder.append("<m4u:BoxingHorizontal>").append(viableFrame.boxingHorizontal).append("</m4u:BoxingHorizontal>");
                xmlBuilder.append("<m4u:BoxingVertical>").append(viableFrame.boxingVertical).append("</m4u:BoxingVertical>");
                xmlBuilder.append("<m4u:PantoscopicAngleLeft>").append(viableFrame.leftTemplePantoAngle).append("</m4u:PantoscopicAngleLeft>");
                xmlBuilder.append("<m4u:PantoscopicAngleRight>").append(viableFrame.rightTemplePantoAngle).append("</m4u:PantoscopicAngleRight>");
                xmlBuilder.append("<m4u:BridgeWidth>").append(viableFrame.bridgeWidth).append("</m4u:BridgeWidth>");
                xmlBuilder.append("<m4u:BridgeHeight>").append(viableFrame.bridgeHeight).append("</m4u:BridgeHeight>");
                xmlBuilder.append("<m4u:HeelWidth>").append(viableFrame.heelWidth).append("</m4u:HeelWidth>");
                xmlBuilder.append("<m4u:BaseOfFrame>").append(viableFrame.baseOfFrame).append("</m4u:BaseOfFrame>");
                xmlBuilder.append("<m4u:WrapAngle>").append(viableFrame.wrapAngle).append("</m4u:WrapAngle>");
                xmlBuilder.append("<m4u:TotalLength>").append(viableFrame.totalLength).append("</m4u:TotalLength>");
                xmlBuilder.append("<m4u:InternalRimReduction>").append(viableFrame.internalRimReduction).append("</m4u:InternalRimReduction>");        
                
                if ( actualFrame.frontalBaseColours ) {
                    for(FrameBaseColour aColour: actualFrame.frontalBaseColours) {
                        xmlBuilder.append(workoutBaseColourXml(aColour));
                    }
                }
                if ( actualFrame.frontalTexture ) {
                    for(FrameTexture aTexture: actualFrame.frontalTexture) {
                        xmlBuilder.append("<m4u:Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                    }
                }
                xmlBuilder.append("</m4u:FrontalInformation>");

                // Left temple information - some data from catalogue, some from the calculations
                xmlBuilder.append("<m4u:LeftTempleInformation>");
                xmlBuilder.append("<m4u:FinishingAspect>").append(actualFrame.leftTempleFinishingAspect).append("</m4u:FinishingAspect>");
                xmlBuilder.append("<m4u:MattePrice>").append(actualFrame.leftTempleMattePrice).append("</m4u:MattePrice>");
                xmlBuilder.append("<m4u:BrilliantPrice>").append(actualFrame.leftTempleBrilliantPrice).append("</m4u:BrilliantPrice>");
                xmlBuilder.append("<m4u:Length>").append(viableFrame.leftTempleLength).append("</m4u:Length>");
                xmlBuilder.append("<m4u:OpeningAngle>").append(viableFrame.leftTempleOpeningAngle).append("</m4u:OpeningAngle>");
                xmlBuilder.append("<m4u:MainCurvature>").append(viableFrame.leftTempleMainCurvature).append("</m4u:MainCurvature>");
                xmlBuilder.append("<m4u:TempleTerminalAngle>").append(viableFrame.leftTempleTerminalAngle).append("</m4u:TempleTerminalAngle>");

                if ( actualFrame.leftTempleBaseColours ) {
                    for(FrameBaseColour aColour: actualFrame.leftTempleBaseColours) {
                        xmlBuilder.append(workoutBaseColourXml(aColour));
                    }
                }
                if ( actualFrame.leftTempleTexture ) {
                    for(FrameTexture aTexture: actualFrame.leftTempleTexture) {
                        xmlBuilder.append("<m4u:Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                    }
                }
                xmlBuilder.append("</m4u:LeftTempleInformation>");

                // Right temple information - some data from catalogue, some from the calculations
                xmlBuilder.append("<m4u:RightTempleInformation>");
                xmlBuilder.append("<m4u:FinishingAspect>").append(actualFrame.rightTempleFinishingAspect).append("</m4u:FinishingAspect>");
                xmlBuilder.append("<m4u:MattePrice>").append(actualFrame.rightTempleMattePrice).append("</m4u:MattePrice>");
                xmlBuilder.append("<m4u:BrilliantPrice>").append(actualFrame.rightTempleBrilliantPrice).append("</m4u:BrilliantPrice>");
                xmlBuilder.append("<m4u:Length>").append(viableFrame.rightTempleLength).append("</m4u:Length>");
                xmlBuilder.append("<m4u:OpeningAngle>").append(viableFrame.rightTempleOpeningAngle).append("</m4u:OpeningAngle>");
                xmlBuilder.append("<m4u:MainCurvature>").append(viableFrame.rightTempleMainCurvature).append("</m4u:MainCurvature>");
                xmlBuilder.append("<m4u:TempleTerminalAngle>").append(viableFrame.rightTempleTerminalAngle).append("</m4u:TempleTerminalAngle>");

                if ( actualFrame.rightTempleBaseColours ) {
                    for(FrameBaseColour aColour: actualFrame.rightTempleBaseColours) {
                        xmlBuilder.append(workoutBaseColourXml(aColour));
                    }
                }
                if ( actualFrame.rightTempleTexture ) {
                    for(FrameTexture aTexture: actualFrame.rightTempleTexture) {
                        xmlBuilder.append("<m4u:Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                    }
                }
                xmlBuilder.append("</m4u:RightTempleInformation>");
            
                // Standard components
                xmlBuilder.append("<m4u:StandardComponents>");
                xmlBuilder.append("<m4u:Hinge>").append(actualFrame.hinge?.reference).append("</m4u:Hinge>");
                xmlBuilder.append("<m4u:Nylon>").append(actualFrame.nylon?.reference).append("</m4u:Nylon>");
                xmlBuilder.append("<m4u:Screw>").append(actualFrame.screw?.reference).append("</m4u:Screw>");
                xmlBuilder.append("</m4u:StandardComponents>");


                // Precooked files - all from catalogue
                xmlBuilder.append("<m4u:PreCookedFiles>");
                for (Precooked aPrecooked: actualFrame.precookeds) {
                    xmlBuilder.append("<m4u:Pre-Cooked Reference=\"").append(aPrecooked.reference).append("\" BridgeWidth=\"").append(aPrecooked.bridgeWidth).append("\" HeelWidth=\"").append(aPrecooked.heelWidth).append("\"/>")
                }
                xmlBuilder.append("</m4u:PreCookedFiles>");

                // Lenses - combination of stuff from catalogue and from simulations..
                xmlBuilder.append("<m4u:AvailableLenses>");

                if ( actualFrame.frameUse == Use.SOLAR ) {
                    // For Solar frames we want to output colour and polarizing lenses
                    xmlBuilder.append(workoutColourLensesXml(viableLenses, allLensColours, allPolarizingLensColours, allARCoatings, allMirrorCoatings, allTopcoats, viableFrame.rimType));
                } else if ( actualFrame.frameUse == Use.OPHTHALMIC ) {
                    // For Ophthalmic frames we only want to output white and colour lenses
                    xmlBuilder.append(workoutColourLensesXml(viableLenses, allLensColours, allPolarizingLensColours, allARCoatings, allMirrorCoatings, allTopcoats, viableFrame.rimType));
                    xmlBuilder.append(workoutWhiteLensesXml(viableLenses, allARCoatings, allTopcoats, viableFrame.rimType));
                    xmlBuilder.append(workoutPhotochromicLensesXml(viableLenses, allPhotochromicLensColours, allARCoatings, allTopcoats, viableFrame.rimType));
                } else if ( actualFrame.frameUse == Use.BOTH ) {
                    // For frames that allow ophthalmic and solar we want to output white, colour and polarizing lenses
                    xmlBuilder.append(workoutColourLensesXml(viableLenses, allLensColours, allPolarizingLensColours, allARCoatings, allMirrorCoatings, allTopcoats, viableFrame.rimType));
                    xmlBuilder.append(workoutWhiteLensesXml(viableLenses, allARCoatings, allTopcoats, viableFrame.rimType));
                    xmlBuilder.append(workoutPhotochromicLensesXml(viableLenses, allPhotochromicLensColours, allARCoatings, allTopcoats, viableFrame.rimType));
                }

                xmlBuilder.append("</m4u:AvailableLenses>");

                xmlBuilder.append("</m4u:viableFrame>")

                

            } else {
                // The frame isn't viable - don't do anything with it
                
                log.debug("Frame isn't viable: " + frameIdentifier);
            }
                
            
        }
        
        
        xmlBuilder.append("</m4u:AutomaticDesignResponse>");
        

        // Write the information to disk
        log.debug("About to write the XML out to disk");
        
        new File(returnedDesignsDir).mkdir();
        def outputFile = new File(returnedDesignsDir,designResponseFileName);
        outputFile.write(xmlBuilder.toString());

        log.debug("Just finished writing the XML to disk at: " + returnedDesignsDir + "/" + designResponseFileName);
        
    }

    
    private static String workoutAestheticConfigXml(config, id) {
        StringBuilder configBuilder = new StringBuilder();
        
        if ( config && config.frontalFinishingAspect ) {
            configBuilder.append("<m4u:Configuration id=\"").append(id).append("\">");
            configBuilder.append("<m4u:Image>").append(config.reference).append("</m4u:Image>");
            configBuilder.append("<m4u:LeftTemple>");
            configBuilder.append("<m4u:FinishingAspect>").append(config.leftTempleFinishingAspect).append("</m4u:FinishingAspect>");
            if ( config.leftTempleTexture )
                configBuilder.append("<m4u:Texture>").append(config.leftTempleTexture?.reference).append("</m4u:Texture>");
            else
                configBuilder.append("<m4u:Texture/>");
            if ( config.leftTempleBaseColour ) {
                configBuilder.append("<m4u:BaseColour>")
                configBuilder.append("<m4u:name>").append(config.leftTempleBaseColour?.name).append("</m4u:name>");
                configBuilder.append("<m4u:rCode>").append(config.leftTempleBaseColour?.red).append("</m4u:rCode>");
                configBuilder.append("<m4u:gCode>").append(config.leftTempleBaseColour?.green).append("</m4u:gCode>");
                configBuilder.append("<m4u:bCode>").append(config.leftTempleBaseColour?.blue).append("</m4u:bCode>");
                configBuilder.append("</m4u:BaseColour>")
            } else {
                configBuilder.append("<m4u:BaseColour/>");
            }
            configBuilder.append("</m4u:LeftTemple>");
            configBuilder.append("<m4u:RightTemple>");
            configBuilder.append("<m4u:FinishingAspect>").append(config.rightTempleFinishingAspect).append("</m4u:FinishingAspect>");
            if ( config.rightTempleTexture )
                configBuilder.append("<m4u:Texture>").append(config.rightTempleTexture?.reference).append("</m4u:Texture>");
            else
                configBuilder.append("<m4u:Texture/>")
            if ( config.rightTempleBaseColour ) {    
                configBuilder.append("<m4u:BaseColour>")
                configBuilder.append("<m4u:name>").append(config.rightTempleBaseColour?.name).append("</m4u:name>");
                configBuilder.append("<m4u:rCode>").append(config.rightTempleBaseColour?.red).append("</m4u:rCode>");
                configBuilder.append("<m4u:gCode>").append(config.rightTempleBaseColour?.green).append("</m4u:gCode>");
                configBuilder.append("<m4u:bCode>").append(config.rightTempleBaseColour?.blue).append("</m4u:bCode>");
                configBuilder.append("</m4u:BaseColour>")
            } else {
                configBuilder.append("<m4u:BaseColour/>")
            }
            configBuilder.append("</m4u:RightTemple>");
            configBuilder.append("<m4u:Frontal>");
            configBuilder.append("<m4u:FinishingAspect>").append(config.frontalFinishingAspect).append("</m4u:FinishingAspect>");
            if ( config.frontalTexture )
                configBuilder.append("<m4u:Texture>").append(config.frontalTexture?.reference).append("</m4u:Texture>");
            else
                configBuilder.append("<m4u:Texture/>");
            if ( config.frontalBaseColour ) {
                configBuilder.append("<m4u:BaseColour>")
                configBuilder.append("<m4u:name>").append(config.frontalBaseColour?.name).append("</m4u:name>");
                configBuilder.append("<m4u:rCode>").append(config.frontalBaseColour?.red).append("</m4u:rCode>");
                configBuilder.append("<m4u:gCode>").append(config.frontalBaseColour?.green).append("</m4u:gCode>");
                configBuilder.append("<m4u:bCode>").append(config.frontalBaseColour?.blue).append("</m4u:bCode>");
                configBuilder.append("</m4u:BaseColour>")
            } else {
                configBuilder.append("<m4u:BaseColour/>");
            }
            configBuilder.append("</m4u:Frontal>");
            if ( config.emotionalVariables ) {
                def emoVars = config.emotionalVariables;
                for(EmotionalVariable anEmoVar: emoVars) {
                    configBuilder.append("<m4u:EmotionalVariable>").append(anEmoVar.name.toUpperCase()).append("</m4u:EmotionalVariable>");
                }
            } 
            
            configBuilder.append("</m4u:Configuration>");
        }

        return configBuilder.toString();
    }

    private static String  workoutBaseColourXml(FrameBaseColour colour) {
        StringBuilder colourBuilder = new StringBuilder();
        
        if ( colour ) {
            colourBuilder.append("<m4u:BaseColour>");
            colourBuilder.append("<m4u:name>").append(colour.name).append("</m4u:name>");
            colourBuilder.append("<m4u:rCode>").append(colour.red).append("</m4u:rCode>");
            colourBuilder.append("<m4u:gCode>").append(colour.green).append("</m4u:gCode>");
            colourBuilder.append("<m4u:bCode>").append(colour.blue).append("</m4u:bCode>");
            colourBuilder.append("<m4u:price>").append(colour.price).append("</m4u:price>");
            colourBuilder.append("</m4u:BaseColour>");
        }
        return colourBuilder.toString();
    }

    private static String  workoutColourLensesXml(allViableMaterials, allLensColours, allPolarizingLensColours, allARCoatings, allMirrorCoatings, allTopcoats, edgeThicknessType) {
        
        StringBuilder colourLensBuilder = new StringBuilder();
        
        colourLensBuilder.append("<m4u:ColourLens>");
        
        // Materials
        colourLensBuilder.append("<m4u:ViableMaterials>");
        colourLensBuilder.append(workoutViableMaterialsXml(allViableMaterials, LensMaterialType.WHITE, edgeThicknessType));
        colourLensBuilder.append("</m4u:ViableMaterials>");
        colourLensBuilder.append("<m4u:ViablePolarizingMaterials>");
        colourLensBuilder.append(workoutViableMaterialsXml(allViableMaterials, LensMaterialType.POLARIZING, edgeThicknessType));
        colourLensBuilder.append("</m4u:ViablePolarizingMaterials>");
        
        // Colours
        colourLensBuilder.append(workoutLensColourXml(allLensColours, allMirrorCoatings));
        colourLensBuilder.append(workoutPolarizingSpecialLensColourXml(allViableMaterials, allPolarizingLensColours));
        
        // AR
        colourLensBuilder.append(workoutArCoatingXml(allARCoatings));
        
        // Topcoats
        colourLensBuilder.append(workoutTopcoatXml(allTopcoats));
        
        // Mirror coatings
        colourLensBuilder.append(workoutMirrorCoatingXml(allLensColours));
        
        colourLensBuilder.append("</m4u:ColourLens>");
        
        return colourLensBuilder.toString();
    }
    
    private static String  workoutWhiteLensesXml(allViableMaterials, allARCoatings, allTopcoats, edgeThicknessType) {
        
        StringBuilder whiteLensBuilder = new StringBuilder();
        whiteLensBuilder.append("<m4u:WhiteLens>");

        // Materials
        whiteLensBuilder.append("<m4u:ViableMaterials>");
        whiteLensBuilder.append(workoutViableMaterialsXml(allViableMaterials, LensMaterialType.WHITE, edgeThicknessType));
        whiteLensBuilder.append("</m4u:ViableMaterials>");
        
        // AR coatings
        whiteLensBuilder.append(workoutArCoatingXml(allARCoatings));
        
        // Topcoats
        whiteLensBuilder.append(workoutTopcoatXml(allTopcoats));
        
        whiteLensBuilder.append("</m4u:WhiteLens>");
        
        return whiteLensBuilder.toString();
    }

    private static String  workoutPhotochromicLensesXml(allViableMaterials, allPhotochromicLensColours, allARCoatings, allTopcoats, edgeThicknessType) {
        
        StringBuilder photochromicLensBuilder = new StringBuilder();
        photochromicLensBuilder.append("<m4u:PhotoChromicLens>");
        
        // Materials
        photochromicLensBuilder.append("<m4u:ViableMaterials>");
        photochromicLensBuilder.append(workoutViableMaterialsXml(allViableMaterials, LensMaterialType.PHOTOCHROMIC, edgeThicknessType));
        photochromicLensBuilder.append("</m4u:ViableMaterials>");
        
        // Colours
        photochromicLensBuilder.append(workoutPhotochromicSpecialLensColourXml(allViableMaterials, allPhotochromicLensColours));
        
        // AR colour
        photochromicLensBuilder.append(workoutArCoatingXml(allARCoatings));
        
        // Topcoats
        photochromicLensBuilder.append(workoutTopcoatXml(allTopcoats));
        
        photochromicLensBuilder.append("</m4u:PhotoChromicLens>");
        
        return photochromicLensBuilder.toString();
    }
    
    private static String  workoutViableMaterialsXml(allMaterials, requiredMaterialType,  edgeThicknessType) {
        
        StringBuilder viableMaterialsBuilder = new StringBuilder();
        
        for(DesignData aMaterialsData: allMaterials) {
            
            // Work out if we want to output this material (i.e. does it have the right type?
            if ( aMaterialsData.lensMaterialType == requiredMaterialType ) {
                
                LensMaterial actualMaterial = LensMaterial.findById(aMaterialsData.lensMaterialId);
                LensCalcOutput lensCalcOutputData = aMaterialsData.getLensCalcOutputData();

                // Work out which edge thickness we want from the material
                def edgeThickness;
                if ( edgeThicknessType == RimType.RIMLESS )
                    edgeThickness = actualMaterial.minimumEdgeThicknessRimless;
                else if ( edgeThicknessType == RimType.SEMIRIMLESS )
                    edgeThickness = actualMaterial.minimumEdgeThicknessSemi;
                else
                    edgeThickness = actualMaterial.minimumEdgeThicknessFull;

                viableMaterialsBuilder.append("<m4u:ViableMaterial>");
                viableMaterialsBuilder.append("<m4u:name>").append(actualMaterial.reference).append("</m4u:name>");
                viableMaterialsBuilder.append("<m4u:IdMaterial>").append(actualMaterial.identifier).append("</m4u:IdMaterial>");
                viableMaterialsBuilder.append("<m4u:EyeMadePrice>").append(actualMaterial.priceEyeMade).append("</m4u:EyeMadePrice>");
                viableMaterialsBuilder.append("<m4u:LifeMadeExpertPrice>").append(actualMaterial.priceLifeMadeExpert).append("</m4u:LifeMadeExpertPrice>");
                viableMaterialsBuilder.append("<m4u:LifeMadeIniciaPrice>").append(actualMaterial.priceLifeMadeInicia).append("</m4u:LifeMadeIniciaPrice>");
                viableMaterialsBuilder.append("<m4u:SingleVisionPrice>").append(actualMaterial.priceSingleVision).append("</m4u:SingleVisionPrice>");
                viableMaterialsBuilder.append("<m4u:MinimumDrillingThickness>").append(actualMaterial.minimumDrillingThickness).append("</m4u:MinimumDrillingThickness>");
                viableMaterialsBuilder.append("<m4u:MinimumCentreThickness>").append(actualMaterial.centreDrillingThickness).append("</m4u:MinimumCentreThickness>");
                viableMaterialsBuilder.append("<m4u:MinimumEdgeThickness>").append(edgeThickness).append("</m4u:MinimumEdgeThickness>");
                viableMaterialsBuilder.append("<m4u:ThicknessSimulation>");
      
                log.debug("Adding in lens thickness simulation data: " + lensCalcOutputData.getRightEdgeThicknessMin());
                // Get this information from the lens calculation data                
                viableMaterialsBuilder.append("<m4u:REThickness Minimum=\"").append(lensCalcOutputData.getRightEdgeThicknessMin()).append("\" Central=\"").append(lensCalcOutputData.getRightCentreThickness()).append("\" Maximum=\"").append(lensCalcOutputData.getRightEdgeThicknessMax()).append("\"/>");
                viableMaterialsBuilder.append("<m4u:LEThickness Minimum=\"").append(lensCalcOutputData.getLeftEdgeThicknessMin()).append("\" Central=\"").append(lensCalcOutputData.getLeftCentreThickness()).append("\" Maximum=\"").append(lensCalcOutputData.getLeftEdgeThicknessMax()).append("\"/>");
                viableMaterialsBuilder.append("</m4u:ThicknessSimulation>");
                viableMaterialsBuilder.append("<m4u:ND>").append(actualMaterial.refractionIndex).append("</m4u:ND>");
                viableMaterialsBuilder.append("</m4u:ViableMaterial>");
            }            
        }
        
        return viableMaterialsBuilder.toString();
    }
    
    private static String  workoutArCoatingXml(allARCoatings) {

        StringBuilder coatingBuilder = new StringBuilder();
        
        for(Coating anAr: allARCoatings ) {
            
            coatingBuilder.append("<m4u:ARColor>");
            coatingBuilder.append("<m4u:name>").append(anAr.reference).append("</m4u:name>");
            coatingBuilder.append("<m4u:rCode>").append(anAr.red).append("</m4u:rCode>");
            coatingBuilder.append("<m4u:gCode>").append(anAr.green).append("</m4u:gCode>");
            coatingBuilder.append("<m4u:bCode>").append(anAr.blue).append("</m4u:bCode>");
            coatingBuilder.append("<m4u:transparencyIndex>").append(anAr.transparencyIndex).append("</m4u:transparencyIndex>");
            coatingBuilder.append("<m4u:Price>").append(anAr.price).append("</m4u:Price>");            
            coatingBuilder.append("</m4u:ARColor>");
        }
        
        return coatingBuilder.toString();

    }
    
    private static String  workoutTopcoatXml(allTopcoats) {

        StringBuilder topcoatBuilder = new StringBuilder();
        
        for(LensTopcoat aTopcoat: allTopcoats ) {
            
            topcoatBuilder.append("<m4u:Topcoat>");
            topcoatBuilder.append("<m4u:name>").append(aTopcoat.name).append("</m4u:name>");
            topcoatBuilder.append("<m4u:Price>").append(aTopcoat.price).append("</m4u:Price>");
            topcoatBuilder.append("</m4u:Topcoat>");
        }
        
        return topcoatBuilder.toString();

    }
    
    private static String  workoutMirrorCoatingXml(allColours) {
        
        StringBuilder mirrorBuilder = new StringBuilder();
        
        for(LensColour aColour: allColours) {
            
            if ( aColour.acceptMirror ) {
                // This is a colour which can have mirror on top of it - output it
            
                mirrorBuilder.append("<m4u:MirrorBaseColor>");
                mirrorBuilder.append("<m4u:name>").append(aColour.name).append("</m4u:name>");
                mirrorBuilder.append("<m4u:rCode>").append(aColour.rCode).append("</m4u:rCode>");
                mirrorBuilder.append("<m4u:gCode>").append(aColour.gCode).append("</m4u:gCode>");
                mirrorBuilder.append("<m4u:bCode>").append(aColour.bCode).append("</m4u:bCode>");
                mirrorBuilder.append("<m4u:transparencyIndex>").append(aColour.transparencyIndex).append("</m4u:transparencyIndex>");
                mirrorBuilder.append("<m4u:Price>").append(aColour.price).append("</m4u:Price>");            
                mirrorBuilder.append("</m4u:MirrorBaseColor>");
                
            }
        }
        
        return mirrorBuilder.toString();
    }
    
    private static String  workoutLensColourXml(allLensColours, allMirrorCoatings) {
        StringBuilder colourBuilder = new StringBuilder();

        // Output all basic and fashion colours
        for(LensColour aColour: allLensColours) {
            
            if ( aColour.colourType == LensColourType.BASIC )
                colourBuilder.append("<m4u:colour subType=\"BASIC\">");
            else if ( aColour.colourType == LensColourType.FASHION )
                colourBuilder.append("<m4u:colour subType=\"FASHION\">");
            else 
                colourBuilder.append("<m4u:colour subType=\"").append(aColour.colourType).append("\">");
            colourBuilder.append("<m4u:primaryColor>");
            colourBuilder.append("<m4u:name>").append(aColour.name).append("</m4u:name>");
            colourBuilder.append("<m4u:rCode>").append(aColour.rCode).append("</m4u:rCode>");
            colourBuilder.append("<m4u:gCode>").append(aColour.gCode).append("</m4u:gCode>");
            colourBuilder.append("<m4u:bCode>").append(aColour.bCode).append("</m4u:bCode>");
            colourBuilder.append("<m4u:TransparencyIndex>").append(aColour.transparencyIndex).append("</m4u:TransparencyIndex>");
            colourBuilder.append("</m4u:primaryColor>");

            // Add in the gradient information if there is any
            if ( aColour.rCodeGradient == null 
                || aColour.gCodeGradient == null
                || aColour.bCodeGradient == null 
                || (aColour.rCodeGradient == 0 && aColour.gCodeGradient == 0 && aColour.bCodeGradient == 0 ) ) {
                // No gradient information to add..
            } else {
                colourBuilder.append("<m4u:gradientColor>");
                colourBuilder.append("<m4u:rCode>").append(aColour.rCodeGradient).append("</m4u:rCode>");
                colourBuilder.append("<m4u:gCode>").append(aColour.gCodeGradient).append("</m4u:gCode>");
                colourBuilder.append("<m4u:bCode>").append(aColour.bCodeGradient).append("</m4u:bCode>");
                colourBuilder.append("<m4u:TransparencyIndex>").append(aColour.transparencyIndexGradient).append("</m4u:TransparencyIndex>");
                colourBuilder.append("</m4u:gradientColor>")
            }

            colourBuilder.append("<m4u:Price>").append(aColour.price).append("</m4u:Price>");
            colourBuilder.append("</m4u:colour>");
            
        }
        
        // Now output all mirror coatings
        for(Coating aCoating: allMirrorCoatings) {
            
                colourBuilder.append("<m4u:colour subType=\"MIRROR\">");
                colourBuilder.append("<m4u:primaryColor>");
                colourBuilder.append("<m4u:name>").append(aCoating.reference).append("</m4u:name>");
                colourBuilder.append("<m4u:rCode>").append(aCoating.red).append("</m4u:rCode>");
                colourBuilder.append("<m4u:gCode>").append(aCoating.green).append("</m4u:gCode>");
                colourBuilder.append("<m4u:bCode>").append(aCoating.blue).append("</m4u:bCode>");
                colourBuilder.append("<m4u:TransparencyIndex>").append(aCoating.transparencyIndex).append("</m4u:TransparencyIndex>");
                colourBuilder.append("</m4u:primaryColor>");
                colourBuilder.append("<m4u:Price>").append(aCoating.price).append("</m4u:Price>");
                colourBuilder.append("</m4u:colour>");
            
        }
        
        return colourBuilder.toString();
    }
    
    private static String  workoutPolarizingSpecialLensColourXml(allLensMaterials, allLensColours) {
        
        StringBuilder colourBuilder = new StringBuilder();
        
        // Loop through all of the lens materials to see whether we have any polarizing materials
        // and then only output colours if we do
        def hasPolarizing = false;
        
        for(DesignData someData: allLensMaterials) {
            if ( someData.lensMaterialType == LensMaterialType.POLARIZING ) {
                log.debug("We have a polarizing material so will add polarizing colours.. Material id and type: " + someData.lensMaterialId + ", " + someData.lensMaterialType);
                hasPolarizing = true;
                break;
            }
        }
                
        if ( hasPolarizing ) {
            for(LensSpecialColour aColour: allLensColours) {

                colourBuilder.append("<m4u:colour subType=\"POLARIZING\">");
                colourBuilder.append("<m4u:primaryColor>");
                colourBuilder.append("<m4u:name>").append(aColour.name).append("</m4u:name>");
                colourBuilder.append("<m4u:rCode>").append(aColour.rCode).append("</m4u:rCode>");
                colourBuilder.append("<m4u:gCode>").append(aColour.gCode).append("</m4u:gCode>");
                colourBuilder.append("<m4u:bCode>").append(aColour.bCode).append("</m4u:bCode>");
                colourBuilder.append("<m4u:TransparencyIndex>").append(aColour.transparencyIndex).append("</m4u:TransparencyIndex>");
                colourBuilder.append("</m4u:primaryColor>");
                colourBuilder.append("<m4u:Price>").append(aColour.price).append("</m4u:Price>");
                colourBuilder.append("</m4u:colour>");
            }
        }
        
        return colourBuilder.toString();
    }


    private static String  workoutPhotochromicSpecialLensColourXml(allLensMaterials, allLensColours) {
        
        // Loop through all of the lens materials to see whether we have any photochromic materials
        // and then only output colours if we do
        def hasPhotochromic = false;
        
        for(DesignData someData: allLensMaterials) {
            if ( someData.lensMaterialType == LensMaterialType.PHOTOCHROMIC ) {
                log.debug("We have a photochromic material so will add photochromic colours.. Material id and type: " + someData.lensMaterialId + ", " + someData.lensMaterialType);
                hasPhotochromic = true;
                break;
            }
        }
        StringBuilder colourBuilder = new StringBuilder();
        
        if ( hasPhotochromic ) {
            for(LensSpecialColour aColour: allLensColours) {

                colourBuilder.append("<m4u:PhotochromicColour>");
                colourBuilder.append("<m4u:name>").append(aColour.name).append("</m4u:name>");
                colourBuilder.append("<m4u:rCode>").append(aColour.rCode).append("</m4u:rCode>");
                colourBuilder.append("<m4u:gCode>").append(aColour.gCode).append("</m4u:gCode>");
                colourBuilder.append("<m4u:bCode>").append(aColour.bCode).append("</m4u:bCode>");
                colourBuilder.append("<m4u:TransparencyIndex>").append(aColour.transparencyIndex).append("</m4u:TransparencyIndex>");
                colourBuilder.append("<m4u:Price>").append(aColour.price).append("</m4u:Price>");
                colourBuilder.append("</m4u:PhotochromicColour>");
            }
        }
        
        return colourBuilder.toString();
    }

	
}

