package com.example.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Implementation of password service for hashing passwords
 */
public class PasswordService implements IPasswordService {
    
    public PasswordService() {
        // Constructor
    }

    @Override
    public String hashPassword(String plainText) {
        // Using SHA-256 for password hashing
        // In production, consider using bcrypt or Argon2 with salt
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(plainText.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}