package com.example.domain;

import com.example.domain.enums.AccountStatus;
import com.example.domain.enums.AccountOperation;

/**
 * Core domain entity representing a Tourist Account.
 * Implements business logic for activation and deactivation.
 */
public class TouristAccount {
    private String id;
    private String name;
    private String email;
    private boolean isActive;
    private AccountStatus status;

    // Constructors
    public TouristAccount() {
        // Default constructor
    }

    public TouristAccount(String id, String name, String email, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
        this.status = isActive ? AccountStatus.ACTIVE : AccountStatus.INACTIVE;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
        // Synchronize isActive with status
        this.isActive = (status == AccountStatus.ACTIVE);
    }

    // Business methods
    public void activate() {
        this.status = AccountStatus.ACTIVE;
        this.isActive = true;
        // Additional activation logic could be added here
    }

    public void deactivate() {
        this.status = AccountStatus.INACTIVE;
        this.isActive = false;
        // Additional deactivation logic could be added here
    }

    @Override
    public String toString() {
        return "TouristAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", status=" + status +
                '}';
    }
}