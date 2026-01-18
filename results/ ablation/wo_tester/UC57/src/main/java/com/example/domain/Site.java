package com.example.domain;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a tourist site with location, rating, and amenities.
 */
public class Site {
    public String id;
    public String name;
    public String category;
    public double latitude;
    public double longitude;
    public double rating;
    public List<String> amenities;
    public Date createdDate;

    public Site(String id, String name, String category, double latitude, double longitude, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.amenities = new ArrayList<>();
        this.createdDate = new Date();
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Calculates distance from given latitude and longitude using Haversine formula.
     */
    public double getDistanceFrom(double lat, double lon) {
        final int R = 6371; // Earth's radius in kilometers
        double latDistance = Math.toRadians(lat - latitude);
        double lonDistance = Math.toRadians(lon - longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(lat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}