package com.example.login;

/**
 * Service class responsible for authenticating user credentials.
 */
public class AuthenticationService {

    /**
     * Attempts to authenticate a user with the given username and password.
     * This is a simulated authentication.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        // --- Implementation based on Class Diagram ---
        // Simulate a successful authentication for specific credentials
        // and failure for others.
        if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("AuthenticationService: User '" + username + "' authenticated successfully.");
            return true;
        } else {
            System.out.println("AuthenticationService: Authentication failed for user '" + username + "'.");
            return false;
        }
    }
}