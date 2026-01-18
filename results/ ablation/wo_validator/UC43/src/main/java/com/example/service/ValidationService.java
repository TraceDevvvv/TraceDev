package com.example.service;

import com.example.dto.RefreshmentDTO;

/**
 * Service for validating refreshment data.
 */
public class ValidationService {
    /**
     * Validates the refreshment DTO data.
     * Returns true if data is valid, false otherwise.
     * Assumption: validation rules - name not empty, price >= 0, quantity >= 0.
     */
    public boolean validateRefreshmentData(RefreshmentDTO dto) {
        if (dto == null) {
            return false;
        }
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            return false;
        }
        if (dto.getPrice() < 0) {
            return false;
        }
        if (dto.getAvailableQuantity() < 0) {
            return false;
        }
        return true;
    }
}