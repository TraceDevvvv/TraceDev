package com.example.data;

import com.example.model.PointOfRestEntity;

/**
 * Concrete data source that fetches data from an external ETOUR server.
 * Implements IDataSource interface.
 */
public class ETOURServerDataSource implements IDataSource {
    /**
     * Simulates fetching a PointOfRestEntity from the external ETOUR server.
     * In a real implementation, this would involve HTTP requests, etc.
     * @param id the point of rest ID
     * @return PointOfRestEntity (simulated data for demonstration)
     * @throws DataAccessException if connection to server fails
     */
    @Override
    public PointOfRestEntity fetchPointOfRest(String id) throws DataAccessException {
        // Simulate connection interruption for certain IDs as per sequence diagram
        if ("999".equals(id)) {
            throw new DataAccessException("Connection to ETOUR server interrupted");
        }

        // Simulate successful fetch with dummy data
        // Assumption: the external system returns data in PointOfRestEntity format
        PointOfRestEntity.Address address = new PointOfRestEntity.Address("123 Tourism St", "Adventure City", "12345", "Wonderland");
        java.util.List<String> amenities = java.util.Arrays.asList("Restrooms", "Wi-Fi", "Snack Bar");
        return new PointOfRestEntity(id, "Refreshment Point " + id, address, amenities);
    }
}