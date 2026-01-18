package com.example.repository;

import com.example.model.LocationDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Location Repository
 */
public class LocationRepositoryImpl implements LocationRepository {
    
    @Override
    public List<LocationDTO> findAllLocations() {
        // Simulating database call - in real implementation, this would connect to actual data source
        List<LocationDTO> locations = new ArrayList<>();
        
        // Sample data for demonstration
        LocationDTO location1 = new LocationDTO();
        location1.setId("LOC001");
        location1.setName("Central Park");
        location1.setType("Park");
        locations.add(location1);
        
        LocationDTO location2 = new LocationDTO();
        location2.setId("LOC002");
        location2.setName("Downtown Mall");
        location2.setType("Shopping");
        locations.add(location2);
        
        return locations;
    }
}