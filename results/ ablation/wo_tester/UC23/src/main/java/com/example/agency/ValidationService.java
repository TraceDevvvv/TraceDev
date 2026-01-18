package com.example.agency;

/**
 * Service for validating news data.
 */
public class ValidationService {
    public ValidationResult validateNews(NewsDTO newsDTO) {
        ValidationResult result = new ValidationResult();
        
        if (newsDTO.getTitle() == null || newsDTO.getTitle().trim().isEmpty()) {
            result.addError("Title cannot be empty");
        }
        if (newsDTO.getContent() == null || newsDTO.getContent().trim().isEmpty()) {
            result.addError("Content cannot be empty");
        }
        if (newsDTO.getAuthor() == null || newsDTO.getAuthor().trim().isEmpty()) {
            result.addError("Author cannot be empty");
        }
        
        return result;
    }
}