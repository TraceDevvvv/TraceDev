import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a restaurant point (point of rest) that can have banners.
 * Manages the maximum allowed number of banners and the current list of banners.
 */
public class RestaurantPoint {
    private final int maxBanners;
    private final List<Banner> banners;
    private final String pointId;
    private final String pointName;
    
    /**
     * Constructor for RestaurantPoint with default name.
     * @param maxBanners Maximum number of banners allowed for this point
     */
    public RestaurantPoint(int maxBanners) {
        this(maxBanners, "POINT_" + System.currentTimeMillis(), "Restaurant Point");
    }
    
    /**
     * Constructor for RestaurantPoint with custom ID and name.
     * @param maxBanners Maximum number of banners allowed for this point
     * @param pointId Unique identifier for the restaurant point
     * @param pointName Name of the restaurant point
     * @throws IllegalArgumentException if maxBanners is less than 1
     */
    public RestaurantPoint(int maxBanners, String pointId, String pointName) {
        if (maxBanners < 1) {
            throw new IllegalArgumentException("Maximum banners must be at least 1");
        }
        if (pointId == null || pointId.trim().isEmpty()) {
            throw new IllegalArgumentException("Point ID cannot be null or empty");
        }
        
        this.maxBanners = maxBanners;
        this.pointId = pointId;
        this.pointName = pointName != null ? pointName : "Restaurant Point";
        this.banners = new ArrayList<>();
    }
    
    /**
     * Adds a banner to the restaurant point.
     * @param banner The banner to add
     * @return true if banner was added successfully, false if max banners reached
     * @throws IllegalArgumentException if banner is null
     */
    public boolean addBanner(Banner banner) {
        if (banner == null) {
            throw new IllegalArgumentException("Banner cannot be null");
        }
        
        if (hasReachedMaxBanners()) {
            return false;
        }
        
        banners.add(banner);
        return true;
    }
    
    /**
     * Removes a banner from the restaurant point.
     * @param banner The banner to remove
     * @return true if banner was removed, false if banner not found
     */
    public boolean removeBanner(Banner banner) {
        return banners.remove(banner);
    }
    
    /**
     * Removes a banner by its index.
     * @param index Index of the banner to remove
     * @return The removed banner, or null if index is invalid
     */
    public Banner removeBanner(int index) {
        if (index >= 0 && index < banners.size()) {
            return banners.remove(index);
        }
        return null;
    }
    
    /**
     * Checks if the restaurant point has reached the maximum number of banners.
     * @return true if current banners count >= maxBanners, false otherwise
     */
    public boolean hasReachedMaxBanners() {
        return banners.size() >= maxBanners;
    }
    
    /**
     * Gets the current number of banners.
     * @return Current banner count
     */
    public int getCurrentBannerCount() {
        return banners.size();
    }
    
    /**
     * Gets the maximum number of banners allowed.
     * @return Maximum banner limit
     */
    public int getMaxBanners() {
        return maxBanners;
    }
    
    /**
     * Gets all banners as an unmodifiable list.
     * @return Unmodifiable list of banners
     */
    public List<Banner> getBanners() {
        return Collections.unmodifiableList(banners);
    }
    
    /**
     * Gets a specific banner by index.
     * @param index Index of the banner to retrieve
     * @return The banner at the specified index, or null if index is invalid
     */
    public Banner getBanner(int index) {
        if (index >= 0 && index < banners.size()) {
            return banners.get(index);
        }
        return null;
    }
    
    /**
     * Clears all banners from the restaurant point.
     */
    public void clearBanners() {
        banners.clear();
    }
    
    /**
     * Gets the restaurant point ID.
     * @return Point ID
     */
    public String getPointId() {
        return pointId;
    }
    
    /**
     * Gets the restaurant point name.
     * @return Point name
     */
    public String getPointName() {
        return pointName;
    }
    
    /**
     * Checks if the restaurant point has any banners.
     * @return true if banners list is not empty, false otherwise
     */
    public boolean hasBanners() {
        return !banners.isEmpty();
    }
    
    /**
     * Calculates the remaining banner slots available.
     * @return Number of additional banners that can be added
     */
    public int getRemainingBannerSlots() {
        return Math.max(0, maxBanners - banners.size());
    }
    
    /**
     * Returns a string representation of the restaurant point.
     * @return Restaurant point details
     */
    @Override
    public String toString() {
        return "RestaurantPoint{" +
                "pointId='" + pointId + '\'' +
                ", pointName='" + pointName + '\'' +
                ", maxBanners=" + maxBanners +
                ", currentBanners=" + banners.size() +
                ", hasReachedMax=" + hasReachedMaxBanners() +
                '}';
    }
}