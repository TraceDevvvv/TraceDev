package com.example;

/**
 * Service for handling user authentication and session validation.
 */
public class AuthenticationService {
    
    /**
     * Constructor for AuthenticationService.
     */
    public AuthenticationService() {
        // Initialization if needed
    }

    /**
     * Authenticates a user with username and password.
     * Assumption: Simplified authentication - always returns a session with a user.
     * @param username the username
     * @param password the password
     * @return a Session object with the authenticated user
     */
    public Session authenticate(String username, String password) {
        Session session = new Session();
        Administrator admin = new Administrator(username);
        session.setCurrentUser(admin);
        return session;
    }

    /**
     * Validates if a session is still active.
     * @param session the session to validate
     * @return true if the session is authenticated
     */
    public boolean validateSession(Session session) {
        return session != null && session.isAuthenticated();
    }
}