package com.agency.domain;

/**
 * Strategy interface for password hashing and verification.
 * Allows different hashing algorithms to be plugged in.
 */
public interface IPasswordStrategy {
    /**
     * Hashes a plain password.
     * @param password Plain text password.
     * @return Hashed password.
     */
    String hashPassword(String password);

    /**
     * Verifies a plain password against a hashed password.
     * @param plainPassword Plain text password.
     * @param hashedPassword Hashed password.
     * @return true if they match, false otherwise.
     */
    boolean verifyPassword(String plainPassword, String hashedPassword);
}