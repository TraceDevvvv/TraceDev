package com.system.exceptions;

import java.time.LocalDateTime;

/**
 * Custom exception for database-related errors.
 */
public class DatabaseException extends Exception {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;

    public DatabaseException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }
}