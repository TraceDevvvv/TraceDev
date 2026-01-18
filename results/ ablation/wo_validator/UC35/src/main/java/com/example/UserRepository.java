package com.example;

/**
 * Repository interface for fetching User entities by username.
 */
public interface UserRepository {
    /**
     * Finds a user by their username.
     * @param username the username to search for
     * @return the User object, or null if not found
     */
    User findByUsername(String username);
}