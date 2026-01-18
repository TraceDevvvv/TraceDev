package com.example.domain;

/**
 * Domain entity representing a place.
 */
public class Place {
    private String id;
    private String name;

    public Place(String id, String name) {
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