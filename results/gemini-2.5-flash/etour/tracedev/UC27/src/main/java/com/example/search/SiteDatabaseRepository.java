package com.example.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Concrete implementation of SiteRepository that interacts with a simulated database.
 * This class directly maps to the 'SiteDatabaseRepository' class in the UML diagram.
 */
public class SiteDatabaseRepository implements SiteRepository {
    // Private field as specified in the UML diagram
    private Object dbConnection; // Represents a database connection object

    // A simple in-memory list to simulate database storage
    private List<Site> simulatedDatabase;

    /**
     * Constructor for SiteDatabaseRepository.
     * Initializes a simulated database connection and populates with sample data.
     */
    public SiteDatabaseRepository() {
        this.dbConnection = new Object(); // Simulate a connection
        this.simulatedDatabase = new ArrayList<>();
        simulatedDatabase.add(new Site("S001", "Ancient Ruins", "Greece", "Historic site with ancient structures."));
        simulatedDatabase.add(new Site("S002", "Modern Art Museum", "New York", "Contemporary art collections."));
        simulatedDatabase.add(new Site("S003", "Botanical Gardens", "London", "Expansive gardens with diverse flora."));
        simulatedDatabase.add(new Site("S004", "Great Wall", "China", "An iconic historical defense structure."));
    }

    /**
     * Finds a list of sites based on the provided criteria by querying the simulated database.
     * @param criteria A map of key-value pairs representing search criteria (e.g., "name": "example").
     * @return A list of Site objects matching the criteria.
     * @throws ConnectionError if a simulated connection interruption occurs (e.g., if criteria contains "forceConnectionError").
     */
    @Override
    public List<Site> findByCriteria(Map<String, String> criteria) throws ConnectionError {
        // Simulate database query execution based on criteria
        System.out.println("    [SiteDatabaseRepository] Database: executeQuery(" + criteria + ")");

        // Simulate a connection interruption if a specific criterion is present
        if (criteria != null && criteria.containsKey("forceConnectionError")) {
            System.out.println("    [SiteDatabaseRepository] Database: connection_interruption (ETOUR)");
            throw new ConnectionError("ETOUR: Simulated connection interruption to the database.");
        }

        List<Site> results = simulatedDatabase.stream()
                .filter(site -> {
                    boolean match = true;
                    if (criteria != null) {
                        for (Map.Entry<String, String> entry : criteria.entrySet()) {
                            String key = entry.getKey().toLowerCase();
                            String value = entry.getValue().toLowerCase();
                            switch (key) {
                                case "name":
                                    if (!site.name.toLowerCase().contains(value)) match = false;
                                    break;
                                case "location":
                                    if (!site.location.toLowerCase().contains(value)) match = false;
                                    break;
                                case "description":
                                    if (!site.description.toLowerCase().contains(value)) match = false;
                                    break;
                                case "id":
                                    if (!site.id.toLowerCase().contains(value)) match = false;
                                    break;
                                default:
                                    // Ignore unknown criteria or handle as needed
                                    break;
                            }
                            if (!match) break; // No need to check further criteria if one doesn't match
                        }
                    }
                    return match;
                })
                .collect(Collectors.toList());

        System.out.println("    [SiteDatabaseRepository] Database: return site_data (Found " + results.size() + " sites)");
        return results;
    }
}