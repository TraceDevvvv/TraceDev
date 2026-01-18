package com.cultural.application.dto;

/**
 * Response DTO for the insertion use case.
 */
public class InsertCulturalObjectResponse {
    private boolean success;
    private String message;
    private String culturalObjectId;

    public InsertCulturalObjectResponse(boolean success, String message, String culturalObjectId) {
        this.success = success;
        this.message = message;
        this.culturalObjectId = culturalObjectId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getCulturalObjectId() {
        return culturalObjectId;
    }
}