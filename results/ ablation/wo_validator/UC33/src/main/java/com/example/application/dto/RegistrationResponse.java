package com.example.application.dto;

/**
 * Data transfer object for registration response.
 */
public class RegistrationResponse {
    private boolean success;
    private String message;
    private String accountId;

    public RegistrationResponse(boolean success, String message, String accountId) {
        this.success = success;
        this.message = message;
        this.accountId = accountId;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}