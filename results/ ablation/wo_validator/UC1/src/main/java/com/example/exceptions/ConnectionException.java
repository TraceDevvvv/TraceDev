package com.example.exceptions;

/**
 * Custom exception for database connection issues
 */
public class ConnectionException extends RuntimeException {
    private int errorCode;

    public ConnectionException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ConnectionException(String message, int errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}