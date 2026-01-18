package com.example.ui.model;

/**
 * Result of an operation (e.g., insertion).
 */
public class OperationResult {
    private boolean success;
    private String message;
    private String culturalGoodId;
    
    public OperationResult(boolean success, String message, String culturalGoodId) {
        this.success = success;
        this.message = message;
        this.culturalGoodId = culturalGoodId;
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
    
    public String getCulturalGoodId() {
        return culturalGoodId;
    }
    
    public void setCulturalGoodId(String culturalGoodId) {
        this.culturalGoodId = culturalGoodId;
    }
    
    public void operationResult(boolean success, String message, String culturalGoodId) {
        this.success = success;
        this.message = message;
        this.culturalGoodId = culturalGoodId;
    }
}