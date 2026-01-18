package com.example.service;

import com.example.model.GenericPreference;

/**
 * Interface for querying preferences.
 */
public interface PreferenceQueryService {
    GenericPreference fetchPreferences(String touristId);
}