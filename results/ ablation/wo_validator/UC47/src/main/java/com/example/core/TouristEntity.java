package com.example.core;

/**
 * Core business entity containing tourist profile data and basic validation logic.
 */
public class TouristEntity {
    private String userId;
    private String username;
    private String email;
    private String phone;
    private String passwordHash;

    public TouristEntity(String userId, String username, String email, String phone, String passwordHash) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
    }

    public String getUserId() {
        return userId;
    }

    public void updateProfileData(String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public boolean validate() {
        // Basic validation: non-null and non-empty fields
        return userId != null && !userId.trim().isEmpty() &&
               username != null && !username.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               phone != null && !phone.trim().isEmpty() &&
               passwordHash != null && !passwordHash.trim().isEmpty();
    }

    // Getters for fields (used by repository and other layers)
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}