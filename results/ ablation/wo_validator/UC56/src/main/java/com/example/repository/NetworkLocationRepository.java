package com.example.repository;

import com.example.adapter.NetworkLocationAdapter;
import com.example.dto.Coordinate;
import com.example.dto.PositionDTO;
import java.time.LocalDateTime;

/**
 * Repository implementation that uses network location.
 */
public class NetworkLocationRepository implements PositionRepository {
    private NetworkLocationAdapter locationService;

    public NetworkLocationRepository(NetworkLocationAdapter locationService) {
        this.locationService = locationService;
    }

    @Override
    public PositionDTO getCurrentPosition() {
        Coordinate coordinate = locationService.calculatePosition();
        if (isPositionDetectable(coordinate)) {
            return new PositionDTO(
                coordinate.getLatitude(),
                coordinate.getLongitude(),
                coordinate.getAccuracy(),
                LocalDateTime.now()
            );
        } else {
            return null;
        }
    }

    private boolean isPositionDetectable(Coordinate coordinate) {
        if (coordinate == null) return false;
        double lat = coordinate.getLatitude();
        double lon = coordinate.getLongitude();
        double acc = coordinate.getAccuracy();
        return (lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180) && acc > 0;
    }
}