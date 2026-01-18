'''
Model class representing a Point of Rest (Punto di Ristoro).
It manages a list of banners associated with it and enforces a maximum number of banners.
'''
package com.chatdev.bannerapp.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PointOfRest {
    private String id;
    private String name;
    private List<Banner> banners;
    private int maxBanners;
    /**
     * Constructs a new PointOfRest instance.
     *
     * @param id The unique identifier for the point of rest.
     * @param name The name of the point of rest.
     * @param maxBanners The maximum number of banners allowed for this point of rest.
     */
    public PointOfRest(String id, String name, int maxBanners) {
        this.id = id;
        this.name = name;
        this.maxBanners = maxBanners;
        this.banners = new ArrayList<>();
    }
    /**
     * Gets the ID of the point of rest.
     * @return The point of rest ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the point of rest.
     * @param id The new point of rest ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Gets the name of the point of rest.
     * @return The point of rest name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the point of rest.
     * @param name The new point of rest name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets an unmodifiable list of banners associated with this point of rest.
     * @return An unmodifiable list of Banner objects.
     */
    public List<Banner> getBanners() {
        return Collections.unmodifiableList(banners);
    }
    /**
     * Adds a banner to this point of rest if the maximum banner limit has not been reached.
     * @param banner The Banner object to add.
     * @return True if the banner was added successfully, false if the maximum limit was reached.
     */
    public boolean addBanner(Banner banner) {
        if (canAddBanner()) {
            this.banners.add(banner);
            return true;
        }
        return false;
    }
    /**
     * Checks if a new banner can be added to this point of rest without exceeding the maximum limit.
     * @return True if more banners can be added, false otherwise.
     */
    public boolean canAddBanner() {
        return this.banners.size() < this.maxBanners;
    }
    /**
     * Gets the maximum number of banners allowed for this point of rest.
     * @return The maximum banner count.
     */
    public int getMaxBanners() {
        return maxBanners;
    }
    /**
     * Sets the maximum number of banners allowed for this point of rest.
     * @param maxBanners The new maximum banner count.
     */
    public void setMaxBanners(int maxBanners) {
        this.maxBanners = maxBanners;
    }
    @Override
    public String toString() {
        return name + " (ID: " + id + ", Banners: " + banners.size() + "/" + maxBanners + ")";
    }
}