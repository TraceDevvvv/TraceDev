package com.banner.repository;

import com.banner.model.Banner;
import javax.sql.DataSource;
import java.util.*;

/**
 * In-memory implementation of BannerRepository for simplicity.
 * In a real application would use DataSource to connect to a database.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private DataSource dataSource;
    private Map<String, Banner> storage = new HashMap<>();
    private Map<String, List<String>> restBanners = new HashMap<>();

    public BannerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Banner save(Banner banner) {
        storage.put(banner.getId(), banner);
        restBanners.computeIfAbsent(banner.getPointOfRestId(), k -> new ArrayList<>()).add(banner.getId());
        return banner;
    }

    @Override
    public int countByPointOfRestId(String pointOfRestId) {
        return restBanners.getOrDefault(pointOfRestId, Collections.emptyList()).size();
    }

    @Override
    public Optional<Banner> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Banner> findAllByPointOfRestId(String pointOfRestId) {
        List<Banner> result = new ArrayList<>();
        List<String> ids = restBanners.getOrDefault(pointOfRestId, Collections.emptyList());
        for (String id : ids) {
            Banner b = storage.get(id);
            if (b != null) result.add(b);
        }
        return result;
    }

    // Method to simulate sequence diagram note m33
    public void persistWithNotification(Banner banner) {
        // Persist the banner
        save(banner);
        // Note: Banner is persisted - Notification can be sent
        System.out.println("Banner persisted. Notification can be sent.");
    }
}