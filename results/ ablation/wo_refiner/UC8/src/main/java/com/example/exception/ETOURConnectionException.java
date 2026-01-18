package com.example.exception;

/**
 * Custom exception for ETOUR Database connection errors.
 * REQ-014: Exception handling
 */
public class ETOURConnectionException extends RuntimeException {
    private final String errorCode;

    public ETOURConnectionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}