package com.example.ports;

import com.example.model.Location;

/**
 * Port for location persistence.
 */
public interface LocationRepository {
    void save(Location location, String touristId);
    Location findLatest(String touristId);
}