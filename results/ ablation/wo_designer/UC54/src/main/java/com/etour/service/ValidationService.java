package com.etour.service;

/**
 * Service for validating input data.
 */
public class ValidationService {
    /**
     * Validates comment text.
     * @param text the comment text to validate
     * @return true if text is valid (not null, not empty, and within length limits)
     */
    public boolean isValidCommentText(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        // Assume reasonable length limits for a comment.
        int minLength = 1;
        int maxLength = 2000;
        return text.length() >= minLength && text.length() <= maxLength;
    }
}