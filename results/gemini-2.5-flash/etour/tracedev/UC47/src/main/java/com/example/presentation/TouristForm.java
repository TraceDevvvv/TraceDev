package com.example.presentation;

import java.util.Date;
import java.util.Objects; // For Objects.requireNonNullElse

/**
 * Presentation Layer: Represents the data structure for the tourist account editing form.
 * Used to capture user input from the UI.
 */
public class TouristForm {
    public String name;
    public String email;
    public String phoneNumber;
    public Date birthDate;

    public TouristForm(String name, String email, String phoneNumber, Date birthDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    /**
     * Validates the data contained within the form.
     * This is a basic validation. More complex validation might occur in ValidationService.
     * @return true if the form data is valid, false otherwise.
     */
    public boolean isValid() {
        // Basic validation: check if name and email are not null/empty
        System.out.println("TouristForm: Performing basic form validation.");
        return name != null && !name.trim().isEmpty() &&
               email != null && !email.trim().isEmpty();
               // Add more sophisticated checks for email format, phone number, etc. if needed
    }

    @Override
    public String toString() {
        return "TouristForm{" +
               "name='" + Objects.requireNonNullElse(name, "null") + '\'' +
               ", email='" + Objects.requireNonNullElse(email, "null") + '\'' +
               ", phoneNumber='" + Objects.requireNonNullElse(phoneNumber, "null") + '\'' +
               ", birthDate=" + birthDate +
               '}';
    }
}