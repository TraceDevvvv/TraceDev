package com.example.infrastructure;

import com.example.domain.SearchPreferences;

/**
 * Repository interface for SearchPreferences domain objects.
 */
public interface PreferencesRepository {
    SearchPreferences findByTouristId(String touristId);
    void save(SearchPreferences preferences);
}