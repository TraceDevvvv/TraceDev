package com.example.service;

/**
 * Interface for password hashing serv.
 * Defines methods for hashing a plain password and checking a plain password against a hashed one.
 */
public interface IPasswordHasher {

    /**
     * Hashes a plain-text password.
     * @param plainPassword The plain-text password to hash.
     * @return The securely hashed password string.
     */
    String hashPassword(String plainPassword);

    /**
     * Checks if a plain-text password matches a given hashed password.
     * @param plainPassword The plain-text password to check.
     * @param hashedPassword The hashed password to compare against.
     * @return true if the plain password matches the hashed password, false otherwise.
     */
    boolean checkPassword(String plainPassword, String hashedPassword);
}