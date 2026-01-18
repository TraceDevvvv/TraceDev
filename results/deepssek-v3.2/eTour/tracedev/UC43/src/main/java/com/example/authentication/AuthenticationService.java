package com.example.authentication;

/**
 * Service responsible for authentication and session management.
 */
public class AuthenticationService {
    
    /**
     * Authenticates the user based on provided credentials.
     * @param credentials the user credentials
     * @return a Session object if authentication succeeds
     */
    public Session authenticate(Credentials credentials) {
        // Simplified authentication: always returns a valid session for demo.
        // In a real system, this would validate against a user database.
        return new Session("session-" + System.currentTimeMillis(), 1L, 
                           java.time.LocalDateTime.now().plusHours(1));
    }

    /**
     * Checks if the given session is still valid.
     * @param session the session to validate
     * @return true if the session is valid
     */
    public boolean isValidSession(Session session) {
        return session != null && session.isValid();
    }

    /**
     * Logs out the given session.
     * @param session the session to invalidate
     */
    public void logout(Session session) {
        // In a real implementation, this would remove the session from the active sessions store.
        System.out.println("Logging out session: " + session.getId());
    }
}