package com.example.dto;

/**
 * Response object after registering a delay.
 */
public class RegisterDelayResponse {
    private boolean success;
    private String message;
    private LogDataDTO updatedLogData;
    
    public RegisterDelayResponse(boolean success, String message, LogDataDTO updatedLogData) {
        this.success = success;
        this.message = message;
        this.updatedLogData = updatedLogData;
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
    
    public LogDataDTO getUpdatedLogData() {
        return updatedLogData;
    }
    
    public void setUpdatedLogData(LogDataDTO updatedLogData) {
        this.updatedLogData = updatedLogData;
    }
}