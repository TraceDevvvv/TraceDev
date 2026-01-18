package com.example.domain;

import java.time.LocalDateTime;

/**
 * Exception representing a connection interruption.
 * Added to satisfy requirement: Exit Condition "The connection to the server is interrupted."
 */
public class ConnectionException extends RuntimeException {
    private final String message;
    private final LocalDateTime timestamp;

    public ConnectionException(String message) {
        super(message);
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}