package com.example.delay;

/**
 * Result of a use case execution.
 */
public class Result {
    public boolean success;
    public String message;

    /**
     * Constructor.
     * @param success True if operation succeeded.
     * @param message Descriptive message.
     */
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
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
}