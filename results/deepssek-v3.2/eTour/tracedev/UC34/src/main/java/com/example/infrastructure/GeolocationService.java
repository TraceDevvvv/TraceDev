package com.example.infrastructure;

/**
 * Service to obtain the current geographic position.
 */
public class GeolocationService implements LocationService {
    @Override
    public com.example.domain.GeographicCoordinate getCurrentPosition() {
        // Mock implementation: returns fixed coordinates (e.g., Rome)
        return new com.example.domain.GeographicCoordinate(41.9028, 12.4964);
    }
}