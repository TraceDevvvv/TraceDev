package com.etour.search;

/**
 * Entity class representing a historical/cultural site.
 */
public class Site {
    public int id;
    public String name;
    public String description;
    public double latitude;
    public double longitude;
    public SiteCategory category;
    public String historicalPeriod;

    public Site() {
    }

    public Site(int id, String name, String description, double latitude, double longitude, 
                SiteCategory category, String historicalPeriod) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.historicalPeriod = historicalPeriod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public SiteCategory getCategory() {
        return category;
    }

    public void setCategory(SiteCategory category) {
        this.category = category;
    }

    public String getHistoricalPeriod() {
        return historicalPeriod;
    }

    public void setHistoricalPeriod(String historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    /**
     * Calculates the distance from this site to a given location.
     * @param location The location to calculate distance to.
     * @return Distance in kilometers.
     */
    public double getDistanceFrom(Location location) {
        // Simplified distance calculation using Haversine formula
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(location.getLatitude());
        double lon2 = Math.toRadians(location.getLongitude());
        
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        
        double a = Math.pow(Math.sin(dlat / 2), 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        
        double radius = 6371; // Earth's radius in kilometers
        return c * radius;
    }
}