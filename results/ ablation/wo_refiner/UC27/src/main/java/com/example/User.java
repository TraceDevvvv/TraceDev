package com.example;

import java.util.List;

/**
 * Actor class representing a User.
 * Note: Actors typically don't implement system logic (REQ-001 & REQ-006).
 */
public class User {
    private String username;
    private List<String> permissions;

    public User(String username, List<String> permissions) {
        this.username = username;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void searchSite(String searchCriteria) {
        // Implementation of searchSite method from class diagram
        // Actor typically delegates to UI/controller, but we implement as per diagram
        System.out.println("User searching with criteria: " + searchCriteria);
    }
}