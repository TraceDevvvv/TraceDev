package com.example.report;

/**
 * Location class
 * Represents a geographical location or a point of interest.
 */
public class Location {
    private String id;
    // CD: Location attribute name
    private String name;
    // CD: Location attribute description
    private String description;

    /**
     * Constructor for Location.
     * @param id Unique identifier for the location.
     * @param name Name of the location.
     * @param description Description of the location.
     */
    public Location(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Converts this Location object to a LocationDTO.
     * This is useful for transferring data to the presentation layer without exposing internal details.
     * @return A LocationDTO object.
     */
    // CD: Location method toDTO
    public LocationDTO toDTO() {
        return new LocationDTO(this.id, this.name);
    }
}