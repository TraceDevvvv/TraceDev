package com.example.model;

import java.util.Date;

/**
 * Entity representing a user account.
 */
public class Account {
    private String accountId;
    private String username;
    private String email;
    private AccountStatus status;
    private Date createdAt;

    // Constructors
    public Account() {}

    public Account(String accountId, String username, String email, AccountStatus status, Date createdAt) {
        this.accountId = accountId;
        this.username = username;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Activates the account by setting its status to ACTIVE.
     */
    public void activate() {
        this.status = AccountStatus.ACTIVE;
    }
}