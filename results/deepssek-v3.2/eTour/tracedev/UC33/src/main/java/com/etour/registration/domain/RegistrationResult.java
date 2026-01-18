package com.etour.registration.domain;

/**
 * Result of a registration operation.
 */
public class RegistrationResult {
    private boolean success;
    private String accountId;
    private String message;

    public RegistrationResult(boolean success, String accountId, String message) {
        this.success = success;
        this.accountId = accountId;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getMessage() {
        return message;
    }
}