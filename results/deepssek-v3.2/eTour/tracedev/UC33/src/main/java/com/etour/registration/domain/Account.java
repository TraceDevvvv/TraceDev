package com.etour.registration.domain;

import java.util.UUID;

/**
 * Domain entity representing a user account.
 */
public class Account {
    private String accountId;
    private String username;
    private String email;
    private String passwordHash;
    private AccountStatus status;

    public Account(String username, String email, String passwordHash) {
        this.accountId = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = AccountStatus.PENDING; // default status
    }

    public String getId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    /**
     * Validates the account data (simplified validation).
     * @return true if account is valid
     */
    public boolean validate() {
        return username != null && !username.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               passwordHash != null && !passwordHash.trim().isEmpty();
    }

    // Getters for missing attributes from class diagram
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}