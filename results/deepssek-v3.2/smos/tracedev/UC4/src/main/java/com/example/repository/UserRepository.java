package com.example.repository;

import com.example.model.User;

/**
 * Interface for user data access operations.
 */
public interface UserRepository {
    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the User object, or null if not found
     */
    User findByUsername(String username);

    /**
     * Updates an existing user in the data store.
     *
     * @param user the user to update
     */
    void updateUser(User user);
}