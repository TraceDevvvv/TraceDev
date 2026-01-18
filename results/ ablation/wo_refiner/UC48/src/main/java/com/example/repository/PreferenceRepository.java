package com.example.repository;

import com.example.model.GenericPreference;

/**
 * Repository interface for preference persistence.
 */
public interface PreferenceRepository {
    GenericPreference save(GenericPreference preference);
    GenericPreference findById(String id);
    GenericPreference update(GenericPreference preference);
}