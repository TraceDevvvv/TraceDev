package com.etour.login.exception;

import java.time.LocalDateTime;

/**
 * Custom runtime exception for connection errors in the ETOUR system.
 * Extends RuntimeException as per the class diagram.
 */
public class ETOURConnectionException extends RuntimeException {
    private LocalDateTime timestamp;

    public ETOURConnectionException(String message) {
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