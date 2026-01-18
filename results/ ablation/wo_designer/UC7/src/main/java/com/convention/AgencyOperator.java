package com.convention;

/**
 * Represents an Agency Operator who can activate conventions.
 */
public class AgencyOperator {
    private String operatorId;
    private String operatorName;

    public AgencyOperator(String operatorId, String operatorName) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    /**
     * Confirm activation of a convention.
     * @param convention The convention to activate
     * @return true if confirmed, false otherwise
     */
    public boolean confirmActivation(Convention convention) {
        // In a real system, this would involve user interface interaction
        // Simulating confirmation logic
        System.out.println("Operator " + operatorName + " reviewing convention: " 
                         + convention.getConventionName());
        
        // Simulate operator checking and confirming
        // Always returns true for this simulation
        return true;
    }
}