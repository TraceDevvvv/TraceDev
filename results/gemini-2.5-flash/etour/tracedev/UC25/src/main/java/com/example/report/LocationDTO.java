package com.example.report;

/**
 * LocationDTO (Data Transfer Object)
 * Used to transfer location data to the UI or other layers, often containing a subset of information.
 */
public class LocationDTO {
    public String id;
    public String name;

    /**
     * Constructor for LocationDTO.
     * @param id Unique identifier of the location.
     * @param name Name of the location.
     */
    public LocationDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "LocationDTO{id='" + id + "', name='" + name + "'}";
    }
}