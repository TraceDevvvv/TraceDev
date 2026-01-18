package com.example.data;

/**
 * Interface for Unit of Work pattern.
 * Corresponds to IUnitOfWork in the class diagram.
 */
public interface IUnitOfWork {
    boolean commit();
    void rollback();
    void registerUpdate(Object entity);
}