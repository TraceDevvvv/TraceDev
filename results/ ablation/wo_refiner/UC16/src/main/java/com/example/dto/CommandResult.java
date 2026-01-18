package com.example.dto;

/**
 * Result of a command execution.
 */
public class CommandResult {
    public boolean isSuccess;
    public String message;
    public int affectedId;

    public CommandResult() {}

    public CommandResult(boolean isSuccess, String message, int affectedId) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.affectedId = affectedId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAffectedId() {
        return affectedId;
    }

    public void setAffectedId(int affectedId) {
        this.affectedId = affectedId;
    }
}