package com.example.domain;

/**
 * Domain entity representing an account.
 * Contains core account information and basic validation.
 */
public class Account {
    private String username;
    private String password;
    private String email;
    private boolean isActive;

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isActive = false; // default inactive until activated
    }

    /**
     * Validates the account data.
     * Assumption: simple validation checking non-empty fields and basic email format.
     * @return true if valid, false otherwise.
     */
    public boolean validate() {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            return false;
        }
        return true;
    }

    public void activate() {
        this.isActive = true;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    // Additional getters/setters if needed (not in UML, but may be required)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}