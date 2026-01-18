package com.system.repository;

import com.system.Banner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * In-memory implementation of BannerRepository for demonstration.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private List<Banner> banners = new ArrayList<>();

    public BannerRepositoryImpl() {}

    @Override
    public Banner save(Banner banner) {
        // Simulate save operation; in real scenario, persist to database.
        banners.add(banner);
        return banner;
    }

    @Override
    public List<Banner> findByPoint(String pointId) {
        return banners.stream()
                .filter(b -> b.getPoint() != null && pointId.equals(b.getPoint().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public int countByPoint(String pointId) {
        return (int) banners.stream()
                .filter(b -> b.getPoint() != null && pointId.equals(b.getPoint().getId()))
                .count();
    }

    @Override
    public boolean existsById(String id) {
        return banners.stream().anyMatch(b -> id.equals(b.getId()));
    }
}