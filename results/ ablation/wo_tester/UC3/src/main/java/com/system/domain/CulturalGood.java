package com.system.domain;

import java.util.Date;
import java.util.Map;

/**
 * Represents a cultural good/heritage item.
 */
public class CulturalGood {
    private String id;
    private String name;
    private String description;
    private String category;
    private String location;
    private Date lastUpdated;

    public CulturalGood(String id, String name, String description, String category, String location, Date lastUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Updates the cultural good with new data from the form.
     * @param data Map containing field names and new values.
     */
    public void updateData(Map<String, Object> data) {
        if (data.containsKey("name")) {
            this.name = (String) data.get("name");
        }
        if (data.containsKey("description")) {
            this.description = (String) data.get("description");
        }
        if (data.containsKey("category")) {
            this.category = (String) data.get("category");
        }
        if (data.containsKey("location")) {
            this.location = (String) data.get("location");
        }
        this.lastUpdated = new Date();
    }
}