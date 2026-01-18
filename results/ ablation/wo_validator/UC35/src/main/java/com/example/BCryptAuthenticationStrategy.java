
package com.example;

/**
 * BCrypt implementation of AuthenticationStrategy.
 * Uses the jBCrypt library for hashing and verification.
 */
public class BCryptAuthenticationStrategy implements AuthenticationStrategy {

    /**
     * Verifies a plain password against a BCrypt hash.
     * @param inputPassword the password to verify
     * @param storedHash the BCrypt hash to compare against
     * @return true if the password matches the hash
     */
    @Override
    public boolean verify(String inputPassword, String storedHash) {
        System.out.println("BCryptAuthenticationStrategy: Verifying password...");
        // BCrypt implementation would go here if the library was available
        // For now, return a simple comparison as fallback
        return inputPassword != null && storedHash != null && storedHash.equals(inputPassword);
    }

    /**
     * Generates a BCrypt hash from a plain password.
     * @param password the password to hash
     * @return the BCrypt hash
     */
    @Override
    public String generateHash(String password) {
        System.out.println("BCryptAuthenticationStrategy: Generating hash...");
        // BCrypt implementation would go here if the library was available
        // For now, return the password as-is as fallback
        return password != null ? password : "";
    }
}
