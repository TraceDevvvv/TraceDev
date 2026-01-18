package com.example.service;

/**
 * Secure password handling service interface.
 * Stereotype <<secure>> indicates quality requirement for security.
 */
public interface PasswordService {
    /**
     * Validates that a password meets strength requirements.
     *
     * @param password the plaintext password
     * @return true if the password is valid
     */
    boolean validatePassword(String password);

    /**
     * Produces a secure hash of a plaintext password.
     *
     * @param password the plaintext password
     * @return the hashed password
     */
    String hashPassword(String password);

    /**
     * Verifies that a plaintext password matches a hash.
     *
     * @param password the plaintext password
     * @param hash the stored hash
     * @return true if the password matches the hash
     */
    boolean verifyPassword(String password, String hash);
}