package com.example.app.domain;

/**
 * Service for hashing and verifying passwords.
 * In a real application, this would use a strong, secure hashing algorithm (e.g., BCrypt).
 * For this example, it uses a simplified placeholder.
 */
public class PasswordHasher {

    /**
     * Hashes a raw password.
     * Placeholder implementation.
     *
     * @param rawPassword The plain text password.
     * @return A "hashed" version of the password.
     */
    public String hashPassword(String rawPassword) {
        System.out.println("[PasswordHasher] Hashing password...");
        // In a real application, use a secure hashing algorithm like BCrypt
        return rawPassword + "_hashed_securely";
    }

    /**
     * Checks if a raw password matches a hashed password.
     * Placeholder implementation.
     *
     * @param rawPassword The plain text password.
     * @param hashedPassword The stored hashed password.
     * @return true if they match, false otherwise.
     */
    public boolean checkPassword(String rawPassword, String hashedPassword) {
        System.out.println("[PasswordHasher] Checking password...");
        // In a real application, this would use the same hashing algorithm
        // to hash rawPassword and compare with hashedPassword.
        return hashPassword(rawPassword).equals(hashedPassword);
    }
}