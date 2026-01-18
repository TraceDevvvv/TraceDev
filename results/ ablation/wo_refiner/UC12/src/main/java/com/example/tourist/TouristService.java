package com.example.tourist;

/**
 * Service interface for tourist-related business operations.
 */
public interface TouristService {
    /**
     * Gets tourist details and returns as DTO.
     * @param id the tourist ID
     * @return TouristDTO containing tourist details
     */
    TouristDTO getTouristDetails(String id);
}