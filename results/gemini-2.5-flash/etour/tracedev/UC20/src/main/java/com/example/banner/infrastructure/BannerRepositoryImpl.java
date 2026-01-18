package com.example.banner.infrastructure;

import com.example.banner.domain.Banner;
import com.example.banner.domain.BannerStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * In-memory implementation of {@link IBannerRepository}.\n * This class simulates database interactions using a {@link ConcurrentHashMap}.\n * It can also simulate {@link ETOURConnectionException} for testing purposes.
 */
public class BannerRepositoryImpl implements IBannerRepository {
    // In-memory storage for banners
    private final Map<String, Banner> banners = new ConcurrentHashMap<>();
    // Counter for simulating ETOUR connection errors
    private int etourErrorCounter = 0;
    private static final int ERROR_THRESHOLD = 5; // Simulate error every 5 operations for example

    /**
     * Simulates saving a banner to the database.
     *
     * @param banner The banner to save.
     * @return The saved banner.
     * @throws ETOURConnectionException if a simulated connection error occurs.
     */
    @Override
    public Banner save(Banner banner) throws ETOURConnectionException { // SD: m47, m48, m49
        // Simulate ETOUR connection error based on a counter
        etourErrorCounter++;
        if (etourErrorCounter % ERROR_THRESHOLD == 0) {
            System.out.println("[BannerRepositoryImpl] Simulating ETOUR connection error during save.");
            throw new ETOURConnectionException("ETOUR connection interrupted during banner save operation.");
        }

        System.out.println("[BannerRepositoryImpl] Simulating DB: Saving banner with ID: " + banner.getId());
        if (banner.getId() == null) {
            // This should not happen if default constructor generates ID
            System.err.println("[BannerRepositoryImpl] Warning: Banner has no ID, generating a new one.");
        }
        // Update last modified date
        banner.setLastModified(new Date());
        banners.put(banner.getId(), banner);
        System.out.println("[BannerRepositoryImpl] DB: Banner saved: " + banner);
        return banner;
    }

    /**
     * Finds a banner by its ID.
     *
     * @param id The ID of the banner.
     * @return The banner if found, otherwise null.
     */
    @Override
    public Banner findById(String id) {
        System.out.println("[BannerRepositoryImpl] Simulating DB: Finding banner by ID: " + id);
        return banners.get(id);
    }

    /**
     * Finds all banners associated with a specific point of rest.
     *
     * @param pointOfRestId The ID of the point of rest.
     * @return A list of banners.
     */
    @Override
    public List<Banner> findByPointOfRestId(String pointOfRestId) {
        System.out.println("[BannerRepositoryImpl] Simulating DB: Finding banners for PointOfRest ID: " + pointOfRestId);
        return banners.values().stream()
                .filter(banner -> banner.getPointOfRestId().equals(pointOfRestId))
                .collect(Collectors.toList());
    }

    /**
     * Initializes some dummy data for testing purposes.
     */
    public void initDummyData() {
        // Assume some PointOfRest IDs exist
        // These are dummy banners for testing
        Banner banner1 = new Banner("http://example.com/img1.jpg", "por1", BannerStatus.ACTIVE);
        Banner banner2 = new Banner("http://example.com/img2.jpg", "por1", BannerStatus.PENDING);
        Banner banner3 = new Banner("http://example.com/img3.jpg", "por2", BannerStatus.ACTIVE);

        try {
            save(banner1);
            save(banner2);
            save(banner3);
        } catch (ETOURConnectionException e) {
            System.err.println("[BannerRepositoryImpl] Error initializing dummy data: " + e.getMessage());
        }
    }
}