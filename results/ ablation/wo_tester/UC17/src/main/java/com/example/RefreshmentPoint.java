package com.example;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a refreshment point containing multiple banners.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private List<Banner> banners;

    public RefreshmentPoint(String id, String name) {
        this.id = id;
        this.name = name;
        this.banners = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void addBanner(Banner banner) {
        banners.add(banner);
        banner.setRefreshmentPoint(this); // Set bidirectional relationship
    }
}