package com.example.model;

/**
 * Entity representing the result of an authentication attempt.
 */
public class AuthenticationResult {

    private boolean success;
    private String message;

    public AuthenticationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}