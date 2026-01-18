package com.example.application;

import com.example.domain.Tourist;

/**
 * Response DTO for Modify Tourist use case.
 */
public class ModifyTouristResponse {
    private boolean success;
    private String message;
    private Tourist updatedTourist;

    public ModifyTouristResponse(boolean success, String message, Tourist updatedTourist) {
        this.success = success;
        this.message = message;
        this.updatedTourist = updatedTourist;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tourist getUpdatedTourist() {
        return updatedTourist;
    }

    public void setUpdatedTourist(Tourist updatedTourist) {
        this.updatedTourist = updatedTourist;
    }
}