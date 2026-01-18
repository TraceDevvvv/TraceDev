package com.example.app.service;

import com.example.app.dto.UserFormDto;

/**
 * Provides validation serv for user data.
 * This class corresponds to the 'ValidationService' in the UML Class Diagram.
 * It ensures data integrity as per R11.
 */
public class ValidationService {

    /**
     * Validates the provided user data.
     * For demonstration, it performs a simple check for non-empty username and valid email format.
     * @param userData The UserFormDto to validate.
     * @return true if the data is valid, false otherwise.
     */
    public boolean validate(UserFormDto userData) {
        System.out.println("ValidationService: Validating user data for: " + userData.getUsername());

        // Example validation rules (R11: Data integrity)
        if (userData.getUsername() == null || userData.getUsername().trim().isEmpty()) {
            System.err.println("ValidationService: Validation failed - Username cannot be empty.");
            return false;
        }
        if (userData.getEmail() == null || userData.getEmail().trim().isEmpty()) {
            System.err.println("ValidationService: Validation failed - Email cannot be empty.");
            return false;
        }
        // Simple email regex for demonstration
        if (!userData.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            System.err.println("ValidationService: Validation failed - Invalid email format.");
            return false;
        }
        // Add more validation rules as needed (e.g., password strength, ID format)

        System.out.println("ValidationService: User data is valid.");
        return true;
    }
}