package com.example.newsapp.service;

import com.example.newsapp.dto.NewsDTO;

/**
 * Validates NewsDTO objects based on business rules.
 * This class ensures that news data meets certain quality requirements
 * before being processed or persisted.
 */
public class NewsValidator {

    /**
     * Validates a given NewsDTO.
     *
     * @param newsDTO The NewsDTO to validate.
     * @return true if the NewsDTO is valid, false otherwise.
     */
    public boolean isValid(NewsDTO newsDTO) {
        System.out.println("[Validator] Validating NewsDTO: " + newsDTO.getId());

        // Basic validation rules:
        // 1. Title cannot be null or empty.
        if (newsDTO.getTitle() == null || newsDTO.getTitle().trim().isEmpty()) {
            System.out.println("[Validator] Validation failed: Title cannot be empty.");
            return false;
        }
        // 2. Content cannot be null or empty.
        if (newsDTO.getContent() == null || newsDTO.getContent().trim().isEmpty()) {
            System.out.println("[Validator] Validation failed: Content cannot be empty.");
            return false;
        }
        // 3. Author cannot be null or empty.
        if (newsDTO.getAuthor() == null || newsDTO.getAuthor().trim().isEmpty()) {
            System.out.println("[Validator] Validation failed: Author cannot be empty.");
            return false;
        }
        // 4. Publication date should not be in the future (simple check).
        // For simplicity, we are not checking for future dates as Date objects can be tricky.
        // A real system would use Calendar or LocalDate.

        // 5. Status must be one of the predefined values (example: Published, Draft, Archived).
        if (newsDTO.getStatus() != null &&
            !newsDTO.getStatus().equals("Published") &&
            !newsDTO.getStatus().equals("Draft") &&
            !newsDTO.getStatus().equals("Archived")) {
            System.out.println("[Validator] Validation failed: Invalid status value.");
            return false;
        }
        
        System.out.println("[Validator] Validation successful for NewsDTO: " + newsDTO.getId());
        return true;
    }
}