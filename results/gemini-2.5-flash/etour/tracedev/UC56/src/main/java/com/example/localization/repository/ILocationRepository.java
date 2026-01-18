package com.example.localization.repository;

import com.example.localization.model.Location;
import java.util.concurrent.Future;

/**
 * Interface for a repository that provides location data.
 * It's designed to return a Future, indicating an asynchronous operation.
 */
public interface ILocationRepository {
    /**
     * Asynchronously retrieves the current geographical position.
     *
     * @return A Future object that will eventually hold the Location data.
     */
    Future<Location> getPosition();
}