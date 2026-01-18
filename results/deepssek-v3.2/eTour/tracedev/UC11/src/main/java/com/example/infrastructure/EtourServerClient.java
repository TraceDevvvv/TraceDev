package com.example.infrastructure;

import java.util.List;

/**
 * Client for communicating with the external eTour server.
 */
public class EtourServerClient {

    /**
     * Fetches convention data for a given point ID.
     * In a real implementation, this would make an HTTP/API call.
     */
    public List<ConventionData> fetchConventionsForPoint(String pointId) {
        // Simulating data retrieval. In practice, this could throw an exception on connection failure.
        return List.of(
            new ConventionData("conv1", "Details for convention 1", pointId, "2023-10-01T10:00:00"),
            new ConventionData("conv2", "Details for convention 2", pointId, "2023-10-02T14:30:00")
        );
    }
}