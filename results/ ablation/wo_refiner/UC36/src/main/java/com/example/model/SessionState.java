package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a user session state.
 */
public class SessionState {
    public String sessionId;
    public String previousUrl;
    public LocalDateTime timestamp;

    public SessionState(String sessionId, String previousUrl) {
        this.sessionId = sessionId;
        this.previousUrl = previousUrl;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isValid() {
        return sessionId != null && !sessionId.isEmpty();
    }

    public boolean restore() {
        // Simulate restoration logic
        return isValid();
    }
}