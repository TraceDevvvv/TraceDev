package com.example.newsapp.repo;

import com.example.newsapp.common.ConnectionException;
import com.example.newsapp.domain.News;

import java.util.List;

/**
 * Interface for the News Repository, defining the contract for data access operations
 * on News entities. It abstracts the underlying data storage mechanism.
 * All methods are specified to throw ConnectionException to handle potential
 * database/server connectivity issues.
 */
public interface INewsRepository {

    /**
     * Retrieves all news items from the data store.
     *
     * @return A list of all News entities.
     * @throws ConnectionException if there is a problem connecting to the data source.
     */
    List<News> findAll() throws ConnectionException;

    /**
     * Retrieves a specific news item by its unique identifier.
     *
     * @param id The ID of the news item to retrieve.
     * @return The News entity corresponding to the given ID, or null if not found.
     * @throws ConnectionException if there is a problem connecting to the data source.
     */
    News findById(String id) throws ConnectionException;

    /**
     * Saves a new news item to the data store.
     *
     * @param news The News entity to save.
     * @return The saved News entity, potentially with an updated ID or other generated fields.
     *         Note: The sequence diagram does not explicitly mention ConnectionException for save,
     *         but in a real scenario, it would also be possible. For strict adherence to the diagram,
     *         it's omitted here but might be added in a production system.
     */
    News save(News news);

    /**
     * Updates an existing news item in the data store.
     *
     * @param news The News entity with updated information.
     * @return The updated News entity.
     * @throws ConnectionException if there is a problem connecting to the data source.
     */
    News update(News news) throws ConnectionException;
}