package com.example.domain;

/**
 * Domain entity representing a point of rest.
 */
public class PointOfRest {

    private final String id;
    private final String name;
    private final String type;

    public PointOfRest(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}