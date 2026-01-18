package com.example.service;

import com.example.model.Location;

/**
 * Port interface for observers to be notified of location updates.
 */
public interface LocationObserver {
    void onLocationUpdate(Location location);
}