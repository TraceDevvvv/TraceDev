package com.example.service;

import com.example.model.PreferenceDTO;
import com.example.model.SearchPreference;

/**
 * Interface for preference service operations.
 */
public interface PreferenceService {
    SearchPreference loadPreferences(String touristId);
    String updatePreferences(String touristId, PreferenceDTO preference);
    void confirmUpdate(String confirmationId);
    void handleConnectionInterruption();
}