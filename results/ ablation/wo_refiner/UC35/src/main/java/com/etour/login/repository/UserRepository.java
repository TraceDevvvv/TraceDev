package com.etour.login.repository;

import com.etour.login.model.User;
import java.util.Optional;

/**
 * Repository interface for user data access.
 * Tagged with <<performance>> for optimized database queries.
 */
public interface UserRepository {
    /**
     * Finds a user by username.
     * Performance requirement: optimized database queries.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found
     */
    Optional<User> findByUsername(String username);
}