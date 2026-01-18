package com.example.repository;

import com.example.model.Location;
import com.example.exception.ConnectionException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of LocationRepository.
 * Throws ConnectionException as per REQ-011.
 */
public class LocationRepositoryImpl implements LocationRepository {
    private DataSource dataSource;

    // Constructor with DataSource dependency (assumed for persistence)
    public LocationRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Location> findAll() {
        // Simulated data for demonstration.
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("1", "Park A", "A beautiful park"));
        locations.add(new Location("2", "Museum B", "Historical museum"));
        return locations;
    }

    @Override
    public Location findById(String id) throws ConnectionException {
        // Simulating a connection failure for demonstration of REQ-011.
        // For id "error", we simulate an interruption.
        if ("error".equals(id)) {
            throw new ConnectionException("Database connection lost while fetching location.");
        }
        // Otherwise return dummy location.
        return new Location(id, "Location " + id, "Description for " + id);
    }
}