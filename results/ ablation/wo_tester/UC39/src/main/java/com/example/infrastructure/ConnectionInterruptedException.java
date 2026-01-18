package com.example.infrastructure;

import java.util.Date;

/**
 * Exception thrown when server connection is interrupted.
 * As per R-EXIT-INTERRUPT.
 */
public class ConnectionInterruptedException extends Exception {
    private String message;
    private Date timestamp;
    
    public ConnectionInterruptedException(String message) {
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