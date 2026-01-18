package com.example.repository;

import com.example.dto.PositionDTO;

/**
 * Repository interface for acquiring position data.
 * Abstracts the location acquisition source.
 */
public interface PositionRepository {
    /**
     * Retrieves the current position.
     * @return PositionDTO containing position data, or null if not available
     */
    PositionDTO getCurrentPosition();
}