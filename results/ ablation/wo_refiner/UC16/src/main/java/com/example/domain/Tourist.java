package com.example.domain;

/**
 * Domain Layer - Core business entity.
 */
public class Tourist {
    private int id;
    private String name;
    private String email;
    private boolean isActive;

    public Tourist(int id, String name, String email, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Marks the tourist as deleted.
     * This method represents the domain delete operation.
     */
    public void delete() {
        this.isActive = false;
        // Additional domain logic could be added here, e.g., validation, domain events
    }
}