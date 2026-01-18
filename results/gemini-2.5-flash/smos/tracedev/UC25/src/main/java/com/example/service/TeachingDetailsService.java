package com.example.service;

import com.example.model.TeachingDTO;

/**
 * Service interface for retrieving teaching details.
 * This service is responsible for providing data required for displaying teaching information,
 * typically as a prerequisite to editing.
 */
public interface TeachingDetailsService {
    /**
     * Retrieves details of a specific teaching.
     * @param id The ID of the teaching to retrieve details for.
     * @return A TeachingDTO containing the details.
     *         Returns a dummy DTO for demonstration purposes.
     */
    TeachingDTO getTeachingDetails(String id);
}