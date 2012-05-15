package com.k_int.made4u.customer

import grails.test.*
import org.springframework.core.io.*

class CustomerChoicesTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testToCoatingsFileWhite() {

        def whiteLensExpectedCoatingData = "_LENS_COLOR_CC=Uncoated\n_LENS_COATING_CC=Blue\n_LENS_COATING_CX=Blue\n_LENS_TOPCOAT=TC_1";
 
        Resource resource = new ClassPathResource('customerChoicesWhiteLens.xml');
        def f = resource.getFile();
        
        def parsedData = CustomerChoices.parseFile(f);
        def coatingsData = parsedData.toCoatingsFile();
        
        log.debug("Test 1 coatings data: " + coatingsData);
        assert coatingsData.equals(whiteLensExpectedCoatingData);
        
    }
    
    void testToCoatingsFileColour() {

        def colourLensExpectedCoatingData = "_LENS_COLOR_CC=Grey 15\n_LENS_COATING_CC=Green\n_LENS_COATING_CX=Uncoated\n_LENS_TOPCOAT=TC_1"

        Resource resource = new ClassPathResource('customerChoicesColourLens.xml');
        def f = resource.getFile();

        def parsedData = CustomerChoices.parseFile(f);
        def coatingsData = parsedData.toCoatingsFile();
        
        log.debug("Test 2 coatings data: " + coatingsData);
        assert coatingsData.equals(colourLensExpectedCoatingData);
        
    }
    
    void testToCoatingsFilePolar() {

        def polarLensExpectedCoatingData = "_LENS_COLOR_CC=Uncoated\n_LENS_COATING_CC=Blue\n_LENS_COATING_CX=Blue\n_LENS_TOPCOAT=TC_1"
        def mirrorLensExpectedCoatingData = "_LENS_COLOR_CC=Green 15\n_LENS_COATING_CC=Green\n_LENS_COATING_CX=Red\n_LENS_TOPCOAT=TC_1"
        def photoChromicLensExpectedCoatingData = "_LENS_COLOR_CC=Uncoated\n_LENS_COATING_CC=Blue\n_LENS_COATING_CX=Blue\n_LENS_TOPCOAT=TC_1"

        Resource resource = new ClassPathResource('customerChoicesPolarLens.xml');
        def f = resource.getFile();

        def parsedData = CustomerChoices.parseFile(f);
        def coatingsData = parsedData.toCoatingsFile();
        
        log.debug("Test 3 coatings data: " + coatingsData);
        assert coatingsData.equals(polarLensExpectedCoatingData);
    }
    
    void testToCoatingsFileMirror() {

        def mirrorLensExpectedCoatingData = "_LENS_COLOR_CC=Green 15\n_LENS_COATING_CC=Green\n_LENS_COATING_CX=Red\n_LENS_TOPCOAT=TC_1"

        def resource = new ClassPathResource('customerChoicesMirrorLens.xml');
        def f = resource.getFile();

        def parsedData = CustomerChoices.parseFile(f);
        def coatingsData = parsedData.toCoatingsFile();
        
        log.debug("Test 4 coatings data: " + coatingsData);
        assert coatingsData.equals(mirrorLensExpectedCoatingData);
        
    }
    
    void testToCoatingsFilePhotochromic() {

        def photoChromicLensExpectedCoatingData = "_LENS_COLOR_CC=Uncoated\n_LENS_COATING_CC=Blue\n_LENS_COATING_CX=Blue\n_LENS_TOPCOAT=TC_1"

        // Test 5 - Photochromic lens
        def resource = new ClassPathResource('customerChoicesPhotochromicLens.xml');
        def f = resource.getFile();

        def parsedData = CustomerChoices.parseFile(f);
        def coatingsData = parsedData.toCoatingsFile();
        
        log.debug("Test 5 coatings data: " + coatingsData);
        assert coatingsData.equals(photoChromicLensExpectedCoatingData);

    }
}
