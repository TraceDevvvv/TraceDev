
package com.example.auth;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Session entity representing a user session.
 * Added to satisfy requirement REQ-003, REQ-004
 */
public class Session {
    private Long id;
    private Long userId;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public Session(Long userId) {
        this.id = System.currentTimeMillis(); // simple id generation
        this.userId = userId;
        this.token = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.expiresAt = createdAt.plusHours(1); // session valid for 1 hour
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * Checks if the session is currently valid.
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return LocalDateTime.now().isBefore(expiresAt);
    }
}
