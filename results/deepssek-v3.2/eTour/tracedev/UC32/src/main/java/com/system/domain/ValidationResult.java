package com.system.domain;

/**
 * Enumeration of possible validation results during password change.
 */
public enum ValidationResult {
    SUCCESS,
    CONFIRMATION_MISMATCH,
    WEAK_PASSWORD,
    SAME_AS_CURRENT
}