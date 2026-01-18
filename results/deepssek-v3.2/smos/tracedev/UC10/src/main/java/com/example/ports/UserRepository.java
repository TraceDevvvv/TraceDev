package com.example.ports;

import com.example.domain.User;

/**
 * Port for user repository operations.
 */
public interface UserRepository {
    User findById(String id);
    void save(User user);
}