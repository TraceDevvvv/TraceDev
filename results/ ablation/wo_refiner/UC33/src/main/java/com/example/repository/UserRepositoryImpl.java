package com.example.repository;

import com.example.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of UserRepository.
 * Stereotypes applied: Secure, Transactional.
 */
public class UserRepositoryImpl implements UserRepository {
    
    // Simple in-memory storage for example purposes
    private Map<String, User> usersByEmail = new HashMap<>();
    private Map<String, User> usersById = new HashMap<>();
    
    @Override
    public User save(User user) {
        // In a real implementation, this would connect to a database
        usersByEmail.put(user.getEmail(), user);
        usersById.put(user.getUserId(), user);
        return user;
    }
    
    @Override
    public User findByEmail(String email) {
        return usersByEmail.get(email);
    }
    
    /**
     * Finds a user by ID (additional helper method).
     * @param userId the user ID
     * @return the user or null if not found
     */
    public User findById(String userId) {
        return usersById.get(userId);
    }
    
    /**
     * Clears all users (for testing purposes).
     */
    public void clear() {
        usersByEmail.clear();
        usersById.clear();
    }
}