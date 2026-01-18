package com.example.service;

import com.example.dto.TouristProfileDTO;

/**
 * Service interface for tourist profile operations.
 */
public interface ITouristService {
    /**
     * Load tourist profile by ID.
     * Step 2 of Sequence Diagram: Service.loadTouristProfile(touristId)
     */
    TouristProfileDTO loadTouristProfile(String touristId);

    /**
     * Update tourist profile with new data.
     * Step in Sequence Diagram: Service.updateTouristProfile(touristId, profileDTO)
     */
    boolean updateTouristProfile(String touristId, TouristProfileDTO dto);
}