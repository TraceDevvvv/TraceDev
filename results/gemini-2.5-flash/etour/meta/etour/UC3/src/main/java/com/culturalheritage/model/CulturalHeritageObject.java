package com.culturalheritage.model;

/**
 * Represents a cultural heritage object with its attributes.
 * This class holds the data for a cultural object, including its ID, name, description, origin, and year.
 */
public class CulturalHeritageObject {
    private String id;
    private String name;
    private String description;
    private String origin;
    private int year;

    /**
     * Constructs a new CulturalHeritageObject.
     *
     * @param id The unique identifier of the cultural heritage object.
     * @param name The name of the cultural heritage object.
     * @param description A brief description of the cultural heritage object.
     * @param origin The origin (e.g., country, region) of the cultural heritage object.
     * @param year The year of creation or discovery of the cultural heritage object.
     */
    public CulturalHeritageObject(String id, String name, String description, String origin, int year) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.origin = origin;
        this.year = year;
    }

    /**
     * Returns the unique identifier of the cultural heritage object.
     * @return The ID of the object.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the cultural heritage object.
     * @return The name of the object.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the cultural heritage object.
     * @param name The new name for the object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the cultural heritage object.
     * @return The description of the object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the cultural heritage object.
     * @param description The new description for the object.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the origin of the cultural heritage object.
     * @return The origin of the object.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of the cultural heritage object.
     * @param origin The new origin for the object.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Returns the year of the cultural heritage object.
     * @return The year of the object.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the cultural heritage object.
     * @param year The new year for the object.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Provides a string representation of the CulturalHeritageObject.
     * @return A formatted string containing the object's details.
     */
    @Override
    public String toString() {
        return "ID: " + id +
               ", Name: " + name +
               ", Description: " + description +
               ", Origin: " + origin +
               ", Year: " + year;
    }
}