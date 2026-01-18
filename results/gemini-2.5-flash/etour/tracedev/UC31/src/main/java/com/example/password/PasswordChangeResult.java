package com.example.password;

/**
 * Represents the possible outcomes of a password change operation.
 * Used by the UseCase to communicate specific results back to the Controller.
 */
public enum PasswordChangeResult {
    SUCCESS,
    INPUT_MISMATCH,
    POLICY_VIOLATION,
    ACCOUNT_NOT_FOUND
}