package com.example.password;

/**
 * Interface for password hashing serv.
 */
public interface IPasswordHasher {
    /**
     * Hashes the given plain-text password.
     * @param password The plain-text password to hash.
     * @return The hashed password string.
     */
    String hashPassword(String password);
}