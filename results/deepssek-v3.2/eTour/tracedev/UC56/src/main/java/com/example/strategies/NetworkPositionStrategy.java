package com.example.strategies;

import com.example.cache.PositionCache;
import com.example.dto.PositionData;

import java.time.LocalDateTime;

/**
 * Fallback strategy that uses network/cached position data.
 */
public class NetworkPositionStrategy implements IPositionStrategy {
    private PositionCache cache;

    public NetworkPositionStrategy(PositionCache cache) {
        this.cache = cache;
    }

    @Override
    public PositionData fetchPosition() {
        // According to sequence diagram: NetworkStrategy -> Cache: getLastKnownPosition()
        PositionData cachedData = cache.getLastKnownPosition();
        if (cachedData != null) {
            return cachedData;
        }
        // If no cached data available, return a default position (assumption)
        return new PositionData(0.0, 0.0, LocalDateTime.now());
    }
}