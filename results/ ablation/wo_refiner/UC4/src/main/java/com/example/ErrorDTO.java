package com.example;

import java.util.Date;

// Data Transfer Object for error responses
public class ErrorDTO {
    private String errorCode;
    private String message;
    private Date timestamp;

    public ErrorDTO() {
        this.timestamp = new Date();
    }

    public ErrorDTO(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = new Date();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

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

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}