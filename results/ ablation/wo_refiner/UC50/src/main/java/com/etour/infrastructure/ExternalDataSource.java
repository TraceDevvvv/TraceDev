package com.etour.infrastructure;

import java.util.List;
import java.util.Map;

/**
 * External data source simulating ETOUR server.
 */
public class ExternalDataSource {
    /**
     * Fetches raw visited sites data from the external server.
     * @param touristId ID of the tourist
     * @return List of raw data maps (simulated)
     */
    public List<Map<String, Object>> fetchVisitedSites(String touristId) {
        // Simulating data fetch; in reality, this would be a network call.
        // For demonstration, we return a hardcoded list.
        // If connection is interrupted, this method would throw a ConnectionError.
        // Here we assume connection is successful.
        return List.of(
            Map.of("id", "site1", "name", "Eiffel Tower", "location", "Paris"),
            Map.of("id", "site2", "name", "Colosseum", "location", "Rome")
        );
    }
}