package com.example.repository;

/**
 * Interface for token blacklist management.
 */
public interface TokenRepository {
    /**
     * Adds a token to the blacklist.
     * @param token The token to blacklist.
     */
    void addToBlacklist(String token);

    /**
     * Checks if a token is blacklisted.
     * @param token The token to check.
     * @return true if blacklisted, false otherwise.
     */
    boolean isBlacklisted(String token);
}