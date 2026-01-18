package com.touristGuide.model;

import java.util.Date;

public class ValidationError {
    private String errorCode;
    private String message;
    private Date timestamp;

    public ValidationError(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = new Date();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}