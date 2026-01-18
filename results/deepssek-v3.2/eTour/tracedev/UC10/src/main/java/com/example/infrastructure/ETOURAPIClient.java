package com.example.infrastructure;

/**
 * Client for making API calls to the ETOUR server.
 */
public class ETOURAPIClient {
    private String baseUrl;

    public ETOURAPIClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Performs a GET request to the specified path.
     * @param path the API endpoint path.
     * @return the response body as a JSON string.
     * Assumption: returns a hardcoded JSON for simplicity.
     */
    public String get(String path) {
        // Simulate API call; in reality, this would use HTTP client.
        // Return a mock JSON response matching the PointOfRest structure.
        return "{ \"id\": 123, \"name\": \"Rest Area A1\", \"location\": \"Highway 101, km 25\", \"amenities\": [\"Parking\", \"Restrooms\", \"Coffee\"] }";
    }

    /**
     * Returns JSON Response (sequence diagram message m11).
     * @param path the API endpoint path.
     * @return the JSON response.
     */
    public String getJSONResponse(String path) {
        return get(path);
    }
}