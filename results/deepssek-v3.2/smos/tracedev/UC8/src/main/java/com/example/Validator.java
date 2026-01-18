package com.example;

/**
 * Validator matching sequence diagram participant "Validator".
 */
public class Validator {
    private UserValidator userValidator;

    public Validator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    /**
     * Validate input syntax (e.g., email format) (message m13).
     */
    public ValidationResult validateInputSyntax(EditUserDTO dto) {
        return dto.validate();
    }

    /**
     * Validate business rules (e.g., unique email) (message m16).
     */
    public ValidationResult validateBusinessRules(EditUserDTO dto, User existingUser) {
        return userValidator.validateForEdit(dto, existingUser);
    }
}