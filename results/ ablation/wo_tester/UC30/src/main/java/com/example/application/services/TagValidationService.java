package com.example.application.serv;

import com.example.application.dto.InsertTagRequest;
import com.example.application.interfaces.ErrorRepository;
import com.example.domain.entities.Tag;
import com.example.application.interfaces.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for validating tag creation requests.
 * Ensures no duplicate tags for data integrity.
 */
public class TagValidationService {
    private ErrorRepository errorRepository;

    // Constructor injection for flexibility
    public TagValidationService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    /**
     * Validates the insert tag request.
     * Checks for duplicate tags and validates input fields.
     */
    public ValidationResult validate(InsertTagRequest request, TagRepository tagRepository) {
        // Assumption: Validation includes checking for duplicate tags and non-empty fields
        List<String> errors = new ArrayList<>();

        if (request.getTagName() == null || request.getTagName().trim().isEmpty()) {
            errors.add("Tag name cannot be empty.");
        }

        // Check for duplicate tag (ensures no duplicate tags)
        Optional<Tag> existingTag = tagRepository.findByName(request.getTagName());
        if (existingTag.isPresent()) {
            errors.add("Tag with name '" + request.getTagName() + "' already exists.");
        }

        if (errors.isEmpty()) {
            return new ValidationResult(true, errors);
        } else {
            // Log validation errors if needed (assumption: error repository used for logging)
            errorRepository.logError("VALIDATION_ERROR", String.join(", ", errors));
            return new ValidationResult(false, errors);
        }
    }
}