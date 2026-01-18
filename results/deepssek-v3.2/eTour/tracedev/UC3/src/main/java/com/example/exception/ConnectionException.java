package com.example.exception;

import java.util.Date;

/**
 * Exception thrown when connection to server is interrupted.
 */
public class ConnectionException extends Exception {
    private String message;
    private Date timestamp;

    public ConnectionException(String message) {
        super(message);
        this.message = message;
        this.timestamp = new Date();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}