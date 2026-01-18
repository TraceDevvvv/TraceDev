package com.system.validation;

import java.util.Map;

/**
 * Interface for validating form data.
 */
public interface IValidator {
    ValidationResult validate(Map<String, Object> formData);
}