package com.example.repository;

import com.example.model.Location;
import java.util.List;

/**
 * Repository interface for Location entities.
 */
public interface LocationRepository {
    List<Location> findAll();
    Location findById(String id) throws com.example.exception.ConnectionException;
}