package com.example.adapters;

import com.example.dto.PositionData;

/**
 * Adapter interface for GPS hardware.
 * Quality requirement: duration ≤ 5s
 */
public interface IGpsAdapter {
    /**
     * Calculates current position using GPS hardware.
     * Should complete within 5 seconds.
     * @return PositionData object with coordinates from GPS.
     */
    PositionData calculatePosition();
}