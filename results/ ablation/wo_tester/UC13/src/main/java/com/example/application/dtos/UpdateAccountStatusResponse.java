package com.example.application.dtos;

import com.example.domain.enums.AccountStatus;

/**
 * Data Transfer Object for the Update Account Status response.
 */
public class UpdateAccountStatusResponse {
    private boolean success;
    private String message;
    private AccountStatus updatedStatus;

    // Constructors
    public UpdateAccountStatusResponse() {
    }

    public UpdateAccountStatusResponse(boolean success, String message, AccountStatus updatedStatus) {
        this.success = success;
        this.message = message;
        this.updatedStatus = updatedStatus;
    }

    // Getters and setters
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

    public AccountStatus getUpdatedStatus() {
        return updatedStatus;
    }

    public void setUpdatedStatus(AccountStatus updatedStatus) {
        this.updatedStatus = updatedStatus;
    }

    @Override
    public String toString() {
        return "UpdateAccountStatusResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", updatedStatus=" + updatedStatus +
                '}';
    }
}