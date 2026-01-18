package com.touristagency.application;

/**
 * Request model for the use case (application layer).
 */
public class ToggleStatusRequest {
    private final String touristId;
    private final boolean targetStatus;

    public ToggleStatusRequest(String touristId, boolean targetStatus) {
        this.touristId = touristId;
        this.targetStatus = targetStatus;
    }

    public String getTouristId() {
        return touristId;
    }

    public boolean getTargetStatus() {
        return targetStatus;
    }
}