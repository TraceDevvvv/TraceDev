package com.example.app.application;

/**
 * Service responsible for user authentication related tasks.
 * Added to satisfy R3.
 */
public class AuthenticationService {

    /**
     * Checks if a given admin login is authenticated.
     * For this example, we assume "admin" is always authenticated.
     * Added to satisfy R3.
     *
     * @param adminLogin The login of the administrator.
     * @return true if the admin is authenticated, false otherwise.
     */
    public boolean isAuthenticated(String adminLogin) {
        System.out.println("[AuthenticationService] Checking if admin '" + adminLogin + "' is authenticated...");
        // In a real system, this would involve checking session, tokens, etc.
        // For demonstration, let's assume 'admin' is always authenticated.
        return "admin".equals(adminLogin);
    }

    /**
     * Retrieves the login of the current authenticated administrator.
     * Added to satisfy R3.
     *
     * @return The login string of the current administrator.
     */
    public String getCurrentAdminLogin() {
        System.out.println("[AuthenticationService] Getting current admin login...");
        // In a real system, this would retrieve from a security context or session.
        return "admin"; // Hardcoded for demonstration.
    }
}