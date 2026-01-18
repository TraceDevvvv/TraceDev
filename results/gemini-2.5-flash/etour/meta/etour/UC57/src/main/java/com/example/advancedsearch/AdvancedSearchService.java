package com.example.advancedsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Handles the advanced search logic, interacts with a simulated ETOUR server, and applies search criteria.
 * This service simulates fetching data from a backend system and applying various filters.
 */
public class AdvancedSearchService {

    // Simulate a database or external service with a predefined list of sites
    private List<Site> allAvailableSites;
    private static final long TRANSACTION_TIMEOUT_SECONDS = 15; // Quality requirement: transaction in more than 15 seconds

    /**
     * Constructs an AdvancedSearchService and initializes a set of dummy sites.
     */
    public AdvancedSearchService() {
        initializeDummySites();
    }

    /**
     * Initializes a list of dummy tourist sites for simulation purposes.
     */
    private void initializeDummySites() {
        allAvailableSites = new ArrayList<>();
        allAvailableSites.add(new Site("S001", "Eiffel Tower", "Iconic wrought-iron lattice tower on the Champ de Mars in Paris.",
                new Location(48.8584, 2.2945), "Landmark", 4.8, List.of("restaurant", "gift shop"), true));
        allAvailableSites.add(new Site("S002", "Louvre Museum", "World's largest art museum and a historic monument in Paris.",
                new Location(48.8606, 2.3376), "Museum", 4.7, List.of("cafe", "wifi", "restrooms"), true));
        allAvailableSites.add(new Site("S003", "Notre Dame Cathedral", "Medieval Catholic cathedral on the Île de la Cité in Paris.",
                new Location(48.8529, 2.3499), "Religious Site", 4.6, List.of("gift shop"), false));
        allAvailableSites.add(new Site("S004", "Central Park", "An urban park in New York City, New York.",
                new Location(40.785091, -73.968285), "Park", 4.9, List.of("restrooms", "cafe"), true));
        allAvailableSites.add(new Site("S005", "Statue of Liberty", "A colossal neoclassical sculpture on Liberty Island in New York Harbor.",
                new Location(40.689247, -74.044502), "Landmark", 4.7, List.of("gift shop", "ferry access"), false));
        allAvailableSites.add(new Site("S006", "British Museum", "A public institution dedicated to human history, art and culture in London.",
                new Location(51.5194, -0.1269), "Museum", 4.7, List.of("cafe", "wifi", "restrooms"), true));
        allAvailableSites.add(new Site("S007", "Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy.",
                new Location(41.8902, 12.4922), "Historic Site", 4.5, List.of("guided tours"), false));
        allAvailableSites.add(new Site("S008", "Tokyo Skytree", "A broadcasting and observation tower in Sumida, Tokyo.",
                new Location(35.7100, 139.8107), "Landmark", 4.6, List.of("restaurant", "gift shop", "observation deck"), true));
        allAvailableSites.add(new Site("S009", "Mount Fuji", "Japan's highest mountain, an active stratovolcano.",
                new Location(35.3606, 138.7278), "Natural Site", 4.9, List.of("hiking trails", "restrooms"), false));
        allAvailableSites.add(new Site("S010", "Sydney Opera House", "A multi-venue performing arts centre in Sydney, Australia.",
                new Location(-33.8568, 151.2153), "Landmark", 4.8, List.of("restaurant", "bar", "guided tours"), true));
    }

    /**
     * Performs an advanced search based on the provided form criteria and tourist's current location.
     * This method simulates a connection to the ETOUR server and applies filtering logic.
     *
     * @param form The AdvancedSearchForm containing the search criteria.
     * @param touristLocation The current geographical location of the tourist.
     * @return A list of Site objects that match the search criteria.
     * @throws ETOURConnectionException If there's an interruption in connection to the ETOUR server.
     * @throws SearchTimeoutException If the search transaction exceeds the allowed time.
     */
    public List<Site> performAdvancedSearch(AdvancedSearchForm form, Location touristLocation)
            throws ETOURConnectionException, SearchTimeoutException {

        long startTime = System.nanoTime(); // Start timing the transaction

        // Simulate connection to ETOUR server and potential interruption
        simulateETOURConnection();

        // Apply filters based on the form
        List<Site> filteredSites = allAvailableSites.stream()
                .filter(site -> matchesKeyword(site, form.getKeyword()))
                .filter(site -> matchesCategory(site, form.getCategory()))
                .filter(site -> matchesMinRating(site, form.getMinRating()))
                .filter(site -> matchesAmenities(site, form.getAmenities()))
                .filter(site -> matchesAccessibility(site, form.hasAccessibility()))
                .filter(site -> matchesMaxDistance(site, touristLocation, form.getMaxDistanceKm()))
                .collect(Collectors.toList());

        long endTime = System.nanoTime(); // End timing
        long duration = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);

        // Quality requirement: The system requirements into the transaction in more than 15 seconds.
        // This means if it takes MORE than 15 seconds, it's a failure.
        if (duration > TRANSACTION_TIMEOUT_SECONDS) {
            throw new SearchTimeoutException("Search transaction exceeded " + TRANSACTION_TIMEOUT_SECONDS + " seconds.");
        }

        return filteredSites;
    }

    /**
     * Simulates a connection to the ETOUR server.
     * Randomly throws an ETOURConnectionException to simulate an interruption.
     *
     * @throws ETOURConnectionException If the simulated connection fails.
     */
    private void simulateETOURConnection() throws ETOURConnectionException {
        // Simulate a 10% chance of connection interruption
        if (Math.random() < 0.1) {
            throw new ETOURConnectionException("Connection to ETOUR server interrupted.");
        }
        // Simulate some network latency
        try {
            Thread.sleep(50 + (long)(Math.random() * 200)); // 50-250 ms latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ETOURConnectionException("Simulated connection interrupted during latency.", e);
        }
    }

    /**
     * Checks if a site's name or description contains the keyword (case-insensitive).
     */
    private boolean matchesKeyword(Site site, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return true; // No keyword specified, so it matches
        }
        String lowerCaseKeyword = keyword.toLowerCase();
        return site.getName().toLowerCase().contains(lowerCaseKeyword) ||
               site.getDescription().toLowerCase().contains(lowerCaseKeyword);
    }

    /**
     * Checks if a site matches the specified category (case-insensitive).
     */
    private boolean matchesCategory(Site site, String category) {
        if (category == null || category.trim().isEmpty()) {
            return true; // No category specified, so it matches
        }
        return site.getCategory().equalsIgnoreCase(category);
    }

    /**
     * Checks if a site's average rating meets the minimum required rating.
     */
    private boolean matchesMinRating(Site site, double minRating) {
        return site.getAverageRating() >= minRating;
    }

    /**
     * Checks if a site has all the required amenities.
     */
    private boolean matchesAmenities(Site site, List<String> requiredAmenities) {
        if (requiredAmenities == null || requiredAmenities.isEmpty()) {
            return true; // No specific amenities required
        }
        // Check if the site's amenities contain all required amenities (case-insensitive)
        List<String> siteAmenitiesLower = site.getAmenities().stream()
                                            .map(String::toLowerCase)
                                            .collect(Collectors.toList());
        return requiredAmenities.stream()
                                .map(String::toLowerCase)
                                .allMatch(siteAmenitiesLower::contains);
    }

    /**
     * Checks if a site matches the accessibility requirement.
     */
    private boolean matchesAccessibility(Site site, boolean requiresAccessibility) {
        if (!requiresAccessibility) {
            return true; // Accessibility is not required, so it matches
        }
        return site.hasAccessibility(); // Accessibility is required, so check if site has it
    }

    /**
     * Checks if a site is within the maximum allowed distance from the tourist's location.
     */
    private boolean matchesMaxDistance(Site site, Location touristLocation, double maxDistanceKm) {
        if (touristLocation == null || maxDistanceKm == Double.MAX_VALUE) {
            return true; // No tourist location or no distance limit, so it matches
        }
        // Calculate distance in a simplified way (for real apps, use Haversine)
        // The Location.calculateDistance method returns a simplified Euclidean distance.
        // For a more accurate representation of kilometers, a conversion factor would be needed
        // or a proper Haversine implementation. For this simulation, we assume the unit
        // returned by calculateDistance is roughly proportional to kilometers.
        double distance = touristLocation.calculateDistance(site.getLocation());
        // Assuming 1 degree of latitude/longitude is roughly 111 km for a rough estimate
        // This is a very rough approximation for demonstration.
        double distanceInKm = distance * 111; // Very rough conversion
        return distanceInKm <= maxDistanceKm;
    }

    /**
     * Custom exception for ETOUR server connection interruptions.
     */
    public static class ETOURConnectionException extends Exception {
        public ETOURConnectionException(String message) {
            super(message);
        }
        public ETOURConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Custom exception for search transactions exceeding the allowed time.
     */
    public static class SearchTimeoutException extends Exception {
        public SearchTimeoutException(String message) {
            super(message);
        }
    }
}