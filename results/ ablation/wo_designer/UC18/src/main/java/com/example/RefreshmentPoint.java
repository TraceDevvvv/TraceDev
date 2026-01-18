package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a refreshment point at a convention.
 * Contains data about the convention and current banners.
 */
public class RefreshmentPoint {
    private String conventionName;
    private List<Banner> banners;
    private Random random;

    public RefreshmentPoint(String conventionName) {
        this.conventionName = conventionName;
        this.banners = new ArrayList<>();
        this.random = new Random();
        
        // Simulate existing banners (0 to 5 random banners)
        int existingBanners = random.nextInt(6); // 0 to 5
        for (int i = 0; i < existingBanners; i++) {
            banners.add(new Banner("Agency_" + (i + 1), "Banner_" + (i + 1)));
        }
    }

    public String getConventionName() {
        return conventionName;
    }

    public List<Banner> getBanners() {
        return banners;
    }
    
    public int getCurrentBannerCount() {
        return banners.size();
    }
    
    /**
     * Adds a new banner if possible.
     * @param banner The banner to add
     * @return true if added successfully, false otherwise
     */
    public boolean addBanner(Banner banner) {
        return banners.add(banner);
    }
    
    /**
     * Removes the last banner to recover previous state.
     */
    public void removeLastBanner() {
        if (!banners.isEmpty()) {
            banners.remove(banners.size() - 1);
        }
    }
}