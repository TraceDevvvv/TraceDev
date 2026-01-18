package com.example.repository;

import com.example.model.PreferenceSet;

/**
 * Repository interface for preference persistence.
 */
public interface PreferenceRepository {
    PreferenceSet findByTouristId(String touristId);
    void save(PreferenceSet preferences);
}