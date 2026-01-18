package com.example.delay;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that validates change delay commands for data integrity.
 * Trace: QualityRequirement.DataIntegrity
 */
public class ChangeDelayValidator {
    /**
     * Validates the command.
     * @param command The command to validate.
     * @return ValidationResult indicating validity and any errors.
     */
    public ValidationResult validate(ChangeDelayCommand command) {
        List<String> errors = new ArrayList<>();

        // Check date not null
        if (command.getDate() == null) {
            errors.add("Date cannot be null");
        }

        // Check delay is non-negative (assumption: delay is minutes, cannot be negative)
        if (command.getDelay() < 0) {
            errors.add("Delay cannot be negative");
        }

        // Additional integrity checks could be added here.
        // Implement check data integrity (sequence diagram message m9)
        System.out.println("Validator checking data integrity for command.");

        boolean isValid = errors.isEmpty();
        return new ValidationResult(isValid, errors);
    }
}