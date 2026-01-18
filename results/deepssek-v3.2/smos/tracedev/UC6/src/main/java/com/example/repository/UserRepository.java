package com.example.repository;

import com.example.entity.User;
import java.util.Optional;

/**
 * Repository interface for User entities.
 * Defines the contract for user persistence operations.
 */
public interface UserRepository {
    /**
     * Saves a user entity.
     *
     * @param user the user to save.
     * @return the saved user (with generated ID).
     */
    User save(User user);

    /**
     * Finds a user by login.
     *
     * @param login the login identifier.
     * @return an Optional containing the user if found.
     */
    Optional<User> findByLogin(String login);

    /**
     * Finds a user by email.
     *
     * @param email the email address.
     * @return an Optional containing the user if found.
     */
    Optional<User> findByEmail(String email);
}