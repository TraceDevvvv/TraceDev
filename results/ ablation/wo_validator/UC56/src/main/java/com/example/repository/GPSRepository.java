package com.example.repository;

import com.example.adapter.GPSAdapter;
import com.example.dto.Coordinate;
import com.example.dto.PositionDTO;
import java.time.LocalDateTime;

/**
 * Repository implementation that uses GPS to acquire position.
 */
public class GPSRepository implements PositionRepository {
    private GPSAdapter gpsService;

    /**
     * Constructor with dependency injection.
     * @param gpsService the GPS adapter to use
     */
    public GPSRepository(GPSAdapter gpsService) {
        this.gpsService = gpsService;
    }

    /**
     * Retrieves current position via GPS.
     * @return PositionDTO if position is detectable, otherwise null
     */
    @Override
    public PositionDTO getCurrentPosition() {
        // Call GPS adapter to get raw coordinate
        Coordinate coordinate = gpsService.calculatePosition();
        // Check if position is detectable (coordinate not null and within reasonable bounds)
        if (isPositionDetectable(coordinate)) {
            // Create PositionDTO with timestamp
            return new PositionDTO(
                coordinate.getLatitude(),
                coordinate.getLongitude(),
                coordinate.getAccuracy(),
                LocalDateTime.now()
            );
        } else {
            return null; // Position not detectable
        }
    }

    /**
     * Determines if the coordinate represents a detectable position.
     * Simplified check: ensures coordinates are within world bounds and accuracy is positive.
     */
    private boolean isPositionDetectable(Coordinate coordinate) {
        if (coordinate == null) return false;
        double lat = coordinate.getLatitude();
        double lon = coordinate.getLongitude();
        double acc = coordinate.getAccuracy();
        return (lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180) && acc > 0;
    }
}