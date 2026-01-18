package com.example.infrastructure;

import com.example.domain.User;

/**
 * Interface for data access operations related to Users.
 * Defines the contract for saving and finding User entities.
 */
public interface IUserRepository {
    /**
     * Saves a User entity to the repository.
     * @param user The User object to save.
     */
    void save(User user);

    /**
     * Finds a User entity by its unique user ID.
     * @param userId The ID of the user to find.
     * @return The User object if found, null otherwise.
     */
    User findById(String userId);

    /**
     * Finds a User entity by its username.
     * @param username The username to find.
     * @return The User object if found, null otherwise.
     */
    User findByUsername(String username);
}