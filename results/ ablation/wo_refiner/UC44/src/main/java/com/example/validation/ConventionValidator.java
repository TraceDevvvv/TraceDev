package com.example.validation;

import com.example.dto.ConventionRequestDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Validates convention requests.
 */
public class ConventionValidator {
    /**
     * Validates the convention request DTO.
     * Assumption: Performs basic validation rules.
     */
    public ValidationResult validate(ConventionRequestDTO requestDto) {
        List<String> errors = new ArrayList<>();

        // Basic validation rules (example rules)
        if (requestDto.getPointOfRestId() <= 0) {
            errors.add("Invalid point of rest ID");
        }
        if (requestDto.getAgencyId() <= 0) {
            errors.add("Invalid agency ID");
        }
        if (requestDto.getStartDate() == null || requestDto.getEndDate() == null) {
            errors.add("Dates cannot be null");
        } else if (requestDto.getStartDate().after(requestDto.getEndDate())) {
            errors.add("Start date must be before end date");
        }
        if (requestDto.getTerms() == null || requestDto.getTerms().trim().isEmpty()) {
            errors.add("Terms cannot be empty");
        }

        boolean isValid = errors.isEmpty();
        return new ValidationResult(isValid, errors);
    }
}