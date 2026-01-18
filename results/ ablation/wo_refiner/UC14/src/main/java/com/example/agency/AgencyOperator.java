package com.example.agency;

/**
 * Represents an agency operator who interacts with the system.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;
    private String agencyId;

    public AgencyOperator(String operatorId, String name, String agencyId) {
        this.operatorId = operatorId;
        this.name = name;
        this.agencyId = agencyId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getName() {
        return name;
    }

    public String getAgencyId() {
        return agencyId;
    }
}