package com.example.adapters;

import com.example.external.GPSDevice;
import com.example.external.Coordinates;
import com.example.model.Location;
import com.example.ports.LocationProvider;

/**
 * Adapter for GPS device to LocationProvider port.
 */
public class GPSLocationAdapter implements LocationProvider {
    private GPSDevice gpsDevice;

    public GPSLocationAdapter(GPSDevice gpsDevice) {
        this.gpsDevice = gpsDevice;
    }

    @Override
    public Location fetchCurrentLocation() {
        // GPS calculates position (step 2 in flow of events)
        Coordinates coords = gpsDevice.getCurrentCoordinates();
        // Convert coordinates to Location with default accuracy 1.0
        return new Location(coords.getLatitude(), coords.getLongitude(), 1.0);
    }

    @Override
    public boolean isAvailable() {
        // Check if GPS is detectable (requirement REQ-010)
        return gpsDevice.isSignalAvailable();
    }
}