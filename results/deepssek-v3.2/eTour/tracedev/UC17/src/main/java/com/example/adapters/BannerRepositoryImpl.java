package com.example.adapters;

import com.example.domain.Banner;
import com.example.exceptions.ConnectionException;
import com.example.ports.BannerRepository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of BannerRepository.
 * Uses an in-memory map for simplicity (instead of DataSource).
 */
public class BannerRepositoryImpl implements BannerRepository {
    // Simulating DataSource with in-memory storage
    private Map<String, Banner> bannerStore = new HashMap<>();
    private Map<String, List<Banner>> restPointBanners = new HashMap<>();
    private DataSource dataSource;

    public BannerRepositoryImpl() {
        // Initialize with some dummy data for testing
        Banner banner1 = new Banner("banner1", "rest1", "url1", 800, 600);
        Banner banner2 = new Banner("banner2", "rest1", "url2", 1024, 768);
        bannerStore.put("banner1", banner1);
        bannerStore.put("banner2", banner2);
        
        List<Banner> rest1Banners = new ArrayList<>();
        rest1Banners.add(banner1);
        rest1Banners.add(banner2);
        restPointBanners.put("rest1", rest1Banners);
    }

    public BannerRepositoryImpl(DataSource dataSource) {
        this();
        this.dataSource = dataSource;
    }

    @Override
    public List<Banner> findByRestPointId(String restPointId) {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost while finding banners by rest point");
        }
        return restPointBanners.getOrDefault(restPointId, new ArrayList<>());
    }

    @Override
    public Banner findById(String bannerId) {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost while finding banner by ID");
        }
        return bannerStore.get(bannerId);
    }

    @Override
    public Banner save(Banner banner) {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost while saving banner");
        }
        bannerStore.put(banner.getBannerId(), banner);
        
        // Also update restPointBanners map
        List<Banner> banners = restPointBanners.getOrDefault(banner.getRestPointId(), new ArrayList<>());
        banners.removeIf(b -> b.getBannerId().equals(banner.getBannerId()));
        banners.add(banner);
        restPointBanners.put(banner.getRestPointId(), banners);
        
        return banner;
    }
}