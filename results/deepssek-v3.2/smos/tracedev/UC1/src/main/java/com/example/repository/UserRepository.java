package com.example.repository;

import com.example.entity.User;

/**
 * Output Port / Repository Interface for user data access.
 */
public interface UserRepository {
    User findByUsername(String username);
    User searchInArchive(String username);
}