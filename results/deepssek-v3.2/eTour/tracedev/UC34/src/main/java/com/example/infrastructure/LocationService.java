package com.example.infrastructure;

/**
 * Interface for location serv.
 */
public interface LocationService {
    com.example.domain.GeographicCoordinate getCurrentPosition();
}