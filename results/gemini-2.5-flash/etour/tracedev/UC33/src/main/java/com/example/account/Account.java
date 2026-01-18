package com.example.account;

import java.util.UUID;

/**
 * Represents an Account entity in the domain model.
 * This class encapsulates the core data and business rules for an account.
 */
public class Account {
    private String id;
    private String username;
    private String email;
    private String passwordHash; // Storing password hash, not plain password

    /**
     * Private constructor to ensure accounts are created via a factory.
     *
     * @param id The unique identifier for the account.
     * @param username The account's username.
     * @param email The account's email address.
     * @param passwordHash The hashed password for the account.
     */
    Account(String id, String username, String email, String passwordHash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /**
     * Checks if the account object is in a valid state.
     * (Placeholder implementation)
     *
     * @return true if the account is considered valid, false otherwise.
     */
    public boolean isValid() {
        // Basic validation: ensure essential fields are not null or empty
        return id != null && !id.trim().isEmpty() &&
               username != null && !username.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               passwordHash != null && !passwordHash.trim().isEmpty();
    }

    // Getters for properties
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    // No setters for security-sensitive fields or immutable fields after creation.
    // Password updates would typically go through a dedicated method.
}