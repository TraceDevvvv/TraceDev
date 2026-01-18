/**
 * Mock Data Service
 * Simulates data retrieval from a database or external service
 * Handles connection interruption scenarios
 */
import java.util.*;
public class MockDataService {
    /**
     * Simulates retrieving all locations from database
     */
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        // Mock location data
        locations.add(new Location("LOC001", "Central Park", "New York, NY", "Park", 5000));
        locations.add(new Location("LOC002", "Metropolitan Museum", "New York, NY", "Museum", 2000));
        locations.add(new Location("LOC003", "Times Square", "New York, NY", "Public Square", 10000));
        locations.add(new Location("LOC004", "Statue of Liberty", "Liberty Island, NY", "Monument", 3000));
        locations.add(new Location("LOC005", "Brooklyn Bridge", "Brooklyn, NY", "Bridge", 1500));
        locations.add(new Location("LOC006", "Natural History Museum", "New York, NY", "Museum", 2500));
        locations.add(new Location("LOC007", "Empire State Building", "New York, NY", "Observation Deck", 1200));
        return locations;
    }
    /**
     * Simulates retrieving site feedback data for a specific location
     * This mimics the database layer for the SearchSite use case
     * @throws Exception If connection is interrupted or other errors occur
     */
    public List<SiteFeedback> getSiteFeedbackForLocation(String locationName) throws Exception {
        // Simulate possible connection interruption (20% chance)
        if (Math.random() < 0.2) {
            throw new Exception("Connection to server interrupted while retrieving feedback data");
        }
        List<SiteFeedback> feedbackList = new ArrayList<>();
        // Generate mock feedback data based on location
        String locationId = getLocationIdByName(locationName);
        if (locationId == null) {
            // Return empty list for unknown location
            return feedbackList;
        }
        // Generate different amounts of data for different locations
        int dataPoints = 10 + (int)(Math.random() * 15);
        // Ensure we always have at least some data
        if (dataPoints < 5) {
            dataPoints = 5;
        }
        for (int i = 0; i < dataPoints; i++) {
            // Generate dates in the past 30 days
            String date = generateRandomDate();
            // Generate rating (1-5 with .5 increments)
            double rating = 1.0 + (Math.random() * 4);
            rating = Math.round(rating * 2) / 2.0; // Round to nearest 0.5
            // Ensure rating is within valid range
            if (rating < 1.0) rating = 1.0;
            if (rating > 5.0) rating = 5.0;
            // Generate feedback comments
            String[] commentTemplates = {
                "Great experience, would visit again",
                "Average visit, nothing special",
                "Excellent facilities and serv",
                "Could improve signage",
                "Very crowded during peak hours",
                "Well maintained and clean",
                "Informative and educational",
                "Good for family visits",
                "Need more restroom facilities",
                "Beautiful scenery and views"
            };
            String comments = commentTemplates[(int)(Math.random() * commentTemplates.length)];
            // Generate visitor count (location-dependent)
            int baseVisitors = getBaseVisitorCount(locationName);
            int visitorCount = baseVisitors + (int)(Math.random() * 200);
            // Ensure visitor count is not negative
            if (visitorCount < 0) visitorCount = 0;
            try {
                feedbackList.add(new SiteFeedback(locationId, date, rating, comments, visitorCount));
            } catch (IllegalArgumentException e) {
                // If validation fails, use default values
                feedbackList.add(new SiteFeedback(locationId, "2023-01-01", 3.0, "Default feedback", 100));
            }
        }
        // Sort by date (newest first)
        feedbackList.sort((f1, f2) -> {
            if (f1.getDate() == null && f2.getDate() == null) return 0;
            if (f1.getDate() == null) return 1;
            if (f2.getDate() == null) return -1;
            return f2.getDate().compareTo(f1.getDate());
        });
        return feedbackList;
    }
    /**
     * Helper method to generate random dates
     */
    private String generateRandomDate() {
        // Generate a random date in the past 30 days
        int daysAgo = (int)(Math.random() * 30);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -daysAgo);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // Month is 0-based
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return String.format("%04d-%02d-%02d", year, month, day);
    }
    /**
     * Helper method to get location ID by name
     */
    private String getLocationIdByName(String locationName) {
        Map<String, String> locationMap = new HashMap<>();
        locationMap.put("Central Park", "LOC001");
        locationMap.put("Metropolitan Museum", "LOC002");
        locationMap.put("Times Square", "LOC003");
        locationMap.put("Statue of Liberty", "LOC004");
        locationMap.put("Brooklyn Bridge", "LOC005");
        locationMap.put("Natural History Museum", "LOC006");
        locationMap.put("Empire State Building", "LOC007");
        return locationMap.get(locationName);
    }
    /**
     * Helper method to get base visitor count for location
     */
    private int getBaseVisitorCount(String locationName) {
        Map<String, Integer> visitorMap = new HashMap<>();
        visitorMap.put("Central Park", 300);
        visitorMap.put("Metropolitan Museum", 150);
        visitorMap.put("Times Square", 800);
        visitorMap.put("Statue of Liberty", 200);
        visitorMap.put("Brooklyn Bridge", 100);
        visitorMap.put("Natural History Museum", 180);
        visitorMap.put("Empire State Building", 120);
        return visitorMap.getOrDefault(locationName, 100);
    }
}