package com.example.repository;

import com.example.data.DataAccessException;
import com.example.data.IDataSource;
import com.example.model.PointOfRest;
import com.example.model.PointOfRestEntity;

/**
 * Repository responsible for retrieving PointOfRest domain objects.
 * Implements IDataSource (as per class diagram) and delegates to concrete data source.
 */
public class PointOfRestRepository implements IDataSource {
    private IDataSource dataSource;

    public PointOfRestRepository(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Finds a PointOfRest by its ID.
     * Delegates to the data source and maps the entity to domain model.
     * @param id the point of rest ID
     * @return PointOfRest domain object
     * @throws DataAccessException if data retrieval fails
     */
    public PointOfRest findByID(String id) throws DataAccessException {
        PointOfRestEntity entity = fetchPointOfRest(id);
        return mapToDomainModel(entity);
    }

    /**
     * Maps PointOfRestEntity to PointOfRest domain model.
     */
    private PointOfRest mapToDomainModel(PointOfRestEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.toDomainModel();
    }

    /**
     * Implements IDataSource interface by delegating to the concrete data source.
     * Handles connection errors as per sequence diagram.
     */
    @Override
    public PointOfRestEntity fetchPointOfRest(String id) throws DataAccessException {
        try {
            // Delegate to the external data source
            return dataSource.fetchPointOfRest(id);
        } catch (DataAccessException e) {
            // Handle connection error as per sequence diagram
            handleConnectionError();
            throw new DataAccessException("Server unavailable", e);
        }
    }

    /**
     * Handles connection errors (log, retry logic, etc.).
     * Placeholder for actual error handling.
     */
    private void handleConnectionError() {
        // In a real scenario, might log the error or implement retry logic
        System.err.println("Connection to data source interrupted.");
    }
}