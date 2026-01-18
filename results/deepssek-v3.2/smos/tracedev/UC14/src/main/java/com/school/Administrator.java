package com.school;

/**
 * Represents an Administrator actor in the system.
 * Requirement REQ-001 (Actor representation)
 */
public class Administrator {
    private String userId;
    private String name;

    public Administrator(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    /**
     * Checks if the administrator is logged in.
     * @return true if logged in (simplified implementation).
     */
    public boolean isLoggedIn() {
        // In a real system, this would check session/token.
        return true;
    }
}