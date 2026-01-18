package com.example.infrastructure;

import java.time.LocalDateTime;

/**
 * Exception thrown when connection to external server (ETOUR) fails.
 */
public class ConnectionException extends Exception {
    private LocalDateTime timestamp;
    
    public ConnectionException(String message) {
        super(message);
        this.timestamp = LocalDateTime.now();
    }
    
    public String getMessage() {
        return super.getMessage();
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}