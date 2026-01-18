package com.example.repository;

import com.example.model.User;

/**
 * Interface for the User Repository.
 * Defines the contract for data access operations related to User entities.
 * Added to satisfy authentication and authorization requirements.
 */
public interface IUserRepository {

    /**
     * Finds a User by their unique identifier.
     * @param id The unique identifier of the user.
     * @return The User object if found, otherwise null.
     */
    User findById(String id);
}