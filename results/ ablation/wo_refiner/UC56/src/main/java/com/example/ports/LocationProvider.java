package com.example.ports;

import com.example.model.Location;

/**
 * Port for external location providers (GPS, network).
 */
public interface LocationProvider {
    Location fetchCurrentLocation();
    boolean isAvailable();
}