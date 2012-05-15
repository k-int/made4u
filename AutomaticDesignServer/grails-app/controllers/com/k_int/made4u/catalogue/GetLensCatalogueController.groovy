package com.k_int.made4u.catalogue


import grails.converters.deep.XML

import com.k_int.made4u.lens.LensMaterial
import com.k_int.made4u.lens.LensPrescriptionProperties;
import com.k_int.made4u.lens.LensColour
import com.k_int.made4u.lens.Coating
import com.k_int.made4u.lens.CoatingType
import com.k_int.made4u.lens.LensTopcoat

class GetLensCatalogueController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def index = {
        redirect(action: "show", params: params)
    }
        
    def show = {
        
        // Go and get all lens materials in the system
        def allLensMaterials = LensMaterial.list();
        // And all lens colours
        def allLensColours = LensColour.list();
        // And all coatings
        def allLensCoatings = Coating.list();
        // And all lens topcoats
        def allLensTopcoats = LensTopcoat.list();
        
        def completeLensCatalogueXml = "<?xml version=\"1.0\" encoding=\"UTF8\"?>";
        completeLensCatalogueXml = completeLensCatalogueXml + "<lensCatalogue xmlns=\"http://www.made4u.info/schema/lensCatalogueResponse/\" " +
            "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " + 
            "xsi:schemaLocation=\"http://www.made4u.info/schema/lensCatalogueResponse/ http://www.k-int.com/schema/made4u/m4uLensCatalogue.xsd\">" ;

        // Output each of the lens materials
        completeLensCatalogueXml += "<LensMaterials>";
        allLensMaterials.each() {
            def lens = it;
            
            StringBuilder lensMaterialBuilder = new StringBuilder();
            lensMaterialBuilder.append("<LensMaterial>");
            lensMaterialBuilder.append("<name>").append(lens.reference).append("</name>");
            lensMaterialBuilder.append("<EyeMadePrice>").append(lens.priceEyeMade).append("</EyeMadePrice>");
            lensMaterialBuilder.append("<LifeMadeExpertPrice>").append(lens.priceLifeMadeExpert).append("</LifeMadeExpertPrice>");
            lensMaterialBuilder.append("<LifeMadeIniciaPrice>").append(lens.priceLifeMadeInicia).append("</LifeMadeIniciaPrice>");
            lensMaterialBuilder.append("<SphericalSinglePrice>").append(lens.priceSingleVision).append("</SphericalSinglePrice>");
            lensMaterialBuilder.append("<RefractionIndex>").append(lens.refractionIndex).append("</RefractionIndex>");
            lensMaterialBuilder.append("<MinimumDrillingThickness>").append(lens.minimumDrillingThickness).append("</MinimumDrillingThickness>");
            lensMaterialBuilder.append("<CentreDrillingThickness>").append(lens.centreDrillingThickness).append("</CentreDrillingThickness>");
            lensMaterialBuilder.append("<MaterialType>").append(lens.materialType).append("</MaterialType>");
            lensMaterialBuilder.append("<FocalType>").append(lens.focalType).append("</FocalType>");
            lensMaterialBuilder.append("<LensMaterialColour>");
            lensMaterialBuilder.append("<rCode>").append(lens.red).append("</rCode>");
            lensMaterialBuilder.append("<gCode>").append(lens.green).append("</gCode>");
            lensMaterialBuilder.append("<bCode>").append(lens.blue).append("</bCode>");
            lensMaterialBuilder.append("<TransparencyIndex>").append(lens.transparencyIndex).append("</TransparencyIndex>");
            lensMaterialBuilder.append("<SaturationTransparencyIndex>").append(lens.saturationTransparencyIndex).append("</SaturationTransparencyIndex>");
            lensMaterialBuilder.append("<DominantWavelength>").append(lens.dominantWavelength).append("</DominantWavelength>");
            lensMaterialBuilder.append("<RV>").append(lens.rv).append("</RV>");
            lensMaterialBuilder.append("</LensMaterialColour>");

            lensMaterialBuilder.append("<Prescriptions>");
            
            def prescrips = lens.prescriptions;
            for(LensPrescriptionProperties aPrescrip: prescrips) {
                lensMaterialBuilder.append("<Prescription>");
                lensMaterialBuilder.append("<Sphere min=\"").append(aPrescrip.sphereMin).append("\" max=\"").append(aPrescrip.sphereMax).append("\"/>");
                lensMaterialBuilder.append("<Cylinder min=\"").append(aPrescrip.cylinderMin).append("\" max=\"").append(aPrescrip.cylinderMax).append("\"/>");
                lensMaterialBuilder.append("<Addition min=\"").append(aPrescrip.additionMin).append("\" max=\"").append(aPrescrip.additionMax).append("\"/>");
                lensMaterialBuilder.append("<Prism min=\"").append(aPrescrip.prismMin).append("\" max=\"").append(aPrescrip.prismMax).append("\"/>");
                lensMaterialBuilder.append("</Prescription>");
            }
            
            lensMaterialBuilder.append("</Prescriptions>");
        
            lensMaterialBuilder.append("</LensMaterial>");
            
            completeLensCatalogueXml += lensMaterialBuilder.toString();
        }
        completeLensCatalogueXml += "</LensMaterials>";

        // Output each of the colours
        completeLensCatalogueXml += "<LensColours>";
        allLensColours.each() {
            def colour = it;
            
            StringBuilder colourBuilder = new StringBuilder();
            colourBuilder.append("<colour subType=\"").append(colour.colourType).append("\">");
            colourBuilder.append("<primaryColour>");
            colourBuilder.append("<name>").append(colour.name).append("</name>");
            colourBuilder.append("<rCode>").append(colour.rCode).append("</rCode>");
            colourBuilder.append("<gCode>").append(colour.gCode).append("</gCode>");
            colourBuilder.append("<bCode>").append(colour.bCode).append("</bCode>");
            colourBuilder.append("<TransparencyIndex>").append(colour.transparencyIndex).append("</TransparencyIndex>");
            colourBuilder.append("<AcceptMirror>").append(colour.acceptMirror).append("</AcceptMirror>");
            colourBuilder.append("<L>").append(colour.L).append("</L>");
            colourBuilder.append("<a>").append(colour.a).append("</a>");
            colourBuilder.append("<b>").append(colour.b).append("</b>");
            colourBuilder.append("<TV>").append(colour.TV).append("</TV>");
            colourBuilder.append("<black>").append(colour.black).append("</black>");
            colourBuilder.append("<yellow>").append(colour.yellow).append("</yellow>");
            colourBuilder.append("<blue>").append(colour.blue).append("</blue>");
            colourBuilder.append("<red>").append(colour.red).append("</red>");
            colourBuilder.append("</primaryColour>");
            colourBuilder.append("<gradientColour>");
            colourBuilder.append("<rCode>").append(colour.rCodeGradient).append("</rCode>");
            colourBuilder.append("<gCode>").append(colour.gCodeGradient).append("</gCode>");
            colourBuilder.append("<bCode>").append(colour.bCodeGradient).append("</bCode>");
            colourBuilder.append("<TransparencyIndex>").append(colour.transparencyIndexGradient).append("</TransparencyIndex>");
            colourBuilder.append("<L>").append(colour.LGradient).append("</L>");
            colourBuilder.append("<a>").append(colour.aGradient).append("</a>");
            colourBuilder.append("<b>").append(colour.bGradient).append("</b>");
            colourBuilder.append("<TV>").append(colour.TVGradient).append("</TV>");
            colourBuilder.append("<black>").append(colour.blackGradient).append("</black>");
            colourBuilder.append("<yellow>").append(colour.yellowGradient).append("</yellow>");
            colourBuilder.append("<blue>").append(colour.blueGradient).append("</blue>");
            colourBuilder.append("<red>").append(colour.redGradient).append("</red>");
            colourBuilder.append("</gradientColour>");
            colourBuilder.append("</colour>");
            
            completeLensCatalogueXml += colourBuilder.toString();
        }
        completeLensCatalogueXml += "</LensColours>";
        
        // Output each of the coatings
        completeLensCatalogueXml += "<LensCoatings>";
        allLensCoatings.each() {
            
            def coating = it;
            
            StringBuilder coatingBuilder = new StringBuilder();
            coatingBuilder.append("<LensCoating>");
            
            if ( coating.coatingType == CoatingType.AR ) 
                coatingBuilder.append("<ARColor>");
            else if ( coating.coatingType == CoatingType.MIRROR ) 
                coatingBuilder.append("<MirrorBaseColor>");
                
            coatingBuilder.append("<name>").append(coating.reference).append("</name>");
            coatingBuilder.append("<rCode>").append(coating.red).append("</rCode>");
            coatingBuilder.append("<gCode>").append(coating.green).append("</gCode>");
            coatingBuilder.append("<bCode>").append(coating.blue).append("</bCode>");
            coatingBuilder.append("<TransparencyIndex>").append(coating.transparencyIndex).append("</TransparencyIndex>")
            coatingBuilder.append("<DominantWavelength>").append(coating.dominantWavelength).append("</DominantWavelength>");
            coatingBuilder.append("<SaturationTransparencyIndex>").append(coating.saturationTransparencyIndex).append("</SaturationTransparencyIndex>");
                
            if ( coating.coatingType == CoatingType.AR ) 
                coatingBuilder.append("</ARColor>");
            else if ( coating.coatingType == CoatingType.MIRROR ) 
                coatingBuilder.append("</MirrorBaseColor>");
            
            coatingBuilder.append("</LensCoating>");
            
            completeLensCatalogueXml += coatingBuilder.toString();
        }
        
        completeLensCatalogueXml += "</LensCoatings>";
        
        // Output the lens topcoats
        completeLensCatalogueXml += "<LensTopcoats>";
        
        allLensTopcoats.each() {
            def topcoat = it;
            
            StringBuilder topcoatBuilder = new StringBuilder();
            
            topcoatBuilder.append("<Topcoat>");
            topcoatBuilder.append("<name>").append(topcoat.name).append("</name>");
            topcoatBuilder.append("</Topcoat>");
            
            completeLensCatalogueXml += topcoatBuilder.toString();
        }
        
        completeLensCatalogueXml += "</LensTopcoats>";
        
        completeLensCatalogueXml += "</lensCatalogue>";

        // Stream the response back as an XML file
        response.setContentType("application/xml")
        response.setHeader("Content-disposition", "filename=lensCatalogue.xml")
        response.outputStream << completeLensCatalogueXml
        return
        
    }
        
}
