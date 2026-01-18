package com.example;

/**
 * Repository interface for User entities.
 */
public interface UserRepository {
    /**
     * Finds a User by its ID.
     * @param userId the user ID
     * @return the User, or null if not found
     */
    User findById(String userId);

    /**
     * Saves a User.
     * @param user the User to save
     */
    void save(User user);
}