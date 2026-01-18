package com.example.application;

import java.util.List;

/**
 * Data Transfer Object for site details.
 */
public class SiteDTO {
    private String id;
    private String name;
    private String category;
    private double distanceFromUser;
    private double rating;
    private List<String> amenities;

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

    public double getDistanceFromUser() {
        return distanceFromUser;
    }