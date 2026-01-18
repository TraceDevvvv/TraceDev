package com.example.dto;

import com.example.domain.CulturalObject;
import java.util.Date;

/**
 * Data Transfer Object for CulturalObject.
 * Used to transfer data to the presentation layer without exposing domain details.
 */
public class CulturalObjectDTO {
    private Long id;
    private String title;
    private String description;
    private String culturalPeriod;
    private String location;

    /**
     * Constructs a DTO from a CulturalObject entity.
     */
    public CulturalObjectDTO(CulturalObject culturalObject) {
        this.id = culturalObject.getId();
        this.title = culturalObject.getTitle();
        this.description = culturalObject.getDescription();
        this.culturalPeriod = culturalObject.getCulturalPeriod();
        this.location = culturalObject.getLocation();
    }

    // Getters only, as DTOs are typically read-only.
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCulturalPeriod() {
        return culturalPeriod;
    }

    public String getLocation() {
        return location;
    }
}