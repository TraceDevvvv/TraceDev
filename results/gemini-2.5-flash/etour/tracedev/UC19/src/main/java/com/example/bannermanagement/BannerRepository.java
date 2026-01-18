package com.example.bannermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of IBannerRepository.
 * This class simulates data storage and retrieval for Banner entities.
 * It can also simulate ETOURConnectionException for testing purposes.
 */
public class BannerRepository implements IBannerRepository {
    private final Map<String, Banner> banners = new HashMap<>();
    // Static flag to simulate connection errors for demonstration purposes.
    public static boolean simulateConnectionError = false;

    /**
     * Constructor to initialize with some dummy data.
     */
    public BannerRepository() {
        // Add some dummy data
        banners.put("B001", new Banner("B001", "RP001", "http://example.com/ad1.jpg", true));
        banners.put("B002", new Banner("B002", "RP001", "http://example.com/ad2.gif", false));
        banners.put("B003", new Banner("B003", "RP002", "http://example.com/ad3.mp4", true));
        banners.put("B004", new Banner("B004", "RP003", "http://example.com/ad4.png", true));
        banners.put("B005", new Banner("B005", "RP003", "http://example.com/ad5.jpg", true));
    }

    /**
     * Retrieves all Banner entities associated with a specific refreshment point.
     * @param refreshmentPointId The ID of the refreshment point.
     * @return A list of Banner objects associated with the given refreshment point.
     * @throws ETOURConnectionException if simulateConnectionError is true.
     */
    @Override
    public List<Banner> findByRefreshmentPointId(String refreshmentPointId) throws ETOURConnectionException {
        if (simulateConnectionError) {
            throw new ETOURConnectionException("Simulated ETOUR connection error during findByRefreshmentPointId Banners.");
        }
        return banners.values().stream()
                .filter(banner -> banner.getRefreshmentPointId().equals(refreshmentPointId))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a Banner entity by its unique identifier.
     * This operation is irreversible.
     * @param bannerId The unique ID of the banner to delete.
     * @throws ETOURConnectionException if simulateConnectionError is true.
     */
    @Override
    public void delete(String bannerId) throws ETOURConnectionException {
        if (simulateConnectionError) {
            throw new ETOURConnectionException("Simulated ETOUR connection error during delete Banner.");
        }
        System.out.println("DEBUG: Attempting to delete banner with ID: " + bannerId);
        if (banners.remove(bannerId) != null) {
            System.out.println("DEBUG: Banner " + bannerId + " successfully removed from in-memory store.");
        } else {
            System.out.println("DEBUG: Banner " + bannerId + " not found for deletion.");
        }
    }
}