package com.example.exception;

import java.util.Date;

/**
 * Exception for connection interruptions with ETOUR server.
 */
public class ConnectionInterruptedException extends RuntimeException {
    private String errorCode;
    private String message;
    private Date timestamp;

    public ConnectionInterruptedException(String errorCode, String message, Date timestamp) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}