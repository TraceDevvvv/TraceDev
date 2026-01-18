package com.cultural.application.service;

/**
 * Custom exception for ETOUR connection failures.
 */
public class ConnectionException extends Exception {
    private String errorCode;

    public ConnectionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}