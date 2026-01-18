package com.example.validators;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator for comment data.
 * Performs simple validation as per the sequence diagram.
 */
public class CommentValidator {
    /**
     * Validates the given comment data.
     * Returns a list of error messages; empty list means validation passed.
     *
     * @param commentId the comment ID
     * @param touristId the tourist ID
     * @param siteId the site ID
     * @param content the content
     * @return list of validation errors (empty if none)
     */
    public List<String> validate(String commentId, String touristId, String siteId, String content) {
        List<String> errors = new ArrayList<>();
        if (commentId == null || commentId.trim().isEmpty()) {
            errors.add("Comment ID cannot be empty");
        }
        if (touristId == null || touristId.trim().isEmpty()) {
            errors.add("Tourist ID cannot be empty");
        }
        if (siteId == null || siteId.trim().isEmpty()) {
            errors.add("Site ID cannot be empty");
        }
        if (content == null || content.trim().isEmpty()) {
            errors.add("Content cannot be empty");
        } else if (content.length() > 500) { // arbitrary length limit
            errors.add("Content must be 500 characters or less");
        }
        return errors;
    }
}