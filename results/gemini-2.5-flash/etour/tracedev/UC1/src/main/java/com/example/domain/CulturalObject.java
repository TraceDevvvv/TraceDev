package com.example.domain;

/**
 * Represents a cultural object in the system.
 * This class corresponds to the 'CulturalObject' entity in the Class Diagram.
 */
public class CulturalObject {
    private String id;
    private String name;

    /**
     * Constructs a new CulturalObject.
     * @param id The unique identifier of the cultural object.
     * @param name The name of the cultural object.
     */
    public CulturalObject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the cultural object.
     * @return The ID of the cultural object.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the cultural object.
     * @return The name of the cultural object.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CulturalObject{id='" + id + "', name='" + name + "'}";
    }
}