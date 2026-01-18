package com.example.exception;

import java.util.Date;

/**
 * Exception thrown when a connection interruption occurs (REQ-014).
 */
public class ConnectionException extends RuntimeException {
    private String message;
    private Date timestamp;
    private int retryAfter = 30; // default 30 seconds

    public ConnectionException(String message, Date timestamp) {
        super(message);
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getRetryAfter() {
        return retryAfter;
    }

    public void setRetryAfter(int retryAfter) {
        this.retryAfter = retryAfter;
    }
}