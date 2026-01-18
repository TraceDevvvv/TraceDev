package com.example.account;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validator for account registration requests.
 * Contains business rules for validating input data.
 */
public class AccountValidator {
    private List<String> errors;

    public AccountValidator() {
        this.errors = new ArrayList<>();
    }

    /**
     * Validates the provided registration request DTO.
     *
     * @param request The RegistrationRequestDTO to validate.
     * @return true if the request is valid, false otherwise.
     */
    public boolean validate(RegistrationRequestDTO request) {
        errors.clear(); // Clear previous errors

        if (request == null) {
            errors.add("Registration request cannot be null.");
            return false;
        }

        // Validate username
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            errors.add("Username cannot be empty.");
        } else if (request.getUsername().length() < 3 || request.getUsername().length() > 20) {
            errors.add("Username must be between 3 and 20 characters.");
        }
        // Additional username validation, e.g., allowed characters, uniqueness (could be done in use case)

        // Validate email
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            errors.add("Email cannot be empty.");
        } else if (!isValidEmail(request.getEmail())) {
            errors.add("Invalid email format.");
        }

        // Validate password
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            errors.add("Password cannot be empty.");
        } else if (request.getPassword().length() < 8) {
            errors.add("Password must be at least 8 characters long.");
        }
        // Additional password strength rules (e.g., complexity)

        return errors.isEmpty();
    }

    /**
     * Returns a list of validation errors.
     *
     * @return A list of error messages.
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors); // Return a copy to prevent external modification
    }

    /**
     * Basic email format validation using a regular expression.
     *
     * @param email The email string to validate.
     * @return true if the email format is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        // A more robust regex might be needed for production
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        return emailPattern.matcher(email).matches();
    }
}