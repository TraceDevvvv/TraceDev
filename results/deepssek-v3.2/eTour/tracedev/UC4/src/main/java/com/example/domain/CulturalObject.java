package com.example.domain;

import java.util.Date;

/**
 * Represents a cultural object in the system.
 * Core domain entity containing metadata about cultural items.
 */
public class CulturalObject {
    private String id;
    private String title;
    private String description;
    private String creator;
    private Date date;
    private String type;

    public CulturalObject(String id, String title, String description, String creator, Date date, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.date = date;
        this.type = type;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CulturalObject{" +
                "title='" + title + '\'' +
                ", creator='" + creator + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}