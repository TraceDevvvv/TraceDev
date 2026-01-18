package com.example.repository;

import com.example.model.SearchPreference;
import java.util.Optional;

/**
 * Repository interface for SearchPreference entities.
 */
public interface PreferenceRepository {
    Optional<SearchPreference> findById(String id);
    SearchPreference save(SearchPreference preference);
}