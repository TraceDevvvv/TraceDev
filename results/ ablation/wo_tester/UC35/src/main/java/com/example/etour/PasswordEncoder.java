package com.example.etour;

/**
 * Secure password encoder.
 */
public class PasswordEncoder {
    /**
     * Encodes a raw password.
     * @param password the raw password.
     * @return encoded password.
     */
    public String encode(String password) {
        // Simple hash simulation.
        return "hashed_" + password;
    }

    /**
     * Checks if raw password matches encoded password.
     * @param rawPassword the raw password.
     * @param encodedPassword the encoded password.
     * @return true if matches.
     */
    public Boolean matches(String rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}