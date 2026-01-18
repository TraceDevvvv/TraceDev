/*
 * Represents a Restaurant or "Point of Rest" that can have banners associated with it.
 * It manages a list of banners and enforces a maximum limit.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Restaurant {
    private String name;
    private int maxBanners;
    private List<Banner> banners; // List to store banners associated with this restaurant
    /**
     * Constructs a new Restaurant with a given name and maximum banner capacity.
     * @param name The name of the restaurant.
     * @param maxBanners The maximum number of banners this restaurant can have.
     */
    public Restaurant(String name, int maxBanners) {
        this.name = name;
        this.maxBanners = maxBanners;
        this.banners = new ArrayList<>(); // Initialize the list of banners
    }
    /**
     * Gets the name of the restaurant.
     * @return The restaurant's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the maximum number of banners allowed for this restaurant.
     * @return The maximum banner limit.
     */
    public int getMaxBanners() {
        return maxBanners;
    }
    /**
     * Adds a banner to the restaurant's list of banners.
     * This method assumes that the check for max banners and other validations
     * have been performed externally (e.g., by BannerService).
     * @param banner The Banner object to add.
     * @return True if the banner was added successfully, false otherwise.
     */
    public boolean addBanner(Banner banner) {
        // In a real system, this might involve saving to a database.
        // For this simulation, we just add to the in-memory list.
        if (banners.size() < maxBanners) {
            return banners.add(banner);
        }
        return false; // Max banners reached, though this check should ideally be done before calling this.
    }
    /**
     * Gets an unmodifiable list of banners currently associated with the restaurant.
     * @return An unmodifiable List of Banner objects.
     */
    public List<Banner> getBanners() {
        return Collections.unmodifiableList(banners); // Return an unmodifiable list to prevent external modification
    }
    /**
     * Checks if the restaurant can add more banners without exceeding its maximum limit.
     * @return True if more banners can be added, false otherwise.
     */
    public boolean canAddMoreBanners() {
        return banners.size() < maxBanners;
    }
    /**
     * Gets the current count of banners associated with the restaurant.
     * @return The number of banners.
     */
    public int getBannerCount() {
        return banners.size();
    }
}