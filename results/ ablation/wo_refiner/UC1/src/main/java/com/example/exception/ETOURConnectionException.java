package com.example.exception;

/**
 * Custom exception for ETOUR server connection failures.
 * Added to satisfy requirement REQ-012.
 */
public class ETOURConnectionException extends Exception {
    private int errorCode;
    private String errorMessage;

    public ETOURConnectionException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}