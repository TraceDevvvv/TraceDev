
package com.example.domain;

import java.util.List;

/**
 * Domain entity representing a cultural site.
 */
public class CulturalSite {
    private SiteId id;
    private String name;
    private String description;
    private SiteType type;
    private GeographicCoordinate location;
    private List<String> openingHours;
    private double averageRating;

    public CulturalSite(SiteId id, String name, String desc, SiteType type,
                        GeographicCoordinate location, List<String> hours, double rating) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.type = type;
        this.location = location;
        this.openingHours = hours;
        this.averageRating = rating;
    }

    public CulturalSite(SiteId id, String name, String desc, SiteType type, GeographicCoordinate location) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.type = type;
        this.location = location;
        this.openingHours = null;
        this.averageRating = 0.0;
    }

    public SiteId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public SiteType getType() {
        return type;
    }

    public GeographicCoordinate getLocation() {
        return location;
    }

    public List<String> getOpeningHours() {
        return openingHours;
    }

    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Calculate distance from given coordinates using the Haversine formula.
     */
    public double calculateDistanceFrom(double lat, double lon) {
        return location.calculateDistanceTo(new GeographicCoordinate(lat, lon));
    }
}
