package com.example.domain;

import java.time.LocalDateTime;

/**
 * Custom exception for connection interruption.
 * Added to satisfy requirement Exit Conditions.
 */
public class ConnectionInterruptedException extends Exception {
    private String cause;
    private LocalDateTime timestamp;

    public ConnectionInterruptedException(String message, String cause, LocalDateTime timestamp) {
        super(message);
        this.cause = cause;
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return "Connection interrupted due to: " + cause + " at " + timestamp;
    }
}