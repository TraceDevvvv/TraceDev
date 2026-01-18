package com.etour.agency;

import com.etour.security.Permission;
import java.util.List;

/**
 * Represents an agency operator who can perform operations.
 * Implements authentication and authorization checks.
 */
public class AgencyOperator {
    private String userId;
    private String name;

    public AgencyOperator(String userId, String name) {
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
     * Simulates authentication check.
     * In a real system, this would verify credentials.
     */
    public boolean isAuthenticated() {
        // For simplicity, assume authenticated if userId is not null/empty
        return userId != null && !userId.trim().isEmpty();
    }

    /**
     * Checks if the operator has the given permission.
     */
    public boolean isAuthorized(Permission permission) {
        // In a real system, this would check against a role/permission store.
        // For this example, assume the operator has MANAGE_TAGS permission.
        return Permission.MANAGE_TAGS.equals(permission);
    }

    /**
     * Receives a notification message.
     * This satisfies the requirement for exit conditions (success/error notification).
     */
    public void receiveNotification(String message) {
        System.out.println("Notification to " + name + ": " + message);
    }
}