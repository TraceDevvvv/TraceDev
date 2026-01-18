package com.example.model;

import java.util.Date;
import java.util.Map;

/**
 * Represents a Cultural Heritage entity.
 * Maps to the CulturalHeritage class in the UML diagram.
 */
public class CulturalHeritage {
    private String id;
    private String title;
    private String description;
    private Date creationDate;
    private String location;
    private String category;

    /**
     * Constructor as per UML specification.
     */
    public CulturalHeritage(String id, String title, String description,
                            Date creationDate, String location, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.location = location;
        this.category = category;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Updates the cultural heritage with the provided data.
     * Implementation follows the UML method signature.
     */
    public void update(Map<String, Object> data) {
        // Update fields only if they exist in the map and are of correct type
        if (data.containsKey("title") && data.get("title") instanceof String) {
            this.title = (String) data.get("title");
        }
        if (data.containsKey("description") && data.get("description") instanceof String) {
            this.description = (String) data.get("description");
        }
        if (data.containsKey("creationDate") && data.get("creationDate") instanceof Date) {
            this.creationDate = (Date) data.get("creationDate");
        }
        if (data.containsKey("location") && data.get("location") instanceof String) {
            this.location = (String) data.get("location");
        }
        if (data.containsKey("category") && data.get("category") instanceof String) {
            this.category = (String) data.get("category");
        }
    }
}