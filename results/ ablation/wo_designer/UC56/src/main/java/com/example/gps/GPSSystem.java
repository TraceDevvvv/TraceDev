package com.example.gps;

/**
 * Simulates a GPS system that can calculate the current position.
 * In a real system, this would interface with hardware or a GPS service.
 */
public class GPSSystem {
    /**
     * Simulates calculating the current position.
     * This method can randomly return a position or throw a GPSException
     * if the position is not detectable.
     *
     * @return the current GPS position
     * @throws GPSException if the position is not detectable
     */
    public GPSPosition calculateCurrentPosition() throws GPSException {
        // Simulate GPS signal detection with 80% success rate
        if (Math.random() > 0.2) {
            // Generate a random position for simulation
            double lat = 37.7749 + (Math.random() - 0.5) * 0.1;
            double lon = -122.4194 + (Math.random() - 0.5) * 0.1;
            return new GPSPosition(lat, lon);
        } else {
            throw new GPSException("GPS position not detectable.");
        }
    }
}