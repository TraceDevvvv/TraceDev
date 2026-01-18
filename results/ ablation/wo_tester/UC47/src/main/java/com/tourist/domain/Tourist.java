package com.tourist.domain;

/**
 * Domain entity representing a Tourist.
 */
public class Tourist {
    private String touristId;
    private String name;
    private String email;
    private String phoneNumber;

    /**
     * Constructor.
     * @param id the tourist id
     * @param name the tourist name
     * @param email the tourist email
     * @param phone the tourist phone number
     */
    public Tourist(String id, String name, String email, String phone) {
        this.touristId = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
    }

    // Getters (no setters to enforce domain logic)

    public String getTouristId() {
        return touristId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Updates the tourist profile.
     * @param newName the new name
     * @param newEmail the new email
     * @param newPhone the new phone number
     */
    public void updateProfile(String newName, String newEmail, String newPhone) {
        this.name = newName;
        this.email = newEmail;
        this.phoneNumber = newPhone;
    }

    /**
     * Validates the tourist data.
     * @return true if valid, false otherwise
     */
    public boolean validate() {
        // Simple validation: fields should not be null or empty.
        return touristId != null && !touristId.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               phoneNumber != null && !phoneNumber.trim().isEmpty();
    }
}