package com.example.model;

/**
 * Represents an agency operator who can delete news.
 * Added to satisfy requirement REQ-003.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;

    public AgencyOperator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getName() {
        return name;
    }
}