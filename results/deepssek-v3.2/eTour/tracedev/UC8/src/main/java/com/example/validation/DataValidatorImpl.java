package com.example.validation;

import com.example.dto.RefreshmentPointUpdateDTO;

/**
 * Concrete validator implementing IDataValidator.
 */
public class DataValidatorImpl implements IDataValidator {
    @Override
    public ValidationResult validateUpdateDTO(RefreshmentPointUpdateDTO dto) {
        ValidationResult result = new ValidationResult(true);
        if (dto == null) {
            result.addErrorMessage("Update DTO cannot be null.");
            result.setValid(false);
            return result;
        }
        if (!dto.isValid()) {
            result.addErrorMessage("At least one field must be updated.");
            result.setValid(false);
        }
        // Example: name length validation
        if (dto.getUpdatedName() != null && dto.getUpdatedName().length() > 100) {
            result.addErrorMessage("Name must not exceed 100 characters.");
            result.setValid(false);
        }
        // Add more validation rules as needed.
        return result;
    }
}