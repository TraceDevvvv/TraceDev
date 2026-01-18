package com.example.validator;

import com.example.dto.CulturalObjectDTO;
import com.example.result.ValidationResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic implementation of CulturalObjectValidator.
 */
public class BasicCulturalObjectValidator implements CulturalObjectValidator {
    @Override
    public ValidationResult validateForUpdate(CulturalObjectDTO dto) {
        List<String> errors = new ArrayList<>();
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            errors.add("Name is required");
        }
        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            errors.add("Description is required");
        }
        if (dto.getLocation() == null || dto.getLocation().trim().isEmpty()) {
            errors.add("Location is required");
        }
        boolean valid = errors.isEmpty();
        return new ValidationResult(valid, errors);
    }
}