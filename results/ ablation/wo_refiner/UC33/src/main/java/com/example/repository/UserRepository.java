package com.example.repository;

import com.example.model.User;

/**
 * Repository interface for user data operations.
 */
public interface UserRepository {
    
    /**
     * Saves a user.
     * @param user the user to save
     * @return the saved user
     */
    User save(User user);
    
    /**
     * Finds a user by email.
     * @param email the email to search for
     * @return the user or null if not found
     */
    User findByEmail(String email);
}