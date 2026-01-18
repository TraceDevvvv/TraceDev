package com.example.model;

/**
 * Represents the result of a deletion operation.
 */
public class DeletionResult {
    public boolean success;
    public String message;

    public DeletionResult(boolean success, String message) {
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

    @Override
    public String toString() {
        return "DeletionResult{success=" + success + ", message='" + message + "'}";
    }
}