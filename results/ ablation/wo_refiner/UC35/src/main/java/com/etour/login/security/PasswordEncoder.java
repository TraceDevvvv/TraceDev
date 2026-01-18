package com.etour.login.security;

/**
 * Security interface for password encoding and matching.
 * Tagged with <<security>> for secure password hashing.
 */
public interface PasswordEncoder {
    /**
     * Checks if a raw password matches an encoded password.
     * Security requirement: implements secure password hashing.
     *
     * @param rawPassword the raw password to check
     * @param encodedPassword the encoded password to compare against
     * @return true if they match, false otherwise
     */
    boolean matches(String rawPassword, String encodedPassword);
}