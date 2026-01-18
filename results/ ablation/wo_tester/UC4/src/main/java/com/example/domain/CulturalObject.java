package com.example.domain;

import java.util.Date;

/**
 * Domain entity representing a cultural object.
 */
public class CulturalObject {
    private Long id;
    private String title;
    private String description;
    private Date creationDate;
    private String culturalPeriod;
    private String location;

    public CulturalObject() {
    }

    public CulturalObject(Long id, String title, String description, Date creationDate, String culturalPeriod, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.culturalPeriod = culturalPeriod;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCulturalPeriod() {
        return culturalPeriod;
    }

    public void setCulturalPeriod(String culturalPeriod) {
        this.culturalPeriod = culturalPeriod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}