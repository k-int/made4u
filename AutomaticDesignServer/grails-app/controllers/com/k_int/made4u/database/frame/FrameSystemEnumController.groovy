package com.k_int.made4u.database.frame

import com.k_int.made4u.frame.*;
import com.k_int.made4u.PersonalisationType;

class FrameSystemEnumController {

    def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        
        [endpieceHeights: EndpieceHeight.values(), finishingAspects: FinishingAspect.values(), genders: Gender.values(), imageTypes: FrameImageType.values(), rimTypes: RimType.values(), standardTypes: StandardType.values(), uses: Use.values(), personalisationTypes: PersonalisationType.values()] 
        
    }
}
