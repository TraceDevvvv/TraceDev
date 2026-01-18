package com.example;

/**
 * Data Transfer Object for Location.
 * Used to transfer location data between layers.
 */
public class LocationDTO {
    public String id;
    public String name;

    public LocationDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}