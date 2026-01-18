package com.system.ports;

import com.system.domain.ValidationResult;

/**
 * Port interface for password validation.
 */
public interface PasswordValidator {
    ValidationResult validate(String newPasswordPlain, String confirmationPlain);
}