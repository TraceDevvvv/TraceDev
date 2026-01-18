package com.example;

/**
 * Concrete implementation of ISecurityService.
 * This class provides dummy security checks for demonstration purposes.
 * In a real application, this would interact with an authentication/authorization system.
 */
public class AuthService implements ISecurityService {

    // Internal state to simulate authentication for testing specific scenarios.
    private boolean authenticated = true;
    private String userRole = "ADMIN"; // Default role for testing

    /**
     * Sets the authentication status for testing purposes.
     * @param authenticated True to simulate an authenticated user, false otherwise.
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        System.out.println("AuthService: Authentication status set to " + authenticated);
    }

    /**
     * Sets the user role for testing purposes.
     * @param userRole The role to assign to the simulated user.
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
        System.out.println("AuthService: User role set to " + userRole);
    }

    /**
     * Checks if the current user is authenticated.
     * @return true if authenticated, false otherwise.
     */
    @Override
    public boolean isAuthenticated() {
        System.out.println("AuthService: Checking authentication status. Is authenticated: " + authenticated);
        return authenticated;
    }

    /**
     * Checks if the current user has a specific role.
     * @param role The role to check against.
     * @return true if the user has the specified role (or if current userRole is null/empty for no role), false otherwise.
     */
    @Override
    public boolean hasRole(String role) {
        boolean hasSpecificRole = authenticated && (this.userRole != null && this.userRole.equalsIgnoreCase(role));
        System.out.println("AuthService: Checking role '" + role + "'. Has role: " + hasSpecificRole);
        return hasSpecificRole;
    }
}