package com.example.repository;

import com.example.model.LocationDTO;
import java.util.List;

/**
 * Interface for Location Repository
 */
public interface LocationRepository {
    List<LocationDTO> findAllLocations();
}