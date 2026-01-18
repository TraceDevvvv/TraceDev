package com.example.adapter;

import com.example.dto.Coordinate;

/**
 * Adapter for network‑based location service.
 */
public class NetworkLocationAdapter {
    /**
     * Calculates the current position using network information.
     * @return Coordinate object with latitude, longitude, and accuracy
     */
    public Coordinate calculatePosition() {
        // Simulate network location acquisition.
        // Assumption: returns a coordinate; accuracy might be lower than GPS.
        try {
            Thread.sleep(800); // Network location may be faster
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Coordinate(40.7589, -73.9851, 15.0); // Example: Times Square, lower accuracy
    }
}