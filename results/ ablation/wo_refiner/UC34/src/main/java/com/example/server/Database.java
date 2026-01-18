package com.example.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 * Represents the Database class from the class diagram.
 */
public class Database {
    private DataSource dataSource;

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}