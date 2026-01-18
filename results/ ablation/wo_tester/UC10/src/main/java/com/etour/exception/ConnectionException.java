package com.etour.exception;

import java.util.Date;

/**
 * Models connection interruption errors (R2 Exit Condition 2)
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