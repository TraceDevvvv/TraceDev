package com.example.validation;

import com.example.dto.TouristDTO;

/**
 * Validates phone field of TouristDTO.
 */
public class PhoneValidator implements IDataValidator {
    @Override
    public ValidationResult validate(TouristDTO data) {
        ValidationResult result = new ValidationResult();
        String phone = data.getPhone();
        if (phone == null || phone.isEmpty()) {
            result.addError("Phone is required");
        } else if (!phone.matches("\\d{10}")) {
            result.addError("Phone must be 10 digits");
        }
        return result;
    }
}