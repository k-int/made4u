package com.k_int.made4u.serviceInputs

/**
 * Class to represent input for a single combination of frame design and lens material
 * @author rpb rich@k-int.com
 * @version 1.0 28.10.11
 */
class LensCalcInput {
	
    def jobId;
    def designId;
    def designName;
    def materialId;
    def schema;
    
    def setJobId(jobId) { this.jobId = jobId}
    def getJobId() { return this.jobId; }
    def setDesignId(designId) { this.designId = designId}
    def getDesignId() { return this.designId }
    def setDesignName(designName) { this.designName = designName}
    def getDesignName() { return this.designName }
    def setMaterialId(materialId) { this.materialId = materialId}
    def getMaterialId() { return this.materialId; }
    def setSchema(schema) { this.schema = schema; }
    def getSchema() { return this.schema; }
    
    def LensCalcInput() {}

    String toOutput() {
        StringBuilder outputBuilder = new StringBuilder();
        
        outputBuilder.append("job id: ").append(this.jobId);
        outputBuilder.append(" design id: ").append(this.designId);
        outputBuilder.append(" design name: ").append(this.designName);
        outputBuilder.append(" material id: ").append(this.materialId);
        outputBuilder.append(" schema: ").append(this.schema);
        
        return outputBuilder.toString();
    }

    /**
     * Parse one of the made4u platform debug files so that we can pick up
     * where we left off and submit tests manually, etc.
     */
    def static LensCalcInput parseMyDebugFile(File debugFile) {
        
        LensCalcInput retval = new LensCalcInput();
        
        StringBuilder schemaBuilder = new StringBuilder();
        
        // Loop through each line of the file and 
        debugFile.eachLine() { 
            line ->
                def trimmed = line.trim();
                
                if ( trimmed =~ /^Lens calculation input/ ) {
                        // A line that we don't care about..
                } else if ( trimmed =~ /^job id:/ ) {
                    // The line that has most of the stuff we want - need to split it out..
                    def jobIdIndex = trimmed.indexOf("job id:");
                    def designIdIndex = trimmed.indexOf("design id:");
                    def designNameIndex = trimmed.indexOf("design name:");
                    def materialIdIndex = trimmed.indexOf("material id:");
                    def schemaIndex = trimmed.indexOf("schema:");
                    
                def jobIdLength = designIdIndex - jobIdIndex - 7;
                def designIdLength = designNameIndex - designIdIndex - 10;
                def designNameLength = materialIdIndex - designNameIndex - 12;
                def materialIdLength = schemaIndex - materialIdIndex - 12;
                
                    
                log.debug("jobIdIndex = " + jobIdIndex + " designIdIndex = " + designIdIndex + " designNameIndex = " + designNameIndex + " materialIdIndex = " + materialIdIndex + " schemaIndex = " +schemaIndex);
                log.debug("jobIdLength = " + jobIdLength + " designIdLength = " + designIdLength + " designNameLength = " + designNameLength + " materialIdLength = " + materialIdLength);
                
                    def jobIdVal = trimmed.substring(jobIdIndex+7, designIdIndex).trim();
                    def designIdVal = trimmed.substring(designIdIndex+10,designNameIndex).trim();
                    def designNameVal = trimmed.substring(designNameIndex+12,materialIdIndex).trim();
                    def materialIdVal = trimmed.substring(materialIdIndex+12,schemaIndex).trim();
                    def schemaVal = trimmed.substring(schemaIndex+7).trim();
                    
                    retval.setJobId(jobIdVal);
                    retval.setDesignId(designIdVal);
                    retval.setDesignName(designNameVal);
                    retval.setMaterialId(materialIdVal);
                    
                    schemaBuilder.append(schemaVal);
                    
                } else if ( !"".equals(trimmed) ) {
                    // Another non-empty line - part of the schema - just add it to the schema builder
                    schemaBuilder.append(trimmed);
                }
        }

        retval.setSchema(schemaBuilder.toString());
        
        return retval;
    }

}



