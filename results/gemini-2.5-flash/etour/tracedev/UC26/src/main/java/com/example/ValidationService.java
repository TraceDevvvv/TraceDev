package com.example;

/**
 * Service for validating various input data.
 */
public class ValidationService {

    /**
     * Validates a given comment string.
     * For this simulation, a comment is considered valid if it's not null, not empty,
     * and not just whitespace.
     * @param comment The comment string to validate.
     * @return true if the comment is valid, false otherwise.
     */
    public boolean validateComment(String comment) {
        // Simple validation: comment must not be null, empty, or just whitespace.
        boolean isValid = comment != null && !comment.trim().isEmpty();
        if (!isValid) {
            System.out.println("ValidationService: Comment validation failed (cannot be empty).");
        }
        return isValid;
    }
}