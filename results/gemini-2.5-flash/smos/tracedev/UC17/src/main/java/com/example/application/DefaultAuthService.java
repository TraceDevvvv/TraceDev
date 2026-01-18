package com.example.application;

/**
 * Default implementation of the IAuthService.
 * Provides basic, simulated authorization logic.
 */
public class DefaultAuthService implements IAuthService {

    public DefaultAuthService() {
        System.out.println("[DefaultAuthService] Initialized.");
    }

    /**
     * Checks if a user is an administrator.
     * For demonstration purposes, "adminUser" is considered an administrator.
     *
     * @param userId The ID of the user to check.
     * @return true if the user ID is "adminUser", false otherwise.
     */
    @Override
    public boolean isAdmin(String userId) {
        System.out.println("[DefaultAuthService] Checking if user '" + userId + "' is admin.");
        // Simple mock: assume "adminUser" is an admin
        boolean isAdmin = "adminUser".equalsIgnoreCase(userId);
        System.out.println("[DefaultAuthService] User '" + userId + "' is admin: " + isAdmin);
        return isAdmin;
    }
}