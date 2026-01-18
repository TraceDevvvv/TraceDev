package com.example.presentation;

/**
 * Request DTO for banner deletion.
 */
public class DeleteBannerRequest {
    private String pointOfRestId;
    private String bannerId;
    private String operatorId;
    private String authToken;

    // Getters and setters
    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public void setPointOfRestId(String pointOfRestId) {
        this.pointOfRestId = pointOfRestId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}