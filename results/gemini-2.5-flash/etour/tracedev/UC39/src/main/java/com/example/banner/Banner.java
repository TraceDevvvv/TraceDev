package com.example.banner;

/**
 * Represents a Banner entity in the system.
 */
public class Banner {
    private String id;
    private String name;
    private String currentImageUrl;
    private String restaurantId;

    /**
     * Constructs a new Banner instance.
     * @param id The unique identifier of the banner.
     * @param name The name of the banner.
     * @param currentImageUrl The URL of the image currently displayed on the banner.
     * @param restaurantId The ID of the restaurant this banner belongs to.
     */
    public Banner(String id, String name, String currentImageUrl, String restaurantId) {
        this.id = id;
        this.name = name;
        this.currentImageUrl = currentImageUrl;
        this.restaurantId = restaurantId;
    }

    // --- Getters ---

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

    /**
     * Gets the current image URL of the banner.
     * @return The current image URL.
     */
    public String getCurrentImageUrl() {
        return currentImageUrl;
    }

    /**
     * Gets the ID of the restaurant this banner belongs to.
     * @return The restaurant's ID.
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    // --- Methods ---

    /**
     * Updates the current image URL of the banner.
     * @param newUrl The new image URL to set.
     */
    public void updateImageUrl(String newUrl) {
        // Comment: This method directly modifies the banner's image URL.
        this.currentImageUrl = newUrl;
        System.out.println("[Banner] Banner '" + name + "' (" + id + ") image updated to: " + newUrl);
    }

    @Override
    public String toString() {
        return "Banner{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", currentImageUrl='" + currentImageUrl + '\'' +
               ", restaurantId='" + restaurantId + '\'' +
               '}';
    }
}