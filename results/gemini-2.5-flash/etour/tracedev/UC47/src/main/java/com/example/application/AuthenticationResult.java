package com.example.application;

/**
 * Helper class to encapsulate the result of an authentication attempt.
 */
public class AuthenticationResult {
    private boolean success;
    private String touristId;
    private String sessionId;

    public AuthenticationResult(boolean success, String touristId, String sessionId) {
        this.success = success;
        this.touristId = touristId;
        this.sessionId = sessionId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSessionId() {
        return sessionId;
    }
}