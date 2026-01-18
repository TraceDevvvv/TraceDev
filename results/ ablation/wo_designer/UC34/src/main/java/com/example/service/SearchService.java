package com.example.service;

import com.example.model.Site;
import com.example.model.SearchCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to process research and find sites matching criteria.
 * Simulates interaction with a server (e.g., ETOUR).
 */
public class SearchService {
    // In-memory dummy data for demonstration.
    private List<Site> allSites;

    public SearchService() {
        initializeDummySites();
    }

    private void initializeDummySites() {
        allSites = new ArrayList<>();
        allSites.add(new Site("1", "Colosseum", "Ancient amphitheater in Rome", 41.8902, 12.4922, "Historical"));
        allSites.add(new Site("2", "Louvre Museum", "World's largest art museum", 48.8606, 2.3376, "Museum"));
        allSites.add(new Site("3", "Milan Cathedral", "Gothic cathedral in Milan", 45.4641, 9.1900, "Religious"));
        allSites.add(new Site("4", "Pompeii", "Ancient Roman city near Naples", 40.7490, 14.4848, "Archaeological"));
        allSites.add(new Site("5", "Uffizi Gallery", "Art museum in Florence", 43.7687, 11.2553, "Museum"));
    }

    /**
     * Processes research based on criteria and user location.
     * Simulates server communication; handles interruption.
     * @param criteria The search criteria.
     * @param userLat User's latitude.
     * @param userLon User's longitude.
     * @return List of matching sites.
     * @throws RuntimeException if connection to server is interrupted (simulated).
     */
    public List<Site> searchSites(SearchCriteria criteria, double userLat, double userLon) {
        // Simulate potential server interruption (e.g., ETOUR server down)
        if (isConnectionInterrupted()) {
            throw new RuntimeException("Connection to server ETOUR is interrupted.");
        }

        return allSites.stream()
                .filter(site -> matchesCriteria(site, criteria, userLat, userLon))
                .collect(Collectors.toList());
    }

    private boolean matchesCriteria(Site site, SearchCriteria criteria, double userLat, double userLon) {
        // Keyword filter
        if (criteria.getKeyword() != null && !criteria.getKeyword().isEmpty()) {
            String keywordLower = criteria.getKeyword().toLowerCase();
            boolean keywordMatch = site.getName().toLowerCase().contains(keywordLower) ||
                                   site.getDescription().toLowerCase().contains(keywordLower);
            if (!keywordMatch) return false;
        }

        // Category filter
        if (criteria.getCategory() != null && !criteria.getCategory().isEmpty()) {
            if (!site.getCategory().equalsIgnoreCase(criteria.getCategory())) {
                return false;
            }
        }

        // Distance filter (optional)
        if (criteria.getMaxDistance() != null) {
            double distance = calculateDistance(userLat, userLon, site.getLatitude(), site.getLongitude());
            if (distance > criteria.getMaxDistance()) {
                return false;
            }
        }

        return true;
    }

    // Haversine formula to calculate distance between two coordinates in km.
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // Simulate random connection interruption for demonstration.
    private boolean isConnectionInterrupted() {
        return Math.random() < 0.1; // 10% chance of interruption
    }
}