package com.example.app.domain;

import com.example.app.dtos.UserCreationRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates user-related data, particularly for creation requests.
 */
public class UserValidator {
    // Basic email pattern regex
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * Validates a UserCreationRequestDTO.
     * Added to satisfy R10 (UserValidator validates UserCreationRequestDTO).
     *
     * @param request The DTO containing user creation data.
     * @return A list of validation error messages. Returns an empty list if validation passes.
     */
    public List<String> validateUserCreation(UserCreationRequestDTO request) {
        List<String> errors = new ArrayList<>();

        System.out.println("[UserValidator] Validating user creation request for login: " + request.login);

        if (request.name == null || request.name.trim().isEmpty()) {
            errors.add("Name cannot be empty.");
        }
        if (request.surname == null || request.surname.trim().isEmpty()) {
            errors.add("Surname cannot be empty.");
        }
        if (request.email == null || request.email.trim().isEmpty()) {
            errors.add("Email cannot be empty.");
        } else if (!isValidEmail(request.email)) {
            errors.add("Email format is invalid.");
        }
        // Cell can be optional, no validation for empty string.
        if (request.login == null || request.login.trim().isEmpty()) {
            errors.add("Login cannot be empty.");
        } else if (request.login.length() < 3) {
            errors.add("Login must be at least 3 characters long.");
        }
        if (request.password == null || request.password.trim().isEmpty()) {
            errors.add("Password cannot be empty.");
        } else if (request.password.length() < 6) {
            errors.add("Password must be at least 6 characters long.");
        }
        if (!request.password.equals(request.confirmPassword)) {
            errors.add("Password and Confirm Password do not match.");
        }

        if (!errors.isEmpty()) {
            System.out.println("[UserValidator] Validation failed with " + errors.size() + " errors.");
            errors.forEach(System.out::println);
        } else {
            System.out.println("[UserValidator] Validation successful.");
        }

        return errors;
    }

    /**
     * Helper method to validate email format.
     */
    private boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}