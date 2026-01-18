package com.example.model;

/**
 * Value Object representing the result of an operation.
 */
public class OperationResult {
    private boolean isSuccess;
    private String message;
    private String errorCode;

    public OperationResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.errorCode = null;
    }

    public OperationResult(boolean isSuccess, String message, String errorCode) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.errorCode = errorCode;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}