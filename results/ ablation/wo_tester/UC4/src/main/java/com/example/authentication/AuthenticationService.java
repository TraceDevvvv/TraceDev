package com.example.authentication;

/**
 * Interface for user authentication serv.
 * Entry Condition: User IS authenticated.
 */
public interface AuthenticationService {
    /**
     * Authenticates the current user.
     * @return true if the user is authenticated, false otherwise.
     */
    boolean authenticateUser();
}