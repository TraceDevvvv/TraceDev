package com.example.adapters;

import com.example.external.NetworkService;
import com.example.external.Coordinates;
import com.example.model.Location;
import com.example.ports.LocationProvider;

/**
 * Adapter for network service to LocationProvider port.
 */
public class NetworkLocationAdapter implements LocationProvider {
    private NetworkService networkService;

    public NetworkLocationAdapter(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Location fetchCurrentLocation() {
        Coordinates coords = networkService.getLocationFromNetwork();
        // Network location typically has lower accuracy, assume 50.0
        return new Location(coords.getLatitude(), coords.getLongitude(), 50.0);
    }

    @Override
    public boolean isAvailable() {
        return networkService.hasNetworkCoverage();
    }
}