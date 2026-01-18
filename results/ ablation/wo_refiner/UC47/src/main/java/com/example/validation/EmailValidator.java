package com.example.validation;

import com.example.dto.TouristDTO;

/**
 * Validates email field of TouristDTO.
 */
public class EmailValidator implements IDataValidator {
    @Override
    public ValidationResult validate(TouristDTO data) {
        ValidationResult result = new ValidationResult();
        String email = data.getEmail();
        if (email == null || email.isEmpty()) {
            result.addError("Email is required");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            result.addError("Email format is invalid");
        }
        return result;
    }
}