package com.school;

/**
 * Validates class form data.
 */
public class ClassValidator {
    /**
     * Validates the DTO.
     * @param dto the data to validate.
     * @return validation result enum.
     */
    public ValidationResult validate(ClassFormDTO dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            return ValidationResult.INVALID_NAME;
        }
        if (dto.getAddress() == null || dto.getAddress().trim().isEmpty()) {
            return ValidationResult.INVALID_ADDRESS;
        }
        // Assume academic year must be between 2000 and 2100.
        if (dto.getAcademicYear() < 2000 || dto.getAcademicYear() > 2100) {
            return ValidationResult.INVALID_YEAR;
        }
        return ValidationResult.VALID;
    }

    /**
     * Returns a user-friendly error message for a validation result.
     * Added to satisfy quality requirement for error messages.
     * @param result the validation result.
     * @return error message string.
     */
    public String getErrorMessage(ValidationResult result) {
        switch (result) {
            case INVALID_NAME:
                return "Invalid name";
            case INVALID_ADDRESS:
                return "Invalid address";
            case INVALID_YEAR:
                return "Invalid academic year";
            case VALID:
                return "No error";
            default:
                return "Unknown validation error";
        }
    }
}