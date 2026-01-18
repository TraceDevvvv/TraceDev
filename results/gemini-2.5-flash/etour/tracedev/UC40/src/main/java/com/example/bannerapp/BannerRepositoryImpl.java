package com.example.bannerapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IBannerRepository.
 * This class simulates database interactions for banners.
 */
public class BannerRepositoryImpl implements IBannerRepository {
    // In-memory data store for simulation
    private Map<String, Banner> bannerStore = new HashMap<>();
    private boolean simulateNetworkError = false; // Flag to trigger NetworkConnectionException

    /**
     * Constructor to initialize with some dummy data.
     */
    public BannerRepositoryImpl() {
        bannerStore.put("B001", new Banner("B001", "Summer Sale", "url/summer.jpg", "POR001"));
        bannerStore.put("B002", new Banner("B002", "Winter Deals", "url/winter.jpg", "POR001"));
        bannerStore.put("B003", new Banner("B003", "Spring Collection", "url/spring.jpg", "POR002"));
        bannerStore.put("B004", new Banner("B004", "Autumn Festival", "url/autumn.jpg", "POR001"));
    }

    /**
     * Sets a flag to simulate a NetworkConnectionException on subsequent repository calls.
     * @param simulate true to simulate an error, false otherwise.
     */
    public void setSimulateNetworkError(boolean simulate) {
        this.simulateNetworkError = simulate;
    }

    /**
     * Simulates a network operation that might throw an exception.
     * @throws NetworkConnectionException if simulation flag is true.
     */
    private void simulateNetworkOperation() throws NetworkConnectionException {
        if (simulateNetworkError) {
            throw new NetworkConnectionException("Simulated network connection interruption to ETOUR server.");
        }
        // In a real scenario, this would involve actual database/network calls.
        try {
            Thread.sleep(50); // Simulate some latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public List<Banner> findBannersByPointOfRest(String pointOfRestId) throws NetworkConnectionException {
        simulateNetworkOperation();
        System.out.println("[Repository] Finding banners for Point Of Rest: " + pointOfRestId);
        return bannerStore.values().stream()
                .filter(banner -> banner.getPointOfRestId().equals(pointOfRestId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(String bannerId) throws NetworkConnectionException {
        simulateNetworkOperation();
        System.out.println("[Repository] Attempting to delete banner with ID: " + bannerId);
        if (bannerStore.containsKey(bannerId)) {
            bannerStore.remove(bannerId);
            System.out.println("[Repository] Banner " + bannerId + " deleted successfully.");
            return true;
        }
        System.out.println("[Repository] Banner " + bannerId + " not found for deletion.");
        return false;
    }

    @Override
    public Banner findById(String bannerId) throws NetworkConnectionException {
        simulateNetworkOperation();
        System.out.println("[Repository] Finding banner with ID: " + bannerId);
        return bannerStore.get(bannerId);
    }
}