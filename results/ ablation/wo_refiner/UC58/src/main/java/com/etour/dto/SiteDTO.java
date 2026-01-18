package com.etour.dto;

import com.etour.domain.Site;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Site.
 * Used to transfer site data between layers.
 */
public class SiteDTO {
    private String id;
    private String name;
    private String description;
    private String location;
    private double rating;

    public SiteDTO(String id, String name, String description, String location, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public double getRating() {
        return rating;
    }

    /**
     * Static factory method to create a SiteDTO from a Site entity.
     *
     * @param site the Site entity
     * @return a new SiteDTO
     */
    public static SiteDTO fromEntity(Site site) {
        return new SiteDTO(
                site.getId(),
                site.getName(),
                site.getDescription(),
                site.getLocation(),
                site.getRating());
    }
}