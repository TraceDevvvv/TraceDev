package com.example.repository;

import com.example.model.SearchPreferences;

/**
 * Repository interface for SearchPreferences entity.
 * Defines CRUD operations as per class diagram.
 */
public interface SearchPreferencesRepository {
    SearchPreferences findById(String preferenceId);
    SearchPreferences findByTouristId(String touristId);
    SearchPreferences save(SearchPreferences preferences);
}