package com.system.domain;

/**
 * Represents a cultural heritage agency.
 */
public class Agency {
    private String id;
    private String name;
    private boolean isActive;

    public Agency(String id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}