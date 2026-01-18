package com.example.model;

import com.example.dto.SiteDetailsDTO;
import java.math.BigDecimal;

/**
 * Entity representing a site in the database.
 */
public class SiteEntity {
    public String id;
    public String name;
    public String description;
    public String location;
    public String historicalPeriod;
    public double rating;

    // Additional fields that might be in the DTO but not in the entity diagram?
    // The diagram shows only the above fields in SiteEntity.
    // We assume toDTO() creates a DTO with default values for missing fields.

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getHistoricalPeriod() { return historicalPeriod; }
    public void setHistoricalPeriod(String historicalPeriod) { this.historicalPeriod = historicalPeriod; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    /**
     * Converts this entity to a DTO (renamed from addToResponse() to toDTO()).
     * @return SiteDetailsDTO populated with entity data and default values.
     */
    public SiteDetailsDTO toDTO() {
        SiteDetailsDTO dto = new SiteDetailsDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setLocation(this.location);
        dto.setHistoricalPeriod(this.historicalPeriod);
        dto.setRating(this.rating);
        // Set default values for fields not present in the entity
        dto.setOpeningHours("09:00-17:00");
        dto.setAdmissionFee(new BigDecimal("10.00"));
        return dto;
    }
}