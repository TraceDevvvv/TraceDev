package com.example.exceptions;

import java.util.Date;

/**
 * Exception thrown when a database connection is lost.
 */
public class ConnectionException extends Exception {
    private String message;
    private Date timestamp;

    public ConnectionException(String message) {
        super(message);
        this.message = message;
        this.timestamp = new Date();
    }

    /**
     * Provides error details including timestamp.
     */
    public String getErrorDetails() {
        return "ConnectionException occurred at " + timestamp + ": " + message;
    }
}