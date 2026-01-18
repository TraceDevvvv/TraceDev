package com.example.dto;

/**
 * Data Transfer Object for password modification response.
 * Contains success flag and message.
 */
public class ModifyPasswordResponse {
    private boolean success;
    private String message;
    
    public ModifyPasswordResponse() {}
    
    public ModifyPasswordResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
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
}