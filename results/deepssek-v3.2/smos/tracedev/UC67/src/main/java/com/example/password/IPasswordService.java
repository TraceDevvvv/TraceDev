package com.example.password;

/**
 * Interface for password serv
 */
public interface IPasswordService {
    /**
     * Hashes a plain text password
     * @param plainText the plain text password
     * @return the hashed password
     */
    String hashPassword(String plainText);
}