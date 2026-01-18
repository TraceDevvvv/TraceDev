package com.example.application.dtos;

import com.example.domain.enums.AccountOperation;

/**
 * Data Transfer Object for the Update Account Status request.
 */
public class UpdateAccountStatusRequest {
    private String accountId;
    private AccountOperation operation;

    // Constructors
    public UpdateAccountStatusRequest() {
    }

    public UpdateAccountStatusRequest(String accountId, AccountOperation operation) {
        this.accountId = accountId;
        this.operation = operation;
    }

    // Getters and setters
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AccountOperation getOperation() {
        return operation;
    }

    public void setOperation(AccountOperation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "UpdateAccountStatusRequest{" +
                "accountId='" + accountId + '\'' +
                ", operation=" + operation +
                '}';
    }
}