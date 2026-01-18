package com.example.search.infrastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Simulates an external client for the ETOUR system.
 * It provides methods to check connection status and search for data.
 * Requirement R7: Added ETOURClient as an explicit participant in the sequence diagram.
 */
public class ETOURClient {
    // Simulate connection status, can be toggled for testing failure scenarios.
    private boolean connectionActive = true;
    private final Random random = new Random();

    /**
     * Simulates checking the connection status to the ETOUR system.
     *
     * @return true if the connection is active, false otherwise.
     */
    public boolean checkConnection() {
        System.out.println("ETOURClient: Checking connection to ETOUR system...");
        // Simulate some network delay or instability
        try {
            Thread.sleep(random.nextInt(50) + 10); // 10-60 ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connectionActive;
    }

    /**
     * Simulates searching the ETOUR system for data based on a query map.
     * This method directly corresponds to `searchETOUR` in the sequence diagram.
     *
     * @param query A map of query parameters (e.g., keyword, type, year ranges).
     * @return A list of maps, where each map represents a raw data entry from ETOUR.
     * @throws ETOURConnectionException if the connection is not active.
     */
    public List<Map<String, String>> searchETOUR(Map<String, String> query) {
        System.out.println("ETOURClient: Searching ETOUR with query: " + query);

        if (!checkConnection()) {
            throw new ETOURConnectionException("ETOUR connection is not active.");
        }

        // Simulate external system processing time
        try {
            Thread.sleep(random.nextInt(100) + 50); // 50-150 ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // --- Simulate search results based on query ---
        List<Map<String, String>> rawResults = new ArrayList<>();

        String keyword = query.getOrDefault("keyword", "").toLowerCase();
        String type = query.getOrDefault("type", "").toLowerCase();
        int yearStart = Integer.parseInt(query.getOrDefault("yearStart", "0"));
        int yearEnd = Integer.parseInt(query.getOrDefault("yearEnd", String.valueOf(Integer.MAX_VALUE)));

        // Sample data
        List<Map<String, String>> sampleData = Arrays.asList(
            createRawCulturalObject("CO001", "Mona Lisa", "A famous portrait by Leonardo da Vinci.", "Painting", "1503"),
            createRawCulturalObject("CO002", "Venus de Milo", "Ancient Greek sculpture, believed to depict Aphrodite.", "Sculpture", "130"),
            createRawCulturalObject("CO003", "The Starry Night", "Oil on canvas by Dutch Post-Impressionist painter Vincent van Gogh.", "Painting", "1889"),
            createRawCulturalObject("CO004", "Great Pyramid of Giza", "The oldest and largest of the three pyramids in the Giza Necropolis.", "Monument", "-2580"),
            createRawCulturalObject("CO005", "The Thinker", "Bronze and marble sculpture by Auguste Rodin.", "Sculpture", "1904")
        );

        for (Map<String, String> item : sampleData) {
            boolean matchesKeyword = keyword.isEmpty() ||
                                     item.get("name").toLowerCase().contains(keyword) ||
                                     item.get("description").toLowerCase().contains(keyword);
            boolean matchesType = type.isEmpty() ||
                                  item.get("type").toLowerCase().equals(type);
            boolean matchesYear = true;
            try {
                int itemYear = Integer.parseInt(item.get("year"));
                matchesYear = (yearStart <= 0 || itemYear >= yearStart) &&
                              (yearEnd == Integer.MAX_VALUE || itemYear <= yearEnd);
            } catch (NumberFormatException e) {
                // Ignore items with invalid years
            }


            if (matchesKeyword && matchesType && matchesYear) {
                rawResults.add(item);
            }
        }

        System.out.println("ETOURClient: Found " + rawResults.size() + " raw results.");
        return rawResults;
    }

    /**
     * Helper to create a raw cultural object map.
     */
    private Map<String, String> createRawCulturalObject(String id, String name, String description, String type, String year) {
        Map<String, String> obj = new HashMap<>();
        obj.put("id", id);
        obj.put("name", name);
        obj.put("description", description);
        obj.put("type", type);
        obj.put("year", year);
        return obj;
    }

    /**
     * Toggles the connection status for testing purposes.
     * @param active true to set connection active, false to set inactive.
     */
    public void setConnectionActive(boolean active) {
        this.connectionActive = active;
        System.out.println("ETOURClient: Connection status set to " + (active ? "ACTIVE" : "INACTIVE"));
    }
}