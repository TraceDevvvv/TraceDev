package com.example.exception;

/**
 * Exception for connection-related errors.
 */
public class ConnectionException extends RuntimeException {
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