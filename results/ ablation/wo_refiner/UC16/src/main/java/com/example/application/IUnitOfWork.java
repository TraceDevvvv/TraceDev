package com.example.application;

/**
 * Interface for Unit of Work abstraction.
 */
public interface IUnitOfWork {
    boolean commit();
    void rollback();
}