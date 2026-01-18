package com.example.exception;

import java.util.Date;

/**
 * Exception representing a connection failure.
 * Satisfies requirement REQ-011.
 */
public class ConnectionException extends Exception {
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