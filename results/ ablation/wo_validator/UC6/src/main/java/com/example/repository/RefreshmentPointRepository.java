package com.example.repository;

import com.example.model.RefreshmentPoint;
import com.example.server.ServerConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing refreshment point data.
 */
public class RefreshmentPointRepository {
    private ServerConnection connection;

    public RefreshmentPointRepository(ServerConnection connection) {
        this.connection = connection;
    }

    /**
     * Finds all refreshment points.
     *
     * @return a list of refreshment points.
     */
    public List<RefreshmentPoint> findAll() {
        // For simplicity, returns an empty list.
        // In a real implementation, this would query the server.
        return new ArrayList<>();
    }

    /**
     * Finds a refreshment point by its ID.
     *
     * @param id the ID of the refreshment point.
     * @return the refreshment point, or null if not found.
     */
    public RefreshmentPoint findById(int id) {
        // Assumption: For demonstration, we simulate returning a refreshment point.
        // In a real scenario, this would query the server.
        if (connection.isConnected()) {
            return new RefreshmentPoint(id, "Sample Rest Point", "Sample Location", "A sample description.");
        }
        return null;
    }

    /**
     * Deletes a refreshment point by its ID.
     *
     * @param id the ID of the refreshment point.
     * @return true if deletion was successful, false otherwise.
     */
    public boolean delete(int id) {
        if (!connection.isConnected()) {
            System.out.println("Cannot delete: server connection is not established.");
            return false;
        }
        // Simulate deletion.
        System.out.println("Deleting refreshment point with ID: " + id);
        return true;
    }

    /**
     * Checks if the repository is connected to the server.
     *
     * @return true if connected.
     */
    public boolean isConnected() {
        return connection != null && connection.isConnected();
    }
}