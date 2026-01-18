package com.example;

import java.util.Date;

/**
 * Exception class for connection errors.
 * Thrown on server interruption (REQ-011).
 */
public class ConnectionException extends Exception {
    private int errorCode;
    private String message;
    private Date timestamp;

    public ConnectionException(int errorCode, String message, Date timestamp) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
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