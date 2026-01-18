package com.example.database;

/**
 * Interface for unit of work pattern ensuring transaction management.
 * <<Transaction>> stereotype.
 */
public interface IUnitOfWork {
    void commit();
    void rollback();
    void beginTransaction();
}