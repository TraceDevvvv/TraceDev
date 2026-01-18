package com.school;

/**
 * Enumeration of possible insertion results.
 */
public enum InsertionResult {
    SUCCESS,
    VALIDATION_ERROR,
    PERSISTENCE_ERROR,
    NETWORK_ERROR,
    USER_CANCELLED
}