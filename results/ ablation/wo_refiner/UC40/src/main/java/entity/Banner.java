package entity;

/**
 * Banner entity representing a banner advertisement.
 * Contains banner details and business logic for deactivation.
 */
public class Banner {
    private String bannerId;
    private String pointOfRestId;
    private String imageUrl;
    private boolean isActive;

    public Banner(String bannerId, String pointOfRestId, String imageUrl, boolean isActive) {
        this.bannerId = bannerId;
        this.pointOfRestId = pointOfRestId;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Deactivates the banner.
     * Implements the first phase of the two‑phase delete for data integrity.
     */
    public void deactivate() {
        this.isActive = false;
    }
}