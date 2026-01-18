package com.example.app.repository;

import com.example.app.model.User;
import com.example.app.exception.ConnectionError;

/**
 * Interface for the User Repository, defining data access operations for User entities.
 * Specifies the contract for retrieving user data, ensuring accurate and up-to-date data retrieval (R8).
 */
public interface IUserRepository {

    /**
     * Finds a User by their unique ID.
     * @param userId The ID of the user to find.
     * @return The User object if found.
     * @throws ConnectionError If there is an issue connecting to the data source.
     */
    User findById(String userId) throws ConnectionError;
}