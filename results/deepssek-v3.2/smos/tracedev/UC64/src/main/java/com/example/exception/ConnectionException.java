package com.example.exception;

import java.util.Date;

public class ConnectionException extends RuntimeException {
    private String message;
    private int errorCode;
    private Date timestamp;

    public ConnectionException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}