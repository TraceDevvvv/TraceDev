package com.example.validation;

import com.example.dto.RefreshmentPointUpdateDTO;
import com.example.validation.ValidationResult;

/**
 * Interface for validating update DTOs.
 */
public interface IDataValidator {
    ValidationResult validateUpdateDTO(RefreshmentPointUpdateDTO dto);
}