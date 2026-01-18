package com.example.domain;

import java.time.LocalDateTime;

/**
 * Represents a login attempt record.
 */
public class LoginAttempt {
    private Long attemptId;
    private String username;
    private LocalDateTime timestamp;
    private boolean success;
    private String ipAddress;

    public LoginAttempt(String username, LocalDateTime timestamp, boolean success, String ipAddress) {
        this.username = username;
        this.timestamp = timestamp;
        this.success = success;
        this.ipAddress = ipAddress;
    }

    public Long getAttemptId() {
        return attemptId;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}