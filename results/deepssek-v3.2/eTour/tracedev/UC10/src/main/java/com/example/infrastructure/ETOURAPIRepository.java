package com.example.infrastructure;

import com.example.domain.PointOfRest;
import com.example.repository.PointOfRestRepository;
import java.util.Arrays;
import java.util.List;

/**
 * Infrastructure/Data Layer implementation of PointOfRestRepository.
 * Connects to the external ETOUR API.
 */
public class ETOURAPIRepository implements PointOfRestRepository {
    private ETOURAPIClient client;
    private ConnectionManager connectionManager;

    public ETOURAPIRepository(ETOURAPIClient client, ConnectionManager connectionManager) {
        this.client = client;
        this.connectionManager = connectionManager;
    }

    @Override
    public PointOfRest findById(int id) throws DataAccessException {
        try {
            // Ensure connection as per sequence diagram
            connectionManager.ensureConnection();
            // Call the API client
            String jsonResponse = client.get("/points-of-rest/" + id);
            // Map JSON to domain object
            return mapToDomainObject(jsonResponse);
        } catch (ConnectionException e) {
            // Connection failure leads to DataAccessException
            throw new DataAccessException("Could not connect to ETOUR server", e);
        }
    }

    /**
     * Maps a JSON string to a PointOfRest domain object.
     * Assumption: simple parsing; in reality, use a JSON library like Jackson.
     * @param json the JSON response string.
     * @return the mapped PointOfRest.
     */
    private PointOfRest mapToDomainObject(String json) {
        // Simplified parsing - in practice, use a proper JSON parser.
        // For this example, extract data from the mock JSON format.
        // The mock JSON: { "id": 123, "name": "Rest Area A1", "location": "Highway 101, km 25", "amenities": ["Parking","Restrooms","Coffee"] }
        // We'll simulate parsing by creating a PointOfRest with fixed values.
        // In a real implementation, you would parse the JSON dynamically.
        int parsedId = 123;
        String parsedName = "Rest Area A1";
        String parsedLocation = "Highway 101, km 25";
        List<String> parsedAmenities = Arrays.asList("Parking", "Restrooms", "Coffee");
        return new PointOfRest(parsedId, parsedName, parsedLocation, parsedAmenities);
    }

    /**
     * Returns OK (sequence diagram message m9).
     * @return "OK"
     */
    public String getConnectionStatus() {
        try {
            connectionManager.ensureConnection();
            return "OK";
        } catch (ConnectionException e) {
            return "ERROR";
        }
    }
}