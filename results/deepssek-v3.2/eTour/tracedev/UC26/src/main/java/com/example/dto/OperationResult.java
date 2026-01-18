package com.example.dto;

/**
 * Result of an operation, indicating success or failure.
 */
public class OperationResult {
    public boolean success;
    public String message;

    // Constructor
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
}