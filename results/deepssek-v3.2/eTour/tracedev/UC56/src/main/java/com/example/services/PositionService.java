package com.example.serv;

import com.example.dto.PositionData;
import com.example.dto.PositionResult;
import com.example.exceptions.GpsUnavailableException;
import com.example.strategies.IPositionStrategy;

/**
 * Core service that orchestrates position fetching using strategies.
 * Implements fallback logic when GPS fails.
 */
public class PositionService implements IPositionService {
    private IPositionStrategy positionStrategy;

    public PositionService(IPositionStrategy strategy) {
        this.positionStrategy = strategy;
    }

    @Override
    public PositionResult getPosition() {
        try {
            PositionData data = positionStrategy.fetchPosition();
            return new PositionResult(true, data, null);
        } catch (GpsUnavailableException e) {
            return new PositionResult(false, null, "GPS unavailable: " + e.getMessage());
        } catch (Exception e) {
            return new PositionResult(false, null, "Unexpected error: " + e.getMessage());
        }
    }
}