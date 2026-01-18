package com.example.authentication;

import java.time.LocalDateTime;

/**
 * Represents a user session after successful authentication.
 */
public class Session {
    private String id;
    private Long userId;
    private LocalDateTime expiresAt;

    public Session(String id, Long userId, LocalDateTime expiresAt) {
        this.id = id;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isValid() {
        return expiresAt.isAfter(LocalDateTime.now());
    }
}