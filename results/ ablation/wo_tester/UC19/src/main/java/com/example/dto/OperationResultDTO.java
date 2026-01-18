package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for operation results.
 */
public class OperationResultDTO {
    private boolean success;
    private String message;
    private Date timestamp;

    public OperationResultDTO() {
        this.timestamp = new Date();
    }

    public OperationResultDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.timestamp = new Date();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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