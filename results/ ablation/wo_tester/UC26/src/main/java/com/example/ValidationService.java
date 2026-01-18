package com.example;

/**
 * Service for validating comments and feedback existence.
 */
public class ValidationService {
    public boolean validateComment(String comment) {
        return comment != null && !comment.trim().isEmpty();
    }

    public boolean validateFeedbackExists(String feedbackId) {
        // Simulate checking if feedback exists in the database.
        // For simplicity, assume feedback exists if ID is not null and not empty.
        return feedbackId != null && !feedbackId.trim().isEmpty();
    }
}