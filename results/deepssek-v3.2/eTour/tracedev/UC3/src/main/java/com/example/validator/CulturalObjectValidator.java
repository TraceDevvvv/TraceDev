package com.example.validator;

import com.example.dto.CulturalObjectDTO;
import com.example.result.ValidationResult;

/**
 * Validator interface for CulturalObject updates.
 */
public interface CulturalObjectValidator {
    ValidationResult validateForUpdate(CulturalObjectDTO dto);
}