package com.example.notification;

import java.time.LocalDateTime;

/**
 * Notification entity for operation results.
 * Added to satisfy requirement REQ-010
 */
public class Notification {
    private String message;
    private LocalDateTime timestamp;
    private boolean success;

    public Notification(String message, boolean success) {
        this.message = message;
        this.success = success;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}