package com.etour.advancedsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for processing advanced search requests.
 * Simulates interaction with a backend/database.
 */
public class AdvancedSearchService {
    private static final double EARTH_RADIUS_KM = 6371.0;

    // Simulated database of sites
    private List<Site> siteDatabase;

    public AdvancedSearchService() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        siteDatabase = new ArrayList<>();
        siteDatabase.add(new Site("1", "Eiffel Tower", "Iconic tower in Paris", "Landmark",
                new Location(48.8584, 2.2945), 4.8));
        siteDatabase.add(new Site("2", "Louvre Museum", "World's largest art museum", "Museum",
                new Location(48.8606, 2.3376), 4.7));
        siteDatabase.add(new Site("3", "Notre-Dame Cathedral", "Medieval Catholic cathedral", "Religious",
                new Location(48.8530, 2.3499), 4.6));
        siteDatabase.add(new Site("4", "Central Park", "Urban park in Manhattan", "Park",
                new Location(40.7851, -73.9683), 4.5));
        siteDatabase.add(new Site("5", "Statue of Liberty", "Colossal neoclassical sculpture", "Monument",
                new Location(40.6892, -74.0445), 4.9));
    }

    /**
     * Performs advanced search based on form criteria and tourist's location.
     * Includes a timeout simulation to meet quality requirement (<15 seconds).
     *
     * @param form the advanced search form
     * @param touristLocation the current location of the tourist
     * @return list of matching sites
     * @throws InterruptedException if search is interrupted
     */
    public List<Site> performAdvancedSearch(AdvancedSearchForm form, Location touristLocation) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // Simulate network/processing delay (max 3 seconds for demonstration)
        Thread.sleep(1000);

        // Filter sites based on criteria
        List<Site> results = siteDatabase.stream()
                .filter(site -> matchesName(site, form.getNameKeyword()))
                .filter(site -> matchesCategory(site, form.getCategory()))
                .filter(site -> matchesRating(site, form.getMinRating()))
                .filter(site -> matchesDistance(site, touristLocation, form.getMaxDistance()))
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Quality requirement: process in less than 15 seconds
        if (duration > 15000) {
            System.err.println("Warning: Search took " + duration + " ms, exceeding 15-second requirement.");
        } else {
            System.out.println("Search completed in " + duration + " ms.");
        }

        return results;
    }

    private boolean matchesName(Site site, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return true;
        }
        return site.getName().toLowerCase().contains(keyword.toLowerCase());
    }

    private boolean matchesCategory(Site site, String category) {
        if (category == null || category.trim().isEmpty()) {
            return true;
        }
        return site.getCategory().equalsIgnoreCase(category);
    }

    private boolean matchesRating(Site site, Double minRating) {
        if (minRating == null) {
            return true;
        }
        return site.getRating() >= minRating;
    }

    private boolean matchesDistance(Site site, Location touristLocation, Integer maxDistanceKm) {
        if (touristLocation == null || maxDistanceKm == null) {
            return true; // No distance constraint
        }
        double distance = calculateDistance(touristLocation, site.getLocation());
        return distance <= maxDistanceKm;
    }

    /**
     * Calculate distance between two locations using Haversine formula.
     *
     * @param loc1 first location
     * @param loc2 second location
     * @return distance in kilometers
     */
    private double calculateDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.getLatitude());
        double lon1 = Math.toRadians(loc1.getLongitude());
        double lat2 = Math.toRadians(loc2.getLatitude());
        double lon2 = Math.toRadians(loc2.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}