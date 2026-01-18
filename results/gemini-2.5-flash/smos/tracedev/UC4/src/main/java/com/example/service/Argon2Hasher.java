package com.example.service;

/**
 * Concrete implementation of IPasswordHasher using a simulated Argon2 hashing algorithm.
 * For demonstration purposes, this uses simple string manipulation instead of real Argon2.
 */
public class Argon2Hasher implements IPasswordHasher {

    private static final String HASH_PREFIX = "argon2hashed_";

    @Override
    public String hashPassword(String plainPassword) {
        // Simulate Argon2 hashing by adding a prefix.
        // In a real application, this would involve a cryptographic hashing library.
        System.out.println("[Argon2Hasher] Hashing password...");
        return HASH_PREFIX + plainPassword;
    }

    @Override
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        // Simulate Argon2 password verification.
        // In a real application, this would involve comparing hashes using the library.
        System.out.println("[Argon2Hasher] Checking password...");
        return (HASH_PREFIX + plainPassword).equals(hashedPassword);
    }
}