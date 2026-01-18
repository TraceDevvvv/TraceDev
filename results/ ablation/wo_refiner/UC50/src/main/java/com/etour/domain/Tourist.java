package com.etour.domain;

/**
 * Tourist entity representing an authenticated tourist.
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

    public String getName() {
        return name;
    }
}