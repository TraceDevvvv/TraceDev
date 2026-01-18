package com.etour.repository;

import com.etour.domain.Tourist;
import com.etour.dto.TouristDTO;
import com.etour.mapper.TouristDataMapper;
import com.etour.util.ConnectionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of TouristRepository.
 * Simulates database access with a simple in-memory map.
 * Includes connection checks.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private TouristDataMapper dataMapper;
    private ConnectionManager connectionManager;
    // Simulating a database with an in-memory map
    private static Map<String, Tourist> touristDatabase = new HashMap<>();

    static {
        // Initialize with some sample data
        touristDatabase.put("T001", new Tourist("T001", "John Doe", "john@example.com", "1234567890"));
        touristDatabase.put("T002", new Tourist("T002", "Jane Smith", "jane@example.com", "0987654321"));
    }

    public TouristRepositoryImpl(TouristDataMapper dataMapper, ConnectionManager connectionManager) {
        this.dataMapper = dataMapper;
        this.connectionManager = connectionManager;
    }

    @Override
    public Tourist findById(String id) {
        // Check connection before proceeding
        if (!connectionManager.isConnected()) {
            throw new RuntimeException("Connection to server ETOUR interrupted.");
        }
        // Simulate database query
        return touristDatabase.get(id);
    }

    @Override
    public void save(Tourist tourist) {
        // Check connection before proceeding
        if (!connectionManager.isConnected()) {
            throw new RuntimeException("Connection to server ETOUR interrupted.");
        }
        // Simulate database update
        touristDatabase.put(tourist.getId(), tourist);
        System.out.println("Tourist saved: " + tourist.getId());
    }

    /**
     * Added to satisfy requirement: Exit Conditions: The connection to the server ETOUR is interrupted.
     */
    public boolean checkConnection() {
        return connectionManager.isConnected();
    }
}