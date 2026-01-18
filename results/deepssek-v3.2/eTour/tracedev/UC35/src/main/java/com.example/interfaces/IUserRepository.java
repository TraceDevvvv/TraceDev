package com.example.interfaces;

import com.example.domain.User;
import com.example.domain.LoginAttempt;
import java.util.Optional;

/**
 * Repository interface for user-related data access.
 * Follows the Repository pattern.
 */
public interface IUserRepository {
    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Saves a login attempt to the persistence layer.
     *
     * @param attempt the login attempt to save
     */
    void save(LoginAttempt attempt);
}