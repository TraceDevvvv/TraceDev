package com.example.infrastructure;

import com.example.application.interfaces.ILocationService;

/**
 * Implementation of location service using a GeoApiClient.
 */
public class LocationServiceImpl implements ILocationService {
    private GeoApiClient geoApiClient;

    public LocationServiceImpl(GeoApiClient geoApiClient) {
        this.geoApiClient = geoApiClient;
    }

    @Override
    public String getCurrentLocation(String userId) {
        // Simulate getting location from external API
        return geoApiClient.getCurrentLocation(userId);
    }

    @Override
    public double calculateDistance(String location1, String location2) {
        if (location1 == null || location2 == null) {
            return 0.0;
        }
        String[] loc1Parts = location1.split(",");
        String[] loc2Parts = location2.split(",");
        if (loc1Parts.length < 2 || loc2Parts.length < 2) {
            return 0.0;
        }
        try {
            double lat1 = Double.parseDouble(loc1Parts[0]);
            double lon1 = Double.parseDouble(loc1Parts[1]);
            double lat2 = Double.parseDouble(loc2Parts[0]);
            double lon2 = Double.parseDouble(loc2Parts[1]);
            final int R = 6371;
            double latDistance = Math.toRadians(lat2 - lat1);
            double lonDistance = Math.toRadians(lon2 - lon1);
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return R * c;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}