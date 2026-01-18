package com.example.dto;

/**
 * Request DTO for viewing convention history.
 * Updated to satisfy requirement REQ-004.
 */
public class ViewConventionHistoryRequest {
    private String pointOfRestId;
    private String agencyId;

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public void setPointOfRestId(String pointOfRestId) {
        this.pointOfRestId = pointOfRestId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}