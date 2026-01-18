package com.example.model;

import java.util.List;

/**
 * Represents a refreshment point with its associated banners.
 */
public class RefreshmentPoint {
    private String pointId;
    private String location;
    private String description;
    private List<Banner> banners;

    public RefreshmentPoint() {
    }

    public RefreshmentPoint(String pointId, String location, String description, List<Banner> banners) {
        this.pointId = pointId;
        this.location = location;
        this.description = description;
        this.banners = banners;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }
}