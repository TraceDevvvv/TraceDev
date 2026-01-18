package com.example;

import java.util.Date;

// Custom exception for ETOUR server connection failures
public class ETOURConnectionException extends RuntimeException {
    private String message;
    private String errorCode;
    private Date timestamp;

    public ETOURConnectionException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ETOURConnectionException{" +
                "message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}