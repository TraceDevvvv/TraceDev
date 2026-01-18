package com.example.result;

import java.util.Date;

/**
 * Result object returned after an update operation.
 */
public class UpdateResult {
    private boolean success;
    private String message;
    private Date timestamp;
    private boolean needsConfirmation;

    public UpdateResult() {}

    public UpdateResult(boolean success, String message, boolean needsConfirmation) {
        this.success = success;
        this.message = message;
        this.needsConfirmation = needsConfirmation;
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

    public boolean getNeedsConfirmation() {
        return needsConfirmation;
    }

    public void setNeedsConfirmation(boolean needsConfirmation) {
        this.needsConfirmation = needsConfirmation;
    }
}