package adapters;

import application.ValidationResult;
import java.util.List;

/**
 * Validator for refreshment point DTOs.
 */
public class RefreshmentPointValidator {

    /**
     * Validates the DTO.
     * @param dto the DTO to validate.
     * @return the validation result.
     */
    public ValidationResult validate(RefreshmentPointDTO dto) {
        ValidationResult result = new ValidationResult();
        // Check data completeness
        if (!isDataComplete(dto)) {
            result.addError("Data is incomplete");
        }
        // Additional validation rules can be added here
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            result.addError("Name is required");
        }
        if (dto.getAddress() == null || dto.getAddress().trim().isEmpty()) {
            result.addError("Address is required");
        }
        if (dto.getStatus() == null || dto.getStatus().trim().isEmpty()) {
            result.addError("Status is required");
        }
        return result;
    }

    /**
     * Checks if the DTO data is complete.
     * @param dto the DTO to check.
     * @return true if complete, false otherwise.
     */
    public boolean isDataComplete(RefreshmentPointDTO dto) {
        return dto.getId() != null && !dto.getId().trim().isEmpty() &&
               dto.getName() != null && !dto.getName().trim().isEmpty() &&
               dto.getAddress() != null && !dto.getAddress().trim().isEmpty() &&
               dto.getStatus() != null && !dto.getStatus().trim().isEmpty();
    }
}