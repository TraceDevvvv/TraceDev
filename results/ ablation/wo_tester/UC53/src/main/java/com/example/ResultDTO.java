package com.example;

/**
 * Result object returned after feedback submission.
 */
public class ResultDTO {
    private boolean success;
    private String message;
    private String errorCode;

    public ResultDTO(boolean success, String message, String errorCode) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}