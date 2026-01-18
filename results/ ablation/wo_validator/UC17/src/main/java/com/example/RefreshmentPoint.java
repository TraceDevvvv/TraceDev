package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a refreshment point that contains multiple banners.
 * Provides methods to get id, name, location, and associated banners.
 */
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    private List<Banner> banners;

    public RefreshmentPoint(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.banners = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }
}