package com.example.banner;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IBannerRepository.
 * Simulates persistence using an in-memory map and can simulate network errors.
 */
public class BannerRepository implements IBannerRepository {
    private Map<String, Banner> dataStore = new HashMap<>();
    private boolean simulateNetworkError = false; // Flag to simulate R17 NetworkException

    /**
     * Constructor for BannerRepository.
     * @param initialBanners Optional list of banners to pre-populate the repository.
     */
    public BannerRepository(List<Banner> initialBanners) {
        if (initialBanners != null) {
            initialBanners.forEach(banner -> dataStore.put(banner.getId(), banner));
        }
    }

    /**
     * Sets whether the repository should simulate network errors.
     * @param simulateNetworkError If true, methods will throw NetworkException.
     */
    public void setSimulateNetworkError(boolean simulateNetworkError) {
        this.simulateNetworkError = simulateNetworkError;
    }

    /**
     * Simulates a network operation delay and potential error.
     * @throws NetworkException if simulateNetworkError is true.
     */
    private void simulateNetworkDelayAndError() throws NetworkException {
        try {
            Thread.sleep(100); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (simulateNetworkError) {
            throw new NetworkException("Simulated network connection interrupted to Database.");
        }
    }

    @Override
    public Banner findById(String bannerId) throws NetworkException {
        simulateNetworkDelayAndError();
        // Comment: Simulating database query
        System.out.println("[Repository] Querying DB for banner with ID: " + bannerId);
        return dataStore.get(bannerId);
    }

    @Override
    public List<Banner> findAllByRestaurant(String restaurantId) throws NetworkException {
        simulateNetworkDelayAndError();
        // Comment: Simulating database query
        System.out.println("[Repository] Querying DB for banners by restaurant ID: " + restaurantId);
        return dataStore.values().stream()
                .filter(banner -> banner.getRestaurantId().equals(restaurantId))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Banner banner) throws NetworkException {
        simulateNetworkDelayAndError();
        // Comment: Simulating database persistence
        System.out.println("[Repository] Persisting banner: " + banner.getId());
        dataStore.put(banner.getId(), banner);
    }
}