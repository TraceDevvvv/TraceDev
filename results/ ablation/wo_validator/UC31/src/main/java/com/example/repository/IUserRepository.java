package com.example.repository;

import com.example.dto.UserDto;

/**
 * Interface for user repository operations.
 */
public interface IUserRepository {
    /**
     * Finds a user by username.
     * @param username the username
     * @return UserDto containing user data, or null if not found
     */
    UserDto findByUsername(String username);

    /**
     * Updates the password for a user.
     * @param username the username
     * @param newPasswordHash the new hashed password
     * @return true if update successful, false otherwise
     * @throws DatabaseException if a database error occurs
     */
    boolean updatePassword(String username, String newPasswordHash) throws DatabaseException;
}