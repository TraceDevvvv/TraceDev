package com.example.password;

/**
 * Concrete implementation of IPasswordHasher using a simplified hashing mechanism
 * (for demonstration purposes, not real BCrypt).
 */
public class BCryptPasswordHasher implements IPasswordHasher {
    /**
     * Hashes the given plain-text password.
     * In a real application, this would use a robust hashing algorithm like BCrypt.
     * For this simulation, it simply prefixes the password with "hashed_".
     *
     * @param password The plain-text password to hash.
     * @return A simulated hashed password.
     */
    @Override
    public String hashPassword(String password) {
        // In a real application, you would use a library like jBCrypt:
        // return BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("DEBUG: Hashing password: " + password);
        return "hashed_" + password; // Simplified for demonstration
    }
}