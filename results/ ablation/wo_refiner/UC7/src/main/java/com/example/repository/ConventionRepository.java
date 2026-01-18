package com.example.repository;

import com.example.model.Convention;

/**
 * Port interface for convention data access operations.
 */
public interface ConventionRepository {
    Convention findById(String id);
    Convention save(Convention convention);
}