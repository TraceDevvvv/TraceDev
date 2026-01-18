package com.example.service;

/**
 * Service to get the current user's location.
 * In a real application, this might use GPS or IP geolocation.
 */
public class LocationService {
    // For simplicity, we return a fixed location (simulating user's position).
    // In production, this would be dynamic.
    public static class UserLocation {
        public double latitude;
        public double longitude;
        public UserLocation(double lat, double lon) {
            latitude = lat;
            longitude = lon;
        }
    }

    /**
     * Gets the current user's location by citing the use case location.
     * @return UserLocation object with latitude and longitude.
     */
    public UserLocation getCurrentUserLocation() {
        // Simulating location retrieval. Could throw exception if service is unavailable.
        return new UserLocation(45.4642, 9.1900); // Milan, Italy coordinates
    }
}