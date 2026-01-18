package com.system;

/**
 * Generic repository interface.
 * @param <T> the entity type.
 */
public interface IRepository<T> {
    void save(T entity);
    void delete(T entity);
    T findById(String id);
}