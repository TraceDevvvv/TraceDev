package com.example.actor;

/**
 * Actor: Agency Operator
 * Participating Actor: initialization Operator Agency
 * trace(R3)
 */
public class AgencyOperator {
    private boolean loggedIn;
    private String operatorId;

    public AgencyOperator(String operatorId) {
        this.operatorId = operatorId;
        this.loggedIn = true; // Assume logged in for simulation
    }

    /**
     * trace(R4)
     * Entry condition: The agency has logged
     * @return true if the operator is logged in
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}