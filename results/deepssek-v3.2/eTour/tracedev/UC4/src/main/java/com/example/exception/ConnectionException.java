package com.example.exception;

import java.util.Date;

/**
 * Exception thrown when connection to external service/database is interrupted.
 * Corresponds to Exit Conditions: Connection interruption.
 */
public class ConnectionException extends RuntimeException {
    private String message;
    private Date timestamp;
    
    public ConnectionException(String message) {
        super(message);
        this.message = message;
        this.timestamp = new Date();
    }
    
    public String getMessage() {
        return message;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
}