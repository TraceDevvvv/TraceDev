import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a refreshment point (rest point) with associated banners.
 * This class manages the collection of banners at a specific location.
 */
public class RestPoint {
    private String pointId;
    private String pointName;
    private String location;
    private List<Banner> banners;
    private Date createdAt;
    
    /**
     * Constructor for creating a new refreshment point.
     * @param pointId Unique identifier for the refreshment point
     * @param pointName Name of the refreshment point
     * @param location Physical location description
     */
    public RestPoint(String pointId, String pointName, String location) {
        this.pointId = pointId;
        this.pointName = pointName;
        this.location = location;
        this.banners = new ArrayList<>();
        this.createdAt = new Date();
    }
    
    /**
     * Adds a banner to this refreshment point.
     * @param banner The banner to add
     * @return true if banner was added successfully, false if banner already exists
     */
    public boolean addBanner(Banner banner) {
        if (banner == null) {
            return false;
        }
        
        // Check if banner with same ID already exists
        boolean exists = banners.stream()
            .anyMatch(b -> b.getBannerId().equals(banner.getBannerId()));
        
        if (!exists) {
            banners.add(banner);
            return true;
        }
        
        return false;
    }
    
    /**
     * Removes a banner from this refreshment point.
     * @param bannerId ID of the banner to remove
     * @return true if banner was removed, false if banner not found
     */
    public boolean removeBanner(String bannerId) {
        if (bannerId == null) {
            return false;
        }
        
        return banners.removeIf(banner -> banner.getBannerId().equals(bannerId));
    }
    
    /**
     * Gets a banner by its ID.
     * @param bannerId ID of the banner to retrieve
     * @return the Banner object, or null if not found
     */
    public Banner getBanner(String bannerId) {
        if (bannerId == null) {
            return null;
        }
        
        return banners.stream()
            .filter(banner -> banner.getBannerId().equals(bannerId))
            .findFirst()
            .orElse(null);
    }
    
    /**
     * Gets all banners associated with this refreshment point.
     * @return list of banners (unmodifiable)
     */
    public List<Banner> getBanners() {
        return Collections.unmodifiableList(banners);
    }
    
    /**
     * Gets the number of banners at this refreshment point.
     * @return count of banners
     */
    public int getBannerCount() {
        return banners.size();
    }
    
    /**
     * Gets banners that have valid images.
     * @return list of banners with valid images
     */
    public List<Banner> getValidBanners() {
        return banners.stream()
            .filter(Banner::validateImage)
            .collect(Collectors.toList());
    }
    
    /**
     * Updates a banner image at this refreshment point.
     * @param bannerId ID of the banner to update
     * @param newImagePath Path to the new image file
     * @return true if update was successful, false otherwise
     */
    public boolean updateBannerImage(String bannerId, String newImagePath) {
        Banner banner = getBanner(bannerId);
        if (banner == null) {
            return false;
        }
        
        return banner.updateImage(newImagePath);
    }
    
    /**
     * Validates all banners at this refreshment point.
     * @return list of banners with invalid images
     */
    public List<Banner> validateAllBanners() {
        List<Banner> invalidBanners = new ArrayList<>();
        
        for (Banner banner : banners) {
            if (!banner.validateImage()) {
                invalidBanners.add(banner);
            }
        }
        
        return invalidBanners;
    }
    
    /**
     * Gets the point ID.
     * @return point ID
     */
    public String getPointId() {
        return pointId;
    }
    
    /**
     * Gets the point name.
     * @return point name
     */
    public String getPointName() {
        return pointName;
    }
    
    /**
     * Gets the location description.
     * @return location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Gets the creation timestamp.
     * @return creation date
     */
    public Date getCreatedAt() {
        return createdAt;
    }
    
    /**
     * Sets the point name.
     * @param pointName New point name
     */
    public void setPointName(String pointName) {
        this.pointName = pointName;
    }
    
    /**
     * Sets the location description.
     * @param location New location
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /**
     * Returns a string representation of the refreshment point.
     * @return string representation
     */
    @Override
    public String toString() {
        return "RestPoint{" +
                "pointId='" + pointId + '\'' +
                ", pointName='" + pointName + '\'' +
                ", location='" + location + '\'' +
                ", bannerCount=" + banners.size() +
                ", createdAt=" + createdAt +
                '}';
    }
    
    /**
     * Creates a summary of all banners at this point.
     * @return formatted string with banner details
     */
    public String getBannersSummary() {
        if (banners.isEmpty()) {
            return "No banners associated with this point.";
        }
        
        StringBuilder summary = new StringBuilder();
        summary.append("Banners at ").append(pointName).append(":\n");
        
        for (int i = 0; i < banners.size(); i++) {
            Banner banner = banners.get(i);
            summary.append(i + 1).append(". ID: ").append(banner.getBannerId())
                   .append(", Image: ").append(banner.getImagePath())
                   .append(", Valid: ").append(banner.validateImage() ? "Yes" : "No")
                   .append("\n");
        }
        
        return summary.toString();
    }
    
    /**
     * Clears all banners from this refreshment point.
     * @return number of banners removed
     */
    public int clearAllBanners() {
        int count = banners.size();
        banners.clear();
        return count;
    }
    
    /**
     * Checks if this refreshment point has any banners.
     * @return true if has banners, false otherwise
     */
    public boolean hasBanners() {
        return !banners.isEmpty();
    }
}