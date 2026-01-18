package com.example.domain;

import java.time.LocalDateTime;

/**
 * Domain entity representing a user session.
 * Added to satisfy requirement Entry Conditions.
 */
public class Session {
    private String userId;
    private String sessionId;
    private LocalDateTime createdAt;

    public Session(String userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.createdAt = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isValid() {
        // Example validation: session is valid if created within last 24 hours
        return createdAt.isAfter(LocalDateTime.now().minusHours(24));
    }
}