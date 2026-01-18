package com.example.hardware;

import com.example.model.GPSRawData;

/**
 * Represents an external GPS hardware sensor.
 */
public class GPSSensor {
    private boolean active = true;

    public GPSSensor() {
        // Default constructor
    }

    /**
     * Simulates reading raw data from the GPS sensor.
     * In a real implementation, this would interface with hardware.
     */
    public GPSRawData readRawData() {
        if (!isActive()) {
            return null;
        }
        // Simulate raw data (could be random or from actual sensor)
        String lat = "40.7128";
        String lon = "-74.0060";
        int strength = 85;
        int satellites = 8;
        return new GPSRawData(lat, lon, strength, satellites);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}