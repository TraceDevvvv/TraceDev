package com.example.account;

import java.util.UUID;

/**
 * Factory for creating Account objects.
 * Encapsulates the logic for constructing valid Account instances.
 */
public class AccountFactory {

    /**
     * Creates a new Account instance.
     * This method handles the generation of a unique ID and password hashing.
     *
     * @param username The desired username for the account.
     * @param email The email address for the account.
     * @param password The plain-text password (will be hashed internally).
     * @return A new Account object.
     */
    public Account createAccount(String username, String email, String password) {
        String id = UUID.randomUUID().toString(); // Generate a unique ID
        // In a real application, a robust password hashing algorithm (e.g., BCrypt) would be used.
        String passwordHash = hashPassword(password); // Hash the password
        return new Account(id, username, email, passwordHash);
    }

    /**
     * Placeholder for a password hashing function.
     * In a real application, this would use a secure hashing algorithm.
     *
     * @param password The plain-text password.
     * @return The hashed password.
     */
    private String hashPassword(String password) {
        // For demonstration, a simple "hashing" for now.
        // DO NOT use this in production.
        return password + "_hashed";
    }
}