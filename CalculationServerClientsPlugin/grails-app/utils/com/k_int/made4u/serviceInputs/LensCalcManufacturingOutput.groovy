package com.k_int.made4u.serviceInputs

/**
 * Class to represent output from a single combination of frame design and lens material from
 * the lens calculation service in manufacturing mode
 * 
 * @author rpb rich@k-int.com
 * @version 1.0 31.10.11
 */
class LensCalcManufacturingOutput {
	
    def jobId;
    def designId;
    def designName;
    def materialId;
    def lms;
    def message;
    
    def setJobId(jobId) { this.jobId = jobId}
    def getJobId() { return this.jobId; }
    def setDesignId(designId) { this.designId = designId}
    def getDesignId() { return this.designId }
    def setDesignName(designName) { this.designName = designName}
    def getDesignName() { return this.designName }
    def setMaterialId(materialId) { this.materialId = materialId}
    def getMaterialId() { return this.materialId; }
    def setLms(lms) { this.lms = lms; }
    def getLms() { return this.lms; }
    def getMessage() { return this.message; }
    def setMessage(message) { this.message = message; }

    def LensCalcInput() {}
    
    String toOutput() {
        StringBuilder outputBuilder = new StringBuilder();
        
        outputBuilder.append("job id: ").append(this.jobId);
        outputBuilder.append("\ndesign id: ").append(this.designId);
        outputBuilder.append("\ndesign name: ").append(this.designName);
        outputBuilder.append("\nmaterial id: ").append(this.materialId);
        outputBuilder.append("\nlms: ").append(this.lms);
        outputBuilder.append("\nmessage: ").append(this.message);
        outputBuilder.append("\n\n\n");
        
        return outputBuilder.toString();
    }
}
