package com.example.data;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Interface for ETOUR server data source operations.
 */
public interface IETourServerDataSource {
    Connection connect();
    void disconnect();
    ResultSet executeQuery(String sql);
    void handleConnectionError();
}