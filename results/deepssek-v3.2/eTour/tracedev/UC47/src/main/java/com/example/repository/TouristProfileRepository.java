
package com.example.repository;

import com.example.model.TouristProfileEntity;

/**
 * Repository implementation for tourist profile database operations.
 */
public class TouristProfileRepository implements ITouristProfileRepository {
    private Object dataSource;
    private Object connectionMonitor;

    // Constructor with dependency injection
    public TouristProfileRepository(Object dataSource, Object connectionMonitor) {
        this.dataSource = dataSource;
        this.connectionMonitor = connectionMonitor;
    }

    @Override
    public TouristProfileEntity findById(String id) {
        // Step in Sequence Diagram: SELECT * FROM tourist_profiles
        // For simplicity, we simulate database access
        System.out.println("[Repository] Executing SELECT * FROM tourist_profiles WHERE id = " + id);
        
        // Simulate database lookup
        // In real implementation, this would use JDBC or JPA
        // For demonstration, return a mock entity if ID matches
        if ("TOUR123".equals(id) || "TEST123".equals(id)) {
            TouristProfileEntity entity = new TouristProfileEntity();
            entity.setId(id);
            entity.setName("John Doe");
            entity.setEmail("john@example.com");
            entity.setPhone("+1234567890");
            return entity;
        }
        
        // Return null if not found (simulating database behavior)
        return null;
    }

    @Override
    public boolean save(TouristProfileEntity entity) {
        // Check connection before attempting save
        if (connectionMonitor != null) {
            System.out.println("[Repository] Connection check failed before save");
            return false;
        }
        
        // Step in Sequence Diagram: UPDATE tourist_profiles
        System.out.println("[Repository] Executing UPDATE tourist_profiles SET " +
                          "name='" + entity.getName() + "', " +
                          "email='" + entity.getEmail() + "', " +
                          "phone='" + entity.getPhone() + "', " +
                          "modifiedAt=" + entity.getModifiedAt() +
                          " WHERE id='" + entity.getId() + "'");
        
        // Simulate database update
        // In real implementation, this would use JDBC or JPA
        // For demonstration, always return true to simulate successful save
        boolean success = true;
        
        // Simulate occasional connection error (for testing)
        if (Math.random() < 0.2) { // 20% chance of simulated connection error
            System.out.println("[Repository] Simulated connection lost during save");
            success = false;
        }
        
        return success;
    }

    // Getters and setters for dependencies
    public Object getDataSource() {
        return dataSource;
    }

    public void setDataSource(Object dataSource) {
        this.dataSource = dataSource;
    }

    public Object getConnectionMonitor() {
        return connectionMonitor;
    }

    public void setConnectionMonitor(Object connectionMonitor) {
        this.connectionMonitor = connectionMonitor;
    }
}
