package com.example.entity;

import java.time.LocalDateTime;

/**
 * Represents a user session.
 * Entity class from the Class Diagram.
 */
public class Session {
    private int userId;
    private LocalDateTime expiryTime;

    public Session(int userId, LocalDateTime expiryTime) {
        this.userId = userId;
        this.expiryTime = expiryTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    /**
     * Checks if the session is still valid.
     * @return true if the session is valid (expiry time is in the future), false otherwise.
     */
    public boolean isValid() {
        return expiryTime.isAfter(LocalDateTime.now());
    }
}