package com.example.model;

/**
 * Represents an Administrator actor in the system.
 * Contains user identification, role, and authentication methods.
 */
public class Administrator {
    private String userId;
    private String role;

    public Administrator(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Checks if the administrator is currently logged in.
     * This is a simplified simulation; in a real system, this would check session or token.
     */
    public boolean isLoggedIn() {
        // Assumption: For simulation, we consider the administrator always logged in.
        return true;
    }

    /**
     * Checks if the administrator has a specific role.
     * @param roleName The role to check.
     * @return true if the administrator's role matches the given role name.
     */
    public boolean hasRole(String roleName) {
        return role != null && role.equals(roleName);
    }

    /**
     * Validates if the administrator has the admin role.
     * Added to satisfy entry condition: User HAS administrator role.
     * @return true if the role is "Administrator".
     */
    public boolean validateAdminRole() {
        return hasRole("Administrator");
    }
}