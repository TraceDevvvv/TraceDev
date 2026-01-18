package com.example.infrastructure;

/**
 * Represents a user session. Used for authentication purposes.
 * Added for AuthenticationService parameter type (R4).
 */
public class Session {
    public String sessionId;
    public String userId;

    public Session(String sessionId, String userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    // A dummy method to check if the session is valid (e.g., not expired)
    public boolean isValid() {
        return sessionId != null && !sessionId.isEmpty() && userId != null && !userId.isEmpty();
    }
}