package com.example;

/**
 * Enum to represent the result of banner verification.
 * Added to satisfy R7, R8, R9, R13.
 */
public enum VerificationResult {
    /**
     * Indicates that the banner limit has been exceeded.
     */
    LIMIT_EXCEEDED,
    /**
     * Indicates that the banner limit is not exceeded, and a banner can be added.
     */
    LIMIT_OK,
    /**
     * Indicates that a connection error occurred during verification.
     */
    CONNECTION_ERROR
}