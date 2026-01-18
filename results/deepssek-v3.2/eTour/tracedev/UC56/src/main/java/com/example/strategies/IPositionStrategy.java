package com.example.strategies;

import com.example.dto.PositionData;

/**
 * Strategy interface for fetching position data.
 * Implementations provide different ways to obtain position (GPS, Network, etc.)
 */
public interface IPositionStrategy {
    /**
     * Fetches the current position data.
     * @return PositionData object containing coordinates and timestamp.
     */
    PositionData fetchPosition();
}