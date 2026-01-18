package com.example.model;

/**
 * Represents a professor user.
 * Authentication is handled by AuthenticationService (REQ-001).
 */
public class Professor {
    private String id;
    private String name;

    public Professor() {
    }

    public Professor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Professor login method.
     * According to class diagram, returns boolean.
     * Implementation may delegate to AuthenticationService.
     */
    public boolean login() {
        // For now, assume login is successful if id and name are set.
        return this.id != null && this.name != null;
    }
}