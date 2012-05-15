package com.k_int.made4u.catalogue


import grails.converters.deep.XML

import com.k_int.made4u.frame.Frame;
import com.k_int.made4u.frame.EmotionalVariable;
import com.k_int.made4u.frame.FrameAestheticConfiguration
import com.k_int.made4u.frame.FrameBaseColour
import com.k_int.made4u.frame.FrameTexture
import com.k_int.made4u.frame.Precooked

class GetFrameCatalogueController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def index = {
        redirect(action: "show", params: params)
    }
        
    def show = {
        
        // Go and get all frames in the system
        def allFrames = Frame.list();
        
        def completeFrameCatalogueXml = "<?xml version=\"1.0\" encoding=\"UTF8\"?>";
        completeFrameCatalogueXml = completeFrameCatalogueXml + "<frameCatalogue xmlns=\"http://www.made4u.info/schema/frameCatalogueResponse/\" " +
            "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " + 
            "xsi:schemaLocation=\"http://www.made4u.info/schema/frameCatalogueResponse/ http://www.k-int.com/schema/made4u/m4uFrameCatalogue.xsd\">" ;
        
        allFrames.each() {
            def frame = it;
            
            // Create the frame itself
            StringBuilder frameXmlBuilder = new StringBuilder();
            frameXmlBuilder.append("<frame>");
            // Create the general information about the frame
            frameXmlBuilder.append("<General FrameID=\"").append(frame.identifier).append("\">");
            frameXmlBuilder.append("<Gender>").append(frame.gender).append("</Gender>");
            frameXmlBuilder.append("<RimType>").append(frame.rimType).append("</RimType>");
            frameXmlBuilder.append("<RimShape>").append(frame.rimShape?.name).append("</RimShape>");
            frameXmlBuilder.append("<EndpieceHeight>").append(frame.endpieceHeight).append("</EndpieceHeight>");
            frameXmlBuilder.append("<Use>").append(frame.frameUse).append("</Use>");
            frameXmlBuilder.append("<Material>").append(frame.material?.name).append("</Material>");
            frameXmlBuilder.append("<BasePriceFullyPersonalised>").append(frame.fullyPersonalisedBasePrice).append("</BasePriceFullyPersonalised>");
            frameXmlBuilder.append("<BasePriceSemiPersonalised>").append(frame.semiPersonalisedBasePrice).append("</BasePriceSemiPersonalised>");
            frameXmlBuilder.append("<BasePriceStandard>").append(frame.standardBasePrice).append("</BasePriceStandard>");
//            frameXmlBuilder.append("<Standard>").append(frame.standard).append("</Standard>");
            frameXmlBuilder.append("</General>");
            
            // Add in the aesthetic configurations
            frameXmlBuilder.append("<AestheticConfiguration>");
            // Config 1
            frameXmlBuilder.append(workoutAestheticConfigXml(frame.config1, "configuration1"));
            // Config 2
            frameXmlBuilder.append(workoutAestheticConfigXml(frame.config2, "configuration2"));
            // Config 3
            frameXmlBuilder.append(workoutAestheticConfigXml(frame.config3, "configuration3"));
            frameXmlBuilder.append("</AestheticConfiguration>");
            
            // Frontal information
            frameXmlBuilder.append("<FrontalInformation>");
            frameXmlBuilder.append("<FinishingAspect>").append(frame.finishingAspect).append("</FinishingAspect>");
            frameXmlBuilder.append("<MattePrice>").append(frame.mattePrice).append("</MattePrice>");
            frameXmlBuilder.append("<BrilliantPrice>").append(frame.brilliantPrice).append("</BrilliantPrice>");
            if ( frame.frontalBaseColours ) {
                for(FrameBaseColour aColour: frame.frontalBaseColours) {
                    frameXmlBuilder.append(workoutBaseColourXml(aColour));
                }
            }
            if ( frame.frontalTexture ) {
                for(FrameTexture aTexture: frame.frontalTexture) {
                    frameXmlBuilder.append("<Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                }
            }
            frameXmlBuilder.append("</FrontalInformation>");
            
            // Left temple information
            frameXmlBuilder.append("<LeftTempleInformation>");
            frameXmlBuilder.append("<FinishingAspect>").append(frame.leftTempleFinishingAspect).append("</FinishingAspect>");
            frameXmlBuilder.append("<MattePrice>").append(frame.leftTempleMattePrice).append("</MattePrice>");
            frameXmlBuilder.append("<BrilliantPrice>").append(frame.leftTempleBrilliantPrice).append("</BrilliantPrice>");
            if ( frame.leftTempleBaseColours ) {
                for(FrameBaseColour aColour: frame.leftTempleBaseColours) {
                    frameXmlBuilder.append(workoutBaseColourXml(aColour));
                }
            }
            if ( frame.leftTempleTexture ) {
                for(FrameTexture aTexture: frame.leftTempleTexture) {
                    frameXmlBuilder.append("<Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                }
            }
            frameXmlBuilder.append("</LeftTempleInformation>");
            
            // Right temple information
            frameXmlBuilder.append("<RightTempleInformation>");
            frameXmlBuilder.append("<FinishingAspect>").append(frame.rightTempleFinishingAspect).append("</FinishingAspect>");
            frameXmlBuilder.append("<MattePrice>").append(frame.rightTempleMattePrice).append("</MattePrice>");
            frameXmlBuilder.append("<BrilliantPrice>").append(frame.rightTempleBrilliantPrice).append("</BrilliantPrice>");
            if ( frame.rightTempleBaseColours ) {
                for(FrameBaseColour aColour: frame.rightTempleBaseColours) {
                    frameXmlBuilder.append(workoutBaseColourXml(aColour));
                }
            }
            if ( frame.rightTempleTexture ) {
                for(FrameTexture aTexture: frame.rightTempleTexture) {
                    frameXmlBuilder.append("<Texture image=\"").append(aTexture.image.reference).append("\" Price=\"").append(aTexture.price).append("\"/>");
                }
            }
            frameXmlBuilder.append("</RightTempleInformation>");
            
            // Standard components
            frameXmlBuilder.append("<StandardComponents>");
            if ( frame.hinge )
                frameXmlBuilder.append("<Hinge>").append(frame.hinge?.reference).append("</Hinge>");
            frameXmlBuilder.append("</StandardComponents>");

            // Precooked
            if ( frame.precookeds ) {
                for(Precooked aPrecooked: frame.precookeds) {
                    frameXmlBuilder.append("<Pre-Cooked Reference=\"").append(aPrecooked.reference).append("\" BridgeWidth=\"").append(aPrecooked.bridgeWidth).append("\" HeelWidth=\"").append(aPrecooked.heelWidth).append("\"/>");
                }
            } 
            frameXmlBuilder.append("</frame>");
            
            
            completeFrameCatalogueXml += frameXmlBuilder.toString();
        }

        completeFrameCatalogueXml += "</frameCatalogue>";

        // Stream the response back as an XML file
        response.setContentType("application/xml")
        response.setHeader("Content-disposition", "filename=frameCatalogue.xml")
        response.outputStream << completeFrameCatalogueXml
        return
        
    }
    
    def workoutAestheticConfigXml(config, id) {
        StringBuilder configBuilder = new StringBuilder();
        
        if ( config && config.frontalFinishingAspect ) {
            configBuilder.append("<Configuration id=\"").append(id).append("\">");
            configBuilder.append("<Image>").append(config.imagePath).append("</Image>");
            configBuilder.append("<LeftTemple>");
            configBuilder.append("<FinishingAspect>").append(config.leftTempleFinishingAspect).append("</FinishingAspect>");
            if ( config.leftTempleTexture )
                configBuilder.append("<Texture>").append(config.leftTempleTexture).append("</Texture>");
            else
                configBuilder.append("<Texture/>");
            if ( config.leftTempleBaseColour ) {
                configBuilder.append("<BaseColour>")
                configBuilder.append("<name>").append(config.leftTempleBaseColour?.name).append("</name>");
                configBuilder.append("<rCode>").append(config.leftTempleBaseColour?.red).append("</rCode>");
                configBuilder.append("<gCode>").append(config.leftTempleBaseColour?.green).append("</gCode>");
                configBuilder.append("<bCode>").append(config.leftTempleBaseColour?.blue).append("</bCode>");
                configBuilder.append("</BaseColour>")
            } else {
                configBuilder.append("<BaseColour/>");
            }
            configBuilder.append("</LeftTemple>");
            configBuilder.append("<RightTemple>");
            configBuilder.append("<FinishingAspect>").append(config.rightTempleFinishingAspect).append("</FinishingAspect>");
            if ( config.rightTempleTexture )
                configBuilder.append("<Texture>").append(config.rightTempleTexture).append("</Texture>");
            else
                configBuilder.append("<Texture/>")
            if ( config.rightTempleBaseColour ) {    
                configBuilder.append("<BaseColour>")
                configBuilder.append("<name>").append(config.rightTempleBaseColour?.name).append("</name>");
                configBuilder.append("<rCode>").append(config.rightTempleBaseColour?.red).append("</rCode>");
                configBuilder.append("<gCode>").append(config.rightTempleBaseColour?.green).append("</gCode>");
                configBuilder.append("<bCode>").append(config.rightTempleBaseColour?.blue).append("</bCode>");
                configBuilder.append("</BaseColour>")
            } else {
                configBuilder.append("<BaseColour/>")
            }
            configBuilder.append("</RightTemple>");
            configBuilder.append("<Frontal>");
            configBuilder.append("<FinishingAspect>").append(config.frontalFinishingAspect).append("</FinishingAspect>");
            if ( config.frontalTexture )
                configBuilder.append("<Texture>").append(config.frontalTexture).append("</Texture>");
            else
                configBuilder.append("<Texture/>");
            if ( config.frontalBaseColour ) {
                configBuilder.append("<BaseColour>")
                configBuilder.append("<name>").append(config.frontalBaseColour?.name).append("</name>");
                configBuilder.append("<rCode>").append(config.frontalBaseColour?.red).append("</rCode>");
                configBuilder.append("<gCode>").append(config.frontalBaseColour?.green).append("</gCode>");
                configBuilder.append("<bCode>").append(config.frontalBaseColour?.blue).append("</bCode>");
                configBuilder.append("</BaseColour>")
            } else {
                configBuilder.append("<BaseColour/>");
            }
            configBuilder.append("</Frontal>");
            if ( config.emotionalVariables ) {
                def emoVars = config.emotionalVariables;
                for(EmotionalVariable anEmoVar: emoVars) {
                    configBuilder.append("<EmotionalVariable>").append(anEmoVar.name).append("</EmotionalVariable>");
                }
            } 
            
            configBuilder.append("</Configuration>");
        }

        return configBuilder.toString();
    }
    
    def workoutBaseColourXml(FrameBaseColour colour) {
        StringBuilder colourBuilder = new StringBuilder();
        
        if ( colour ) {
            colourBuilder.append("<BaseColour>");
            colourBuilder.append("<name>").append(colour.name).append("</name>");
            colourBuilder.append("<rCode>").append(colour.red).append("</rCode>");
            colourBuilder.append("<gCode>").append(colour.green).append("</gCode>");
            colourBuilder.append("<bCode>").append(colour.blue).append("</bCode>");
            colourBuilder.append("<price>").append(colour.price).append("</price>");
            colourBuilder.append("</BaseColour>");
        }
        return colourBuilder.toString();
    }
        
}
