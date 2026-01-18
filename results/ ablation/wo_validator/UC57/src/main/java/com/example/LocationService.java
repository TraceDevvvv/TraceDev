package com.example;

/**
 * Service interface for obtaining location information.
 */
public interface LocationService {
    /**
     * Gets the current position.
     * @return the current location.
     */
    Location getCurrentPosition();
}