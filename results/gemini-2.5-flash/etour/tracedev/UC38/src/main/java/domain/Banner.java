package domain;

import java.util.UUID;

/**
 * Represents a banner entity in the domain.
 * Each banner has a unique ID, an image URL, and is associated with a PointOfRestaurant.
 */
public class Banner {
    private String id;
    private String imageUrl;
    private String pointOfRestaurantId;

    /**
     * Constructs a new Banner.
     * The ID is automatically generated upon creation.
     *
     * @param imageUrl The URL of the banner image.
     * @param pointOfRestaurantId The ID of the PointOfRestaurant this banner belongs to.
     */
    public Banner(String imageUrl, String pointOfRestaurantId) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the banner
        this.imageUrl = imageUrl;
        this.pointOfRestaurantId = pointOfRestaurantId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the banner. This might be used by a repository
     * if it needs to assign a persistent ID.
     *
     * @param id The new ID for the banner.
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Banner{" +
               "id='" + id + '\'' +
               ", imageUrl='" + imageUrl + '\'' +
               ", pointOfRestaurantId='" + pointOfRestaurantId + '\'' +
               '}';
    }
}