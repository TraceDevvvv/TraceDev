package com.example.service;

import com.example.domain.SearchPreferences;

/**
 * Control service that manages confirmation requests for preference changes.
 */
public class ConfirmationService {
    // Requests confirmation for a change in preferences.
    // Returns true if confirmation is required, false otherwise.
    public boolean requestConfirmation(String touristId, SearchPreferences oldPreferences, SearchPreferences newPreferences) {
        // In a real system, this might check if changes are significant.
        // For simplicity, we require confirmation if price range changes.
        if (oldPreferences == null || newPreferences == null) {
            return false;
        }
        double oldMin = oldPreferences.getPriceRange().getMin();
        double oldMax = oldPreferences.getPriceRange().getMax();
        double newMin = newPreferences.getPriceRange().getMin();
        double newMax = newPreferences.getPriceRange().getMax();
        return (oldMin != newMin) || (oldMax != newMax);
    }
}