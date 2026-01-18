package com.example.unitofwork;

/**
 * Unit of Work interface for transaction management.
 */
public interface UnitOfWork {
    void begin();
    void commit();
    void rollback();
}