package com.example.connection;

import java.util.Date;

/**
 * Exception thrown when connection to ETOUR is lost (REQ-015).
 */
public class ConnectionInterruptedException extends RuntimeException {
    
    private String message;
    private Date timestamp;
    
    /**
     * Constructs the exception with a message.
     * @param message the error message
     */
    public ConnectionInterruptedException(String message) {
        super(message);
        this.message = message;
        this.timestamp = new Date();
    }
    
    /**
     * Returns the error message.
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }
    
    /**
     * Returns the timestamp when the exception was created.
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }
}