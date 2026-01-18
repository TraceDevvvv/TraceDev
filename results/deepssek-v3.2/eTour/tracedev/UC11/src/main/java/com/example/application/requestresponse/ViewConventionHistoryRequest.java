package com.example.application.requestresponse;

/**
 * Request DTO for the view convention history use case.
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