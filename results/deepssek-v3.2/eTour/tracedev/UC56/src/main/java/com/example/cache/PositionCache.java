package com.example.cache;

import com.example.dto.PositionData;

import java.time.LocalDateTime;

/**
 * Simple cache for storing last known position.
 */
public class PositionCache {
    private PositionData lastKnownPosition;

    public PositionCache() {
        // Initialize with a default position
        this.lastKnownPosition = new PositionData(0.0, 0.0, LocalDateTime.now());
    }

    public PositionData getLastKnownPosition() {
        return lastKnownPosition;
    }

    public void setLastKnownPosition(PositionData position) {
        this.lastKnownPosition = position;
    }
}