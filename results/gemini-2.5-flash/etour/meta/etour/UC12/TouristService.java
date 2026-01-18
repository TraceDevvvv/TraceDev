package com.etour.searchtourist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Handles the business logic for searching tourist accounts.
 * This service interacts with a data source (simulated here) to retrieve tourist information.
 */
public class TouristService {

    // A mock database to simulate stored tourist accounts.
    private final List<Tourist> touristDatabase;

    /**
     * Constructs a TouristService and initializes the mock database with some sample data.
     */
    public TouristService() {
        this.touristDatabase = new ArrayList<>();
        // Populate with sample data
        touristDatabase.add(new Tourist("T001", "Alice", "Smith", "alice.smith@example.com", "USA"));
        touristDatabase.add(new Tourist("T002", "Bob", "Johnson", "bob.j@example.com", "Canada"));
        touristDatabase.add(new Tourist("T003", "Charlie", "Brown", "charlie.b@example.com", "USA"));
        touristDatabase.add(new Tourist("T004", "Diana", "Miller", "diana.m@example.com", "UK"));
        touristDatabase.add(new Tourist("T005", "Eve", "Davis", "eve.d@example.com", "Australia"));
        touristDatabase.add(new Tourist("T006", "Frank", "White", "frank.w@example.com", "USA"));
        touristDatabase.add(new Tourist("T007", "Grace", "Taylor", "grace.t@example.com", "Canada"));
    }

    /**
     * Searches for tourist accounts based on the provided criteria.
     * This method simulates fetching data from a backend system (ETOUR server).
     *
     * @param criteria The {@link TouristSearchCriteria} object containing the search parameters.
     * @return A list of {@link Tourist} objects that match the criteria.
     * @throws ConnectionInterruptionException If there's a simulated interruption to the ETOUR server.
     */
    public List<Tourist> searchTourists(TouristSearchCriteria criteria) throws ConnectionInterruptionException {
        // Simulate a potential connection interruption to the ETOUR server.
        // This can be triggered by some condition or randomly for testing.
        if (simulateConnectionInterruption()) {
            throw new ConnectionInterruptionException("Connection to ETOUR server interrupted during search.");
        }

        // If no criteria are provided (all fields are null or empty), return an empty list
        // or all tourists, depending on business rules. For this use case, we'll return all
        // if no specific criteria are given, but typically a search with no criteria
        // might return nothing or require at least one field.
        // Let's assume if criteria is empty, it means "search all".
        if (criteria == null || criteria.isEmpty()) {
            return new ArrayList<>(touristDatabase); // Return a copy to prevent external modification
        }

        // Filter the mock database based on the provided criteria.
        // The filtering is case-insensitive and checks for partial matches for string fields.
        return touristDatabase.stream()
                .filter(tourist -> matches(tourist.getTouristId(), criteria.getTouristId()))
                .filter(tourist -> matches(tourist.getFirstName(), criteria.getFirstName()))
                .filter(tourist -> matches(tourist.getLastName(), criteria.getLastName()))
                .filter(tourist -> matches(tourist.getEmail(), criteria.getEmail()))
                .filter(tourist -> matches(tourist.getCountry(), criteria.getCountry()))
                .collect(Collectors.toList());
    }

    /**
     * Helper method to check if a tourist's attribute matches a given criterion.
     * The match is case-insensitive and checks for partial containment.
     * If the criterion is null or empty, it means this criterion is not applied.
     *
     * @param touristAttribute The attribute of the tourist (e.g., tourist.getFirstName()).
     * @param criterion The search criterion provided by the user.
     * @return True if the attribute matches the criterion or if the criterion is null/empty, false otherwise.
     */
    private boolean matches(String touristAttribute, String criterion) {
        if (criterion == null || criterion.trim().isEmpty()) {
            return true; // No criterion specified for this field, so it always matches.
        }
        if (touristAttribute == null) {
            return false; // Cannot match a null attribute with a non-empty criterion.
        }
        return touristAttribute.toLowerCase().contains(criterion.trim().toLowerCase());
    }

    /**
     * Simulates a random connection interruption.
     * In a real application, this would involve actual network checks or error handling.
     *
     * @return True if a connection interruption should be simulated, false otherwise.
     */
    private boolean simulateConnectionInterruption() {
        // For demonstration, let's say there's a 10% chance of interruption.
        return Math.random() < 0.1;
    }

    /**
     * Custom exception for simulating connection interruptions.
     */
    public static class ConnectionInterruptionException extends Exception {
        public ConnectionInterruptionException(String message) {
            super(message);
        }
    }
}