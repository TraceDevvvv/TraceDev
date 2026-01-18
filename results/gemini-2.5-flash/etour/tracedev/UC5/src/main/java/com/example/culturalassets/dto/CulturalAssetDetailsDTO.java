package com.example.culturalassets.dto;

import com.example.culturalassets.domain.CulturalAsset;
import java.util.Date;

/**
 * Data Transfer Object (DTO) for cultural asset details.
 * This object is used to transfer specific data from the application layer
 * to the presentation layer, potentially hiding internal domain details
 * and providing presentation-specific formatting.
 */
public class CulturalAssetDetailsDTO {
    private String id;
    private String name;
    private String description;
    private String location;
    private String type;
    private Date lastUpdated;

    /**
     * Private constructor to enforce creation via the static factory method.
     * @param id The unique ID of the cultural asset.
     * @param name The name of the cultural asset.
     * @param description A brief description of the asset.
     * @param location The physical or conceptual location.
     * @param type The category or type of the asset.
     * @param lastUpdated The timestamp of the last update or retrieval.
     */
    private CulturalAssetDetailsDTO(String id, String name, String description, String location, String type, Date lastUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Static factory method to create a CulturalAssetDetailsDTO from a CulturalAsset domain object
     * and a specific last updated timestamp.
     * This method acts as the conversion point from domain model to DTO.
     * @param asset The CulturalAsset domain object.
     * @param lastUpdated The date when the asset details were last updated or retrieved.
     * @return A new CulturalAssetDetailsDTO instance.
     */
    public static CulturalAssetDetailsDTO createFrom(CulturalAsset asset, Date lastUpdated) {
        System.out.println("DTO: Creating CulturalAssetDetailsDTO from CulturalAsset.");
        return new CulturalAssetDetailsDTO(
            asset.getId(),
            asset.getName(),
            asset.getDescription(),
            asset.getLocation(),
            asset.getType(),
            lastUpdated
        );
    }

    // Getters for all attributes
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

    public String getType() {
        return type;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return "CulturalAssetDetailsDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", location='" + location + '\'' +
               ", type='" + type + '\'' +
               ", lastUpdated=" + lastUpdated +
               '}';
    }
}