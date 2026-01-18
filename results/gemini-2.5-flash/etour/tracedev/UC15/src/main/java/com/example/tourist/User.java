package com.example.tourist;

import java.util.Collections;
import java.util.List;

/**
 * Represents a User (Domain Entity), specifically for authentication context.
 */
public class User {
    private String username;
    private List<String> roles; // e.g., "AGENCY_OPERATOR", "ADMIN"

    public User(String username, List<String> roles) {
        this.username = username;
        this.roles = Collections.unmodifiableList(roles); // Make roles immutable
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", roles=" + roles +
               '}';
    }
}