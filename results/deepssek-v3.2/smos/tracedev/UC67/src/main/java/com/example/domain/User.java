package com.example.domain;

/**
 * Domain entity containing core business data and rules
 * Note top: Domain entity containing core business data and rules
 */
public class User {
    private int userId;
    private String name;
    private String surname;
    private String mobilePhone;
    private String email;
    private String username;
    private String passwordHash;

    public User(String name, String surname, String mobilePhone, 
                String email, String username, String passwordHash) {
        this.name = name;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Validates the user entity
     * As per class diagram method
     * @return true if user is valid
     */
    public boolean validate() {
        // Basic domain validation
        return name != null && !name.trim().isEmpty() &&
               surname != null && !surname.trim().isEmpty() &&
               mobilePhone != null && !mobilePhone.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               username != null && !username.trim().isEmpty() &&
               passwordHash != null && !passwordHash.trim().isEmpty();
    }
}