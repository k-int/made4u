package com.k_int.made4u.frame

import java.util.HashSet;

/**
 * Frame domain class to represent the complete frame within the database
 * @author rpb rich@k-int.com
 * @version 1.0 10.06.11
 */
class Frame {
	String          identifier;
        Material        material;
        RimType         rimType             = RimType.FULL;
        Gender          gender              = Gender.UNISEX;
        FrameShape      rimShape;
        EndpieceHeight  endpieceHeight      = EndpieceHeight.UP;
        Use             frameUse            = Use.OPHTHALMIC;
        // Sphere range...
        Float           fullyPersonalisedBasePrice = 0.0f;
        Float           semiPersonalisedBasePrice = 0.0f;
        Float           standardBasePrice = 0.0f;
        int            fullyPersonalisedProductionTime = 0;
        int            semiPersonalisedProductionTime = 0;
        
        FinishingAspect finishingAspect = FinishingAspect.BOTH;
        FinishingAspect leftTempleFinishingAspect = FinishingAspect.BOTH;
        FinishingAspect rightTempleFinishingAspect = FinishingAspect.BOTH;
        Float           mattePrice = 0.0f;
        Float           leftTempleMattePrice = 0.0f;
        Float           rightTempleMattePrice = 0.0f;
        int             matteProductionTime = 0;
        int             leftTempleMatteProductionTime = 0;
        int             rightTempleMatteProductionTime = 0;
        Float           brilliantPrice = 0.0f;
        Float           leftTempleBrilliantPrice = 0.0f;
        Float           rightTempleBrilliantPrice = 0.0f;
        int             brilliantProductionTime = 0;
        int             leftTempleBrilliantProductionTime = 0;
        int             rightTempleBrilliantProductionTime = 0;
        
    
        Float           minLensThickness = 0.0f;
        Float           leftTopPupilFrameDistance = 0.0f;
        Float           leftBottomPupilFrameDistancePSV = 0.0f;
        Float           leftBottomPupilFrameDistanceMF = 0.0f;
        Float           rightTopPupilFrameDistance = 0.0f;
        Float           rightBottomPupilFrameDistancePSV = 0.0f;
        Float           rightBottomPupilFrameDistanceMF = 0.0f;
        
        FrameDimensions         dimensions = new FrameDimensions();
        FrameTempleDimensions   leftTempleDims = new FrameTempleDimensions();
        FrameTempleDimensions   rightTempleDims = new FrameTempleDimensions();
        
        StandardComponent   hinge;
        StandardComponent   nylon;
        StandardComponent   screw;
        
        Set<Precooked>  precookeds = new HashSet<Precooked>();
        
        Set<FrameBaseColour> frontalBaseColours = new HashSet<FrameBaseColour>();
        Set<FrameTexture> frontalTexture = new HashSet<FrameTexture>();
        Set<FrameBaseColour> leftTempleBaseColours = new HashSet<FrameBaseColour>();
        Set<FrameTexture> leftTempleTexture = new HashSet<FrameTexture>();
        Set<FrameBaseColour> rightTempleBaseColours = new HashSet<FrameBaseColour>();
        Set<FrameTexture> rightTempleTexture = new HashSet<FrameTexture>();
        
        FrameAestheticConfiguration config1;
        FrameAestheticConfiguration config2;
        FrameAestheticConfiguration config3 ;
        
        Long            displayOrder = 1l;
    
    static hasMany = [  precookeds : com.k_int.made4u.frame.Precooked , frontalBaseColours: com.k_int.made4u.frame.FrameBaseColour , 
        leftTempleBaseColours: com.k_int.made4u.frame.FrameBaseColour, rightTempleBaseColours: com.k_int.made4u.frame.FrameBaseColour, 
        frontalTexture: com.k_int.made4u.frame.FrameTexture, leftTempleTexture: com.k_int.made4u.frame.FrameTexture, rightTempleTexture: com.k_int.made4u.frame.FrameTexture ]
                
    static constraints = {
        identifier                          (blank: false, nullable: false, unique: true)
        material                            (nullable: false)
        rimType                             (nullable: false)
        gender                              (nullable: false)
        rimShape                            (nullable: false)
        endpieceHeight                      (nullable: false)
        frameUse                            (nullable: false)
        fullyPersonalisedBasePrice          (min: 0.0f, max: 2000.0f)
        semiPersonalisedBasePrice           (min: 0.0f, max: 2000.0f)
        standardBasePrice                   (min: 0.0f, max: 2000.0f)
        fullyPersonalisedProductionTime     (min: 0, max: 30)
        semiPersonalisedProductionTime      (min: 0, max: 30)
        finishingAspect                     (nullable: false)
        leftTempleFinishingAspect           (nullable: false)
        rightTempleFinishingAspect          (nullable: false)
        mattePrice                          (min: 0.0f, max: 2000.0f)
        leftTempleMattePrice                (min: 0.0f, max: 2000.0f)
        rightTempleMattePrice               (min: 0.0f, max: 2000.0f)
        matteProductionTime                 (min: 0, max: 30)
        leftTempleMatteProductionTime       (min: 0, max: 30)
        rightTempleMatteProductionTime      (min: 0, max: 30)
        brilliantPrice                      (min: 0.0f, max: 2000.0f)
        leftTempleBrilliantPrice            (min: 0.0f, max: 2000.0f)
        rightTempleBrilliantPrice           (min: 0.0f, max: 2000.0f)
        brilliantProductionTime             (min: 0, max: 30)
        leftTempleBrilliantProductionTime   (min: 0, max: 30)
        rightTempleBrilliantProductionTime  (min: 0, max: 30)
        minLensThickness                    (min: 0.0f, max: 10.0f)
        leftTopPupilFrameDistance           (min: 0.0f, max: 20.0f)
        leftBottomPupilFrameDistancePSV     (min: 0.0f, max: 20.0f)
        leftBottomPupilFrameDistanceMF      (min: 0.0f, max: 20.0f)
        rightTopPupilFrameDistance          (min: 0.0f, max: 20.0f)
        rightBottomPupilFrameDistancePSV    (min: 0.0f, max: 20.0f)
        rightBottomPupilFrameDistanceMF     (min: 0.0f, max: 20.0f)
        
        hinge                               (nullable: true)
        nylon                               (nullable: true)
        screw                               (nullable: true)
        
        dimensions                          (nullable: false)
        leftTempleDims                      (nullable: false)
        rightTempleDims                     (nullable: false)
        
        config1                             (nullable: true)
        config2                             (nullable: true)
        config3                             (nullable: true)
        
        displayOrder                        (nullable: true)
    }

        
    
    static mapping = {
        table 'M4U_FRAME'
        precookeds joinTable: 'M4U_FRAME_PRECOOKED_JOIN'
        frontalBaseColours joinTable: 'M4U_FRAME_FRONTAL_BASE_COLOURS_JOIN'
        frontalTexture joinTable: 'M4U_FRAME_FRONTAL_TEXTURE_JOIN'
        leftTempleBaseColours joinTable: 'M4U_FRAME_LEFT_TEMPLE_BASE_COLOURS_JOIN'
        leftTempleTexture joinTable: 'M4U_FRAME_LEFT_TEMPLE_TEXTURE_JOIN'
        rightTempleBaseColours joinTable: 'M4U_FRAME_RIGHT_TEMPLE_BASE_COLOURS_JOIN'
        rightTempleTexture joinTable: 'M4U_FRAME_RIGHT_TEMPLE_TEXTURE_JOIN'
    }
    
    
    String frontalToXML() {
        StringBuilder xml = new StringBuilder();
        
        xml.append("<m4u:FrontalInformation>");
        xml.append("<m4u:FinishingAspect>").append(finishingAspect).append("</m4u:FinishingAspect>");
        xml.append("<m4u:MattePrice>").append(mattePrice).append("</m4u:MattePrice>");
        xml.append("<m4u:BrilliantPrice>").append(brilliantPrice).append("</m4u:BrilliantPrice>");
        // TODO - need to put in the frame dimension information...
        
        for(FrameBaseColour colour: frontalBaseColours) {
            xml.append(colour.toXML());
        }
        
        for(FrameTexture texture: frontalFrameTextures) {
            xml.append("<m4u:Texture image=\"").append(texture.reference).append("\" path=\"").append(texture.image?.path).append("\" Price=\"").apend(texture.price).append("\"/>");
        }
        
        xml.append("</m4u:FrontalInformation>");
        
        // TODO - add in the following...
//            <m4u:BoxingHorizontal>55</m4u:BoxingHorizontal>
//            <m4u:BoxingVertical>45</m4u:BoxingVertical>
//            <m4u:PantoscopicAngleLeft>10</m4u:PantoscopicAngleLeft>
//            <m4u:PantoscopicAngleRight>10</m4u:PantoscopicAngleRight>
//            <m4u:BridgeWidth>17</m4u:BridgeWidth>
//            <m4u:BridgeHeight>9.5</m4u:BridgeHeight>
//            <m4u:HeelWidth>3</m4u:HeelWidth>
//            <m4u:BaseOfFrame>5</m4u:BaseOfFrame>
//            <m4u:WrapAngle>10</m4u:WrapAngle>
//            <m4u:TotalLength>127</m4u:TotalLength>
//            <m4u:InternalRimReduction>0</m4u:InternalRimReduction>
        
        return xml.toString();
    }
    
    
    
}

