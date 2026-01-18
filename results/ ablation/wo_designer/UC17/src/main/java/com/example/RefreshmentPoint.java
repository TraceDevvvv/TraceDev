package com.example;

import java.util.List;

public class RefreshmentPoint {
    private String id;
    private String name;
    private List<Banner> banners;

    public RefreshmentPoint(String id, String name, List<Banner> banners) {
        this.id = id;
        this.name = name;
        this.banners = banners;
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
}