package com.example.culturalobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * GATEWAY: Simulates an external ETOUR system.
 * Handles communication and data transformation specific to the ETOUR API.
 */
public class ETOURGateway {

    // Flag to simulate connection failures for testing purposes
    private boolean simulateConnectionFailure = false;

    /**
     * Custom exception for ETOUR connection failures.
     */
    public static class ETOURConnectionException extends Exception {
        public ETOURConnectionException(String message) {
            super(message);
        }
    }

    /**
     * Sets whether the gateway should simulate a connection failure.
     * @param simulateConnectionFailure True to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }

    /**
     * Simulates searching the ETOUR system with a given query.
     * REQ-007: This method now explicitly returns a String which will then be parsed by `parseETOURResponse`.
     *
     * @param query A map of search parameters (e.g., keywords, category, location).
     * @return A JSON-like string representing the raw response from ETOUR.
     * @throws ETOURConnectionException if a simulated connection failure occurs.
     */
    public String searchETOUR(Map<String, String> query) throws ETOURConnectionException {
        System.out.println("[ETOURGateway] Searching ETOUR with query: " + query);

        // Simulate network delay
        try {
            Thread.sleep(500); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate ETOUR connection interruption
        if (simulateConnectionFailure) {
            System.err.println("[ETOURGateway] Simulating ETOUR connection failure.");
            throw new ETOURConnectionException("ETOUR server is unreachable.");
        }

        // Simulate generating a raw JSON response based on the query
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\"items\": [");

        String keywords = query.getOrDefault("keywords", "");
        String category = query.getOrDefault("category", "");
        String location = query.getOrDefault("location", "");

        Random random = new Random();
        int numResults = random.nextInt(3) + 1; // Simulate 1 to 3 results

        if (keywords.toLowerCase().contains("museum") || category.toLowerCase().contains("art")) {
            numResults += 1; // More results for art/museum queries
        }

        List<String> objectsJson = new ArrayList<>();
        for (int i = 0; i < numResults; i++) {
            String id = "CO-" + (random.nextInt(9000) + 1000);
            String name = "Cultural Object " + (i + 1);
            String description = "Description for " + name + " related to " + keywords + " in " + location + ".";
            String type = category.isEmpty() ? "General" : category;

            objectsJson.add(String.format("{\"id\": \"%s\", \"name\": \"%s\", \"description\": \"%s\", \"type\": \"%s\"}",
                    id, name, description, type));
        }

        jsonBuilder.append(String.join(",", objectsJson));
        jsonBuilder.append("]}");

        System.out.println("[ETOURGateway] Raw ETOUR response generated.");
        return jsonBuilder.toString();
    }

    /**
     * Parses the raw string response from the ETOUR system into a list of CulturalObject.
     * Modified to satisfy requirement REQ-007.
     *
     * @param etourRawResponse The raw string (e.g., JSON) received from ETOUR.
     * @return A List of CulturalObject parsed from the raw response.
     */
    public List<CulturalObject> parseETOURResponse(String etourRawResponse) {
        System.out.println("[ETOURGateway] Parsing raw ETOUR response...");
        List<CulturalObject> culturalObjects = new ArrayList<>();

        // Simulate parsing a simple JSON string
        // In a real application, a JSON parsing library (like Jackson or Gson) would be used.
        if (etourRawResponse != null && etourRawResponse.contains("\"items\": [")) {
            String itemsContent = etourRawResponse.substring(etourRawResponse.indexOf("[") + 1, etourRawResponse.lastIndexOf("]"));
            if (!itemsContent.trim().isEmpty()) {
                String[] itemStrings = itemsContent.split("},\\{");
                for (String itemString : itemStrings) {
                    itemString = itemString.replace("{", "").replace("}", ""); // Clean up object delimiters
                    String id = extractValue(itemString, "id");
                    String name = extractValue(itemString, "name");
                    String description = extractValue(itemString, "description");
                    String type = extractValue(itemString, "type");
                    culturalObjects.add(new CulturalObject(id, name, description, type));
                }
            }
        }
        System.out.println("[ETOURGateway] Finished parsing. Found " + culturalObjects.size() + " cultural objects.");
        return culturalObjects;
    }

    /**
     * Helper method to extract a value from a simulated JSON string fragment.
     */
    private String extractValue(String jsonFragment, String key) {
        String searchKey = "\"" + key + "\": \"";
        int startIndex = jsonFragment.indexOf(searchKey);
        if (startIndex != -1) {
            startIndex += searchKey.length();
            int endIndex = jsonFragment.indexOf("\"", startIndex);
            if (endIndex != -1) {
                return jsonFragment.substring(startIndex, endIndex);
            }
        }
        return "";
    }
}