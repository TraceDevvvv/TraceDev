package com.example.app.repository;

import com.example.app.model.User;

/**
 * Interface for the User Repository.
 * This defines the contract for data persistence operations related to User entities.
 * This corresponds to the 'UserRepository' interface in the UML Class Diagram.
 */
public interface UserRepository {

    /**
     * Finds a User by their unique identifier.
     * @param id The ID of the user to find.
     * @return The User object if found, null otherwise.
     */
    User findById(String id);

    /**
     * Saves a User entity. This can be for creating a new user or updating an existing one.
     * @param user The User entity to save.
     * @return The saved User entity, possibly with generated IDs or updated fields.
     */
    User save(User user);
}