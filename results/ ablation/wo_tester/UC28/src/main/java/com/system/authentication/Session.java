package com.system.authentication;

import java.time.LocalDateTime;

/**
 * Represents a user session.
 */
public class Session {
    private String id;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public Session(String id, String userId, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public boolean isValid() {
        return LocalDateTime.now().isBefore(expiresAt);
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }
}