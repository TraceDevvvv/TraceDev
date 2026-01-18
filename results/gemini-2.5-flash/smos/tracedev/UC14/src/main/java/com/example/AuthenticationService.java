package com.example;

/**
 * Service for user authentication.
 * Added to satisfy requirement R3 (entry condition).
 */
public class AuthenticationService {

    /**
     * Authenticates a user with the given username and password.
     * This is a placeholder implementation.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        System.out.println("AuthenticationService: Attempting to authenticate user: " + username);
        // Simulate authentication logic
        return "admin".equals(username) && "password".equals(password);
    }
}