package com.example.dto;

import com.example.model.CulturalGood;

/**
 * Data Transfer Object for CulturalGood, used to send data to the UI.
 * As per class diagram, it maps from a CulturalGood entity.
 */
public class CulturalGoodDTO {
    private String id;
    private String title;
    private String description;
    private String location;
    private String period;

    /**
     * Constructor that maps a CulturalGood entity to a DTO.
     * Corresponds to step 7 in the sequence diagram.
     * @param culturalGood the entity to map from
     */
    public CulturalGoodDTO(CulturalGood culturalGood) {
        this.id = culturalGood.getId();
        this.title = culturalGood.getTitle();
        this.description = culturalGood.getDescription();
        this.location = culturalGood.getLocation();
        this.period = culturalGood.getPeriod();
    }

    // Getters as per class diagram
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getPeriod() {
        return period;
    }
}