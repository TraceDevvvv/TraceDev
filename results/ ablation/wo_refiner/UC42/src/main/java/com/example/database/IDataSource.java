package com.example.database;

import java.sql.ResultSet;

/**
 * Interface for database operations.
 */
public interface IDataSource {
    ResultSet executeQuery(String query);
    int executeUpdate(String query);
    void beginTransaction();
    void commit();
    void rollback();
}