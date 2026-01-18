package com.example.repository;

import com.example.model.Convention;

/**
 * Repository interface for Convention persistence operations.
 */
public interface ConventionRepository {
    Convention findById(String id);
    void save(Convention convention);
}