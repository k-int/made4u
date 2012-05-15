package com.k_int.made4u.database.lens

import com.k_int.made4u.lens.LensMaterial;
import com.k_int.made4u.lens.LensMaterialType;
import com.k_int.made4u.lens.LensType;
import com.k_int.made4u.lens.LensPrescriptionProperties;

import org.springframework.dao.DataIntegrityViolationException;

class LensMaterialController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [lensMaterialList: LensMaterial.list(params), lensMaterialTotal: LensMaterial.count()]

    }

    def show = {
        def materialInstance = LensMaterial.get(params.id)
        if (!materialInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lens.material.label', default: 'Lens material'), params.id])}"
            redirect(action: "list")
        }
        else {
            [materialInstance: materialInstance]
        }

    }

    def create = {
        def materialInstance = new LensMaterial()
        materialInstance.properties = params;
        
        def possibleMaterialTypes = LensMaterialType.values();
        def possibleFocalTypes = LensType.values();
        
        [materialInstance: materialInstance, availableMaterialTypes: possibleMaterialTypes, availableFocalTypes: possibleFocalTypes]
    }

    def save = {
        def materialInstance = new LensMaterial();
        
        materialInstance.properties = params;
        
        if ( materialInstance.save(flush: true) ) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'lens.material.label', default: 'Lens material'), materialInstance.reference])}"
            redirect(action: "show", id: materialInstance.id);
        } else {
            def possibleMaterialTypes = LensMaterialType.values();
            def possibleFocalTypes = LensType.values();
            render(view: "create", model: [materialInstance: materialInstance, availableMaterialTypes: possibleMaterialTypes, availableFocalTypes: possibleFocalTypes]);
        }
    }

    def edit = {
        def materialInstance = LensMaterial.get(params.id)
        if (!materialInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'lens.material.label', default: 'Lens material'), params.id])}"
            redirect(action: "list")
        }
        else {
            // We have the material - now go and get other available values to be chosen from
            def possibleMaterialTypes = LensMaterialType.values();
            def possibleFocalTypes = LensType.values();

            [materialInstance: materialInstance, availableMaterialTypes: possibleMaterialTypes, availableFocalTypes: possibleFocalTypes]
        }
    }

    def update = {
        def materialInstance = LensMaterial.get(params.id)
        if ( materialInstance ) {
            if ( params.version ) {
                def version = params.version.toLong()
                if ( materialInstance.version > version ) {
                    materialInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'lens.material.label', default: 'Lens material')] as Object[], message(code: 'lens.material.update.conflict', default: 'Another user has updated this material while you were editing'))
                    redirect(action: "edit", params:params)
                }
            }

            materialInstance.properties = params
            
            // Handle the addition / removal of prescription data
            Set<LensPrescriptionProperties> prescriptions = materialInstance.prescriptions;
            Set<LensPrescriptionProperties> toRemove = new HashSet<LensPrescriptionProperties>();
            
            if ( params.sphereMinVals ) {
                // We have some new prescription information
                String allSphereMinVals = params.sphereMinVals;
                String allSphereMaxVals = params.sphereMaxVals;
                String allCylinderMinVals = params.cylinderMinVals;
                String allCylinderMaxVals = params.cylinderMaxVals;
                String allAdditionMinVals = params.additionMinVals;
                String allAdditionMaxVals = params.additionMaxVals;
                String allPrismMinVals = params.prismMinVals;
                String allPrismMaxVals = params.prismMaxVals;

                String[] sphereMinVals = allSphereMinVals.split("_BREAK_");
                String[] sphereMaxVals = allSphereMaxVals.split("_BREAK_");
                String[] cylinderMinVals = allCylinderMinVals.split("_BREAK_");
                String[] cylinderMaxVals = allCylinderMaxVals.split("_BREAK_");
                String[] additionMinVals = allAdditionMinVals.split("_BREAK_");
                String[] additionMaxVals = allAdditionMaxVals.split("_BREAK_");
                String[] prismMinVals = allPrismMinVals.split("_BREAK_");
                String[] prismMaxVals = allPrismMaxVals.split("_BREAK_");
                
                if ( sphereMinVals.length != sphereMaxVals.length || sphereMinVals.length != cylinderMinVals.length 
                    || sphereMinVals.length != cylinderMaxVals.length || sphereMinVals.length != additionMinVals.length
                    || sphereMinVals.length != additionMaxVals.length || sphereMinVals.length != prismMinVals.length
                    || sphereMinVals.length != prismMaxVals.length) {

                    // Non matching numbers - need to do something here..
                    log.error("non matching numbers!!!");
                    log.error("allSphereMinVals = " + allSphereMinVals);
                    log.error("allSphereMaxVals = " + allSphereMaxVals);
                    log.error("allCylinderMinVals = " + allCylinderMinVals);
                    log.error("allCylinderMaxVals = " + allCylinderMaxVals);
                    log.error("allAdditionMinVals = " + allAdditionMinVals);
                    log.error("allAdditionMaxVals = " + allAdditionMaxVals);
                    log.error("allPrismMinVals = " + allPrismMinVals);
                    log.error("allPrismMaxVals = " + allPrismMaxVals);
                    
                    // TODO - handle the error
                } else {
                    // Matching numbers for all of the fields - create the new data
                    for(int ctr = 0; ctr < sphereMinVals.length; ctr++) {
                        
                        Float thisSphereMin = new Float(sphereMinVals[ctr]);
                        Float thisSphereMax = new Float(sphereMaxVals[ctr]);
                        Float thisCylinderMin = new Float(cylinderMinVals[ctr]);
                        Float thisCylinderMax = new Float(cylinderMaxVals[ctr]);
                        Float thisAdditionMin = new Float(additionMinVals[ctr]);
                        Float thisAdditionMax = new Float(additionMaxVals[ctr]);
                        Float thisPrismMin = new Float(prismMinVals[ctr]);
                        Float thisPrismMax = new Float(prismMaxVals[ctr]);
                        
                        LensPrescriptionProperties newPrescrip = new LensPrescriptionProperties(sphereMin: thisSphereMin, sphereMax: thisSphereMax, 
                                    cylinderMin: thisCylinderMin, cylinderMax: thisCylinderMax, additionMin: thisAdditionMin, additionMax: thisAdditionMax,
                                    prismMin: thisPrismMin, prismMax: thisPrismMax).save();
                        prescriptions.add(newPrescrip);
                    }
                    
                }
            }
            
            if ( params.prescriptionDeleteVals ) {
                // We have information about deleting prescription data
                String[] deleteVals = params.prescriptionDeleteVals.split("_BREAK_");
                
                for(String delId: deleteVals) {
                    Long thisDelId = new Long(delId);
                    LensPrescriptionProperties toDel = LensPrescriptionProperties.get(thisDelId);
                    
                    if ( prescriptions.contains(toDel) ) {
                        // Do need to remove this one..
                        prescriptions.remove(toDel);
                        toRemove.add(toDel);
                    }
                }
            }

            materialInstance.save(flush: true);
            for(LensPrescriptionProperties toDel: toRemove) {
                try {
                    toDel.delete(flush: true);
                } catch (DataIntegrityViolationException dive) {
                    log.error dive.message;
                    dive.printStackTrace();

                    flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'lens.prescription.properties.label', default: 'Prescription properties'), params.id])}"
                    redirect(action: "show", id: params.id);
                }
            }
            
            if ( !materialInstance.hasErrors() ) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'lens.material.label', default: 'Lens material'), materialInstance.reference])}"
                redirect(action: "show", id: materialInstance.id)
            } else {
                render(view: "edit", model: [id: params.id, materialInstance: materialInstance, availableMaterialTypes: LensMaterialType.values(), availableFocalTypes: LensType.values()])
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'lens.material.label', default: 'Lens material'), params.id])}"
            redirect(action: "list")
        }
    }


    def delete = {
        def materialInstance = LensMaterial.get(params.id)
        if ( materialInstance ) {
            // We have something to delete - do so
            try {
                materialInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'lens.material.label', default: 'Lens material'), materialInstance.reference])}"
                redirect(action: "list")
            } catch ( DataIntegrityViolationException dive ) {
                log.error dive.message;
                dive.printStackTrace();

                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'lens.material.label', default: 'Lens material'), params.id])}"
                redirect(action: "show", id: params.id);
            }
        } else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'lens.material.label', default: 'Lens material'), params.id])}"
            redirect(action: "list")
        }
    }


}
