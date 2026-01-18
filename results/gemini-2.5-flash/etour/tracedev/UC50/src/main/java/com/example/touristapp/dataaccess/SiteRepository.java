package com.example.touristapp.dataaccess;

import com.example.touristapp.domain.Site;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Concrete implementation of {@link ISiteRepository}.
 * This class simulates data access to a database for {@link Site} entities.
 * It includes a mechanism to simulate {@link NetworkConnectionException} for testing error handling.
 */
public class SiteRepository implements ISiteRepository {

    // --- Mock Database Simulation ---
    // In a real application, this would interact with a database (e.g., via JDBC, JPA, etc.)
    private static final List<Site> MOCK_SITES = Arrays.asList(
            new Site("S001", "Eiffel Tower", "Paris"),
            new Site("S002", "Louvre Museum", "Paris"),
            new Site("S003", "Colosseum", "Rome"),
            new Site("S004", "Trevi Fountain", "Rome"),
            new Site("S005", "Sagrada Familia", "Barcelona")
    );

    // This map simulates which sites a given tourist has visited.
    // Key: touristId, Value: List of site IDs visited by that tourist.
    private static final java.util.Map<String, List<String>> MOCK_TOURIST_VISITED_SITES = new java.util.HashMap<>();
    static {
        MOCK_TOURIST_VISITED_SITES.put("T001", Arrays.asList("S001", "S002", "S003"));
        MOCK_TOURIST_VISITED_SITES.put("T002", Arrays.asList("S004", "S005"));
        MOCK_TOURIST_VISITED_SITES.put("T003", Arrays.asList("S001", "S005"));
        // No sites for T004 to test empty list
    }

    // --- Network Error Simulation ---
    private boolean simulateNetworkError = false;
    private Random random = new Random();

    /**
     * Sets whether to simulate a network error.
     * @param simulateNetworkError If true, find methods will randomly throw NetworkConnectionException.
     */
    public void setSimulateNetworkError(boolean simulateNetworkError) {
        this.simulateNetworkError = simulateNetworkError;
    }

    /**
     * Finds all sites visited by a specific tourist.
     * This implementation simulates database interaction and can throw {@link NetworkConnectionException}.
     * Satisfies REQ-007: Exit Conditions: Connection to the server ETOUR IS interrupted.
     *
     * @param touristId The unique identifier of the tourist.
     * @return A list of {@link Site} objects visited by the tourist.
     * @throws NetworkConnectionException If a simulated network connection issue occurs.
     */
    @Override
    public List<Site> findVisitedSitesByTourist(String touristId) throws NetworkConnectionException {
        // Simulate network latency or interruption
        try {
            Thread.sleep(100); // Simulate some processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate network error with a 30% chance if enabled
        if (simulateNetworkError && random.nextInt(10) < 3) {
            System.out.println("DEBUG: SiteRepository simulating NetworkConnectionException for tourist " + touristId);
            throw new NetworkConnectionException("Failed to connect to site database while finding sites for " + touristId);
        }

        // Simulate database query("SELECT * FROM Sites WHERE touristId = :touristId")
        System.out.println("DEBUG: SiteRepository querying database for sites visited by tourist: " + touristId);
        List<String> visitedSiteIds = MOCK_TOURIST_VISITED_SITES.getOrDefault(touristId, new ArrayList<>());

        List<Site> visitedSites = MOCK_SITES.stream()
                .filter(site -> visitedSiteIds.contains(site.getId()))
                .collect(Collectors.toList());

        System.out.println("DEBUG: SiteRepository found " + visitedSites.size() + " sites for tourist " + touristId);
        return visitedSites;
    }
}