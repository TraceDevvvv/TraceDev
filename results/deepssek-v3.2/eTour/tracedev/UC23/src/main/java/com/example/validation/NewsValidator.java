package com.example.validation;

import com.example.dto.NewsDTO;

/**
 * Validates NewsDTO objects.
 */
public class NewsValidator {

    /**
     * Validates the provided NewsDTO.
     * @param newsDTO the DTO to validate.
     * @return a ValidationResult object.
     */
    public ValidationResult validate(NewsDTO newsDTO) {
        ValidationResult result = new ValidationResult();
        // Check title rules
        if (newsDTO.getTitle() == null || newsDTO.getTitle().trim().isEmpty()) {
            result.addError("Title cannot be empty.");
        } else if (newsDTO.getTitle().length() > 200) {
            result.addError("Title cannot exceed 200 characters.");
        }
        // Check content rules
        if (newsDTO.getContent() == null || newsDTO.getContent().trim().isEmpty()) {
            result.addError("Content cannot be empty.");
        }
        return result;
    }
}