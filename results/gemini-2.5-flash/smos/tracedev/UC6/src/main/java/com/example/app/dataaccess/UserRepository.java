package com.example.app.dataaccess;

import com.example.app.domain.User;

import java.util.List;

/**
 * Repository interface for User entities.
 * Defines the contract for data access operations related to Users.
 */
public interface UserRepository {
    /**
     * Saves a User entity. If the user already exists (e.g., by ID or login), it might update it.
     * For this example, we assume it's for new users and saves them.
     *
     * @param user The User entity to save.
     */
    void save(User user);

    /**
     * Finds a User by their login identifier.
     *
     * @param login The login string of the user to find.
     * @return The User object if found, null otherwise.
     */
    User findByLogin(String login);

    // Additional methods could be added, e.g., findAll(), delete(), findById(), etc.
    List<User> findAll();
}