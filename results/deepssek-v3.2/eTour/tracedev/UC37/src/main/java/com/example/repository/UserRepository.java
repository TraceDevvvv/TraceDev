package com.example.repository;

import com.example.model.RegisteredUser;

/**
 * Interface for user data access.
 */
public interface UserRepository {
    /**
     * Finds a user by ID.
     * @param userId The user ID.
     * @return The RegisteredUser object.
     */
    RegisteredUser findById(String userId);

    /**
     * Updates a user's data.
     * @param user The user object.
     * @return true if update succeeded, false otherwise.
     */
    boolean update(RegisteredUser user);
}