package com.example;

import java.util.List;

/**
 * Interface for accessing refreshment point data.
 */
public interface RefreshmentPointRepository {
    List<RefreshmentPoint> findAll();
    RefreshmentPoint findById(int id);
}