package com.etour.domain;

/**
 * Custom exception thrown when connection to the ETOUR server is interrupted.
 * Satisfies REQ-ExceptionHandling as per audit report.
 */
public class ConnectionException extends Exception {
    private String message;

    public ConnectionException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}