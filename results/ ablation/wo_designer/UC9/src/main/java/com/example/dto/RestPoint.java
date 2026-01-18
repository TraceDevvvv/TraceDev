package com.example.dto;

/**
 * DTO representing a point of rest.
 */
public class RestPoint {
    private String id;
    private String name;
    private String location;
    private boolean hasShelter;
    private boolean hasWater;
    private int rating; // 1 to 5

    public RestPoint(String id, String name, String location, boolean hasShelter, boolean hasWater, int rating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.hasShelter = hasShelter;
        this.hasWater = hasWater;
        this.rating = rating;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isHasShelter() {
        return hasShelter;
    }

    public void setHasShelter(boolean hasShelter) {
        this.hasShelter = hasShelter;
    }

    public boolean isHasWater() {
        return hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RestPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", hasShelter=" + hasShelter +
                ", hasWater=" + hasWater +
                ", rating=" + rating +
                '}';
    }
}