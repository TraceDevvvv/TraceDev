package com.example.domain;

import com.example.enums.AccountStatus;

/**
 * Represents a tourist entity in the system.
 */
public class Tourist {
    private String id;
    private String name;
    private String email;
    private AccountStatus accountStatus;

    public Tourist(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountStatus = AccountStatus.ACTIVE; // initial status is ACTIVE
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus status) {
        this.accountStatus = status;
    }

    @Override
    public String toString() {
        return "Tourist [id=" + id + ", name=" + name + ", email=" + email + ", accountStatus=" + accountStatus + "]";
    }
}