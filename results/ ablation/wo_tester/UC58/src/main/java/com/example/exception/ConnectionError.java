package com.example.exception;

import java.time.LocalDateTime;

/**
 * Represents a connection error exception.
 */
public class ConnectionError extends RuntimeException {
    private String message;
    private LocalDateTime timestamp;

    public ConnectionError(String message) {
        super(message);
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}