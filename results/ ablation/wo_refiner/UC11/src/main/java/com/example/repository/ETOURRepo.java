
package com.example.repository;

import com.example.model.Convention;
import java.util.List;

/**
 * Repository implementation that connects to the ETOUR server (aliased as ETOURRepo in sequence diagram).
 */
public class ETOURRepo implements RepoInterface {
    private String serverEndpoint;
    private int connectionTimeout;
    private int retryPolicy;

    // Default constructor with example values.
    public ETOURRepo() {
        this.serverEndpoint = "https://api.etour.example.com";
        this.connectionTimeout = 5000; // 5 seconds
        this.retryPolicy = 3;
    }

    public ETOURRepo(String serverEndpoint, int connectionTimeout, int retryPolicy) {
        this.serverEndpoint = serverEndpoint;
        this.connectionTimeout = connectionTimeout;
        this.retryPolicy = retryPolicy;
    }

    @Override
    public List<Convention> findByPointOfRestId(String pointOfRestId) throws ServerConnectionException {
        try {
            // Simulate network call with potential interruption.
            return fetchFromRemoteServer(pointOfRestId);
        } catch (RuntimeException e) {
            // Simulate server connection interruption.
            throw new ServerConnectionException("Server connection interrupted", e);
        }
    }

    /**
     * Fetches conventions from the remote server.
     * @param pointOfRestId the point of rest ID.
     * @return list of conventions.
     * @throws ServerConnectionException if connection fails.
     */
    private List<Convention> fetchFromRemoteServer(String pointOfRestId) throws ServerConnectionException {
        // Simulate a remote fetch by returning dummy data.
        // In a real implementation, this would make an HTTP request.
        if (pointOfRestId == null || pointOfRestId.isEmpty()) {
            throw new ServerConnectionException("Invalid point of rest ID");
        }
        
        // Example data - need to create DateTime objects
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        // Use a simple representation
        Convention conv1 = new Convention("1", pointOfRestId, "Convention Data A", 
                java.time.LocalDateTime.of(2023, 10, 1, 10, 0, 0));
        Convention conv2 = new Convention("2", pointOfRestId, "Convention Data B", 
                java.time.LocalDateTime.of(2023, 10, 2, 11, 30, 0));
        return List.of(conv1, conv2);
    }
}
