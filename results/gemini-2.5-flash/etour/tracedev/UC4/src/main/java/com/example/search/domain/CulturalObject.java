package com.example.search.domain;

/**
 * Represents a cultural object in the domain.
 */
public class CulturalObject {
    private String id;
    private String name;
    private String description;
    private String type;
    private int year;

    /**
     * Constructs a new CulturalObject.
     *
     * @param id The unique identifier of the cultural object.
     * @param name The name of the cultural object.
     * @param description A brief description of the cultural object.
     * @param type The type or category of the cultural object.
     * @param year The year associated with the cultural object (e.g., year of creation).
     */
    public CulturalObject(String id, String name, String description, String type, int year) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.year = year;
    }

    // Getters for all attributes
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "CulturalObject{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", type='" + type + '\'' +
               ", year=" + year +
               '}';
    }
}