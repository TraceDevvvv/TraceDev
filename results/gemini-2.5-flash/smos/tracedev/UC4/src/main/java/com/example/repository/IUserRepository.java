package com.example.repository;

import com.example.model.User;
import com.example.exception.ConnectionLostException;

/**
 * Interface for user data repository operations.
 * Defines methods for finding and updating user information.
 */
public interface IUserRepository {

    /**
     * Finds a user by their unique ID.
     * @param userId The ID of the user to find.
     * @return The User object if found, null otherwise.
     * @throws ConnectionLostException if the database connection is lost during the operation.
     */
    User findById(String userId) throws ConnectionLostException;

    /**
     * Updates an existing user's information in the repository.
     * @param user The User object with updated information.
     * @return true if the update was successful, false otherwise.
     * @throws ConnectionLostException if the database connection is lost during the operation.
     */
    boolean update(User user) throws ConnectionLostException;
}