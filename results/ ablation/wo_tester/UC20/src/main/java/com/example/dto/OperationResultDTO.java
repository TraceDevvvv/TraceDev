package com.example.dto;

/**
 * DTO for operation result.
 */
public class OperationResultDTO {
    private boolean success;
    private String message;
    private String errorCode;
    private int retryAttempts;

    public OperationResultDTO(boolean success, String message, String errorCode, int retryAttempts) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.retryAttempts = retryAttempts;
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
    }
}