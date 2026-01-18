package com.example.service;

import com.example.dto.TouristDTO;
import com.example.validation.ValidationResult;

/**
 * Service interface for tourist operations.
 */
public interface ITouristService {
    TouristDTO loadTouristData(String userId);
    ValidationResult validateTouristData(TouristDTO data);
    boolean saveTouristData(TouristDTO data);
    boolean retry(); // Added for REQ-014
}