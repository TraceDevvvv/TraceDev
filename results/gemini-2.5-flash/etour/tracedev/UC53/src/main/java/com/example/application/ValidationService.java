package com.example.application;

import com.example.domain.FeedbackFormDTO; // Keep for now if other methods use it, but not for validateFeedbackForm

/**
 * Service responsible for validating feedback form data.
 */
public class ValidationService {

    /**
     * Validates the provided feedback data.
     * Checks if vote is within a valid range and comment is not empty.
     * Aligned with sequence diagram message: validateFeedbackForm(siteId, vote, comment).
     * @param siteId The ID of the site (not used in current validation logic, but part of SD signature).
     * @param vote The rating given.
     * @param comment The textual comment.
     * @return true if the data is valid, false otherwise.
     */
    public boolean validateFeedbackForm(String siteId, int vote, String comment) {
        System.out.println("[ValidationService] Validating feedback form data...");

        // Validate vote: Must be between 1 and 5 (inclusive)
        if (vote < 1 || vote > 5) {
            System.out.println("[ValidationService] Validation failed: Vote must be between 1 and 5.");
            return false;
        }

        // Validate comment: Must not be null or empty/whitespace
        if (comment == null || comment.trim().isEmpty()) {
            System.out.println("[ValidationService] Validation failed: Comment cannot be empty.");
            return false;
        }
        
        // Site ID validation (if needed, though not specified in this simple example)
        if (siteId == null || siteId.trim().isEmpty()) {
             System.out.println("[ValidationService] Validation failed: Site ID cannot be empty.");
             return false;
        }

        System.out.println("[ValidationService] Feedback form data is valid.");
        return true;
    }
}