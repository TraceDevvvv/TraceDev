package com.example.dto;

import java.io.Serializable;

/**
 * Response object for tourist profile update operation.
 * Indicates success/failure and provides updated data.
 */
public class UpdateTouristResponse implements Serializable {
    private boolean success;
    private String message;
    private TouristProfileDTO updatedData;

    // Default constructor
    public UpdateTouristResponse() {}

    // Parameterized constructor
    public UpdateTouristResponse(boolean success, String message, TouristProfileDTO updatedData) {
        this.success = success;
        this.message = message;
        this.updatedData = updatedData;
    }

    // Getters and setters
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

    public TouristProfileDTO getUpdatedData() {
        return updatedData;
    }

    public void setUpdatedData(TouristProfileDTO updatedData) {
        this.updatedData = updatedData;
    }
}