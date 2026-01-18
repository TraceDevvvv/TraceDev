package com.newsagency.system.utility;

import com.newsagency.system.dto.NewsFormDTO;
import com.newsagency.system.valueobject.ValidationResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility service that validates news form data.
 */
public class ValidationService {
    private static final int TITLE_MAX_LENGTH = 200;
    private static final int CONTENT_MAX_LENGTH = 5000;
    private static final int AUTHOR_MAX_LENGTH = 100;

    /**
     * Validates a news form DTO.
     * @param newsForm the form data
     * @return validation result with errors if any
     */
    public ValidationResult validateNewsForm(NewsFormDTO newsForm) {
        List<String> errors = new ArrayList<>();

        // Validate title
        if (isEmpty(newsForm.getTitle())) {
            errors.add("Title cannot be empty.");
        } else if (isTooLong(newsForm.getTitle(), TITLE_MAX_LENGTH)) {
            errors.add("Title cannot exceed " + TITLE_MAX_LENGTH + " characters.");
        }

        // Validate content
        if (isEmpty(newsForm.getContent())) {
            errors.add("Content cannot be empty.");
        } else if (isTooLong(newsForm.getContent(), CONTENT_MAX_LENGTH)) {
            errors.add("Content cannot exceed " + CONTENT_MAX_LENGTH + " characters.");
        }

        // Validate author
        if (isEmpty(newsForm.getAuthor())) {
            errors.add("Author cannot be empty.");
        } else if (isTooLong(newsForm.getAuthor(), AUTHOR_MAX_LENGTH)) {
            errors.add("Author cannot exceed " + AUTHOR_MAX_LENGTH + " characters.");
        }

        boolean valid = errors.isEmpty();
        return new ValidationResult(valid, errors);
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isTooLong(String str, int maxLength) {
        return str != null && str.length() > maxLength;
    }
}