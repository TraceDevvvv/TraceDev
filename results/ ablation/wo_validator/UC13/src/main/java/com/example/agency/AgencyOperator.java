package com.example.agency;

import java.util.List;

/**
 * Represents an agency operator who can manage tourist accounts.
 * Uses the ManageTouristAccountController to perform operations.
 */
public class AgencyOperator {
    private String id;
    private String username;
    private List<String> permissions;

    public AgencyOperator(String id, String username, List<String> permissions) {
        this.id = id;
        this.username = username;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * Enables a tourist account.
     * @param touristId the ID of the tourist to enable
     */
    public void enableTouristAccount(String touristId) {
        // In a real scenario, this would call the controller.
        // For simplicity, we assume the controller is injected or obtained elsewhere.
        System.out.println("Operator " + username + " enabling tourist account: " + touristId);
    }

    /**
     * Disables a tourist account.
     * @param touristId the ID of the tourist to disable
     */
    public void disableTouristAccount(String touristId) {
        System.out.println("Operator " + username + " disabling tourist account: " + touristId);
    }
}