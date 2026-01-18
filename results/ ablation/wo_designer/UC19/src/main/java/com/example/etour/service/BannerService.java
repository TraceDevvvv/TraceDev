package com.example.etour.service;

import com.example.etour.model.Banner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling banner operations.
 * In a real application, this would interact with a database.
 */
public class BannerService {
    // Simulated in‑memory storage
    private List<Banner> banners = new ArrayList<>();
    private int nextId = 1;

    public BannerService() {
        // Add some sample banners for demonstration
        banners.add(new Banner(nextId++, "Summer Sale", "summer.jpg", 1));
        banners.add(new Banner(nextId++, "Winter Discount", "winter.jpg", 1));
        banners.add(new Banner(nextId++, "Grand Opening", "opening.jpg", 2));
    }

    /**
     * Get all banners for a given refreshment point.
     */
    public List<Banner> getBannersByRefreshmentPoint(int refreshmentPointId) {
        return banners.stream()
                .filter(b -> b.getRefreshmentPointId() == refreshmentPointId)
                .collect(Collectors.toList());
    }

    /**
     * Delete a banner by its ID.
     * Returns true if deletion was successful, false otherwise.
     */
    public boolean deleteBanner(int bannerId) {
        return banners.removeIf(b -> b.getId() == bannerId);
    }

    /**
     * Add a banner for testing.
     */
    public void addBanner(Banner banner) {
        banner.setId(nextId++);
        banners.add(banner);
    }
}