package domain;

import infrastructure.ConnectionException;

/**
 * Infrastructure Layer: Interface for the Point of Rest Repository.
 * Defines the contract for data access operations for PointOfRest entities.
 */
public interface IPointOfRestRepository {

    /**
     * Finds a PointOfRest entity by its unique identifier.
     * @param id The ID of the Point of Rest.
     * @return The PointOfRest entity if found, null otherwise.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    PointOfRest findById(String id) throws ConnectionException;

    /**
     * Saves a PointOfRest entity. This can be either an insert or an update.
     * @param point The PointOfRest entity to save.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    void save(PointOfRest point) throws ConnectionException;
}