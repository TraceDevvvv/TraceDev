package com.example.model;

/**
 * Result of an operation.
 * Added to satisfy requirement REQ-013
 */
public class OperationResult {
    private boolean success;
    private String message;
    private Object updatedBanner;

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

    public Object getUpdatedBanner() {
        return updatedBanner;
    }

    public void setUpdatedBanner(Object updatedBanner) {
        this.updatedBanner = updatedBanner;
    }
}