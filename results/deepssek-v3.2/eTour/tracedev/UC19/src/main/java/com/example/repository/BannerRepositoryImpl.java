package com.example.repository;

import com.example.model.Banner;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BannerRepository using a DataSource.
 * Simplified for demonstration.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private DataSource dataSource;

    public BannerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Banner> findAllByRefreshmentPointId(long pointId) {
        // Simulate database query: return dummy banners for the given pointId
        List<Banner> banners = new ArrayList<>();
        banners.add(new Banner(1L, "Banner A", pointId, "http://example.com/banner1.jpg"));
        banners.add(new Banner(2L, "Banner B", pointId, "http://example.com/banner2.jpg"));
        return banners;
    }

    @Override
    public boolean deleteById(long bannerId) {
        // Simulate deletion: assume success for bannerId > 0
        return bannerId > 0;
    }
}