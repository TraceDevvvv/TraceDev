package com.example.repository;

import com.example.entity.Tourist;
import com.example.dto.TouristDTO;

/**
 * Repository implementation for Tourist entities.
 * Includes conversion methods between entity and DTO.
 */
public class TouristRepository implements ITouristRepository {
    // Simulated database connection
    private boolean connectionActive = true;
    
    @Override
    public Tourist findById(String touristId) {
        // Check connection per REQ-013
        if (!isConnectionActive()) {
            // In a real scenario, would throw an exception or return null
            System.err.println("Connection error while finding tourist: " + touristId);
            return null;
        }
        
        // Simulate database lookup
        // In a real implementation, this would query a database
        if ("tourist123".equals(touristId)) {
            return new Tourist("tourist123", "John Doe", "john@example.com", "1234567890", "123 Main St");
        }
        return null;
    }
    
    @Override
    public void save(Tourist tourist) {
        // Check connection per REQ-013
        if (!isConnectionActive()) {
            System.err.println("Connection error while saving tourist: " + tourist.getTouristId());
            return;
        }
        
        // Simulate database save operation
        System.out.println("Saved tourist: " + tourist.getTouristId());
    }
    
    /**
     * Convert TouristDTO to Tourist entity.
     */
    private Tourist convertToEntity(TouristDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Tourist(
            dto.getTouristId(),
            dto.getName(),
            dto.getEmail(),
            dto.getPhone(),
            dto.getAddress()
        );
    }
    
    /**
     * Convert Tourist entity to TouristDTO.
     */
    private TouristDTO convertToDTO(Tourist entity) {
        if (entity == null) {
            return null;
        }
        return new TouristDTO(
            entity.getTouristId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getAddress()
        );
    }
    
    /**
     * Check if database connection is active.
     * Added to satisfy requirement REQ-013.
     */
    private boolean isConnectionActive() {
        // In a real application, this would check database connectivity
        return connectionActive;
    }
    
    /**
     * Simulate connection interruption for testing.
     */
    public void simulateConnectionError() {
        this.connectionActive = false;
    }
    
    /**
     * Restore connection for testing.
     */
    public void restoreConnection() {
        this.connectionActive = true;
    }
}