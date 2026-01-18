package com.example;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of BannerRepository for demonstration.
 */
public class BannerRepositoryImpl implements BannerRepository {
    // Using in-memory storage for simplicity
    private Map<String, Banner> bannerStore = new HashMap<>();
    private Map<String, RefreshmentPoint> refreshmentPointStore = new HashMap<>();
    
    // Assume dataSource is initialized elsewhere
    private Object dataSource; // Placeholder for actual DataSource

    public BannerRepositoryImpl() {
        // Initialize with sample data
        RefreshmentPoint rp1 = new RefreshmentPoint("rp1", "Point A");
        RefreshmentPoint rp2 = new RefreshmentPoint("rp2", "Point B");
        
        refreshmentPointStore.put("rp1", rp1);
        refreshmentPointStore.put("rp2", rp2);
        
        Banner b1 = new Banner("b1", "http://example.com/banner1.jpg", "rp1");
        Banner b2 = new Banner("b2", "http://example.com/banner2.jpg", "rp1");
        Banner b3 = new Banner("b3", "http://example.com/banner3.jpg", "rp2");
        
        bannerStore.put("b1", b1);
        bannerStore.put("b2", b2);
        bannerStore.put("b3", b3);
        
        rp1.addBanner(b1);
        rp1.addBanner(b2);
        rp2.addBanner(b3);
    }

    @Override
    public Banner findById(String id) {
        return bannerStore.get(id);
    }

    @Override
    public Banner save(Banner banner) {
        bannerStore.put(banner.getId(), banner);
        return banner;
    }

    @Override
    public List<Banner> findByRefreshmentPointId(String pointId) {
        List<Banner> result = new ArrayList<>();
        for (Banner banner : bannerStore.values()) {
            if (pointId.equals(banner.getRefreshmentPointId())) {
                result.add(banner);
            }
        }
        return result;
    }

    @Override
    public List<RefreshmentPoint> findAllRefreshmentPoints() {
        return new ArrayList<>(refreshmentPointStore.values());
    }
}