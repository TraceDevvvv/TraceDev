// CulturalHeritage.java
package com.example.culturalheritage;

/**
 * Represents a cultural heritage item with properties such as ID, name, description, and location.
 * This class is a data model for cultural heritage objects.
 */
public class CulturalHeritage {
    private String id;
    private String name;
    private String description;
    private String location;

    /**
     * Constructs a new CulturalHeritage object.
     *
     * @param id The unique identifier for the cultural heritage item.
     * @param name The name of the cultural heritage item.
     * @param description A detailed description of the cultural heritage item.
     * @param location The geographical location of the cultural heritage item.
     */
    public CulturalHeritage(String id, String name, String description, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    /**
     * Returns the ID of the cultural heritage item.
     * @return The ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the cultural heritage item.
     * @param id The new ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the cultural heritage item.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the cultural heritage item.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the cultural heritage item.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the cultural heritage item.
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the location of the cultural heritage item.
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the cultural heritage item.
     * @param location The new location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Provides a string representation of the CulturalHeritage object,
     * useful for displaying its details.
     * @return A formatted string containing the cultural heritage details.
     */
    @Override
    public String toString() {
        return "CulturalHeritage Details:\n" +
               "  ID: " + id + "\n" +
               "  Name: " + name + "\n" +
               "  Description: " + description + "\n" +
               "  Location: " + location;
    }
}