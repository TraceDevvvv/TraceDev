package com.tourism.domain;

/**
 * Domain entity representing a Tourist.
 */
public class Tourist {
    private String touristId;
    private String name;

    public Tourist() {
    }

    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}