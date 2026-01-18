package com.example.application;

import com.example.dto.PreferencesDTO;
import com.example.application.Result;

/**
 * Command interface for updating preferences.
 * Part of CQRS pattern.
 */
public interface UpdatePreferencesCommand {
    Result execute(String touristId, PreferencesDTO preferencesData);
}