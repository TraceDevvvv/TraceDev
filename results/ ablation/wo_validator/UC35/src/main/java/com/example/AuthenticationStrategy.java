package com.example;

/**
 * Interface for password hashing and verification strategies.
 */
public interface AuthenticationStrategy {
    /**
     * Verifies an input password against a stored hash.
     * @param inputPassword the password to verify
     * @param storedHash the stored hash to compare against
     * @return true if the password matches the hash, false otherwise
     */
    boolean verify(String inputPassword, String storedHash);

    /**
     * Generates a hash from a plain password.
     * @param password the password to hash
     * @return the generated hash
     */
    String generateHash(String password);
}