package com.example.infrastructure;

import com.example.domain.TurningPoint;
import java.util.List;

/**
 * Interface for Data Access operations related to TurningPoint entities.
 * Part of the Infrastructure Layer.
 */
public interface ITurningPointRepository {
    /**
     * Finds a TurningPoint by its unique identifier.
     * @param id The ID of the turning point.
     * @return The TurningPoint if found, null otherwise.
     */
    TurningPoint findById(String id);

    /**
     * Retrieves all available TurningPoints.
     * @return A list of all TurningPoint objects.
     */
    List<TurningPoint> findAll();
}