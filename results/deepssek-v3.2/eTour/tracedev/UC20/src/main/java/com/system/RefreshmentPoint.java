package com.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a refreshment point where banners can be displayed.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private Location location;
    private int maxBanners;
    private List<Banner> banners = new ArrayList<>();

    public RefreshmentPoint(String id, String name) {
        this.id = id;
        this.name = name;
        this.location = new Location("Unknown");
        this.maxBanners = 5; // Default maximum
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public int getMaxBanners() { return maxBanners; }
    public void setMaxBanners(int maxBanners) { this.maxBanners = maxBanners; }
    public List<Banner> getBanners() { return banners; }
    public void setBanners(List<Banner> banners) { this.banners = banners; }

    /**
     * Adds a banner to this refreshment point if the maximum is not reached.
     * @param banner the banner to add
     * @return true if banner was added successfully
     */
    public boolean addBanner(Banner banner) {
        if (hasReachedMaxBanners()) {
            return false;
        }
        banners.add(banner);
        return true;
    }

    /**
     * Gets the current number of banners.
     * @return the count of banners
     */
    public int getCurrentBannerCount() {
        return banners.size();
    }

    /**
     * Checks if the point has reached its maximum banner capacity.
     * @return true if current count >= maxBanners
     */
    public boolean hasReachedMaxBanners() {
        return getCurrentBannerCount() >= maxBanners;
    }
}