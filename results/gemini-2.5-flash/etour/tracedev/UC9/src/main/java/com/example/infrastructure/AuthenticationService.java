package com.example.infrastructure;

/**
 * Service for user authentication.
 * Added to satisfy requirement R4 for entry condition check.
 */
public class AuthenticationService {

    /**
     * Checks if a given user session is authenticated.
     *
     * @param userSession The session to check.
     * @return true if the session is authenticated, false otherwise.
     */
    public boolean isAuthenticated(Session userSession) {
        // Simulate authentication logic. For this example, any valid session is authenticated.
        if (userSession != null && userSession.isValid()) {
            System.out.println("DEBUG: User session " + userSession.userId + " is authenticated.");
            return true;
        }
        System.out.println("DEBUG: User session is NOT authenticated.");
        return false;
    }
}