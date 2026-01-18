package com.example.strategies;

import com.example.adapters.IGpsAdapter;
import com.example.dto.PositionData;
import com.example.exceptions.GpsUnavailableException;

import java.time.LocalDateTime;

/**
 * Strategy that uses GPS hardware to fetch position.
 * Throws GpsUnavailableException if GPS is not available or times out.
 */
public class GpsPositionStrategy implements IPositionStrategy {
    private IGpsAdapter gpsAdapter;

    public GpsPositionStrategy(IGpsAdapter adapter) {
        this.gpsAdapter = adapter;
    }

    @Override
    public PositionData fetchPosition() {
        try {
            // According to sequence diagram: GpsStrategy -> GpsAdapter: calculatePosition()
            PositionData data = gpsAdapter.calculatePosition();
            if (data == null) {
                // Simulate timeout/error scenario
                throw new GpsUnavailableException("GPS hardware returned null data");
            }
            return data;
        } catch (GpsUnavailableException e) {
            // Re-throw to be handled by the service layer (fallback)
            throw e;
        } catch (Exception e) {
            // Wrap any other exception
            throw new GpsUnavailableException("GPS strategy failed: " + e.getMessage());
        }
    }
}