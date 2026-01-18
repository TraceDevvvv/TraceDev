package com.example.uml;

import java.util.List;

/**
 * Represents a refreshment point that can contain multiple banners.
 */
public class RefreshmentPoint {
    private String refreshmentPointId;
    private String location;
    private String name;

    public RefreshmentPoint(String refreshmentPointId, String location, String name) {
        this.refreshmentPointId = refreshmentPointId;
        this.location = location;
        this.name = name;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Banner> getAssociatedBanners() {
        // Simulate fetching associated banners; in a real system, this would query a repository.
        System.out.println("Fetching banners for refreshment point: " + refreshmentPointId);
        return List.of();
    }
}