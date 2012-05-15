package com.k_int.made4u.database.lens

import com.k_int.made4u.lens.*;

class LensSystemEnumController {

    def index = {
        redirect(action: "list", params: params)
    }
    
    def list = {
        
        [coatingTypes: CoatingType.values(), lensColourTypes: LensColourType.values(), lensMaterialTypes: LensMaterialType.values(), lensTypes: LensType.values(), specialColourTypes: LensSpecialColourType.values() ]
        
    }
}
