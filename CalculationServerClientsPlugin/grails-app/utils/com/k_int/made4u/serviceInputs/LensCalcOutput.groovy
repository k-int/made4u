package com.k_int.made4u.serviceInputs

/**
 * Class to represent output from a single combination of frame design and lens material from
 * the lens calculation service
 * 
 * @author rpb rich@k-int.com
 * @version 1.0 31.10.11
 */
class LensCalcOutput {
	
    def jobId;
    def designId;
    def designName;
    def materialId;
    def leftCentreThickness;
    def rightCentreThickness;
    def leftEdgeThicknessMax;
    def leftEdgeThicknessMin;
    def rightEdgeThicknessMax;
    def rightEdgeThicknessMin;
    def message;
    
    def setJobId(jobId) { this.jobId = jobId}
    def getJobId() { return this.jobId; }
    def setDesignId(designId) { this.designId = designId}
    def getDesignId() { return this.designId }
    def setDesignName(designName) { this.designName = designName}
    def getDesignName() { return this.designName }
    def setMaterialId(materialId) { this.materialId = materialId}
    def getMaterialId() { return this.materialId; }
    def getLeftCentreThickness() { return this.leftCentreThickness; }
    def setLeftCentreThickness(leftCentreThickness) { this.leftCentreThickness = leftCentreThickness; }
    def getRightCentreThickness() { return this.rightCentreThickness; }
    def setRightCentreThickness(rightCentreThickness) { this.rightCentreThickness = rightCentreThickness; }
    def getLeftEdgeThicknessMax() { return this.leftEdgeThicknessMax; }
    def setLeftEdgeThicknessMax(leftEdgeThicknessMax) { this.leftEdgeThicknessMax = leftEdgeThicknessMax; }
    def getLeftEdgeThicknessMin() { return this.leftEdgeThicknessMin; }
    def setLeftEdgeThicknessMin(leftEdgeThicknessMin) { this.leftEdgeThicknessMin = leftEdgeThicknessMin; }
    def getRightEdgeThicknessMax() { return this.rightEdgeThicknessMax; }
    def setRightEdgeThicknessMax(rightEdgeThicknessMax) { this.rightEdgeThicknessMax = rightEdgeThicknessMax; }
    def getRightEdgeThicknessMin() { return this.rightEdgeThicknessMin; }
    def setRightEdgeThicknessMin(rightEdgeThicknessMin) { this.rightEdgeThicknessMin = rightEdgeThicknessMin; }
    def getMessage() { return this.message; }
    def setMessage(message) { this.message = message; }

    def LensCalcInput() {}
    
    String toOutput() {
        StringBuilder outputBuilder = new StringBuilder();
        
        outputBuilder.append("job id: ").append(this.jobId);
        outputBuilder.append("\ndesign id: ").append(this.designId);
        outputBuilder.append("\ndesign name: ").append(this.designName);
        outputBuilder.append("\nmaterial id: ").append(this.materialId);
        outputBuilder.append("\nleft centre thickness: ").append(this.leftCentreThickness);
        outputBuilder.append("\nright centre thickness: ").append(this.rightCentreThickness);
        outputBuilder.append("\nleft edge thickness min: ").append(this.leftEdgeThicknessMin);
        outputBuilder.append("\nleft edge thickness max: ").append(this.leftEdgeThicknessMax);
        outputBuilder.append("\nright edge thickness min: ").append(this.rightEdgeThicknessMin);
        outputBuilder.append("\nright edge thickness max: ").append(this.rightEdgeThicknessMax);
        outputBuilder.append("\nmessage: ").append(this.message);
        outputBuilder.append("\n\n\n");
        
        return outputBuilder.toString();
    }
}
