package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of PointOfRestRepository that queries the ETOUR server.
 */
public class PointOfRestRepositoryImpl implements PointOfRestRepository {
    private ETOURServer etourServer;

    public PointOfRestRepositoryImpl(ETOURServer etourServer) {
        this.etourServer = etourServer;
    }

    @Override
    public List<PointOfRest> findAll(PointOfRestSpecification specification) {
        // Delegate to fetchFromServer; could be cached in a real implementation
        return fetchFromServer(specification);
    }

    @Override
    public List<PointOfRest> fetchFromServer(PointOfRestSpecification specification) {
        // Exit condition: Connection to server ETOUR is interrupted.
        if (!etourServer.isConnected()) {
            // Return empty list to indicate connection error (as per sequence diagram)
            return new ArrayList<>();
        }
        // Query the external server
        List<PointOfRest> allPoints = etourServer.queryData(specification);
        // Filter using specification
        List<PointOfRest> filtered = new ArrayList<>();
        for (PointOfRest point : allPoints) {
            if (specification.isSatisfiedBy(point)) {
                filtered.add(point);
            }
        }
        return filtered;
    }
}