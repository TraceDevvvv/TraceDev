package com.example.login;

/**
 * Infrastructure/Data Access Layer (Repository Pattern): Interface for user data access.
 */
public interface UserRepository {
    /**
     * Finds a user by their username.
     * // Modified to satisfy requirement R13, R14
     * @param username The username to search for.
     * @return The User object if found, otherwise null.
     * @throws ConnectionException If there's an issue connecting to the data source.
     */
    User findByUsername(String username) throws ConnectionException;

    /**
     * Finds a user by their username and hashed password.
     * // Modified to satisfy requirement R13, R14
     * @param username The username to search for.
     * @param passwordHash The hashed password to match.
     * @return The User object if found and credentials match, otherwise null.
     * @throws ConnectionException If there's an issue connecting to the data source.
     */
    User findByUsernameAndPasswordHash(String username, String passwordHash) throws ConnectionException;
}