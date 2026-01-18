package com.example.domain;

/**
 * Custom exception for data source errors.
 * Added to satisfy requirement Alternate Flow and Exit Conditions.
 */
public class DataSourceException extends RuntimeException {
    private String message;
    private int errorCode;

    public DataSourceException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}