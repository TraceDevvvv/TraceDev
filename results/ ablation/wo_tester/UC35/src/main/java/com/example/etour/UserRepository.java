package com.example.etour;

/**
 * Repository interface for user persistence.
 */
public interface UserRepository {
    UserEntity findByUsername(String username);
    UserEntity save(UserEntity user);
}