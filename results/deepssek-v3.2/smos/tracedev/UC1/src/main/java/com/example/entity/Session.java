package com.example.entity;

import java.time.LocalDateTime;

/**
 * Represents a user session.
 * Created upon successful login.
 */
public class Session {
    private String sessionId;
    private String username;
    private LocalDateTime expiryTime;

    public Session(String sessionId, String username, LocalDateTime expiryTime) {
        this.sessionId = sessionId;
        this.username = username;
        this.expiryTime = expiryTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Checks if the session is still valid based on expiry time.
     */
    public boolean isValid() {
        return LocalDateTime.now().isBefore(expiryTime);
    }
}