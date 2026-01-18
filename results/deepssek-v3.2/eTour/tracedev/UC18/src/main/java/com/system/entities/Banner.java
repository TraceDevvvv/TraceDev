package com.system.entities;

/**
 * Represents a banner with an id, name, and associated convention.
 */
public class Banner {
    private String id;
    private String name;
    private String conventionId;

    public Banner(String id, String name, String conventionId) {
        this.id = id;
        this.name = name;
        this.conventionId = conventionId;
    }

    public String getId() {
        return id;
    }

    public String getConventionId() {
        return conventionId;
    }

    public String getName() {
        return name;
    }
}