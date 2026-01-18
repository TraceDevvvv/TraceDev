package com.example;

/**
 * Repository interface for User entities.
 */
public interface UserRepository {
    User findById(String id);
    void save(User user);
}