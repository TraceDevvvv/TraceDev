package com.example.adapters;

import com.example.model.Location;
import com.example.external.Database;
import com.example.ports.LocationRepository;

/**
 * Adapter for database to LocationRepository port.
 */
public class LocationRepositoryImpl implements LocationRepository {
    private Database database;

    public LocationRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public void save(Location location, String touristId) {
        database.saveLocationData(location);
    }

    @Override
    public Location findLatest(String touristId) {
        return database.queryLocationData(touristId);
    }
}