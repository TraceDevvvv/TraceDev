package com.example.domain;

import com.example.presentation.TouristForm;

import java.util.Date;
import java.util.Objects; // For Objects.requireNonNullElse

/**
 * Domain Layer: Represents the core data entity for a Tourist.
 * Contains business logic related to the Tourist's attributes.
 */
public class TouristData {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Date birthDate;

    /**
     * Constructor for TouristData.
     * @param id The unique identifier for the tourist.
     * @param name The name of the tourist.
     * @param email The email address of the tourist.
     * @param phoneNumber The phone number of the tourist.
     * @param birthDate The birth date of the tourist.
     */
    public TouristData(String id, String name, String email, String phoneNumber, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    // Getters
    public String getId() {
        return id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    // Setters (for internal domain use or controlled updates)
    // Note: ID should typically not be changed once set.
    private void setName(String name) {
        this.name = name;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Updates the tourist's data using information from a TouristForm.
     * Corresponds to `updateFromForm(formData: TouristForm)` in the class diagram.
     * This method contains the domain logic for applying form updates to the entity.
     * @param formData The form data containing the updated information.
     */
    public void updateFromForm(TouristForm formData) {
        System.out.println("TouristData: Updating entity for ID " + this.id + " from form data.");
        // Apply updates only if the form data is not null
        if (formData.name != null) {
            this.setName(formData.name);
        }
        if (formData.email != null) {
            this.setEmail(formData.email);
        }
        if (formData.phoneNumber != null) {
            this.setPhoneNumber(formData.phoneNumber);
        }
        if (formData.birthDate != null) {
            this.setBirthDate(formData.birthDate);
        }
        System.out.println("TouristData: Entity for ID " + this.id + " updated.");
    }

    @Override
    public String toString() {
        return "TouristData{" +
               "id='" + Objects.requireNonNullElse(id, "null") + '\'' +
               ", name='" + Objects.requireNonNullElse(name, "null") + '\'' +
               ", email='" + Objects.requireNonNullElse(email, "null") + '\'' +
               ", phoneNumber='" + Objects.requireNonNullElse(phoneNumber, "null") + '\'' +
               ", birthDate=" + birthDate +
               '}';
    }
}