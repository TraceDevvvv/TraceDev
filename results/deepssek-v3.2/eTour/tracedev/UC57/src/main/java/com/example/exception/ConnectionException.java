package com.example.exception;

/**
 * Exception thrown when connection to external service (e.g., ETOUR server) fails.
 */
public class ConnectionException extends Exception {
    private String errorCode;
    private String message;

    public ConnectionException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}