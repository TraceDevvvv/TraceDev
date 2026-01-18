package com.example.serv;

import com.example.dto.PositionResult;

/**
 * Service interface for position-related operations.
 */
public interface IPositionService {
    /**
     * Gets the current position.
     * @return PositionResult containing success status and data.
     */
    PositionResult getPosition();
}