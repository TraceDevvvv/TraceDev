package com.example.service;

import com.example.model.Location;

/**
 * Port interface for location service (application core).
 */
public interface LocationService {
    Location getCurrentLocation(String touristId);
    Location calculateLocation();
}