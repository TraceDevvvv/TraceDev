
package com.example.repository;

import com.example.model.Convention;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Repository implementation that connects to the ETOUR server.
 * Updated to satisfy requirements REQ-009 and REQ-010.
 */
public class ETOURServerRepository implements ConventionHistoryRepository {
    private String serverEndpoint;
    private int connectionTimeout;
    private int retryPolicy;

    // Default constructor with example values.
    public ETOURServerRepository() {
        this.serverEndpoint = "https://api.etour.example.com";
        this.connectionTimeout = 5000; // 5 seconds
        this.retryPolicy = 3;
    }

    public ETOURServerRepository(String serverEndpoint, int connectionTimeout, int retryPolicy) {
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
        
        // Example data.
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        Convention conv1 = new Convention("1", pointOfRestId, "Convention Data A", LocalDateTime.parse("2023-10-01T10:00:00", formatter));
        Convention conv2 = new Convention("2", pointOfRestId, "Convention Data B", LocalDateTime.parse("2023-10-02T11:30:00", formatter));
        return List.of(conv1, conv2);
    }
}
