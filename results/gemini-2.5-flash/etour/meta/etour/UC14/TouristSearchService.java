package com.etour.touristsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class responsible for searching tourist accounts.
 * It simulates a data source and provides methods to filter tourists based on various criteria.
 */
public class TouristSearchService {

    // A simulated database or data source for tourist accounts.
    // In a real application, this would interact with a database.
    private List<Tourist> touristDatabase;

    /**
     * Constructor for TouristSearchService.
     * Initializes the simulated tourist database with some sample data.
     */
    public TouristSearchService() {
        this.touristDatabase = new ArrayList<>();
        // Populate with some sample data
        touristDatabase.add(new Tourist("T001", "Alice", "Smith", "alice.smith@example.com", "USA", "123-456-7890"));
        touristDatabase.add(new Tourist("T002", "Bob", "Johnson", "bob.j@example.com", "Canada", "098-765-4321"));
        touristDatabase.add(new Tourist("T003", "Charlie", "Brown", "charlie.b@example.com", "USA", "111-222-3333"));
        touristDatabase.add(new Tourist("T004", "Diana", "Prince", "diana.p@example.com", "UK", "444-555-6666"));
        touristDatabase.add(new Tourist("T005", "Eve", "Adams", "eve.a@example.com", "USA", "777-888-9999"));
        touristDatabase.add(new Tourist("T006", "Frank", "White", "frank.w@example.com", "Germany", "000-111-2222"));
    }

    /**
     * Searches for tourist accounts based on the provided search criteria.
     * This method allows for flexible searching by various fields.
     * All parameters are optional; if a parameter is null or empty, it is ignored in the search.
     * The search is case-insensitive for string fields.
     *
     * @param touristId Optional. The unique identifier of the tourist.
     * @param firstName Optional. The first name of the tourist.
     * @param lastName Optional. The last name of the tourist.
     * @param email Optional. The email address of the tourist.
     * @param country Optional. The country of residence of the tourist.
     * @param phoneNumber Optional. The phone number of the tourist.
     * @return A list of Tourist objects that match the specified criteria.
     *         Returns an empty list if no matches are found or if all search parameters are null/empty.
     */
    public List<Tourist> searchTourists(String touristId, String firstName, String lastName,
                                        String email, String country, String phoneNumber) {
        // If the database is empty, return an empty list immediately.
        if (touristDatabase.isEmpty()) {
            return new ArrayList<>();
        }

        // Filter the tourist database based on the provided criteria.
        // Using Java Streams for efficient and readable filtering.
        return touristDatabase.stream()
                .filter(tourist -> {
                    boolean matches = true; // Assume a match until a criterion fails

                    // Check touristId if provided
                    if (touristId != null && !touristId.trim().isEmpty()) {
                        matches = matches && tourist.getTouristId().equalsIgnoreCase(touristId.trim());
                    }
                    // Check firstName if provided
                    if (firstName != null && !firstName.trim().isEmpty()) {
                        matches = matches && tourist.getFirstName().equalsIgnoreCase(firstName.trim());
                    }
                    // Check lastName if provided
                    if (lastName != null && !lastName.trim().isEmpty()) {
                        matches = matches && tourist.getLastName().equalsIgnoreCase(lastName.trim());
                    }
                    // Check email if provided
                    if (email != null && !email.trim().isEmpty()) {
                        matches = matches && tourist.getEmail().equalsIgnoreCase(email.trim());
                    }
                    // Check country if provided
                    if (country != null && !country.trim().isEmpty()) {
                        matches = matches && tourist.getCountry().equalsIgnoreCase(country.trim());
                    }
                    // Check phoneNumber if provided
                    if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                        matches = matches && tourist.getPhoneNumber().equalsIgnoreCase(phoneNumber.trim());
                    }
                    return matches;
                })
                .collect(Collectors.toList()); // Collect the filtered results into a new list
    }

    /**
     * Simulates an interruption of connection to the ETOUR server.
     * In a real scenario, this would involve handling network exceptions or
     * checking the status of a remote connection.
     * For this simulation, it simply prints a message.
     */
    public void simulateConnectionInterruption() {
        System.out.println("ETOUR server connection interrupted. Search functionality may be affected.");
        // In a real application, this might throw an exception or set a status flag.
    }
}