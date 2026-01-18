package com.example.dto;

import java.util.Date;

/**
 * Result of an operation containing success status and message
 */
public class OperationResult {
    private boolean success;
    private String message;
    private Date timestamp;

    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.timestamp = new Date();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "OperationResult [success=" + success + ", message=" + message + ", timestamp=" + timestamp + "]";
    }
}