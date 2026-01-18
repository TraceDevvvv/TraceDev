package com.example.application;

import com.example.presentation.TouristForm;

/**
 * Application Layer (EnsuresIntegrity): Provides data validation serv.
 * This class ensures that data conforms to business rules and integrity constraints.
 */
public class ValidationService {

    /**
     * Validates the provided tourist form data.
     * Corresponds to `validateTouristData(touristData: TouristForm)` in the class diagram.
     * Stereotype <<EnsuresIntegrity>> implies this is critical for data quality.
     * @param touristForm The form data to validate.
     * @return true if the data is valid, false otherwise.
     */
    public boolean validateTouristData(TouristForm touristForm) {
        System.out.println("ValidationService: Validating tourist form data.");

        // Check basic validity using TouristForm's own method first
        if (!touristForm.isValid()) {
            System.out.println("ValidationService: Basic form validation failed.");
            return false;
        }

        // Example: More sophisticated validation rules can be added here
        // - Email format check (regex)
        // - Phone number format check
        // - Birth date in a reasonable range
        // - Name does not contain forbidden characters

        if (!isValidEmail(touristForm.email)) {
            System.out.println("ValidationService: Invalid email format: " + touristForm.email);
            return false;
        }

        if (!isValidPhoneNumber(touristForm.phoneNumber)) {
            System.out.println("ValidationService: Invalid phone number format: " + touristForm.phoneNumber);
            return false;
        }

        System.out.println("ValidationService: Tourist form data is valid.");
        return true;
    }

    /**
     * Helper method to validate email format.
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        // Simple regex for email validation
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    /**
     * Helper method to validate phone number format.
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return true; // Assuming phone number can be optional, or it's validated elsewhere
        }
        // Simple regex for phone number validation (e.g., digits and optional hyphens/spaces)
        return phoneNumber.matches("^[\\d\\s\\-()]{7,20}$");
    }
}