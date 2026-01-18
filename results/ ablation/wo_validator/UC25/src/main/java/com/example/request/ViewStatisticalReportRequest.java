package com.example.request;

/**
 * Request DTO for viewing statistical report
 */
public class ViewStatisticalReportRequest {
    private String locationId;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}