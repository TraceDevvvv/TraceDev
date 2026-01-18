package com.example.agencyapp.repository;

import com.example.agencyapp.entity.User;

/**
 * Repository interface for user data operations.
 */
public interface UserRepository {
    /**
     * Updates the user in the data store.
     *
     * @param user the user with updated information.
     * @return true if update succeeded, false otherwise.
     */
    boolean updateUser(User user);
}