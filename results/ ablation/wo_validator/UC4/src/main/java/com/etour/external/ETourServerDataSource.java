package com.etour.external;

import com.etour.adapters.SearchQuery;
import com.etour.entities.CulturalObject;
import com.etour.usecases.SearchCulturalObjectsUseCase;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Simulates an external data source that connects to the ETour server.
 * Implements the ExternalDataSource interface.
 */
public class ETourServerDataSource implements ExternalDataSource {
    private boolean connectionAvailable;
    private final Random random;

    public ETourServerDataSource() {
        this.connectionAvailable = true;
        this.random = new Random();
    }

    /**
     * Checks the connection to the server (as per sequence diagram).
     * @return true if connection is available, false otherwise
     */
    private boolean checkConnection() {
        // Simulate occasional connection failures (10% chance)
        if (random.nextInt(10) == 0) {
            connectionAvailable = false;
            return false;
        }
        connectionAvailable = true;
        return true;
    }

    @Override
    public List<CulturalObject> fetchCulturalObjects(SearchQuery query) {
        System.out.println("ETourServerDataSource: fetchCulturalObjects called with query: " + query);
        
        // Check connection (step in sequence diagram)
        if (!checkConnection()) {
            System.err.println("ETourServerDataSource: Connection to server failed!");
            throw new SearchCulturalObjectsUseCase.ServerConnectionException("Connection lost to ETour server");
        }

        // Simulate fetching data from server
        // In a real implementation, this would make an HTTP/API call
        List<CulturalObject> results = new ArrayList<>();
        
        // Generate some mock data based on query
        int count = Math.max(1, random.nextInt(5)); // 1-4 mock objects
        
        for (int i = 1; i <= count; i++) {
            CulturalObject obj = new CulturalObject(
                "ID-" + i,
                generateTitle(query),
                "Description for object " + i,
                query.getObjectType() != null ? query.getObjectType() : "Unknown",
                query.getLocation() != null ? query.getLocation() : "Unknown location"
            );
            results.add(obj);
        }
        
        System.out.println("ETourServerDataSource: Returning " + results.size() + " cultural objects");
        return results;
    }

    @Override
    public int countCulturalObjects(SearchQuery query) {
        System.out.println("ETourServerDataSource: countCulturalObjects called with query: " + query);
        
        if (!checkConnection()) {
            System.err.println("ETourServerDataSource: Connection to server failed during count!");
            throw new SearchCulturalObjectsUseCase.ServerConnectionException("Connection lost to ETour server");
        }

        // Simulate counting - typically this would be a separate server call
        // For simplicity, we'll return a random count between 5 and 20
        int count = 5 + random.nextInt(16);
        System.out.println("ETourServerDataSource: Count result: " + count);
        return count;
    }

    // Helper to generate mock title based on query keywords
    private String generateTitle(SearchQuery query) {
        if (query.getKeywords() != null && !query.getKeywords().isEmpty()) {
            return "Object related to " + String.join(" ", query.getKeywords());
        }
        return "Cultural Object";
    }

    public boolean isConnectionAvailable() {
        return connectionAvailable;
    }
}