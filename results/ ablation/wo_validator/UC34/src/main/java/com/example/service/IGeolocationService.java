package com.example.service;

import com.example.domain.Coordinates;

/**
 * Interface for geolocation serv.
 */
public interface IGeolocationService {
    /**
     * Retrieves the current user's geographical position.
     * @return Coordinates object with latitude and longitude
     */
    Coordinates getUserPosition();
}