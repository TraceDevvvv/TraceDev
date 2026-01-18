package com.example.external;

/**
 * External GPS device (hardware or service).
 */
public class GPSDevice {
    private String manufacturer;
    private String model;

    public GPSDevice(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Coordinates getCurrentCoordinates() {
        // Simulate GPS coordinate acquisition
        return new Coordinates(48.8566, 2.3522); // Paris coordinates
    }

    public boolean isSignalAvailable() {
        // Assume GPS signal is available
        return true;
    }
}