package com.example.bannerapp;

/**
 * Represents a Banner entity with its properties.
 */
public class Banner {
    private String id;
    private String name;
    private String imageUrl;
    private String pointOfRestId;

    /**
     * Constructs a new Banner.
     * @param id The unique identifier for the banner.
     * @param name The name of the banner.
     * @param imageUrl The URL of the banner image.
     * @param pointOfRestId The ID of the Point of Rest this banner belongs to.
     */
    public Banner(String id, String name, String imageUrl, String pointOfRestId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.pointOfRestId = pointOfRestId;
    }

    /**
     * Gets the unique identifier of the banner.
     * @return The banner's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the banner.
     * @return The banner's name.
     */
    public String getName() {
        return name;
    }

    // Assuming other getters might be needed based on context, but only getId and getName are explicitly shown.
    // Adding for completeness and potential future use.
    public String getImageUrl() {
        return imageUrl;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    @Override
    public String toString() {
        return "Banner{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", pointOfRestId='" + pointOfRestId + '\'' +
               '}';
    }
}