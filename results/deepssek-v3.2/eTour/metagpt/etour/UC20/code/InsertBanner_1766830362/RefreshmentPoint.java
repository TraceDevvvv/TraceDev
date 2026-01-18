package com.etur.insertbanner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a refreshment point (point of rest/eating establishment) 
 * that can have banners associated with it. Manages banner associations
 * and validates if maximum banner limit is reached.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String address;
    private String city;
    private String agencyId; // ID of the agency that manages this point
    private int maximumBanners; // Maximum number of banners allowed
    private List<Banner> banners; // List of associated banners
    
    // Default maximum banners if not specified
    public static final int DEFAULT_MAX_BANNERS = 5;
    
    /**
     * Constructor for creating a new refreshment point with default maximum banners.
     * 
     * @param name Name of the refreshment point
     * @param address Address of the refreshment point
     * @param city City where the refreshment point is located
     * @param agencyId ID of the managing agency
     */
    public RefreshmentPoint(String name, String address, String city, String agencyId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.city = city;
        this.agencyId = agencyId;
        this.maximumBanners = DEFAULT_MAX_BANNERS;
        this.banners = new ArrayList<>();
    }
    
    /**
     * Constructor for creating a new refreshment point with custom maximum banners.
     * 
     * @param name Name of the refreshment point
     * @param address Address of the refreshment point
     * @param city City where the refreshment point is located
     * @param agencyId ID of the managing agency
     * @param maximumBanners Maximum number of banners allowed
     */
    public RefreshmentPoint(String name, String address, String city, String agencyId, int maximumBanners) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.city = city;
        this.agencyId = agencyId;
        this.maximumBanners = maximumBanners > 0 ? maximumBanners : DEFAULT_MAX_BANNERS;
        this.banners = new ArrayList<>();
    }
    
    /**
     * Checks if the refreshment point has reached its maximum banner capacity.
     * 
     * @return true if maximum banners reached, false otherwise
     */
    public boolean hasReachedMaxBanners() {
        return banners.size() >= maximumBanners;
    }
    
    /**
     * Gets the number of remaining banner slots available.
     * 
     * @return Number of banners that can still be added
     */
    public int getRemainingBannerSlots() {
        return Math.max(0, maximumBanners - banners.size());
    }
    
    /**
     * Adds a banner to the refreshment point if capacity allows.
     * 
     * @param banner The banner to add
     * @return true if banner was added successfully, false if maximum reached
     * @throws IllegalArgumentException if banner is null or belongs to a different point
     */
    public boolean addBanner(Banner banner) {
        if (banner == null) {
            throw new IllegalArgumentException("Banner cannot be null");
        }
        
        // Check if banner already belongs to a different refreshment point
        if (banner.getRefreshmentPointId() != null && !banner.getRefreshmentPointId().equals(this.id)) {
            throw new IllegalArgumentException("Banner belongs to a different refreshment point");
        }
        
        // Update banner's refreshment point ID if not set
        if (banner.getRefreshmentPointId() == null) {
            banner.setRefreshmentPointId(this.id);
        }
        
        // Check if maximum capacity reached
        if (hasReachedMaxBanners()) {
            return false;
        }
        
        return banners.add(banner);
    }
    
    /**
     * Removes a banner from the refreshment point.
     * 
     * @param bannerId ID of the banner to remove
     * @return true if banner was removed, false if not found
     */
    public boolean removeBanner(String bannerId) {
        for (int i = 0; i < banners.size(); i++) {
            if (banners.get(i).getId().equals(bannerId)) {
                banners.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets a banner by its ID.
     * 
     * @param bannerId ID of the banner to retrieve
     * @return The banner if found, null otherwise
     */
    public Banner getBanner(String bannerId) {
        for (Banner banner : banners) {
            if (banner.getId().equals(bannerId)) {
                return banner;
            }
        }
        return null;
    }
    
    /**
     * Gets all banners associated with this refreshment point.
     * 
     * @return List of all banners (returns a copy to prevent external modification)
     */
    public List<Banner> getAllBanners() {
        return new ArrayList<>(banners);
    }
    
    /**
     * Gets a summary of the refreshment point's banner status.
     * 
     * @return String containing banner count and limit information
     */
    public String getBannerStatusSummary() {
        return String.format("%s has %d/%d banners (%d slots remaining)", 
                name, banners.size(), maximumBanners, getRemainingBannerSlots());
    }
    
    /**
     * Validates if a new banner can be added to this refreshment point.
     * Used before actual insertion to check constraints.
     * 
     * @return true if a new banner can be added, false otherwise
     */
    public boolean canAddNewBanner() {
        return !hasReachedMaxBanners();
    }
    
    // Getters and setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAgencyId() {
        return agencyId;
    }
    
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    
    public int getMaximumBanners() {
        return maximumBanners;
    }
    
    public void setMaximumBanners(int maximumBanners) {
        if (maximumBanners <= 0) {
            throw new IllegalArgumentException("Maximum banners must be positive");
        }
        
        // If reducing maximum and current banners exceed new maximum,
        // we don't automatically remove banners, but it will prevent new additions
        this.maximumBanners = maximumBanners;
    }
    
    public int getBannerCount() {
        return banners.size();
    }
    
    /**
     * Returns a string representation of the refreshment point.
     * 
     * @return String containing refreshment point details
     */
    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", agencyId='" + agencyId + '\'' +
                ", maximumBanners=" + maximumBanners +
                ", currentBanners=" + banners.size() +
                '}';
    }
    
    /**
     * Provides detailed information about the refreshment point and its banners.
     * 
     * @return String containing detailed information
     */
    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Refreshment Point Details:\n");
        info.append("========================\n");
        info.append("ID: ").append(id).append("\n");
        info.append("Name: ").append(name).append("\n");
        info.append("Address: ").append(address).append("\n");
        info.append("City: ").append(city).append("\n");
        info.append("Agency ID: ").append(agencyId).append("\n");
        info.append("Banner Capacity: ").append(banners.size()).append("/").append(maximumBanners).append("\n");
        info.append("Can Add New Banner: ").append(canAddNewBanner() ? "Yes" : "No (max reached)").append("\n");
        
        if (!banners.isEmpty()) {
            info.append("\nAssociated Banners:\n");
            info.append("-----------------\n");
            for (int i = 0; i < banners.size(); i++) {
                Banner banner = banners.get(i);
                info.append(i + 1).append(". ").append(banner.getImageName())
                    .append(" (").append(banner.getImageFormat()).append(", ")
                    .append(banner.getWidth()).append("x").append(banner.getHeight()).append(")\n");
            }
        } else {
            info.append("\nNo banners associated with this refreshment point.\n");
        }
        
        return info.toString();
    }
}