package com.example.password;

/**
 * Represents the rules and requirements for a valid password.
 */
public class PasswordPolicy {
    /**
     * Validates a given password against the defined policy.
     * For this simulation, the policy requires the password to be at least 8 characters long
     * and contain at least one digit and one uppercase letter.
     *
     * @param password The password string to validate.
     * @return true if the password meets the policy, false otherwise.
     */
    public boolean validate(String password) {
        System.out.println("DEBUG: Validating password against policy: " + password);
        if (password == null || password.length() < 8) {
            System.out.println("DEBUG: Policy failed: password too short or null.");
            return false; // Minimum length requirement
        }
        boolean hasDigit = false;
        boolean hasUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
        }
        if (!hasDigit || !hasUppercase) {
            System.out.println("DEBUG: Policy failed: missing digit or uppercase letter.");
            return false; // Must contain at least one digit and one uppercase letter
        }
        System.out.println("DEBUG: Policy passed.");
        return true;
    }
}