package com.agency.infrastructure;

/**
 * Validates password strength, confirmation, and history.
 * Supports secure password processing as per quality requirements.
 */
public class PasswordValidator {
    /**
     * Validates that the new password meets strength requirements.
     * For demonstration, requires at least 8 characters, one digit, one uppercase, one lowercase.
     * @param password The new password.
     * @return true if password is strong enough, false otherwise.
     */
    public boolean validateStrength(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
        }
        return hasDigit && hasUpper && hasLower;
    }

    /**
     * Validates that the new password is not the same as the old one (simple history check).
     * In a real system, this might check against a history of recent hashes.
     * @param oldHash The old hashed password.
     * @param newHash The new hashed password.
     * @return true if the new password is different from the old one, false if they are the same.
     */
    public boolean validateHistory(String oldHash, String newHash) {
        // For demonstration, we simply disallow reusing the exact same password.
        // In reality, you would compare against a list of recent hashes.
        return !oldHash.equals(newHash);
    }

    /**
     * Validates that the new password and confirmation password match.
     * @param password The new password.
     * @param confirmation The confirmation password.
     * @return true if they match, false otherwise.
     */
    public boolean validateConfirmation(String password, String confirmation) {
        return password != null && password.equals(confirmation);
    }
}