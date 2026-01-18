package com.example.application;

import com.example.domain.SearchPreferences;

/**
 * Query interface for retrieving preferences.
 * Part of CQRS pattern.
 */
public interface GetPreferencesQuery {
    SearchPreferences execute(String touristId);
}