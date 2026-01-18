package com.system.validation;

/**
 * Abstract base class for validation rules.
 */
public abstract class ValidationRule {
    /**
     * Validates the input object.
     * @param input the object to validate
     * @return the validation result
     */
    public abstract ValidationResult validate(Object input);

    /**
     * Gets the error message for this rule.
     * @return the error message
     */
    public abstract String getErrorMessage();
}