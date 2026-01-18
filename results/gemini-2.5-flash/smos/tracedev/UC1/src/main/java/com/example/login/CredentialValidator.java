package com.example.login;

/**
 * Domain Serv / Utilities: Validates user credentials based on predefined rules.
 */
public class CredentialValidator {

    /**
     * Checks if the given username meets the validation criteria.
     * For this example, a username must have a length of at least 5 characters.
     * @param username The username to validate.
     * @return true if the username is valid, false otherwise.
     */
    public boolean isValidUsername(String username) {
        // Requirement from Sequence Diagram: username length >= 5
        boolean isValid = username != null && username.length() >= 5;
        System.out.println("CredentialValidator: Username '" + username + "' is valid: " + isValid + " (length >= 5)");
        return isValid;
    }

    /**
     * Checks if the given password meets the validation criteria.
     * For this example, a password must have a length of at least 5 characters.
     * @param password The password to validate.
     * @return true if the password is valid, false otherwise.
     */
    public boolean isValidPassword(String password) {
        // Requirement from Sequence Diagram: password length >= 5
        boolean isValid = password != null && password.length() >= 5;
        System.out.println("CredentialValidator: Password length is valid: " + isValid + " (length >= 5)");
        return isValid;
    }
}