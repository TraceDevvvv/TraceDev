package com.example.service;

/**
 * Domain Service for password validation.
 * Validates length of credentials (Flow of Events #1).
 */
public class PasswordValidator {
    private static final int MIN_LENGTH = 5;

    /**
     * Validates that the credential string meets minimum length requirement.
     */
    public boolean validateLength(String credential) {
        return credential != null && credential.length() >= MIN_LENGTH;
    }
}