package com.example.repository;

import com.example.entity.RefreshmentPoint;

/**
 * Repository interface for RefreshmentPoint entities.
 */
public interface RefreshmentPointRepository {
    RefreshmentPoint findById(String id);
    RefreshmentPoint save(RefreshmentPoint refreshmentPoint);
}