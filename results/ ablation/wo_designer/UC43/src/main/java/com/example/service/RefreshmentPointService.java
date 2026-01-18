// Service layer for business logic and data validation
package com.example.service;

import com.example.model.RefreshmentPoint;
import java.util.HashMap;
import java.util.Map;

public class RefreshmentPointService {
    // Simulating a database with an in-memory map
    private Map<String, RefreshmentPoint> refreshmentPointDatabase;

    public RefreshmentPointService() {
        refreshmentPointDatabase = new HashMap<>();
        // Initialize with sample data
        refreshmentPointDatabase.put("RP001", new RefreshmentPoint("RP001", "Mountain Cafe", "Trail Head", "Coffee and snacks", 20, "8AM-6PM"));
    }

    // Step 2: Upload refreshment point data
    public RefreshmentPoint uploadPointData(String pointId) {
        if (!refreshmentPointDatabase.containsKey(pointId)) {
            throw new IllegalArgumentException("Refreshment point with ID " + pointId + " not found.");
        }
        return refreshmentPointDatabase.get(pointId);
    }

    // Step 6: Validate the updated refreshment point data
    public boolean validatePointData(RefreshmentPoint point) {
        if (point == null) {
            return false;
        }
        if (point.getName() == null || point.getName().trim().isEmpty()) {
            return false;
        }
        if (point.getLocation() == null || point.getLocation().trim().isEmpty()) {
            return false;
        }
        if (point.getSeatingCapacity() < 0) {
            return false;
        }
        // Additional validation rules can be added here
        return true;
    }

    // Step 9: Store the modified data
    public void updatePointData(RefreshmentPoint point) {
        if (!refreshmentPointDatabase.containsKey(point.getId())) {
            throw new IllegalArgumentException("Cannot update non-existent point with ID " + point.getId());
        }
        refreshmentPointDatabase.put(point.getId(), point);
    }

    // Utility method to check if point exists (for entry condition)
    public boolean isPointAvailableForEditing(String pointId) {
        return refreshmentPointDatabase.containsKey(pointId);
    }
}