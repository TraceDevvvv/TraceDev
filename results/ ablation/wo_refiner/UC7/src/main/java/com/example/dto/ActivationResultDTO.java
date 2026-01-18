package com.example.dto;

/**
 * Data Transfer Object for activation results.
 */
public class ActivationResultDTO {
    private boolean success;
    private String message;
    private String conventionId;
    
    public ActivationResultDTO() {}
    
    public ActivationResultDTO(boolean success, String message, String conventionId) {
        this.success = success;
        this.message = message;
        this.conventionId = conventionId;
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
    
    public String getConventionId() {
        return conventionId;
    }
    
    public void setConventionId(String conventionId) {
        this.conventionId = conventionId;
    }
}