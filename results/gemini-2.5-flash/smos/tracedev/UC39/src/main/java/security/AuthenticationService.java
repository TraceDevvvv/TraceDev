package security;

import domain.Administrator;

/**
 * Provides authentication serv for the system (REQ-003).
 */
public class AuthenticationService {

    /**
     * Checks if a user is currently authenticated.
     * @return true if a user is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        System.out.println("[AuthenticationService] Checking if user is authenticated...");
        // Dummy implementation: always returns true for demonstration
        return true;
    }

    /**
     * Retrieves the currently logged-in administrator.
     * @return An Administrator object representing the logged-in user, or null if not authenticated.
     */
    public Administrator getLoggedInUser() {
        System.out.println("[AuthenticationService] Getting logged in user...");
        // Dummy implementation: returns a default administrator
        if (isAuthenticated()) {
            return new Administrator("admin001", "system_admin");
        }
        return null;
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        System.out.println("[AuthenticationService] User logged out.");
        // Dummy implementation: no actual session management
    }
}