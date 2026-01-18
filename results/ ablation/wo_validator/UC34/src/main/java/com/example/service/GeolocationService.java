package com.example.service;

import com.example.domain.Coordinates;

/**
 * Concrete implementation of geolocation service.
 * In a real application this would integrate with device GPS or IP‑based location.
 */
public class GeolocationService implements IGeolocationService {
    @Override
    public Coordinates getUserPosition() {
        // Simulate getting device location.
        // For demonstration, return a fixed coordinate (e.g., center of Rome).
        Coordinates coords = new Coordinates();
        coords.setLatitude(41.9028);
        coords.setLongitude(12.4964);
        return coords;
    }
}