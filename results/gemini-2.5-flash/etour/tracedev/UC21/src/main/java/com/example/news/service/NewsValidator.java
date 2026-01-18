package com.example.news.service;

import com.example.news.dto.NewsFormRequest;
import com.example.news.util.ValidationResult;

/**
 * Service responsible for validating NewsFormRequest DTOs.
 */
public class NewsValidator {

    /**
     * Validates the fields of a NewsFormRequest.
     *
     * @param request The NewsFormRequest DTO to validate.
     * @return A ValidationResult indicating whether the request is valid and any errors found.
     */
    public ValidationResult validate(NewsFormRequest request) {
        ValidationResult result = new ValidationResult();

        if (request == null) {
            result.addError("News form request cannot be null.");
            return result;
        }

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            result.addError("Title cannot be empty.");
        }
        if (request.getTitle() != null && request.getTitle().length() > 255) {
            result.addError("Title cannot exceed 255 characters.");
        }

        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            result.addError("Content cannot be empty.");
        }
        // Assuming a minimum content length for meaningful news
        if (request.getContent() != null && request.getContent().length() < 10) {
            result.addError("Content must be at least 10 characters long.");
        }

        if (request.getAuthorId() == null || request.getAuthorId().trim().isEmpty()) {
            result.addError("Author ID cannot be empty.");
        }
        // Assuming authorId follows a specific format, e.g., alphanumeric
        if (request.getAuthorId() != null && !request.getAuthorId().matches("^[a-zA-Z0-9]+$")) {
            result.addError("Author ID must be alphanumeric.");
        }

        return result;
    }
}