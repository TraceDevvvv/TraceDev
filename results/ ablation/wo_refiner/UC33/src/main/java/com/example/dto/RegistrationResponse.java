package com.example.dto;

/**
 * Data transfer object for registration responses.
 */
public class RegistrationResponse {
    
    private boolean success;
    private String message;
    private String userId;
    
    /**
     * Constructor for RegistrationResponse.
     * @param success whether registration was successful
     * @param message response message
     * @param userId the created user ID (null if failed)
     */
    public RegistrationResponse(boolean success, String message, String userId) {
        this.success = success;
        this.message = message;
        this.userId = userId;
    }
    
    // Getters as per class diagram
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getUserId() {
        return userId;
    }
    
    // Setters (not in class diagram but useful)
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
}