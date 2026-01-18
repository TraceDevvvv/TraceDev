package com.example.monitoring.application;

/**
 * Application Layer: Handles user authentication and authorization checks.
 * This is a placeholder service to simulate authorization as per the sequence diagram.
 */
public class AuthService {
    // Assumption: For demonstration, a simple flag controls authorization.
    // In a real system, this would involve checking user credentials, roles, etc.
    private boolean isAuthorized = true;

    /**
     * Checks if the current user is authenticated and possesses the required role.
     *
     * @param role The role to check against (e.g., "Administrator").
     * @return true if the user is authenticated and has the role, false otherwise.
     */
    public boolean checkAuthenticationAndRole(String role) {
        System.out.println("AuthService: Checking authentication and role for: " + role);
        // Simulate checking if user is logged in and has the specified role.
        // For this example, we simply check the 'isAuthorized' flag.
        return isAuthorized;
    }

    /**
     * Setter to control the authorization status for testing purposes.
     * @param authorized true to grant authorization, false to deny.
     */
    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}