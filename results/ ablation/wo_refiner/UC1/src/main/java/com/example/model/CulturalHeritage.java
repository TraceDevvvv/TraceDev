package com.example.model;

/**
 * Entity representing a cultural heritage item.
 */
public class CulturalHeritage {
    private int id;
    private String name;
    private String description;

    public CulturalHeritage(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void delete() {
        // This method represents the delete operation on the CulturalHeritage object.
        // In a real scenario, this might set a flag or perform cleanup.
        // For the purpose of traceability, we provide an empty implementation.
    }
}