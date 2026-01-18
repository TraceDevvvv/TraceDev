package com.touristagency.interfaceadapters.dtos;

/**
 * Data Transfer Object for incoming toggle status requests.
 */
public class ToggleStatusRequestDTO {
    private final String touristId;
    private final boolean targetStatus;

    public ToggleStatusRequestDTO(String touristId, boolean targetStatus) {
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