package com.example.application;

import com.example.domain.SearchPreferences;
import com.example.infrastructure.PreferencesRepository;

/**
 * Use case for retrieving a tourist's search preferences.
 */
public class GetPreferencesUseCase {
    private PreferencesRepository preferencesRepository;

    public GetPreferencesUseCase(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    // Executes the get preferences query
    public SearchPreferences execute(String touristId) {
        return preferencesRepository.findByTouristId(touristId);
    }
}