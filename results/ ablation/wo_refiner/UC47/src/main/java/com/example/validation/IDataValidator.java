package com.example.validation;

import com.example.dto.TouristDTO;

/**
 * Strategy interface for data validation (REQ-015).
 */
public interface IDataValidator {
    ValidationResult validate(TouristDTO data);
}