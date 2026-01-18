package com.example.culturalheritage.adapter;

import com.example.culturalheritage.model.Location;
import com.example.culturalheritage.exception.SearchFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * An Adapter that interfaces with the external ETOUR cultural heritage system.
 * It translates application-specific calls into ETOUR-specific requests and
 * adapts ETOUR's responses back into a format usable by the application.
 */
public class ETOURSystemAdapter {

    /**
     * Queries the external ETOUR system for cultural heritage data.
     * This method simulates the interaction with the ETOUR system (REQ-009, REQ-011).
     * @param query The string query derived from search criteria.
     * @param position The user's current location.
     * @return A list of raw cultural heritage data, typically as maps of key-value pairs.
     * @throws SearchFailedException if the connection to the ETOUR system is interrupted (REQ-011).
     */
    public List<Map<String, Object>> queryCulturalHeritage(String query, Location position) throws SearchFailedException {
        System.out.println("ETOURSystemAdapter: queryCulturalHeritage() - Sending query to ETOUR System...");
        System.out.println("  Query: " + query + ", Position: " + position.getLatitude() + ", " + position.getLongitude());

        // Simulate sending query to ETOUR system (REQ-009).
        // This method would typically involve making an actual API call (HTTP, SOAP, etc.)
        // to an external ETOUR server.
        // For this example, we simulate success or failure.

        // Simulate a connection error for demonstration purposes (REQ-011).
        // If the query contains "error" keyword, or with a certain probability, simulate failure.
        if (query.contains("error") || new Random().nextInt(100) < 10) { // 10% chance of failure
            System.err.println("ETOURSystemAdapter: Simulating ETOUR System ConnectionError (REQ-011).");
            // ETS --x ESA: ConnectionError
            // ESA --x CHR: SearchFailedException
            throw new SearchFailedException("Connection to ETOUR system interrupted or failed.");
        }

        // Simulate successful data retrieval from ETOUR (REQ-009).
        // This would be raw data (e.g., JSON parsed into Maps) from the external system.
        List<Map<String, Object>> rawData = new ArrayList<>();

        if (query.contains("museum")) {
            Map<String, Object> item1 = new HashMap<>();
            item1.put("id", "CH001");
            item1.put("name", "Historical Museum");
            item1.put("description", "A museum showcasing local history.");
            item1.put("type", "museum");
            item1.put("latitude", 40.7128 + 0.01);
            item1.put("longitude", -74.0060 + 0.01);
            rawData.add(item1);

            Map<String, Object> item2 = new HashMap<>();
            item2.put("id", "CH002");
            item2.put("name", "Art Gallery");
            item2.put("description", "Modern art collection.");
            item2.put("type", "art gallery");
            item2.put("latitude", 40.7128 - 0.005);
            item2.put("longitude", -74.0060 + 0.005);
            rawData.add(item2);
        } else if (query.contains("park")) {
            Map<String, Object> item3 = new HashMap<>();
            item3.put("id", "CH003");
            item3.put("name", "Central Park");
            item3.put("description", "Large urban park.");
            item3.put("type", "park");
            item3.put("latitude", 40.7850);
            item3.put("longitude", -73.9682);
            rawData.add(item3);
        } else {
            // Default data if no specific keyword match
            Map<String, Object> item4 = new HashMap<>();
            item4.put("id", "CH004");
            item4.put("name", "Ancient Ruins");
            item4.put("description", "Remains of an ancient civilization.");
            item4.put("type", "historical site");
            item4.put("latitude", 34.0522);
            item4.put("longitude", -118.2437);
            rawData.add(item4);
        }

        System.out.println("ETOURSystemAdapter: Received rawHeritageData from ETOUR System.");
        return rawData;
    }

    /**
     * Simulates sending a query to the external ETOUR system.
     * This is a conceptual method that would encapsulate the actual network call.
     * Added for Sequence Diagram clarity (ETS participant).
     * @param stringQuery The formatted query string.
     * @param userLocation The user's location.
     * @return Raw data from ETOUR.
     * @throws SearchFailedException if an error occurs during the external call.
     */
    public List<Map<String, Object>> sendQueryToETOUR(String stringQuery, Location userLocation) throws SearchFailedException {
        System.out.println("ETOURSystemAdapter: sendQueryToETOUR() - Actual call to external ETOUR system.");
        // This method would contain the actual HTTP client or network call logic.
        // For simulation purposes, it just delegates back to queryCulturalHeritage which has the simulation logic.
        // In a real scenario, queryCulturalHeritage would prepare the request, and this method would execute it.
        return queryCulturalHeritage(stringQuery, userLocation);
    }
}