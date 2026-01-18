package com.example.bannerchecker;

/**
 * Repository implementation that interacts with the ETOUR server.
 * Simulates network operations and may throw ConnectionException.
 */
public class ETOURServerRepository implements RefreshmentPointConventionRepository {
    private String serverEndpoint;

    public ETOURServerRepository(String serverEndpoint) {
        this.serverEndpoint = serverEndpoint;
    }

    @Override
    public RefreshmentPointConvention findById(String refreshmentPointId) throws ConnectionException {
        // Simulate a database or remote lookup.
        // In a real implementation, this would connect to the server endpoint.
        // For demonstration, we return a dummy convention if the ID is "valid".
        if (refreshmentPointId == null || refreshmentPointId.trim().isEmpty()) {
            return null;
        }
        // Simulate a connection failure randomly (for REQ-010).
        if (Math.random() < 0.1) { // 10% chance to simulate connection loss.
            throw new ConnectionException("ETOUR server connection failed for ID: " + refreshmentPointId);
        }
        // Return a dummy convention for known IDs.
        // Assume max allowed banners is 5 for all points.
        return new RefreshmentPointConvention(refreshmentPointId, 3, 5);
    }

    @Override
    public void save(RefreshmentPointConvention convention) throws ConnectionException {
        // Simulate saving to the server.
        // Simulate a connection failure randomly.
        if (Math.random() < 0.1) {
            throw new ConnectionException("ETOUR server connection failed while saving.");
        }
        // In a real implementation, we would persist the convention.
        System.out.println("[ETOURServerRepository] Saved convention for refreshment point: " + convention.getRefreshmentPointId());
    }

    @Override
    public RefreshmentPointConvention getPreviousState(String refreshmentPointId) throws ConnectionException {
        // Simulate retrieving a previous state (e.g., from a log or backup).
        // Simulate a connection failure randomly.
        if (Math.random() < 0.1) {
            throw new ConnectionException("ETOUR server connection failed while retrieving previous state.");
        }
        // Return a dummy previous state (with one less banner for demonstration).
        return new RefreshmentPointConvention(refreshmentPointId, 2, 5);
    }
}