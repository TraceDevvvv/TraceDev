package com.example.adapters;

import com.example.service.LocationService;
import com.example.service.LocationObserver;
import com.example.model.Location;

/**
 * Adapter acting as a controller to handle search requests and observe location updates.
 */
public class SearchController implements LocationObserver {
    private LocationService locationService;

    public SearchController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Handles a search request from a tourist.
     * Entry conditions satisfied (Tourist begins search).
     */
    public Location handleSearchRequest(String touristId) {
        // Exit conditions met when system receives position
        return locationService.getCurrentLocation(touristId);
    }

    /**
     * Observer pattern notification.
     */
    @Override
    public void onLocationUpdate(Location location) {
        // In a real application, this could trigger UI updates or further processing
        System.out.println("SearchController: Received location update at " + location.getTimestamp());
    }

    public void onLocationReceived(Location location) {
        // This method corresponds to the missing traceability item from class diagram.
        // The diagram shows a method with signature: onLocationReceived(Location location)
        // We implement it as a no-op or forward to onLocationUpdate
        onLocationUpdate(location);
    }
}