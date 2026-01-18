package com.example.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a Turning Point, which can contain multiple banners.
 * Part of the Domain Layer.
 */
public class TurningPoint {
    private String id;
    private String name;
    private List<Banner> banners; // Association: TurningPoint "1" -- "0..*" Banner : has

    /**
     * Constructor for TurningPoint.
     * @param id The unique identifier for the turning point.
     * @param name The name of the turning point.
     */
    public TurningPoint(String id, String name) {
        this.id = id;
        this.name = name;
        this.banners = new ArrayList<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable list of banners associated with this turning point.
     * @return A list of banners.
     */
    public List<Banner> getBanners() {
        return Collections.unmodifiableList(banners);
    }

    /**
     * Finds a specific banner within this turning point by its ID.
     * @param bannerId The ID of the banner to find.
     * @return The Banner object if found, otherwise null.
     */
    public Banner findBanner(String bannerId) {
        return banners.stream()
                .filter(b -> b.getId().equals(bannerId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a banner to this turning point.
     * @param banner The banner to add.
     */
    public void addBanner(Banner banner) {
        if (banner != null && !banners.contains(banner)) {
            this.banners.add(banner);
        }
    }

    /**
     * Removes a banner from this turning point by its ID.
     * REQ-CD-003: Added removeBanner method.
     * @param bannerId The ID of the banner to remove.
     * @return true if the banner was found and removed, false otherwise.
     */
    public boolean removeBanner(String bannerId) { // REQ-CD-003
        return banners.removeIf(banner -> banner.getId().equals(bannerId));
    }

    // For demonstration purposes, you might want a toString or equals/hashCode
    @Override
    public String toString() {
        return "TurningPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bannersCount=" + banners.size() +
                '}';
    }
}