package com.example.exception;

import java.util.Date;

/**
 * Custom exception for ETOUR server connection failures (REQ-011).
 */
public class ETOURConnectionException extends Exception {
    private String errorCode;
    private String message;
    private Date timestamp;

    public ETOURConnectionException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = new Date();
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}