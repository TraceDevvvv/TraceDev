package com.example.culturalheritage;

/**
 * Represents a cultural object with properties such as ID, name, description,
 * category, and location. This class is a data model for cultural heritage items.
 */
public class CulturalObject {
    private String id;
    private String name;
    private String description;
    private String category;
    private String location;
    private int year; // Added for more detailed search criteria

    /**
     * Constructs a new CulturalObject.
     *
     * @param id The unique identifier of the cultural object.
     * @param name The name of the cultural object.
     * @param description A brief description of the cultural object.
     * @param category The category to which the cultural object belongs (e.g., "Painting", "Sculpture", "Building").
     * @param location The physical location of the cultural object.
     * @param year The year the cultural object was created or discovered.
     */
    public CulturalObject(String id, String name, String description, String category, String location, int year) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.year = year;
    }

    /**
     * Returns the ID of the cultural object.
     * @return The ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the cultural object.
     * @param id The new ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the cultural object.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the cultural object.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the cultural object.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the cultural object.
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the category of the cultural object.
     * @return The category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the cultural object.
     * @param category The new category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the location of the cultural object.
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the cultural object.
     * @param location The new location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the year of the cultural object.
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the cultural object.
     * @param year The new year.
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CulturalObject{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", category='" + category + '\'' +
               ", location='" + location + '\'' +
               ", year=" + year +
               '}';
    }
}