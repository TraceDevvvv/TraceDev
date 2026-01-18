package com.example.adapters;

import com.example.dto.PositionData;
import com.example.exceptions.GpsUnavailableException;

import java.time.LocalDateTime;

/**
 * Concrete adapter for actual GPS hardware.
 * Simulates GPS hardware interaction.
 */
public class GpsHardwareAdapter implements IGpsAdapter {
    // Simulate GPS availability (can be toggled for testing)
    private static boolean gpsAvailable = true;
    private static double simulatedLatitude = 40.7128;
    private static double simulatedLongitude = -74.0060;

    public static void setGpsAvailable(boolean available) {
        gpsAvailable = available;
    }

    public static void setSimulatedPosition(double lat, double lon) {
        simulatedLatitude = lat;
        simulatedLongitude = lon;
    }

    @Override
    public PositionData calculatePosition() {
        // Simulate hardware delay (but within 5 seconds)
        try {
            Thread.sleep(100); // 100ms delay for simulation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // According to sequence diagram: GPS may be undetectable
        if (!gpsAvailable) {
            throw new GpsUnavailableException("GPS hardware not detected or timed out");
        }

        // Return simulated position data
        return new PositionData(simulatedLatitude, simulatedLongitude, LocalDateTime.now());
    }
}