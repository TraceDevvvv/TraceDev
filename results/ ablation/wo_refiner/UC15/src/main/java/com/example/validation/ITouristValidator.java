package com.example.validation;

import com.example.dto.TouristDTO;

/**
 * Interface for validating TouristDTO.
 */
public interface ITouristValidator {
    ValidationResult validate(TouristDTO tourist);
}