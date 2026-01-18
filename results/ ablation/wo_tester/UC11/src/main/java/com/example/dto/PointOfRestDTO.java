package com.example.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for PointOfRest.
 * Used to transfer data between layers, particularly from UI to Controller.
 */
public class PointOfRestDTO implements Serializable {
    private String id;
    private String name;
    private String location;

    public PointOfRestDTO(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}