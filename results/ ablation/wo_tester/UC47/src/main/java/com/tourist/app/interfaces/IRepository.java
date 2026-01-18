package com.tourist.app.interfaces;

/**
 * Generic repository interface for data access.
 * @param <TEntity> the entity type
 * @param <TKey> the key type
 */
public interface IRepository<TEntity, TKey> {
    /**
     * Retrieves an entity by its id.
     * @param id the entity id
     * @return the entity
     */
    TEntity GetById(TKey id);

    /**
     * Updates an existing entity.
     * @param entity the entity to update
     */
    void Update(TEntity entity);
}