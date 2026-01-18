package com.example.repository;

import com.example.domain.User;

/**
 * Interface for user repository operations
 */
public interface IUserRepository {
    /**
     * Saves a user to the repository
     * @param user the user to save
     * @return true if save successful, false otherwise
     */
    boolean save(User user);
    
    /**
     * Finds a user by username
     * @param username the username to search for
     * @return the User object or null if not found
     */
    User findByUsername(String username);
    
    /**
     * Finds a user by email
     * @param email the email to search for
     * @return the User object or null if not found
     */
    User findByEmail(String email);
    
    /**
     * Generates a new user ID
     * Added to satisfy requirement: User ID generation
     * @return a new unique user ID
     */
    int generateUserId();
}