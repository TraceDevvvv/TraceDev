package com.newsagency.system.dto;

import java.util.Date;

/**
 * DTO for detailed error information.
 */
public class ErrorDTO {
    private String errorCode;
    private String message;
    private Date timestamp;

    public ErrorDTO() {
        this.timestamp = new Date();
    }

    public ErrorDTO(String errorCode, String message) {
        this();
        this.errorCode = errorCode;
        this.message = message;
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
}