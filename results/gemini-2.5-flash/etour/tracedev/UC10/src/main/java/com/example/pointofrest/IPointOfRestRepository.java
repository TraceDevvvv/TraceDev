package com.example.pointofrest;

import java.util.List;

/**
 * Interface for accessing PointOfRest data.
 * Defines the contract for fetching PointOfRest entities from a data source.
 */
public interface IPointOfRestRepository {

    /**
     * Finds a PointOfRest entity by its unique identifier.
     *
     * @param id The unique identifier of the PointOfRest.
     * @return The found PointOfRest entity.
     * @throws Exception if an error occurs during data retrieval or mapping.
     */
    PointOfRest findById(String id) throws Exception;

    /**
     * Finds all available PointOfRest entities.
     * Added to satisfy requirement Flow of Events: 1. Agency Operator views the list of points of rest as a result of the use case RicercaPuntoDiRistoro.
     *
     * @return A list of all PointOfRest entities.
     * @throws Exception if an error occurs during data retrieval.
     */
    List<PointOfRest> findAll() throws Exception;
}