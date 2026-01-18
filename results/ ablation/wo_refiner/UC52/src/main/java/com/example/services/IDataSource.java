package com.example.serv;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data source abstraction for database operations.
 */
public interface IDataSource {
    ResultSet executeQuery(String query) throws SQLException;
    boolean isConnected();
}