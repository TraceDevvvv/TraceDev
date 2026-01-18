package com.agency.domain;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Concrete password strategy using BCrypt hashing.
 * Provides secure password processing as per quality requirements.
 */
public class BcryptPasswordStrategy implements IPasswordStrategy {
    /**
     * Hashes a password using BCrypt.
     */
    @Override
    public String hashPassword(String password) {
        // Using a work factor of 12 for reasonable security vs performance
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    /**
     * Verifies a plain password against a BCrypt hash.
     */
    @Override
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}