package com.example.convention;

/**
 * Result of an activation operation.
 */
public class ActivationResult {
    public boolean success;
    public String message;

    public ActivationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}