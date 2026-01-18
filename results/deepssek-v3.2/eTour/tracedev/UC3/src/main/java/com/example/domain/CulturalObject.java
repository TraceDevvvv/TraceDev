package com.example.domain;

import com.example.dto.CulturalObjectDTO;
import java.util.Date;

/**
 * Domain entity representing a cultural object.
 */
public class CulturalObject {
    private int id;
    private String name;
    private String description;
    private String location;
    private String historicalPeriod;
    private Date createdAt;
    private Date updatedAt;

    public CulturalObject() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHistoricalPeriod() {
        return historicalPeriod;
    }

    public void setHistoricalPeriod(String historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Converts this domain object to a DTO.
     */
    public CulturalObjectDTO toDTO() {
        return new CulturalObjectDTO(id, name, description, location, historicalPeriod);
    }

    /**
     * Updates this domain object from DTO data.
     */
    public void updateFromDTO(CulturalObjectDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.location = dto.getLocation();
        this.historicalPeriod = dto.getHistoricalPeriod();
        this.updatedAt = new Date();
    }
}