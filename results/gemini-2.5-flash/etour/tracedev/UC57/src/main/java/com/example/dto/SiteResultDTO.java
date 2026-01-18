package com.example.dto;

/**
 * Data Transfer Object for search results.
 * Carries simplified site data from the application layer back to the presentation layer.
 */
public class SiteResultDTO {
    public String id;
    public String name;
    public String description;
    public double distanceKm;

    /**
     * Constructor for SiteResultDTO.
     * @param id The unique identifier of the site.
     * @param name The name of the site.
     * @param description A brief description of the site.
     * @param distanceKm The distance of the site from the user's location in kilometers.
     */
    public SiteResultDTO(String id, String name, String description, double distanceKm) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.distanceKm = distanceKm;
    }

    @Override
    public String toString() {
        return "SiteResultDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", distanceKm=" + String.format("%.2f", distanceKm) + "km" +
               '}';
    }
}