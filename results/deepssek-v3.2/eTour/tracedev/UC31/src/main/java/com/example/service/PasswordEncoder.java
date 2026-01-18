package com.example.service;

/**
 * Utility for password encoding, hashing, and verification.
 */
public class PasswordEncoder {

    public String generateSalt() {
        // Simplified: generate a random salt (dummy implementation)
        return "generatedSalt" + System.currentTimeMillis();
    }

    public String hash(String password, String salt) {
        // Simplified: compute a hash (dummy implementation)
        // In real application, use a secure hashing algorithm like bcrypt.
        return "hash(" + password + "+" + salt + ")";
    }

    public boolean verify(String input, String hash, String salt) {
        // Simplified: verify by re-hashing and comparing (dummy implementation)
        String computedHash = hash(input, salt);
        return computedHash.equals(hash);
    }
}