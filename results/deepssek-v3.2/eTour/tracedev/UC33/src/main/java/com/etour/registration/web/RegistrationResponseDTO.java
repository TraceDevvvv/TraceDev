package com.etour.registration.web;

/**
 * Data Transfer Object for registration response.
 */
public class RegistrationResponseDTO {
    private boolean success;
    private String message;
    private String accountId;

    public RegistrationResponseDTO(boolean success, String message, String accountId) {
        this.success = success;
        this.message = message;
        this.accountId = accountId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccountId() {
        return accountId;
    }
}