package com.example.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple User model for authentication and authorization.
 */
public class User {
    private String username;
    private Set<String> roles; // Roles assigned to the user

    public User(String username, Set<String> roles) {
        this.username = username;
        this.roles = new HashSet<>(roles); // Create a copy to prevent external modification
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return Collections.unmodifiableSet(roles); // Return unmodifiable set
    }
}