package com.convention.request.util;

import com.convention.request.exception.InvalidConventionDataException;
import com.convention.request.model.ConventionRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating ConventionRequestDTO objects.
 * This class provides custom validation logic beyond standard JSR 380 annotations,
 * especially for inter-field dependencies like start and end dates.
 */
@Component
public class ConventionValidator {

    // Basic email pattern for additional validation if needed, though @Email annotation is used
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Validates the provided ConventionRequestDTO.
     * This method performs custom business logic validations that might not be covered
     * by standard JSR 380 annotations or require cross-field checks.
     *
     * @param dto The ConventionRequestDTO to validate.
     * @throws InvalidConventionDataException if the DTO contains invalid or insufficient data.
     */
    public void validate(ConventionRequestDTO dto) {
        // JSR 380 annotations handle @NotBlank, @NotNull, @Size, @Email for individual fields.
        // This method focuses on logical consistency and cross-field validation.

        if (dto.getStartDate() == null) {
            throw new InvalidConventionDataException("Start date cannot be null.");
        }
        if (dto.getEndDate() == null) {
            throw new InvalidConventionDataException("End date cannot be null.");
        }

        // Validate that endDate is not before startDate
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new InvalidConventionDataException("End date cannot be before start date.");
        }

        // Optional: Add more specific business rules here
        // Example: Convention duration limits
        long duration = java.time.temporal.ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        if (duration < 0) { // Should be caught by previous check, but good for robustness
            throw new InvalidConventionDataException("Convention duration cannot be negative.");
        }
        if (duration > 365) { // Example: Max 1 year convention
            throw new InvalidConventionDataException("Convention duration cannot exceed 365 days.");
        }

        // Example: Validate contact email format more strictly if @Email is not enough
        if (dto.getContactEmail() != null && !EMAIL_PATTERN.matcher(dto.getContactEmail()).matches()) {
            throw new InvalidConventionDataException("Contact email format is invalid.");
        }

        // Example: Ensure requiredDocuments list is not empty if the convention type demands it
        // This would depend on specific business rules not defined in the PRD.
        // For now, we assume requiredDocuments can be null or empty if not specified.
    }
}