'''
Represents a Banner advertisement with its properties.
This class models a banner ad for a point of rest restaurant.
'''
public class Banner {
    private String name;
    private String imageUrl;
    private String status;
    /**
     * Constructor to create a new Banner.
     * @param name The name of the banner
     * @param imageUrl The URL of the banner image
     * @param status The status of the banner (Active/Inactive)
     */
    public Banner(String name, String imageUrl, String status) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.status = status;
    }
    /**
     * Get the name of the banner.
     * @return The banner name
     */
    public String getName() {
        return name;
    }
    /**
     * Set the name of the banner.
     * @param name The new banner name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the image URL of the banner.
     * @return The image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }
    /**
     * Set the image URL of the banner.
     * @param imageUrl The new image URL
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    /**
     * Get the status of the banner.
     * @return The banner status
     */
    public String getStatus() {
        return status;
    }
    /**
     * Set the status of the banner.
     * @param status The new status (Active/Inactive)
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * String representation of the Banner.
     * @return A formatted string with banner details
     */
    @Override
    public String toString() {
        return name + " (" + status + ")";
    }
    /**
     * Check if two banners are equal based on their name.
     * @param obj The object to compare
     * @return true if the banners have the same name (case-insensitive)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Banner banner = (Banner) obj;
        return name.equalsIgnoreCase(banner.name);
    }
    /**
     * Generate hash code based on banner name (case-insensitive).
     * @return The hash code
     */
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}