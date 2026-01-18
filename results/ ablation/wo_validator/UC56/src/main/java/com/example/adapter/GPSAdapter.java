package com.example.adapter;

import com.example.dto.Coordinate;

/**
 * Adapter for GPS service to calculate position.
 * Quality Requirement: Response within 5 seconds.
 */
public class GPSAdapter {
    /**
     * Calculates the current position coordinate.
     * Simulates a GPS service call.
     * @return Coordinate object with latitude, longitude, and accuracy
     */
    public Coordinate calculatePosition() {
        // Simulate GPS calculation; in real scenario, this would call a hardware/API.
        // Assumption: returns a hardcoded coordinate for demonstration.
        // Accuracy and timestamp are generated here but passed via Coordinate DTO.
        try {
            // Simulate processing time (max 5 seconds as per requirement)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Coordinate(40.7128, -74.0060, 2.5); // Example: New York coordinates
    }
}