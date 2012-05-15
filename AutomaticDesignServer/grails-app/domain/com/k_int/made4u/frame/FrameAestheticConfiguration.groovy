package com.k_int.made4u.frame

import java.util.HashSet;

/**
 * Frame domain class to represent a single aesthetic configuration for a frame
 * @author rpb rich@k-int.com
 * @version 1.0 16.08.11
 */
class FrameAestheticConfiguration {
    
    
    // Left temple information
    FinishingAspect leftTempleFinishingAspect;
    FrameTexture    leftTempleTexture;
    FrameBaseColour leftTempleBaseColour;
    
    // Right temple information
    FinishingAspect rightTempleFinishingAspect;
    FrameTexture    rightTempleTexture;
    FrameBaseColour rightTempleBaseColour;
    
    // Frontal information
    FinishingAspect frontalFinishingAspect;
    FrameTexture    frontalTexture;
    FrameBaseColour frontalBaseColour;
    
    // General
    String                  reference;
    Set<EmotionalVariable>  emotionalVariables = new HashSet<EmotionalVariable>();
    String                  imagePath;
    String                  privateImagePath;
    
    static mapping = {
        table 'M4U_FRAME_AESTHETIC_CONFIGURATIONS'
    }
    
    static hasMany = [  emotionalVariables: com.k_int.made4u.frame.EmotionalVariable ]
    
    static constraints = {
        reference                   (nullable: true)
        leftTempleFinishingAspect   (nullable: true)
        leftTempleTexture           (nullable: true)
        leftTempleBaseColour        (nullable: true)
        frontalFinishingAspect      (nullable: true)
        frontalTexture              (nullable: true)
        frontalBaseColour           (nullable: true)
        rightTempleFinishingAspect  (nullable: true)
        rightTempleTexture          (nullable: true)
        rightTempleBaseColour       (nullable: true)
        imagePath                   (nullable: true)
        privateImagePath            (nullable: true)
    }

    String toXML(String idToUse) {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<m4u:Configuration id=\"").append(idToUse).append("\">");
        xml.append("<m4u:Image>").append(imagePath).append("</m4u:Image>");
        xml.append("<m4u:LeftTemple>")
        xml.append("<m4u:FinishingAspect>").append(leftTempleFinishingAspect).append("</m4u:FinishingAspect>");
        xml.append("<m4u:Texture>").append(leftTempleTexture?.image?.path).append("</m4u:Texture>");
        xml.append(leftTempleBaseColour.toXML());
        xml.append("</m4u:LeftTemple>");
        xml.append("<m4u:RightTemple>")
        xml.append("<m4u:FinishingAspect>").append(rightTempleFinishingAspect).append("</m4u:FinishingAspect>");
        xml.append("<m4u:Texture>").append(rightTempleTexture?.image?.path).append("</m4u:Texture>");
        xml.append(rightTempleBaseColour.toXML());
        xml.append("</m4u:RightTemple>");
        xml.append("<m4u:Frontal>")
        xml.append("<m4u:FinishingAspect>").append(frontalFinishingAspect).append("</m4u:FinishingAspect>");
        xml.append("<m4u:Texture>").append(frontalTexture?.image?.path).append("</m4u:Texture>");
        xml.append(frontalBaseColour.toXML());
        xml.append("</m4u:Frontal>");
        for(EmotionalVariable emo: emotionalVariables) {
            xml.append(emo.toXML());
        }
        xml.append("</m4u:Configuration");
        
        return xml.toString();
    }

}

