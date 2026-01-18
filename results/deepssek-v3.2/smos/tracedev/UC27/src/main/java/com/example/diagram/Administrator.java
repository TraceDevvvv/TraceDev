package com.example.diagram;

/**
 * Represents an administrator user.
 * Role validation is handled by AuthorizationService.
 */
public class Administrator {
    private String userId;

    public Administrator(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Checks if the administrator is logged in.
     * @return true if logged in, false otherwise.
     * Assumption: Simplified implementation returning true for demo.
     */
    public boolean isLoggedIn() {
        // In a real scenario, this would check session or authentication status.
        return true;
    }

    /**
     * Checks if the administrator has a specific role.
     * @param roleName the role to check.
     * @return true if the administrator has the role, false otherwise.
     * Assumption: Role validation is delegated to AuthorizationService, so this method is kept for compatibility but may be deprecated.
     */
    public boolean hasRole(String roleName) {
        // Role validation is handled by AuthorizationService.
        // This method might be kept for backward compatibility.
        // For demo, returning true for "Administrator" role.
        return "Administrator".equals(roleName);
    }
}