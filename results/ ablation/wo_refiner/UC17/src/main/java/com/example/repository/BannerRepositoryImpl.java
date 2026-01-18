package com.example.repository;

import com.example.banner.Banner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple in‑memory implementation of BannerRepository for demonstration.
 * In a real application, this would connect to a database.
 */
public class BannerRepositoryImpl implements BannerRepository {
    
    // Simulating a database with in‑memory maps
    private Map<Integer, Banner> bannerMap = new HashMap<>();
    private Map<Integer, List<Banner>> pointToBannersMap = new HashMap<>();
    
    @Override
    public Banner findBannerById(int bannerId) {
        // Simulate database retrieval
        return bannerMap.get(bannerId);
    }
    
    @Override
    public List<Banner> findBannersByRefreshmentPoint(int pointId) {
        // Return list of banners for the point, or empty list if none
        return pointToBannersMap.getOrDefault(pointId, new ArrayList<>());
    }
    
    @Override
    public void save(Banner banner) {
        // Save or update the banner
        bannerMap.put(banner.getId(), banner);
        int pointId = banner.getRefreshmentPointId();
        pointToBannersMap.computeIfAbsent(pointId, k -> new ArrayList<>());
        List<Banner> list = pointToBannersMap.get(pointId);
        // Remove old version if exists
        list.removeIf(b -> b.getId() == banner.getId());
        list.add(banner);
        System.out.println("Banner " + banner.getId() + " saved.");
    }
    
    // Helper method to simulate database initialization
    public void addSampleBanner(Banner banner) {
        save(banner);
    }
    
    /**
     * Query banners by point.
     * @param pointId the refreshment point ID
     * @return list of banners
     */
    public List<Banner> queryBannersByPoint(int pointId) {
        return findBannersByRefreshmentPoint(pointId);
    }
    
    /**
     * Retrieve banner data.
     * @param bannerId the banner ID
     * @return the Banner object
     */
    public Banner retrieveBannerData(int bannerId) {
        return findBannerById(bannerId);
    }
    
    /**
     * Update banner image.
     * @param bannerId the banner ID
     * @param imageData the new image data
     */
    public void updateBannerImage(int bannerId, byte[] imageData) {
        Banner banner = findBannerById(bannerId);
        if (banner != null) {
            banner.setImageData(imageData);
            save(banner);
        }
    }
}