package com.etour.registration.application;

import com.etour.registration.domain.CreateAccountCommand;
import com.etour.registration.domain.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates registration data.
 */
public class RegistrationDataValidator {
    public ValidationResult validate(CreateAccountCommand command) {
        List<String> errors = new ArrayList<>();

        if (command.getUsername() == null || command.getUsername().trim().isEmpty()) {
            errors.add("Username is required.");
        } else if (command.getUsername().length() < 3) {
            errors.add("Username must be at least 3 characters.");
        }

        if (command.getEmail() == null || command.getEmail().trim().isEmpty()) {
            errors.add("Email is required.");
        } else if (!command.getEmail().contains("@")) {
            errors.add("Email must be valid.");
        }

        if (command.getPassword() == null || command.getPassword().trim().isEmpty()) {
            errors.add("Password is required.");
        } else if (command.getPassword().length() < 6) {
            errors.add("Password must be at least 6 characters.");
        }

        return new ValidationResult(errors.isEmpty(), errors);
    }

    // New method for sequence diagram traceability
    public ValidationResult validateCommand(CreateAccountCommand command) {
        return validate(command);
    }
}