package com.etour.search;

/**
 * Represents a guest user of the system.
 * Traceability link to requirement Actors: Guest User
 */
public class GuestUser {
    private String userId;
    private String sessionId;

    public GuestUser(String userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Checks if the user is authenticated.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        // In a real scenario, this would check session or token validity.
        return sessionId != null && !sessionId.isEmpty();
    }
}