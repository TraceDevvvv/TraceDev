package com.example.repository;

import com.example.model.PointOfRest;
import com.example.server.ServerConnection;

import java.util.List;
import java.util.Map;

/**
 * Implementation of PointOfRestRepository.
 * Abstracts data access and uses ServerConnection.
 * Repository Pattern.
 */
public class PointOfRestRepositoryImpl implements PointOfRestRepository {
    private ServerConnection serverConnection;

    public PointOfRestRepositoryImpl(ServerConnection connection) {
        this.serverConnection = connection;
    }

    @Override
    public List<PointOfRest> findAll() {
        // Assumption: findAll uses empty criteria
        return findByCriteria(Map.of());
    }

    @Override
    public List<PointOfRest> findByCriteria(Map<String, Object> criteria) {
        // Fetch from server using the connection
        return fetchFromServer(criteria);
    }

    /**
     * Private method to fetch data from server.
     * Called within findByCriteria.
     */
    private List<PointOfRest> fetchFromServer(Map<String, Object> criteria) {
        // Delegate to ServerConnection
        return serverConnection.sendRequest(criteria);
    }
}