package com.example.etour.interfaces;

import com.example.etour.entities.RefreshmentPoint;

/**
 * Repository interface for refreshment point persistence operations.
 */
public interface RefreshmentPointRepository {
    RefreshmentPoint findById(String id);
    boolean delete(String id);
}