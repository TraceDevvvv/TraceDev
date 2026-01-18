package com.example.domain;

import java.util.List;
import java.util.Collections;

/**
 * Represents an authenticated user in the system.
 * (Added to satisfy REQ-001)
 */
public class User {
    private String id;
    private List<String> roles;

    /**
     * Constructs a User object.
     *
     * @param id The unique ID of the user.
     * @param roles A list of roles assigned to the user.
     */
    public User(String id, List<String> roles) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        this.id = id;
        this.roles = roles != null ? Collections.unmodifiableList(roles) : Collections.emptyList();
    }

    public String getId() {
        return id;
    }

    public List<String> getRoles() {
        return roles;
    }
}