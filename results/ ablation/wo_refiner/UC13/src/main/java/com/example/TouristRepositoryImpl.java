package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of TouristRepository with a simulated server connection.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private ETOURServerConnection connection;
    // Simulated in‑memory storage
    private List<Tourist> tourists = new ArrayList<>();

    public TouristRepositoryImpl(ETOURServerConnection connection) {
        this.connection = connection;
        // Initialize with some sample tourists for demonstration
        tourists.add(new Tourist(1, "Alice", true));
        tourists.add(new Tourist(2, "Bob", false));
        tourists.add(new Tourist(3, "Charlie", true));
    }

    @Override
    public Tourist findById(int touristId) {
        // Simulate repository operation, with possible error
        if (!connection.isConnected()) {
            throw new RuntimeException("RepositoryException: Not connected to server");
        }
        for (Tourist t : tourists) {
            if (t.getId() == touristId) {
                return t;
            }
        }
        return null; // Tourist not found
    }

    @Override
    public void save(Tourist tourist) {
        // Simulate save operation, with possible error
        if (!connection.isConnected()) {
            throw new RuntimeException("SaveException: Connection lost during save");
        }
        // In a real implementation, update the tourist in the storage
        // For simplicity, we assume the tourist object in the list is mutable and already updated.
        System.out.println("TouristRepositoryImpl: Tourist saved (ID: " + tourist.getId() + ")");
    }

    @Override
    public List<Tourist> getActiveTourists() {
        List<Tourist> active = new ArrayList<>();
        for (Tourist t : tourists) {
            if (t.isActive()) {
                active.add(t);
            }
        }
        return active;
    }

    @Override
    public List<Tourist> getInactiveTourists() {
        List<Tourist> inactive = new ArrayList<>();
        for (Tourist t : tourists) {
            if (!t.isActive()) {
                inactive.add(t);
            }
        }
        return inactive;
    }
}