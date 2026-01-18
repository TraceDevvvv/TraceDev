package com.example.service;

import com.example.model.Location;
import com.example.ports.LocationProvider;
import com.example.ports.LocationRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Core implementation of LocationService with observer support and timeout handling.
 */
public class LocationServiceImpl implements LocationService {
    private LocationProvider locationProvider;
    private LocationRepository locationRepository;
    private List<LocationObserver> observers;
    private int timeoutThreshold = 5000; // Requirement REQ-012

    public LocationServiceImpl(LocationProvider provider, LocationRepository repository) {
        this.locationProvider = provider;
        this.locationRepository = repository;
        this.observers = new ArrayList<>();
    }

    @Override
    public Location getCurrentLocation(String touristId) {
        // Try to fetch fresh location
        Location location = null;
        try {
            // Start timeout check (requirement REQ-012)
            long startTime = System.currentTimeMillis();
            location = locationProvider.fetchCurrentLocation();
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed > timeoutThreshold) {
                throw new RuntimeException("GPS response took more than 5 seconds");
            }
        } catch (Exception e) {
            // GPS may be unavailable (step 10 exception)
            System.out.println("GPS failed: " + e.getMessage());
            // Fallback to cached location
            location = locationRepository.findLatest(touristId);
            if (location == null) {
                // If no cached location, return a default location (assumption)
                location = new Location(0.0, 0.0, 1000.0);
            }
        }

        // The system is on hold until data is received (step 3)
        if (location != null) {
            // Calculate location (though already computed, may involve further processing)
            calculateLocation();
            notifyObservers(location);
            locationRepository.save(location, touristId);
        }
        return location;
    }

    /**
     * Calculate location (could involve fusion or validation).
     * In this implementation, it simply returns a new location based on provider.
     */
    @Override
    public Location calculateLocation() {
        // This method might combine multiple sources; here we just fetch from provider.
        return locationProvider.fetchCurrentLocation();
    }

    public void addObserver(LocationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LocationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Location location) {
        for (LocationObserver observer : observers) {
            observer.onLocationUpdate(location);
        }
    }

    /**
     * Check if a timeout condition has occurred (requirement REQ-012).
     * This is a simplified check; real implementation would use async timing.
     */
    public boolean checkTimeout() {
        // This method is a placeholder for actual timeout logic.
        // In the sequence diagram, it's used in the parallel timeout branch.
        return false;
    }
}