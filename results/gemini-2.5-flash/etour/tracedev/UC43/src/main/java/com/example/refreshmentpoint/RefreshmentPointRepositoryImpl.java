package com.example.refreshmentpoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IRefreshmentPointRepository.
 * Simulates database interaction for RefreshmentPoint entities.
 */
public class RefreshmentPointRepositoryImpl implements IRefreshmentPointRepository {
    private Object databaseConnection; // Placeholder for a database connection object
    private Map<String, RefreshmentPoint> database; // In-memory "database" for simulation
    private NetworkStatusMonitor networkStatusMonitor; // Dependency for network status (XC3)

    public RefreshmentPointRepositoryImpl(NetworkStatusMonitor networkStatusMonitor) {
        this.networkStatusMonitor = networkStatusMonitor;
        this.database = new HashMap<>();
        // Initialize with some dummy data
        database.put("RP001", new RefreshmentPoint("RP001", "Coffee Corner", "Lobby", "Cafe"));
        database.put("RP002", new RefreshmentPoint("RP002", "Snack Bar", "Floor 3", "Snacks"));
        database.put("RP003", new RefreshmentPoint("RP003", "Water Fountain", "Hallway", "Utility"));
    }

    /**
     * Simulates finding a RefreshmentPoint by ID in the database.
     *
     * @param id The ID of the refreshment point.
     * @return The RefreshmentPoint found, or null if not found.
     * @throws ETOURConnectionException If the ETOUR server connection is lost.
     */
    @Override
    public RefreshmentPoint findById(String id) throws ETOURConnectionException {
        // XC3: Check ETOUR connection status
        if (!networkStatusMonitor.isETOURConnected()) {
            networkStatusMonitor.notifyConnectionLoss(); // Notify about connection loss
            throw new ETOURConnectionException("Connection to ETOUR server interrupted during findById operation.");
        }
        System.out.println("Repository: Searching for RefreshmentPoint with ID: " + id);
        // Simulate database lookup
        return database.get(id);
    }

    /**
     * Simulates saving a RefreshmentPoint to the database.
     * Not directly used in the sequence diagram but implemented for completeness.
     *
     * @param point The RefreshmentPoint to save.
     * @throws ETOURConnectionException If the ETOUR server connection is lost.
     */
    @Override
    public void save(RefreshmentPoint point) throws ETOURConnectionException {
        // XC3: Check ETOUR connection status
        if (!networkStatusMonitor.isETOURConnected()) {
            networkStatusMonitor.notifyConnectionLoss();
            throw new ETOURConnectionException("Connection to ETOUR server interrupted during save operation.");
        }
        System.out.println("Repository: Saving RefreshmentPoint: " + point.getId());
        database.put(point.getId(), point);
    }

    /**
     * Simulates updating a RefreshmentPoint in the database.
     *
     * @param point The RefreshmentPoint with updated details.
     * @throws ETOURConnectionException If the ETOUR server connection is lost.
     */
    @Override
    public void update(RefreshmentPoint point) throws ETOURConnectionException {
        // XC3: Check ETOUR connection status
        if (!networkStatusMonitor.isETOURConnected()) {
            networkStatusMonitor.notifyConnectionLoss();
            throw new ETOURConnectionException("Connection to ETOUR server interrupted during update operation.");
        }
        System.out.println("Repository: Updating RefreshmentPoint with ID: " + point.getId());
        if (database.containsKey(point.getId())) {
            database.put(point.getId(), point); // Replace with updated point
            System.out.println("DB: RefreshmentPoint " + point.getId() + " updated successfully.");
        } else {
            System.out.println("DB: RefreshmentPoint " + point.getId() + " not found for update.");
            // In a real scenario, this might throw a NotFoundException or similar
        }
    }
}