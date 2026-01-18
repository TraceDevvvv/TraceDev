package com.example.auth;

import com.example.agency.AgencyOperator;
import com.example.auth.Session;

/**
 * Validates user sessions and provides authentication context for all operations.
 * Implements login/logout functionality.
 */
public interface AuthenticationService {
    /**
     * Validates a session token.
     * @param token the session token
     * @return true if valid, false otherwise
     */
    boolean validateSession(String token);

    /**
     * Retrieves the currently authenticated user.
     * @return the current AgencyOperator
     */
    AgencyOperator getCurrentUser();

    /**
     * Login with username and password.
     * @param username the username
     * @param password the password
     * @return a new Session object
     */
    Session login(String username, String password);

    /**
     * Logout by invalidating the session token.
     * @param token the session token to invalidate
     */
    void logout(String token);
}