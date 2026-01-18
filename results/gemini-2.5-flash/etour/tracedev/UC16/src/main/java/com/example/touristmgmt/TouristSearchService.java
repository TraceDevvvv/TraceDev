package com.example.touristmgmt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service for searching and retrieving tourist information.
 * Added to satisfy requirement R4 (TouristSearchService).
 */
public class TouristSearchService {

    // Dummy data for demonstration
    private final List<Tourist> allTourists = new ArrayList<>();

    public TouristSearchService() {
        allTourists.add(new Tourist("T001", "Alice Smith", "alice.s@example.com"));
        allTourists.add(new Tourist("T002", "Bob Johnson", "bob.j@example.com"));
        allTourists.add(new Tourist("T003", "Charlie Brown", "charlie.b@example.com"));
        allTourists.add(new Tourist("T004", "Diana Prince", "diana.p@example.com"));
    }

    /**
     * Searches for tourists based on a given query string (e.g., name or email).
     *
     * @param query The search query.
     * @return A list of matching Tourist objects.
     */
    public List<Tourist> searchTourists(String query) {
        System.out.println("TouristSearchService: Searching tourists for query: '" + query + "'");
        List<Tourist> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String lowerCaseQuery = query.toLowerCase();
        for (Tourist tourist : allTourists) {
            if (tourist.getName().toLowerCase().contains(lowerCaseQuery) ||
                tourist.getEmail().toLowerCase().contains(lowerCaseQuery) ||
                tourist.getTouristId().toLowerCase().contains(lowerCaseQuery)) {
                results.add(tourist);
            }
        }
        System.out.println("TouristSearchService: Found " + results.size() + " results.");
        return results;
    }

    /**
     * Retrieves a list of all tourists in the system.
     *
     * @return A list containing all Tourist objects.
     */
    public List<Tourist> getAllTourists() {
        System.out.println("TouristSearchService: Retrieving all tourists.");
        return new ArrayList<>(allTourists); // Return a copy to prevent external modification
    }
}