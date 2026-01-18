package com.example.application.dto;

/**
 * Data Transfer Object (DTO) for the response of the insert user operation.
 * Carries success status and error messages back to the presentation layer.
 */
public class InsertUserResponse {
    private boolean isSuccess;
    private String errorMessage;

    // Getters and Setters
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}