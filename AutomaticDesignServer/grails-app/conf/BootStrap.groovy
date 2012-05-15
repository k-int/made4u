import com.k_int.made4u.lens.*
import com.k_int.made4u.frame.*
import com.k_int.made4u.job.*

class BootStrap {

    def init = { servletContext ->

        // Frame materials
        def titaniumFrameMaterial =  Material.findByName('Titanium') ?: new Material(name: 'Titanium', description: '').save()
        def polyamideFrameMaterial = Material.findByName('Polyamide') ?: new Material(name: 'Polyamide', description: '').save()

        // Frame images
//        def frontalImage = FrameImage.findByReference('Frontal image') ?: new FrameImage(reference: 'Frontal image', path: '/frameImages/frontal1.png', imageType: FrameImageType.FRONTAL, price: 0.0f).save()

        // Frame shapes
        def squareShape = FrameShape.findByName('Square') ?: new FrameShape(name: 'Square', description: 'Square rimmed frames').save()
        def roundShape = FrameShape.findByName('Round') ?: new FrameShape(name: 'Round', description: 'Round rimmed frames').save()

        // Frame emotional variables
        def strongEmotion = EmotionalVariable.findByName('Strong') ?: new EmotionalVariable(name: 'Strong', description: '').save()
        def casualEmotion = EmotionalVariable.findByName('Casual') ?: new EmotionalVariable(name: 'Casual', description: '').save()

        // Actual frames
//        def frameOne = Frame.findByIdentifier('testFrame1');
//        if ( frameOne == null ) {
//            def frameTotalDims = new FrameFrontalTotalDimensions().save();
//            def frameOneDims = new FrameDimensions(totalLength: frameTotalDims).save();
//            def leftTempleDims = new FrameTempleDimensions().save();
//            def rightTempleDims = new FrameTempleDimensions().save();
//            
//            def precookedOne = Precooked.findByReference('testPrecooked1') ?: new Precooked(path: 'a', reference: 'testPrecooked1', heelWidth: 10, bridgeWidth: 11).save()
//            def precookedTwo = Precooked.findByReference('testPrecooked2') ?: new Precooked(path: 'b', reference: 'testPrecooked2', heelWidth: 5, bridgeWidth: 2).save()
//            
//            def hinge = StandardComponent.findByReference('testHinge01') ?: new StandardComponent(reference: 'testHinge01', description: '', standardType: StandardType.HINGE).save();
//            
//            frameOne = Frame.findByIdentifier('testFrame1') ?: new Frame(identifier: 'testFrame1', material: polyamideFrameMaterial, rimType: RimType.FULL, gender: Gender.UNISEX, rimShape: squareShape, endpieceHeight: EndpieceHeight.UP, frameUse: Use.OPHTHALMIC, dimensions: frameOneDims, leftTempleDims: leftTempleDims, rightTempleDims: rightTempleDims, hinge: hinge, precookeds: [precookedOne, precookedTwo]).save()
//        }
        
        // Set up the manufacturing order and get design timer tasks
        def manOrderTimerData = TimerData.findByTimerType(TimerType.MANUFACTURING_ORDER);
        if ( !manOrderTimerData) 
            manOrderTimerData = new TimerData(lastRunTime: new Date(), timerStatus: TimerStatus.IDLE, timerType: TimerType.MANUFACTURING_ORDER, runInterval: 0).save(flush:true);
        else {
            manOrderTimerData.timerStatus = TimerStatus.IDLE;
            manOrderTimerData.save(flush:true);
        }

        def manOrderFrameProgressTimerData = TimerData.findByTimerType(TimerType.MANUFACTURING_ORDER_FRAME_CALC_PROGRESS);
        if ( !manOrderFrameProgressTimerData) 
            manOrderFrameProgressTimerData = new TimerData(lastRunTime: new Date(), timerStatus: TimerStatus.IDLE, timerType: TimerType.MANUFACTURING_ORDER_FRAME_CALC_PROGRESS, runInterval: 0).save(flush:true);
        else {
            manOrderFrameProgressTimerData.timerStatus = TimerStatus.IDLE;
            manOrderFrameProgressTimerData.save(flush:true);
        }

        def manOrderPartTwoTimerData = TimerData.findByTimerType(TimerType.MANUFACTURING_ORDER_PART_TWO);
        if ( !manOrderPartTwoTimerData) 
            manOrderPartTwoTimerData = new TimerData(lastRunTime: new Date(), timerStatus: TimerStatus.IDLE, timerType: TimerType.MANUFACTURING_ORDER_PART_TWO, runInterval: 0).save(flush:true);
        else {
            manOrderPartTwoTimerData.timerStatus = TimerStatus.IDLE;
            manOrderPartTwoTimerData.save(flush:true);
        }
        
        def manOrderWCSSubTimerData = TimerData.findByTimerType(TimerType.MANUFACTURING_ORDER_WCS_SUBMISSION);
        if ( !manOrderWCSSubTimerData) 
            manOrderWCSSubTimerData = new TimerData(lastRunTime: new Date(), timerStatus: TimerStatus.IDLE, timerType: TimerType.MANUFACTURING_ORDER_WCS_SUBMISSION, runInterval: 0).save(flush:true);
        else {
            manOrderWCSSubTimerData.timerStatus = TimerStatus.IDLE;
            manOrderWCSSubTimerData.save(flush:true);
        }

        
        def designRequestTimerData = TimerData.findByTimerType(TimerType.DESIGN_REQUEST);
        if ( !designRequestTimerData ) 
            designRequestTimerData = new TimerData(lastRunTime: new Date(), timerStatus: TimerStatus.IDLE, timerType: TimerType.DESIGN_REQUEST, runInterval: 0).save(flush:true);
        else {
            designRequestTimerData.timerStatus = TimerStatus.IDLE;
            designRequestTimerData.save(flush:true);
        }

        def designRequestP2TimerData = TimerData.findByTimerType(TimerType.DESIGN_REQUEST_PART_TWO);
        if ( !designRequestP2TimerData ) 
            designRequestP2TimerData = new TimerData(lastRunTime: new Date(), timerStatus: TimerStatus.IDLE, timerType: TimerType.DESIGN_REQUEST_PART_TWO, runInterval: 0).save(flush:true);
        else {
            designRequestP2TimerData.timerStatus = TimerStatus.IDLE;
            designRequestP2TimerData.save(flush:true);
        }
        
        
                
        // Lens colours?
//        def redColour = Colour.findByReference('red') ?: new Colour(reference: 'red', red: 255, green: 0, blue: 0, transparencyIndex: 0).save()
//        def greenColour = Colour.findByReference('green') ?: new Colour(reference: 'green', red: 0, green: 255, blue: 0, transparencyIndex: 0).save()
//        def blueColour = Colour.findByReference('blue') ?: new Colour(reference: 'blue', red: 0, green: 0, blue: 255, transparencyIndex: 0).save()
//        def redMirrorCoating = Coating.findByCoatingTypeAndColour(CoatingType.MIRROR, redColour) ?: new Coating(reference: 'Red mirror coating', coatingType: CoatingType.MIRROR, colour: redColour).save();
//        def redARCoating = Coating.findByCoatingTypeAndColour(CoatingType.AR, redColour) ?: new Coating(reference: 'Red AR coating', coatingType: CoatingType.AR, colour: redColour).save();
//        def greenMirrorCoating = Coating.findByCoatingTypeAndColour(CoatingType.MIRROR, greenColour) ?: new Coating(reference: 'Green mirror coating', coatingType: CoatingType.MIRROR, colour: greenColour).save()
//        def greenARCoating = Coating.findByCoatingTypeAndColour(CoatingType.AR, greenColour) ?: new Coating(reference: 'Green AR coating', coatingType: CoatingType.AR, colour: greenColour).save()
//        def blueMirrorCoating = Coating.findByCoatingTypeAndColour(CoatingType.MIRROR, blueColour) ?: new Coating(reference: 'Blue mirror coating', coatingType: CoatingType.MIRROR, colour: blueColour).save()
//        def blueARCoating = Coating.findByCoatingTypeAndColour(CoatingType.AR, blueColour) ?: new Coating(reference: 'Blue AR coating', coatingType: CoatingType.AR, colour: blueColour).save()
        
    }
    
    def destroy = {
    }
}
