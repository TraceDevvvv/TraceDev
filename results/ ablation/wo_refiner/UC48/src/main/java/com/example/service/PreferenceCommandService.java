package com.example.service;

import com.example.model.GenericPreference;

/**
 * Interface for commanding preference modifications.
 * Added to satisfy requirement 13.
 */
public interface PreferenceCommandService {
    boolean modifyPreferences(GenericPreference preference);
    void confirmModification(String confirmationId);
    void cancelModification(String operationId);
}