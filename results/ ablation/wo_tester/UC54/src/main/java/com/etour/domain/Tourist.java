package com.etour.domain;

/**
 * Domain entity representing a tourist.
 */
public class Tourist {
    private String id;
    private String name;

    public Tourist(String id, String name) {
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
     * Authenticates the tourist (stub implementation).
     */
    public void authenticate() {
        // In a real system, this would involve credential validation.
        System.out.println("Tourist " + id + " authenticated.");
    }
}