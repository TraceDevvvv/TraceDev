package com.example.value;

/**
 * Value object representing the result of an operation.
 */
public class OperationResult {
    private boolean success;
    private String message;

    // Constructors
    public OperationResult() {
    }

    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    // Setters (if needed)
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}