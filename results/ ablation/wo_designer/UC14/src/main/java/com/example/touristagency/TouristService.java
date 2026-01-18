/**
 * Service layer for Tourist account operations, including search.
 */
package com.example.touristagency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TouristService {
    // Simulating a data source (in a real application, this would be a database)
    private List<Tourist> touristDatabase;

    public TouristService() {
        initializeSampleData();
    }

    /**
     * Initialize sample tourist data for demonstration.
     */
    private void initializeSampleData() {
        touristDatabase = new ArrayList<>();
        touristDatabase.add(new Tourist("1", "John", "Doe", "john.doe@example.com",
                "+1234567890", "USA", "A12345678"));
        touristDatabase.add(new Tourist("2", "Jane", "Smith", "jane.smith@example.com",
                "+9876543210", "Canada", "B98765432"));
        touristDatabase.add(new Tourist("3", "Ahmed", "Khan", "ahmed.khan@example.com",
                "+1122334455", "India", "C11223344"));
        touristDatabase.add(new Tourist("4", "Maria", "Garcia", "maria.garcia@example.com",
                "+5566778899", "Spain", "D55667788"));
    }

    /**
     * Searches for tourist accounts based on the provided criteria.
     * Criteria fields that are null or empty are ignored (partial matching).
     *
     * @param criteria the search criteria
     * @return a list of Tourist accounts matching the criteria
     */
    public List<Tourist> searchTourists(TouristSearchCriteria criteria) {
        // Simulate processing time (to meet quality requirement of <2 seconds)
        // In real scenario, this would be a database query with indexes.
        List<Tourist> result = touristDatabase.stream()
                .filter(tourist -> matchesCriteria(tourist, criteria))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * Checks if a tourist matches the given search criteria.
     * All non‑null/non‑blank criteria must match (case‑insensitive partial match).
     */
    private boolean matchesCriteria(Tourist tourist, TouristSearchCriteria criteria) {
        if (criteria.getFirstName() != null && !criteria.getFirstName().isBlank() &&
                !tourist.getFirstName().toLowerCase().contains(criteria.getFirstName().toLowerCase())) {
            return false;
        }
        if (criteria.getLastName() != null && !criteria.getLastName().isBlank() &&
                !tourist.getLastName().toLowerCase().contains(criteria.getLastName().toLowerCase())) {
            return false;
        }
        if (criteria.getEmail() != null && !criteria.getEmail().isBlank() &&
                !tourist.getEmail().toLowerCase().contains(criteria.getEmail().toLowerCase())) {
            return false;
        }
        if (criteria.getPhone() != null && !criteria.getPhone().isBlank() &&
                !tourist.getPhone().contains(criteria.getPhone())) {
            return false;
        }
        if (criteria.getNationality() != null && !criteria.getNationality().isBlank() &&
                !tourist.getNationality().toLowerCase().contains(criteria.getNationality().toLowerCase())) {
            return false;
        }
        if (criteria.getPassportNumber() != null && !criteria.getPassportNumber().isBlank() &&
                !tourist.getPassportNumber().toLowerCase().contains(criteria.getPassportNumber().toLowerCase())) {
            return false;
        }
        return true;
    }

    /**
     * Adds a new tourist to the database (for testing purposes).
     */
    public void addTourist(Tourist tourist) {
        touristDatabase.add(tourist);
    }
}